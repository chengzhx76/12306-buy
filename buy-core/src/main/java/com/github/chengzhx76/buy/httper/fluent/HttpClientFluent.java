package com.github.chengzhx76.buy.httper.fluent;


import com.github.chengzhx76.buy.httper.Downloader;
import com.github.chengzhx76.buy.model.Cookie;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.model.Site;
import com.github.chengzhx76.buy.utils.HttpConstant;
import com.github.chengzhx76.buy.utils.OperationType;
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
import java.util.List;
import java.util.Map;

public class HttpClientFluent implements Downloader {

    private final static Logger LOG = LoggerFactory.getLogger(HttpClientFluent.class);
    private HttpClientFluentGenerator httpClientGenerator = new HttpClientFluentGenerator();
    private CookieStore cookieStore = new BasicCookieStore();
    private CookieStore tempCookieStore = new BasicCookieStore();
    private volatile boolean addCookies = true;

    @Override
    public Response request(Request request, Site site) {

        Response response = null;
        Executor executor = Executor.newInstance(httpClientGenerator.getClient());
        setCookies(request, site);
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
            getCookies(cookieStore, response);
        } catch (IOException e) {
            LOG.error("获取资源出错", e);
            response = new Response();
            response.setOperation(request.getOperation());
            response.setRequestSuccess(false);
        }
        return response;
    }

    private void setCookies(Request request, Site site) {
        if (request.isDisableCookie()) {
            if (OperationType.CAPTCHA_IMG.equals(request.getOperation())) {
                List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
                cookieStore.clear();
                for (org.apache.http.cookie.Cookie cookie : cookies) {
                    if (!"_passport_ct".equals(cookie.getName()) &&
                            !"_passport_session".equals(cookie.getName())) {
                        cookieStore.addCookie(cookie);
                    }
                }
                addCookies(request, site);
            } else if (OperationType.QUERY.equals(request.getOperation())) {
                List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
                // 先把上一个CDN的Cookies删除
                cookieStore.clear();
                addRequestCookies(request);
                for (org.apache.http.cookie.Cookie cookie : cookies) {
                    cookieStore.addCookie(cookie);
                }

            } else {
                cookieStore.clear();
            }
        } else {
            addCookies(request, site);
        }
    }


    private void addCookies(Request request, Site site) {
        addSiteCookies(request, site);
        addRequestCookies(request);
    }

    private void addSiteCookies(Request request, Site site) {
        if (addCookies && site.getCookies() != null && !site.getCookies().isEmpty()) {
            addCookies = false;
            for (Cookie cookieEntry : site.getCookies()) {
                addCookie(request, cookieStore, cookieEntry);
            }
        }
    }
    private void addRequestCookies(Request request) {
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


    private void getCookies(CookieStore cookieStore, Response response) {
        for (org.apache.http.cookie.Cookie cookie : cookieStore.getCookies()) {
            response.addCookie(cookie.getName(), cookie.getValue(), cookie.getDomain(), cookie.getPath());
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
