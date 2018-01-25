package com.github.chengzhx76.test;

import com.github.chengzhx76.buy.utils.HttpConstant;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

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
        Map<String, String> map = new HashMap<>();
        map.put("_jc_save_fromStation", URLEncoder.encode("北京BJP", "UTF-8"));
        map.put("_jc_save_fromDate", "2018-01-26");
        map.put("_jc_save_toStation", URLEncoder.encode("曹县CXK", "UTF-8"));
        map.put("_jc_save_toDate", "2018-01-26");
        map.put("_jc_save_wfdc_flag", "dc");

        List<String> ips = new ArrayList<>();
        ips.add("42.81.28.117");
        ips.add("123.138.60.232");
        ips.add("61.163.111.74");
        ips.add("116.55.236.35");
        ips.add("150.138.214.84");
        ips.add("115.231.20.49");
        ips.add("124.239.186.165");
        ips.add("36.99.32.182");
        ips.add("150.138.169.29");
        ips.add("117.148.165.161");
        ips.add("59.44.148.55");
        ips.add("211.91.168.28");
        ips.add("182.140.147.61");
        ips.add("14.215.231.134");
        ips.add("117.187.46.242");
        ips.add("113.16.210.131");
        ips.add("59.63.244.111");
        ips.add("112.17.29.134");
        ips.add("180.97.244.190");
        ips.add("182.242.43.188");
        ips.add("111.20.250.117");
        ips.add("61.138.219.85");
        ips.add("59.49.94.147");
        ips.add("36.102.238.45");
        ips.add("111.6.192.131");
        ips.add("182.140.147.104");
        ips.add("182.34.127.23");
        ips.add("122.143.27.169");
        ips.add("119.6.226.157");
        ips.add("121.30.196.35");
        ips.add("123.183.164.137");
        ips.add("218.29.50.20");
        ips.add("125.77.130.216");
        ips.add("123.128.14.198");
        ips.add("58.18.254.234");
        ips.add("61.147.227.140");
        ips.add("113.207.77.115");
        ips.add("182.140.147.79");
        ips.add("61.184.117.30");
        ips.add("120.221.25.177");
        ips.add("61.136.167.78");
        ips.add("219.146.68.78");
        ips.add("117.145.179.145");


        for (;;) {
            int index = new Random().nextInt(ips.size());
            String ip = ips.get(index);
            System.out.println(index+"-->"+ips.get(index));

            String url = "http://"+ip+"/otn/leftTicket/queryZ?leftTicketDTO.train_date=2018-01-26&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT";

            CookieStore cookieStore = new BasicCookieStore();

            for (Map.Entry<String, String> cookieEntry : map.entrySet()) {
                BasicClientCookie cookie = new BasicClientCookie(cookieEntry.getKey(), cookieEntry.getValue());
                cookie.setDomain(ip);
                cookie.setPath("/");
                cookieStore.addCookie(cookie);
            }

            Executor executor = Executor.newInstance();
            String content = executor.use(cookieStore)
                    .execute(Request.Get(url)
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

            sleep();
        }
    }

    public void sleep() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
