package com.github.chengzhx76.buy.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/10
 */
public enum OperationType {
    LOG(HttpConstant.URL.LOG, HttpConstant.Method.GET, HttpConstant.Headers.LOG),
    QUERY(HttpConstant.URL.QUERY, HttpConstant.Method.GET, HttpConstant.Headers.QUERY),
    CHECK_USER(HttpConstant.URL.CHECK_USER, HttpConstant.Method.POST, HttpConstant.Headers.CHECK_USER),
    CAPTCHA_IMG(HttpConstant.URL.CAPTCHA_IMG, HttpConstant.Method.GET, HttpConstant.Headers.CAPTCHA_IMG),
    CHECK_CAPTCHA(HttpConstant.URL.CHECK_CAPTCHA, HttpConstant.Method.POST, HttpConstant.Headers.CHECK_CAPTCHA),
    LOGIN(HttpConstant.URL.LOGIN, HttpConstant.Method.POST, HttpConstant.Headers.LOGIN),
    AUTH_UAMTK(HttpConstant.URL.AUTH_UAMTK, HttpConstant.Method.POST, HttpConstant.Headers.AUTH_UAMTK),
    UAM_AUTH_CLIENT(HttpConstant.URL.UAM_AUTH_CLIENT, HttpConstant.Method.POST, HttpConstant.Headers.UAM_AUTH_CLIENT),
    SUBMIT_ORDER(HttpConstant.URL.SUBMIT_ORDER, HttpConstant.Method.POST, HttpConstant.Headers.SUBMIT_ORDER),
    INIT_DC(HttpConstant.URL.INIT_DC, HttpConstant.Method.POST, HttpConstant.Headers.INIT_DC),
    PASSENGER(HttpConstant.URL.PASSENGER, HttpConstant.Method.POST, HttpConstant.Headers.PASSENGER),
    CHECK_ORDER(HttpConstant.URL.CHECK_ORDER, HttpConstant.Method.POST, HttpConstant.Headers.CHECK_ORDER),
    QUEUE_COUNT(HttpConstant.URL.QUEUE_COUNT, HttpConstant.Method.POST, HttpConstant.Headers.QUEUE_COUNT),
    CONFIRM_SINGLE_FOR_QUEUE(HttpConstant.URL.CONFIRM_SINGLE_FOR_QUEUE, HttpConstant.Method.POST, HttpConstant.Headers.CONFIRM_SINGLE_FOR_QUEUE),
    QUERY_ORDER_WAIT_TIME(HttpConstant.URL.QUERY_ORDER_WAIT_TIME, HttpConstant.Method.GET, HttpConstant.Headers.QUERY_ORDER_WAIT_TIME),
    RESULT_ORDER_FOR_DC_QUEUE(HttpConstant.URL.RESULT_ORDER_FOR_DC_QUEUE, HttpConstant.Method.POST, HttpConstant.Headers.RESULT_ORDER_FOR_DC_QUEUE),
    TEST(HttpConstant.URL.TEST, HttpConstant.Method.GET, HttpConstant.Headers.LOG),

    END("success", "success", new HashMap<String, String>());

    private String url;
    private String method;
    private Map<String, String> header;

    OperationType(String url, String method, Map<String, String> header) {
        this.url = url;
        this.method = method;
        this.header = header;

    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, String> getHeader() {
        return header;
    }
}
