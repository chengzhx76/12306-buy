package com.github.chengzhx76.buy.pipeline;

import com.github.chengzhx76.buy.model.Cookie;
import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;

import java.util.Set;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/12
 */
public class ConsolePipeline implements Pipeline {
    @Override
    public void process(Request request, Response response) {
        System.out.println("--------------------RESULT-----------------");
        System.out.println(response.getOperation()+"\r\n" +
                request.getMethod() + " " + request.getUrl()+"\r\n" +
                response.getRawText());

        Set<Cookie> cookies = response.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + "|" + cookie.getValue() +
                    "|" + cookie.getPath() + "|" + cookie.getDomain());
        }
        System.out.println("---------------------END-------------------\r\n");
        request.destroy(); // 处理完成之后把Request销毁，交给下一业务赋值
    }
}
