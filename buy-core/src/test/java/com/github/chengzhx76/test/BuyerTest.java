package com.github.chengzhx76.test;

import com.github.chengzhx76.buy.Buyer;
import com.github.chengzhx76.buy.Site;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/11
 */
public class BuyerTest {

    public static void main(String[] args) {
        Site site = Site.me();
        Buyer.create(site).setUsername("chengzhx76")
                            .setPassword("123456")
                            .go();
    }

}
