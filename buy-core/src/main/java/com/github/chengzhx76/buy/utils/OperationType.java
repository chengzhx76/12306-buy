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
