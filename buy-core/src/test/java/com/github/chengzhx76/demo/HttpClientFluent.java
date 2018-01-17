package com.github.chengzhx76.demo;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/17
 *
 .http://daihaixiang.blog.163.com/blog/static/3830134201410238554195/
 */
public class HttpClientFluent {
    /*public void fluentAPIDemo(String contentUrl) throws IOException {
        try {
            // demo1: 获取文字 , 使用默认连接池(200 total/100 per route), returnContent()会自动获取全部内容后关闭inputstream。
            String resultString = Request.Get(contentUrl).execute().returnContent().asString();

            // demo2: 获取图片, 增加超时设定。
            byte[] resultBytes = Request.Get(contentUrl).connectTimeout(TIMEOUT_SECONDS * 1000)
                    .socketTimeout(TIMEOUT_SECONDS * 1000).execute().returnContent().asBytes();

            // demo3: 获取图片，使用之前设置好了的自定义连接池与超时的httpClient
            Executor executor = Executor.newInstance(httpClient);
            String resultString2 = executor.execute(Request.Get(contentUrl)).returnContent().asString();
        } catch (HttpResponseException e) {
            logger.error("Status code:" + e.getStatusCode(), e);
        }
    }*/
}
