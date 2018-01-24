package com.github.chengzhx76.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.chengzhx76.buy.utils.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/18
 */
public class RegexTest {
    public static void main(String[] args) throws IOException {

        String html = "var globalRepeatSubmitToken = 'b73ca29c9307b5573875a64fd9b166d9';";

        html = FileUtils.readFileToString(new File("C:\\Works\\idea\\12306-buy\\html\\initDc.html"));

        System.out.println(html);
        System.out.println("-----------------------");


        String regex = "var globalRepeatSubmitToken = '(\\S+)'";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        }
        //String token = pattern.matcher(html).group(0);
        //System.out.println(token);
    }

    @Test
    public void test_html_ticketInfoForPassengerFormRegex() throws IOException {

        String html = FileUtils.readFileToString(new File("E:\\Idea\\12306-buy\\html\\initDc.html"));

        String ticketInfoForPassengerFormRegex = "var ticketInfoForPassengerForm=(\\{.+\\})?";
        Pattern ticketInfoForPassengerFormPattern = Pattern.compile(ticketInfoForPassengerFormRegex);
        Matcher ticketInfoForPassengerFormMatcher = ticketInfoForPassengerFormPattern.matcher(html);

        String ticketInfoForPassenger = "";
        if (ticketInfoForPassengerFormMatcher.find()) {
            ticketInfoForPassenger = ticketInfoForPassengerFormMatcher.group(1);
        }
        System.out.println(ticketInfoForPassenger);


        JSONObject msg = JSON.parseObject(ticketInfoForPassenger);

        System.out.println(msg.getString("cardTypes"));

    }

}
