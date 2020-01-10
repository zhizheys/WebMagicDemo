package us.codecraft.webmagic.model.samples;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.model.samples.parseHtml.HttpUtil;

import java.util.HashMap;
import java.util.Map;


public class TestConsole {
    public static  void main(String[] args){

        String url="http://10.86.16.248:8000/matchdatapoint/";
        String data ="{\"dataInfo\":\"investment objective\"}";
        String result = HttpUtil.sendPost(url,data);
        System.out.println(result);
    }

    public static void showElement(Element element){

        String text = element.text();
        String html = element.html();
        String outerHtml = element.outerHtml();
        String tag = element.tagName();

        //String classInfo=element.attr("class");
       // String styleInfo = element.attr("style");


        System.out.println("----------------------");
        System.out.println("current text: " + text);
        System.out.println("current html: " + html);
        System.out.println("current outer html: " + outerHtml);

        element.attr("nodeid","1234567a");
        element.tagName("strong");
        System.out.println("current outer html: " + element.outerHtml());
        System.out.println("=============================");


        if("head".equalsIgnoreCase(tag)){
            return;
        }

        Elements  sub = element.children();

        if(sub.size() >0){
            System.out.println("size is: " + sub.size());
            for(Element item : sub){
                showElement(item);
            }
        }else{

            System.out.println("no child ++++++++++" + sub.size());
        }


    }


}
