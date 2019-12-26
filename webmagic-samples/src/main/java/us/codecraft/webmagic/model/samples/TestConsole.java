package us.codecraft.webmagic.model.samples;

import com.itextpdf.text.*;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.*;
import java.util.Date;

public class TestConsole {
    public static  void main(String[] args){

       System.out.println(String.format("Processing trade with id:%s and symbol : %s ", "aaa", "bbb"));
       // readPDF("F:/test11.pdf");
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
