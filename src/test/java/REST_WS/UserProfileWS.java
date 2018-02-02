package REST_WS;

import REST_Core.TestBase;
import REST_utils.Log4Test;
import com.jayway.restassured.response.Response;

import static REST_Framework.CommonTask.wsGET;
import static REST_Framework.CommonTask.wsPOSTmultiPartOnlyFile;
import static REST_WS.LoginWS.loginWSResponse;

public class UserProfileWS extends TestBase {

    public static Response userProfileGETResponse;
    public static Response userProfileAvatarUploadResponse;

    /**
     * method used to upload avatar for user profile WS
     */
    public static void userProfileAvatarUpload(String filePath, String mimeType) {

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();

            Log4Test.info("Environment used for UserInfo avatar upload is: " + env);
            userProfileAvatarUploadResponse = wsPOSTmultiPartOnlyFile(multiPart, userProfileAvatarUloadWSpath, loginWSResponse.cookies(),
                    "picture", filePath, mimeType);

        } catch (Exception userProfileGetException) {
            Log4Test.test("userProfileGet error");
            throw userProfileGetException;

        }
    }

    /**
     * method used to call teh user profile WS and store the response in userProfileGETResponse
     */
    public void userProfileGET() {

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();

            Log4Test.info("Environment used for UserInfo is: " + env);
            userProfileGETResponse = wsGET(userProfileWSpath, loginWSResponse.cookies());

        } catch (Exception userProfileGetException) {
            Log4Test.test("userProfileGet error");
            throw userProfileGetException;

        }
    }
}