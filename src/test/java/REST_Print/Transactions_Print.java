package REST_Print;

import REST_Core.TestBase;
import REST_Framework.TestData_DataProvider;
import REST_WS.TransactionsWS;
import org.testng.annotations.Test;

import static REST_WS.TransactionsWS.transactionsResponse;

public class Transactions_Print extends TestBase {

    private final TransactionsWS transactionsResponseTest = new TransactionsWS();

    /**
     * Printing transaction dates for all accounts type set
     */
    @Test(dataProvider = "accountsForTransactions", dataProviderClass = TestData_DataProvider.class, groups = {"all", "transactions", "transactionsDate"})
    public void transactions_1_DateGET_allAccounts(String accountId, String accountType) {
        transactionsResponseTest.transactionsDateGET(accountId, accountType);
        transactionsResponse.prettyPrint();
    }

    /**
     * Printing transaction dates for custom account
     */
    @Test(groups = {"all", "transactions", "transactionsDate"})
    public void transactions_2_DateGET_custom() {
        transactionsResponseTest.transactionsDateGET(ACCOUNT_ID_CUSTOM, ACCOUNT_TYPE_CUSTOM);
        transactionsResponse.prettyPrint();
    }

    /**
     * Printing transaction list for custom parameters
     */
    @Test(groups = {"all", "transactions", "transactionsList"})
    public void transactions_3_transactionGET_custom() {
        transactionsResponseTest.transactionsGET(
                "0005540642",
                "20",
                "",
                "",
                "",
                true, true,
                999,
                "\"SUCCESS\",\"PENDING\"");
        transactionsResponse.prettyPrint();
    }
}

