package com.qaautomation.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    static {
        try {
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
            if (inputStream == null) {
                throw new RuntimeException("config.properties not found");
            }
            properties.load(inputStream);
        } catch (IOException e){
            logger.error("Failed to load config file", e);
            throw new RuntimeException("Configuration initialization failed", e);
        }
    }
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
