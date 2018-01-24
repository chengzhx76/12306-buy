package com.github.chengzhx76.test;

import com.github.chengzhx76.buy.model.Site;
import com.github.chengzhx76.buy.httper.Downloader;
import com.github.chengzhx76.buy.httper.client.HttpClientDownloader;
import com.github.chengzhx76.buy.httper.fluent.HttpClientFluent;
import com.github.chengzhx76.buy.model.HttpRequestBody;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.utils.HttpConstant;
import com.github.chengzhx76.buy.utils.OperationType;
import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runnable;
import com.github.dreamhead.moco.Runner;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.dreamhead.moco.Moco.*;
import static org.assertj.core.api.Assertions.assertThat;


/**
 * @desc:
 * @author: hp
 * @date: 2018/1/10
 */
public class HttpClientDownloaderTest {

    @Test
    public void test12306() throws Exception {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Request request = new Request();
        request.setOperation(OperationType.QUERY);
        Response response = httpClientDownloader.request(request, Site.me().setUserAgent(HttpConstant.UserAgent.CHROME));
        System.out.println(response.getContent());
    }

    @Test
    public void testDownload() throws Exception {
        HttpServer server = httpServer(9091);
        server.response("foo");
        Runner.running(server, new Runnable() {
            @Override
            public void run() throws Exception {
                final HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
                Request request = new Request();
                request.setOperation(OperationType.QUERY);
                Response response = httpClientDownloader.request(request, Site.me());
                System.out.println(response.getContent());
            }
        });
    }

    @Test
    public void test_set_request_cookie() throws Exception {
        HttpServer server = httpServer(9091);
        server.get(eq(cookie("cookie"), "cookie-cheng")).response("ok");
        Runner.running(server, new Runnable() {
            @Override
            public void run() throws Exception {
                HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
                Request request = new Request();
                request.setOperation(OperationType.TEST);
                request.addCookie("cookie","cookie-cheng");
                Response response = httpClientDownloader.request(request, Site.me().addCookie("cookie","cookie-cheng"));
                assertThat(response.getContent()).isEqualTo("ok");
            }
        });
    }

    @Test
    public void test_disableCookieManagement() throws Exception {
        HttpServer server = httpServer(9091);
        server.get(not(eq(cookie("cookie"), "cookie-cheng"))).response("ok");
        Runner.running(server, new Runnable() {
            @Override
            public void run() throws Exception {
                HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
                Request request = new Request();
                request.setOperation(OperationType.TEST);
                request.addCookie("cookie","cookie-cheng");
                Response response = httpClientDownloader.request(request, Site.me().setDisableCookieManagement(true));
                assertThat(response.getRawText()).isEqualTo("ok");
            }
        });
    }

    @Test
    public void test_selectRequestMethod() throws Exception {
        HttpServer server = httpServer(9091);
        server.post(eq(query("q"), "webmagic")).response("post");
        Runner.running(server, new Runnable() {
            @Override
            public void run() throws Exception {
                HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
                Request request = new Request();
                Map<String, Object> params = new HashMap<>();
                params.put("q","webmagic");

                request.setUrl("http://127.0.0.1:9091/search");
                request.setMethod(HttpConstant.Method.POST);
                request.setRequestBody(HttpRequestBody.form(params, "utf-8"));

                Response response =httpClientDownloader.request(request, Site.me());
                System.out.println(response.getRawText());
                assertThat(response.getRawText()).isEqualTo("post");
            }
        });

    }

    @Test
    public void test_postMethod() throws IOException {
        String checkUser = "https://kyfw.12306.cn/otn/login/checkUser";
        //String checkUser = "https://kyfw.12306.cn/passport/captcha/captcha-check";
        String data = "";
        Request request = new Request();
        request.setUrl(checkUser);
        request.setMethod(HttpConstant.Method.POST);
        Map<String, Object> params = new HashMap<>();
        params.put("q","webmagic");
        request.setRequestBody(HttpRequestBody.form(params, "utf-8"));

        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Response response =httpClientDownloader.request(request, Site.me());
        data = response.getRawText();

        //HttpUriRequestConverter httpUriRequestConverter = new HttpUriRequestConverter();
        //HttpUriRequest httpUriRequest = httpUriRequestConverter.convert(request, Site.me(), null).getHttpUriRequest();
        //data = EntityUtils.toString(HttpClients.custom().build().execute(httpUriRequest).getEntity(), "UTF-8");

        System.out.println(data);
    }

