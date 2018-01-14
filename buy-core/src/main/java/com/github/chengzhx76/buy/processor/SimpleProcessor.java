package com.github.chengzhx76.buy.processor;

import com.alibaba.fastjson.JSON;
import com.github.chengzhx76.buy.Buyer;
import com.github.chengzhx76.buy.model.HttpRequestBody;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.model.ValidateMsg;
import com.github.chengzhx76.buy.utils.FileUtils;
import com.github.chengzhx76.buy.utils.HttpConstant;
import com.github.chengzhx76.buy.utils.OperationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        }else if (OperationType.CHECK_USER.equals(operation)) {
            request.setUrl(operation.getUrl());
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            Map<String, Object> params = new HashMap<>();
            params.put("json_att", "");
            HttpRequestBody.form(params, "UTF-8");
        }else if (OperationType.CHECK_CAPTCHA.equals(operation)) {
            request.setUrl(operation.getUrl());
            System.out.println("输入图片坐标：");
            /*Scanner scan = new Scanner(System.in);
            String read = scan.nextLine();*/
            String read = "41,41";

            Map<String, Object> params = new HashMap<>();
            params.put("answer", read);
            params.put("login_site", "E");
            params.put("rand", "sjrand");
            params.put("_json_att", "");

            /*params.put("randCode", read);
            params.put("rand", "sjrand");*/
            HttpRequestBody.form(params, "UTF-8");

            request.addHeader("Content-Type", "application/x-www-form-urlencoded;application/json;charset=utf-8");
            request.addHeader("X-Requested-With", "xmlHttpRequest");
            request.addHeader("Referer", "https://kyfw.12306.cn/otn/leftTicket/init");
            request.addHeader("Accept", "*/*");
            request.addHeader("User-Agent", HttpConstant.UserAgent.CHROME);

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

            /*Query query = parseObject(response, Query.class);
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
            }*/
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
            System.out.println(response.getRawText());


            request.setOperation(OperationType.CHECK_CAPTCHA);
        } else if (OperationType.LOGIN.equals(operation)) {
            System.out.println("-----用户登陆-----");
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
