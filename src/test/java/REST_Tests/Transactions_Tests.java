package REST_Tests;

import REST_Core.TestBase;
import REST_Framework.TestData_DataProvider;
import REST_WS.TransactionsWS;
import REST_utils.Log4Test;
import com.jayway.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static REST_Framework.CommonTask.*;
import static REST_WS.TransactionsWS.transactionsResponse;

public class Transactions_Tests extends TestBase {

    private final TransactionsWS transactionsResponseTest = new TransactionsWS();

    /**
     * wip
     */
    @Test(dataProvider = "accountsForTransactions", dataProviderClass = TestData_DataProvider.class)
    public void transactions_1_DateGET_allAccounts(String accountId, String accountType) {
        transactionsResponseTest.transactionsDateGET(accountId, accountType);
    }

    /**
     * wip
     */
    @Test
    public void transactions_2_DateGET_custom() {
        transactionsResponseTest.transactionsDateGET(ACCOUNT_ID_CUSTOM, ACCOUNT_TYPE_CUSTOM);
    }

    /**
     * Transaction download document for selected id
     */
    @Test
    public void transactions_3_downloadDocument() throws Exception {
        //get response
        String fileType = "jpg";
        transactionsResponseTest.transactions_downloadDocument(TRANSACTION_ID_FOR_DOWNLOAD);
        //set download path and file name
        String downloadPathAndFileName_Transaction = "WS_Downloads/transactions_3_downloadDocument_" + TRANSACTION_ID_FOR_DOWNLOAD + "." + fileType;
        //download file
        downloadFileFromWS(transactionsResponse, downloadPathAndFileName_Transaction);
        Log4Test.info("Document downloaded with name: " + downloadPathAndFileName_Transaction);
    }

