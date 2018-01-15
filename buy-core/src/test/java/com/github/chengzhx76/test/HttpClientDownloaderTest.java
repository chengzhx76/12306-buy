package com.github.chengzhx76.test;

import com.github.chengzhx76.buy.Site;
import com.github.chengzhx76.buy.httper.HttpClientDownloader;
import com.github.chengzhx76.buy.httper.HttpUriRequestConverter;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.utils.HttpConstant;
import com.github.chengzhx76.buy.utils.OperationType;
import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runnable;
import com.github.dreamhead.moco.Runner;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

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
                /*HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
                Request request = new Request();
                Map<String, Object> params = new HashMap<>();
                params.put("q","webmagic");

                request.setUrl("http://127.0.0.1:9091/search");
                request.setMethod(HttpConstant.Method.POST);
                request.setRequestBody(HttpRequestBody.form(params, "utf-8"));

                Response response =httpClientDownloader.request(request, Site.me());
                System.out.println(response.getRawText());
                assertThat(response.getRawText()).isEqualTo("post");*/

                //HttpUriRequest httpUriRequest = httpUriRequestConverter.convert(request, Site.me(), null).getHttpUriRequest();
                //assertThat(EntityUtils.toString(HttpClients.custom().build().execute(httpUriRequest).getEntity())).isEqualTo("post");

                //String data = org.apache.http.client.fluent.Request.Post("https://kyfw.12306.cn/passport/captcha/captcha-check")
                String data = org.apache.http.client.fluent.Request.Post("https://kyfw.12306.cn/otn/login/checkUser")
                        .execute().returnContent().asString();

                System.out.println(data);

            }
        });

    }

    @Test
    public void test_postMethod() throws IOException {
        String checkUser = "https://kyfw.12306.cn/otn/login/checkUser";
        //String checkUser = "http://localhost/ServletMVC/index?method=index";
        String data = "";
        //data = org.apache.http.client.fluent.Request.Post("https://kyfw.12306.cn/otn/login/checkUser")
        //        .execute().returnContent().asString();

        Request request = new Request();
        request.setUrl(checkUser);
        request.setMethod(HttpConstant.Method.POST);
        //Map<String, Object> params = new HashMap<>();
        //params.put("q","webmagic");
        //request.setRequestBody(HttpRequestBody.form(params, "utf-8"));

        /*HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Response response =httpClientDownloader.request(request, Site.me());
        data = response.getRawText();*/

        HttpUriRequestConverter httpUriRequestConverter = new HttpUriRequestConverter();
        HttpUriRequest httpUriRequest = httpUriRequestConverter.convert(request, Site.me(), null).getHttpUriRequest();
        //HttpUriRequest httpUriRequest = new HttpGet(checkUser);
        data = EntityUtils.toString(HttpClients.custom().build().execute(httpUriRequest).getEntity(), "UTF-8");

        System.out.println(data);
    }

    @Test
    public void test_postMethod_2() throws IOException {

//        CloseableHttpClient client = HttpClientBuilder.create().setProxy(new HttpHost("127.0.0.1", 8888)).build();

        String checkUser = "https://kyfw.12306.cn/otn/login/checkUser";
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
        String url = "https://kyfw.12306.cn/otn/login/checkUser";
        String data = "";
        /*data = org.apache.http.client.fluent.Request.Post(url)
                .setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36")
                .setHeader("token", "123456")
                .execute().returnContent().asString();*/

        data = EntityUtils.toString(
                HttpClients.custom().build()
                .execute(
                        RequestBuilder
                                .post()
                                .addHeader("Content-Length","0")
                                .setUri(url)
                                .build()
                ).getEntity(), "UTF-8");


        System.out.println(data);
    }


}
