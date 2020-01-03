package us.codecraft.webmagic.model.samples.parseHtml;

import java.io.File;
import java.util.List;

public class TestParseHtml {
    public static void main(String[] args) {
        String filePath = "E:\\DeveloperContent\\prospetusParse\\1_f_1.html";
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
