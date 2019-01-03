package com.keenoor.toolkit.utils.httpclient;

import com.google.gson.Gson;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Gson gson = new Gson();
    
    static {
        requestConfig = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();
    }

    public static void setConfig(RequestConfig config) {
        HttpClientUtil.requestConfig = config;
    }

    private HttpClientUtil() {
    }

    static HttpClientBuilder getClientBuilder() {
        if (builder == null) {
            synchronized (HttpClientUtil.class) {
                if (builder == null) {
                    builder = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig);
                }
            }
        }
        return builder;
    }

    public static <T> GetRequest<T> get(String url) {
        return new GetRequest<>(url);
    }

    public static <T> PostRequest<T> post(String url) {
        return new PostRequest<>(url);
    }

}