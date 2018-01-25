package com.github.chengzhx76.test;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/25
 */
public class ReadIPTest {
    @Test
    public void test_read_ip() throws IOException {
        List<String> ips = IOUtils.readLines(ReadIPTest.class.getClassLoader().getResourceAsStream("ips.txt"));
        System.out.println(ips.size());
    }
}
