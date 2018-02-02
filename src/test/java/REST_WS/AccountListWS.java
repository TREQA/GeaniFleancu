package REST_WS;

import REST_Core.TestBase;
import REST_utils.Log4Test;
import com.jayway.restassured.response.Response;

import static REST_Framework.CommonTask.wsGET;
import static REST_WS.LoginWS.loginWSResponse;

public class AccountListWS extends TestBase {

    public static Response accountListResponse;

    /**
     * Method used to call account list WS and store response is accountListResponse
     */
    public void accountListGET() {

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();

            Log4Test.info("Environment used for UserInfo is: " + env);

            accountListResponse = wsGET(accountListWSpath, loginWSResponse.cookies());

        } catch (Exception accountListGETException) {
            Log4Test.test("accountListGET error");
            throw accountListGETException;

        }
    }
}

