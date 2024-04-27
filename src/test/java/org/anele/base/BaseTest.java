package org.anele.base;
import org.anele.Helpers.OAuth2Helper;
import org.anele.utils.ReadConfigFileUtil;
import org.anele.utils.WriteToConfigFileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.anele.utils.ReadConfigFileUtil.*;

public class BaseTest extends OAuth2Helper {
    protected static String code = "";

    public BaseTest() {
        new ReadConfigFileUtil();
        new WriteToConfigFileUtils();
    }

    @BeforeClass
    void setup() {
        generateSessionCode();
    }

    @AfterClass
    void tearDown() {
        DriverFactory.quitDrivers();
    }

    private static void generateSessionCode() {

        code = OAuth2Helper.returnOAuthorizationCode
                (
                        getBrowser(),
                        getBaseURL(),
                        getClientId(), getScope(),
                        getUsername(),
                        getPassword()
                );
        System.out.println("session code: " + code);

    }

}
