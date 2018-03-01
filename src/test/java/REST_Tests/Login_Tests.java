package REST_Tests;

import REST_Core.TestBase;
import REST_WS.LoginWS;
import REST_utils.Log4Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static REST_Framework.CommonTask.assertJsonKeyExistence;
import static REST_Framework.CommonTask.assertNotNull;
import static REST_WS.LoginWS.loginWSResponse;

public class Login_Tests extends TestBase {

    private static final LoginWS loginWSResponseTest = new LoginWS();

    @BeforeClass(alwaysRun = true)
    public static void loginWSResponse() {
        loginWSResponseTest.loginWSResponse();
    }

    /**
     * This test verify the login function and asserts the user profile response
     */
    @Test(groups = {"all", "login"})
    public void loginTests_1_Asserts() {
        Log4Test.info("Starting login assertions test");

        try {
            //failing assertion
            Log4Test.info("Starting assertions for the principal fields to not be null" + NEW_LINE);
            assertJsonKeyExistence(loginWSResponse, "user.id");
            assertJsonKeyExistence(loginWSResponse, "user.cnp");
            assertJsonKeyExistence(loginWSResponse, "user.primaryMobilePhone");
            assertJsonKeyExistence(loginWSResponse, "branchName");
            softAssert.assertNotNull(loginWSResponse.cookie("Authorization"), "No authorization cooke set");
            softAssert.assertFalse(assertNotNull(loginWSResponse, "user.id"), "No user id");
            softAssert.assertFalse(assertNotNull(loginWSResponse, "user.cnp"), "No user CNP");
            softAssert.assertFalse(assertNotNull(loginWSResponse, "user.primaryMobilePhone"), "No user primaryMobilePhone");
            softAssert.assertFalse(assertNotNull(loginWSResponse, "branchName"), "No branch name");
            softAssert.assertAll();

            //non failing assertions
            Log4Test.info("Starting assertions to see if account list order and nicknames are null" + NEW_LINE);
            try {
                Assert.assertFalse(assertNotNull(loginWSResponse, "accountsListOrder"));
            } catch (AssertionError accountsListOrderAssertError) {
                Log4Test.test("WARNING! Account List order is null: " + accountsListOrderAssertError);
            }
            try {
                Assert.assertFalse(assertNotNull(loginWSResponse, "accountsNicknames"));
            } catch (AssertionError accountsNickNamesAssertError) {
                Log4Test.test("WARNING! Account Nick names is null: " + accountsNickNamesAssertError);
            }
        } catch (IllegalArgumentException IllegalArgumentException) {
            loginWSResponse.prettyPeek();
            throw IllegalArgumentException;
        }
        Log4Test.test("Assertion complete" + NEW_LINE);

    }

    /**
     * Tis test verify wong user name scenario
     */
    @Test
    public void loginTests_2_wrongUsername() {
        LoginWS loginWSResponse_wrongUsername = new LoginWS();
        loginWSResponse_wrongUsername.loginWSResponse_wrongUsername();

    }
}
