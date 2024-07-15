package org.anele.baseTest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.anele.basePage.BaseCore;
import org.anele.basePage.DriverFactory;
import org.anele.utils.ConfigFileUtils;
import org.testng.annotations.*;

public class BaseTest extends BaseCore {
    private static RequestSpecification httpRequest;
    @BeforeSuite
    public void setup() throws Exception {
        generateSessionCode();
        //build a request
        httpRequest = new RequestSpecBuilder()
                .setBaseUri(ConfigFileUtils.getBaseUrl())
                .setAccept(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + access_token())
                .setConfig(RestAssuredConfig.config().logConfig(LogConfig.logConfig().blacklistHeader("Authorization")))
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
