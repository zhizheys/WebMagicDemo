package us.codecraft.webmagic.model.samples.parsePdf;

import com.itextpdf.text.pdf.PdfDocument;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import javax.imageio.ImageIO;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TestParsePdf {

    public static void main(String[] args) {
        System.out.println("hello pdf");
        String path = TestParsePdf.class.getClassLoader().getResource("testPdf/isee.pdf").getPath();
        File file = new File(path);
        InputStream is = null;
        PDDocument document = null;

        try {
            if (path.endsWith("pdf")) {
                document = PDDocument.load(file);
                int pageSize = document.getNumberOfPages();

                for (int i = 0; i < pageSize; i++) {
                    PDFTextStripper stripper = new PDFTextStripper();
                    //根据位置读取
                    //stripper.setSortByPosition(true);
                    //根据块读取, 能读取多列内容
                    stripper.setSortByPosition(false);
                    stripper.setStartPage(i + 1);
                    stripper.setEndPage(i + 1);
                    String text = stripper.getText(document);
                    System.out.println(text.trim());
                    System.out.println("=================");

                    //get image
                    PDPage page = document.getPage(i);
                    PDResources resources = page.getResources();
                    Iterable<COSName> cosNames = resources.getXObjectNames();

                    if (cosNames != null) {
                        Iterator<COSName> cosNamesIter = cosNames.iterator();
                        while (cosNamesIter.hasNext()) {
                            COSName cosName = cosNamesIter.next();
                            if (resources.isImageXObject(cosName)) {
                                PDImageXObject pdImage = (PDImageXObject) resources.getXObject(cosName);
                                BufferedImage bImage = pdImage.getImage();
                                FileOutputStream out = new FileOutputStream("G:\\Test\\parsePdf\\" + UUID.randomUUID().toString() + ".png");

                                try {
                                    ImageIO.write(bImage, "png", out);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                } finally {
                                    try {
                                        out.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        }
                    }


                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // ---------------------


        //----------------------


        //----------------------


    }
}
