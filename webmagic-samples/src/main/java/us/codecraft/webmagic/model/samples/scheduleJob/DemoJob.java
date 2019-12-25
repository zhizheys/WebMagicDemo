package us.codecraft.webmagic.model.samples.scheduleJob;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoJob implements Job {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException{
        System.out.println("the demo job -----------------------------");
        log.info("最近我安装了 elasticsearch head。但是访问http://localhost:9100/却提示“集群值未连接”。由于是第一次使用,所以不清楚具体情况,");
        log.error("截至 2019 年 9 月，天猫平台上的商家发布了超 9000 万款新品；过去半年，品牌仅在天猫官方旗舰店就总计收获超 9 亿新增粉丝。阿里巴巴搭建的数字商业基础设施已为数千万企业提供普惠金融支持，上万品牌享受数智化供应链服务，以中台为代表的数字化转型技术正在通过阿里云对外输出，目前已有 100 余个针对企业全面上云的定制化解决方案诞生，使得企业 IT 综合成本下降一半、创新提效 3 倍");
    }
}
