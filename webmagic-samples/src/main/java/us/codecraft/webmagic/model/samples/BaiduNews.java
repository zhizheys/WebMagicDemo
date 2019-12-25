package us.codecraft.webmagic.model.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.example.BaiduBaikePageProcessor;
import us.codecraft.webmagic.samples.AngularJSProcessor;

/**
 * @author code4crafter@gmail.com
 */
public class BaiduNews {

    @ExtractBy("//a/text()")
    private String name;

    @ExtractBy("//div/text()")
    private String description;

    @Override
    public String toString() {
        return "BaiduNews{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static void main(String[] args) {
//        OOSpider ooSpider = OOSpider.create(Site.me().setSleepTime(0), BaiduNews.class);
//        //single download
//        BaiduNews baike = ooSpider.<BaiduNews>get("http://news.baidu.com/ns?tn=news&cl=2&rn=20&ct=1&fr=bks0000&ie=utf-8&word=httpclient");
//        System.out.println(baike);

        Spider.create(new BaiduBaikePageProcessor())
                .addUrl("https://www.cnblogs.com/")
                .addPipeline(new JsonFilePipeline("E:\\Test\\aaa"))
                .run();

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}