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
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");
        String baseUrl = System.getenv("BASE_URL");
        String browser = System.getenv("BROWSER");

        if (clientId != null && clientSecret != null && oauthBaseUrl != null && username != null && password != null && baseUrl != null && browser != null) {
            // Load properties from environment variables in CI/CD
            properties.setProperty("CLIENT_ID", clientId);
            properties.setProperty("CLIENT_SECRET", clientSecret);
            properties.setProperty("OAUTH_BASE_URL", oauthBaseUrl);
            properties.setProperty("USERNAME", username);
            properties.setProperty("PASSWORD", password);
            properties.setProperty("BASE_URL", baseUrl);
            properties.setProperty("browser", browser);

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
        return properties.getProperty("browser");
    }

    public static String getOAuthBaseURL() {
        return properties.getProperty("oauth.base.url");
    }

    public static String getClientId() {
        return properties.getProperty("client_id");
    }

    public static String getOAuthTokenURL() {
        return properties.getProperty("access.token.url");
    }

    public static String getClientSecret() {
        return properties.getProperty("client_secret");
    }

    public static String getScope() {
        return properties.getProperty("scope");
    }

    public static String getUsername() {
        return properties.getProperty("USERNAME");
    }

    public static String getPassword() {
        return properties.getProperty("PASSWORD");
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }
}
