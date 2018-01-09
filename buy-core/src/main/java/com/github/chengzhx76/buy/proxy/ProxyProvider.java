package com.github.chengzhx76.buy.proxy;

import com.github.chengzhx76.buy.Page;
import com.github.chengzhx76.buy.Site;

/**
 * Proxy provider. <br>
 *     
 * @since 0.7.0
 */
public interface ProxyProvider {

    /**
     *
     * Return proxy to Provider when complete a download.
     * @param proxy the proxy config contains host,port and identify info
     * @param page the download result
     * @param site the download task
     */
    void returnProxy(Proxy proxy, Page page, Site site);

    /**
     * Get a proxy for task by some strategy.
     * @param site the download task
     * @return proxy 
     */
    Proxy getProxy(Site site);
    
}
