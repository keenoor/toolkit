package com.keenoor.toolkit.utils.httpclient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Author: keenor
 * Date: 2018/5/31
 * Description: HttpClient 工具类
 */

public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static final int CONNECT_TIMEOUT = 5_000;
    private static final int REQUEST_TIMEOUT = 5_000;
    private static final int SOCKET_TIMEOUT = 20_000;

    private volatile static HttpClientBuilder builder;
    private volatile static RequestConfig requestConfig;

    public static void setConfig(RequestConfig config) {
        HttpClientUtil.requestConfig = config;
    }

    private HttpClientUtil() {
    }

    private static HttpClientBuilder getClientBuilder() {
        if (builder == null) {
            synchronized (HttpClientUtil.class) {
                if (builder == null) {
                    if (requestConfig == null) {
                        requestConfig = RequestConfig.custom()
                                .setConnectTimeout(CONNECT_TIMEOUT)
                                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
                                .setSocketTimeout(SOCKET_TIMEOUT)
                                .build();
                    }
                    builder = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig);
                }
            }
        }
        return builder;
    }

    public static String get(String url) throws HttpRequestException {
        return get(url, null);
    }

    public static String get(String url, Map<String, Object> params) throws HttpRequestException {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = getClientBuilder().build();
        CloseableHttpResponse response = null;

        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    builder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
            URI uri = builder.build();
            logger.info("HTTP-GET-URI: {}", uri.toString());

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            return handleResponse(response);
        } catch (URISyntaxException | IOException e) {
            logger.error("", e);
            throw new HttpRequestException(e);
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
    }

    public static String post(String url) throws HttpRequestException {
        return post(url, null);
    }

    public static String post(String url, Map<String, Object> params) throws HttpRequestException {

        logger.info("HTTP-POST-URL: {}", url);
        if (params != null) {
            logger.info("HTTP-POST-PARAMS: {}", params);
        }

        // 创建Httpclient对象
        CloseableHttpClient httpClient = getClientBuilder().build();
        CloseableHttpResponse response = null;

        // 创建Http Post请求
        HttpPost httpPost = new HttpPost(url);
        // 创建参数列表
        if (params != null) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                paramList.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            // 模拟表单
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, StandardCharsets.UTF_8);
            httpPost.setEntity(entity);
        }

        try {
            // 执行 http 请求
            response = httpClient.execute(httpPost);
            return handleResponse(response);
        } catch (IOException e) {
            logger.error("", e);
            throw new HttpRequestException(e);
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
    }

    public static String postJson(String url, String json) throws HttpRequestException {

        logger.info("HTTP-POST-URL: {}", url);
        if (StringUtils.isEmpty(json)) {
            throw new IllegalArgumentException("the param [String json] is empty");
        }
        logger.info("HTTP-POST-PARAMS: {}", json);

        // 创建Httpclient对象
        CloseableHttpClient httpClient = getClientBuilder().build();
        CloseableHttpResponse response = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            return handleResponse(response);
        } catch (IOException e) {
            logger.error("", e);
            throw new HttpRequestException(e);
        } finally {
            HttpClientUtils.closeQuietly(response);
        }
    }

    private static String handleResponse(HttpResponse response) throws HttpRequestException {
        // 判断返回状态是否为200
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result;
            try {
                result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                logger.error("", e);
                throw new HttpRequestException(e);
            }
            logger.info("HTTP-RESPONSE: {}", result);
            return result;
        } else {
            throw new HttpRequestException(response.getStatusLine());
        }
    }

}