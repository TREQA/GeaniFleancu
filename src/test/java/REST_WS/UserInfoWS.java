package REST_WS;

import REST_Core.TestBase;
import REST_utils.Log4Test;
import com.jayway.restassured.response.Response;

import static REST_Framework.CommonTask.wsGET;
import static REST_WS.LoginWS.loginWSResponse;


public class UserInfoWS extends TestBase {

    public static Response userInfoGETResponse;

    /**
     * Method used to call user info WS and store response is userInfoGETResponse
     */
    public void userInfoGET() {

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();

            Log4Test.info("Environment used for UserInfo is: " + env);

            userInfoGETResponse = wsGET(userInfoWSpath, loginWSResponse.cookies());

        } catch (Exception userInfoGetException) {
            Log4Test.test("userInfoGet error");
            throw userInfoGetException;

        }
    }
}
