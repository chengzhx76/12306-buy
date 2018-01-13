package com.github.chengzhx76.buy.utils;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/10
 */
public enum OperationType {
    // 查询
    QUERY(HttpConstant.URL.QUERY, HttpConstant.Method.GET),
    LOG(HttpConstant.URL.LOG, HttpConstant.Method.GET),
    LOGIN(HttpConstant.URL.LOGIN, HttpConstant.Method.POST),
    CHECK_USER(HttpConstant.URL.CHECK_USER, HttpConstant.Method.POST),
    CAPTCHA_IMG(HttpConstant.URL.CAPTCHA_IMG, HttpConstant.Method.GET),
    TEST(HttpConstant.URL.TEST, HttpConstant.Method.GET),

    END("success", "success");

    private String url;
    private String method;

    OperationType(String url, String method) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
