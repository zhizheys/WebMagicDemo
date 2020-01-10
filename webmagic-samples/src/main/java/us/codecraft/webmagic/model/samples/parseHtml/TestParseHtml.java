package us.codecraft.webmagic.model.samples.parseHtml;

import us.codecraft.webmagic.model.samples.parseHtml.entity.ApiEntity;

import java.io.File;
import java.util.List;

public class TestParseHtml {
    public static void main(String[] args) {

        ParseService parseService = new ParseService();
        ApiEntity apiEntity= parseService.getPredictDataPoint("The iShares BB Rated Corporate Bond ETF (the “Fund”) seeks to track the investment results of an index composed of BB, or equivalently rated, fixed rate U.S. dollar-denominated bonds issued by U.S. and non-U.S. corporations.");

        if(apiEntity !=null && apiEntity.getApiCode()==1){
            System.out.println("sucess");
            System.out.println(apiEntity.getDataPointName());
        }

        System.out.println("-------------end");


    }
}
