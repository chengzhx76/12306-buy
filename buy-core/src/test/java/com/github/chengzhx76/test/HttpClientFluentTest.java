package com.github.chengzhx76.test;

import com.github.chengzhx76.buy.utils.HttpConstant;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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


    @Test
    public void test_cnd() throws IOException {
        String date = "2018-01-29";
        Map<String, String> map = new HashMap<>();
        map.put("_jc_save_fromStation", URLEncoder.encode("北京BJP", "UTF-8"));
        map.put("_jc_save_fromDate", date);
        map.put("_jc_save_toStation", URLEncoder.encode("曹县CXK", "UTF-8"));
        map.put("_jc_save_toDate", date);
        map.put("_jc_save_wfdc_flag", "dc");

        List<String> ips = IOUtils.readLines(ReadIPTest.class.getClassLoader().getResourceAsStream("cdn.txt"));


        for (;;) {
            int index = new Random().nextInt(ips.size());
            String ip = ips.get(index);
            System.out.println(index+"-->"+ip);

            String url = "http://"+ip+"/otn/leftTicket/queryZ?leftTicketDTO.train_date="+date+"&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT";

            CookieStore cookieStore = new BasicCookieStore();

            for (Map.Entry<String, String> cookieEntry : map.entrySet()) {
                BasicClientCookie cookie = new BasicClientCookie(cookieEntry.getKey(), cookieEntry.getValue());
                cookie.setDomain(ip);
                cookie.setPath("/");
                cookieStore.addCookie(cookie);
            }

            try {
                Executor executor = Executor.newInstance();
                String content = executor.use(cookieStore)
                        .execute(Request.Get(url)
                                .connectTimeout(1000)
                                .socketTimeout(1000)
                                .userAgent(HttpConstant.UserAgent.CHROME)
                                .addHeader(HttpConstant.Header.ACCEPT_ENCODING, HttpConstant.HeaderValue.ENCODING)
                                .addHeader(HttpConstant.Header.ACCEPT_LANGUAGE, HttpConstant.HeaderValue.LANGUAGE)
                                .addHeader(HttpConstant.Header.CONNECTION, HttpConstant.HeaderValue.KEEP_ALIVE)
                                .addHeader(HttpConstant.Header.HOST, HttpConstant.HeaderValue.HOST)
                                .addHeader(HttpConstant.Header.ORIGIN, HttpConstant.HeaderValue.ORIGIN)
                                .addHeader(HttpConstant.Header.ACCEPT, HttpConstant.HeaderValue.APPLICATION_ALL)
                                .addHeader(HttpConstant.Header.IF_MODIFIED_SINCE, HttpConstant.HeaderValue.ZERO)
                                .addHeader(HttpConstant.Header.REFERER, HttpConstant.Referer.INIT)
                                .addHeader(HttpConstant.Header.X_REQUESTED_WITH, HttpConstant.HeaderValue.XML))
                        .returnContent()
                        .asString(Charset.forName("UTF-8"));

                System.out.println(content);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            sleep();
        }
    }

    public void sleep() {
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
