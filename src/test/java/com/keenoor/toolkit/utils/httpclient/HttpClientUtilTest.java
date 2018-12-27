package com.keenoor.toolkit.utils.httpclient;

import com.google.common.collect.Maps;
import com.google.gson.reflect.TypeToken;
import com.keenoor.toolkit.utils.GsonUtil;
import com.keenoor.toolkit.utils.model.AccountAddress;
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
        String result;
        try {
            result = HttpClientUtil.get(url, map);
        } catch (HttpRequestException e) {
            throw new RuntimeException(e);
        }

        System.out.println(GsonUtil.parseType(result, new TypeToken<MwResult<Long>>() {
        }).getDetail());
    }

    @Test
    public void post() {
        String url = "http://192.168.2.23:9900/admin/login.json";
        HashMap<String, Object> map = Maps.newLinkedHashMap();
        map.put("user", "admin");
        map.put("password", "admin123456");
        String result;
        try {
            result = HttpClientUtil.postJson(url, GsonUtil.toJson(map));
        } catch (HttpRequestException e) {
            throw new RuntimeException(e);
        }

        System.out.println("result: "+result);
    }

    @Test
    public void postJson() {
        String path = "/common/contract.json";
        String params = "{\n" +
                "  \"contractAddress\": \"30df390d913e070af7c95df597420d8bb3da9d48\",\n" +
                "  \"functionName\": \"getAccountAddressByAccountId\",\n" +
                "  \"params\": [100000128]\n" +
                "}\n";
        String result;
        try {
            result = HttpClientUtil.postJson(url + path, params);
        } catch (HttpRequestException e) {
            throw new RuntimeException(e.getStatusLine().toString(), e);
        }

        MwResult<AccountAddress> mwResult = GsonUtil.parseType(result, new TypeToken<MwResult<AccountAddress>>(){});

        System.out.println(GsonUtil.toJson(mwResult));
    }
}