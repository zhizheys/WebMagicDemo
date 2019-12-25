package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 */
public class HuxiuProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        List<String> requests = page.getHtml().links().all();
        page.addTargetRequests(requests);
        //page.putField("title",page.getHtml().xpath("//div[@class='clearfix neirong']//h1/text()"));
        //page.putField("content",page.getHtml().xpath("//div[@id='neirong_box']/tidyText()"));

        page.putField("title",page.getHtml().xpath("//a/@href"));
        page.putField("content",page.getHtml().xpath("//a/@href"));
        page.putField("aaa",page.getHtml().xpath("//div"));
    }

    @Override
    public Site getSite() {
        return Site.me().setDomain("www.cnblogs.com");
    }

    public static void main(String[] args) {
        Spider.create(new HuxiuProcessor()).addUrl("http://www.cnblogs.com/")
                .addPipeline(new JsonFilePipeline("E:\\Test\\aaa")).run();
    }

}
