package com.keenoor.toolkit.utils.httpclient;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-01-03 14:07
 * Revision history:
 * Date         Description
 * --------------------------------------------------
 */

public class PostRequest<T> extends BaseRequest<T> {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private String json;

    PostRequest(String url) {
        super(url);
    }

    public PostRequest<T> json(String json) {
        this.json = json;
        return this;
    }

    @Override
    public T execute() throws HttpCodeException {

        logger.info("HTTP-POST-URL: {}", url);
        if (params != null) {
            logger.info("HTTP-POST-PARAMS: {}", params);
        }else if (json !=null){
            logger.info("HTTP-POST-PARAMS: {}", json);
        }

        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClientUtil.getClientBuilder().build();
        CloseableHttpResponse response = null;

        // 创建Http Post请求
        HttpPost httpPost = new HttpPost(url);
        // 创建参数列表
        StringEntity entity;
        if (params != null) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                paramList.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            // 模拟表单
            entity = new UrlEncodedFormEntity(paramList, StandardCharsets.UTF_8);
        } else if (json !=null){
            entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        } else {
            throw new HttpCodeException("params and json cannot be null together");
        }
        httpPost.setEntity(entity);

        try {
            // 执行 http 请求
            response = httpClient.execute(httpPost);
            return convertRsp(response);
        } catch (IOException e) {
            logger.error("", e);
            throw new HttpCodeException(e);
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
    }


}
