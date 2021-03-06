package com.keenoor.toolkit.utils;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.reflect.TypeUtils;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Author: chenliuchun
 * Date: 2018/6/21
 * Description: gson 处理 json 的工具类
 * 可用注解： @SerializedName
 */

public class GsonUtil {

    protected static final Gson gson = new Gson();

    public static String toJson(Object object) {
        return GsonUtil.gson.toJson(object);
    }

    public static <T> T parseObj(String json, Class<T> clazz) {
        return GsonUtil.gson.fromJson(json, clazz);
    }

    public static <T> T[] parseArray(String json, Class<T[]> clazz) {
        return GsonUtil.gson.fromJson(json, clazz);
    }

    public static <T> List<T> parseList(String json, Class<T> clazz) {
        ParameterizedType type = TypeUtils.parameterize(List.class, clazz);
        return GsonUtil.gson.fromJson(json, type);
    }

    public static <K, V> Map<K, V> parseMap(String json, Class<K> kClass, Class<V> vClass) {
        ParameterizedType type = TypeUtils.parameterize(Map.class, kClass, vClass);
        return GsonUtil.gson.fromJson(json, type);
    }

    public static <K, V> List<Map<K, V>> parseListMap(String json, Class<K> kClass, Class<V> vClass) {
        ParameterizedType mapType = TypeUtils.parameterize(Map.class, kClass, vClass);
        ParameterizedType listType = TypeUtils.parameterize(List.class, mapType);
        return GsonUtil.gson.fromJson(json, listType);
    }

    public static <T> T parseType(String json, TypeToken<T> type) {
        return GsonUtil.gson.fromJson(json, type.getType());
    }

}
