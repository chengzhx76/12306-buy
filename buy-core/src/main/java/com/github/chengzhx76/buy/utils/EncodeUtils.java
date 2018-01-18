package com.github.chengzhx76.buy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/18
 */
public class EncodeUtils {

    private final static Logger LOG = LoggerFactory.getLogger(EncodeUtils.class);

    /**
     * 将 URL 编码
     */
    public static String encodeURL(String str) {
        String target;
        try {
            target = URLEncoder.encode(str, "UTF-8");
        } catch (Exception e) {
            LOG.error("编码出错！", e);
            throw Exceptions.unchecked(e);
        }
        return target;
    }

    /**
     * 将 URL 解码
     */
    public static String decodeURL(String str) {
        String target;
        try {
            target = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            LOG.error("解码出错！", e);
            throw Exceptions.unchecked(e);
        }
        return target;
    }


}
