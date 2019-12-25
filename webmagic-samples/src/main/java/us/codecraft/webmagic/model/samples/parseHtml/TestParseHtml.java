package us.codecraft.webmagic.model.samples.parseHtml;

import java.io.File;

public class TestParseHtml {
    public static void main(String[] args){
        String filePath="E:\\MyTestDemo\\parseHtml\\test_3f.html";

        JsoupParse jsoupParse = new JsoupParse();
        try{
            jsoupParse.readFile2(filePath);
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }
}
