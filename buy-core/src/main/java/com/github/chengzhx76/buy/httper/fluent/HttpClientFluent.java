package com.github.chengzhx76.buy.httper.fluent;


import com.github.chengzhx76.buy.Site;
import com.github.chengzhx76.buy.httper.Downloader;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.utils.HttpConstant;
import com.github.chengzhx76.buy.utils.UrlUtils;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Executor;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class HttpClientFluent implements Downloader {

    private final static Logger LOG = LoggerFactory.getLogger(HttpClientFluent.class);
    private HttpClientFluentGenerator httpClientGenerator = new HttpClientFluentGenerator();
    CookieStore cookieStore = new BasicCookieStore();

    @Override
    public Response request(Request request, Site site) {

        Response response = null;
        Executor executor = Executor.newInstance(httpClientGenerator.getClient());
        setCookie(request);
        try {
            Content content = executor.use(cookieStore)
                    .execute(
                            selectRequestMethod(request)
                                    .userAgent(site.getUserAgent())
                                    .setHeaders(setHeader(request))
                    )
                    .returnContent();
            response = handleResponse(content, site, request);
            getCookies(cookieStore, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void setCookie(Request request) {
//        CookieStore cookieStore = new BasicCookieStore();
        if (request.getCookies() != null && !request.getCookies().isEmpty()) {
            for (Map.Entry<String, String> cookieEntry : request.getCookies().entrySet()) {
                BasicClientCookie cookie = new BasicClientCookie(cookieEntry.getKey(), cookieEntry.getValue());
                cookie.setDomain(UrlUtils.getDomain(request.getUrl()));
                cookie.setPath("/");
                cookieStore.addCookie(cookie);
            }
        }
//        return cookieStore;
    }

    private Header[] setHeader(Request request) {
        Header headers[] = new Header[request.getHeaders().size()];
        if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
            int i = 0;
            for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
                headers[i] = new BasicHeader(header.getKey(), header.getValue());
                i++;
            }
        }
        return headers;
    }

    private Response handleResponse(Content content, Site site, Request request) {
        Response response = new Response();
        response.setContent(content.asBytes());
        response.setOperation(request.getOperation());
        response.setRequestSuccess(true);
        response.setStatusCode(HttpConstant.StatusCode.CODE_200);
        return response;
    }

    private org.apache.http.client.fluent.Request selectRequestMethod(Request request) {
        String method = request.getMethod();
        String url = request.getUrl();
        if (method == null || method.equalsIgnoreCase(HttpConstant.Method.GET)) {
            return org.apache.http.client.fluent.Request.Get(url);
        } else if (method.equalsIgnoreCase(HttpConstant.Method.POST)) {
            return addFormParams(org.apache.http.client.fluent.Request.Post(url), request);
        }
        throw new IllegalArgumentException("Illegal HTTP Method " + method);
    }

    private org.apache.http.client.fluent.Request addFormParams(org.apache.http.client.fluent.Request request, Request requestMode) {
        if (requestMode.getRequestBody() != null) {
            ByteArrayEntity entity = new ByteArrayEntity(requestMode.getRequestBody().getBody());
            entity.setContentType(requestMode.getRequestBody().getContentType());
            request.body(entity);
        }
        return request;
    }

    private void getCookies(CookieStore cookieStore, Request request) {
        for (Cookie cookie : cookieStore.getCookies()) {
            System.out.println(cookie);
        }
    }
}
