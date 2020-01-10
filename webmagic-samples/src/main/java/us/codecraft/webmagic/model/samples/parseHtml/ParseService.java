package us.codecraft.webmagic.model.samples.parseHtml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.model.samples.parseHtml.entity.ApiEntity;

public class ParseService {
    private static String url="http://10.86.16.248:8000/matchdatapoint/";

    public ApiEntity getPredictDataPoint(String content){
        ApiEntity apiEntity=null;

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("dataInfo", content);
        String data =jsonObject1.toJSONString();
        String result = HttpUtil.sendPost(url,data);
        if(StringUtils.isNotBlank(result)){
            apiEntity = JSON.parseObject(result,ApiEntity.class);
        }
        return apiEntity;
    }
}
