package com.github.chengzhx76.test;

import org.apache.http.client.fluent.Request;

import java.io.IOException;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/14
 */
public class HttpClientFluentTest {
    public static void main(String[] args) throws IOException {
        String data = Request.Post("https://kyfw.12306.cn/passport/captcha/captcha-check")
                .connectTimeout(10000)
                .socketTimeout(10000)
                .execute().returnContent().asString();

        System.out.println(data);
    }
}
