package org.anele.base;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.anele.helpers.OAuth2Helper;
import org.anele.helpers.LogHelper;
import org.anele.utils.ReadConfigFileUtil;
import org.anele.utils.WriteToConfigFileUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.anele.utils.ReadConfigFileUtil.*;
import static org.anele.utils.WriteToConfigFileUtils.setOAuthToken;

public class BaseTest extends OAuth2Helper {
    static LogHelper log = new LogHelper(BaseTest.class);
    protected static String code = "";
    //define Request specification to build a request with access token;
    RequestSpecification httpRequest;

    public BaseTest() {
        new ReadConfigFileUtil();
        new WriteToConfigFileUtils();
    }

    @BeforeSuite
    public void setup() throws Exception {
        generateSessionCode();
        //build a request
        httpRequest = new RequestSpecBuilder()
                .setBaseUri(ReadConfigFileUtil.getBaseUrl())
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + access_token())
                .build();

    }

    @Test
    void sample() {
        System.out.println("Welcome home..." + httpRequest);
    }

    @AfterSuite
    void tearDown() {
        DriverFactory.quitDrivers();
    }

    private static void generateSessionCode() {

        code = OAuth2Helper.returnOAuthorizationCode
                (
                        getBrowser(),
                        getOAuthBaseURL(),
                        getClientId(), getScope(),
                        getUsername(),
                        getPassword()
                );
    }

    //define a method, to get requestSpecification builder for oauth end point
    private static RequestSpecification getRequestSpec(String oauthBaseUrl) {

        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setBaseUri(oauthBaseUrl)
                .build();
    }

    //extract access token
    public static String access_token() throws Exception {

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
                throw new Exception("Failed to get access token from the request " + httpResponse.statusCode());
            }

            String accessToken = httpResponse.jsonPath().get("access_token");

            log.info("Access token extracted successfully: "
                    + accessToken);
            //save into config.properties file
            setOAuthToken(accessToken);
            //return token
            return accessToken;

        } catch (IOException e) {
            log.error("Error occurred while trying to extract access token: ",
                    e.getMessage());
            throw new Exception("Error occurred while trying to extract access token:"
                    + e.getMessage(), e);
        }
    }
}
