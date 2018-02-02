package REST_WS;

import REST_Core.TestBase;
import REST_utils.Log4Test;
import com.jayway.jsonpath.internal.JsonFormatter;
import com.jayway.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static REST_Framework.CommonTask.wsGET;
import static REST_Framework.CommonTask.wsPOSTnoAuth;


public class LoginWS extends TestBase {

    public static Response loginWSResponse;

    /**
     * This method is used to create the login body
     */
    private Map<String, String> loginWSbody() {

        //Set login body json
        Map<String, String> loginBody = new HashMap<>();
        loginBody.put("username", String.valueOf(loginUsername));
        loginBody.put("password", loginPassword);
        loginBody.put("channelSubtype", channelSubtype);

        return loginBody;
    }


    /**
     * This method is used to generated the login request and saving the response in loginWSResponse
     * A status check is made and if status == 200 a massage will be displayed
     */
    public void loginWSResponse() {

        Log4Test.info("Environment used for LoginWS is: " + env);
        try {
            loginWSResponse = wsPOSTnoAuth(appJSON, loginWSbody(), loginWSpath);
            if (loginWSResponse.statusCode() == 200) {
                Log4Test.info("Login Success with username and password: " + loginUsername + " / " + loginPassword);

                /*
                 * Get server time response
                 */
                Response wsServerTimeResponse = wsGET(wsTime, loginWSResponse.cookies());
                Log4Test.info("WS server time is: " + wsServerTimeResponse.path("timestamp"));
            }
        } catch (Exception loginWSException) {
            Log4Test.test("loginWSResponse error");
            throw loginWSException;
        }
    }

    /**
     * this method is used to generate a bag user name request body for login
     */
    private Map<String, String> loginWSbody_wrongUsername() {

        //Set login body json
        Map<String, String> loginBody_wrongUsername = new HashMap<>();
        loginBody_wrongUsername.put("username", loginUsername + "1");
        loginBody_wrongUsername.put("password", loginPassword);
        loginBody_wrongUsername.put("channelSubtype", channelSubtype);

        return loginBody_wrongUsername;
    }

    /**
     * method used to bad username request
     */
    public void loginWSResponse_wrongUsername() {

        try {

            wsPOSTnoAuth(appJSON, loginWSbody_wrongUsername(), loginWSpath);

        } catch (Exception loginWSException_wrong) {
            Log4Test.test("loginWSResponse error");
            throw loginWSException_wrong;
        }
    }

    /**
     * Method use for account list order print
     */
    public String loginWS_printAccountListOrder(Response wsResponse) {
        if (loginWSResponse.path("accountsListOrder") != null) {
            return JsonFormatter.prettyPrint(wsResponse.path("accountsListOrder").toString());
        } else {
            return "Account list order is null!!!";
        }
    }

    /**
     * Method use for account nickname print
     */
    public String loginWS_printAccountNicknames(Response wsResponse) {
        if (loginWSResponse.path("accountsNicknames") != null) {
            return JsonFormatter.prettyPrint(wsResponse.path("accountsNicknames").toString());
        } else {
            return "Account nicknames is null!!!";
        }
    }

    /**
     * Method use for account groups print
     */
    public String loginWS_printAccountGroups(Response wsResponse) {
        if (loginWSResponse.path("groups") != null) {
            return JsonFormatter.prettyPrint(wsResponse.path("groups").toString());
        } else {
            return "Account groups is null!!!";
        }
    }
}