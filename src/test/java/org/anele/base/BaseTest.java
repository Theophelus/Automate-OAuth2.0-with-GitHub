package org.anele.base;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.anele.Helpers.OAuth2Helper;
import org.anele.utils.ReadConfigFileUtil;
import org.anele.utils.WriteToConfigFileUtils;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
}
