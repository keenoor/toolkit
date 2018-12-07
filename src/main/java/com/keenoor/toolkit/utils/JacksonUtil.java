package com.keenoor.toolkit.utils;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Author: chenliuchun
 * Date: 2018/6/21
 * Description: jackson 处理 json 的工具类
 * Ref: http://www.ibm.com/developerworks/cn/java/jackson-advanced-application/index.html
 */

public class JacksonUtil {

    protected static final ObjectMapper mapper = new ObjectMapper()
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    private static final String PARSE_JSON="parse json: ";
    
    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(PARSE_JSON, e);
        }
    }

    public static <T> T parseObj(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(PARSE_JSON, e);
        }
    }

    public static <T> T[] parseArray(String json, Class<T[]> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(PARSE_JSON, e);
        }
    }

    public static <T> List<T> parseList(String json, Class<T> clazz) {
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(PARSE_JSON, e);
        }
    }

    public static <K,V> Map<K, V> parseMap(String json, Class<K> kClass, Class<V> vClass) {
        MapType type = mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass);
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(PARSE_JSON, e);
        }
    }

    public static <K,V> List<Map<K, V>> parseListMap(String json, Class<K> kClass, Class<V> vClass) {
        MapType mapType = mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass);
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, mapType);
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(PARSE_JSON, e);
        }
    }

    public static <T> T parseType(String json, TypeReference<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException(PARSE_JSON, e);
        }
    }

}
