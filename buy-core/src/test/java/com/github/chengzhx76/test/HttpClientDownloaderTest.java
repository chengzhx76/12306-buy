package com.github.chengzhx76.test;

import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.Site;
import com.github.chengzhx76.buy.httper.HttpClientDownloader;
import com.github.chengzhx76.buy.utils.HttpConstant;
import com.github.chengzhx76.buy.utils.OperationType;
import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runnable;
import com.github.dreamhead.moco.Runner;
import org.junit.Test;

import static com.github.dreamhead.moco.Moco.*;
import static com.github.dreamhead.moco.Moco.cookie;
import static com.github.dreamhead.moco.Moco.eq;
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
                assertThat(response.getContent()).isEqualTo("ok");
            }
        });
    }


}
