package us.codecraft.webmagic.model.samples.parseHtml;

import us.codecraft.webmagic.model.samples.parseHtml.entity.ApiEntity;

import java.io.File;
import java.util.List;

public class TestParseHtml {
    public static void main(String[] args) {

        ParseService parseService = new ParseService();
        ApiEntity apiEntity= parseService.getPredictDataPoint("investment objective");

        if(apiEntity !=null && apiEntity.getApiCode()==1){
            System.out.println("sucess");
        }

        System.out.println("-------------end");


    }
}
