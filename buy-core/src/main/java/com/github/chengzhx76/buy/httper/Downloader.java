package com.github.chengzhx76.buy.httper;

import com.github.chengzhx76.buy.model.Request;
import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.model.Site;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/11
 */
public interface Downloader {

    Response request(Request request, Site site);
}
