package com.github.chengzhx76.buy.pipeline;

import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/12
 */
public class ConsolePipeline implements Pipeline {
    @Override
    public void process(Request request, Response response) {
        if (!response.isDestroy()) {
            System.out.println(response.getOperation()+"\r\n" +
                    request.getMethod()+"\t" + request.getUrl()+"\r\n" +
                    response.getRawText()+"\r\n");
        }
        request.destroy(); // 处理完成之后把Request销毁，交给下一业务赋值
    }
}
