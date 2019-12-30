package us.codecraft.webmagic.model.samples.parseHtml;

import java.util.List;

public abstract class ParseBase {
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

    protected static String[] lineTagsArray = new String[]{"a", "abbr", "acronym", "b", "bdo", "big", "br", "cite", "code", "dfn",
            "em", "font", "i", "img", "input", "kbd", "label", "q", "s", "samp",
            "select", "small", "span", "strike", "strong", "sub", "sup", "textarea", "tt", "u"};
    protected static String[] blockTagsArray = {"address", "blockquote", "center", "dir", "div", "dl", "fieldset",
            "form", "isindex", "menu", "ol", "p", "pre", "table", "ul", "h1", "h2", "h3", "h4", "h5", "h6", "hr"};

    protected static String identifyFilePath="E:\\MyTestDemo\\parseHtml\\test_3f_identity.html";;
    protected static String createFilePath="E:\\MyTestDemo\\parseHtml\\test_3f_create.html";;

    public abstract void addIdentifyToDocumentElement(String filePath);
    public abstract List<String> readHtmlFile() throws Exception;
    public abstract void createHtmlFile(List<String> contentList) throws Exception;
    public abstract void addElementAnnotation();
    public abstract String getElementData(String annotationFilePath);
}
