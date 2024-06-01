package org.anele.tests;
import org.anele.calls.UserApiCalls;
import org.testng.annotations.Test;

public class UserTests extends UserApiCalls {

    private final UserApiCalls userApiCalls;

    public UserTests(){
        userApiCalls = new UserApiCalls();
    }

    @Test(description = "Basic test case to retrieve authenticated user GitHub information")
    public void getAuthenticatedUserEmail() throws Exception {

        String username = "Theophelus";
        //get user data
        var user = userApiCalls.getUser(username);

        System.out.println("Print user email address: " + user.getEmail());

    }

    @Test(description = "Basic test case to retrieve authenticated user GitHub information")
    public void getAuthenticatedUserName() throws Exception {

        String username = "Theophelus";
        //get user data
        var user = userApiCalls.getUser(username);

        System.out.println("Print user name: " + user.getName());

    }


}
