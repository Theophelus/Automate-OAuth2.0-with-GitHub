package org.anele.calls;

import io.restassured.response.Response;
import org.anele.base.BaseTest;
import org.anele.pojo.User;
import org.anele.utils.PathUtils;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class UserApiCalls extends BaseTest {

    private static BaseTest baseTest;

    public UserApiCalls(){
        baseTest = new BaseTest();
    }

    public static User getUser(String value) throws Exception {
        //define HashMap to control params
        Map<String, String> params = new HashMap<>();
        params.put("username", value);

        //get http response
        var httpResponse =
                baseTest.getOperation(getHttpRequest(),
                        PathUtils.GET_SPECIFIC_USER, params);

        if (httpResponse.statusCode() != HttpStatus.SC_OK){
            throw new Exception("Failed to get access token from the request " + httpResponse.statusCode());
        }

        //deserialize json response into User class
        return httpResponse
                .then()
                    .assertThat()
                    .statusCode(200)
                .extract()
                    .response()
                    .as(User.class);
    }
}
