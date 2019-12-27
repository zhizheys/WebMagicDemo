package us.codecraft.webmagic.model.samples;

import com.itextpdf.text.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.*;
import java.util.Date;
import java.util.regex.Pattern;

public class TestConsole {
    public static  void main(String[] args){

        String content="<div style=\"FONT-SIZE: 10pt; FONT-FAMILY: Sans-Serif; FONT-WEIGHT: bold; FONT-STYLE: normal; TEXT-ALIGN: left; TEXT-INDENT: 2.9pt\"> Investment Objective</div>";
        String pattern_fontWeight=".*(bold|bolder).*";

        //是否有句号等符号
        Boolean hasFullStop= Pattern.matches(pattern_fontWeight,content);
        System.out.println(hasFullStop);

        System.out.println("--------------end");


    }

    public static void dealArray(Integer ...intArray){
        for(Integer j : intArray){
            System.out.println(j);
        }
    }

    public static  String getTextFromPDF(String pdfFilePath){
        //System.out.println("begin parse ===========" + new Date());
        String result=null;
        PDDocument document = null;
        File file = new File(pdfFilePath);

        try{
            PDFParser parser = new PDFParser(new RandomAccessFile(file,"rw"));
            parser.parse();
            document = parser.getPDDocument();
            PDFTextStripper stripper = new PDFTextStripper();
            result = stripper.getText(document);

        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            try{
                if(document !=null){
                    document.close();
                }
            }catch (IOException  ex){
                ex.printStackTrace();
            }
        }

        System.out.println("end parse ===============" + new Date());
        return  result;
    }


}
