package REST_Tests;

import REST_Core.TestBase;
import REST_WS.CyberReceiptWS;
import REST_utils.Log4Test;
import org.testng.annotations.Test;

import static REST_Framework.CommonTask.downloadFileFromWS;
import static REST_WS.CyberReceiptWS.cyberReceiptResponse;

public class CyberReceipt_Tests extends TestBase {

    private static final CyberReceiptWS cyberReceiptResponseTest = new CyberReceiptWS();

    /**
     * This method generates cyber receipt document id and prints it then extracts it for further tests
     */
    @Test(groups = {"all", "payments", "cyberReceipt", "transactions"})
    public void cyberReceipt_1_GenerateDocumentID() {
        //generate document id
        cyberReceiptResponseTest.cyberReceipt_Generate(cyberReceiptGenerateDocumentWSpath, cyberReceiptAccId, cyberReceiptPostingDate, cyberReceiptCassId);
        //print document id
        System.out.println("Response: ");
        cyberReceiptResponse.prettyPrint();
        //extract document id
        cyberReceiptDocumentId = cyberReceiptResponse.path("receiptDocumentId");
    }

    /**
     * This method checks status of the generated cyber receipt document id
     */
    @Test(groups = {"all", "payments", "cyberReceipt", "transactions"})
    public void cyberReceipt_2_documentIdStatus() throws InterruptedException {
        //check document id status
        cyberReceiptResponseTest.cyberReceipt_Status_Download(cyberReceiptDocumentStatusWSpath, cyberReceiptDocumentId);
        //print document id status
        System.out.println("Response: ");
        cyberReceiptResponse.prettyPrint();
        Log4Test.info("Document id " + cyberReceiptDocumentId + " status is: " + cyberReceiptResponse.path("status"));
    }

    /**
     * Cyber receipt download
     */
    @Test(groups = {"all", "payments", "cyberReceipt", "transactions"})
    public void cyberReceipt_3_Download() throws Exception {
        //get response
        cyberReceiptResponseTest.cyberReceipt_Status_Download(cyberReceiptDocumentDownloadWSpath, cyberReceiptDocumentId);
        //set download path and file name
        String cyberReceipt_dwl = "WS_Downloads/cyberReceipt_1_Download_" + cyberReceiptDocumentId + "_" + cyberReceiptCassId + "_" + timeStamp + ".pdf";
        //download file
        //cyberReceiptResponse.prettyPrint();
        downloadFileFromWS(cyberReceiptResponse, cyberReceipt_dwl);
        Log4Test.info("Document downloaded with name: " + cyberReceipt_dwl);
    }
}