    @Test
    public void test_postMethod_2() throws IOException {

//        CloseableHttpClient client = HttpClientBuilder.create().setProxy(new HttpHost("127.0.0.1", 8888)).build();

//        String checkUser = "https://kyfw.12306.cn/otn/login/checkUser";
        String checkUser = "https://kyfw.12306.cn/passport/web/login";
        String data = "";
        //data = org.apache.http.client.fluent.Request.Post("https://kyfw.12306.cn/otn/login/checkUser")
        //        .execute().returnContent().asString();

        HttpUriRequest httpUriRequest = new HttpPost(checkUser);

        //Request request = new Request();
        //request.setUrl(checkUser);
        //request.setMethod(HttpConstant.Method.POST);
        //HttpUriRequestConverter httpUriRequestConverter = new HttpUriRequestConverter();
        //HttpUriRequest httpUriRequest = httpUriRequestConverter.convert(request, Site.me(), null).getHttpUriRequest();
        data = EntityUtils.toString(HttpClients.custom().build().execute(httpUriRequest).getEntity(), "UTF-8");

        System.out.println(data);
    }

    @Test
    public void test_postMethod_3() throws IOException {
//        String url = "http://account.xici.net/login";
        String url = "https://kyfw.12306.cn/passport/web/login";
        //String url = "https://kyfw.12306.cn/otn/login/checkUser";
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("username", "cheng"));
        nameValuePairs.add(new BasicNameValuePair("password", "123892"));
        nameValuePairs.add(new BasicNameValuePair("appid", "otn"));
        nameValuePairs.add(new BasicNameValuePair("_json_att", ""));
        String data = "";
        data = org.apache.http.client.fluent.Request.Post(url)
                .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .bodyForm(nameValuePairs)
                .execute().returnContent().asString();

//        byte[] b = URLEncodedUtils.format(nameValuePairs, "UTF-8").getBytes("UTF-8");
//        System.out.println(b.length);

        /*data = EntityUtils.toString(
                HttpClients.custom().build()
                .execute(
                        RequestBuilder
                                .post()
                                .addHeader("Content-Length","0")
                                .setUri(url)
                                .build()
                ).getEntity(), "UTF-8");*/


