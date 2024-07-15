package org.anele.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocalConfigUtils {

    private static Properties properties = null;

    public LocalConfigUtils() {
        properties = new Properties();
        loadProperties();
    }

    private static synchronized void loadProperties() {
        String configFilePath = "src/main/resources/config.properties";

        // Load properties from local file in development
        try (FileInputStream file = new FileInputStream(configFilePath)) {
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Error while trying to load the config file: " + e);
        }

    }

    public  String getBrowser() {
        return properties.getProperty("BROWSER");
    }

    public  String getOAuthBaseURL() {
        return properties.getProperty("OAUTH_BASE_URL");
    }

    public  String getClientId() {
        return properties.getProperty("CLIENT_ID");
    }

    public  String getOAuthTokenURL() {
        return properties.getProperty("OAUTH_TOKEN_URL");
    }

    public String getClientSecret() {
        return properties.getProperty("CLIENT_SECRET");
    }

    public String getScope() {
        return properties.getProperty("SCOPE");
    }

    public String getUsername() {
        return properties.getProperty("USERNAME");
    }

    public String getPassword() {
        return properties.getProperty("PASSWORD");
    }

    public String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }
}