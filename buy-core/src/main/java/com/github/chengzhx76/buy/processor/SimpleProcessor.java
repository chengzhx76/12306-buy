package com.github.chengzhx76.buy.processor;

import com.github.chengzhx76.buy.Buyer;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.utils.OperationEnum;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/12
 */
public class SimpleProcessor implements Processor {

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
        if (OperationEnum.LOG.equals(operation)) {
            System.out.println("SimpleProcessor--> 解析为日志操作");
            System.out.println("---正常切换查询---");
            request.setOperation(OperationEnum.QUERY);
        } else if (OperationEnum.QUERY.equals(operation)) {
            System.out.println("SimpleProcessor--> 解析为查询操作");
            System.out.println("---已有数据切换登录---");
            request.setOperation(OperationEnum.LOGIN);
        } else if (OperationEnum.LOGIN.equals(operation)) {
            System.out.println("SimpleProcessor--> 解析为结束");
            System.out.println("---结束---");
            request.setOperation(OperationEnum.END);
        } else {
            System.out.println("SimpleProcessor--> " + request.getOperation());
        }
    }
}
