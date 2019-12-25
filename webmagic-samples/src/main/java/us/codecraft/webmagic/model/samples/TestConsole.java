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

        String fileName = "E:\\Test\\pdf\\test11.pdf";  //这里先手动把绝对路径的文件夹给补上。
        PDFUtil pdfUtil = new PDFUtil();

        Font chapterFont = PDFUtil.createCHineseFont(20, Font.BOLD, new BaseColor(0, 0, 255));//文章标题字体
        Font sectionFont = PDFUtil.createCHineseFont(16, Font.BOLD, new BaseColor(0, 0, 255));//文章小节字体
        Font textFont = PDFUtil.createCHineseFont(10, Font.NORMAL, new BaseColor(0, 0, 0));//小节内容字体

        pdfUtil.createDocument(fileName);
        Chapter chapter = PDFUtil.createChapter("abc糖尿病病例1", 1, 1, 0, chapterFont);
        Section section1 = PDFUtil.createSection(chapter, "def病例联系人信息", sectionFont, 0);
        Phrase text1 = PDFUtil.createPhrase("ggg如您手中有同类现成病例，在填写完以上基础信息后，传病例附件", textFont);
        section1.add(text1);

        Section section2 = PDFUtil.createSection(chapter, "kk病例个人体会", sectionFont, 0);
        Phrase text2 = PDFUtil.createPhrase("ttt1.下载病例生成PDF文档", textFont);
//      text2.setFirstLineIndent(20);  //第一行空格距离
        section2.add(text1);
        section2.add(text2);

        List list = PDFUtil.createList(true, false, 20);
        String tmp = "ff还有什么能够文档。文档是 PDF 文档的所有元素的容器。 ";
        ListItem listItem1 = PDFUtil.createListItem(tmp, textFont);
        ListItem listItem2 = PDFUtil.createListItem("ee列表2", textFont);
        list.add(listItem1);
        list.add(listItem2);
        section2.add(list);

        pdfUtil.writeChapterToDoc(chapter);
        pdfUtil.closeDocument();

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
