package org.anele.calls;

import io.restassured.response.Response;
import org.anele.baseTest.BaseTest;
import org.anele.pojo.User;
import org.anele.utils.PathUtils;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.anele.utils.QueryableUtils.RequestAndResponseLogInfo;


public class UserApiCalls extends BaseTest {

    private static BaseTest baseTest;

    public UserApiCalls() {
        baseTest = new BaseTest();
    }

    public static User getUser(String value) throws Exception {
        Response httpResponse = null;
        //define HashMap to control params
        Map<String, String> params = new HashMap<>();
        params.put("username", value);
        //print Request information
//        requestLogForReport(getHttpRequest());
        //get http response
        httpResponse =
                baseTest.getOperation(getHttpRequest(),
                        PathUtils.GET_SPECIFIC_USER, params);
        
//        responseLogForReport(httpResponse);

        if (httpResponse.statusCode() != HttpStatus.SC_OK) {
            throw new Exception("Failed to get access token from the request " + httpResponse.statusCode());
        }

        //deserialize json response into User class
         var h= httpResponse
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
        //print Request information
        RequestAndResponseLogInfo(getHttpRequest(), h);

        return h.as(User.class);

    }
}
