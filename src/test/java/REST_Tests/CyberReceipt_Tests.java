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
        cyberReceiptResponseTest.cyberReceipt_Generate(CYBER_RECEIPT_GENERATE_DOCUMENT_WS_PATH, CYBER_RECEIPT_ACC_ID, CYBER_RECEIPT_POSTING_DATE, CYBER_RECEIPT_CASS_ID);
        //print document id
        System.out.println("Response: ");
        cyberReceiptResponse.prettyPrint();
        //extract document id
        CYBER_RECEIPT_DOCUMENT_ID = cyberReceiptResponse.path("receiptDocumentId");
    }

    /**
     * This method checks status of the generated cyber receipt document id
     */
    @Test(groups = {"all", "payments", "cyberReceipt", "transactions"})
    public void cyberReceipt_2_documentIdStatus() throws InterruptedException {
        //check document id status
        cyberReceiptResponseTest.cyberReceipt_Status_Download(CYBER_RECEIPT_DOCUMENT_STATUS_WS_PATH, CYBER_RECEIPT_DOCUMENT_ID);
        //print document id status
        System.out.println("Response: ");
        cyberReceiptResponse.prettyPrint();
        Log4Test.info("Document id " + CYBER_RECEIPT_DOCUMENT_ID + " status is: " + cyberReceiptResponse.path("status"));
    }

    /**
     * Cyber receipt download
     */
    @Test(groups = {"all", "payments", "cyberReceipt", "transactions"})
    public void cyberReceipt_3_Download() throws Exception {
        //get response
        cyberReceiptResponseTest.cyberReceipt_Status_Download(CYBER_RECEIPT_DOCUMENT_DOWNLOAD_WS_PATH, CYBER_RECEIPT_DOCUMENT_ID);
        //set download path and file name
        String cyberReceipt_dwl = "WS_Downloads/cyberReceipt_1_Download_" + CYBER_RECEIPT_ACC_ID +"_"+ CYBER_RECEIPT_DOCUMENT_ID + "_" + CYBER_RECEIPT_CASS_ID + "_" + TIME_STAMP + ".pdf";
        //download file
        //cyberReceiptResponse.prettyPrint();
        downloadFileFromWS(cyberReceiptResponse, cyberReceipt_dwl);
        Log4Test.info("Document downloaded with name: " + cyberReceipt_dwl);
    }
}
