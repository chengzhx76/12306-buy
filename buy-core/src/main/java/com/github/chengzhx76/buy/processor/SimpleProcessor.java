package com.github.chengzhx76.buy.processor;

import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.utils.OperationEnum;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/12
 */
public class SimpleProcessor implements Processor {
    @Override
    public void process(Response response) {
        OperationEnum operation = response.getOperation();
        if (OperationEnum.QUERY.equals(operation)) {
            System.out.println("SimpleProcessor--> 解析为查询操作");
        }
    }
}
