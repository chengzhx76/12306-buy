package com.github.chengzhx76.buy.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class HttpConstant {

    public static abstract class Method {

        public static final String GET = "GET";

        public static final String POST = "POST";

    }

    public static abstract class StatusCode {

        public static final int CODE_200 = 200;

    }

    public static abstract class Header {

        public static final String ACCEPT = "Accept";

        public static final String CACHE_CONTROL = "Cache-Control";

        public static final String IF_MODIFIED_SINCE = "If-Modified-Since";

        public static final String REFERER = "Referer";

        public static final String USER_AGENT = "User-Agent";

        public static final String X_REQUESTED_WITH = "X-Requested-With";

        public static final String ACCEPT_ENCODING = "Accept-Encoding";

        public static final String ACCEPT_LANGUAGE = "Accept-Language";

        public static final String CONNECTION = "Connection";

        public static final String HOST = "Host";

        public static final String CONTENT_TYPE = "Content-Type";

        public static final String ORIGIN = "Origin";

        public static final String UPGRADE_INSECURE_REQUESTS = "Upgrade-Insecure-Requests";



    }

    public static abstract class HeaderValue {

        public static final String APPLICATION_ALL = "*/*";

        public static final String APPLICATION_JSON_TEXT = "application/json, text/javascript, */*; q=0.01";

        public static final String APPLICATION_TEXT_HTML_XML_IMG = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8";

        public static final String XML = "XMLHttpRequest";

        public static final String MAX_AGE_ZERO = "max-age=0";

        public static final String UPGRADE_ONE = "1";

        public static final String ENCODING = "gzip, deflate, br";

        public static final String LANGUAGE = "zh-CN,zh;q=0.9,en;q=0.8";

        public static final String KEEP_ALIVE = "keep-alive";

        public static final String HOST = "kyfw.12306.cn";

        public static final String ORIGIN = "https://kyfw.12306.cn";

        public static final String NO_CACHE = "no-cache";

        public static final String ZERO = "0";

    }

    public static abstract class Referer {

        public static final String INIT = "https://kyfw.12306.cn/otn/leftTicket/init";

        public static final String INIT_DC = "https://kyfw.12306.cn/otn/confirmPassenger/initDc";

    }

    public static abstract class UserAgent {

        public static final String CHROME = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";

        public static final String FIREFOX = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0";

        public static final String IE = "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko";
    }

    public static abstract class URL {

        public static final String LOG = "https://{IP}/otn/leftTicket/log?leftTicketDTO.train_date={TRAINDATE}&leftTicketDTO.from_station={FROMSTATION}&leftTicketDTO.to_station={TOSTATION}&purpose_codes=ADULT";

        public static final String QUERY = "https://{IP}/otn/leftTicket/queryZ?leftTicketDTO.train_date={TRAINDATE}&leftTicketDTO.from_station={FROMSTATION}&leftTicketDTO.to_station={TOSTATION}&purpose_codes=ADULT";

        public static final String CHECK_USER = "https://kyfw.12306.cn/otn/login/checkUser";

        public static final String CAPTCHA_IMG = "https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand";

        public static final String CHECK_CAPTCHA = "https://kyfw.12306.cn/passport/captcha/captcha-check";

        public static final String LOGIN = "https://kyfw.12306.cn/passport/web/login";

        public static final String AUTH_UAMTK = "https://kyfw.12306.cn/passport/web/auth/uamtk";

        public static final String UAM_AUTH_CLIENT = "https://kyfw.12306.cn/otn/uamauthclient";

        public static final String SUBMIT_ORDER = "https://kyfw.12306.cn/otn/leftTicket/submitOrderRequest";

        public static final String INIT_DC = "https://kyfw.12306.cn/otn/confirmPassenger/initDc";

        public static final String PASSENGER = "https://kyfw.12306.cn/otn/confirmPassenger/getPassengerDTOs";

        public static final String CHECK_ORDER = "https://kyfw.12306.cn/otn/confirmPassenger/checkOrderInfo";

        public static final String QUEUE_COUNT = "https://kyfw.12306.cn/otn/confirmPassenger/getQueueCount";

        public static final String CONFIRM_SINGLE_FOR_QUEUE = "https://kyfw.12306.cn/otn/confirmPassenger/confirmSingleForQueue";

        public static final String QUERY_ORDER_WAIT_TIME = "https://kyfw.12306.cn/otn/confirmPassenger/queryOrderWaitTime?random={RANDOM}&tourFlag={TOURFLAG}&_json_att=&REPEAT_SUBMIT_TOKEN={REPEAT_SUBMIT_TOKEN}";

        public static final String RESULT_ORDER_FOR_DC_QUEUE = "https://kyfw.12306.cn/otn/confirmPassenger/resultOrderForDcQueue";

        public static final String TEST = "http://127.0.0.1:9091";
    }

    public static abstract class Headers {
        public static Map<String, String> LOG = new HashMap<>();
        public static Map<String, String> QUERY = new HashMap<>();
        public static Map<String, String> CHECK_USER = new HashMap<>();
        public static Map<String, String> CAPTCHA_IMG = new HashMap<>();
        public static Map<String, String> CHECK_CAPTCHA = new HashMap<>();
        public static Map<String, String> LOGIN = new HashMap<>();
        public static Map<String, String> AUTH_UAMTK = new HashMap<>();
        public static Map<String, String> UAM_AUTH_CLIENT = new HashMap<>();
        public static Map<String, String> SUBMIT_ORDER = new HashMap<>();
        public static Map<String, String> INIT_DC = new HashMap<>();
        public static Map<String, String> PASSENGER = new HashMap<>();
        public static Map<String, String> CHECK_ORDER = new HashMap<>();
        public static Map<String, String> QUEUE_COUNT = new HashMap<>();
        public static Map<String, String> CONFIRM_SINGLE_FOR_QUEUE = new HashMap<>();
        public static Map<String, String> QUERY_ORDER_WAIT_TIME = new HashMap<>();
        public static Map<String, String> RESULT_ORDER_FOR_DC_QUEUE = new HashMap<>();

        static {
            LOG.put(Header.ACCEPT, HeaderValue.APPLICATION_ALL);
            LOG.put(Header.CACHE_CONTROL, HeaderValue.NO_CACHE);
            LOG.put(Header.IF_MODIFIED_SINCE, HeaderValue.ZERO);
            LOG.put(Header.REFERER, Referer.INIT);
            LOG.put(Header.X_REQUESTED_WITH, HeaderValue.XML);

            QUERY.put(Header.ACCEPT, HeaderValue.APPLICATION_ALL);
            QUERY.put(Header.CACHE_CONTROL, HeaderValue.NO_CACHE);
            QUERY.put(Header.IF_MODIFIED_SINCE, HeaderValue.ZERO);
            QUERY.put(Header.REFERER, Referer.INIT);
            QUERY.put(Header.X_REQUESTED_WITH, HeaderValue.XML);

            CHECK_USER.put(Header.REFERER, Referer.INIT);

            CAPTCHA_IMG.put(Header.ACCEPT, HeaderValue.APPLICATION_TEXT_HTML_XML_IMG);
            CAPTCHA_IMG.put(Header.REFERER, Referer.INIT);

            CHECK_CAPTCHA.put(Header.ACCEPT, HeaderValue.APPLICATION_JSON_TEXT);
            CHECK_CAPTCHA.put(Header.REFERER, Referer.INIT);
            CHECK_CAPTCHA.put(Header.X_REQUESTED_WITH, HeaderValue.XML);

            LOGIN.put(Header.ACCEPT, HeaderValue.APPLICATION_JSON_TEXT);
            LOGIN.put(Header.REFERER, Referer.INIT);
            LOGIN.put(Header.X_REQUESTED_WITH, HeaderValue.XML);

            AUTH_UAMTK.put(Header.ACCEPT, HeaderValue.APPLICATION_JSON_TEXT);
            AUTH_UAMTK.put(Header.REFERER, Referer.INIT);
            AUTH_UAMTK.put(Header.X_REQUESTED_WITH, HeaderValue.XML);

            UAM_AUTH_CLIENT.put(Header.ACCEPT, HeaderValue.APPLICATION_ALL);
            UAM_AUTH_CLIENT.put(Header.REFERER, Referer.INIT);
            UAM_AUTH_CLIENT.put(Header.X_REQUESTED_WITH, HeaderValue.XML);

            SUBMIT_ORDER.put(Header.ACCEPT, HeaderValue.APPLICATION_ALL);
            SUBMIT_ORDER.put(Header.REFERER, Referer.INIT);
            SUBMIT_ORDER.put(Header.X_REQUESTED_WITH, HeaderValue.XML);

            INIT_DC.put(Header.ACCEPT, HeaderValue.APPLICATION_TEXT_HTML_XML_IMG);
            INIT_DC.put(Header.CACHE_CONTROL, HeaderValue.MAX_AGE_ZERO);
            INIT_DC.put(Header.UPGRADE_INSECURE_REQUESTS, HeaderValue.UPGRADE_ONE);
            INIT_DC.put(Header.REFERER, Referer.INIT);

            PASSENGER.put(Header.ACCEPT, HeaderValue.APPLICATION_ALL);
            PASSENGER.put(Header.X_REQUESTED_WITH, HeaderValue.XML);
            PASSENGER.put(Header.REFERER, Referer.INIT_DC);

            CHECK_ORDER.put(Header.ACCEPT, HeaderValue.APPLICATION_JSON_TEXT);
            CHECK_ORDER.put(Header.X_REQUESTED_WITH, HeaderValue.XML);
            CHECK_ORDER.put(Header.REFERER, Referer.INIT_DC);

            QUEUE_COUNT.put(Header.ACCEPT, HeaderValue.APPLICATION_JSON_TEXT);
            QUEUE_COUNT.put(Header.X_REQUESTED_WITH, HeaderValue.XML);
            QUEUE_COUNT.put(Header.REFERER, Referer.INIT_DC);

            CONFIRM_SINGLE_FOR_QUEUE.put(Header.ACCEPT, HeaderValue.APPLICATION_JSON_TEXT);
            CONFIRM_SINGLE_FOR_QUEUE.put(Header.X_REQUESTED_WITH, HeaderValue.XML);
            CONFIRM_SINGLE_FOR_QUEUE.put(Header.REFERER, Referer.INIT_DC);

            QUERY_ORDER_WAIT_TIME.put(Header.ACCEPT, HeaderValue.APPLICATION_JSON_TEXT);
            QUERY_ORDER_WAIT_TIME.put(Header.X_REQUESTED_WITH, HeaderValue.XML);
            QUERY_ORDER_WAIT_TIME.put(Header.REFERER, Referer.INIT_DC);

            QUERY_ORDER_WAIT_TIME.put(Header.ACCEPT, HeaderValue.APPLICATION_JSON_TEXT);
            QUERY_ORDER_WAIT_TIME.put(Header.X_REQUESTED_WITH, HeaderValue.XML);
            RESULT_ORDER_FOR_DC_QUEUE.put(Header.REFERER, Referer.INIT_DC);

        }

    }

}
