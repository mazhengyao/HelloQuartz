package main.webapp;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 打印当前的执行时间
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current time is:" + sf.format(date));
        // 创建一个JobDetail实例，将该实例与HelloJob.class绑定
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob", "group1").usingJobData("message","hello myJob1")
                .usingJobData("FloatJobValue",3.14F).build();
        // System.out.println("jobDetails's name:" + jobDetail.getKey().getName());
        // System.out.println("jobDetails's group:" + jobDetail.getKey().getGroup());
        // System.out.println("jobDetails's jobClass:" + jobDetail.getJobClass().getName());

        // 获取距离当前时间3秒后的时间 3000毫秒
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + 3000);
        // 获取距离当前时间6秒后的时间 6000毫秒
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 6000);
        // 创建一个Trigger实例，定义该job立即执行 并且每隔两秒执行一次，直到永远
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger","group1")
                .usingJobData("message","hello myTrigger1").usingJobData("DoubleTriggerValue",2.0D)
                .startAt(startDate).endAt(endDate).withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2).repeatForever()).build();
        // 距离当前时间4秒后执行且仅执行一次
//         SimpleTrigger trigger1 = (SimpleTrigger) TriggerBuilder.newTrigger().
//                 withIdentity("myTrigger1","group1").startAt(date).withSchedule(SimpleScheduleBuilder
//                    .simpleSchedule().withIntervalInSeconds(2).withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY));
        CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
            .withIdentity("myTrigger3", "group3")
            .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *")).build();



        // 创建Scheduler实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        // 打印当前的执行时间
        // Date date = new Date();
        // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println("Current time is:" + sf.format(date));
        // System.out.println("scheduled time is:" + sf.format((scheduler.scheduleJob(jobDetail,cronTrigger))));
        scheduler.scheduleJob(jobDetail,cronTrigger);
        Thread.sleep(2000L);
        // scheduler.standby();
        // shutdown(true)表示等待所有正在执行的job执行完毕之后再关闭scheduler
        // shutdown(false) or shutdown() 表示直接关闭scheduler
        scheduler.shutdown(true);
        // Thread.sleep(3000L);
        // scheduler.start();
        System.out.println("scheduler is shut down?"+ scheduler.isShutdown());
    }
}
