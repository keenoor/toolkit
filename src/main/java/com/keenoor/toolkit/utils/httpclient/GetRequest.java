package com.keenoor.toolkit.utils.httpclient;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

public class GetRequest<T> extends BaseRequest<T> {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private Header[] headers;

    GetRequest(String url) {
        super(url);
    }

    public GetRequest<T> setHeaders(List<Header> headerList) {
        this.headers = headerList.toArray(new Header[]{});
        return this;
    }

    @Override
    public T execute() throws HttpCodeException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClientUtil.getClientBuilder().build();
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

            if (headers != null) {
                httpGet.setHeaders(headers);
            }

            // 执行请求
            response = httpclient.execute(httpGet);
            return convertRsp(response);
        } catch (URISyntaxException | IOException e) {
            logger.error("", e);
            throw new HttpConnException(e);
        } finally {
            HttpClientUtils.closeQuietly(response);
        }

    }

}
