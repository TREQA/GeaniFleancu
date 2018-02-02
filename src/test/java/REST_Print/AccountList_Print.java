package REST_Print;

import REST_Core.TestBase;
import REST_WS.AccountListWS;
import org.testng.annotations.Test;

import static REST_WS.AccountListWS.accountListResponse;

public class AccountList_Print extends TestBase {

    private static final AccountListWS accountListResponseTest = new AccountListWS();

    /**
     * This method is used to print account list for logged in user
     */
    @Test(groups = {"all", "accountList"})
    public void accountList_1_PrintAccountList() {
        accountListResponseTest.accountListGET();
        accountListResponse.body().prettyPrint();
    }
}