    /**
     * Transactions MATRIX test
     */
    @Test(dataProvider = "transactionsMatrixTest", dataProviderClass = TestData_DataProvider.class, groups = {"all", "transactionsMatrix"})
    public void transactions_4_matrixTest(String chanelType, String transType, String iban) {
        String transactionJson = transactionsResponseTest.transactionMatrixTestTransactionExpose(chanelType, transType, iban);
        JsonPath transactionMatrixTestJsonPath = JsonPath.from(transactionJson);
        Map transactionMatrixCaseMap = transactionsResponseTest.transactionMatrixCase(chanelType, transType, iban, transactionMatrixTestJsonPath);

        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionMatrixTestJsonPath,"channel"), chanelType), "Expected channel " + chanelType + " but found: " + getStringFromJsonPath(transactionMatrixTestJsonPath,"channel"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionMatrixTestJsonPath,"transactionType"), transType), "Expected transactionType " + transType + " but found: " + getStringFromJsonPath(transactionMatrixTestJsonPath,"transactionType"));
        assertJsonKeyExistenceForString(transactionJson, getStringFromMapList(transactionMatrixCaseMap,"counterparty.iban"));
        assertJsonKeyExistenceForString(transactionJson, getStringFromMapList(transactionMatrixCaseMap,"description"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionMatrixTestJsonPath,"counterparty.avatarUrl"), getStringFromMapList(transactionMatrixCaseMap,"counterparty.avatarUrl")), "Expected avatarUrl " + getStringFromMapList(transactionMatrixCaseMap,"counterparty.avatarUrl") + " but found: " + getStringFromJsonPath(transactionMatrixTestJsonPath,"counterparty.avatarUrl"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionMatrixTestJsonPath,"payTo"), getStringFromMapList(transactionMatrixCaseMap,"payTo")), "Expected payTo " + getStringFromMapList(transactionMatrixCaseMap,"payTo") + " but found: " + getStringFromJsonPath(transactionMatrixTestJsonPath,"payTo"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionMatrixTestJsonPath,"cyberReceipt"), getStringFromMapList(transactionMatrixCaseMap,"cyberReceipt")), "Expected cyberReceipt " + getStringFromMapList(transactionMatrixCaseMap,"cyberReceipt") + " but found: " + getStringFromJsonPath(transactionMatrixTestJsonPath,"cyberReceipt"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionMatrixTestJsonPath,"attachDocument"), getStringFromMapList(transactionMatrixCaseMap,"attachDocument")), "Expected attachDocument " + getStringFromMapList(transactionMatrixCaseMap,"attachDocument") + " but found: " + getStringFromJsonPath(transactionMatrixTestJsonPath,"attachDocument"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionMatrixTestJsonPath,"showYouPaidTo"), getStringFromMapList(transactionMatrixCaseMap,"showYouPaidTo")), "Expected showYouPaidTo " + getStringFromMapList(transactionMatrixCaseMap,"showYouPaidTo") + " but found: " + getStringFromJsonPath(transactionMatrixTestJsonPath,"showYouPaidTo"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionMatrixTestJsonPath,"showYouDeposit"), getStringFromMapList(transactionMatrixCaseMap,"showYouDeposit")), "Expected showYouDeposit " + getStringFromMapList(transactionMatrixCaseMap,"showYouDeposit") + " but found: " + getStringFromJsonPath(transactionMatrixTestJsonPath,"showYouDeposit"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionMatrixTestJsonPath,"showYouReceiveFrom"), getStringFromMapList(transactionMatrixCaseMap,"showYouReceiveFrom")), "Expected showYouReceiveFrom " + getStringFromMapList(transactionMatrixCaseMap,"showYouReceiveFrom") + " but found: " + getStringFromJsonPath(transactionMatrixTestJsonPath,"showYouReceiveFrom"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionMatrixTestJsonPath,"showYouWithdraw"), getStringFromMapList(transactionMatrixCaseMap,"showYouWithdraw")), "Expected showYouWithdraw " + getStringFromMapList(transactionMatrixCaseMap,"showYouWithdrew") + " but found: " + getStringFromJsonPath(transactionMatrixTestJsonPath,"showYouWithdrew"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionMatrixTestJsonPath,"showTransactionDate"), getStringFromMapList(transactionMatrixCaseMap,"showTransactionDate")), "Expected showTransactionDate " + getStringFromMapList(transactionMatrixCaseMap,"showTransactionDate") + " but found: " + getStringFromJsonPath(transactionMatrixTestJsonPath,"showTransactionDate"));
        assertJsonKeyExistenceForString(transactionJson, getStringFromMapList(transactionMatrixCaseMap,"transactionCardImageUrl"));

        Log4Test.test("Transaction matrix assertion test completed.");
    }

    /**
     * Transactions CREDIT CARD MATRIX test
     */
    @Test(dataProvider = "creditCardTransactionsMatrixTest", dataProviderClass = TestData_DataProvider.class, groups = {"all", "transactionsCreditMatrix"})
    public void transactions_5_creditMatrixTest(String internalTransactionTestCaseType, String transactionType, String[] internalTransactionTypeIds) {

        String transactionCreditJson = transactionsResponseTest.transactionCreditMatrixTestTransactionExpose(internalTransactionTestCaseType, transactionType, internalTransactionTypeIds);
        JsonPath transactionCreditMatrixTestJsonPath = JsonPath.from(transactionCreditJson);
        Map transactionCreditMatrixMap = transactionsResponseTest.transactionCreditMatrixCase(internalTransactionTestCaseType, transactionType, transactionCreditMatrixTestJsonPath);

        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"transactionType"), transactionType), "Expected transactionType " + transactionType + " but found: " + getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"transactionType"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"counterparty.avatarUrl"), getStringFromMapList(transactionCreditMatrixMap,"counterparty.avatarUrl")), "Expected avatarUrl " + getStringFromMapList(transactionCreditMatrixMap,"counterparty.avatarUrl") + " but found: " + getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"counterparty.avatarUrl"));
        assertJsonKeyExistenceForString(transactionCreditJson, getStringFromMapList(transactionCreditMatrixMap,"description"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"attachDocument"), getStringFromMapList(transactionCreditMatrixMap,"attachDocument")), "Expected attachDocument " + getStringFromMapList(transactionCreditMatrixMap,"attachDocument") + " but found: " + getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"attachDocument"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"showYouPaidTo"), getStringFromMapList(transactionCreditMatrixMap,"showYouPaidTo")), "Expected showYouPaidTo " + getStringFromMapList(transactionCreditMatrixMap,"showYouPaidTo") + " but found: " + getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"showYouPaidTo"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"showYouDeposit"), getStringFromMapList(transactionCreditMatrixMap,"showYouDeposit")), "Expected showYouDeposit " + getStringFromMapList(transactionCreditMatrixMap,"showYouDeposit") + " but found: " + getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"showYouDeposit"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"showYouReceiveFrom"), getStringFromMapList(transactionCreditMatrixMap,"showYouReceiveFrom")), "Expected showYouReceiveFrom " + getStringFromMapList(transactionCreditMatrixMap,"showYouReceiveFrom") + " but found: " + getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"showYouReceiveFrom"));
        Assert.assertTrue(assertEqualsStringToString(getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"showYouWithdraw"), getStringFromMapList(transactionCreditMatrixMap,"showYouWithdraw")), "Expected showYouWithdraw " + getStringFromMapList(transactionCreditMatrixMap,"showYouWithdrew") + " but found: " + getStringFromJsonPath(transactionCreditMatrixTestJsonPath,"showYouWithdrew"));
        assertJsonKeyExistenceForString(transactionCreditJson, getStringFromMapList(transactionCreditMatrixMap,"transactionCardImageUrl"));
        assertJsonKeyExistenceForString(transactionCreditJson, getStringFromMapList(transactionCreditMatrixMap,"cardNumber"));
        assertJsonKeyExistenceForString(transactionCreditJson, getStringFromMapList(transactionCreditMatrixMap,"cardName"));
        assertJsonKeyExistenceForString(transactionCreditJson, getStringFromMapList(transactionCreditMatrixMap,"originalAmount"));
        assertJsonKeyExistenceForString(transactionCreditJson, getStringFromMapList(transactionCreditMatrixMap,"originalExchangeRate"));
        assertJsonKeyExistenceForString(transactionCreditJson, getStringFromMapList(transactionCreditMatrixMap,"originalCurrency"));

        Log4Test.test("Transaction credit matrix assertion test completed.");
    }
}
