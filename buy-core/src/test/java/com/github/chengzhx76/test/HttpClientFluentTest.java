package com.github.chengzhx76.test;

import com.github.chengzhx76.buy.utils.HttpConstant;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.BasicCookieStore;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;

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

    @Test
    public void test_init_doc() throws IOException {
        CookieStore cookieStore = new BasicCookieStore();
//        cookieStore.addCookie(new BasicClientCookie("", "");
        Executor executor = Executor.newInstance();
        String content = executor.use(cookieStore)
                .execute(Request.Post(HttpConstant.URL.INIT_DC)
                .userAgent(HttpConstant.UserAgent.CHROME)
                .setHeader("", ""))
                .returnContent()
                .asString(Charset.forName("UTF-8"));

        System.out.println(content);
    }
}
