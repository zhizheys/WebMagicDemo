package us.codecraft.webmagic.model.samples;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.model.samples.parseHtml.HttpUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class TestConsole {

    static   String a;

    public static  void main(String[] args){

        for(int j=0;j<100;j++){
            Random random = new Random();
            Integer a =random.nextInt(3);
            System.out.println(a);
        }

        System.out.println("end");
    }

    public static void showElement(Element element){




    }


}
