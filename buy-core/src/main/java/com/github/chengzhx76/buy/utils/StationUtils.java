package com.github.chengzhx76.buy.utils;

import com.github.chengzhx76.buy.model.Cookie;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/13
 */
public class StationUtils {
    // @bjb|北京北|VAP|beijingbei|bjb|0
    public static String getStationCode(String stationName) {
        String allStation = null;
        try {
            allStation = IOUtils.toString(StationUtils.class.getClassLoader().getResourceAsStream("station_name.txt"));
        } catch (IOException e) {
            throw new RuntimeException("未找到station_name.txt文件");
        }
        String stations[] = StringUtils.split(allStation, "@");
        Map<String, String> map = new HashMap<>();
        for (String station : stations) {
            String onStation[] = StringUtils.split(station,"|");
            map.put(onStation[1], onStation[2]);
        }
        return map.get(stationName);
    }


    public static String getCaptchaXY(String positions) {
        StringBuilder offsetsXY = new StringBuilder();
        String posArr[] = positions.split("\\ ");
        for (String pos : posArr) {
            int offsetsX = 0;
            int offsetsY = 0;
            switch (pos) {
                case "1":
                    offsetsX = 37;
                    offsetsY = 45;
                    break;
                case "2":
                    offsetsX = 110;
                    offsetsY = 45;
                    break;
                case "3":
                    offsetsX = 186;
                    offsetsY = 45;
                    break;
                case "4":
                    offsetsX = 254;
                    offsetsY = 45;
                    break;
                case "5":
                    offsetsX = 37;
                    offsetsY = 120;
                    break;
                case "6":
                    offsetsX = 110;
                    offsetsY = 113;
                    break;
                case "7":
                    offsetsX = 186;
                    offsetsY = 118;
                    break;
                case "8":
                    offsetsX = 254;
                    offsetsY = 116;
                    break;
                default:
                    break;
            }
            offsetsXY.append(offsetsX)
                    .append(",")
                    .append(offsetsY)
                    .append(",");
        }
        return offsetsXY.deleteCharAt(offsetsXY.length()-1).toString();
    }

    public static String getSeatType(String seatCn) {
        String seatType = "";
        if ("一等座".equals(seatCn)) {
            seatType = "M";
        } else if ("特等座".equals(seatCn)) {
            seatType = "P";
        } else if ("二等座".equals(seatCn)) {
            seatType = "O";
        } else if ("商务座".equals(seatCn)) {
            seatType = "9";
        } else if ("硬座".equals(seatCn) || "无座".equals(seatCn)) {
            seatType = "1";
        } else if ("软卧".equals(seatCn)) {
            seatType = "4";
        } else if ("硬卧".equals(seatCn)) {
            seatType = "3";
        }
        return seatType;
    }

    public static void saveLoginCookie(Set<Cookie> cookies) {
        Map<String, String> loginCookies = new HashMap<>();
        for (Cookie cookie : cookies) {
            if ("JSESSIONID".equals(cookie.getName()) ||
                    "BIGipServerotn".equals(cookie.getName()) ||
                    "route".equals(cookie.getName())) {
                loginCookies.put(cookie.getName(), cookie.getValue());
            }
        }
        ConfigUtils.writeProperties("cookies.properties", loginCookies);
    }

    public static String getCdnIP() {
        String ip = "kyfw.12306.cn";
        try {
            List<String> ips = IOUtils.readLines(StationUtils.class.getClassLoader().getResourceAsStream("cdn.txt"));
            int index = new Random().nextInt(ips.size());
            ip = ips.get(index);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }
}
