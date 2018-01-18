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

        public static final String LOG = "https://kyfw.12306.cn/otn/leftTicket/log?leftTicketDTO.train_date={TRAINDATE}&leftTicketDTO.from_station={FROMSTATION}&leftTicketDTO.to_station={TOSTATION}&purpose_codes=ADULT";

        public static final String QUERY = "https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date={TRAINDATE}&leftTicketDTO.from_station={FROMSTATION}&leftTicketDTO.to_station={TOSTATION}&purpose_codes=ADULT";

        public static final String CHECK_USER = "https://kyfw.12306.cn/otn/login/checkUser";

        public static final String CAPTCHA_IMG = "https://kyfw.12306.cn/passport/captcha/captcha-image?login_site=E&module=login&rand=sjrand";

        public static final String CHECK_CAPTCHA = "https://kyfw.12306.cn/passport/captcha/captcha-check";
        //public static final String CHECK_CAPTCHA = "https://kyfw.12306.cn/otn/passcodeNew/checkRandCodeAnsyn";

        public static final String LOGIN = "https://kyfw.12306.cn/passport/web/login";

        public static final String AUTH_UAMTK = "https://kyfw.12306.cn/passport/web/auth/uamtk";
        // https://kyfw.12306.cn/passport/web/auth/uamtk?callback=jQuery191033498948723597777_1516253656156
        // jQuery191033498948723597777_1516253656156({"result_message":"验证通过","result_code":0,"apptk":null,"newapptk":"eQO2aJzqlEJE4QyTddHCir_EqPg32XyYAyKf3Aafc2c0"});

        public static final String UAM_AUTH_CLIENT = "https://kyfw.12306.cn/otn/uamauthclient";
        // {"apptk":"eQO2aJzqlEJE4QyTddHCir_EqPg32XyYAyKf3Aafc2c0","result_code":0,"result_message":"验证通过","username":"程光灿"}

        public static final String SUBMIT_ORDER = "https://kyfw.12306.cn/otn/leftTicket/submitOrderRequest";
        // {"validateMessagesShowId":"_validatorMessage","status":true,"httpstatus":200,"data":"N","messages":[],"validateMessages":{}}

        public static final String TEST = "http://127.0.0.1:9091";
    }

}
