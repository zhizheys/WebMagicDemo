package us.codecraft.webmagic.model.samples.parseHtml;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class JsoupParse extends ParseBase {

    @Override
    public void addIdentifyToDocumentElement(String sourceFilePath) {
        File input = new File(sourceFilePath);

        try {
            Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
            Elements elements = doc.children();

            if (elements != null && elements.size() > 0) {
                for (Element el : elements) {
                    addIdentifySubElement(el);
                }
            }

            String identityFileContent=doc.html();
            FileUtil.createFile(super.identifyFilePath,identityFileContent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<String> readHtmlFile() throws Exception {
        String filePath = this.identifyFilePath;
        File input = new File(filePath);
        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
        Elements elements = doc.children();
        List<String> contentList = new ArrayList<String>();

        if (elements != null && elements.size() > 0) {
            for (Element el : elements) {
                getSub(el, contentList);
            }
        }
        return contentList;
    }

    @Override
    public void createHtmlFile(List<String> contentList) throws Exception {
        String fileFullPath = this.createFilePath;
        File file = new File(fileFullPath);
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;

        try {
            if (contentList != null && !contentList.isEmpty()) {
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                fos = new FileOutputStream(file);
                osw = new OutputStreamWriter(fos, "utf-8");
                //create html head and body
                osw.write("<html><head>");
                osw.write("\r\n");
                osw.write("</head><body>");
                osw.write("\r\n");
                for (String item : contentList) {
                    osw.write(item);
                    osw.write("\r\n");
                }
                osw.write("</body></html>");
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

    @Override
    public void addElementAnnotation() {

    }

    @Override
    public String getElementData(String annotationFilePath) {
        return "jsonpath";
    }

    //读取元素内容，添加id
    public void addIdentifySubElement(Element element) {
        if(element !=null){
            element.attr("nodeId", UUID.randomUUID().toString().replaceAll("-",""));
            Elements elements = element.children();
            Boolean isTitle = false;

            if (elements != null && elements.size() > 0) {
                for (Element item : elements) {
                    addIdentifySubElement(item);
                }
            }
        }

    }

    //读取元素内容，进行处理，进行标签转换
    public void getSub(Element element, List<String> contentList) {
        String elementText = element.text();
        String tagName = element.tagName();
        String contentHtml = element.html();
        String contentOutHtml = null;
        Elements elements = element.children();
        Boolean isTitle = false;

        //get nodeid
        String nodeId = element.attr("nodeid");
        String nodeItAttr = String.format("nodeid=%s",nodeId);

        if ("table".equalsIgnoreCase(tagName)) {
            contentList.add(String.format("<%s %s>%s</%s>", tagName,nodeItAttr,contentHtml,tagName));

        } else {
            if (elements != null && elements.size() > 0) {
                Boolean isAllLineTag = true;
                for (Element item : elements) {
                    isAllLineTag = isExistTag(lineTagsArray, item.tagName());
                    if (!isAllLineTag) {
                        break;
                    }
                }

                if (!isAllLineTag) {
                    for (Element link : elements) {
                        getSub(link, contentList);
                    }
                } else {
                    elementText = element.text();
                    elementText = elementText.replace("\n", "");
                    elementText = elementText.replace("\r", "");

                    if (StringUtils.isNotBlank(elementText)) {
                        tagName = element.tagName();
                        isAllLineTag = isExistTag(lineTagsArray, tagName);
                        if (isAllLineTag) {
                            if (!"img".equalsIgnoreCase(tagName)) {
                                tagName = "h3";
                            }
                        } else {
                            if (!"table".equalsIgnoreCase(tagName)) {
                                contentOutHtml = element.outerHtml().replace("\n", "");
                                contentOutHtml = contentOutHtml.replace("\r", "");
                                isTitle = isTitle(elementText, contentOutHtml);

                                if (isTitle) {
                                    tagName = "h3";
                                } else {
                                    tagName = "p";
                                }
                            }
                        }

                         nodeId = element.attr("nodeid");
                         nodeItAttr = String.format("nodeid=%s",nodeId);
                        contentList.add(String.format("<%s %s>%s</%s>", tagName,nodeItAttr,contentHtml,tagName));

                    }
                }

            } else {
                elementText = element.text();
                elementText = elementText.replace("\n", "");
                elementText = elementText.replace("\r", "");

                if (StringUtils.isNotBlank(elementText)) {
                    tagName = element.tagName();
                    if (!"table".equalsIgnoreCase(tagName) || !"img".equalsIgnoreCase(tagName)) {
                        contentOutHtml = element.outerHtml().replace("\n", "");
                        contentOutHtml = contentOutHtml.replace("\r", "");
                        isTitle = isTitle(elementText, contentOutHtml);

                        if (isTitle) {
                            tagName = "h3";
                        } else {
                            tagName = "p";
                        }
                    }

                    nodeId = element.attr("nodeid");
                    nodeItAttr = String.format("nodeid=%s",nodeId);
                    contentList.add(String.format("<%s %s>%s</%s>", tagName,nodeItAttr,contentHtml,tagName));
                }
            }
        }
    }

    public Boolean isExistTag(String[] array, String value) {
        Boolean isExist = false;
        if (array != null && array.length > 0) {
            for (String item : array) {
                if (item.equalsIgnoreCase(value)) {
                    isExist = true;
                    break;
                }
            }
        }

        return isExist;
    }

    //判断是否是title, 根据内容长度，内容结尾或整句不能有句号等标点符号，title字体一般是加粗等
    public Boolean isTitle(String content, String contentOutHtml) {
        Boolean isTitle = false;
        Integer maxTitleLength = 160;
        String pattern = ".*[/.,;].*";
        String pattern_fontWeight = ".*(bold|bolder).*";

        if (content.trim().equalsIgnoreCase("Investment Objective") || content.trim().equalsIgnoreCase("Fees and Expenses of the Fund")) {
            String a = "aaaa";
        }

        if (StringUtils.isNotBlank(content)) {
            //超长
            if (content.length() > maxTitleLength) {
                return isTitle;
            }

            //是否有句号等符号
            Boolean hasFullStop = Pattern.matches(pattern, content);
            if (hasFullStop) {
                return isTitle;
            }

            //是否加粗
            Boolean hasBoldWeight = Pattern.matches(pattern, contentOutHtml);
            if (!hasBoldWeight) {
                return isTitle;
            }

            isTitle = true;
        }
        return isTitle;
    }

    //判断段落内容中是否有关键字
    public void dealParagraphKeyWord() {

    }

}
