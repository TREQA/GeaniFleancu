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
        accountStatementResponseTest.accountStatementDownloadActivityReport_CreditWithFieldID(ACCOUNT_STATEMENT_CREDIT_DOWNLOAD_WS_PATH, ACCOUNT_STATEMENT_ACCOUNT_ID_CREDIT, ACCOUNT_STATEMENT_FILE_ID_CREDIT);
        //set download path and file name
        String downloadPathAndFileName_Credit = "WS_Downloads/accountStatement_1_DownloadActivityReport_CreditWithFieldID_OnRequest_" + ACCOUNT_STATEMENT_ACCOUNT_ID_CREDIT + "_" + ACCOUNT_STATEMENT_FILE_ID_CREDIT + "_" + TIME_STAMP + ".pdf";
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
        accountStatementResponseTest.accountStatementDownloadActivityReport_CreditWithoutFieldID(ACCOUNT_STATEMENT_CREDIT_DOWNLOAD_WS_PATH, ACCOUNT_STATEMENT_ACCOUNT_ID_CREDIT);
        //set download path and file name
        String downloadPathAndFileName_Credit = "WS_Downloads/accountStatement_2_DownloadActivityReport_CreditWithoutFieldID_LastStatement_" + ACCOUNT_STATEMENT_ACCOUNT_ID_CREDIT + "_" + ACCOUNT_STATEMENT_FILE_ID_CREDIT + "_" + TIME_STAMP + ".pdf";
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
        accountStatementResponseTest.accountStatementDownloadActivityReport_DebiLastStatement(ACCOUNT_STATEMENT_DEBIT_DOWNLOAD_WS_PATH, ACCOUNT_STATEMENT_ACCOUNT_ID_DEBIT, ACCOUNT_STATEMENT_ACCOUNT_TYPE_DEBIT, "PDF");
        //set download path and file name
        String downloadPathAndFileName_Debit = "WS_Downloads/accountStatement_3_DownloadDebitActivityReport_LastStatement_" + ACCOUNT_STATEMENT_ACCOUNT_ID_DEBIT + "_" + "_" + TIME_STAMP + ".pdf";
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
        accountStatementResponseTest.accountStatementDownloadActivityReport_DebitWithoutOnRequestParam(ACCOUNT_STATEMENT_DEBIT_DOWNLOAD_WS_PATH, ACCOUNT_STATEMENT_ACCOUNT_ID_DEBIT, ACCOUNT_STATEMENT_ACCOUNT_TYPE_DEBIT, ACCOUNT_STATEMENT_START_DATE_DEBIT, ACCOUNT_STATEMENT_END_DATE_DEBIT, "PDF");
        //set download path and file name
        String downloadPathAndFileName_Debit = "WS_Downloads/accountStatement_4_DownloadDebitActivityReport_" + ACCOUNT_STATEMENT_ACCOUNT_ID_DEBIT + "_" + ACCOUNT_STATEMENT_START_DATE_DEBIT + "_" + ACCOUNT_STATEMENT_END_DATE_DEBIT + "_" + TIME_STAMP + ".pdf";
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
        accountStatementResponseTest.accountStatementDownloadActivityReport_DebitWithOnRequestParam(ACCOUNT_STATEMENT_DEBIT_DOWNLOAD_WS_PATH, ACCOUNT_STATEMENT_ACCOUNT_ID_DEBIT, ACCOUNT_STATEMENT_ACCOUNT_TYPE_DEBIT, ACCOUNT_STATEMENT_START_DATE_DEBIT, ACCOUNT_STATEMENT_END_DATE_DEBIT, "PDF", true);
        //set download path and file name
        String downloadPathAndFileName_Debit = "WS_Downloads/accountStatement_5_DownloadDebitActivityReport_OnRequestTrue_" + ACCOUNT_STATEMENT_ACCOUNT_ID_DEBIT + "_" + ACCOUNT_STATEMENT_START_DATE_DEBIT + "_" + ACCOUNT_STATEMENT_END_DATE_DEBIT + "_" + TIME_STAMP + ".pdf";
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
        accountStatementResponseTest.loanGraphicFileStatusAndDownload(ACCOUNT_STATEMENT_LOAN_GRAPHIC_FILE_DOWNLOAD_WS_PATH, LOAN_DOCUMENT_ID);
        //set download path and file name
        String downloadPathAndFileName_Loan = "WS_Downloads/accountStatement_6_LoanGraphicDownload_" + LOAN_ACCOUNT_ID + "_" + LOAN_DOCUMENT_ID + "_" + TIME_STAMP + ".pdf";
        //download file
        downloadFileFromWS(accountStatementResponse, downloadPathAndFileName_Loan);
        Log4Test.info("Document downloaded with name: " + downloadPathAndFileName_Loan);

    }
}
