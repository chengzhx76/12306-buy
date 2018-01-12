package com.github.chengzhx76.buy.processor;

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
    public void process(Request request, Response response) {
        OperationEnum operation = response.getOperation();
        if (OperationEnum.QUERY.equals(operation)) {
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
