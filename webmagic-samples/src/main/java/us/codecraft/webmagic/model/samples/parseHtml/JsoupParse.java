package us.codecraft.webmagic.model.samples.parseHtml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

public class JsoupParse {
    private static String[] lineTagsArray = new String[]{"a","abbr","acronym","b","bdo","big","br","cite","code","dfn",
                               "em","font","i","img","input","kbd","label","q","s","samp",
                               "select","small","span","strike","strong","sub","sup","textarea","tt","u"};
    private static String[] blockTagsArray = {"address","blockquote","center","dir","div","dl","fieldset",
            "form","isindex","menu","ol","p","pre","table","ul","h1","h2","h3","h4","h5","h6","hr"};

    public void readFile(String filePath) throws Exception{
        File input = new File(filePath);
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

        Element content = doc.getElementsByTag("body").first();
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
            System.out.println(linkText);
        }
    }

    public void readFile2(String filePath) throws Exception{

        File input = new File(filePath);
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Elements elements = doc.children();

        if(elements !=null && elements.size() >0){
            for (Element link : elements) {
                getSub(link);
            }
        }
    }

    public void getSub(Element element){
        Elements elements = element.children();

        if(elements !=null && elements.size() >0){
            Boolean isAllLineTag = true;
            for (Element link : elements) {
                isAllLineTag=isExist(lineTagsArray,link.tagName());
                if(!isAllLineTag){
                    break;
                }
            }

            if(!isAllLineTag){
                for (Element link : elements) {
                    getSub(link);
                }
            }else {
                String linkText = element.text();
                System.out.println("------------------");
                System.out.println(linkText);
                //System.out.println(element.outerHtml());
                //System.out.println(element.tagName());
            }

        }else{
            String linkText = element.text();
            System.out.println("------------------");
            System.out.println(linkText);
            //System.out.println(element.outerHtml());
            //System.out.println(element.tagName());
        }
    }

    public static Boolean isExist(String[] array,String value){
        Boolean isExist=false;
        if(array !=null && array.length >0){
            for(String item : array){
                if(item.equalsIgnoreCase(value)){
                    isExist=true;
                    break;
                }
            }
        }

        return isExist;
    }
}
