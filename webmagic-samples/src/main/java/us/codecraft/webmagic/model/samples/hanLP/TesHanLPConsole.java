package us.codecraft.webmagic.model.samples.hanLP;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.List;

public class TesHanLPConsole {
    public static void main(String[] args){
        System.out.println("hello hanLP");

        System.out.println(HanLP.segment("工信处女干事每月经过下属科室都要亲口交代24口交换机等技术性器件的安装工作"));
        List<Term> termList = StandardTokenizer.segment("商品和服务");
        System.out.println(termList);

        List<Term> termList2 = StandardTokenizer.segment("let's go and plan game, it's not good");
        System.out.println(termList2);
    }
}
