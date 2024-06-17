package org.anele.tests;

import org.anele.calls.UserApiCalls;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class UserTests extends UserApiCalls {

    private final UserApiCalls userApiCalls;

    public UserTests() {
        userApiCalls = new UserApiCalls();
    }

    @Test(description = "Basic test case to retrieve authenticated user GitHub information")
    public void getAuthenticatedUserEmail() throws Exception {

        String username = "Theophelus";
        //get user data
        var user = userApiCalls.getUser(username);
        Assert.assertEquals("aneletom10@gmail.com", user.getEmail(),"Provided user email does not match");
    }

    @Test(description = "Basic test case to retrieve authenticated user GitHub information")
    public void getAuthenticatedUserName() throws Exception {

        String username = "Theophelus";
        //get user data
        var user = userApiCalls.getUser(username);
        System.out.println("Get username: " + user.getName());
        Assert.assertEquals("Anele Theophelus  Tom", user.getName());

    }

    @Test(description = "Skipped method")
    public void testCaseSkipException() {
        System.out.println("Im in skip exception");
        throw new SkipException("Skipping this exception");
    }


}
