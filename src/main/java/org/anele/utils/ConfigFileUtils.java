package org.anele.utils;

public class ConfigFileUtils {

    static LocalConfigUtils local = null;
    static CiConfigFileUtils ci = null;
    static boolean platform;

    //initial different environments
    static {
        //as long platform is true, execute CI
        platform = System.getenv("CLIENT_ID") != null;

        if (platform) {
            ci = new CiConfigFileUtils();
        } else
            local = new LocalConfigUtils();
    }

    public static String getBrowser() {
        return platform ? ci.getBrowser() : local.getBrowser();
    }

    public static String getOAuthBaseURL() {
        return platform ? ci.getOAuthBaseURL() : local.getOAuthBaseURL();
    }

    public static String getClientId() {
        return platform ? ci.getClientId() : local.getClientId();
    }

    public static String getOAuthTokenURL() {
        return platform ? ci.getOAuthTokenURL() : local.getOAuthTokenURL();
    }

    public static String getClientSecret() {
        return platform ? ci.getClientSecret() : local.getClientSecret();
    }

    public static String getScope() {
        return platform ? ci.getScope() : local.getScope();
    }

    public static String getUsername() {
        return platform ? ci.getUsername() : local.getUsername();
    }

    public static String getPassword() {
        return platform ? ci.getPassword() : local.getPassword();
    }

    public static String getBaseUrl() {
        return platform ? ci.getBaseUrl() : local.getBaseUrl();
    }
}
