package pecker.utils;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * <b>HTTP POST工具</b> 支持 http https(url以https://开头自动加上SSL加密)
 *
 * @author
 */
@Slf4j
public class HttpPoster {

    /**
     * post请求
     *
     * @param url
     * @param jsonObject
     * @return
     */
    public static JSONObject doPost(String url, JSONObject jsonObject) throws Exception{
        log.info("url: {}", url);
        log.info("body: {}", jsonObject);
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        CloseableHttpResponse res;
        List<NameValuePair> params = new ArrayList<>();
        Set<String> set = jsonObject.keySet();
        for (String key : set) {
            String value = jsonObject.getString(key);
            params.add(new BasicNameValuePair(key, value));
        }
        try {
            post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8.name()));
            res = client.execute(post);
            HttpEntity entity = res.getEntity();
            String result = EntityUtils.toString(entity, Consts.UTF_8.name());// 返回json格式：
            log.info("result: {}", result);
            response = JSONObject.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return response;
    }
}