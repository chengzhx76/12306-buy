package com.github.chengzhx76.buy.processor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

    @Override
    public void preHandle(Buyer buyer, Request request) {
        OperationType operation = request.getOperation();
        request.setMethod(operation.getMethod());
        request.setHeaders(operation.getHeader());

        if (OperationType.CHECK_USER.equals(operation)) {
            request.setUrl(operation.getUrl());
            Map<String, Object> params = new HashMap<>();
            params.put("json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.CAPTCHA_IMG.equals(operation)) {
            request.setUrl(operation.getUrl()+"&"+new Random().nextDouble());
            request.setDisableCookieManagement(true);
        } else if (OperationType.CHECK_CAPTCHA.equals(operation)) {
            request.setUrl(operation.getUrl());

            System.out.print("输入图片位置：");
            Scanner scan = new Scanner(System.in);
            String read = scan.nextLine();

            Map<String, Object> params = new HashMap<>();
            params.put("answer", StationUtils.getCaptchaXY(read));
            params.put("login_site", "E");
            params.put("rand", "sjrand");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.LOGIN.equals(operation)){
            request.setUrl(operation.getUrl());

            /*request.addCookie(
                    "RAIL_DEVICEID",
                    "OYkMQlD-v2EBlkvCaOpycpRgTz64KyojWaie7e2GQNoS6_YgIN_cxk46Gdk8eL4Aey4CZ_9l8ZuKMn4gtpS173lNXtS9DgY4sZXi1NKeX_Dmm-gJv8QbWBWUVmOQeUmE1ox9vEg69E0AZ6ccCdZBFvJZgXs8SFi2",
                    ".12306.cn");*/

            Map<String, Object> params = new HashMap<>();
            params.put("username", buyer.getUsername());
            params.put("password", buyer.getPassword());
            params.put("appid", "otn");
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.AUTH_UAMTK.equals(operation)) {
            request.setUrl(operation.getUrl());

            Map<String, Object> params = new HashMap<>();
            params.put("appid", "otn");
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.UAM_AUTH_CLIENT.equals(operation)) {
            request.setUrl(operation.getUrl());

            Map<String, Object> params = new HashMap<>();
            params.put("tk", request.getExtra("tk"));
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.LOG.equals(operation) ||
                OperationType.QUERY.equals(operation)) {
            String ip = StationUtils.getCdnIP();
            request.setUrl(operation.getUrl()
                    .replace("https", "http")
                    .replace("{IP}", ip)
                    .replace("{TRAINDATE}", buyer.getStationDate())
                    .replace("{FROMSTATION}", buyer.getFromStationCode())
                    .replace("{TOSTATION}", buyer.getToStationCode()));

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
        } else if (OperationType.SUBMIT_ORDER.equals(operation)) {
            request.setUrl(operation.getUrl());

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
            Map<String, Object> params = new HashMap<>();
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.PASSENGER.equals(operation)){
            request.setUrl(operation.getUrl());

            Map<String, Object> params = new HashMap<>();
            params.put("REPEAT_SUBMIT_TOKEN", request.getExtra("token"));
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.CHECK_ORDER.equals(operation)){
            request.setUrl(operation.getUrl());

            String passengers = request.getExtra("passengers");

            Map<String, Object> params = new HashMap<>();
            params.put("cancel_flag", "2");
            params.put("bed_level_order_num", "000000000000000000000000000000");
            params.put("passengerTicketStr", passengerTicketStr(buyer, passengers));
            params.put("oldPassengerStr", oldPassengerStr(buyer, passengers));
            params.put("tour_flag", "dc");
            params.put("randCode", "");
            params.put("whatsSelect", "1");
            params.put("_json_att", "");
            params.put("_json_att", "");
            params.put("REPEAT_SUBMIT_TOKEN", request.getExtra("token"));
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.QUEUE_COUNT.equals(operation)){
            request.setUrl(operation.getUrl());

            Map<String, Object> params = new HashMap<>();
            String token = request.getExtra("token");
            String ticketInfoForPassenger = request.getExtra("ticketInfoForPassenger");

            JSONObject ticketInfoForPassengerJsonObject = JSON.parseObject(ticketInfoForPassenger);

            JSONObject queryLeftTicketRequestJsonObject = ticketInfoForPassengerJsonObject.getJSONObject("queryLeftTicketRequestDTO");

            String trainNo = queryLeftTicketRequestJsonObject.getString("train_no");
            String stationTrainCode = queryLeftTicketRequestJsonObject.getString("station_train_code");
            String seatType = StationUtils.getSeatType(buyer.getSetType().get(0));
            String fromStationTelecode = queryLeftTicketRequestJsonObject.getString("from_station");
            String toStationTelecode = queryLeftTicketRequestJsonObject.getString("to_station");
            String leftTicketStr = ticketInfoForPassengerJsonObject.getString("leftTicketStr");
            String purposeCodes = ticketInfoForPassengerJsonObject.getString("purpose_codes");
            String trainLocation = ticketInfoForPassengerJsonObject.getString("train_location");

            params.put("train_date", DateUtils.toEnDate(buyer.getStationDate()));
            params.put("train_no", trainNo);
            params.put("stationTrainCode", stationTrainCode);
            params.put("seatType", seatType);
            params.put("fromStationTelecode", fromStationTelecode);
            params.put("toStationTelecode", toStationTelecode);
            params.put("leftTicket", leftTicketStr);
            params.put("purpose_codes", purposeCodes);
            params.put("train_location", trainLocation);
            params.put("_json_att", "");
            params.put("REPEAT_SUBMIT_TOKEN", token);
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.CONFIRM_SINGLE_FOR_QUEUE.equals(operation)) {
            request.setUrl(operation.getUrl());

            Map<String, Object> params = new HashMap<>();

            String token = request.getExtra("token");
            String ticketInfoForPassenger = request.getExtra("ticketInfoForPassenger");
            JSONObject ticketInfoForPassengerJsonObject = JSON.parseObject(ticketInfoForPassenger);

            String passengers = request.getExtra("passengers");

            params.put("passengerTicketStr", passengerTicketStr(buyer, passengers));
            params.put("oldPassengerStr", oldPassengerStr(buyer, passengers));
            params.put("purpose_codes", ticketInfoForPassengerJsonObject.getString("purpose_codes"));
            params.put("key_check_isChange", ticketInfoForPassengerJsonObject.getString("key_check_isChange"));
            params.put("leftTicketStr", ticketInfoForPassengerJsonObject.getString("leftTicketStr"));
            params.put("train_location", ticketInfoForPassengerJsonObject.getString("train_location"));
            params.put("seatDetailType", "000");
            params.put("roomType", "00");
            params.put("dwAll", "N");
            params.put("whatsSelect", "1");
            params.put("_json_at", "");
            params.put("REPEAT_SUBMIT_TOKEN", token);
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else if (OperationType.QUERY_ORDER_WAIT_TIME.equals(operation)) {
            request.setUrl(operation.getUrl()
                    .replace("{RANDOM}", System.currentTimeMillis()+"")
                    .replace("{TOURFLAG}", "dc")
                    .replace("{REPEAT_SUBMIT_TOKEN}", request.getExtra("token")));
        } else if (OperationType.RESULT_ORDER_FOR_DC_QUEUE.equals(operation)) {
            Map<String, Object> params = new HashMap<>();

            String token = request.getExtra("token");
            String orderNo = request.getExtra("orderNo");

            params.put("orderSequence_no", orderNo);
            params.put("_json_att", "");
            params.put("REPEAT_SUBMIT_TOKEN", token);
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else {
            request.setUrl(operation.getUrl());
        }
    }

    @Override
    public void afterCompletion(Buyer buyer, Request request, Response response) {
        OperationType operation = response.getOperation();
        if (OperationType.CHECK_USER.equals(operation)) {
            JSONObject checkUser = parse(response.getRawText());
            if (checkUser.getBoolean("flag")) {
                if (StringUtils.isBlank(request.getExtra("secretStr"))) {
                    System.out.println("----------开始查询-------------");
                    request.setOperation(OperationType.QUERY);
                } else {
                    request.setOperation(OperationType.SUBMIT_ORDER);
                }

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
            JSONObject checkCaptcha = JSON.parseObject(response.getRawText());
            Integer resultCode = checkCaptcha.getInteger("result_code");
            String resultMsg = checkCaptcha.getString("result_message");
            if (4 == resultCode) {
                request.setOperation(OperationType.LOGIN);
            } else if (5 == resultCode) {
                request.setDisableCookieManagement(true);
                LOG.info("验证码校验失败,重新认证");
                request.setOperation(OperationType.CAPTCHA_IMG);
            } else if (7 == resultCode) {
                LOG.info("验证码已经过期,重新获取");
                request.setOperation(OperationType.CAPTCHA_IMG);
            } else if (8 == resultCode) {
                throw new RuntimeException(resultMsg);
            } else { // 正常不走这里，避免死循环
                request.setOperation(OperationType.END);
            }
        } else if (OperationType.LOGIN.equals(operation)) {
            System.out.println("-----用户登陆-----");
            JSONObject login = JSON.parseObject(response.getRawText());
            Integer resultCode = login.getInteger("result_code");
            String uamtk = login.getString("uamtk");
            if (0 == resultCode) {
                System.out.println("登录成功---->开始认证-1");

                // 登录成功把Cookie写文件
                StationUtils.saveLoginCookie(request.getCookies());

                request.putExtra("uamtk", uamtk);
                request.setOperation(OperationType.AUTH_UAMTK);
            } else if (1 == resultCode) {
                System.out.println("登录失败---->重新登录");
                request.setOperation(OperationType.CAPTCHA_IMG);
            } else { // 正常不走这里，避免死循环
                request.setOperation(OperationType.END);
            }
        } else if (OperationType.AUTH_UAMTK.equals(operation)) {
            System.out.println("认证-1" + "结果--> "+ response.getRawText());

            JSONObject login = JSON.parseObject(response.getRawText());
            Integer resultCode = login.getInteger("result_code");
            String newapptk = login.getString("newapptk");
            String msg = login.getString("result_message");
            if (0 == resultCode) {
                request.putExtra("tk", newapptk);
                request.setOperation(OperationType.UAM_AUTH_CLIENT);
            } else {
                throw new RuntimeException(msg);
            }
        } else if (OperationType.UAM_AUTH_CLIENT.equals(operation)) {
            System.out.println("认证-2" + "结果--> "+ response.getRawText());
            JSONObject login = JSON.parseObject(response.getRawText());
            Integer resultCode = login.getInteger("result_code");
            String apptk = login.getString("apptk");
            String username = login.getString("username");
            String msg = login.getString("result_message");
            if (0 == resultCode) {
                LOG.info("认证-2成功-{}", username);
                request.setOperation(OperationType.QUERY);
            } else {
                throw new RuntimeException(msg);
            }
        } else if (OperationType.LOG.equals(operation)) {

            JSONObject log = parse(response.getRawText());
            if (log.getInteger("data") == 1) {
                request.setSleepTime(0L);
                request.setOperation(OperationType.QUERY);
            } else {
                throw new RuntimeException("日志接口返回失败");
            }

        } else if (OperationType.QUERY.equals(operation)) {
            JSONObject query = null;
            try {
                query = parse(response.getRawText());
            } catch (Exception e) {
                LOG.warn("请求错误");
                request.setOperation(OperationType.QUERY);
                return;
            }
            JSONArray tickets = query.getJSONArray("result");
            for (int i = 0; i < tickets.size(); i++) {
                String[] ticket = tickets.getString(i).split("\\|");
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
                                request.putExtra("secretStr", EncodeUtils.decode(secretStr));
                                request.setOperation(OperationType.CHECK_USER);
                                break;
                            }
                        }
                    }
                }
            }
        } else if (OperationType.SUBMIT_ORDER.equals(operation)) {
            System.out.println("提交订单结果--> "+ response.getRawText());
            String submitOrder = null;
            try {
                submitOrder = parseStr(response.getRawText());
            } catch (Exception e) {
                LOG.warn("提交订单出错，重新查询");
                request.setOperation(OperationType.QUERY);
                return;
            }

            if ("N".equals(submitOrder)) {
                LOG.info("开始订单确认");
            } else if ("Y".equals(submitOrder)){
                LOG.warn("您选择的列车距开车时间很近了，请确保有足够的时间抵达车站。");
            }
            request.setOperation(OperationType.INIT_DC);
        } else if (OperationType.INIT_DC.equals(operation)) {
            System.out.println("获取token结果--> "+ response.getRawText());
            String tokenRegex = "var globalRepeatSubmitToken = '(\\S+)'";
            Pattern tokenPattern = Pattern.compile(tokenRegex);
            Matcher tokenMatcher = tokenPattern.matcher(response.getRawText());
            String token = "";
            if (tokenMatcher.find()) {
                token = tokenMatcher.group(1);
            }else {
                LOG.warn("未查找到TOKEN");
            }

            String ticketInfoForPassengerFormRegex = "var ticketInfoForPassengerForm=(\\{.+\\})?";
            Pattern ticketInfoForPassengerFormPattern = Pattern.compile(ticketInfoForPassengerFormRegex);
            Matcher ticketInfoForPassengerFormMatcher = ticketInfoForPassengerFormPattern.matcher(response.getRawText());

            String ticketInfoForPassenger = "";
            if (ticketInfoForPassengerFormMatcher.find()) {
                ticketInfoForPassenger = ticketInfoForPassengerFormMatcher.group(1);
            }else {
                LOG.warn("未查找到乘客信息");
            }

            String orderRequestParamsRegex = "var ticketInfoForPassengerForm=(\\{.+\\})?";
            Pattern orderRequestParamsPattern = Pattern.compile(orderRequestParamsRegex);
            Matcher orderRequestParamsMatcher = orderRequestParamsPattern.matcher(response.getRawText());

            String orderRequestParams = "";
            if (orderRequestParamsMatcher.find()) {
                orderRequestParams = orderRequestParamsMatcher.group(1);
            }else {
                LOG.warn("未查找到参数信息");
            }

            if (StringUtils.isNotBlank(token)) {
                request.putExtra("token", token);
                request.putExtra("ticketInfoForPassenger", ticketInfoForPassenger);
                request.putExtra("orderRequestParams", orderRequestParams);
                request.setOperation(OperationType.PASSENGER);
            } else {
                request.setOperation(OperationType.INIT_DC);
            }

        } else if (OperationType.PASSENGER.equals(operation)) {
            String passengers = parse(response.getRawText()).getJSONArray("normal_passengers").toJSONString();
            request.putExtra("passengers", passengers);
            request.setOperation(OperationType.CHECK_ORDER);
        } else if (OperationType.CHECK_ORDER.equals(operation)) {

            JSONObject checkOrder = parse(response.getRawText());

            String ifShowPassCode = checkOrder.getString("ifShowPassCode");
            Boolean submitStatus = checkOrder.getBoolean("submitStatus");

            if (StringUtils.isNotBlank(ifShowPassCode) && "Y".equals(ifShowPassCode)) {
                // TODO 验证码实现
                throw new RuntimeException("需要验证码");
            }
            if (submitStatus) {
                System.out.println("车票提交通过，正在尝试排队");
                request.setOperation(OperationType.QUEUE_COUNT);
            }
        } else if (OperationType.QUEUE_COUNT.equals(operation)) {
            JSONObject queueCount = parse(response.getRawText());
            String[] ticket = queueCount.getString("ticket").split("\\,");
            Integer ticketCount = Integer.valueOf(ticket[0]) + Integer.valueOf(ticket[1]);
            Integer countT = queueCount.getInteger("countT");

            if (countT == 0) {
                if (ticketCount < buyer.getTickePeoples().size()) {
                    System.out.println("当前余票数小于乘车人数，放弃订票");
                    request.setOperation(OperationType.QUERY);
                } else {
                    System.out.println("排队成功, 当前余票还剩余: "+ticketCount+" 张");
                    request.setOperation(OperationType.CONFIRM_SINGLE_FOR_QUEUE);
                }
            }
        } else if (OperationType.CONFIRM_SINGLE_FOR_QUEUE.equals(operation)) {
            JSONObject queueCount = parse(response.getRawText());
            if (queueCount.getBoolean("submitStatus")) {
                request.setOperation(OperationType.QUERY_ORDER_WAIT_TIME);
            }
        } else if (OperationType.QUERY_ORDER_WAIT_TIME.equals(operation)) {
            JSONObject queryOrder = parse(response.getRawText());
            if (StringUtils.isBlank(queryOrder.getString("orderId"))) {
                System.out.println("订票成功，订单号为：" + queryOrder.getString("orderId"));
                request.setOperation(OperationType.END);
            }else if (StringUtils.isBlank(queryOrder.getString("waitTime"))) {
                System.out.println("排队等待时间预计还剩 "+ queryOrder.getString("waitTime") +" ms");
                request.setOperation(OperationType.QUERY_ORDER_WAIT_TIME);
            }
        } else if (OperationType.RESULT_ORDER_FOR_DC_QUEUE.equals(operation)) {
            JSONObject queryOrder = parse(response.getRawText());
            if (queryOrder.getBoolean("submitStatus")) {
                System.out.println("----");
            }
            request.setOperation(OperationType.END);
        } else {
            System.out.println("SimpleProcessor--> ---------");
        }
    }

    private JSONObject parse(String text) {
        return parseMsg(text).getJSONObject("data");
    }

    private String parseStr(String text) {
        return parseMsg(text).getString("data");
    }

    private JSONObject parseMsg(String text) {
        JSONObject msg = JSON.parseObject(text);
        Boolean status = msg.getBoolean("status");
        Integer httpStatus = msg.getInteger("httpstatus");
        if ((status == null || !status) || (httpStatus == null || httpStatus != 200)) {
            LOG.warn("请求失败 Response {}", text);
            throw new RuntimeException(text);
        }
        return msg;
    }

    // "座位编号,0,票类型,乘客名,证件类型,证件号,手机号码,保存常用联系人(Y或N)";
    private String passengerTicketStr(Buyer buyer, String passengers) {
        StringBuilder passengerTicket = new StringBuilder();
        for (String ticketPeople : buyer.getTickePeoples()) {
            String seatType = StationUtils.getSeatType(buyer.getSetType().get(0));
            Passenger passenger = getPassenger(ticketPeople, passengers);
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
    private String oldPassengerStr(Buyer buyer, String passengers) {
        StringBuilder oldPassenger = new StringBuilder();
        for (String ticketPeople : buyer.getTickePeoples()) {
            Passenger passenger = getPassenger(ticketPeople, passengers);
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

    private Passenger getPassenger(String passengerName, String passengers) {
        for (Passenger passenger : JSON.parseArray(passengers, Passenger.class)) {
            if (passengerName.equals(passenger.getPassenger_name())) {
                return passenger;
            }
        }
        return null;
    }

}
