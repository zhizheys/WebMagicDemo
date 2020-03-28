package us.codecraft.webmagic.model.samples;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.model.samples.parseHtml.HttpUtil;
import us.codecraft.webmagic.samples.TianyaPageProcesser;
import us.codecraft.webmagic.samples.ZhihuPageProcessor;

import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class TestConsole {


    public static  void main(String[] args){


        Integer a=0;
        Integer b=1;

        try{
            int c= b/a;
        }catch (Exception ex){
           System.out.println(ex);
        }


        System.out.println("--------------------");

        try{
            new TestConsole().test();
        }catch (Exception e){
            e.printStackTrace();
        }




    }

    public void show(){
        System.out.println("show");
    }


    public void  test() throws  Exception{
        Integer a=0;
        Integer b=1;

        try{
            int c= b/a;
        }catch (Exception ex){
            throw new Exception(ex.toString());
        }
    }



}
