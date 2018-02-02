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
        accountStatementResponseTest.accountStatementGetActivityReport_Debit(accountStatementDebitActivityWSpath, accountStatement_accountId_debit, accountStatement_accountType_debit, accountStatement_startDate_debit, accountStatement_endDate_debit);
        accountStatementResponse.body().prettyPrint();
    }

    /**
     * CREDIT - get available dates
     */
    @Test(groups = {"all", "accountStatement", "accountStatementCredit"})
    public void accountStatement_2_GetActivityReport_CreditPrint() {
        accountStatementResponseTest.accountStatementGetActivityReport_Credit(accountStatementCreditActivityWSpath, accountStatement_accountId_credit, accountStatement_startDate_credit, accountStatement_endDate_credit);
        //accountStatement_field_credit = accountStatementResponse.path("fileId"); // this is used in a suite to dynamically extract the file id
        accountStatementResponse.body().prettyPrint();
    }

    /**
     * Loan - Graphic details
     */
    @Test(groups = {"all", "accountStatement", "accountStatementLoan"})
    public void accountStatement_3_loanGraphicDetails() {
        accountStatementResponseTest.loanGraphicDetailsAndDocumentGeneration(accountStatementLoanGraphicDetailsWSpath, loan_accountId);
        accountStatementResponse.body().prettyPrint();
    }

    /**
     * Loan - Generate file
     */
    @Test(groups = {"all", "accountStatement", "accountStatementLoan"})
    public void accountStatement_4_LoanGenerateFile() {
        accountStatementResponseTest.loanGraphicDetailsAndDocumentGeneration(accountStatementLoanGraphicGenerateWSpath, loan_accountId);
        accountStatementResponse.body().prettyPrint();
        loan_documentId = accountStatementResponse.path("documentId"); // this is used in a suite to dynamically extract the document id
        Log4Test.info("Document id is: " + accountStatementResponse.path("documentId"));
    }

    /**
     * Loan - Check File Status
     */
    @Test(groups = {"all", "accountStatement", "accountStatementLoan"})
    public void accountStatement_5_LoanGraphicFileStatus() throws Exception {
        accountStatementResponseTest.loanGraphicFileStatusAndDownload(accountStatementLoanGraphicFileStatusWSpath, loan_documentId);
        accountStatementResponse.body().prettyPrint();
        Log4Test.info("Document id " + loan_documentId + " status is: " + accountStatementResponse.path("status"));
    }
}