package com.github.chengzhx76.test;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/22
 */
public class TestDate {
    public static void main(String[] args) {
        System.out.println(new Date());
    }

    @Test
    public void test_format_date() {
        // Mon Jan 22 2018 00:00:00 GMT+0800 (中国标准时间)
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d yyyy 00:00:00 'GMT+0800 (中国标准时间)'", Locale.ENGLISH);
        System.out.println(dateFormat.format(new Date()));
    }

}
