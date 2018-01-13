package com.github.chengzhx76.buy.model;


import com.github.chengzhx76.buy.utils.OperationType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Request implements Serializable {

    private static final long serialVersionUID = 2062192774891352043L;

    private String url;

    private String method;

    private OperationType operation;

    private HttpRequestBody requestBody;

    private boolean disableCookieManagement;

    private long sleepTime;

    /**
     * Store additional information in extras.
     */
    private Map<String, Object> extras;

    /**
     * cookies for current url, if not set use Site's cookies
     */
    private Map<String, String> cookies = new HashMap<>();

    private Map<String, String> headers = new HashMap<>();


    public Request() {
    }

    public String getUrl() {
        return url;
    }

    public Request setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public Request setMethod(String method) {
        this.method = method;
        return this;
    }

    public Object getExtra(String key) {
        if (extras == null) {
            return null;
        }
        return extras.get(key);
    }

    public Request putExtra(String key, Object value) {
        if (extras == null) {
            extras = new HashMap<String, Object>();
        }
        extras.put(key, value);
        return this;
    }

    public OperationType getOperation() {
        return operation;
    }

    public Request setOperation(OperationType operation) {
        this.operation = operation;
        return this;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public Request setExtras(Map<String, Object> extras) {
        this.extras = extras;
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

    public HttpRequestBody getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(HttpRequestBody requestBody) {
        this.requestBody = requestBody;
    }
    public boolean isDisableCookieManagement() {
        if (OperationType.QUERY.equals(getOperation())) {
            disableCookieManagement = true;
        }
        return disableCookieManagement;
    }

    /**
     * Downloader is supposed to store response cookie.
     * Disable it to ignore all cookie fields and stay clean.
     * Warning: Set cookie will still NOT work if disableCookieManagement is true.
     * @param disableCookieManagement disableCookieManagement
     * @return this
     */
    public Request setDisableCookieManagement(boolean disableCookieManagement) {
        this.disableCookieManagement = disableCookieManagement;
        return this;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public Request setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }
}
