package us.codecraft.webmagic.model.samples.parseHtml;

import java.io.File;
import java.util.List;

public class TestParseHtml {
    public static void main(String[] args) {
        String filePath = "E:\\MyTestDemo\\parseHtml\\0001081400-18-000877.htm";
        ParseBase parse = new JsoupParse();
        try {
            parse.addIdentifyToDocumentElement(filePath);
            List<String> contentList = parse.readHtmlFile();
            parse.createHtmlFile(contentList);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("-------------end");


    }
}
