package com.github.chengzhx76.test;

import com.github.chengzhx76.buy.utils.HttpConstant;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/25
 */
public class ReadIPTest {
    @Test
    public void test_read_ip() throws IOException {
        List<String> ips = IOUtils.readLines(ReadIPTest.class.getClassLoader().getResourceAsStream("ips.txt"));
        System.out.println(ips.size());
    }

    @Test
    public void test_merge_cdn() throws IOException {
        List<String> ips = IOUtils.readLines(ReadIPTest.class.getClassLoader().getResourceAsStream("ips.txt"));
        List<String> ips_2 = IOUtils.readLines(ReadIPTest.class.getClassLoader().getResourceAsStream("ips-2.txt"));
        ips.addAll(ips_2);

        Set<String> cdns = new HashSet<>();
        for (String cdn : ips) {
            cdns.add(cdn.trim());
        }
        System.out.println(cdns.size());
        FileWriter writer = new FileWriter("E:\\Idea\\12306-buy\\buy-core\\src\\test\\resources\\cdn.txt");

        IOUtils.writeLines(cdns, null, writer);

        writer.flush();
        writer.close();
    }

    @Test
    public void test_conn_cdn() throws IOException {
        String date = "2018-02-02";
        Map<String, String> map = new HashMap<>();
        map.put("_jc_save_fromStation", URLEncoder.encode("北京BJP", "UTF-8"));
        map.put("_jc_save_fromDate", date);
        map.put("_jc_save_toStation", URLEncoder.encode("曹县CXK", "UTF-8"));
        map.put("_jc_save_toDate", date);
        map.put("_jc_save_wfdc_flag", "dc");

        List<String> cdns = IOUtils.readLines(ReadIPTest.class.getClassLoader().getResourceAsStream("cdn.txt"));


        FileWriter writer = new FileWriter("E:\\Idea\\12306-buy\\buy-core\\src\\test\\resources\\cdn-black.txt");

        for (String cdn : cdns) {

            System.out.println("测试--> " + cdn);

            String url = "http://"+cdn+"/otn/leftTicket/queryZ?leftTicketDTO.train_date="+date+"&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT";

            CookieStore cookieStore = new BasicCookieStore();

            for (Map.Entry<String, String> cookieEntry : map.entrySet()) {
                BasicClientCookie cookie = new BasicClientCookie(cookieEntry.getKey(), cookieEntry.getValue());
                cookie.setDomain(cdn);
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
                System.out.println();

                System.out.println("不可用--> " + cdn + " 错误信息--> " + e.getMessage());

                IOUtils.write(cdn+"\r\n", writer);

            }
            sleep();
        }

        writer.flush();
        writer.close();

    }

    public void sleep() {
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
