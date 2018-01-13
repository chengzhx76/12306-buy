package com.github.chengzhx76.buy.utils;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Desc:
 * Author: 光灿
 * Date: 2018/1/13
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

    public static void writeToLocal(String destination, byte[] bytes) throws IOException {
        FileOutputStream downloadFile = new FileOutputStream(destination);
        downloadFile.write(bytes);
        downloadFile.flush();
        downloadFile.close();
    }
}
