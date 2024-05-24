package org.anele.utils;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import java.util.HashMap;
import java.util.Map;

public class QueryableUtils {

    //map data to be appended on the table's template
    static Map<String, Object> map = new HashMap<>();

    //request information to be appended on the as part of the request information
    public static void requestLogForReport(RequestSpecification requestSpecification) {

        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        map.put("Endpoint", queryableRequestSpecification.getBaseUri());

        Map<String, Object> storeRequestInfo = new HashMap<>();
        //loop through the headers, then extract the name and the value then store in a map
        for (int i = 0; i < queryableRequestSpecification.getHeaders().asList().size(); i++) {
            //get the current header
            Header header = queryableRequestSpecification.getHeaders().asList().get(i);
            //store each header with its value
            storeRequestInfo.put(header.getName(), header.getValue());
        }
        //push the headers in the template as key value pair
        map.put("headers", storeRequestInfo);
    }

    public static void responseLogForReport(Response response) {
        map.put("Status", response.getStatusCode());
        map.put("Body", response.getBody().prettyPrint());
    }

    //consolidate both for request and response
    public static void RequestAndResponseLogInfo(RequestSpecification requestSpecification, Response response) {
        requestLogForReport(requestSpecification);
        responseLogForReport(response);
    }

    public static Map<String, Object> getBuildRequests() {
        return map;
    }


}