        System.out.println(data);
    }


    @Test
    public void test_Login() throws IOException {
        String url = "https://kyfw.12306.cn/passport/web/login";
        String data = "";
        Request request = new Request();
        request.setUrl(url);
        request.setMethod(HttpConstant.Method.POST);
        Map<String, Object> params = new HashMap<>();
        params.put("username", "chengzhx67");
        params.put("password", "cgc1027689");
        params.put("appid", "otn");
        params.put("_json_att", "");
        request.setRequestBody(HttpRequestBody.form(params, "utf-8"));

        request.addCookie("BIGipServerpool_passport", "233636362.50215.0000");
        request.addCookie("_passport_ct", "b225878ee0cf465190d1edf0a56eaab2t5545");
        request.addCookie("_passport_session", "7bd182fc3ef349e686d9ddca2373eedd9871");

        request.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        request.addHeader("Accept-Encoding", "gzip, deflate, br");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("Host", "kyfw.12306.cn");
        request.addHeader("Origin", "https://kyfw.12306.cn");
        request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        request.addHeader("X-Requested-With", "XMLHttpRequest");

        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Response response =httpClientDownloader.request(request, Site.me().setUserAgent(HttpConstant.UserAgent.CHROME));
        data = response.getRawText();
        System.out.println(data);
    }

    @Test
    public void test_byte() {
        byte[] b = new String("_json_att=11111&username=chengzhx76&appid=otn&password=123456").getBytes();
        System.out.println(b.length);
    }

    @Test
    public void test_query() throws IOException {
        String url = "https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2018-01-17&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT";
        String data = "";
        data = org.apache.http.client.fluent.Request.Get(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .execute().returnContent().asString();

    }

    @Test
    public void test_query_cookies() throws IOException {
        String url = "https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2018-01-18&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT";
        //String url = "https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2018-01-17&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT";
        CookieStore cookieStore = new BasicCookieStore();

        BasicClientCookie cookie1 = new BasicClientCookie("_jc_save_fromStation", URLEncoder.encode("BJP,北京", "UTF-8"));
        cookie1.setDomain("https://kyfw.12306.cn");
        BasicClientCookie cookie2 = new BasicClientCookie("_jc_save_fromDate", URLEncoder.encode("2018-01-18", "UTF-8"));
        cookie2.setDomain("https://kyfw.12306.cn");
        BasicClientCookie cookie3 = new BasicClientCookie("_jc_save_toStation", URLEncoder.encode("CXK,曹县", "UTF-8"));
        cookie3.setDomain("https://kyfw.12306.cn");
        BasicClientCookie cookie4 = new BasicClientCookie("_jc_save_toDate", URLEncoder.encode("2018-01-18", "UTF-8"));
        cookie4.setDomain("https://kyfw.12306.cn");
        BasicClientCookie cookie5 = new BasicClientCookie("_jc_save_wfdc_flag", "dc");
        cookie5.setDomain("https://kyfw.12306.cn");

        cookieStore.addCookie(cookie1);
        cookieStore.addCookie(cookie2);
        cookieStore.addCookie(cookie3);
        cookieStore.addCookie(cookie4);
        cookieStore.addCookie(cookie5);
        Executor executor = Executor.newInstance();
        String data = executor.use(cookieStore)
                .execute(
                        org.apache.http.client.fluent.Request.Get(url)
                                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                                //.addHeader("Accept", "*/*")
                                //.addHeader("Accept-Encoding", "gzip, deflate, br")
                                //.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                                //.addHeader("Cache-Control", "no-cache")
                                //.addHeader("Connection", "keep-alive")
                                //.addHeader("Host", "kyfw.12306.cn")
                                //.addHeader("If-Modified-Since", "0")
                                //.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init")
                                //.addHeader("X-Requested-With", "XMLHttpRequest")
                                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8")
                                .addHeader("X-Requested-With", "xmlHttpRequest")
                                .addHeader("Referer", "https://kyfw.12306.cn/otn/confirmPassenger/initDc")
                                .addHeader("Accept", "*/*")
                )
                .returnContent()
                .asString(Charset.forName("UTF-8"));
        System.out.println(data);
    }

    /*@Test
    public void test_query_cookies_2() throws IOException {
        String url = "https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2018-01-17&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT";
        //String url = "https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2018-01-17&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT";
        CookieStore cookieStore = new BasicCookieStore();

        BasicClientCookie cookie1 = new BasicClientCookie("_jc_save_fromStation", URLEncoder.encode("BJP,北京", "UTF-8"));
        cookie1.setDomain("https://kyfw.12306.cn");
        BasicClientCookie cookie2 = new BasicClientCookie("_jc_save_fromDate", URLEncoder.encode("2018-01-17", "UTF-8"));
        cookie2.setDomain("https://kyfw.12306.cn");
        BasicClientCookie cookie3 = new BasicClientCookie("_jc_save_toStation", URLEncoder.encode("CXK,曹县", "UTF-8"));
        cookie3.setDomain("https://kyfw.12306.cn");
        BasicClientCookie cookie4 = new BasicClientCookie("_jc_save_toDate", URLEncoder.encode("2018-01-17", "UTF-8"));
        cookie4.setDomain("https://kyfw.12306.cn");
        BasicClientCookie cookie5 = new BasicClientCookie("_jc_save_wfdc_flag", "dc");
        cookie5.setDomain("https://kyfw.12306.cn");

        cookieStore.addCookie(cookie1);
        cookieStore.addCookie(cookie2);
        cookieStore.addCookie(cookie3);
        cookieStore.addCookie(cookie4);
        cookieStore.addCookie(cookie5);

        HttpUriRequestConverter httpUriRequestConverter = new HttpUriRequestConverter();
        HttpUriRequest httpUriRequest = httpUriRequestConverter.convert(request, Site.me(), null).getHttpUriRequest();
        String data = EntityUtils.toString(HttpClients.custom().build().execute(httpUriRequest).getEntity(), "UTF-8");
        System.out.println(data);
    }*/

    @Test
    public void test_download_fluent() throws UnsupportedEncodingException {
        String url = "https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2018-01-17&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT";
        Site site = Site.me().setUserAgent(HttpConstant.UserAgent.FIREFOX);
        Request request = new Request();
        request.setUrl(url);
        request.setMethod(HttpConstant.Method.GET);
        request.addCookie("_jc_save_fromStation", URLEncoder.encode("BJP,北京", "UTF-8"));
        request.addCookie("_jc_save_fromStation", URLEncoder.encode("2018-01-17", "UTF-8"));
        request.addCookie("_jc_save_fromStation", URLEncoder.encode("CXK,曹县", "UTF-8"));
        request.addCookie("_jc_save_fromStation", URLEncoder.encode("2018-01-17", "UTF-8"));

        request.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        request.addHeader("X-Requested-With", "xmlHttpRequest");
        request.addHeader("Referer", "https://kyfw.12306.cn/otn/confirmPassenger/initDc");
        request.addHeader("Accept", "*/*");

        Downloader downloader = new HttpClientFluent();
        Response response = downloader.request(request, site);
        System.out.println(response.getRawText());
    }

    @Test
    public void test() throws UnsupportedEncodingException, InterruptedException {
        while (true) {
            test_download_fluent();
            Thread.sleep(500);
        }
    }


}
