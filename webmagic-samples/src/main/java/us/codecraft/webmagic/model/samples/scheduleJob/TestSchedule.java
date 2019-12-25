package us.codecraft.webmagic.model.samples.scheduleJob;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestSchedule {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {

        try {
            TestSchedule testSchedule = new TestSchedule();
            testSchedule.runProcessorJob();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void runProcessorJob() throws Exception {
        log.info("begin parse +++++++++++");
        log.error("parse error ++++++++++++");

        SchedulerFactory sf = new StdSchedulerFactory();
        //2.从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();
        //3.创建JobDetail(作业信息)
        JobDetail jb = JobBuilder.newJob(TestJob.class)
                .withDescription("this is a test job") //job的描述
                .withIdentity("testJob", "testGroup") //job 的name和group
                .build();

        //向任务传递数据
        JobDataMap jobDataMap = jb.getJobDataMap();
        jobDataMap.put("uname", "张三");

        Trigger tr = TriggerBuilder.newTrigger()
                .withDescription("test trigger")
                .withIdentity("ramTrigger", "ramTriggerGroup")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
                .build();

        scheduler.scheduleJob(jb, tr);

        //job2 and trigger 2
        JobDetail demoJb = JobBuilder.newJob(DemoJob.class)
                .withDescription("this is the demo job")
                .withIdentity("demoJob", "demoGroup")
                .build();

        Trigger demoTr = TriggerBuilder.newTrigger()
                .withDescription("demo trigger")
                .withIdentity("demo trigger", "demo trigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?"))
                .build();
        scheduler.scheduleJob(demoJb, demoTr);
        scheduler.start();
    }

}
