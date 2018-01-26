package com.github.chengzhx76.buy.httper.fluent;


import com.github.chengzhx76.buy.httper.Downloader;
import com.github.chengzhx76.buy.model.Cookie;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.model.Site;
import com.github.chengzhx76.buy.utils.HttpConstant;
import com.github.chengzhx76.buy.utils.UrlUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Executor;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class HttpClientFluent implements Downloader {

    private final static Logger LOG = LoggerFactory.getLogger(HttpClientFluent.class);
    private HttpClientFluentGenerator httpClientGenerator = new HttpClientFluentGenerator();
    private CookieStore cookieStore = new BasicCookieStore();
    private volatile boolean addCookies = true;

    @Override
    public Response request(Request request, Site site) {

        Response response = null;
        Executor executor = Executor.newInstance(httpClientGenerator.getClient());
        addCookies(request, site);
        deleteCookies(request, executor);
        org.apache.http.client.fluent.Request fluentReq = selectRequestMethod(request)
                                                                .connectTimeout(site.getTimeOut())
                                                                .socketTimeout(site.getTimeOut())
                                                            .userAgent(site.getUserAgent());
        addHeaders(fluentReq, request, site);
        try {
            Content content = executor.use(cookieStore)
                    .execute(fluentReq)
                    .returnContent();
            response = handleResponse(content, site, request);
            getCookies(cookieStore, request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void addCookies(Request request, Site site) {
        if (addCookies && site.getCookies() != null && !site.getCookies().isEmpty()) {
            addCookies = false;
            for (Cookie cookieEntry : site.getCookies()) {
                addCookie(request, cookieStore, cookieEntry);
            }
        }
        if (request.getCookies() != null && !request.getCookies().isEmpty()) {
            for (Cookie cookieEntry : request.getCookies()) {
                addCookie(request, cookieStore, cookieEntry);
            }
        }
    }

    private void addCookie(Request request, CookieStore cookieStore, Cookie cookieEntry) {
        BasicClientCookie cookie = new BasicClientCookie(cookieEntry.getName(), cookieEntry.getValue());
        if (StringUtils.isNotBlank(cookieEntry.getDomain())) {
            cookie.setDomain(cookieEntry.getDomain());
        } else {
            cookie.setDomain(UrlUtils.getDomain(request.getUrl()));
        }
        if (StringUtils.isNotBlank(cookieEntry.getPath())) {
            cookie.setPath(cookieEntry.getPath());
        } else {
            cookie.setPath("/");
        }
        cookieStore.addCookie(cookie);
    }

    private void deleteCookies(Request request, Executor executor) {
        if (request.isDisableCookieManagement()) {
            executor.clearCookies();
        }
    }

    private void getCookies(CookieStore cookieStore, Request request) {
        for (org.apache.http.cookie.Cookie cookie : cookieStore.getCookies()) {
            request.addCookie(cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath());
        }
    }

    private void addHeaders(org.apache.http.client.fluent.Request fluentReq, Request request, Site site) {
        if (site.getHeaders() != null && !site.getHeaders().isEmpty()) {
            for (Map.Entry<String, String> header : site.getHeaders().entrySet()) {
                fluentReq.addHeader(header.getKey(), header.getValue());
            }
        }

        if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
            for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
                fluentReq.addHeader(header.getKey(), header.getValue());
            }
        }

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

}
