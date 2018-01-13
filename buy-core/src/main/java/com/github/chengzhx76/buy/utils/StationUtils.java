package com.github.chengzhx76.buy.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

}
