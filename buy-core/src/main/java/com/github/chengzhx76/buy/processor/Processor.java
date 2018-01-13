package com.github.chengzhx76.buy.processor;

import com.github.chengzhx76.buy.Buyer;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/12
 */
public interface Processor {
    void preHandle(Buyer buyer, Request request);
    void afterCompletion(Buyer buyer, Request request, Response response);
}
