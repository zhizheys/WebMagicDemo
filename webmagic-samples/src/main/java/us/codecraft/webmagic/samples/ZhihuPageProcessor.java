package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * @author 410775541@qq.com <br>
 * @since 0.5.1
 */
public class ZhihuPageProcessor extends TianyaPageProcesser {
    public ZhihuPageProcessor(){
        super("abc");
        System.out.println("son innitial");
    }

    public  void doSomethingNew(){

    }

    public void doSomething(){
        System.out.println("son dosomethig");
    }


    @Override
    public    ZhihuPageProcessor foo(){
        return  new ZhihuPageProcessor();
    }

    public  int add(Integer a){
        return a;
    }

    public  int add(Integer a,Integer b){
        return a + b;
    }



}
