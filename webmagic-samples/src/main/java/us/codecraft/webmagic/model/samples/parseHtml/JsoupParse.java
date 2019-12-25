package us.codecraft.webmagic.model.samples.parseHtml;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;

public class JsoupParse {

    //解析基金HTML文件过程
    //1.（是否要先给原始文档的所有节点tag添加上绝对坐标或唯一id，以方便以后追踪？？？？），在流程中切fund，即在原document中添加fund标签，并生成fundName和FundId
    //2.在准备解析HTML文件时， 首先判断该文件是否有fund标签
    //3.格式规整html文件，生成只包含 title 标题的h3标签, p 内容标签，p中可以包含keyword关键字strong标签， table表格标签， img 图片标签的html文件
    //4.读取规整后的html文件，判断节点内容（通过规则和机器学习来判断内容），并打上数据点标签， 同时生成对应的json文件
    //5.将规整后html文件的打标内容同步到原始html文档中
    /*
    示例：
        <h3 uid='abc123456' datapointname='fundName' investmentid='f000001'>MainStay Short Term Bond Fund</h3>
        <h3 uid='abc123457' datapointname='investmentObjective' investmentid='f000001'>Investment Objective</h3>
        <p uid='abc123458' datapointname='investmentObjective' >The Cannabis ETF (the “Fund”) seeks to provide investment results that, before fees and expenses, correspond generally to the total return performance of the Innovation Labs Cannabis Index</p>

     */

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
