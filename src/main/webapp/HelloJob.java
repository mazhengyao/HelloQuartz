package main.webapp;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {
    // ��key��ͬ
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
        // ��ӡ��ǰ��ִ��ʱ��
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current time is:" + sf.format(date));
        //��дҵ���߼�
        // System.out.println("Hello Quartz!");
        JobKey key = context.getJobDetail().getKey();
        // System.out.println("My job name and group are:"+key.getName()+":"+key.getGroup());
        TriggerKey triggerKey = context.getTrigger().getKey();
        // System.out.println("My trigger name and group are:"+triggerKey.getName()+":"+triggerKey.getGroup());
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        JobDataMap triggerJobDataMap = context.getTrigger().getJobDataMap();
        // ��ͬKEY�Ḳ�� trigger����
        // JobDataMap dataMap = context.getMergedJobDataMap();
        // ��һ�ֻ�ȡ��ʽ ��ʽ��ȡ
        // String jobMsg = jobDataMap.getString("message");
        // Float jobFloatValue = jobDataMap.getFloat("FloatJobValue");
        // String triggerMsg = triggerJobDataMap.getString("message");
        // Double triggerDoublerValue = triggerJobDataMap.getDouble("DoubleTriggerValue");
        // System.out.println("JobMsg is:" + jobMsg);
        // System.out.println("JobFloatValue is:" + jobFloatValue);
        // System.out.println("TriggerMsg is:" + triggerMsg);
        // System.out.println("TriggerDoublerValue is:" + triggerDoublerValue);
        // �ڶ��ֻ�ȡ��ʽ set��ʽ��ȡ

        // System.out.println("JobFloatValue is:" + message);
        // System.out.println("Msg is:" + FloatJobValue);
        // System.out.println("TriggerDoublerValue is:" + DoubleTriggerValue);

        Trigger trigger = context.getTrigger();
        // System.out.println("Start time is:" + trigger.getStartTime());
        // System.out.println("End time is:" + trigger.getEndTime());

    }
}
