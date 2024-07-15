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


        try (FileInputStream file = new FileInputStream("src/main/resources/config.properties")) {
            //load the file
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Error while trying to load the config file: " + e);
        }
    }

    public static String getBrowser() {
        return properties.getProperty("browser");
    }

    public static String getOAuthBaseURL() {
        return properties.getProperty("oauth.base.url");
    }

    public static String getClientId() {
        return properties.getProperty("CLIENT_ID");
    }

    public static String getOAuthTokenURL() {
        return properties.getProperty("access.token.url");
    }

    public static String getClientSecret() {
        return properties.getProperty("CLIENT_SECRET");
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
