package REST_Framework;

import static REST_Core.TestBase.ENV_map;
import static REST_Core.TestBase.channelSubtype_map;
import static REST_Core.TestBase.loginUsername_map;

public class TestData {

    /**
     * // ////////////////////////////////////////////////////////////////////////////////
     * // ////////////////////////////////////////////////////////////////////////////////
     * // ////////////////////////////////////////////////////////////////////////////////
     * // ////////////////////////////// EDITABLE CONFIGS ////////////////////////////////
     * // ////////////////////////////////////////////////////////////////////////////////
     * // ////////////////////////////////////////////////////////////////////////////////
     * // ////////////////////////////////////////////////////////////////////////////////
     * <p>
     * <p>
     * <p>
     * Set ENV settings
     */
    // 1 - test // 2 - pre // 3 - dev
    protected static final Object ENV = ENV_map().get(1);
    /**
     * Set URL
     */
    protected static final String SERVICES_PATH = "/services/";
    protected static final String URL = "https://ro-" + ENV + ".rbro.rbg.cc";
    // --Commented out by Inspection (20-Dec-17 13:05):public static int port = 8080;
    /**
     * Set username and password
     */
    // 1 = vn-84-vly // 2 = testpf08 // 3 = testpf03 // 4 - testpf01 // 5 - testpf09 // 6 - testpf12 // 7 - alandala70 // 8 - gabist72 // 9 - vintcod1981
    protected static final Object LOGIN_USERNAME = loginUsername_map().get(2);
    protected static final String LOGIN_PASSWORD = "test1234";
    // 1 - ROATH // 2 - SMATH
    protected static final Object CHANNEL_SUBTYPE = channelSubtype_map().get(1);

    /**
     * set accounts for transactions tests
     * Note: make sure that account ID can be found in account list for the user used at log in!
     */
    static final String ACCOUNT_ID_20 = "5161502"; // 5161502 - vn-84-vly
    static final String ACCOUNT_ID_26 = "0011532971"; // 0011532971 - vn-84-vly
    static final String ACCOUNT_ID_30 = "18036413"; // 18036413 - vn-84-vly
    static final String ACCOUNT_ID_50 = "000018058607"; // 000018058607 - vn-84-vly

    protected static final String ACCOUNT_ID_CUSTOM = "0011532971"; // 5161502 - vn-84-vly / 20
    protected static final String ACCOUNT_TYPE_CUSTOM = "26"; // 20,26,30,50

    //5540642_20170831_1 - testpf08 // 11023130_20170901_3 - vn-84-vly
    protected static final String TRANSACTION_ID_FOR_DOWNLOAD = "5540642_20170831_1";
    /**
     * Cyber receipt variables
     */
    // 0007378341 (euroig) - testpf08 // 0005540642 (ancasalariu) - testpf08 // 0015480271 - testpf01(prelive) // 0005161502 - vn-84-vly
    protected static final String CYBER_RECEIPT_ACC_ID = "0005161502";
    // cc67f647-9a61-11e7-9b01-f939de091570 (euro) - testpf08 // 989e9154-9d51-11e7-9139-b52709ae3d40 (ron)- testpf08 // ad7790d0-964a-11e7-b59d-3305c7b6331d - testpf01(prelive)
    // e25d4db2-ce5f-11e7-9b8a-7fcd1683291a - vn-84-vly (salariu) //
    protected static final String CYBER_RECEIPT_CASS_ID = "e25d4db2-ce5f-11e7-9b8a-7fcd1683291a";
    protected static final String CYBER_RECEIPT_POSTING_DATE = "20171120"; // 20170803 - 989e9154-9d51-11e7-9139-b52709ae3d40 (testpf08)// 20171120 - e25d4db2-ce5f-11e7-9b8a-7fcd1683291a (vn-84-vly)
    protected static String CYBER_RECEIPT_DOCUMENT_ID = "0002206091-1517323017247";
    /**
     * Account ID for account statement and start end date
     */
    //credit
    // 10026961 - testpf08 // 12506241 - vintcod1981 // 6851623 - gabist72(prelive) // 0012506241 - vintcod1981(prelive)
    protected static final String ACCOUNT_STATEMENT_ACCOUNT_ID_CREDIT = "0012506241";
    protected static final String ACCOUNT_STATEMENT_START_DATE_CREDIT = "2014-02-17";
    protected static final String ACCOUNT_STATEMENT_END_DATE_CREDIT = "2018-02-20";
    // 14251466 for 10026961 - testpf08 // 23383750 - vintcod1981
    protected static final String ACCOUNT_STATEMENT_FILE_ID_CREDIT = "14251466";

    // debit
    // 0005161502 - vn-84-vly // 0005540642 - testpf08 // 0010868927 - vintcod1981 // 0015480271 - testpf01(prelive)
    protected static final String ACCOUNT_STATEMENT_ACCOUNT_ID_DEBIT = "0015480271";
    protected static final String ACCOUNT_STATEMENT_ACCOUNT_TYPE_DEBIT = "20";
    protected static final String ACCOUNT_STATEMENT_START_DATE_DEBIT = "2017-10-18";
    protected static final String ACCOUNT_STATEMENT_END_DATE_DEBIT = "2018-01-15";

    //loan
    // 000018058605 - vn-84-vly ; 000009652743 - alandala70(prelive)
    protected static final String LOAN_ACCOUNT_ID = "000018058607";
    // 0002052120-1515141646028 - vn-84-vly
    protected static String LOAN_DOCUMENT_ID = "0002052120-1515141646028";

}
