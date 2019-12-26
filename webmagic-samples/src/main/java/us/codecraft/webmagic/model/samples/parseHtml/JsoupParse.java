package us.codecraft.webmagic.model.samples.parseHtml;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;

public class JsoupParse {
    //思路： 先通过传统NLP抓取到一部分数据点或标题， 再通过机器学习预测部分内容位置，辅助抓取。 对抓取的内容可以通过bizRule和机器
    //      学习相似度一起验证

    //解析基金HTML文件过程
    //1.（是否要先给原始文档的所有节点tag添加上绝对坐标或唯一id，以方便以后追踪？？？？），在流程中切fund，即在原document中添加fund标签，并生成fundName和FundId
    //2.在准备解析HTML文件时， 首先判断该文件是否有fund标签
    //3.格式规整html文件，生成只包含 title 标题的h3标签, p 内容标签，p中可以包含keyword关键字strong标签， table表格标签， img 图片标签的html文件
    //4.读取规整后的html文件，判断节点内容（通过规则和机器学习来判断内容），并打上数据点标签， 同时生成对应的json文件
    //5.将规整后html文件的打标内容同步到原始html文档中
    /*
    示例：
        <h3 uid='abc123456' datapointname='fundName' investmentid='f000001'>MainStay Short Term Bond Fund</h3>
        <h3 uid='abc123457' datapointname='investmentObjective_title' investmentid='f000001'>Investment Objective</h3>
        <p uid='abc123458' datapointname='investmentObjective' >The Cannabis ETF (the “Fund”) seeks to provide investment results that, before fees and expenses, correspond generally to the total return performance of the Innovation Labs Cannabis Index</p>

     */

    private static String[] lineTagsArray = new String[]{"a", "abbr", "acronym", "b", "bdo", "big", "br", "cite", "code", "dfn",
            "em", "font", "i", "img", "input", "kbd", "label", "q", "s", "samp",
            "select", "small", "span", "strike", "strong", "sub", "sup", "textarea", "tt", "u"};
    private static String[] blockTagsArray = {"address", "blockquote", "center", "dir", "div", "dl", "fieldset",
            "form", "isindex", "menu", "ol", "p", "pre", "table", "ul", "h1", "h2", "h3", "h4", "h5", "h6", "hr"};

    public List<String> readHtmlFile(String filePath) throws Exception {
        File input = new File(filePath);
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Elements elements = doc.children();
        List<String> contentList = new ArrayList<String>();

        if (elements != null && elements.size() > 0) {
            for (Element el : elements) {
                getSub(el, contentList);
            }
        }
        return contentList;
    }

    //读取元素内容，进行处理，进行标签转换
    public void getSub(Element element, List<String> contentList) {
        String elementText = element.text();
        String tagName = element.tagName();
        String contentHtml = element.html();
        Elements elements = element.children();

        System.out.println("---------------------------");
        System.out.println("html is " + element.html());
        System.out.println("==============================");
        System.out.println("outerhtml is " + element.outerHtml());

        if("table".equalsIgnoreCase(tagName)){
            contentList.add(String.format("<%s>%s</%s>", tagName, contentHtml, tagName));

        }else{
            if (elements != null && elements.size() > 0) {
                Boolean isAllLineTag = true;
                for (Element item : elements) {
                    isAllLineTag = isExistTag(lineTagsArray, item.tagName());
                    if (!isAllLineTag) {
                        break;
                    }
                }

                if (!isAllLineTag) {
                    for (Element link : elements) {
                        getSub(link, contentList);
                    }
                } else {
                    elementText = element.text();
                    //System.out.println(element.outerHtml());
                    //System.out.println(element.tagName());

                    if (StringUtils.isNotBlank(elementText)) {
                        tagName = element.tagName();
                        contentList.add(String.format("<%s>%s</%s>", tagName, elementText, tagName));
                    }
                }

            } else {
                elementText = element.text();

                if (StringUtils.isNotBlank(elementText)) {
                    tagName = element.tagName();
                    Boolean isAllLineTag = isExistTag(lineTagsArray, tagName);
                    if (isAllLineTag) {
                        if(!"img".equalsIgnoreCase(tagName)){
                            tagName="h3";
                        }
                    }else {
                        if(!"table".equalsIgnoreCase(tagName)){
                            tagName="p";
                        }
                    }
                    contentList.add(String.format("<%s>%s</%s>", tagName, elementText, tagName));
                }
            }
        }
    }

    public Boolean isExistTag(String[] array, String value) {
        Boolean isExist = false;
        if (array != null && array.length > 0) {
            for (String item : array) {
                if (item.equalsIgnoreCase(value)) {
                    isExist = true;
                    break;
                }
            }
        }

        return isExist;
    }

    public void createHtmlFile(String fileFullPath, List<String> contentList) throws Exception {
        File file = new File(fileFullPath);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (contentList != null && !contentList.isEmpty()) {
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                fos = new FileOutputStream(file);
                osw = new OutputStreamWriter(fos, "utf-8");
                //create html head and body
                osw.write("<html><head>");
                osw.write("\r\n");
                osw.write("</head><body>");
                osw.write("\r\n");
                for (String item : contentList) {
                    osw.write(item);
                    osw.write("\r\n");
                }
                osw.write("</body></html>");
            }

        } catch (Exception e) {
            throw e;

        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                throw e;
            }

            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }

}
