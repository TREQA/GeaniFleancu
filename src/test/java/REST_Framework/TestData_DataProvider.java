package REST_Framework;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static REST_Framework.TestData.*;

public class TestData_DataProvider {

    /**
     * Data provider used for transactions tests
     */
    @DataProvider(name = "accountsForTransactions")
    public Object[][] accountsForTransactions() {

        Object[][] accountsForTransactions = new Object[4][2];

        accountsForTransactions[0][0] = ACCOUNT_ID_20; // account id
        accountsForTransactions[0][1] = "20"; // account type

        accountsForTransactions[1][0] = ACCOUNT_ID_26; // account id
        accountsForTransactions[1][1] = "26"; // account type

        accountsForTransactions[2][0] = ACCOUNT_ID_30; // account id
        accountsForTransactions[2][1] = "30"; // account type

        accountsForTransactions[3][0] = ACCOUNT_ID_50; // account id
        accountsForTransactions[3][1] = "50"; // account type

        return accountsForTransactions;
    }

    /**
     * Data provider used for interest rates tests
     */
    @DataProvider(name = "interestRates")
    public Object[][] interestRates() {

        Object[][] interestRates = new Object[3][2];

        interestRates[0][0] = "categories[0]";
        interestRates[0][1] = "ROBOR";

        interestRates[1][0] = "categories[1]";
        interestRates[1][1] = "EURIBOR";

        interestRates[2][0] = "categories[2]";
        interestRates[2][1] = "LIBOR";

        return interestRates;
    }

    /**
     * Data provider used for FX rates tests with path
     */
    @DataProvider(name = "currenciesList")
    public Object[][] currenciesList() {

        Object[][] testCurrencyList = new Object[13][2];

        testCurrencyList[0][0] = "currencies[0]";
        testCurrencyList[0][1] = "EUR";

        testCurrencyList[1][0] = "currencies[1]";
        testCurrencyList[1][1] = "USD";

        testCurrencyList[2][0] = "currencies[2]";
        testCurrencyList[2][1] = "GBP";

        testCurrencyList[3][0] = "currencies[3]";
        testCurrencyList[3][1] = "CHF";

        testCurrencyList[4][0] = "currencies[4]";
        testCurrencyList[4][1] = "AUD";

        testCurrencyList[5][0] = "currencies[5]";
        testCurrencyList[5][1] = "BGN";

        testCurrencyList[6][0] = "currencies[6]";
        testCurrencyList[6][1] = "CAD";

        testCurrencyList[7][0] = "currencies[7]";
        testCurrencyList[7][1] = "DKK";

        testCurrencyList[8][0] = "currencies[8]";
        testCurrencyList[8][1] = "HUF";

        testCurrencyList[9][0] = "currencies[9]";
        testCurrencyList[9][1] = "JPY";

        testCurrencyList[10][0] = "currencies[10]";
        testCurrencyList[10][1] = "NOK";

        testCurrencyList[11][0] = "currencies[11]";
        testCurrencyList[11][1] = "PLN";

        testCurrencyList[12][0] = "currencies[12]";
        testCurrencyList[12][1] = "SEK";

        return testCurrencyList;
    }

    /**
     * Data provider used for FX rates for sell / buy tests
     */
    @DataProvider(name = "currenciesSell_Buy_RON")
    public Object[][] currenciesSell_Buy_RON() {

        Object[][] currenciesSell_Buy_RON = new Object[13][1];

        currenciesSell_Buy_RON[0][0] = "EUR";
        currenciesSell_Buy_RON[1][0] = "USD";
        currenciesSell_Buy_RON[2][0] = "GBP";
        currenciesSell_Buy_RON[3][0] = "CHF";
        currenciesSell_Buy_RON[4][0] = "AUD";
        currenciesSell_Buy_RON[5][0] = "BGN";
        currenciesSell_Buy_RON[6][0] = "CAD";
        currenciesSell_Buy_RON[7][0] = "DKK";
        currenciesSell_Buy_RON[8][0] = "HUF";
        currenciesSell_Buy_RON[9][0] = "JPY";
        currenciesSell_Buy_RON[10][0] = "NOK";
        currenciesSell_Buy_RON[11][0] = "PLN";
        currenciesSell_Buy_RON[12][0] = "SEK";

        return currenciesSell_Buy_RON;
    }


    /**
     * This data provider is used a combination for FX rats sell / buy combination tests
     */
    @DataProvider(name = "currenciesSell_Buy_FCY")
    public Object[][] currenciesSell_Buy_FCY() {

        return combine(currenciesSell_Buy_RON(), currenciesSell_Buy_RON());
    }

