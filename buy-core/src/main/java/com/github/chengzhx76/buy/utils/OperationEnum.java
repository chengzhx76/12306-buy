package com.github.chengzhx76.buy.utils;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/10
 */
public enum OperationEnum {
    // 查询
    QUERY(Constant.QUERY);

    private String url;

    OperationEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
