package com.github.chengzhx76.buy.httper;


import com.github.chengzhx76.buy.Site;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.proxy.Proxy;
import com.github.chengzhx76.buy.proxy.ProxyProvider;
import com.github.chengzhx76.buy.utils.HttpConstant;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The http downloader based on HttpClient.
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class HttpClientDownloader implements Downloader {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<String, CloseableHttpClient> httpClients = new HashMap<String, CloseableHttpClient>();

    private HttpClientGenerator httpClientGenerator = new HttpClientGenerator();

    private HttpUriRequestConverter httpUriRequestConverter = new HttpUriRequestConverter();

    private ProxyProvider proxyProvider;
    
    public void setHttpUriRequestConverter(HttpUriRequestConverter httpUriRequestConverter) {
        this.httpUriRequestConverter = httpUriRequestConverter;
    }

    public void setProxyProvider(ProxyProvider proxyProvider) {
        this.proxyProvider = proxyProvider;
    }

    private CloseableHttpClient getHttpClient(Site site) {
        String domain = site.getDomain();
        CloseableHttpClient httpClient = httpClients.get(domain);
        if (httpClient == null) {
            synchronized (this) {
                httpClient = httpClients.get(domain);
                if (httpClient == null) {
                    httpClient = httpClientGenerator.getClient(site);
                    httpClients.put(domain, httpClient);
                }
            }
        }
        return httpClient;
    }

    @Override
    public Response request(Request request, Site site) {
        CloseableHttpResponse httpResponse = null;
        CloseableHttpClient httpClient = getHttpClient(site);
        Proxy proxy = proxyProvider != null ? proxyProvider.getProxy(site) : null;
        HttpClientRequestContext requestContext = httpUriRequestConverter.convert(request, site, proxy);
        Response response = null;
        try {
            httpResponse = httpClient.execute(requestContext.getHttpUriRequest(), requestContext.getHttpClientContext());
            response = handleResponse(httpResponse, site, request);
            //getCookies(requestContext, request);
            onSuccess(request);
            //logger.info("downloading page success {}", request.getOperation());
            return response;
        } catch (IOException e) {
            logger.warn("download page {} error", request.getOperation(), e);
            onError(request);
            return response;
        } finally {
            if (httpResponse != null) {
                //ensure the connection is released back to pool
                EntityUtils.consumeQuietly(httpResponse.getEntity());
            }
            if (proxyProvider != null && proxy != null) {
                proxyProvider.returnProxy(proxy, response, site);
            }
        }
    }

    public void setThread(int thread) {
        httpClientGenerator.setPoolSize(thread);
    }

    protected Response handleResponse(HttpResponse httpResponse, Site site, Request request) throws IOException {
        Response response = new Response();
        byte[] bytes = IOUtils.toByteArray(httpResponse.getEntity().getContent());
        response.setContent(bytes);
        response.setOperation(request.getOperation());
        response.setRequestSuccess(true);
        response.setStatusCode(HttpConstant.StatusCode.CODE_200);
        return response;
    }

    private void getCookies(HttpClientRequestContext requestContext, Request request) {
        for (Cookie cookie : requestContext.getHttpClientContext().getCookieStore().getCookies()) {
            System.out.println(cookie);
        }

    }

    protected void onSuccess(Request request) {
    }

    protected void onError(Request request) {
    }
}