    /**
     * Data provider used for transactions matrix test
     */
    @DataProvider(name = "transactionsMatrixTest")
    public Object[][] transactionsMatrixTest() {

        Object[][] transactionsMatrixTest = new Object[60][3]; // Object[62][3]

        transactionsMatrixTest[0][0] = "SWF"; // channel
        transactionsMatrixTest[0][1] = "DEBIT"; //  CREDIT / DEBIT
        transactionsMatrixTest[0][2] = "iban"; // iban(shortAccount) / null

        transactionsMatrixTest[1][0] = "SWF";
        transactionsMatrixTest[1][1] = "DEBIT";
        transactionsMatrixTest[1][2] = "noIban";

        transactionsMatrixTest[2][0] = "SWF";
        transactionsMatrixTest[2][1] = "CREDIT";
        transactionsMatrixTest[2][2] = "iban";

        transactionsMatrixTest[3][0] = "SWF";
        transactionsMatrixTest[3][1] = "CREDIT";
        transactionsMatrixTest[3][2] = "noIban";

        transactionsMatrixTest[4][0] = "GRN";
        transactionsMatrixTest[4][1] = "DEBIT";
        transactionsMatrixTest[4][2] = "iban";

        transactionsMatrixTest[5][0] = "GRN";
        transactionsMatrixTest[5][1] = "DEBIT";
        transactionsMatrixTest[5][2] = "noIban";

        transactionsMatrixTest[6][0] = "GRN";
        transactionsMatrixTest[6][1] = "CREDIT";
        transactionsMatrixTest[6][2] = "iban";

        transactionsMatrixTest[7][0] = "GRN";
        transactionsMatrixTest[7][1] = "CREDIT";
        transactionsMatrixTest[7][2] = "noIban";

        transactionsMatrixTest[8][0] = "FLCY";
        transactionsMatrixTest[8][1] = "DEBIT";
        transactionsMatrixTest[8][2] = "iban";

        transactionsMatrixTest[9][0] = "FLCY";
        transactionsMatrixTest[9][1] = "DEBIT";
        transactionsMatrixTest[9][2] = "noIban";

        transactionsMatrixTest[10][0] = "FLCY";
        transactionsMatrixTest[10][1] = "CREDIT";
        transactionsMatrixTest[10][2] = "iban";

        transactionsMatrixTest[11][0] = "FLCY";
        transactionsMatrixTest[11][1] = "CREDIT";
        transactionsMatrixTest[11][2] = "noIban";

        transactionsMatrixTest[12][0] = "MCH";
        transactionsMatrixTest[12][1] = "DEBIT";
        transactionsMatrixTest[12][2] = "iban";

        transactionsMatrixTest[13][0] = "MCH";
        transactionsMatrixTest[13][1] = "DEBIT";
        transactionsMatrixTest[13][2] = "noIban";

        transactionsMatrixTest[14][0] = "MCH";
        transactionsMatrixTest[14][1] = "CREDIT";
        transactionsMatrixTest[14][2] = "iban";

        transactionsMatrixTest[15][0] = "FOTO";
        transactionsMatrixTest[15][1] = "DEBIT";
        transactionsMatrixTest[15][2] = "iban";

        transactionsMatrixTest[16][0] = "FOTO";
        transactionsMatrixTest[16][1] = "DEBIT";
        transactionsMatrixTest[16][2] = "noIban";

        transactionsMatrixTest[17][0] = "FOTO";
        transactionsMatrixTest[17][1] = "CREDIT";
        transactionsMatrixTest[17][2] = "iban";

        transactionsMatrixTest[18][0] = "FOTO";
        transactionsMatrixTest[18][1] = "CREDIT";
        transactionsMatrixTest[18][2] = "noIban";

        transactionsMatrixTest[19][0] = "OPH";
        transactionsMatrixTest[19][1] = "DEBIT";
        transactionsMatrixTest[19][2] = "iban";

        transactionsMatrixTest[20][0] = "OPH";
        transactionsMatrixTest[20][1] = "DEBIT";
        transactionsMatrixTest[20][2] = "noIban";

        transactionsMatrixTest[21][0] = "OPH";
        transactionsMatrixTest[21][1] = "CREDIT";
        transactionsMatrixTest[21][2] = "iban";

        transactionsMatrixTest[22][0] = "IBK";
        transactionsMatrixTest[22][1] = "DEBIT";
        transactionsMatrixTest[22][2] = "iban";

        transactionsMatrixTest[23][0] = "IBK";
        transactionsMatrixTest[23][1] = "DEBIT";
        transactionsMatrixTest[23][2] = "noIban";

        transactionsMatrixTest[24][0] = "IBK";
        transactionsMatrixTest[24][1] = "CREDIT";
        transactionsMatrixTest[24][2] = "iban";

        transactionsMatrixTest[25][0] = "IBK";
        transactionsMatrixTest[25][1] = "CREDIT";
        transactionsMatrixTest[25][2] = "noIban";

        transactionsMatrixTest[26][0] = "SFI";
        transactionsMatrixTest[26][1] = "CREDIT";
        transactionsMatrixTest[26][2] = "iban";

        transactionsMatrixTest[27][0] = "POD";
        transactionsMatrixTest[27][1] = "DEBIT";
        transactionsMatrixTest[27][2] = "noIban";

        transactionsMatrixTest[28][0] = "POD";
        transactionsMatrixTest[28][1] = "CREDIT";
        transactionsMatrixTest[28][2] = "noIban";

        transactionsMatrixTest[29][0] = "FCT";
        transactionsMatrixTest[29][1] = "DEBIT";
        transactionsMatrixTest[29][2] = "noIban";

        transactionsMatrixTest[30][0] = "FCT";
        transactionsMatrixTest[30][1] = "CREDIT";
        transactionsMatrixTest[30][2] = "noIban";

        transactionsMatrixTest[31][0] = "FIPO";
        transactionsMatrixTest[31][1] = "DEBIT";
        transactionsMatrixTest[31][2] = "noIban";

        transactionsMatrixTest[32][0] = "FIPO";
        transactionsMatrixTest[32][1] = "CREDIT";
        transactionsMatrixTest[32][2] = "noIban";

        transactionsMatrixTest[33][0] = "PAID";
        transactionsMatrixTest[33][1] = "DEBIT";
        transactionsMatrixTest[33][2] = "noIban";

        transactionsMatrixTest[34][0] = "PAID";
        transactionsMatrixTest[34][1] = "CREDIT";
        transactionsMatrixTest[34][2] = "noIban";

        transactionsMatrixTest[35][0] = "SPID";
        transactionsMatrixTest[35][1] = "DEBIT";
        transactionsMatrixTest[35][2] = "noIban";

        transactionsMatrixTest[36][0] = "SPID";
        transactionsMatrixTest[36][1] = "CREDIT";
        transactionsMatrixTest[36][2] = "noIban";

        transactionsMatrixTest[37][0] = "PBA";
        transactionsMatrixTest[37][1] = "DEBIT";
        transactionsMatrixTest[37][2] = "noIban";

        transactionsMatrixTest[38][0] = "PBA";
        transactionsMatrixTest[38][1] = "CREDIT";
        transactionsMatrixTest[38][2] = "noIban";

        transactionsMatrixTest[39][0] = "MFM";
        transactionsMatrixTest[39][1] = "DEBIT";
        transactionsMatrixTest[39][2] = "noIban";

        transactionsMatrixTest[40][0] = "MFM";
        transactionsMatrixTest[40][1] = "CREDIT";
        transactionsMatrixTest[40][2] = "noIban";

        transactionsMatrixTest[41][0] = "ABT";
        transactionsMatrixTest[41][1] = "DEBIT";
        transactionsMatrixTest[41][2] = "iban";

        transactionsMatrixTest[42][0] = "ABT";
        transactionsMatrixTest[42][1] = "DEBIT";
        transactionsMatrixTest[42][2] = "noIban";

        transactionsMatrixTest[43][0] = "ABT";
        transactionsMatrixTest[43][1] = "CREDIT";
        transactionsMatrixTest[43][2] = "iban";

        transactionsMatrixTest[44][0] = "ABT";
        transactionsMatrixTest[44][1] = "CREDIT";
        transactionsMatrixTest[44][2] = "noIban";

        transactionsMatrixTest[45][0] = "EFT";
        transactionsMatrixTest[45][1] = "DEBIT";
        transactionsMatrixTest[45][2] = "EF1A";

        transactionsMatrixTest[46][0] = "EFT";
        transactionsMatrixTest[46][1] = "DEBIT";
        transactionsMatrixTest[46][2] = "EF1C";

        transactionsMatrixTest[47][0] = "EFT";
        transactionsMatrixTest[47][1] = "DEBIT";
        transactionsMatrixTest[47][2] = "EF9A";

        transactionsMatrixTest[48][0] = "EFT";
        transactionsMatrixTest[48][1] = "DEBIT";
        transactionsMatrixTest[48][2] = "EF9C";

        transactionsMatrixTest[49][0] = "EFT";
        transactionsMatrixTest[49][1] = "DEBIT";
        transactionsMatrixTest[49][2] = "noIban";

        transactionsMatrixTest[50][0] = "EFT";
        transactionsMatrixTest[50][1] = "CREDIT";
        transactionsMatrixTest[50][2] = "noIban";

        transactionsMatrixTest[51][0] = "BATCH";
        transactionsMatrixTest[51][1] = "DEBIT";
        transactionsMatrixTest[51][2] = "iban";

        transactionsMatrixTest[52][0] = "BATCH";
        transactionsMatrixTest[52][1] = "DEBIT";
        transactionsMatrixTest[52][2] = "noIban";

        transactionsMatrixTest[53][0] = "BATCH";
        transactionsMatrixTest[53][1] = "CREDIT";
        transactionsMatrixTest[53][2] = "noIban";

        transactionsMatrixTest[54][0] = "AUT";
        transactionsMatrixTest[54][1] = "DEBIT";
        transactionsMatrixTest[54][2] = "iban";

        transactionsMatrixTest[55][0] = "AUT";
        transactionsMatrixTest[55][1] = "DEBIT";
        transactionsMatrixTest[55][2] = "noIban";

        transactionsMatrixTest[56][0] = "AUT";
        transactionsMatrixTest[56][1] = "CREDIT";
        transactionsMatrixTest[56][2] = "noIban";

        transactionsMatrixTest[57][0] = "SEP";
        transactionsMatrixTest[57][1] = "DEBIT";
        transactionsMatrixTest[57][2] = "iban";

        transactionsMatrixTest[58][0] = "SEP";
        transactionsMatrixTest[58][1] = "DEBIT";
        transactionsMatrixTest[58][2] = "noIban";

        transactionsMatrixTest[59][0] = "SEP";
        transactionsMatrixTest[59][1] = "CREDIT";
        transactionsMatrixTest[59][2] = "noIban";


        return transactionsMatrixTest;
    }


