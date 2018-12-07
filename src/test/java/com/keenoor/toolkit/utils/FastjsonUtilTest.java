package com.keenoor.toolkit.utils;


import com.keenoor.toolkit.utils.model.City;

import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author: chenliuchun
 * Date:        2018/8/7
 * Description:
 * Modification History:
 * Date       Author       Version     Description
 * -----------------------------------------------------
 */
public class FastjsonUtilTest {

    String jsonArray = "[\n" +
            "    {\n" +
            "        \"code\": 101,\n" +
            "        \"city\": \"江苏苏州\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"code\": 102,\n" +
            "        \"city\": \"江苏泰州\"\n" +
            "    }\n" +
            "]";

    String jsonMap = "{\n" +
            "    \"city\": {\n" +
            "        \"code\": 101,\n" +
            "        \"city\": \"苏州\"\n" +
            "    },\n" +
            "    \"town\": {\n" +
            "        \"code\": 102,\n" +
            "        \"city\": \"杭州\"\n" +
            "    }\n" +
            "}";

    String jsonListMap = "[\n" +
            "    {\n" +
            "        \"name\": {\n" +
            "            \"code\": 101,\n" +
            "            \"city\": \"苏州\"\n" +
            "        }\n" +
            "    },\n" +
            "    {\n" +
            "        \"name\": {\n" +
            "            \"code\": 102,\n" +
            "            \"city\": \"南京\"\n" +
            "        }\n" +
            "    }\n" +
            "]";


    @Test
    public void toJson() {
    }

    @Test
    public void parseObj() {
    }

    @Test
    public void parseArray() {
        City[] cities = FastjsonUtil.parseArray(jsonArray, City[].class);
        System.out.println(cities.length);
    }

    @Test
    public void parseList() {
        List<City> cities = FastjsonUtil.parseList(jsonArray, City.class);
        System.out.println(cities.size());
    }

    @Test
    public void parseListMap() {
        List<Map<String, City>> maps = FastjsonUtil.parseListMap(jsonListMap, String.class, City.class);
        System.out.println(maps.size());
    }

    @Test
    public void parseType() {
    }

}