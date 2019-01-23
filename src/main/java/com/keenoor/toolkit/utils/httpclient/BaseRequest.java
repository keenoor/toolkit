package com.keenoor.toolkit.utils.httpclient;

import com.google.gson.reflect.TypeToken;
import com.keenoor.toolkit.utils.GsonUtil;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.BufferedHttpEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Description:
 * -----------------------------------------------
 * Author:      chenliuchun
 * Date:        2019-01-03 16:03
 * Revision history:
 * Date         Description
 * --------------------------------------------------
 */

public abstract class BaseRequest<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseRequest.class);

    String url;
    Map<String, Object> params;
    private Class<T> clazz;
    private TypeToken<T> type;

    BaseRequest(String url) {
        this.url = url;
    }

    public BaseRequest<T> params(Map<String, Object> val) {
        params = val;
        return this;
    }

    public BaseRequest<T> clazz(Class<T> val) {
        clazz = val;
        return this;
    }

    public BaseRequest<T> type(TypeToken<T> val) {
        type = val;
        return this;
    }

    public abstract T execute() throws HttpCodeException;

    T convertRsp(CloseableHttpResponse response) throws HttpCodeException {
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            try {
                BufferedHttpEntity entity = new BufferedHttpEntity(response.getEntity());
                InputStreamReader reader = new InputStreamReader(entity.getContent(), StandardCharsets.UTF_8);
                logger.info("HTTP-RESPONSE: {}", IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8));
                if (clazz != null) {
                    return GsonUtil.parseObj(reader, clazz);
                } else if (type != null) {
                    return GsonUtil.parseType(reader, type);
                } else {
                    throw new NullPointerException("clazz and type cannot be null together");
                }
            } catch (IOException e) {
                logger.error("", e);
                throw new HttpProcessException(e);
            }
        } else {
            throw new HttpCodeException(response.getStatusLine());
        }
    }

}
