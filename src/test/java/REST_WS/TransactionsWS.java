package REST_WS;

import REST_Core.TestBase;
import REST_utils.Log4Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.SkipException;

import java.util.*;

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
            Log4Test.info("Environment used for Transactions Dates is: " + ENV);

            Log4Test.info("AccountID and account Type used for transactions dates are: " + accountId + " / " + accountType);
            transactionsResponse = wsGETwithParam(TRANSACTIONS_DATE_WS_PATH, loginWSResponse.cookies(), accountStatementActivityParama);

        } catch (Exception transactionsDateGetException) {
            Log4Test.test("transactionsDateGet error");
            throw transactionsDateGetException;
        }
    }

    /**
     * This method is used to create transaction body request
     */
    public String transactionBodyCreate(String accountId, String accountType, String startDate, String endDate, String searchText, Boolean incBolCredit, Boolean incBolDebit, int size, String transactionStatusTypes) {

        return "{" +
                "\"" + "accountId" + "\"" + ":" + "\"" + accountId + "\"" + "," +
                "\"" + "accountType" + "\"" + ":" + "\"" + accountType + "\"" + "," +
                "\"" + "startDate" + "\"" + ":" + "\"" + startDate + "\"" + "," +
                "\"" + "endDate" + "\"" + ":" + "\"" + endDate + "\"" + "," +
                "\"" + "searchText" + "\"" + ":" + "\"" + searchText + "\"" + "," +
                "\"" + "filter" + "\"" + ":" + "{\"includeCreditTransactions\":" + incBolCredit + ",\"includeDebitTransactions\":" + "" + incBolDebit + "}" + "," +
                "\"" + "size" + "\"" + ":" + "\"" + size + "\"" + "," +
                "\"" + "transactionStatusTypes" + "\"" + ":" + "[" + transactionStatusTypes + "]" +
                "}";
    }

    /**
     * This method is used to create transaction body request for credit card account
     */
    public String transactionCreditCardBodyCreate(String accountId, String branchId, String ccAccountId, String ccBranchId, String ccProductId, String currency, String productId, String startDate, String endDate, String searchText, int size, String transactionStatusTypes) {

        return "{" +
                "\"" + "accountId" + "\"" + ":" + "\"" + accountId + "\"" + "," +
                "\"" + "branchId" + "\"" + ":" + "\"" + branchId + "\"" + "," +
                "\"" + "ccAccountId" + "\"" + ":" + "\"" + ccAccountId + "\"" + "," +
                "\"" + "ccBranchId" + "\"" + ":" + "\"" + ccBranchId + "\"" + "," +
                "\"" + "ccProductId" + "\"" + ":" + "\"" + ccProductId + "\"" + "," +
                "\"" + "currency" + "\"" + ":" + "\"" + currency + "\"" + "," +
                "\"" + "productId" + "\"" + ":" + "\"" + productId + "\"" + "," +
                "\"" + "startDate" + "\"" + ":" + "\"" + startDate + "\"" + "," +
                "\"" + "endDate" + "\"" + ":" + "\"" + endDate + "\"" + "," +
                "\"" + "searchText" + "\"" + ":" + "\"" + searchText + "\"" + "," +
                "\"" + "size" + "\"" + ":" + "\"" + size + "\"" + "," +
                "\"" + "transactionStatusTypes" + "\"" + ":" + "[" + transactionStatusTypes + "]" +
                "}";
    }

    /**
     * method used to get transactions based on search parameters
     */
    public void transactionsGET(String accountId, String accountType, String startDate, String endDate, String searchText, Boolean incBolCredit, Boolean incBolDebit, int size, String transactionStatusTypes) {

        //Set transaction body json

        try {
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();
            Log4Test.info("Environment used for transaction list get is: " + ENV);
            Log4Test.info("AccountID and account Type used for transactions list are: " + accountId + " / " + accountType);

            transactionsResponse = wsPOST(APP_JSON, transactionBodyCreate(accountId, accountType, startDate, endDate, searchText, incBolCredit, incBolDebit, size, transactionStatusTypes),
                    TRANSACTIONS_GET_WS_PATH, loginWSResponse.cookies());

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
            Log4Test.info("Environment used for Account Statement Debit Activity is: " + ENV);

            transactionsResponse = wsGET(TRANSACTION_DOWNLOAD_WS_PATH + transactionId, loginWSResponse.cookies());

        } catch (Exception transactions_downloadDocumentException) {
            Log4Test.test("transactions_downloadDocument error");
            throw transactions_downloadDocumentException;

        }
    }

    /**
     * This method is used to isolate transaction for transaction MATRIX test
     */
    public String transactionMatrixTestTransactionExpose(String channelType, String transactionType, String iban) {
        try {
            Log4Test.info("Starting transaction MATRIX test");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String finalTransactionMatrixSearchJson = "{[\"defaultValue\":\"defaultValue\"]}";

            //login
            //Log4Test.info("Login for transaction MATRIX test");
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();

            //get account list
            //Log4Test.info("Get account list for transaction MATRIX test");
            Response accountListResponseForTransMatrixTest = wsGET(ACCOUNT_LIST_WS_PATH, loginWSResponse.cookies());

            //set account list size
            //Log4Test.info("Get account list size");
            int accountListSize = accountListResponseForTransMatrixTest.jsonPath().getList("").size();

            if (accountListSize == 0) {
                Log4Test.info("Account " + LOGIN_USERNAME + " has no accounts!");
                //Log4Test.info("finalTransactionCreditMatrixSearchJson will be null!");
            } else {
                int i;
                //Log4Test.info("Account " + LOGIN_USERNAME + " has " + accountListSize + " accounts.");

                //starting transaction test
                Log4Test.info("Starting credit matrix transactions search for chanel: " + channelType + ", transaction type " + transactionType + ", and contains iban(" + iban + ")");

                JsonPath matrixResponseJsonPath = null;

                for (i = 0; i < accountListSize; i++) {
                    //reporting some stuff
                    String matrixAccID = accountListResponseForTransMatrixTest.path("[" + i + "].id");
                    String matrixTypeID = accountListResponseForTransMatrixTest.path("[" + i + "].typeId");

                    if (Objects.equals(matrixTypeID, "20") || Objects.equals(matrixTypeID, "26") || Objects.equals(matrixTypeID, "30") || Objects.equals(matrixTypeID, "50")) {

                        //Log4Test.info("Searching for transaction with chanel: " + channelType + ", transaction type " + transactionType + "(iban?  " + iban + ") on account id: " + accountListResponseForTransMatrixTest.path("[" + i + "].id") + " type: " + accountListResponseForTranstMatrixTest.path("[" + i + "].typeId") + " and balance: " + accountListResponseForTransMatrixTest.path("[" + i + "].balance"));

                        //get transactions for selected account
                        String transactions_requestBody_String = transactionBodyCreate(matrixAccID, matrixTypeID, "", "", "", true, true, 999, "\"SUCCESS\",\"PENDING\",\"HOLD\",\"REJECTED\"");

                        Log4Test.info("Environment used for transaction matrix list get is: " + ENV);
                        //Log4Test.info("AccountID and account Type used for transactions credit matrix list are: " + matrixAccID + " / " + matrixTypeID);

                        transactionsResponse = wsPOST(APP_JSON, transactions_requestBody_String, TRANSACTIONS_GET_WS_PATH, loginWSResponse.cookies());

                        if (transactionsResponse.statusCode() == 400) {
                            Log4Test.info("Account is not owned. Carry on.");
                            continue;
                        }
                        //Log4Test.info("Getting transactions completed.");

                        //Log4Test.info("Applying filters: " + channelType + " " + transactionType);
                        //Set initial response
                        JsonPath matrixJsonBody = JsonPath.from(transactionsResponse.body().asString());
                        //Apply first parameter, channelType
                        List matrixResponseP1 = matrixJsonBody.getList("transactions.findAll {it.channel == \"" + channelType + "\" && it.transactionType == \"" + transactionType + "\"}");
                        String matrixResponseP1json = gson.toJson(matrixResponseP1);
                        //Set json after first search
                        //Prepare for final search
                        JsonPath matrixJsonBodyP2 = JsonPath.from(matrixResponseP1json);
                        //Check for transactions that match parameter 3
                        //Log4Test.info("Applying filter iban/shot account: " + iban);
                        //setting json size after second parameter
                        int matrixP2list = matrixJsonBodyP2.getList("").size();
                        //Checking for iban or short account
                        for (int x = 0; x < matrixP2list; x++) {

                            if (Objects.equals(iban, "iban") && matrixJsonBodyP2.getString("[" + x + "].counterparty.iban") != null) {
                                //Log4Test.info("Iban / short account found");
                                Map matrixResponseP3 = matrixJsonBodyP2.get("[" + x + "]");
                                finalTransactionMatrixSearchJson = gson.toJson(matrixResponseP3);
                                matrixResponseJsonPath = JsonPath.from(finalTransactionMatrixSearchJson);
                                Log4Test.test("Selected transaction is: " + finalTransactionMatrixSearchJson);
                                break;

                            } else if (Objects.equals(iban, "noIban")
                                    && Objects.equals(matrixJsonBodyP2.getString("[" + x + "].channel"), "EFT")
                                    && !matrixJsonBodyP2.getString("[" + x + "].transactionCode").equals("EF1A")
                                    && !matrixJsonBodyP2.getString("[" + x + "].transactionCode").equals("EF1C")
                                    && !matrixJsonBodyP2.getString("[" + x + "].transactionCode").equals("EF9A")
                                    && !matrixJsonBodyP2.getString("[" + x + "].transactionCode").equals("EF9C")) {

                                Map matrixResponseP3 = matrixJsonBodyP2.get("[" + x + "]");
                                finalTransactionMatrixSearchJson = gson.toJson(matrixResponseP3);
                                matrixResponseJsonPath = JsonPath.from(finalTransactionMatrixSearchJson);
                                Log4Test.test("Selected transaction is: " + finalTransactionMatrixSearchJson);
                                break;

                            } else if (Objects.equals(iban, "noIban") && matrixJsonBodyP2.getString("[" + x + "].counterparty.iban") == null) {
                                //Log4Test.info("Iban / short account NOT found");
                                Map matrixResponseP3 = matrixJsonBodyP2.get("[" + x + "]");
                                finalTransactionMatrixSearchJson = gson.toJson(matrixResponseP3);
                                matrixResponseJsonPath = JsonPath.from(finalTransactionMatrixSearchJson);
                                Log4Test.test("Selected transaction is: " + finalTransactionMatrixSearchJson);
                                break;

                            } else if (Objects.equals(iban, "EF1A")
                                    && matrixJsonBodyP2.getString("[" + x + "].transactionCode") != null
                                    && matrixJsonBodyP2.getString("[" + x + "].transactionCode").equals("EF1A")) {

                                Map matrixResponseP3 = matrixJsonBodyP2.get("[" + x + "]");
                                finalTransactionMatrixSearchJson = gson.toJson(matrixResponseP3);
                                matrixResponseJsonPath = JsonPath.from(finalTransactionMatrixSearchJson);
                                Log4Test.test("Selected transaction is: " + finalTransactionMatrixSearchJson);
                                break;

                            } else if (Objects.equals(iban, "EF1C")
                                    && matrixJsonBodyP2.getString("[" + x + "].transactionCode") != null
                                    && matrixJsonBodyP2.getString("[" + x + "].transactionCode").equals("EF1C")) {

                                Map matrixResponseP3 = matrixJsonBodyP2.get("[" + x + "]");
                                finalTransactionMatrixSearchJson = gson.toJson(matrixResponseP3);
                                matrixResponseJsonPath = JsonPath.from(finalTransactionMatrixSearchJson);
                                Log4Test.test("Selected transaction is: " + finalTransactionMatrixSearchJson);
                                break;

                            } else if (Objects.equals(iban, "EF9A")
                                    && matrixJsonBodyP2.getString("[" + x + "].transactionCode") != null
                                    && matrixJsonBodyP2.getString("[" + x + "].transactionCode").equals("EF9A")) {

                                Map matrixResponseP3 = matrixJsonBodyP2.get("[" + x + "]");
                                finalTransactionMatrixSearchJson = gson.toJson(matrixResponseP3);
                                matrixResponseJsonPath = JsonPath.from(finalTransactionMatrixSearchJson);
                                Log4Test.test("Selected transaction is: " + finalTransactionMatrixSearchJson);
                                break;

                            } else if (Objects.equals(iban, "EF9C")
                                    && matrixJsonBodyP2.getString("[" + x + "].transactionCode") != null
                                    && matrixJsonBodyP2.getString("[" + x + "].transactionCode").equals("EF9C")) {

                                Map matrixResponseP3 = matrixJsonBodyP2.get("[" + x + "]");
                                finalTransactionMatrixSearchJson = gson.toJson(matrixResponseP3);
                                matrixResponseJsonPath = JsonPath.from(finalTransactionMatrixSearchJson);
                                Log4Test.test("Selected transaction is: " + finalTransactionMatrixSearchJson);
                                break;
                            }
                        }
                        if (matrixResponseJsonPath != null) {
                            break;
                        }
                    }
                }
                if (matrixResponseJsonPath == null) {
                    Log4Test.info("Final json was null. No transaction to match criteria was found.");
                } else {
                    boolean channelCondition = matrixResponseJsonPath.get("channel == \"" + channelType + "\"");
                    boolean transactionTypeCondition = matrixResponseJsonPath.get("transactionType == \"" + transactionType + "\"");

                    if (channelCondition && transactionTypeCondition) {
                        Log4Test.info("Transaction with chanel: " + channelType + ", transaction type " + transactionType + "(iban? " + iban + ") found on account id: " + accountListResponseForTransMatrixTest.path("[" + i + "].id") + " and balance: " + accountListResponseForTransMatrixTest.path("[" + i + "].balance"));

                        if (accountListResponseForTransMatrixTest.path("[" + i + "].nickname") != null) {
                            Log4Test.info("Account nickname is: " + accountListResponseForTransMatrixTest.path("[" + i + "].nickname"));
                        } else if (accountListResponseForTransMatrixTest.path("[" + i + "].productName") != null) {
                            Log4Test.info("Account product name is: " + accountListResponseForTransMatrixTest.path("[" + i + "].productName"));
                        } else {
                            Log4Test.info("No account nickname or product name.");
                        }
                    } else {
                        Log4Test.info("Transaction with chanel: " + channelType + ", transaction type " + transactionType + "(iban? " + iban + ") NOT found on account id: " + accountListResponseForTransMatrixTest.path("[" + i + "].id") + " . Continue searching.");
                    }
                }
                Log4Test.info("Finished matrix transactions search for chanel: " + channelType + ", transaction type " + transactionType + ", and contains iban(" + iban + ")");
                System.out.println(NEW_LINE);
            }
            try {
                if (finalTransactionMatrixSearchJson.equals("{[\"defaultValue\":\"defaultValue\"]}")) {
                    throw new SkipException("Skipped this test because transaction with specified parameters was not found.");
                }
                return finalTransactionMatrixSearchJson;
            } catch (SkipException SkipException) {
                Log4Test.error("Skipped this test because transaction with specified parameters was not found.");
                //Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
                throw SkipException;
            }

        } catch (Exception transactionMatrixTestException) {
            Log4Test.error("Error occurred during matrix test: " + transactionMatrixTestException);
            throw transactionMatrixTestException;
        }
    }

    /**
     * Template map for transaction matrix
     */
    public Map<String, String> transactionsMatrixTemplateCase(JsonPath jsonpath, String iban, String
            avatarUrl, String payTo, String cyberReceipt, String showYouPaidTo, String showYouDeposit, String
                                                                      showYouReceiveFrom, String showYouWithdraw, String transactionCardImageUrl) {

        String attachDocumentConditionString = jsonpath.getString("");
        boolean attachDocumentCondition = attachDocumentConditionString.contains("documentStatus");

        Map<String, String> transactionMatrixTemplateCase = new HashMap<>();

        transactionMatrixTemplateCase.put("counterparty.iban", iban);
        transactionMatrixTemplateCase.put("counterparty.avatarUrl", avatarUrl);
        transactionMatrixTemplateCase.put("showTransactionDate", "true");
        transactionMatrixTemplateCase.put("payTo", payTo);
        transactionMatrixTemplateCase.put("cyberReceipt", cyberReceipt);
        transactionMatrixTemplateCase.put("showYouPaidTo", showYouPaidTo);
        transactionMatrixTemplateCase.put("showYouDeposit", showYouDeposit);
        transactionMatrixTemplateCase.put("showYouReceiveFrom", showYouReceiveFrom);
        transactionMatrixTemplateCase.put("showYouWithdraw", showYouWithdraw);
        transactionMatrixTemplateCase.put("description", "description");
        transactionMatrixTemplateCase.put("transactionCardImageUrl", transactionCardImageUrl);
        if (attachDocumentCondition) {
            transactionMatrixTemplateCase.put("attachDocument", "false");
        } else {
            transactionMatrixTemplateCase.put("attachDocument", "true");
        }

        return transactionMatrixTemplateCase;
    }

    /**
     * This method si used to generate assert conditions for transaction MATRIX test
     */
    public Map transactionMatrixCase(String channelType, String transactionType, String iban, JsonPath jsonpath) {

        Map<String, String> transactionMatrixCase;

        Log4Test.test("Starting assertion for chanel: " + channelType + ", transaction type " + transactionType + ", and contains iban(" + iban + ")");

        if (Objects.equals(channelType, "SWF") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "true",
                    "true",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "SWF") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "SWF") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "false",
                    "false",
                    "false",
                    "true",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "SWF") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "GRN") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "false",
                    "false",
                    "true",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "GRN") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "GRN") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "GRN") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FLCY") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "true",
                    "true",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FLCY") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FLCY") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "false",
                    "false",
                    "false",
                    "true",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FLCY") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "MCH") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "true",
                    "true",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "MCH") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "MCH") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "false",
                    "false",
                    "false",
                    "true",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FOTO") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "true",
                    "true",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FOTO") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FOTO") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "false",
                    "false",
                    "false",
                    "true",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FOTO") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "OPH") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "true",
                    "true",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "OPH") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "OPH") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "true",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "IBK") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "true",
                    "true",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "IBK") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "IBK") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "false",
                    "false",
                    "false",
                    "true",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "IBK") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "SFI") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "false",
                    "false",
                    "false",
                    "true",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "POD") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "POD") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FCT") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FCT") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FIPO") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "FIPO") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "PAID") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "PAID") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "SPID") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "SPID") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "PBA") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "PBA") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "MFM") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "true",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "MFM") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "true",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "ABT") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "ABT") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "ABT") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "ABT") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "EFT") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "EF1A")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "true",
                    "transactionCardImageUrl");

        } else if (Objects.equals(channelType, "EFT") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "EF1C")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "true",
                    "transactionCardImageUrl");

        } else if (Objects.equals(channelType, "EFT") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "EF9A")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    jsonpath.getString("transactionCardImageUrl"),
                    "false",
                    "false",
                    "true",
                    "false",
                    "false",
                    "false",
                    "transactionCardImageUrl");

        } else if (Objects.equals(channelType, "EFT") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "EF9C")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    jsonpath.getString("transactionCardImageUrl"),
                    "false",
                    "false",
                    "true",
                    "false",
                    "false",
                    "false",
                    "transactionCardImageUrl");

        } else if (Objects.equals(channelType, "EFT") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "EFT") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "BATCH") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "false",
                    "true",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "BATCH") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "BATCH") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "AUT") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "true",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "AUT") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "AUT") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "SEP") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "true",
                    "true",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "SEP") && Objects.equals(transactionType, "DEBIT") && Objects.equals(iban, "noIban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert");

        } else if (Objects.equals(channelType, "SEP") && Objects.equals(transactionType, "CREDIT") && Objects.equals(iban, "iban")) {

            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "counterparty.iban",
                    "skipThisAssert",
                    "true",
                    "false",
                    "false",
                    "false",
                    "true",
                    "false",
                    "skipThisAssert");
        } else {
            //skip everything
            transactionMatrixCase = transactionsMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert");
        }

        return transactionMatrixCase;
    }

    /**
     * This method is used to isolate transaction for CREDIT CARD transaction MATRIX test
     */
    public String transactionCreditMatrixTestTransactionExpose(String internalTransactionTestCaseType, String
            transactionType, String[] internalTransactionTypeIds) {
        try {
            Log4Test.test("Starting transaction CREDIT MATRIX test");
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String finalTransactionCreditMatrixSearchJson = "{[\"defaultValue\":\"defaultValue\"]}";

            //login
            //Log4Test.info("Login for transaction MATRIX test");
            LoginWS loginWSResponseForGet = new LoginWS();
            loginWSResponseForGet.loginWSResponse();

            //get account list
            //Log4Test.info("Get account list for transaction MATRIX test");
            Response accountListResponseForTransCreditMatrixTest = wsGET(ACCOUNT_LIST_WS_PATH, loginWSResponse.cookies());

            //set account list size
            //Log4Test.info("Get account list size");
            int accountListSize = accountListResponseForTransCreditMatrixTest.jsonPath().getList("").size();

            if (accountListSize == 0) {
                Log4Test.info("Account " + LOGIN_USERNAME + " has no accounts!");
                //Log4Test.info("finalTransactionCreditMatrixSearchJson will be null!");
            } else {
                //Log4Test.info("Account " + LOGIN_USERNAME + " has " + accountListSize + " accounts.");

                //starting transaction test
                Log4Test.info("Starting credit matrix transactions search for chanel: " + internalTransactionTestCaseType + ", transaction type " + transactionType);

                JsonPath matrixResponseJsonPath = null;
                for (int i = 0; i < accountListSize; i++) {
                    //reporting some stuff
                    String accountId_list = accountListResponseForTransCreditMatrixTest.path("[" + i + "].accountId");
                    String matrixTypeID = accountListResponseForTransCreditMatrixTest.path("[" + i + "].typeId");
                    String matrixProductID = accountListResponseForTransCreditMatrixTest.path("[" + i + "].productId");

                    if (Objects.equals(matrixTypeID, "20") && Objects.equals(matrixProductID, "133")) {

                        //get credit card ws response
                        Response creditCardResponseForTransCreditMatrixTest = wsGET(CARDS_CREDIT_WS_PATH, loginWSResponse.cookies());

                        if (!creditCardResponseForTransCreditMatrixTest.getBody().asString().equals("[]")) {

                            String accountId_cc = creditCardResponseForTransCreditMatrixTest.path("[0].accountId");
                            String ccAccountId = creditCardResponseForTransCreditMatrixTest.path("[0].ccAccountId");
                            String ccProductId = creditCardResponseForTransCreditMatrixTest.path("[0].ccProductId");
                            String ccCurrency = creditCardResponseForTransCreditMatrixTest.path("[0].currency");
                            String balance = creditCardResponseForTransCreditMatrixTest.path("[0].amount");

                            if (accountId_list.equals(accountId_cc)) {
                                //Log4Test.info("Searching for transaction with chanel: " + channelType + ", transaction type " + transactionType + "(iban?  " + iban + ") on account id: " + accountListResponseForTransCreditMatrixTest.path("[" + i + "].id") + " type: " + accountListResponseForTransCreditMatrixTest.path("[" + i + "].typeId") + " and balance: " + accountListResponseForTransCreditMatrixTest.path("[" + i + "].balance"));

                                //get transactions for selected account
                                String transactions_requestBody_String = transactionCreditCardBodyCreate(accountId_cc, "322", ccAccountId, "554", ccProductId, ccCurrency, "133", "", "", "", 999, "\"SUCCESS\",\"HOLD\"");

                                Log4Test.info("Environment used for transaction credit matrix list get is: " + ENV);
                                //Log4Test.info("AccountID and account Type used for transactions credit matrix list are: " + matrixAccID + " / " + matrixTypeID);

                                transactionsResponse = wsPOST(APP_JSON, transactions_requestBody_String, TRANSACTIONS_CREDIT_CARD_GET_WS_PATH, loginWSResponse.cookies());
                                if (transactionsResponse.statusCode() == 400) {
                                    Log4Test.info("Account is not owned. Carry on.");
                                    continue;
                                }
                                //Log4Test.info("Getting transactions completed.");

                                //Log4Test.info("Applying filters: " + channelType + " " + transactionType);
                                //Set initial response
                                JsonPath matrixJsonBody = JsonPath.from(transactionsResponse.body().asString());
                                //Apply first parameter, channelType
                                List matrixResponseP1 = matrixJsonBody.getList("transactions.findAll {it.transactionType == \"" + transactionType + "\"}");
                                String matrixResponseP1json = gson.toJson(matrixResponseP1);
                                //Prepare for final search
                                JsonPath matrixJsonBodyP2 = JsonPath.from(matrixResponseP1json);
                                //setting json size after first search
                                int matrixP2list = matrixJsonBodyP2.getList("").size();
                                //Checking for matching internalTransactionType and transactionType
                                for (int x = 0; x < matrixP2list; x++) {

                                    for (String internalTransactionTypeId : internalTransactionTypeIds) {

                                        if (matrixJsonBodyP2.getString("[" + x + "].internalTransactionType").equals(internalTransactionTypeId)
                                                && matrixJsonBodyP2.getString("[" + x + "].transactionType").equals(transactionType)) {

                                            Map matrixResponseP3 = matrixJsonBodyP2.get("[" + x + "]");
                                            finalTransactionCreditMatrixSearchJson = gson.toJson(matrixResponseP3);
                                            matrixResponseJsonPath = JsonPath.from(finalTransactionCreditMatrixSearchJson);
                                            Log4Test.test("Selected transaction is: " + finalTransactionCreditMatrixSearchJson);
                                            break;
                                        }
                                        if (matrixResponseJsonPath != null) {
                                            break;
                                        }
                                    }
                                    if (matrixResponseJsonPath != null) {
                                        break;
                                    }
                                }

                                Log4Test.info("Finished credit matrix transactions search for internalTransactionId test case: " + internalTransactionTestCaseType + ", transaction type " + transactionType);

                                if (matrixResponseJsonPath == null) {
                                    Log4Test.info("Final json was null. No transaction to match criteria was found.");
                                } else {
                                    String conditionString = Arrays.toString(internalTransactionTypeIds);
                                    boolean internalTransactionIdCondition = conditionString.contains(matrixResponseJsonPath.getString("internalTransactionType"));
                                    boolean transactionTypeCondition = matrixResponseJsonPath.get("transactionType == \"" + transactionType + "\"");

                                    if (internalTransactionIdCondition && transactionTypeCondition) {
                                        Log4Test.info("Transaction for internalTransactionId test case: " + internalTransactionTestCaseType + ", transaction type " + transactionType + " found on account id: " + accountListResponseForTransCreditMatrixTest.path("[" + i + "].id") + " and balance: " + balance);

                                        if (accountListResponseForTransCreditMatrixTest.path("[" + i + "].nickname") != null) {
                                            Log4Test.info("Account nickname is: " + accountListResponseForTransCreditMatrixTest.path("[" + i + "].nickname"));
                                        } else if (accountListResponseForTransCreditMatrixTest.path("[" + i + "].productName") != null) {
                                            Log4Test.info("Account product name is: " + accountListResponseForTransCreditMatrixTest.path("[" + i + "].productName"));
                                        } else {
                                            Log4Test.info("No account nickname or product name.");
                                        }
                                        break;
                                    }
                                }
                            } else {
                                Log4Test.error("Not the same acc id for credit card account and list product 133 account !!!!");
                            }
                        } else {
                            Log4Test.error("NO CREDIT CARD!!");
                        }
                    }
                }
                System.out.println(NEW_LINE);
            }
            try {
                if (finalTransactionCreditMatrixSearchJson.equals("{[\"defaultValue\":\"defaultValue\"]}")) {
                    System.out.println(NEW_LINE);
                    throw new SkipException("Skipped this test because transaction with specified parameters was not found.");
                }
                return finalTransactionCreditMatrixSearchJson;
            } catch (SkipException SkipException) {
                Log4Test.error("Skipped this test because transaction with specified parameters was not found.");
                //Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
                throw SkipException;
            }

        } catch (Exception transactionCreditMatrixTestException) {
            Log4Test.error("Error occurred during credit matrix test: " + transactionCreditMatrixTestException);
            throw transactionCreditMatrixTestException;
        }
    }

    /**
     * Template map for CREDIT CARD transaction matrix
     */
    public Map<String, String> transactionsCreditCardMatrixTemplateCase(JsonPath jsonpath, String avatarUrl, String
            showYouPaidTo, String showYouDeposit, String showYouReceiveFrom, String showYouWithdraw, String
                                                                                transactionCardImageUrl, String originalAmount, String originalExchangeRate, String originalCurrency) {

        String attachDocumentConditionString = jsonpath.getString("");
        boolean attachDocumentCondition = attachDocumentConditionString.contains("documentStatus");

        Map<String, String> transactionsCreditCardMatrixTemplateCase = new HashMap<>();

        transactionsCreditCardMatrixTemplateCase.put("counterparty.avatarUrl", avatarUrl);
        transactionsCreditCardMatrixTemplateCase.put("description", "description");

        if (attachDocumentCondition) {
            transactionsCreditCardMatrixTemplateCase.put("attachDocument", "false");
        } else {
            transactionsCreditCardMatrixTemplateCase.put("attachDocument", "true");
        }

        transactionsCreditCardMatrixTemplateCase.put("showYouPaidTo", showYouPaidTo);
        transactionsCreditCardMatrixTemplateCase.put("showYouDeposit", showYouDeposit);
        transactionsCreditCardMatrixTemplateCase.put("showYouReceiveFrom", showYouReceiveFrom);
        transactionsCreditCardMatrixTemplateCase.put("showYouWithdraw", showYouWithdraw);
        transactionsCreditCardMatrixTemplateCase.put("originalAmount", originalAmount);
        transactionsCreditCardMatrixTemplateCase.put("originalExchangeRate", originalExchangeRate);
        transactionsCreditCardMatrixTemplateCase.put("originalCurrency", originalCurrency);
        transactionsCreditCardMatrixTemplateCase.put("transactionCardImageUrl", transactionCardImageUrl);

        if (Objects.equals(jsonpath.getString("transactionCardImageUrl"), "https://ro-cdn-" + ENV + ".rbro.rbg.cc/portal/img/cards/fallback.png")) {
            transactionsCreditCardMatrixTemplateCase.put("cardNumber", "skipThisAssert");
            transactionsCreditCardMatrixTemplateCase.put("cardName", "skipThisAssert");

        } else if (transactionCardImageUrl.equals("transactionCardImageUrl") && !Objects.equals(jsonpath.getString("transactionCardImageUrl"), "https://ro-cdn-" + ENV + ".rbro.rbg.cc/portal/img/cards/fallback.png")) {
            transactionsCreditCardMatrixTemplateCase.put("cardNumber", "cardNumber");
            transactionsCreditCardMatrixTemplateCase.put("cardName", "cardName");

        } else if (transactionCardImageUrl.equals("skipThisAssert")) {
            transactionsCreditCardMatrixTemplateCase.put("cardNumber", "skipThisAssert");
            transactionsCreditCardMatrixTemplateCase.put("cardName", "skipThisAssert");
        }

        return transactionsCreditCardMatrixTemplateCase;
    }


    /**
     * This method is used to generate assert conditions for CREDIT CARD transaction MATRIX test
     */
    public Map transactionCreditMatrixCase(String internalTransactionTestCaseType, String transactionType, JsonPath
            jsonpath) {

        Map<String, String> transactionCreditCardMatrixCase;

        Log4Test.info("Starting assertion for internalTransactionTestCaseType: " + internalTransactionTestCaseType + ", transaction type " + transactionType);

        if (Objects.equals(internalTransactionTestCaseType, "320") && Objects.equals(transactionType, "DEBIT")) {

            transactionCreditCardMatrixCase = transactionsCreditCardMatrixTemplateCase(jsonpath,
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "false",
                    "true",
                    "transactionCardImageUrl",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert");

        } else if (Objects.equals(internalTransactionTestCaseType, "320") && Objects.equals(transactionType, "CREDIT")) {

            transactionCreditCardMatrixCase = transactionsCreditCardMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "false",
                    "false",
                    "true",
                    "false",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert");

        } else if (Objects.equals(internalTransactionTestCaseType, "3110") && Objects.equals(transactionType, "DEBIT")) {

            transactionCreditCardMatrixCase = transactionsCreditCardMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "true",
                    "false",
                    "false",
                    "false",
                    "transactionCardImageUrl",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert");

        } else if (Objects.equals(internalTransactionTestCaseType, "3110") && Objects.equals(transactionType, "CREDIT")) {

            transactionCreditCardMatrixCase = transactionsCreditCardMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "false",
                    "false",
                    "true",
                    "false",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert");

        } else if (Objects.equals(internalTransactionTestCaseType, "9375") && Objects.equals(transactionType, "DEBIT")) {

            transactionCreditCardMatrixCase = transactionsCreditCardMatrixTemplateCase(jsonpath,
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "true",
                    "false",
                    "false",
                    "false",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert");

        } else if (Objects.equals(internalTransactionTestCaseType, "9375") && Objects.equals(transactionType, "CREDIT")) {

            transactionCreditCardMatrixCase = transactionsCreditCardMatrixTemplateCase(jsonpath,
                    "https://ro-cdn-" + ENV + ".rbro.rbg.cc/img/rbro-icon-copy-2@3x.png",
                    "false",
                    "false",
                    "true",
                    "false",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert");

        } else if (Objects.equals(internalTransactionTestCaseType, "6120") && Objects.equals(transactionType, "CREDIT")) {

            transactionCreditCardMatrixCase = transactionsCreditCardMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "false",
                    "false",
                    "true",
                    "false",
                    "transactionCardImageUrl",
                    "originalAmount",
                    "originalExchangeRate",
                    "originalCurrency");

        } else if (Objects.equals(internalTransactionTestCaseType, "9700") && Objects.equals(transactionType, "DEBIT")) {

            transactionCreditCardMatrixCase = transactionsCreditCardMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "true",
                    "false",
                    "false",
                    "false",
                    "transactionCardImageUrl",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert");

        } else {

            transactionCreditCardMatrixCase = transactionsCreditCardMatrixTemplateCase(jsonpath,
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert",
                    "skipThisAssert");
        }
        return transactionCreditCardMatrixCase;
    }
}
