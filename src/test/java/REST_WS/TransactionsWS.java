package REST_WS;

import REST_Core.TestBase;
import REST_utils.Log4Test;
import com.jayway.restassured.response.Response;

import static REST_Framework.CommonTask.*;
import static REST_WS.LoginWS.loginWSResponse;

public class TransactionsWS extends TestBase {

    public static Response transactionsResponse;

    /**
     * method used to get transaction dates
     */
    public void transactionsDateGET(String accountId, String accountType) {

        String accountStatementActivityParama =
                "accountId=" + accountId + "&" +
                        "accountType=" + accountType;

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for Transactions Dates is: " + env);

            Log4Test.info("AccountID and account Type used for transactions dates are: " + accountId + " / " + accountType);
            transactionsResponse = wsGETwithParam(transactionsDateWSpath, loginWSResponse.cookies(), accountStatementActivityParama);

        } catch (Exception transactionsDateGetException) {
            Log4Test.test("transactionsDateGet error");
            throw transactionsDateGetException;
        }
    }

    /**
     * method used to get transactions based on search parameters
     */
    public void transactionsGET(String accountId, String accountType, String startDate, String endDate, String searchText, Boolean incBolCredit, Boolean incBolDebit, int size, String transactionStatusTypes) {

        //Set transaction body json

        String transactions_requestBody_String = "{" +
                "\"" + "accountId" + "\"" + ":" + "\"" + accountId + "\"" + "," +
                "\"" + "accountType" + "\"" + ":" + "\"" + accountType + "\"" + "," +
                "\"" + "startDate" + "\"" + ":" + "\"" + startDate + "\"" + "," +
                "\"" + "endDate" + "\"" + ":" + "\"" + endDate + "\"" + "," +
                "\"" + "searchText" + "\"" + ":" + "\"" + searchText + "\"" + "," +
                "\"" + "filter" + "\"" + ":" + "{\"includeCreditTransactions\":" + incBolCredit + ",\"includeDebitTransactions\":" + "" + incBolDebit + "}" + "," +
                "\"" + "size" + "\"" + ":" + "\"" + size + "\"" + "," +
                "\"" + "transactionStatusTypes" + "\"" + ":" + "[" + transactionStatusTypes + "]" +
                "}";

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for transaction list get is: " + env);
            Log4Test.info("AccountID and account Type used for transactions dates are: " + accountId + " / " + accountType);

            transactionsResponse = wsPOST(appJSON, transactions_requestBody_String, transactionsGetWSpath, loginWSResponse.cookies());

        } catch (Exception loginWSException_wrong) {
            Log4Test.test("loginWSResponse error");
            throw loginWSException_wrong;
        }
    }

    /**
     * Download transaction document
     */
    public void transactions_downloadDocument(String transactionId) {

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for Account Statement Debit Activity is: " + env);

            transactionsResponse = wsGET(transactionDownloadWSpath + transactionId, loginWSResponse.cookies());

        } catch (Exception transactions_downloadDocumentException) {
            Log4Test.test("transactions_downloadDocument error");
            throw transactions_downloadDocumentException;

        }
    }
}
