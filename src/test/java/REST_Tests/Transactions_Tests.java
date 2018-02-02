package REST_Tests;

import REST_Core.TestBase;
import REST_Framework.TestData_DataProvider;
import REST_WS.TransactionsWS;
import REST_utils.Log4Test;
import org.testng.annotations.Test;

import static REST_Framework.CommonTask.downloadFileFromWS;
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
        transactionsResponseTest.transactionsDateGET(accountID_custom, accountType_custom);
    }

    /**
     * Transaction download document for selected id
     */
    @Test
    public void transactions_3_downloadDocument() throws Exception {
        //get response
        String fileType = "jpg";
        transactionsResponseTest.transactions_downloadDocument(transactionIdForDownload);
        //set download path and file name
        String downloadPathAndFileName_Transaction = "WS_Downloads/transactions_3_downloadDocument_" + transactionIdForDownload + "." + fileType;
        //download file
        downloadFileFromWS(transactionsResponse, downloadPathAndFileName_Transaction);
        Log4Test.info("Document downloaded with name: " + downloadPathAndFileName_Transaction);
    }
}
