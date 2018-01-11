package com.github.chengzhx76.buy.utils;

public abstract class HttpConstant {

    public static abstract class Method {

        public static final String GET = "GET";

        public static final String POST = "POST";

    }

    public static abstract class StatusCode {

        public static final int CODE_200 = 200;

    }

    public static abstract class Header {

        public static final String REFERER = "Referer";

        public static final String USER_AGENT = "User-Agent";
    }

    public static abstract class UserAgent {

        public static final String CHROME = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";

        public static final String FIREFOX = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0";

        public static final String IE = "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko";
    }

    public static abstract class URL {

        public static final String QUERY = "https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2018-01-19&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT";

        public static final String LOG = "https://kyfw.12306.cn/otn/leftTicket/log?leftTicketDTO.train_date=2018-01-19&leftTicketDTO.from_station=BJP&leftTicketDTO.to_station=CXK&purpose_codes=ADULT";
    }

}
