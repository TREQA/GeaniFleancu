package REST_WS;

import REST_Core.TestBase;
import REST_utils.Log4Test;
import com.jayway.restassured.response.Response;

import static REST_Framework.CommonTask.*;
import static REST_WS.LoginWS.loginWSResponse;

public class CyberReceiptWS extends TestBase {

    public static Response cyberReceiptResponse;

    /**
     * This method is used  to generate cyber receipt document id
     */
    public void cyberReceipt_Generate(String wsPath, String accountNo, String postingDate, String id) {

        String cyberReceiptBody = "{" +
                "\"" + "accountNo" + "\"" + ":" + "\"" + accountNo + "\"" + "," +
                "\"" + "postingDate" + "\"" + ":" + "\"" + postingDate + "\"" + "," +
                "\"" + "id" + "\"" + ":" + "\"" + id + "\"" +
                "}";

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for cyber receipt is: " + env);

            cyberReceiptResponse = wsPOST(appJSON, cyberReceiptBody, wsPath, loginWSResponse.cookies());

        } catch (Exception cyberReceipt_DownloadException) {
            Log4Test.test("cyberReceipt_Generate error");
            throw cyberReceipt_DownloadException;

        }
    }

    /**
     * This method is used to check status of document id generated
     */
    public void cyberReceipt_Status_Download(String wsPath, String documentId) throws InterruptedException {
        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for cyber receipt is: " + env);

            if (wsPath.contains(cyberReceiptDocumentStatusWSpath)) {
                documentStatusCheckCounter(wsPath + documentId, loginWSResponse.cookies(), "status", documentId);
                cyberReceiptResponse = wsGET(wsPath + documentId, loginWSResponse.cookies());
            } else {
                cyberReceiptResponse = wsGET(wsPath + documentId, loginWSResponse.cookies());
            }

        } catch (Exception cyberReceipt_DownloadException) {
            Log4Test.test("cyberReceipt_Generate error");
            throw cyberReceipt_DownloadException;

        }
    }
}
