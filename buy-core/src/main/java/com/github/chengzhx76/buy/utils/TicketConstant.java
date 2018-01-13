package com.github.chengzhx76.buy.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/13
 */
public class TicketConstant {
    private final static Map<String, Integer> SEAT = new HashMap<>();

    static {
        SEAT.put("商务座", 32);
        SEAT.put("一等座", 31);
        SEAT.put("二等座", 30);
        SEAT.put("特等座", 25);
        SEAT.put("软卧", 23);
        SEAT.put("硬卧", 28);
        SEAT.put("硬座", 29);
        SEAT.put("无座", 26);
    }

    public static Integer getSeat(String seatCn) {
        return SEAT.get(seatCn);
    }
}
