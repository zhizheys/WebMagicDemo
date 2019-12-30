package us.codecraft.webmagic.model.samples.parseHtml;

import java.io.*;

public class FileUtil {
    public static void copyFile(String sourceFile, String destinationFile) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(new File(sourceFile));
            out = new FileOutputStream(new File(destinationFile));
            byte[] buff = new byte[10240];
            int n = 0;
            while ((n = in.read(buff)) != -1) {
                out.write(buff, 0, n);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                out.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                in.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                out.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void createFile(String filePath,String fileContent) throws Exception{
        File file = new File(filePath);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (fileContent != null && !fileContent.isEmpty()) {
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                fos = new FileOutputStream(file);
                osw = new OutputStreamWriter(fos, "utf-8");
                osw.write(fileContent);
            }

        } catch (Exception e) {
            throw e;

        } finally {
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                throw e;
            }

            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                throw e;
            }
        }
    }

}
