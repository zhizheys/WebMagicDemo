package us.codecraft.webmagic.model.samples.openNLP;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class TestOpenNLPConsole {
    //open nlp 模型包下载路径：
    //http://opennlp.apache.org/models.html
    // http://opennlp.sourceforge.net/models-1.5/


    private static  String str="Let's go,This isn't the greatest example sentence in the world because I've seen better.  Neither is this one.  This one's not bad, though.";

    public static void main(String[] args){
        System.out.println("hello open nlp");
        //sentence();
        tokenize();
    }

    public static void sentence(){
        URL url = TestOpenNLPConsole.class.getClassLoader().getResource("en-sent.bin");
        String path = url.getFile();

        try{
            InputStream modelIn = new FileInputStream(path);
            SentenceModel model = new SentenceModel(modelIn);
            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
            /* sentence segmentation */
            String sentences[] = sentenceDetector.sentDetect(str);

            for(String item : sentences){
                System.out.println(item);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static  void tokenize(){
        URL url = TestOpenNLPConsole.class.getClassLoader().getResource("en-token.bin");
        String path = url.getFile();

        try{
            InputStream modelIn = new FileInputStream(path);
            TokenizerModel model = new TokenizerModel(modelIn);
            Tokenizer tokenizer = new TokenizerME(model);
            /* sentence segmentation */
            String sentences[] = tokenizer.tokenize(str);

            for(String item : sentences){
                System.out.println(item);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
