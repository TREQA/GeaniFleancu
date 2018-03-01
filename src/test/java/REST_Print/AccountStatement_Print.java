package REST_Print;

import REST_Core.TestBase;
import REST_WS.AccountStatementWS;
import REST_utils.Log4Test;
import org.testng.annotations.Test;

import static REST_WS.AccountStatementWS.accountStatementResponse;

public class AccountStatement_Print extends TestBase {

    private static final AccountStatementWS accountStatementResponseTest = new AccountStatementWS();

    /**
     * DEBIT - get available dates
     */
    @Test(groups = {"all", "accountStatement", "accountStatementDebit"})
    public void accountStatement_1_GetActivityReport_Debit_Print() {
        accountStatementResponseTest.accountStatementGetActivityReport_Debit(ACCOUNT_STATEMENT_DEBIT_ACTIVITY_WS_PATH, ACCOUNT_STATEMENT_ACCOUNT_ID_DEBIT, ACCOUNT_STATEMENT_ACCOUNT_TYPE_DEBIT, ACCOUNT_STATEMENT_START_DATE_DEBIT, ACCOUNT_STATEMENT_END_DATE_DEBIT);
        accountStatementResponse.body().prettyPrint();
    }

    /**
     * CREDIT - get available dates
     */
    @Test(groups = {"all", "accountStatement", "accountStatementCredit"})
    public void accountStatement_2_GetActivityReport_CreditPrint() {
        accountStatementResponseTest.accountStatementGetActivityReport_Credit(ACCOUNT_STATEMENT_CREDIT_ACTIVITY_WS_PATH, ACCOUNT_STATEMENT_ACCOUNT_ID_CREDIT, ACCOUNT_STATEMENT_START_DATE_CREDIT, ACCOUNT_STATEMENT_END_DATE_CREDIT);
        //ACCOUNT_STATEMENT_FILE_ID_CREDIT = accountStatementResponse.path("fileId"); // this is used in a suite to dynamically extract the file id
        accountStatementResponse.body().prettyPrint();
    }

    /**
     * Loan - Graphic details
     */
    @Test(groups = {"all", "accountStatement", "accountStatementLoan"})
    public void accountStatement_3_loanGraphicDetails() {
        accountStatementResponseTest.loanGraphicDetailsAndDocumentGeneration(ACCOUNT_STATEMENT_LOAN_GRAPHIC_DETAILS_WS_PATH, LOAN_ACCOUNT_ID);
        accountStatementResponse.body().prettyPrint();
    }

    /**
     * Loan - Generate file
     */
    @Test(groups = {"all", "accountStatement", "accountStatementLoan"})
    public void accountStatement_4_LoanGenerateFile() {
        accountStatementResponseTest.loanGraphicDetailsAndDocumentGeneration(ACCOUNT_STATEMENT_LOAN_GRAPHIC_GENERATE_WS_PATH, LOAN_ACCOUNT_ID);
        accountStatementResponse.body().prettyPrint();
        LOAN_DOCUMENT_ID = accountStatementResponse.path("documentId"); // this is used in a suite to dynamically extract the document id
        Log4Test.info("Document id is: " + accountStatementResponse.path("documentId"));
    }

    /**
     * Loan - Check File Status
     */
    @Test(groups = {"all", "accountStatement", "accountStatementLoan"})
    public void accountStatement_5_LoanGraphicFileStatus() throws Exception {
        accountStatementResponseTest.loanGraphicFileStatusAndDownload(ACCOUNT_STATEMENT_LOAN_GRAPHIC_FILE_STATUS_WS_PATH, LOAN_DOCUMENT_ID);
        accountStatementResponse.body().prettyPrint();
        Log4Test.info("Document id " + LOAN_DOCUMENT_ID + " status is: " + accountStatementResponse.path("status"));
    }
}