    /**
     * Data provider used for credit card transactions matrix test
     */
    @DataProvider(name = "creditCardTransactionsMatrixTest")
    public Object[][] creditCardTransactionsMatrixTest_DETAILS() {

        String[] string320 = {"310", "330", "3320", "3330", "6310", "6320", "6330", "320", "3310"};
        String[] string3110 = {"110", "210", "220", "420", "520", "620", "3120", "3121", "3210", "3220", "3221", "3410", "3421", "3510", "3520", "3610", "3620", "3630", "3720", "3810", "3820", "3920", "3930", "4020", "4021", "4120", "4121", "4210", "4230", "6110", "6120", "6220", "6420", "6510", "6520", "6620", "6630", "6710", "6720", "6820", "6920", "7010", "7020", "7120", "7210", "7220", "8121", "7771", "7772", "7775", "7776", "7777", "7778", "8122", "174", "175", "177", "120", "3910", "3110", "3521", "3710", "6410", "188", "192", "9700", "162", "164", "165", "6230", "510", "630", "3420", "3821", "4010", "4110", "4220", "6210", "6610", "6810", "6930", "7110", "7230", "8110", "8120", "8310", "8320", "8330", "8113", "8123", "8313", "8323", "8333", "8116", "8126", "8316", "8326", "8336", "6910", "610", "3721", "410", "8905", "158", "159", "160", "161", "163", "166", "167", "168", "169", "170", "171", "172", "184", "180", "182", "185", "186", "187", "189", "190", "7690", "7691", "8690", "8907", "194", "152", "153", "154", "155", "156", "157", "131", "132", "133", "134", "151", "340", "640", "3340", "3940", "6340", "6940", "8906", "173", "176", "178", "179", "181", "183", "191", "193", "121", "122", "196", "221", "222", "331", "421", "422", "521", "631"};
        String[] string9375 = {"9100", "9101", "9110", "9111", "9120", "9130", "9140", "9160", "9362", "9376", "9377", "9378", "9384", "9400", "9405", "9410", "9415", "9420", "9425", "9430", "9435", "9440", "9441", "9442", "9445", "9455", "9460", "9465", "9470", "9471", "9473", "9475", "9480", "9481", "9485", "9180", "9190", "9200", "9300", "9302", "9330", "9335", "9340", "9341", "9346", "9350", "9351", "9853", "9484", "9306", "8904", "9713", "9650", "9398", "9450", "9490", "9491", "9492", "9493", "9494", "9495", "9496", "9497", "9498", "9621", "9622", "9623", "9720", "9730", "9740", "9750", "9760", "9780", "9790", "9791", "9792", "9793", "9794", "9795", "9796", "9798", "9810", "9820", "9830", "9999", "9250", "9356", "9360", "9361", "9370", "9371", "9372", "9373", "9374", "9375", "9380", "9381", "9382", "9390", "9391", "9392", "9393", "9394", "9395", "9396", "9397", "9550", "9551", "9559", "9565", "9566", "9383", "9385", "9386", "9610", "9010", "9011", "9012", "9015", "9016", "9017", "9018", "9020", "9021", "9022", "9023", "9024", "9025", "9026", "9027", "9028", "9499", "9500", "9501", "9502", "9503", "9504", "9505", "9506", "9507", "9508", "9509", "9510", "9511", "9512", "9513", "9514", "9515", "9516", "9517", "9518", "9519", "9520", "9530", "9531", "9532", "9540", "9552", "9553", "9554", "9555", "9556", "9557", "9558", "9560", "9561", "9562", "9563", "9564", "9590", "9591", "9599", "9600", "9601", "9602", "9603", "9604", "9605", "9606", "9607", "9608", "9620", "9567", "9612", "9489", "9303", "9488", "9301", "9331", "9336", "9345", "9355", "9150", "9630", "9631", "9632", "9614", "9851", "9852", "9609", "9625", "9690", "9633", "9634", "9635", "9702", "9703", "9704", "9705", "9706", "9707", "9708", "9709", "9710", "9711", "9712", "9701", "9611", "9613", "9387"};
        String[] string6120 = {"6120"};
        String[] string9700 = {"9700"};

        Object[][] creditCardTransactionsMatrixTest = new Object[8][3];

        creditCardTransactionsMatrixTest[0][0] = "320"; // case type
        creditCardTransactionsMatrixTest[0][1] = "DEBIT"; // credit / debit
        creditCardTransactionsMatrixTest[0][2] = string320; //internal transaction id


        creditCardTransactionsMatrixTest[1][0] = "320";
        creditCardTransactionsMatrixTest[1][1] = "CREDIT";
        creditCardTransactionsMatrixTest[1][2] = string320;


        creditCardTransactionsMatrixTest[2][0] = "3110";
        creditCardTransactionsMatrixTest[2][1] = "DEBIT";
        creditCardTransactionsMatrixTest[2][2] = string3110;


        creditCardTransactionsMatrixTest[3][0] = "3110";
        creditCardTransactionsMatrixTest[3][1] = "CREDIT";
        creditCardTransactionsMatrixTest[3][2] = string3110;


        creditCardTransactionsMatrixTest[4][0] = "9375";
        creditCardTransactionsMatrixTest[4][1] = "DEBIT";
        creditCardTransactionsMatrixTest[4][2] = string9375;


        creditCardTransactionsMatrixTest[5][0] = "9375";
        creditCardTransactionsMatrixTest[5][1] = "CREDIT";
        creditCardTransactionsMatrixTest[5][2] = string9375;


        creditCardTransactionsMatrixTest[6][0] = "6120";
        creditCardTransactionsMatrixTest[6][1] = "DEBIT";
        creditCardTransactionsMatrixTest[6][2] = string6120;


        creditCardTransactionsMatrixTest[7][0] = "9700";
        creditCardTransactionsMatrixTest[7][1] = "CREDIT";
        creditCardTransactionsMatrixTest[7][2] = string9700;

        return creditCardTransactionsMatrixTest;
    }

    /**
     * This method combine two data providers to pair each element to all elements inside
     * for example: EUR with all other currency, then USD with all other currency
     */
    private static Object[][] combine(Object[][] obj1, Object[][] obj2) {

        List<Object[]> objectList = new LinkedList<>();

        for (Object[] object1 : obj1) {
            for (Object[] object2 : obj2) {
                objectList.add(concatAll(object1, object2));
            }
        }
        return objectList.toArray(new Object[0][0]);
    }

    @SafeVarargs
    private static <T> T[] concatAll(T[] first, T[]... rest) {
        //This method by the combine combine methods above
        //calculate the total length of the final object array after the concat
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        //copy the first array to result array and then copy each array completely to result
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }

        return result;
    }

}
