package org.anele.helpers;
import org.anele.pages.LoginIntoGitHubPage;
import java.text.MessageFormat;

public class OAuth2Helper extends LoginIntoGitHubPage {

    static LogHelper log = new LogHelper(OAuth2Helper.class);

    //method to build Auth Url with base url, client id and scope as parameters
    private static StringBuilder buildUrl(String baseUrl,
                                          String clientId,
                                          String scope) {

        try {

            StringBuilder builder = new StringBuilder(baseUrl)
                    .append("?")
                    .append(MessageFormat.format("client_id={0}", clientId))
                    .append("&")
                    .append(MessageFormat.format("scope={0}", scope));

            return builder;

        } catch (Exception e) {
            System.out.println("Base URL not built successfully" + e.getMessage());
            return null;
        }
    }

    public static String returnOAuthorizationCode(String browser, String baseUrl, String clientId, String scope, String username, String password) throws InterruptedException {
        //get current browser to extract auth code
        getCurrentBrowser(browser);
        //build url
        StringBuilder URL = buildUrl(baseUrl, clientId, scope);
        //launch the browser
        assert URL != null;

        getDriver().get(URL.toString());
        log.info(URL.toString() + "is launched successfully");
        //validate login header
        headerText("Sign in to GitHub");
        //login into the login page
        loginIntoGitHub(username, password);
        //get currentUrl
        String getCode = getCurrentUrl();
        //extract authorization code'
        log.info("Session code about to be extracted: " + getCode);
        return extractCode(getCode);
    }

    //create a method to extract authorization code, after logging in into gitHub application
    private static String extractCode(String code) {

        String authorizationCode = null;
        try {

            if (code.contains("=")) {
                //split the string based on the delimiter and get the entry at index 1
                authorizationCode = code.split("=")[1];

            }
            //return the auth code
            log.info("Session code extracted successfully: " + authorizationCode);
            return authorizationCode;

        } catch (Exception e) {
            log.error("Error occurred while trying to extract authorization code:",
                    e.getMessage());
            return null;
        }
    }
}
