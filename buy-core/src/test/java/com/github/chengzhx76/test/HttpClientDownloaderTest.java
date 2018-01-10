package com.github.chengzhx76.test;

import com.github.chengzhx76.buy.Request;
import com.github.chengzhx76.buy.Response;
import com.github.chengzhx76.buy.Site;
import com.github.chengzhx76.buy.httper.HttpClientDownloader;
import com.github.dreamhead.moco.HttpServer;
import com.github.dreamhead.moco.Runnable;
import com.github.dreamhead.moco.Runner;
import org.junit.Test;

import static com.github.dreamhead.moco.Moco.httpServer;


/**
 * @desc:
 * @author: hp
 * @date: 2018/1/10
 */
public class HttpClientDownloaderTest {

    @Test
    public void testDownload() throws Exception {
        HttpServer server = httpServer(9090);
        server.response("foo");
        Runner.running(server, new Runnable() {
            @Override
            public void run() throws Exception {
                final HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
                Request request = new Request();
                request.setUrl("https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2018-01-19&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT");
                Response response = httpClientDownloader.request(request, Site.me());
                System.out.println(response.getContent());
            }
        });
    }

    @Test
    public void test12306() throws Exception {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        Request request = new Request();
        request.setUrl("https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2018-01-19&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT");
        Response response = httpClientDownloader.request(request, Site.me());
        System.out.println(response.getContent());
    }

}
