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
        basePath = SERVICES_PATH;
        baseURI = URL;
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
    protected static final String TIME_STAMP = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    protected static final String NEW_LINE = "\r\n"; //Set break line / new line / br
    protected static final String APP_JSON = "application/json;charset=utf-8";
    protected static final String MULTI_PART = "multipart/form-data";
    protected static final int DOCUMENT_RETRY_TIMES = 5;

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
        loginUsername_map.put(8, "gabist72");
        loginUsername_map.put(9, "vintcod1981");

        return loginUsername_map;
    }

    /**
     * Set ENV MAP
     */
    public static Map ENV_map() {
        Map<Integer, String> ENV_map = new HashMap<>();
        ENV_map.put(1, "test");
        ENV_map.put(2, "pre");
        ENV_map.put(3, "dev");

        return ENV_map;
    }

    /**
     * Set channel subtype map
     */
    public static Map channelSubtype_map() {
        Map<Integer, String> channelSubtype_map = new HashMap<>();
        channelSubtype_map.put(1, "ROATH");
        channelSubtype_map.put(2, "SMATH");

        return channelSubtype_map;
    }

    /**
     * Set ws paths
     */
    protected final String WS_TIME = "utils-service/v1/now";
    protected final String LOGIN_WS_PATH = "auth/v2/login";
    protected final String USER_INFO_WS_PATH = "auth/v1/userInfo";
    protected final String FX_RATS_LIST_WS_PATH = "fx-rates-ws/v1/list";
    protected final String FX_RATS_SELL_WS_PATH = "fx-rates-ws/v1/sell/";
    protected final String FX_RATS_BUY_WS_PATH = "fx-rates-ws/v1/buy/";
    protected final String INTEREST_RATS_LIST_WS_PATH = "interest-rates/v1/interest-rates";
    protected final String USER_PROFILE_WS_PATH = "userprofile-ws/v1/user/profile";
    protected static final String USER_PROFILE_AVATAR_UPLOAD_WS_PATH = "userprofile-ws/v1/user/avatar";
    protected final String TRANSACTIONS_DATE_WS_PATH = "transactions-service/v1/transaction-dates";
    protected final String TRANSACTIONS_GET_WS_PATH = "transactions-service/v1/transactions";
    protected final String TRANSACTIONS_CREDIT_CARD_GET_WS_PATH = "transactions-service/v1/transactions/credit";
    protected final String TRANSACTION_DOWNLOAD_WS_PATH = "transactions-service/v1/download-document/";
    protected final String ACCOUNT_STATEMENT_CREDIT_ACTIVITY_WS_PATH = "account-statement/v1/credit/report/activity";
    protected final String ACCOUNT_STATEMENT_CREDIT_DOWNLOAD_WS_PATH = "account-statement/v1/credit/report/download";
    protected final String ACCOUNT_STATEMENT_DEBIT_ACTIVITY_WS_PATH = "account-statement/v1/debit/report/activity";
    protected final String ACCOUNT_STATEMENT_DEBIT_DOWNLOAD_WS_PATH = "account-statement/v1/debit/report/download";
    protected final String ACCOUNT_STATEMENT_LOAN_GRAPHIC_DETAILS_WS_PATH = "account-statement/v1/loan/payment-graphic";
    protected final String ACCOUNT_STATEMENT_LOAN_GRAPHIC_GENERATE_WS_PATH = "account-statement/v1/loan/payment-graphic/generate";
    protected final String ACCOUNT_STATEMENT_LOAN_GRAPHIC_FILE_STATUS_WS_PATH = "account-statement/v1/loan/payment-graphic/status/";
    protected final String ACCOUNT_STATEMENT_LOAN_GRAPHIC_FILE_DOWNLOAD_WS_PATH = "account-statement/v1/loan/payment-graphic/download/";
    protected final String CYBER_RECEIPT_GENERATE_DOCUMENT_WS_PATH = "payments-ws/v1/cyber-receipt/generate-transaction";
    protected final String CYBER_RECEIPT_DOCUMENT_STATUS_WS_PATH = "payments-ws/v1/cyber-receipt/status/";
    protected final String CYBER_RECEIPT_DOCUMENT_DOWNLOAD_WS_PATH = "payments-ws/v1/cyber-receipt/download/";
    protected final String ACCOUNT_LIST_WS_PATH = "accounts-ws/v2/accounts/list";
    protected final String CARDS_CREDIT_WS_PATH = "cards-ws/v2/cards/credit";
    protected final String CARDS_DEBITT_WS_PATH = "cards-ws/v2/cards/debit";


}
