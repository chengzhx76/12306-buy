package com.github.chengzhx76.buy.proxy;

import com.github.chengzhx76.buy.model.Response;
import com.github.chengzhx76.buy.Site;

public interface ProxyProvider {

    /**
     *
     * Return proxy to Provider when complete a download.
     * @param proxy the proxy config contains host,port and identify info
     * @param page the download result
     * @param site the download task
     */
    void returnProxy(Proxy proxy, Response page, Site site);

    /**
     * Get a proxy for task by some strategy.
     * @param site the download task
     * @return proxy 
     */
    Proxy getProxy(Site site);
    
}
