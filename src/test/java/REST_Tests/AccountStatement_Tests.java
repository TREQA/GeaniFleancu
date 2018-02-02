package REST_Tests;

import REST_Core.TestBase;
import REST_WS.AccountStatementWS;
import REST_utils.Log4Test;
import org.testng.annotations.Test;

import static REST_Framework.CommonTask.downloadFileFromWS;
import static REST_WS.AccountStatementWS.accountStatementResponse;

public class AccountStatement_Tests extends TestBase {

    private static final AccountStatementWS accountStatementResponseTest = new AccountStatementWS();


    /**
     * Account statement downloads:
     * <p>
     * CREDIT - with field id
     */
    @Test(groups = {"all", "accountStatement", "accountStatementCredit"})
    public void accountStatement_1_DownloadActivityReport_CreditWithFieldID_OnRequest() throws Exception {
        //get response
        accountStatementResponseTest.accountStatementDownloadActivityReport_CreditWithFieldID(accountStatementCreditDownloadWSpath, accountStatement_accountId_credit, accountStatement_field_credit);
        //set download path and file name
        String downloadPathAndFileName_Credit = "WS_Downloads/accountStatement_1_DownloadActivityReport_CreditWithFieldID_OnRequest_" + accountStatement_accountId_credit + "_" + accountStatement_field_credit + "_" + timeStamp + ".pdf";
        //download file
        downloadFileFromWS(accountStatementResponse, downloadPathAndFileName_Credit);
        Log4Test.info("Document downloaded with name: " + downloadPathAndFileName_Credit);
    }

    /**
     * download CREDIT - without field id
     */
    @Test(groups = {"all", "accountStatement", "accountStatementCredit"})
    public void accountStatement_2_DownloadActivityReport_CreditWithoutFieldID_LastStatement() throws Exception {
        //get response
        accountStatementResponseTest.accountStatementDownloadActivityReport_CreditWithoutFieldID(accountStatementCreditDownloadWSpath, accountStatement_accountId_credit);
        //set download path and file name
        String downloadPathAndFileName_Credit = "WS_Downloads/accountStatement_2_DownloadActivityReport_CreditWithoutFieldID_LastStatement_" + accountStatement_accountId_credit + "_" + accountStatement_field_credit + "_" + timeStamp + ".pdf";
        //download file
        downloadFileFromWS(accountStatementResponse, downloadPathAndFileName_Credit);
        Log4Test.info("Document downloaded with name: " + downloadPathAndFileName_Credit);
    }

    /**
     * download DEBIT - last statement
     */
    @Test(groups = {"all", "accountStatement", "accountStatementDebit"})
    public void accountStatement_3_DownloadDebitActivityReport_LastStatement() throws Exception {
        //get response
        accountStatementResponseTest.accountStatementDownloadActivityReport_DebiLastStatement(accountStatementDebitDownloadWSpath, accountStatement_accountId_debit, accountStatement_accountType_debit, "PDF");
        //set download path and file name
        String downloadPathAndFileName_Debit = "WS_Downloads/accountStatement_3_DownloadDebitActivityReport_LastStatement_" + accountStatement_accountId_debit + "_" + "_" + timeStamp + ".pdf";
        //download file
        downloadFileFromWS(accountStatementResponse, downloadPathAndFileName_Debit);
        Log4Test.info("Document downloaded with name: " + downloadPathAndFileName_Debit);
    }

    /**
     * download DEBIT - account statement ASR
     */
    @Test(groups = {"all", "accountStatement", "accountStatementDebit"})
    public void accountStatement_4_DownloadDebitActivityReport() throws Exception {
        //get response
        accountStatementResponseTest.accountStatementDownloadActivityReport_DebitWithoutOnRequestParam(accountStatementDebitDownloadWSpath, accountStatement_accountId_debit, accountStatement_accountType_debit, accountStatement_startDate_debit, accountStatement_endDate_debit, "PDF");
        //set download path and file name
        String downloadPathAndFileName_Debit = "WS_Downloads/accountStatement_4_DownloadDebitActivityReport_" + accountStatement_accountId_debit + "_" + accountStatement_startDate_debit + "_" + accountStatement_endDate_debit + "_" + timeStamp + ".pdf";
        //download file
        downloadFileFromWS(accountStatementResponse, downloadPathAndFileName_Debit);
        Log4Test.info("Document downloaded with name: " + downloadPathAndFileName_Debit);
    }

    /**
     * download DEBIT - on request true
     */
    @Test(groups = {"all", "accountStatement", "accountStatementDebit"})
    public void accountStatement_5_DownloadDebitActivityReport_OnRequestTrue() throws Exception {
        //get response
        accountStatementResponseTest.accountStatementDownloadActivityReport_DebitWithOnRequestParam(accountStatementDebitDownloadWSpath, accountStatement_accountId_debit, accountStatement_accountType_debit, accountStatement_startDate_debit, accountStatement_endDate_debit, "PDF", true);
        //set download path and file name
        String downloadPathAndFileName_Debit = "WS_Downloads/accountStatement_5_DownloadDebitActivityReport_OnRequestTrue_" + accountStatement_accountId_debit + "_" + accountStatement_startDate_debit + "_" + accountStatement_endDate_debit + "_" + timeStamp + ".pdf";
        //download file
        downloadFileFromWS(accountStatementResponse, downloadPathAndFileName_Debit);
        Log4Test.info("Document downloaded with name: " + downloadPathAndFileName_Debit);

    }

    /**
     * download Loan - Graphic download
     */
    @Test(groups = {"all", "accountStatement", "accountStatementLoan"})
    public void accountStatement_6_LoanGraphicDownload() throws Exception {
        //get response
        accountStatementResponseTest.loanGraphicFileStatusAndDownload(accountStatementLoanGraphicFileDownloadWSpath, loan_documentId);
        //set download path and file name
        String downloadPathAndFileName_Loan = "WS_Downloads/accountStatement_6_LoanGraphicDownload_" + accountStatement_accountId_debit + "_" + accountStatement_startDate_debit + "_" + accountStatement_endDate_debit + "_" + timeStamp + ".pdf";
        //download file
        downloadFileFromWS(accountStatementResponse, downloadPathAndFileName_Loan);
        Log4Test.info("Document downloaded with name: " + downloadPathAndFileName_Loan);

    }
}
