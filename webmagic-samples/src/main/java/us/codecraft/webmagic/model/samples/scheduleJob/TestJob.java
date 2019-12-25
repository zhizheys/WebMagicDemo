package us.codecraft.webmagic.model.samples.scheduleJob;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.samples.TestProcessor;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.pipeline.MysqlPipeline;

import java.util.Date;

public class TestJob implements Job {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        System.out.println("定时器任务执行" + new Date(System.currentTimeMillis()));
//        JobDataMap info = jobExecutionContext.getMergedJobDataMap();
//        System.out.println("value is " + info.get("uname"));

        System.out.println("begin parse ===============" + new Date(System.currentTimeMillis()));
        log.info("begin parse my test processor");
        Spider.create(new TestProcessor())
                .addUrl("https://my.oschina.net/flashsword")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new JsonFilePipeline("e:\\Test\\webmagic"))
                .addPipeline(new MysqlPipeline())
                .thread(5)
                .run();
        log.info("end parse my test processor");
    }

}
