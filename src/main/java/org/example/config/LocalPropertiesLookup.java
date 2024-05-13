package org.example.config;

import org.apache.commons.text.lookup.StringLookup;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocalPropertiesLookup implements StringLookup {

    private static Properties properties = loadProperties("C:\\dev\\learn-service\\local.properties");

    private static Properties loadProperties(String propertiesFileName) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(propertiesFileName)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file", e);
        }
        return properties;
    }

    @Override
    public String lookup(String key) {
        return properties.getProperty(key);
    }
}
