package REST_Print;

import REST_Core.TestBase;
import REST_WS.LoginWS;
import REST_utils.Log4Test;
import com.jayway.jsonpath.internal.JsonFormatter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static REST_WS.LoginWS.loginWSResponse;

public class Login_Print extends TestBase {

    private static final LoginWS loginWSResponseTest = new LoginWS();

    @BeforeClass(alwaysRun = true)
    public static void loginWSResponse() {
        loginWSResponseTest.loginWSResponse();
    }

    /**
     * This method is used to print login data
     */
    @Test(groups = {"all", "login"})
    public void loginTests_PrintInformation() {

        Log4Test.info("Starting login printing relevant information");
        //print information
        Log4Test.info("Status code: " + loginWSResponse.statusCode());
        Log4Test.info("User CIF: " + loginWSResponse.path("user.id"));
        Log4Test.info("User First Name and Last name: " + loginWSResponse.path("user.firstName") + " " + loginWSResponse.path("user.lastName"));
        Log4Test.info("User AvatarURL: " + loginWSResponse.path("user.avatarUrl"));
        Log4Test.info("User cnp: " + loginWSResponse.path("user.cnp"));
        Log4Test.info("User segmentation: " + JsonFormatter.prettyPrint(loginWSResponse.path("user.segmentation").toString()));
        Log4Test.info("User primaryMobilePhone: " + loginWSResponse.path("user.primaryMobilePhone"));
        Log4Test.info("User phoneNumbers: " + JsonFormatter.prettyPrint(loginWSResponse.path("user.phoneNumbers").toString()));
        Log4Test.info("User branchName: " + loginWSResponse.path("branchName"));
        Log4Test.info("User has EMV?: " + loginWSResponse.path("hasEMV"));
        Log4Test.info("User tcUrl: " + loginWSResponse.path("tcUrl"));
        Log4Test.info("User account groups list is: " + newline + loginWSResponseTest.loginWS_printAccountGroups(loginWSResponse));
        Log4Test.info("User account nickname list is: " + newline + loginWSResponseTest.loginWS_printAccountNicknames(loginWSResponse));
        Log4Test.info("User account list order is: " + newline + loginWSResponseTest.loginWS_printAccountListOrder(loginWSResponse));
        Log4Test.info("Auth token: " + loginWSResponse.cookie("Authorization"));
        Log4Test.info("Auth PD-S-SESSION-ID: " + loginWSResponse.cookie("PD-S-SESSION-ID"));
        /*Log4Test.info("Response body: ");
        loginWSResponse.prettyPrint();*/
    }
}