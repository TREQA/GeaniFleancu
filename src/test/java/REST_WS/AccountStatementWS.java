package REST_WS;

import REST_Core.TestBase;
import REST_utils.Log4Test;
import com.jayway.restassured.response.Response;

import static REST_Framework.CommonTask.*;
import static REST_WS.LoginWS.loginWSResponse;

public class AccountStatementWS extends TestBase {

    public static Response accountStatementResponse;

    /**
     * Credit - This method is used to retrieve the document id that will be used for download
     */
    public void accountStatementGetActivityReport_Credit(String wsPath, String accountId, String startDate, String endDate) {

        String accountStatementActivityParama =
                "accountId=" + accountId + "&" +
                        "startDate=" + startDate + "&" +
                        "endDate=" + endDate;

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for Account Statement Credit Activity is: " + ENV + " and account ID is: " + accountId);

            accountStatementResponse = wsGETwithParam(wsPath, loginWSResponse.cookies(), accountStatementActivityParama);

        } catch (Exception accountStatementGetActivityReport_CreditException) {
            Log4Test.test("accountStatementGetActivityReport_Credit error");
            throw accountStatementGetActivityReport_CreditException;

        }
    }

    /**
     * DEBIT - activity report dates
     */
    public void accountStatementGetActivityReport_Debit(String wsPath, String accountId, String accountType, String startDate, String endDate) {

        String accountStatementActivityParama =
                "accountId=" + accountId + "&" +
                        "accountType=" + accountType + "&" +
                        "startDate=" + startDate + "&" +
                        "endDate=" + endDate;

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for Account Statement Debit Activity is: " + ENV + " and account ID is: " + accountId);

            accountStatementResponse = wsGETwithParam(wsPath, loginWSResponse.cookies(), accountStatementActivityParama);

        } catch (Exception accountStatementGetActivityReport_DebitException) {
            Log4Test.test("accountStatementGetActivityReport_Debit error");
            throw accountStatementGetActivityReport_DebitException;

        }
    }

    /**
     * CREDIT - This method is used to download the document with field ID
     */
    public void accountStatementDownloadActivityReport_CreditWithFieldID(String wsPath, String accountId, String fileId) {

        Log4Test.info("Environment used for Account Statement Credit Download Activity Report: " + ENV + " and account ID is: " + accountId);

        String accountStatementActivityParama =
                "accountId=" + accountId + "&" +
                        "fileId=" + fileId;

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();

            accountStatementResponse = wsGETwithParam(wsPath, loginWSResponse.cookies(), accountStatementActivityParama);

        } catch (Exception accountStatementDownloadActivityReport_CreditWithFieldIDException) {
            Log4Test.test("accountStatementDownloadActivityReport_CreditWithFieldID error");
            throw accountStatementDownloadActivityReport_CreditWithFieldIDException;

        }
    }

    /**
     * CREDIT - This method is used to download the document without field ID
     */
    public void accountStatementDownloadActivityReport_CreditWithoutFieldID(String wsPath, String accountId) {

        Log4Test.info("Environment used for Account Statement Credit Download Activity Report: " + ENV + " and account ID is: " + accountId);

        String accountStatementActivityParama =
                "accountId=" + accountId;

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();

            accountStatementResponse = wsGETwithParam(wsPath, loginWSResponse.cookies(), accountStatementActivityParama);

        } catch (Exception accountStatementDownloadActivityReport_CreditWithoutFieldIDException) {
            Log4Test.test("accountStatementDownloadActivityReport_CreditWithoutFieldID error");
            throw accountStatementDownloadActivityReport_CreditWithoutFieldIDException;

        }
    }

    /**
     * DEBIT - activity report download with onRequest param
     */
    public void accountStatementDownloadActivityReport_DebitWithOnRequestParam(String wsPath, String accountId, String accountType, String startDate, String endDate, String fileFormat, boolean onRequest) {

        String accountStatementActivityParama =
                "accountId=" + accountId + "&" +
                        "accountType=" + accountType + "&" +
                        "fileFormat=" + fileFormat + "&" +
                        "startDate=" + startDate + "&" +
                        "endDate=" + endDate + "&" +
                        "onRequest=" + onRequest;

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for Account Statement Debit Activity is: " + ENV + " and account ID is: " + accountId);

            accountStatementResponse = wsGETwithParam(wsPath, loginWSResponse.cookies(), accountStatementActivityParama);

        } catch (Exception accountStatementDownloadActivityReport_DebitWithOnRequestParamException) {
            Log4Test.test("accountStatementDownloadActivityReport_DebitWithOnRequestParam error");
            throw accountStatementDownloadActivityReport_DebitWithOnRequestParamException;

        }
    }

    /**
     * DEBIT - activity report download without onRequest param
     */
    public void accountStatementDownloadActivityReport_DebitWithoutOnRequestParam(String wsPath, String accountId, String accountType, String startDate, String endDate, String fileFormat) {

        String accountStatementActivityParama =
                "accountId=" + accountId + "&" +
                        "accountType=" + accountType + "&" +
                        "fileFormat=" + fileFormat + "&" +
                        "startDate=" + startDate + "&" +
                        "endDate=" + endDate;

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for Account Statement Debit Activity is: " + ENV + " and account ID is: " + accountId);

            accountStatementResponse = wsGETwithParam(wsPath, loginWSResponse.cookies(), accountStatementActivityParama);

        } catch (Exception accountStatementDownloadActivityReport_DebitWithoutOnRequestParamException) {
            Log4Test.test("accountStatementDownloadActivityReport_DebitWithoutOnRequestParam error");
            throw accountStatementDownloadActivityReport_DebitWithoutOnRequestParamException;

        }
    }

    /**
     * DEBIT - activity report download last statement
     */
    public void accountStatementDownloadActivityReport_DebiLastStatement(String wsPath, String accountId, String accountType, String fileFormat) {

        String accountStatementActivityParama =
                "accountId=" + accountId + "&" +
                        "accountType=" + accountType + "&" +
                        "fileFormat=" + fileFormat;

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for Account Statement Debit Activity is: " + ENV + " and account ID is: " + accountId);

            accountStatementResponse = wsGETwithParam(wsPath, loginWSResponse.cookies(), accountStatementActivityParama);

        } catch (Exception accountStatementDownloadActivityReport_DebiLastStatementException) {
            Log4Test.test("accountStatementDownloadActivityReport_DebiLastStatement error");
            throw accountStatementDownloadActivityReport_DebiLastStatementException;

        }
    }

    /**
     * LOAN - graphic details and document generation
     */
    public void loanGraphicDetailsAndDocumentGeneration(String wsPath, String accountId) {

        String accountStatementActivityParama =
                "accountId=" + accountId;

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for Account Statement Loan Activity is: " + ENV + " and account ID is: " + LOAN_ACCOUNT_ID);

            accountStatementResponse = wsGETwithParam(wsPath, loginWSResponse.cookies(), accountStatementActivityParama);

        } catch (Exception accountStatementDownloadActivityReport_DebiLastStatementException) {
            Log4Test.test("accountStatementDownloadActivityReport_DebiLastStatement error");
            throw accountStatementDownloadActivityReport_DebiLastStatementException;

        }
    }

    /**
     * LOAN - graphic details and document generation
     */
    public void loanGraphicFileStatusAndDownload(String wsPath, String documentID) throws Exception {

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for Account Statement Loan Activity is: " + ENV + " and account ID is: " + LOAN_ACCOUNT_ID);
            String loanFileWSpath = wsPath + documentID;

            if (loanFileWSpath.contains(ACCOUNT_STATEMENT_LOAN_GRAPHIC_FILE_STATUS_WS_PATH)) {
                documentStatusCheckCounter(loanFileWSpath, loginWSResponse.cookies(), "status", documentID);
                accountStatementResponse = wsGET(loanFileWSpath, loginWSResponse.cookies());
            } else {
                accountStatementResponse = wsGET(loanFileWSpath, loginWSResponse.cookies());
            }

        } catch (Exception accountStatementDownloadActivityReport_DebiLastStatementException) {
            Log4Test.test("accountStatementDownloadActivityReport_DebiLastStatement error");
            throw accountStatementDownloadActivityReport_DebiLastStatementException;

        }
    }
}
