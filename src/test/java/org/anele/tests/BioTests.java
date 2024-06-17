package org.anele.tests;

import org.anele.calls.UserApiCalls;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BioTests extends UserApiCalls {
    private final UserApiCalls userApiCalls;

    public BioTests(){

        userApiCalls = new UserApiCalls();
    }

    @Test(description = "Basic test case to retrieve authenticated user GitHub information, Bio information")
    public void getAuthenticatedBio() throws Exception {

        String username = "Theophelus";
        //get user data
        var user = userApiCalls.getUser(username);
        Assert.assertTrue(user.getBio().contains("SDET"),"BIO don't match with provided information");
    }
}
