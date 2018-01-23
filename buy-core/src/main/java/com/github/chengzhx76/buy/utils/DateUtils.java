package com.github.chengzhx76.buy.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/23
 */
public class DateUtils {
    public static String toEnDate(String cnDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(cnDate);
            dateFormat = new SimpleDateFormat("EEE MMM d 00:00:00 'GMT+0800 (中国标准时间)'", Locale.ENGLISH);
            return dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
