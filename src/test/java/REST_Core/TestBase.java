package REST_Core;

import REST_Framework.TestData;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.basePath;
import static com.jayway.restassured.RestAssured.baseURI;

public abstract class TestBase extends TestData {

    /**
     * This method is used to set rest assured base elements before everything
     */
    @BeforeClass(alwaysRun = true)
    protected void setUP() {
        /*
          this method is configuring the basic URL and service path for all WS
         */
        basePath = servicesPath;
        baseURI = uRL;
        //RestAssured.port = port;
        RestAssured.useRelaxedHTTPSValidation();
    }

    /**
     * /**
     * // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * // ////////////////////////////// DO NOT CHANGE THIS. THOSE ARE WS PATHS AND OTHER NON EDITABLE CONFIGS ////////////////////////////////
     * // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * <p>
     * <p>
     * Set base configs
     */
    protected static final SoftAssert softAssert = new SoftAssert();
    protected static final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    protected static final String newline = "\r\n"; //Set break line / new line / br
    protected static final String appJSON = "application/json;charset=utf-8";
    protected static final String multiPart = "multipart/form-data";
    protected static final int documentRetryTimes = 5;

    /**
     * Set login user name MAP
     */
    public static Map loginUsername_map() {
        Map<Integer, String> loginUsername_map = new HashMap<>();
        loginUsername_map.put(1, "vn-84-vly");
        loginUsername_map.put(2, "testpf08");
        loginUsername_map.put(3, "testpf03");
        loginUsername_map.put(4, "testpf01");
        loginUsername_map.put(5, "testpf09");
        loginUsername_map.put(6, "testpf12");
        loginUsername_map.put(7, "alandala70");
        return loginUsername_map;
    }

    /**
     * Set ws paths
     */
    protected final String wsTime = "utils-service/v1/now";
    protected final String loginWSpath = "auth/v2/login";
    protected final String userInfoWSpath = "auth/v1/userInfo";
    protected final String fxRatsListWSpath = "fx-rates-ws/v1/list";
    protected final String fxRatsSellWSpath = "fx-rates-ws/v1/sell/";
    protected final String fxRatsBuyWSpath = "fx-rates-ws/v1/buy/";
    protected final String interestRatsListWSpath = "interest-rates/v1/interest-rates";
    protected final String userProfileWSpath = "userprofile-ws/v1/user/profile";
    protected static final String userProfileAvatarUloadWSpath = "userprofile-ws/v1/user/avatar";
    protected final String transactionsDateWSpath = "transactions-service/v1/transaction-dates";
    protected final String transactionsGetWSpath = "transactions-service/v1/transactions";
    protected final String transactionDownloadWSpath = "transactions-service/v1/download-document/";
    protected final String accountStatementCreditActivityWSpath = "account-statement/v1/credit/report/activity";
    protected final String accountStatementCreditDownloadWSpath = "account-statement/v1/credit/report/download";
    protected final String accountStatementDebitActivityWSpath = "account-statement/v1/debit/report/activity";
    protected final String accountStatementDebitDownloadWSpath = "account-statement/v1/debit/report/download";
    protected final String accountStatementLoanGraphicDetailsWSpath = "account-statement/v1/loan/payment-graphic";
    protected final String accountStatementLoanGraphicGenerateWSpath = "account-statement/v1/loan/payment-graphic/generate";
    protected final String accountStatementLoanGraphicFileStatusWSpath = "account-statement/v1/loan/payment-graphic/status/";
    protected final String accountStatementLoanGraphicFileDownloadWSpath = "account-statement/v1/loan/payment-graphic/download/";
    protected final String cyberReceiptGenerateDocumentWSpath = "payments-ws/v1/cyber-receipt/generate-transaction";
    protected final String cyberReceiptDocumentStatusWSpath = "payments-ws/v1/cyber-receipt/status/";
    protected final String cyberReceiptDocumentDownloadWSpath = "payments-ws/v1/cyber-receipt/download/";
    protected final String accountListWSpath = "accounts-ws/v1/accounts/list";


}
