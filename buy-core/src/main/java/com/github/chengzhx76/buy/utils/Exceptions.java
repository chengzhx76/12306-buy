package com.github.chengzhx76.buy.utils;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/18
 */
public class Exceptions {

    /**
     * 将CheckedException转换为UncheckedException.
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

}
