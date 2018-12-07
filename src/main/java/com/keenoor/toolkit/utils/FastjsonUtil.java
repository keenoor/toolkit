package com.keenoor.toolkit.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Author: chenliuchun
 * Date: 2018/6/21
 * Description: fastjson 处理 json 的工具类
 * 可用注解： @JSONField
 */

public class FastjsonUtil {

    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T parseObj(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, clazz);
    }

    public static <T> T[] parseArray(String json, Class<T[]> clazz) {
        return JSONObject.parseObject(json, clazz);
    }

    public static <T> List<T> parseList(String json, Class<T> clazz) {
        return JSONArray.parseArray(json, clazz);
    }

    public static <K, V> Map<K, V> parseMap(String json, Class<K> kClass, Class<V> vClass) {
        ParameterizedType type = TypeUtils.parameterize(Map.class, kClass, vClass);
        return JSONArray.parseObject(json, type);
    }

    public static <K, V> List<Map<K, V>> parseListMap(String json, Class<K> kClass, Class<V> vClass) {
        ParameterizedType mapType = TypeUtils.parameterize(Map.class, kClass, vClass);
        ParameterizedType type = TypeUtils.parameterize(List.class, mapType);
        return JSONObject.parseObject(json, type);
    }

    public static <T> T parseType(String json, TypeReference<T> type) {
        return JSONObject.parseObject(json, type);
    }

}
