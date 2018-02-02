package REST_Framework;

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
     * Set env settings
     */
    // test // dev // pre
    protected static final String env = "test";
    /**
     * Set URL
     */
    protected static final String servicesPath = "/services/";
    protected static final String uRL = "https://ro-" + env + ".rbro.rbg.cc";
    // --Commented out by Inspection (20-Dec-17 13:05):public static int port = 8080;
    /**
     * Set username and password
     */
    // 1 = vn-84-vly // 2 = testpf08 // 3 = testpf03 // 4 - testpf01 // 5 - testpf09 // 6 - testpf12 // 7 - alandala70
    protected static final Object loginUsername = loginUsername_map().get( 2 );
    protected static final String loginPassword = "test1234";
    protected static final String channelSubtype = "ROATH"; // SMATH or ROATH

    /**
     * set accounts for transactions tests
     * Note: make sure that account ID can be found in account list for the user used at log in!
     */
    static final String accountID_20 = "5161502"; // 5161502 - vn-84-vly
    static final String accountID_26 = "0011532971"; // 0011532971 - vn-84-vly
    static final String accountID_30 = "18036413"; // 18036413 - vn-84-vly
    static final String accountID_50 = "000018058607"; // 000018058607 - vn-84-vly

    protected static final String accountID_custom = "0011532971"; // 5161502 - vn-84-vly / 20
    protected static final String accountType_custom = "26"; // 20,26,30,50

    //5540642_20170831_1 - testpf08 // 11023130_20170901_3 - vn-84-vly
    protected static final String transactionIdForDownload = "5540642_20170831_1";
    /**
     * Cyber receipt variables
     */
    // 0007378341 (euroig) - testpf08 // 0005540642 (ancasalariu) - testpf08 // 0015480271 - testpf01(prelive)
    protected static final String cyberReceiptAccId = "0015480271";
    // cc67f647-9a61-11e7-9b01-f939de091570 (euro) - testpf08 // 989e9154-9d51-11e7-9139-b52709ae3d40 (ron)- testpf08 // ad7790d0-964a-11e7-b59d-3305c7b6331d - testpf01(prelive)
    protected static final String cyberReceiptCassId = "ad7790d0-964a-11e7-b59d-3305c7b6331d";
    protected static final String cyberReceiptPostingDate = "20150323";
    protected static String cyberReceiptDocumentId = "0002206091-1517323017247";
    /**
     * Account ID for account statement and start end date
     */
    //credit
    // 10026961 - testpf08 // 12506241 - vintcod1981
    protected static final String accountStatement_accountId_credit = "10026961";
    protected static final String accountStatement_startDate_credit = "2014-11-17";
    protected static final String accountStatement_endDate_credit = "2018-01-17";
    // 14251466 for 10026961 - testpf08 // 23383750 - vintcod1981
    protected static final String accountStatement_field_credit = "14251466";

    // debit
    // 0005161502 - vn-84-vly // 0005540642 - testpf08 // 0010868927 - vintcod1981 // 0015480271 - testpf01(prelive)
    protected static final String accountStatement_accountId_debit = "0005540642";
    protected static final String accountStatement_accountType_debit = "20";
    protected static final String accountStatement_startDate_debit = "2017-10-18";
    protected static final String accountStatement_endDate_debit = "2018-01-15";

    //loan
    // 000018058605 - vn-84-vly ; 000009652743 - alandala70(prelive)
    protected static final String loan_accountId = "000009652743";
    // 0002052120-1515141646028 - vn-84-vly
    protected static String loan_documentId = "0002052120-1515141646028";

}
