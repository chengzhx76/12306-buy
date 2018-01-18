package com.github.chengzhx76.test;

import java.util.regex.Pattern;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/18
 */
public class RegexTest {
    public static void main(String[] args) {

        String html = "";

        String regex = "var globalRepeatSubmitToken = '(\\S+)'";
        Pattern pattern = Pattern.compile(regex);
        String token = pattern.matcher(html).group(1);
    }
}
