package main.webapp;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {
    // 与key相同
    private String message;
    private Float FloatJobValue;
    private Double DoubleTriggerValue;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Float getFloatJobValue() {
        return FloatJobValue;
    }

    public void setFloatJobValue(Float floatJobValue) {
        FloatJobValue = floatJobValue;
    }

    public Double getDoubleTriggerValue() {
        return DoubleTriggerValue;
    }

    public void setDoubleTriggerValue(Double doubleTriggerValue) {
        DoubleTriggerValue = doubleTriggerValue;
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印当前的执行时间
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current time is:" + sf.format(date));
        //编写业务逻辑
        // System.out.println("Hello Quartz!");
        JobKey key = context.getJobDetail().getKey();
        // System.out.println("My job name and group are:"+key.getName()+":"+key.getGroup());
        TriggerKey triggerKey = context.getTrigger().getKey();
        // System.out.println("My trigger name and group are:"+triggerKey.getName()+":"+triggerKey.getGroup());
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        JobDataMap triggerJobDataMap = context.getTrigger().getJobDataMap();
        // 相同KEY会覆盖 trigger优先
        // JobDataMap dataMap = context.getMergedJobDataMap();
        // 第一种获取方式 显式获取
        // String jobMsg = jobDataMap.getString("message");
        // Float jobFloatValue = jobDataMap.getFloat("FloatJobValue");
        // String triggerMsg = triggerJobDataMap.getString("message");
        // Double triggerDoublerValue = triggerJobDataMap.getDouble("DoubleTriggerValue");
        // System.out.println("JobMsg is:" + jobMsg);
        // System.out.println("JobFloatValue is:" + jobFloatValue);
        // System.out.println("TriggerMsg is:" + triggerMsg);
        // System.out.println("TriggerDoublerValue is:" + triggerDoublerValue);
        // 第二种获取方式 set方式获取

        // System.out.println("JobFloatValue is:" + message);
        // System.out.println("Msg is:" + FloatJobValue);
        // System.out.println("TriggerDoublerValue is:" + DoubleTriggerValue);

        Trigger trigger = context.getTrigger();
        // System.out.println("Start time is:" + trigger.getStartTime());
        // System.out.println("End time is:" + trigger.getEndTime());

    }
}
