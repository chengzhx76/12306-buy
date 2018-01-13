package com.github.chengzhx76.buy.processor;

import com.alibaba.fastjson.JSON;
import com.github.chengzhx76.buy.Buyer;
import com.github.chengzhx76.buy.model.Query;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.model.ValidateMsg;
import com.github.chengzhx76.buy.utils.OperationEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/12
 */
public class SimpleProcessor implements Processor {

    private final static Logger LOG = LoggerFactory.getLogger(SimpleProcessor.class);


    @Override
    public void preHandle(Buyer buyer, Request request) {
        OperationEnum operation = request.getOperation();
        request.setMethod(operation.getMethod());
        if (OperationEnum.LOG.equals(operation) ||
                OperationEnum.QUERY.equals(operation)) {
            request.setUrl(operation.getUrl()
                    .replace("{TRAINDATE}", buyer.getStationDate())
                    .replace("{FROMSTATION}", buyer.getFromStation())
                    .replace("{TOSTATION}", buyer.getToStation()));
        }

    }

    @Override
    public void afterCompletion(Request request, Response response) {
        OperationEnum operation = response.getOperation();
        String content = response.getContent();
        if (OperationEnum.LOG.equals(operation)) {
            ValidateMsg validateMsg = JSON.parseObject(content, ValidateMsg.class);
            if (validateMsg.getStatus()) {
                request.setOperation(OperationEnum.QUERY);
            } else {
                throw new RuntimeException("日志接口返回失败");
            }
        } else if (OperationEnum.QUERY.equals(operation)) {
            System.out.println("SimpleProcessor--> 解析为查询操作");
            System.out.println("---已有数据切换登录---");

            Query query = JSON.parseObject(content, Query.class);
            if (query == null ||
                    query.getData() == null ||
                    StringUtils.isBlank(query.getData().getResult())) {
                LOG.warn("没有查到数据重新查询");
                return;
            }
            String result[] = StringUtils.split(query.getData().getResult(), "|");
            if ("Y".equals(result[11]) && "预定".equals(result[1])) {

            }





            request.setOperation(OperationEnum.LOGIN);
        } else if (OperationEnum.LOGIN.equals(operation)) {
            System.out.println("SimpleProcessor--> 解析为结束");
            System.out.println("---结束---");
            request.setOperation(OperationEnum.END);
            response.destroy();
        } else {
            System.out.println("SimpleProcessor--> " + request.getOperation());
        }
    }
}
