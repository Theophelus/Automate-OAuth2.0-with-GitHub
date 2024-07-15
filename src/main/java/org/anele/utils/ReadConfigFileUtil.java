package org.anele.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigFileUtil {

    private static Properties properties = null;


    public ReadConfigFileUtil() {
        properties = new Properties();
        loadProperties();
    }

    private static synchronized void loadProperties() {
        String configFilePath = "src/main/resources/config.properties";

        // Check if running in CI/CD environment (check for environment variables)
        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        String oauthBaseUrl = System.getenv("OAUTH_BASE_URL");
        String oauthTokenUrl = System.getenv("OAUTH_TOKEN_URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");
        String baseUrl = System.getenv("BASE_URL");
        String scope = System.getenv("SCOPE");

        if (clientId != null && clientSecret != null && oauthBaseUrl != null && username
                != null && password != null && baseUrl != null && oauthTokenUrl != null && scope != null) {
            // Load properties from environment variables in CI/CD
            System.out.println("Client Secrets: " + clientSecret);
            properties.getProperty("CLIENT_ID", clientId);
            properties.getProperty("CLIENT_SECRET", clientSecret);
            properties.getProperty("OAUTH_BASE_URL", oauthBaseUrl);
            properties.getProperty("OAUTH_TOKEN_URL", oauthTokenUrl);
            properties.getProperty("USERNAME", username);
            properties.getProperty("PASSWORD", password);
            properties.getProperty("BASE_URL", baseUrl);
            properties.getProperty("SCOPE", scope);


        } else {
            // Load properties from local file in development
            try (FileInputStream file = new FileInputStream(configFilePath)) {
                properties.load(file);
            } catch (IOException e) {
                throw new RuntimeException("Error while trying to load the config file: " + e);
            }
        }
    }

    public static String getBrowser() {
        return properties.getProperty("BROWSER");
    }

    public static String getOAuthBaseURL() {
        return properties.getProperty("OAUTH_BASE_URL");
    }

    public static String getClientId() {
        return properties.getProperty("CLIENT_ID");
    }

    public static String getOAuthTokenURL() {
        return properties.getProperty("OAUTH_TOKEN_URL");
    }

    public static String getClientSecret() {
        return properties.getProperty("CLIENT_SECRET");
    }

    public static String getScope() {
        return properties.getProperty("SCOPE");
    }

    public static String getUsername() {
        return properties.getProperty("USERNAME");
    }

    public static String getPassword() {
        return properties.getProperty("PASSWORD");
    }

    public static String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }
}
