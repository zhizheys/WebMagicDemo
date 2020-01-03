package us.codecraft.webmagic.model.samples;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class TestConsole {
    public static  void main(String[] args){

        String html = "<html><head><title>First parse</title></head>"
                + "<body> <p class='aclass' style='font-weight:60'>ccc1111<b class='blcass'>333</b></p>    <p class='aclass' style='font-weight:60'>ccc2222<b class='blcass'>Parsed HTML into a doc</b></p><p class='eeee'><b>These tables are intended to help you understand the various costs and expenses you will pay if you buy and hold shares of the Fund. You may qualify for sales charge discounts if you and your family invest, or agree to invest in the future, at l</b></p></body></html>";


        try{
            Document doc = Jsoup.parse(html);
            Elements elements = doc.children();

            for(Element item : elements){
                showElement(item);
            }


        }catch (Exception ex){
            ex.printStackTrace();
        }

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
