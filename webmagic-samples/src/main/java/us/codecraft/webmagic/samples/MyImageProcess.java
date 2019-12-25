package us.codecraft.webmagic.samples;

import org.jsoup.helper.StringUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.monitor.SpiderMonitor;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.management.JMException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MyImageProcess implements PageProcessor {

    //页面URL的正则表达式
    //.是匹配所有的字符，//.表示只匹配一个，//.?同理
    private static String REGEX_PAGE_URL = "http://www\\.521609\\.com/daxuexiaohua/list\\w+.html";
    //爬取的页数
    public static int PAGE_SIZE = 358;
    //下载张数
    public static int INDEX_PHOTO =0;

    //配置
    @Override
    public Site getSite() {
        return Site.me();
    }

    @Override
    public void process(Page page) {
        List<String> targetURL = new ArrayList<String>();

        for (int i = 310; i < PAGE_SIZE; i++){
            //添加到目标url中
            targetURL.add("http://www.521609.com/daxuexiaohua/list" + i + ".html");
        }

        //添加url到请求中
        page.addTargetRequests(targetURL);

        //是图片列表页面
        if (page.getUrl().regex(REGEX_PAGE_URL).match()) {
            //获得所有详情页的连接
            //page.getHtml().xpath("//a[@class=\"title\"]").links().all();
            List<String> detailURL = page.getHtml().xpath("//a[@class=\"title\"]").links().all();
            System.out.println("size:"+detailURL.size());
            for (String str:detailURL)
                //输出所有连接
                System.out.println(str);
            page.addTargetRequests(detailURL);

            //如果是详情页
        } else {
            //要按条件筛选
            String diggNum = page.getHtml().xpath("//div[@class='digg_num']/text()").regex("\\d+").toString();
            //点赞数要超过300
            if (Integer.parseInt(diggNum) > 200) {
                //得到照片url
                String photoURL = page.getHtml().xpath("//div[@class='picbox']/a").css("img", "src").toString();
                System.out.println(photoURL);
                //得到名字
                String nickname = page.getHtml().xpath("//div[@class='title']/h2/text()").toString();
                System.out.println(nickname);

                try {
                    // 根据图片URL 下载图片方法
                    /**
                     * String 图片URL地址
                     * String 图片名称
                     * String 保存路径
                     */
                    //DownloadImage.download( "http://www.521609.com"+photoURL, nickname + ".jpg", "F:\\schoolFlowerImage\\");
                    SpiderDownload.download( "http://www.521609.com"+photoURL, nickname + ".jpg", "E:\\Test\\aaa\\schoolFlowerImage\\");

                    System.out.println("第"+(INDEX_PHOTO++)+"张");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static  void main(String[]arv){
        //起始URL，开启的线程数为10个
        Spider.create(new MyImageProcess()).addUrl("http://www.521609.com/daxuexiaohua/list310.html").thread(10).run();
    }

}
