package org.anele.base;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.anele.helpers.OAuth2Helper;
import org.anele.utils.ReadConfigFileUtil;
import org.anele.utils.WriteToConfigFileUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.anele.utils.ReadConfigFileUtil.*;
import static org.anele.utils.WriteToConfigFileUtils.setOAuthToken;

public class BaseTest extends OAuth2Helper {
    protected static String code = "";

    public BaseTest() {
        new ReadConfigFileUtil();
        new WriteToConfigFileUtils();
    }

    @BeforeClass
    void setup() {
        generateSessionCode();
    }

    @AfterClass
    void tearDown() {
        DriverFactory.quitDrivers();
    }

    private static void generateSessionCode() {

        code = OAuth2Helper.returnOAuthorizationCode
                (
                        getBrowser(),
                        getBaseURL(),
                        getClientId(), getScope(),
                        getUsername(),
                        getPassword()
                );
        System.out.println("session code: " + code);

    }

    //define a method, to get requestSpecification builder for oauth end point
    private static RequestSpecification getRequestSpec(String oauthBaseUrl) {

        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setBaseUri(oauthBaseUrl)
                .build();
    }
    //extract access token
    protected static String access_token() {

        RequestSpecification httpRequest = getRequestSpec(getOAuthTokenURL());
        Map<String, String> param = new HashMap<>();
        param.put("client_id", getClientId());
        param.put("client_secret", getClientSecret());
        param.put("scope", getScope());
        param.put("code", code);

        try {
            //build the response

            Response httpResponse = given()
                    .spec(httpRequest)
                    .formParams(param)
                    .post();

            if (httpResponse.statusCode() != HttpStatus.SC_OK) {
                return null;
            }

            //save into config.properties file
            setOAuthToken(httpResponse.jsonPath().getString("access_token"));
            //return token
            return httpResponse.jsonPath().getString("access_token");

        } catch (Exception e) {
            System.out.println("Error occurred while trying to extract access token: " + e.getMessage());
            return null;
        }
    }
}
