package com.github.chengzhx76.buy.utils;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/10
 */
public enum OperationType {
    // 查询
    LOG(HttpConstant.URL.LOG, HttpConstant.Method.GET),
    QUERY(HttpConstant.URL.QUERY, HttpConstant.Method.GET),
    CHECK_USER(HttpConstant.URL.CHECK_USER, HttpConstant.Method.POST),
    CAPTCHA_IMG(HttpConstant.URL.CAPTCHA_IMG, HttpConstant.Method.GET),
    CHECK_CAPTCHA(HttpConstant.URL.CHECK_CAPTCHA, HttpConstant.Method.POST),
    LOGIN(HttpConstant.URL.LOGIN, HttpConstant.Method.POST),
    AUTH_UAMTK(HttpConstant.URL.AUTH_UAMTK, HttpConstant.Method.POST),
    UAM_AUTH_CLIENT(HttpConstant.URL.UAM_AUTH_CLIENT, HttpConstant.Method.POST),
    SUBMIT_ORDER(HttpConstant.URL.SUBMIT_ORDER, HttpConstant.Method.POST),
    INIT_DC(HttpConstant.URL.INIT_DC, HttpConstant.Method.POST),
    PASSENGER(HttpConstant.URL.PASSENGER, HttpConstant.Method.POST),
    CHECK_ORDER(HttpConstant.URL.CHECK_ORDER, HttpConstant.Method.POST),
    QUEUE_COUNT(HttpConstant.URL.QUEUE_COUNT, HttpConstant.Method.POST),
    CONFIRM_SINGLE_FOR_QUEUE(HttpConstant.URL.CONFIRM_SINGLE_FOR_QUEUE, HttpConstant.Method.POST),
    QUERY_ORDER_WAIT_TIME(HttpConstant.URL.QUERY_ORDER_WAIT_TIME, HttpConstant.Method.GET),
    RESULT_ORDER_FOR_DC_QUEUE(HttpConstant.URL.RESULT_ORDER_FOR_DC_QUEUE, HttpConstant.Method.POST),
    TEST(HttpConstant.URL.TEST, HttpConstant.Method.GET),

    END("success", "success");

    private String url;
    private String method;

    OperationType(String url, String method) {
        this.url = url;
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
