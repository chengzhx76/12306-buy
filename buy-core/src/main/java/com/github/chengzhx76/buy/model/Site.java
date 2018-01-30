package com.github.chengzhx76.buy.model;


import com.github.chengzhx76.buy.utils.ConfigUtils;
import com.github.chengzhx76.buy.utils.HttpConstant;

import java.util.*;

public class Site {

    private String domain;

    private String userAgent;

    private long sleepTime = 3000L;

    private int timeOut = 10000;

    private static final Set<Integer> DEFAULT_STATUS_CODE_SET = new HashSet<>();

    private Set<Integer> acceptStatCode = DEFAULT_STATUS_CODE_SET;

//    private Map<String, String> cookies = new LinkedHashMap<>();
    private Set<Cookie> cookies = new HashSet<>();

    private Map<String, String> headers = new HashMap<>();

    private boolean disableCookieManagement = false;

    static {
        DEFAULT_STATUS_CODE_SET.add(HttpConstant.StatusCode.CODE_200);
    }

    public static Site me() {
        return new Site();
    }

    public Site addCookie(String name, String value) {
        cookies.add(new Cookie(name, value));
        return this;
    }

    public Site addCookie(String name, String value, String path) {
        cookies.add(new Cookie(name, value, null, path));
        return this;
    }

    public Site setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public Set<Cookie> getCookies() {
        return cookies;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getDomain() {
        return domain;
    }

    public Site setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public Site setTimeOut(int timeOut) {
        this.timeOut = timeOut;
        return this;
    }

    public Site setAcceptStatCode(Set<Integer> acceptStatCode) {
        this.acceptStatCode = acceptStatCode;
        return this;
    }

    public Set<Integer> getAcceptStatCode() {
        return acceptStatCode;
    }

    public Site setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    public long getSleepTime() {
        return sleepTime;
    }


    public Map<String, String> getHeaders() {
        return headers;
    }

    public Site addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public Site defaultHeader() {
        headers.put(HttpConstant.Header.ACCEPT_ENCODING, HttpConstant.HeaderValue.ENCODING);
        headers.put(HttpConstant.Header.ACCEPT_LANGUAGE, HttpConstant.HeaderValue.LANGUAGE);
        headers.put(HttpConstant.Header.CONNECTION, HttpConstant.HeaderValue.KEEP_ALIVE);
        headers.put(HttpConstant.Header.HOST, HttpConstant.HeaderValue.HOST);
        headers.put(HttpConstant.Header.ORIGIN, HttpConstant.HeaderValue.ORIGIN);
        return this;
    }

    public Site defaultCookies() {
        Properties properties = ConfigUtils.loadProperties("cookies.properties");
        for (String key : properties.stringPropertyNames()) {
            String data = properties.getProperty(key);
            String value = null;
            String path = null;
            if (data.contains("|")) {
                value = data.split("\\|")[0];
                path = data.split("\\|")[1];
            } else {
                value = data;
            }
            addCookie(key, value, path);
        }
        return this;
    }


    public boolean isDisableCookieManagement() {
        return disableCookieManagement;
    }

    public Site setDisableCookieManagement(boolean disableCookieManagement) {
        this.disableCookieManagement = disableCookieManagement;
        return this;
    }


}
