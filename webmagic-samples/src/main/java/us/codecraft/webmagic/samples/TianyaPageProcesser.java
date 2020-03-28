package us.codecraft.webmagic.samples;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 */
public class TianyaPageProcesser {

    public TianyaPageProcesser(){
        System.out.println("father innitial");
    }

    public TianyaPageProcesser(String a){
        System.out.println("father innitial args " + a);
    }

    protected void doSomething(){
        System.out.println("father dosomething");
    }

    protected TianyaPageProcesser foo(){
        return  new TianyaPageProcesser();
    }


}
