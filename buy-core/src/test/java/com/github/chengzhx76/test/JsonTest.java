package com.github.chengzhx76.test;

import com.alibaba.fastjson.JSON;
import com.github.chengzhx76.buy.model.Query;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/13
 */
public class JsonTest {

    public static void main(String[] args) throws IOException {
        String data = IOUtils.toString(JsonTest.class.getClassLoader().getResourceAsStream("data.json"));
        Query query = JSON.parseObject(data, Query.class);
        System.out.println(query);
    }

}
