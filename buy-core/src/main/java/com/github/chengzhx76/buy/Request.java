package com.github.chengzhx76.buy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Request implements Serializable {

    private static final long serialVersionUID = 2062192774891352043L;

    private String url;

    private String method;

    private Map<String, String> requestBody;

    /**
     * cookies for current url, if not set use Site's cookies
     */
    private Map<String, String> cookies = new HashMap<String, String>();

    private Map<String, String> headers = new HashMap<String, String>();


    public Request() {
    }

    public Request(String url) {
        this.url = url;
    }

    public Request putParam(String key, String value) {
        if (requestBody == null) {
            requestBody = new HashMap<String, String>();
        }
        requestBody.put(key, value);
        return this;
    }

    public Map<String, String> getRequestBody() {
        return requestBody;
    }

    public Request setRequestBody(Map<String, String> requestBody) {
        this.requestBody = requestBody;
        return this;
    }

    public String getUrl() {
        return url;
    }
    public Request setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * The http method of the request. Get for default.
     * @return httpMethod
     * @see com.github.chengzhx76.buy.utils.HttpConstant.Method
     * @since 0.5.0
     */
    public String getMethod() {
        return method;
    }

    public Request setMethod(String method) {
        this.method = method;
        return this;
    }

    public Request addCookie(String name, String value) {
        cookies.put(name, value);
        return this;
    }

    public Request addHeader(String name, String value) {
        headers.put(name, value);
        return this;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

}
