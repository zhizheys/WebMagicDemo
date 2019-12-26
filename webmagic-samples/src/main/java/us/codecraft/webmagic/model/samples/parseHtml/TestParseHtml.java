package us.codecraft.webmagic.model.samples.parseHtml;

import java.io.File;
import java.util.List;

public class TestParseHtml {
    public static void main(String[] args){
        String filePath="E:\\MyTestDemo\\parseHtml\\test_3f.html";
        String createPath="E:\\MyTestDemo\\parseHtml\\test_3f_create.html";

        JsoupParse jsoupParse = new JsoupParse();
        try{
          List<String> contentList =  jsoupParse.readHtmlFile(filePath);
          jsoupParse.createHtmlFile(createPath,contentList);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        System.out.println("-------------end");


    }
}
