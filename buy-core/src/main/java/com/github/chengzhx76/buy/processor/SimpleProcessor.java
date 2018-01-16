package com.github.chengzhx76.buy.processor;

import com.alibaba.fastjson.JSON;
import com.github.chengzhx76.buy.Buyer;
import com.github.chengzhx76.buy.model.*;
import com.github.chengzhx76.buy.utils.FileUtils;
import com.github.chengzhx76.buy.utils.OperationType;
import com.github.chengzhx76.buy.utils.TicketConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
        if (OperationType.LOG.equals(operation) ||
                OperationType.QUERY.equals(operation)) {
            request.setUrl(operation.getUrl()
                    .replace("{TRAINDATE}", buyer.getStationDate())
                    .replace("{FROMSTATION}", buyer.getFromStationCode())
                    .replace("{TOSTATION}", buyer.getToStationCode()));
            request.addHeader("Accept", "*/*");
            request.addHeader("Accept-Encoding", "gzip, deflate, br");
            request.addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
            request.addHeader("Cache-Control", "no-cache");
            request.addHeader("Connection", "keep-alive");
            request.addHeader("Host", "kyfw.12306.cn");
            request.addHeader("If-Modified-Since", "0");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            request.addHeader("X-Requested-With", "XMLHttpRequest");
//            request.addCookie("_jc_save_fromStation", "%u5317%u4EAC%2CBJP");
//            request.addCookie("_jc_save_toStation", "%u66F9%u53BF%2CCXK");
//            request.addCookie("_jc_save_fromDate", "2018-01-16");
//            request.addCookie("_jc_save_toDate", "2018-01-16");
//            request.addCookie("_jc_save_wfdc_flag", "dc");
        }else if (OperationType.CHECK_USER.equals(operation)) {
            request.setUrl(operation.getUrl());
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            Map<String, Object> params = new HashMap<>();
            params.put("json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        }else if (OperationType.CHECK_CAPTCHA.equals(operation)) {
            request.setUrl(operation.getUrl());
            System.out.println("输入图片坐标：");
            Scanner scan = new Scanner(System.in);
            String read = scan.nextLine();
            //String read = "41,41";

            Map<String, Object> params = new HashMap<>();
            params.put("answer", read);
            params.put("login_site", "E");
            params.put("rand", "sjrand");
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        }else if (OperationType.LOGIN.equals(operation)){
            request.setUrl(operation.getUrl());
            Map<String, Object> params = new HashMap<>();
            params.put("username", buyer.getUsername());
            params.put("password", buyer.getPassword());
            params.put("appid", "otn");
            params.put("_json_att", "");
            request.setRequestBody(HttpRequestBody.form(params, "UTF-8"));
        } else {
            request.setUrl(operation.getUrl());
        }

    }

    @Override
    public void afterCompletion(Buyer buyer, Request request, Response response) {
        OperationType operation = response.getOperation();
        if (OperationType.LOG.equals(operation)) {

            ValidateMsg validateMsg = parseObject(response, ValidateMsg.class);
            if (validateMsg.getStatus()) {
                request.setSleepTime(0L);
                request.setOperation(OperationType.QUERY);
            } else {
                throw new RuntimeException("日志接口返回失败");
            }

        } else if (OperationType.QUERY.equals(operation)) {

            Query query = parseObject(response, Query.class);
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
                                request.setOperation(OperationType.CHECK_USER);
                            }
                        }
                    }
                }
            }
            request.setOperation(OperationType.CHECK_USER);
        } else if (OperationType.CHECK_USER.equals(operation)) {
            ValidateMsg validateMsg = parseObject(response, ValidateMsg.class);
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

            ValidateMsg validateMsg = parseObject(response, ValidateMsg.class);
            if ("4".equals(validateMsg.getResult_code())) { // 验证码校验成功
                request.setOperation(OperationType.LOGIN);
            } else if ("5".equals(validateMsg.getResult_code())) {
                throw new RuntimeException(validateMsg.getResult_message());
            } else if ("7".equals(validateMsg.getResult_code())) {
                throw new RuntimeException(validateMsg.getResult_message());
            } else if ("8".equals(validateMsg.getResult_code())) {
                throw new RuntimeException(validateMsg.getResult_message());
            } else { // 正常不走这里
                request.setOperation(OperationType.END);
            }
        } else if (OperationType.LOGIN.equals(operation)) {
            System.out.println("-----用户登陆-----");

            //- 校验用户名密码：
            //  - 密码输入错误：   {"result_message":"密码输入错误。如果输错次数超过4次，用户将被锁定。","result_code":1}
            //  - 用户不存在：     {"result_message":"登录名不存在。","result_code":1}
            //  - 密码输入正确：   {"result_message":"登录成功","result_code":0,"uamtk":"tWDQtPie_z22IWMknmFOymUpDRzvLE4CfzREJBzS9NwrwL2L0"}

            ValidateMsg validateMsg = parseObject(response, ValidateMsg.class);
            if ("1".equals(validateMsg.getResult_code())) {

            } else if ("0".equals(validateMsg.getResult_code())) {

            }
            System.out.println(response.getRawText());
            request.setOperation(OperationType.END);
        } else if (OperationType.END.equals(operation)) {
            System.out.println("-----end-----");
            response.destroy();
        } else {
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
}
