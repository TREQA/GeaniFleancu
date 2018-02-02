package REST_Tests;

import REST_Core.TestBase;
import REST_WS.UserInfoWS;
import REST_utils.Log4Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static REST_Framework.CommonTask.*;
import static REST_WS.UserInfoWS.userInfoGETResponse;


public class UserInfo_Tests extends TestBase {

    private static final UserInfoWS userInfoGETResponseTest = new UserInfoWS();

    @BeforeClass(alwaysRun = true)
    public static void userInfoGETResponseTest() {
        userInfoGETResponseTest.userInfoGET();
    }

    /**
     * This method asserts user info
     */
    @Test(groups = {"all", "userInfo"})
    public void userInfo_1_Asserts() {
        Log4Test.info("Starting assertion test");

        //failing assertion
        Log4Test.test("Starting assertion for the necessary fields to not be null");
        assertJsonKeyExistence(userInfoGETResponse, "username");
        assertJsonKeyExistence(userInfoGETResponse, "enabled");
        assertJsonKeyExistence(userInfoGETResponse, "credentialsNonExpired");
        assertJsonKeyExistence(userInfoGETResponse, "accountNonLocked");
        assertJsonKeyExistence(userInfoGETResponse, "accountNonExpired");
        assertJsonKeyExistence(userInfoGETResponse, "authorities.authority");
        softAssert.assertFalse(assertNotNull(userInfoGETResponse, "username"), "Username is null!");
        softAssert.assertFalse(assertNotNull(userInfoGETResponse, "enabled"), "User status is null!");
        softAssert.assertFalse(assertNotNull(userInfoGETResponse, "credentialsNonExpired"), "User credential status is null!");
        softAssert.assertFalse(assertNotNull(userInfoGETResponse, "accountNonLocked"), "USer lock status is null!");
        softAssert.assertFalse(assertNotNull(userInfoGETResponse, "accountNonExpired"), "User expire status is null!");
        softAssert.assertFalse(assertNotNull(userInfoGETResponse, "authorities.authority"), "User role is null!");
        softAssert.assertAll();

        //non failing assertions
        Log4Test.test("Starting assertion for user account status");
        try {
            Assert.assertTrue(assertEqualsPathToString(userInfoGETResponse, "enabled", String.valueOf(true)));
            Log4Test.info("User is enabled.");
        } catch (AssertionError userEnabled) {
            Log4Test.test("WARNING! User is not enabled: " + userEnabled);
            Log4Test.info("");
        }
        try {
            Assert.assertTrue(assertEqualsPathToString(userInfoGETResponse, "credentialsNonExpired", String.valueOf(true)));
            Log4Test.info("Credentials are not expired.");
        } catch (AssertionError credentialsExpired) {
            Log4Test.test("WARNING! Credentials are expired: " + credentialsExpired);
        }
        try {
            Assert.assertTrue(assertEqualsPathToString(userInfoGETResponse, "accountNonLocked", String.valueOf(true)));
            Log4Test.info("Account is not locked.");
        } catch (AssertionError accountLocked) {
            Log4Test.test("WARNING! Account is locked: " + accountLocked);
        }
        try {
            Assert.assertTrue(assertEqualsPathToString(userInfoGETResponse, "accountNonExpired", String.valueOf(true)));
            Log4Test.info("Account is not expired.");
        } catch (AssertionError accountExpired) {
            Log4Test.test("WARNING! Account expired: " + accountExpired);
        }

        Log4Test.info("Assertion test completed");
    }


}
