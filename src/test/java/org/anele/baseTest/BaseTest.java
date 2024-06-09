package org.anele.baseTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import org.anele.basePage.BaseCore;
import org.anele.basePage.DriverFactory;
import org.anele.utils.ReadConfigFileUtil;
import org.testng.annotations.*;

public class BaseTest extends BaseCore {
    private static RequestSpecification httpRequest;
    @BeforeSuite
    public void setup() throws Exception {
        generateSessionCode();
        //build a request
        httpRequest = new RequestSpecBuilder()
                .setBaseUri(ReadConfigFileUtil.getBaseUrl())
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + access_token())
                .addHeader("X-GitHub-Api-Version", "2022-11-28")
                .and()
                .build();
    }

    @AfterSuite
    void tearDown() {
        DriverFactory.quitDrivers();
    }

    public static RequestSpecification getHttpRequest() {
        return httpRequest;
    }
}
