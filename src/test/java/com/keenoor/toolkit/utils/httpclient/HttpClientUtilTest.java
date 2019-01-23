package com.keenoor.toolkit.utils.httpclient;

import com.google.common.collect.Maps;
import com.google.gson.reflect.TypeToken;
import com.keenoor.toolkit.utils.GsonUtil;
import com.keenoor.toolkit.utils.model.MwResult;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author: chenliuchun
 * Date:        2018/8/7
 * Description:
 * Modification History:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 */
public class HttpClientUtilTest {

    String url = "http://192.168.2.38:9999";

    @Test
    public void get() {
        String url = "http://192.168.2.38:9999/common/blockNumber.json";
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("aa", 2);
        map.put("bb", "fsfdsfsdf");
        MwResult<Long> result = null;
        try {
            result = HttpClientUtil.<MwResult<Long>>get(url).params(map).type(new TypeToken<MwResult<Long>>() {
            }).execute();
        } catch (HttpCodeException e) {
            e.printStackTrace();
            System.out.println("hshs: " + e.getStatusLine().toString());
        }

        System.out.println("result: " + result.getDetail());
    }

    @Test
    public void postJson() {
        String url = "http://192.168.2.23:9900/admin/login.json";
        HashMap<String, Object> map = Maps.newLinkedHashMap();
        map.put("user", "admin");
        map.put("password", "admin123456");
        MwResult<Long> result;
        try {
            result = HttpClientUtil.<MwResult<Long>>post(url).json(GsonUtil.toJson(map)).type(new TypeToken<MwResult<Long>>() {
            }).execute();
        } catch (HttpCodeException e) {
            throw new RuntimeException(e);
        }

        System.out.println("result: " + result);
    }

    @Test
    public void post() {
        String url = "http://localhost:8050/test/post";
        HashMap<String, Object> map = Maps.newLinkedHashMap();
        map.put("user", "admin");
        map.put("password", "admin123456");
        MwResult<Void> result;
        try {
            result = HttpClientUtil.<MwResult<Void>>post(url).json(GsonUtil.toJson(map)).type(new TypeToken<MwResult<Void>>() {
            }).execute();
        } catch (HttpCodeException e) {
            throw new RuntimeException(e);
        }

        System.out.println("result: " + result);
    }

}