package com.github.chengzhx76.test;

import com.github.chengzhx76.buy.utils.ConfigUtils;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/24
 */
public class PropertiesTest {

    @Test
    public void test_update() {
        Map<String, String> map = new HashMap<>();
        map.put("JSESSIONID", "123445567788tttt");
        ConfigUtils.writeProperties("cookies.properties", map);
    }


    @Test
    public void updateProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Works\\idea\\12306-buy\\buy-core\\src\\main\\resources\\cookies.properties"));
            //OutputStream fos = new FileOutputStream("C:\\Works\\idea\\12306-buy\\buy-core\\src\\main\\resources\\cookies.properties");
            String path = PropertiesTest.class.getClassLoader().getResource("cookies.properties").getPath();
            System.out.println(path.substring(1, path.length()));
            OutputStream fos = new FileOutputStream(path.substring(1, path.length()));
            properties.setProperty("route", "666ujj");
            properties.store(fos, "Update value");
            fos.flush();
            fos.close();
        } catch (IOException e) {
            System.err.println("属性文件更新错误");
        }
    }
    @Test
    public void readPro() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("E:\\Idea\\12306-buy\\buy-core\\src\\main\\resources\\cookies.properties"));
        Iterator<Map.Entry<Object, Object>> it = properties.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Object, Object> entry = it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key   :" + key);
            System.out.println("value :" + value);
            System.out.println("---------------");
        }
    }


}
