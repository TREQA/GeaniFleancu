package REST_Print;

import REST_Core.TestBase;
import REST_WS.UserInfoWS;
import REST_utils.Log4Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static REST_WS.UserInfoWS.userInfoGETResponse;

public class UserInfo_Print extends TestBase {

    private static final UserInfoWS userInfoGETResponseTest = new UserInfoWS();

    @BeforeClass(alwaysRun = true)
    public static void userInfoGETResponseTest() {
        userInfoGETResponseTest.userInfoGET();
    }

    /**
     * This method is used to print user info
     */
    @Test(groups = {"all", "userInfo"})
    public void userInfo_1_print() {
        Log4Test.info("Starting printing");
        Log4Test.info("User: " + userInfoGETResponse.path("username"));
        Log4Test.info("User role is: " + userInfoGETResponse.path("authorities.authority"));
        Log4Test.info("User is enabled: " + userInfoGETResponse.path("enabled").toString());
        Log4Test.info("Credentials are not expired: " + userInfoGETResponse.path("credentialsNonExpired").toString());
        Log4Test.info("Account is not locked: " + userInfoGETResponse.path("accountNonLocked").toString());
        Log4Test.info("Account is not expired: " + userInfoGETResponse.path("accountNonExpired").toString());
        Log4Test.info("Ending printing");
    }
}