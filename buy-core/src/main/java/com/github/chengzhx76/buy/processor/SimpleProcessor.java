package com.github.chengzhx76.buy.processor;

import com.alibaba.fastjson.JSON;
import com.github.chengzhx76.buy.Buyer;
import com.github.chengzhx76.buy.model.*;
import com.github.chengzhx76.buy.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/12
 */
public class SimpleProcessor implements Processor {

    private final static Logger LOG = LoggerFactory.getLogger(SimpleProcessor.class);

    List<Passenger> passengers = null;


    @Override
    public void preHandle(Buyer buyer, Request request) {
        OperationType operation = request.getOperation();
        request.setMethod(operation.getMethod());
        if (OperationType.LOG.equals(operation) ||
                OperationType.QUERY.equals(operation)) {
            request.setUrl(operation.getUrl()
                    .replace("{TRAINDATE}", buyer.getStationDate())
                    .replace("{FROMSTATION}", buyer.getFromStationCode())
                    .replace("{TOSTATION}", buyer.getToStationCode()));

            request.addHeader("Accept", "*/*");
            request.addHeader("Cache-Control", "no-cache");
            request.addHeader("If-Modified-Since", "0");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            request.addHeader("X-Requested-With", "XMLHttpRequest");
            setHeader(request);

            if (OperationType.QUERY.equals(operation)) {
                try {
                    request.addCookie("_jc_save_fromStation", URLEncoder.encode(buyer.getFromStation()+","+buyer.getFromStationCode(), "UTF-8"));
                    request.addCookie("_jc_save_fromDate", buyer.getStationDate());
                    request.addCookie("_jc_save_toStation", URLEncoder.encode(buyer.getToStation()+","+buyer.getToStationCode(), "UTF-8"));
                    request.addCookie("_jc_save_toDate", buyer.getStationDate());
                    request.addCookie("_jc_save_wfdc_flag", "dc");
                } catch (UnsupportedEncodingException e) {
                    LOG.error("编码失败", e);
                    throw new RuntimeException("编码失败");
                }
            }
        }else if (OperationType.CHECK_USER.equals(operation)) {
            request.setUrl(operation.getUrl());

            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            setHeader(request);

            Map<String, Object> params = new HashMap<>();
            params.put("json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        }else if (OperationType.CAPTCHA_IMG.equals(operation)) {
            request.setUrl(operation.getUrl()+"&"+new Random().nextDouble());

            request.addHeader("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            setHeader(request);
        }else if (OperationType.CHECK_CAPTCHA.equals(operation)) {
            request.setUrl(operation.getUrl());

            request.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            request.addHeader("X-Requested-With", "XMLHttpRequest");
            setHeader(request);

            System.out.print("输入图片位置：");
            Scanner scan = new Scanner(System.in);
            String read = scan.nextLine();

            Map<String, Object> params = new HashMap<>();
            params.put("answer", getCaptchaXY(read));
            params.put("login_site", "E");
            params.put("rand", "sjrand");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        }else if (OperationType.LOGIN.equals(operation)){
            request.setUrl(operation.getUrl());

            request.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            request.addHeader("X-Requested-With", "XMLHttpRequest");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            setHeader(request);

            Map<String, Object> params = new HashMap<>();
            params.put("username", buyer.getUsername());
            params.put("password", buyer.getPassword());
            params.put("appid", "otn");
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.AUTH_UAMTK.equals(operation)) {
            request.setUrl(operation.getUrl());

            request.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            request.addHeader("X-Requested-With", "XMLHttpRequest");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            setHeader(request);

            Map<String, Object> params = new HashMap<>();
            params.put("appid", "otn");
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.UAM_AUTH_CLIENT.equals(operation)) {
            request.setUrl(operation.getUrl());

            request.addHeader("Accept", "*/*");
            request.addHeader("X-Requested-With", "XMLHttpRequest");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            setHeader(request);

            Map<String, Object> params = new HashMap<>();
            params.put("tk", request.getExtra("tk"));
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.SUBMIT_ORDER.equals(operation)) {
            request.setUrl(operation.getUrl());

            request.addHeader("Accept", "*/*");
            request.addHeader("X-Requested-With", "XMLHttpRequest");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            setHeader(request);

            Map<String, Object> params = new HashMap<>();
            params.put("secretStr", request.getExtra("secretStr"));
            params.put("train_date", buyer.getStationDate());
            params.put("back_train_date", buyer.getStationDate());
            params.put("tour_flag", "dc");
            params.put("purpose_codes", "ADULT");
            params.put("query_from_station_name", buyer.getFromStation());
            params.put("query_to_station_name", buyer.getToStation());
            params.put("undefined:", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.INIT_DC.equals(operation)) {
            request.setUrl(operation.getUrl());
            request.addCookie("tk", request.getExtra("tk"));

            request.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            request.addHeader("Cache-Control", "max-age=0");
            request.addHeader("Upgrade-Insecure-Requests", "1");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            setHeader(request);

            Map<String, Object> params = new HashMap<>();
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.PASSENGER.equals(operation)){
            request.setUrl(operation.getUrl());

            request.addHeader("Accept", "*/*");
            request.addHeader("X-Requested-With", "XMLHttpRequest");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/confirmPassenger/initDc");
            setHeader(request);

            Map<String, Object> params = new HashMap<>();
            params.put("REPEAT_SUBMIT_TOKEN", request.getExtra("token"));
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.CHECK_ORDER.equals(operation)){
            request.setUrl(operation.getUrl());

            request.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            request.addHeader("X-Requested-With", "XMLHttpRequest");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/confirmPassenger/initDc");
            setHeader(request);

            Map<String, Object> params = new HashMap<>();
            params.put("cancel_flag", "2");
            params.put("bed_level_order_num", "000000000000000000000000000000");
            params.put("passengerTicketStr", passengerTicketStr(buyer));
            params.put("oldPassengerStr", oldPassengerStr(buyer));
            params.put("tour_flag", "dc");
            params.put("randCode", "");
            params.put("whatsSelect", "1");
            params.put("_json_att", "");
            params.put("_json_att", "");
            params.put("REPEAT_SUBMIT_TOKEN", request.getExtra("token"));
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else {
            request.setUrl(operation.getUrl());
        }
    }

    @Override
    public void afterCompletion(Buyer buyer, Request request, Response response) {
        OperationType operation = response.getOperation();
        if (OperationType.LOG.equals(operation)) {

            ValidateMsg<String> validateMsg = parseObject(response, ValidateMsg.class);
            if (validateMsg.getStatus() || validateMsg.getData().equals("1")) {
                request.setSleepTime(0L);
                request.setOperation(OperationType.QUERY);
            } else {
                throw new RuntimeException("日志接口返回失败");
            }

        } else if (OperationType.QUERY.equals(operation)) {
            ValidateMsg<Ticket> query = null;
            try {
                query = parseObject(response, ValidateMsg.class);
            }catch (Exception e) {
                request.setOperation(OperationType.QUERY);
                LOG.warn("解析JSON出错{}", e.getMessage());
                return;
            }
            if (query == null ||
                    query.getData() == null ||
                    query.getData().getResult().isEmpty()) {
                LOG.warn("没有查到数据重新查询");
                return;
            }

            List<String> allTicket = query.getData().getResult();
            for (String tickets : allTicket) {
                String ticket[] = tickets.split("\\|");
                if ("Y".equals(ticket[11]) && "预订".equals(ticket[1])) {
                    for (String seatCn : buyer.getSetType()) {
                        int seatCode = TicketConstant.getSeat(seatCn);
                        String seat = ticket[seatCode];
                        if (!"".equals(seat) &&
                                !"*".equals(seat) &&
                                !"无".equals(seat) &&
                                buyer.getStationTrains().contains(ticket[3])) {

                            String secretStr = ticket[0];
                            String trainNo = ticket[3];
                            LOG.info("车次：" + trainNo + " 始发车站：" + buyer.getFromStation() + " 终点车站：" +
                                    buyer.getToStation() + " 席别：" + seatCn + "-" + seat + " 安全码：" + secretStr);
                            if (!"无".equals(seat)) {
                                request.putExtra("secretStr", EncodeUtils.decodeURL(secretStr));
                                request.setOperation(OperationType.CHECK_USER);
                                break;
                            }
                        }
                    }
                }
            }
//            request.setOperation(OperationType.CHECK_USER);
        } else if (OperationType.CHECK_USER.equals(operation)) {
            ValidateMsg<Flag> validateMsg = parseObject(response, ValidateMsg.class);
            if (validateMsg.getData().getFlag()) {
                System.out.println("----------开始尝试下单-------------");
            } else {
                request.setOperation(OperationType.CAPTCHA_IMG);
            }
        } else if (OperationType.CAPTCHA_IMG.equals(operation)) {
            try {
                FileUtils.writeToLocal(".\\s1.png", response.getContent());
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
            System.out.println("-----验证码已保存-----");
            request.setOperation(OperationType.CHECK_CAPTCHA);
        } else if (OperationType.CHECK_CAPTCHA.equals(operation)) {
            System.out.println("-----检查验证码-----");

            //- 校验验证码
            //  - 没携带cookie：         {"result_message":"验证码校验失败,信息为空","result_code":"8"}
            //  - 携带cookie但点错了：    {"result_message":"验证码校验失败","result_code":"5"}
            //  - 携带cookie并且点击正确： {"result_message":"验证码校验成功","result_code":"4"}
            //  - 停留时间过长：           {"result_message":"验证码已经过期","result_code":"7"}

            ValidateMsg<String> validateMsg = parseObject(response, ValidateMsg.class);
            if ("4".equals(validateMsg.getResult_code())) { // 验证码校验成功
                request.setOperation(OperationType.LOGIN);
            } else if ("5".equals(validateMsg.getResult_code())) {
                // 移除_passport_ctCookies
                request.setDisableCookieManagement(true);
                LOG.info("验证码校验失败,重新认证");
                request.setOperation(OperationType.CAPTCHA_IMG);
            } else if ("7".equals(validateMsg.getResult_code())) {
                // 移除_passport_ctCookies
                LOG.info("验证码已经过期,重新获取");
                request.setOperation(OperationType.CAPTCHA_IMG);
            } else if ("8".equals(validateMsg.getResult_code())) {
                throw new RuntimeException(validateMsg.getResult_message());
            } else { // 正常不走这里，避免死循环
                request.setOperation(OperationType.END);
            }
        } else if (OperationType.LOGIN.equals(operation)) {
            System.out.println("-----用户登陆-----");

            //- 校验用户名密码：
            //  - 密码输入错误：   {"result_message":"密码输入错误。如果输错次数超过4次，用户将被锁定。","result_code":1}
            //  - 用户不存在：     {"result_message":"登录名不存在。","result_code":1}
            //  - 密码输入正确：   {"result_message":"登录成功","result_code":0,"uamtk":"0HPOXSv6Wdr4P9Ru0TQsYtAWyadxAtVWPHKB0Qplc2c0"}
            ValidateMsg<String> validateMsg = null;
            try {
                validateMsg = parseObject(response, ValidateMsg.class);
            } catch (Exception e) {
                LOG.warn("登录出错 - 重新登录", e);
                request.setOperation(OperationType.LOGIN);
                return;
            }
            if ("0".equals(validateMsg.getResult_code())) {
                System.out.println(validateMsg.getResult_message());
                System.out.println("登录成功---->开始认证-1");
                request.setOperation(OperationType.AUTH_UAMTK);
            } else if ("1".equals(validateMsg.getResult_code())) {
                System.out.println(validateMsg.getResult_message());
                System.out.println("登录失败---->重新登录");
                request.setOperation(OperationType.CAPTCHA_IMG);
            } else { // 正常不走这里，避免死循环
                request.setOperation(OperationType.END);
            }
        } else if (OperationType.AUTH_UAMTK.equals(operation)) {
            System.out.println("认证-1" + "结果--> "+ response.getRawText());
            ValidateMsg<String> validateMsg = null;
            try {
                validateMsg = parseObject(response, ValidateMsg.class);
            } catch (Exception e) {
                LOG.warn("认证-1出错", e);
                throw new RuntimeException("认证-1出错");
            }
            request.putExtra("tk", validateMsg.getNewapptk());
            request.setOperation(OperationType.UAM_AUTH_CLIENT);
        } else if (OperationType.UAM_AUTH_CLIENT.equals(operation)) {
            System.out.println("认证-2" + "结果--> "+ response.getRawText());
            ValidateMsg<String> validateMsg = null;
            try {
                validateMsg = parseObject(response, ValidateMsg.class);
            } catch (Exception e) {
                LOG.warn("认证-2出错", e);
                throw new RuntimeException("认证-2出错");
            }
            if ("0".equals(validateMsg.getResult_code())) {
                LOG.info("认证-2成功-{}", validateMsg.getUsername());
                request.setOperation(OperationType.SUBMIT_ORDER);
            } else {
                throw new RuntimeException(validateMsg.getResult_message());
            }
        } else if (OperationType.SUBMIT_ORDER.equals(operation)) {
            System.out.println("提交订单结果--> "+ response.getRawText());

            ValidateMsg<String> submitOrder = null;
            try {
                submitOrder = parseObject(response, ValidateMsg.class);
            } catch (Exception e) {
                LOG.warn("提交订单出错", e);
                throw new RuntimeException("提交订单出错");
            }
            if (submitOrder.getStatus()) {
                if ("N".equals(submitOrder.getData())) {
                    LOG.info("开始订单确认");
                } else if ("Y".equals(submitOrder.getData())){
                    LOG.info("您选择的列车距开车时间很近了，请确保有足够的时间抵达车站。");
                }
                request.setOperation(OperationType.INIT_DC);
            }else {
                LOG.info("无法确认订单");
                throw new RuntimeException("无法确认订单");
            }

        } else if (OperationType.INIT_DC.equals(operation)) {
            System.out.println("获取token结果--> "+ response.getRawText());
            String regex = "var globalRepeatSubmitToken = '(\\S+)'";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(response.getRawText());
            String token = "";
            if (matcher.find()) {
                token = matcher.group(1);
            }else {
                LOG.warn("未查找到TOKEN");
            }
            if (StringUtils.isNotBlank(token)) {
                request.putExtra("token", token);
                request.setOperation(OperationType.PASSENGER);
            } else {
                request.setOperation(OperationType.INIT_DC);
            }
        } else if (OperationType.PASSENGER.equals(operation)) {
            ValidateMsg<Passengers> passengers = parseObject(response, ValidateMsg.class);
            this.passengers = passengers.getData().getNormal_passengers();
            request.setOperation(OperationType.CHECK_ORDER);
        } else if (OperationType.CHECK_ORDER.equals(operation)) {
            ValidateMsg<CheckOrder> checkOrder = parseObject(response, ValidateMsg.class);
            if (!checkOrder.getStatus()) {
                throw new RuntimeException("失败");
            }
        } else if (OperationType.END.equals(operation)) {

        }  else {
            System.out.println("SimpleProcessor--> ---------");
        }
    }

    private <T> T parseObject(Response response, Class<T> clazz) {
        try {
            return JSON.parseObject(response.getRawText(), clazz);
        } catch (Exception e) {
            LOG.error("格式化发生错误 Response {}", response.getRawText(), e);
            throw new RuntimeException(e.getMessage());
        }
    }

    private String getCaptchaXY(String positions) {
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

    private String getSeatType(String seatCn) {
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

    // "座位编号,0,票类型,乘客名,证件类型,证件号,手机号码,保存常用联系人(Y或N)";
    private String passengerTicketStr(Buyer buyer) {
        StringBuilder passengerTicket = new StringBuilder();
        for (String ticketPeople : buyer.getTickePeoples()) {
            String seatType = getSeatType(buyer.getSetType().get(0));
            Passenger passenger = getPassenger(ticketPeople);
            if (passenger == null) {
                throw new RuntimeException("没查到该乘客");
            }
            if (passengerTicket.length() > 0) {
                passengerTicket.append("_");
            }
            passengerTicket
                    .append(seatType).append(",")
                    .append("0").append(",")
                    .append(passenger.getPassenger_type()).append(",")
                    .append(passenger.getPassenger_name()).append(",")
                    .append(passenger.getPassenger_id_type_code()).append(",")
                    .append(passenger.getPassenger_id_no()).append(",")
                    .append(passenger.getMobile_no()).append(",")
                    .append("N");
        }
        return passengerTicket.toString();
    }

    // "姓名，证件类别，证件号码，用户类型";
    private String oldPassengerStr(Buyer buyer) {
        StringBuilder oldPassenger = new StringBuilder();
        for (String ticketPeople : buyer.getTickePeoples()) {
            Passenger passenger = getPassenger(ticketPeople);
            if (passenger == null) {
                throw new RuntimeException("没查到该乘客");
            }
            if (oldPassenger.length() > 0) {
                oldPassenger.append("_");
            }
            oldPassenger
                    .append(passenger.getPassenger_name()).append(",")
                    .append(passenger.getPassenger_id_type_code()).append(",")
                    .append(passenger.getPassenger_id_no()).append(",")
                    .append(passenger.getPassenger_type());
        }
        return oldPassenger.toString();
    }




    private Passenger getPassenger(String passengerName) {
        for (Passenger passenger : passengers) {
            if (passengerName.equals(passenger.getPassenger_name())) {
                return passenger;
            }
        }
        return null;
    }

    private void setHeader(Request request) {
        request.addHeader("Accept-Encoding", "gzip, deflate, br");
        request.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
        request.addHeader("Connection", "keep-alive");
        request.addHeader("Host", "kyfw.12306.cn");
        request.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        request.addHeader("Origin", "https://kyfw.12306.cn");
        request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
    }

}
