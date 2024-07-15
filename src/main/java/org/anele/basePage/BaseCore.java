package org.anele.basePage;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.anele.helpers.LogHelper;
import org.anele.helpers.OAuth2Helper;
import org.anele.utils.ConfigFileUtils;
import org.anele.utils.PathUtils;
import org.anele.utils.WriteToConfigFileUtils;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.anele.utils.ConfigFileUtils.*;

public class BaseCore extends OAuth2Helper {
    static LogHelper log = new LogHelper(BaseCore.class);
    protected static String code = "";
    private static RequestSpecification httpRequest;

    public BaseCore() {
        new ConfigFileUtils();
        new WriteToConfigFileUtils();
    }

    protected static void generateSessionCode() throws InterruptedException {

        code = OAuth2Helper.returnOAuthorizationCode
                (
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

            //save into config.properties file
            //return token
            return httpResponse.jsonPath().get("access_token");

        } catch (IOException e) {
            log.error("Error occurred while trying to extract access token: ",
                    e.getMessage());
            throw new Exception("Error occurred while trying to extract access token:"
                    + e.getMessage(), e);
        }
    }

    //get json response from, HTTPS Request
    public Response getOperation(RequestSpecification requestSpec,
                                 PathUtils pathBuilder, Map<String, String> param) {

        httpRequest = requestSpec.relaxedHTTPSValidation();

        if (param != null & !Objects.requireNonNull(param).isEmpty()) {
            httpRequest.pathParams(param);
        }

        //return the response
        var path = pathBuilder.getPath();
        //get json response
        return
                given()
                        .spec(httpRequest)
                        .when()
                        .get(path);
    }

}
