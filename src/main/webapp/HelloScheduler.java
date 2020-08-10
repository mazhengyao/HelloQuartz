package main.webapp;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // ��ӡ��ǰ��ִ��ʱ��
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current time is:" + sf.format(date));
        // ����һ��JobDetailʵ��������ʵ����HelloJob.class��
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("myJob", "group1").usingJobData("message","hello myJob1")
                .usingJobData("FloatJobValue",3.14F).build();
        // System.out.println("jobDetails's name:" + jobDetail.getKey().getName());
        // System.out.println("jobDetails's group:" + jobDetail.getKey().getGroup());
        // System.out.println("jobDetails's jobClass:" + jobDetail.getJobClass().getName());

        // ��ȡ���뵱ǰʱ��3����ʱ�� 3000����
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() + 3000);
        // ��ȡ���뵱ǰʱ��6����ʱ�� 6000����
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 6000);
        // ����һ��Triggerʵ���������job����ִ�� ����ÿ������ִ��һ�Σ�ֱ����Զ
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger","group1")
                .usingJobData("message","hello myTrigger1").usingJobData("DoubleTriggerValue",2.0D)
                .startAt(startDate).endAt(endDate).withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2).repeatForever()).build();
        // ���뵱ǰʱ��4���ִ���ҽ�ִ��һ��
//         SimpleTrigger trigger1 = (SimpleTrigger) TriggerBuilder.newTrigger().
//                 withIdentity("myTrigger1","group1").startAt(date).withSchedule(SimpleScheduleBuilder
//                    .simpleSchedule().withIntervalInSeconds(2).withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY));
        CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
            .withIdentity("myTrigger3", "group3")
            .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *")).build();



        // ����Schedulerʵ��
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        // ��ӡ��ǰ��ִ��ʱ��
        // Date date = new Date();
        // SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // System.out.println("Current time is:" + sf.format(date));
        // System.out.println("scheduled time is:" + sf.format((scheduler.scheduleJob(jobDetail,cronTrigger))));
        scheduler.scheduleJob(jobDetail,cronTrigger);
        Thread.sleep(2000L);
        // scheduler.standby();
        // shutdown(true)��ʾ�ȴ���������ִ�е�jobִ�����֮���ٹر�scheduler
        // shutdown(false) or shutdown() ��ʾֱ�ӹر�scheduler
        scheduler.shutdown(true);
        // Thread.sleep(3000L);
        // scheduler.start();
        System.out.println("scheduler is shut down?"+ scheduler.isShutdown());
    }
}
