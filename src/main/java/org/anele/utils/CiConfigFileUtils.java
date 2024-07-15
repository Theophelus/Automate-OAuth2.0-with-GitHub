package org.anele.utils;

import java.util.Properties;

public class CiConfigFileUtils {

    private static Properties properties = null;

    public CiConfigFileUtils() {
        properties = new Properties();
        loadProperties();

    }

    public static void loadProperties() {
        // Check if running in CI/CD environment (check for environment variables)
        String clientId = System.getenv("CLIENT_ID");
        String clientSecret = System.getenv("CLIENT_SECRET");
        String oauthBaseUrl = System.getenv("OAUTH_BASE_URL");
        String oauthTokenUrl = System.getenv("OAUTH_TOKEN_URL");
        String username = System.getenv("USERNAME");
        String password = System.getenv("PASSWORD");
        String baseUrl = System.getenv("BASE_URL");
        String scope = System.getenv("SCOPE");

        // Load properties from environment variables in CI/CD
        properties.setProperty("CLIENT_ID", clientId);
        properties.setProperty("CLIENT_SECRET", clientSecret);
        properties.setProperty("OAUTH_BASE_URL", oauthBaseUrl);
        properties.setProperty("OAUTH_TOKEN_URL", oauthTokenUrl);
        properties.setProperty("USERNAME", username);
        properties.setProperty("PASSWORD", password);
        properties.setProperty("BASE_URL", baseUrl);
        properties.setProperty("SCOPE", scope);

    }

    public String getBrowser() {
        return properties.getProperty("BROWSER");
    }

    public String getOAuthBaseURL() {
        return properties.getProperty("OAUTH_BASE_URL");
    }

    public String getClientId() {
        return properties.getProperty("CLIENT_ID");
    }

    public String getOAuthTokenURL() {
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
