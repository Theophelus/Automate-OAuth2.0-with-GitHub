package org.anele.tests;

import io.restassured.http.ContentType;
import org.anele.base.BaseTest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserTestExample extends BaseTest {


    public UserTestExample() {
        new BaseTest();
    }


    @Test(description = "Basic test case to retrieve authenticated user GitHub information")
    public void getAuthenticatedUserTextExample() {

        var validatableResponse =
                given()
                    .baseUri("https://api.github.com/")
                        .pathParam("username", "Theophelus")
                    .header("Authorization", "Bearer " + access_token())
                    .header("X-GitHub-Api-Version", "2022-11-28")
                    .accept(ContentType.JSON)
                .when()
                    .get("users/{username}")
                .then()
                    .assertThat().statusCode(HttpStatus.SC_OK);

        System.out.println(validatableResponse.extract().response().getBody().prettyPrint());


    }
}
