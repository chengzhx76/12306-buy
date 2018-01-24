package com.github.chengzhx76.buy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * @desc:
 * @author: hp
 * @date: 2018/1/11
 */
public class ConfigUtils {
    private static final Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

    public static Properties loadProperties(String fileName) {
        Properties properties = new Properties();
        if (fileName.startsWith("/")) {
            try {
                FileInputStream input = new FileInputStream(fileName);
                try {
                    properties.load(input);
                } finally {
                    input.close();
                }
            } catch (Throwable e) {
                logger.warn("Failed to load " + fileName + " error", e);
            }
            return properties;
        }
        try {
            properties.load(new InputStreamReader(ConfigUtils.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static void writeProperties(String fileName, Map<String, String> configs) {
        Properties properties = loadProperties(fileName);
        for (Map.Entry<String, String> config : configs.entrySet()) {
            properties.setProperty(config.getKey(), config.getValue());
        }

        FileOutputStream outputFile = null;
        try {
            outputFile = new FileOutputStream(ConfigUtils.class.getClassLoader().getResource(fileName).getPath());
            properties.store(outputFile, "modify");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputFile != null) {
                try {
                    outputFile.flush();
                    outputFile.close();
                } catch (IOException e) {
                    outputFile = null;
                }
            }
        }
    }

}
