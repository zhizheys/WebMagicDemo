package us.codecraft.webmagic.model.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.entity.InfoEntity;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.pipeline.MysqlPipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

public class TestProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page){
        Html html = page.getHtml();
        List<Selectable> nodes = html.xpath("//div[@class='ui relaxed divided items list-container space-list-container']/div[@class='item blog-item']/div[@class='content']").nodes();
        List<InfoEntity> infoEntityList = new ArrayList<InfoEntity>();

        for(Selectable node : nodes){
            String title = node.xpath("//a[@class='header']/text()").toString();
            String description = node.xpath("//div[@class='description']/p/text()").toString();

            InfoEntity infoEntity = new InfoEntity();
            infoEntity.setTitle(title);
            infoEntity.setDescription(description);
            infoEntityList.add(infoEntity);
        }

        page.putField("dataInfo",infoEntityList);

    }

    @Override
    public Site getSite(){
        return site;
    }

    public static void main(String[] args){
        System.out.println("begin parse ===============");
        Spider.create(new TestProcessor())
                .addUrl("https://my.oschina.net/flashsword")
                .addPipeline(new ConsolePipeline())
                .addPipeline(new JsonFilePipeline("e:\\Test\\webmagic"))
                .addPipeline(new MysqlPipeline())
                .thread(5)
                .run();
        System.out.println("end parse +++++++++++++");
    }

}
