package com.github.chengzhx76.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/13
 */
public class JsonTest {

    public static void main(String[] args) throws IOException {
        String data = IOUtils.toString(JsonTest.class.getClassLoader().getResourceAsStream("data.json"));
//        Query query = JSON.parseObject(data, Query.class);
//        System.out.println(query);
    }

    @Test
    public void test_jsonObject() throws IOException {
        String data = IOUtils.toString(JsonTest.class.getClassLoader().getResourceAsStream("data.json"));
        System.out.println(data);
        JSONObject object = JSON.parseObject(data);
        System.out.println(object.getString("cardTypes"));
        System.out.println(object.getJSONArray("cardTypes").get(0));
    }

    @Test
    public void test_jsonObject_2() throws IOException {
        String data = IOUtils.toString(JsonTest.class.getClassLoader().getResourceAsStream("data.json"));
        System.out.println(data);
        JSONObject object = JSON.parseObject(data);
        System.out.println(object.getString("data"));
    }


}
