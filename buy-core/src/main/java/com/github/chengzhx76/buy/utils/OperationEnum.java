package com.github.chengzhx76.buy.utils;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/10
 */
public enum OperationEnum {
    // 查询
    QUERY(HttpConstant.URL.QUERY, HttpConstant.Method.GET),
    LOGIN(HttpConstant.URL.LOGIN, HttpConstant.Method.POST),
    TEST(HttpConstant.URL.TEST, HttpConstant.Method.GET),

    END("success", "success");

    private String url;
    private String method;

    OperationEnum(String url, String method) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
