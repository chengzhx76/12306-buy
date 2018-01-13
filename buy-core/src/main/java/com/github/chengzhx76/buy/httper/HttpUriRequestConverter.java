package com.github.chengzhx76.buy.httper;

import com.github.chengzhx76.buy.Site;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.proxy.Proxy;
import com.github.chengzhx76.buy.utils.HttpConstant;
import com.github.chengzhx76.buy.utils.UrlUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.ChallengeState;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.util.Map;

public class HttpUriRequestConverter {

    public HttpClientRequestContext convert(Request request, Site site, Proxy proxy) {
        HttpClientRequestContext httpClientRequestContext = new HttpClientRequestContext();
        httpClientRequestContext.setHttpClientContext(convertHttpClientContext(request, site, proxy));
        httpClientRequestContext.setHttpUriRequest(convertHttpUriRequest(request, site, proxy));
        return httpClientRequestContext;
    }

    private HttpClientContext convertHttpClientContext(Request request, Site site, Proxy proxy) {
        HttpClientContext httpContext = new HttpClientContext();
        if (proxy != null && proxy.getUsername() != null) {
            AuthState authState = new AuthState();
            authState.update(new BasicScheme(ChallengeState.PROXY),
                    new UsernamePasswordCredentials(proxy.getUsername(), proxy.getPassword()));
            httpContext.setAttribute(HttpClientContext.PROXY_AUTH_STATE, authState);
        }
        if (request.isDisableCookieManagement()) {
            CookieStore store = httpContext.getCookieStore();
            if (store != null) {
                store.clear();
            }
        } else {
            if (request.getCookies() != null && !request.getCookies().isEmpty()) {
                CookieStore cookieStore = new BasicCookieStore();
                for (Map.Entry<String, String> cookieEntry : request.getCookies().entrySet()) {
                    BasicClientCookie cookie = new BasicClientCookie(cookieEntry.getKey(), cookieEntry.getValue());
                    cookie.setDomain(UrlUtils.getDomain(request.getUrl()));
                    cookieStore.addCookie(cookie);
                }
                httpContext.setCookieStore(cookieStore);
            }
        }
        return httpContext;
    }

    private HttpUriRequest convertHttpUriRequest(Request request, Site site, Proxy proxy) {
        RequestBuilder requestBuilder = selectRequestMethod(request).setUri(request.getUrl());

        if (site.getHeaders() != null) {
            for (Map.Entry<String, String> headerEntry : site.getHeaders().entrySet()) {
                requestBuilder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }

        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
        if (site != null) {
            requestConfigBuilder.setConnectionRequestTimeout(site.getTimeOut())
                    .setSocketTimeout(site.getTimeOut())
                    .setConnectTimeout(site.getTimeOut())
                    .setCookieSpec(CookieSpecs.STANDARD);
        }

        if (proxy != null) {
            requestConfigBuilder.setProxy(new HttpHost(proxy.getHost(), proxy.getPort()));
        }
        requestBuilder.setConfig(requestConfigBuilder.build());
        HttpUriRequest httpUriRequest = requestBuilder.build();
        if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
            for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
                httpUriRequest.addHeader(header.getKey(), header.getValue());
            }
        }
        return httpUriRequest;
    }

    private RequestBuilder selectRequestMethod(Request request) {
        String method = request.getMethod();
        if (method == null || method.equalsIgnoreCase(HttpConstant.Method.GET)) {
            //default get
            return RequestBuilder.get();
        } else if (method.equalsIgnoreCase(HttpConstant.Method.POST)) {
            return addFormParams(RequestBuilder.post(), request);
        }
        throw new IllegalArgumentException("Illegal HTTP Method " + method);
    }

    private RequestBuilder addFormParams(RequestBuilder requestBuilder, Request request) {
        if (request.getRequestBody() != null) {
            ByteArrayEntity entity = new ByteArrayEntity(request.getRequestBody().getBody());
            entity.setContentType(request.getRequestBody().getContentType());
            requestBuilder.setEntity(entity);
        }
        return requestBuilder;
    }

}
