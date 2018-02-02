package REST_Print;

import REST_Core.TestBase;
import REST_WS.UserProfileWS;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static REST_WS.UserProfileWS.userProfileGETResponse;

public class UserProfile_Print extends TestBase {

    private static final UserProfileWS userProfileGETResponseTest = new UserProfileWS();

    @BeforeClass(alwaysRun = true)
    public static void userProfileGETResponseTest() {
        userProfileGETResponseTest.userProfileGET();
    }


    /**
     * This method is used to print user profile
     */
    @Test(groups = {"all","userProfile"})
    public void userProfile_1_userProfilePrint() {
        userProfileGETResponse.prettyPrint();
    }
}
