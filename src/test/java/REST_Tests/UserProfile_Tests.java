package REST_Tests;

import REST_Core.TestBase;
import REST_WS.UserProfileWS;
import REST_utils.Log4Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static REST_Framework.CommonTask.assertNotNull;
import static REST_WS.UserProfileWS.userProfileAvatarUpload;
import static REST_WS.UserProfileWS.userProfileAvatarUploadResponse;

public class UserProfile_Tests extends TestBase {

    private static final UserProfileWS userProfileGETResponseTest = new UserProfileWS();

    @BeforeClass(alwaysRun = true)
    public static void userProfileGETResponseTest() {
        userProfileGETResponseTest.userProfileGET();
    }

    //

    /**
     * This method asserts user profile - wip
     */
    @Test(groups = {"all", "userProfile"})
    public void userProfile_1_mainAsserts() {
        System.out.println( "wip empty method");
    }

    /**
     * This method uploads avatar to user profile
     */
    @Test(groups = {"all", "userProfile"})
    public void userProfile_2_AvatarUpload() {
        String filePath = "FilesForTests/dummyJPG.jpg";
        String mimeType = "image/jpeg";

        userProfileAvatarUpload(filePath, mimeType);
        Assert.assertFalse(assertNotNull(userProfileAvatarUploadResponse, "avatarUrl"), "Avatar Url is null!");
        Log4Test.info("Avatar uploaded successfully!");
        Log4Test.info(userProfileAvatarUploadResponse.path("avatarUrl"));
    }
}
