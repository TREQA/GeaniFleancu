package REST_Print;

import REST_Core.TestBase;
import REST_Framework.TestData_DataProvider;
import REST_WS.FX_RatesWS;
import REST_utils.Log4Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static REST_WS.FX_RatesWS.fxRatesListResponse;
import static REST_WS.FX_RatesWS.fxRates_Print;

public class FX_Rates_Print extends TestBase {
    private static final FX_RatesWS fxRatesListResponseTest = new FX_RatesWS();

    @BeforeClass(alwaysRun = true)
    public static void fxRatesListResponseTest() {
        fxRatesListResponseTest.fxRatesGETlist();
    }

    /**
     * This method is used to get last updated time for FX rates
     */
    @Test(groups = {"all", "fxRates"})
    public void fxRates_1_printLastUpdateTime() {
        Log4Test.info("Update timestamp is: " + fxRatesListResponse.path("updateTimestamp"));
    }

    /**
     * This method is used to print fx rates list data for each currency
     */
    @Test(dataProvider = "currenciesList", dataProviderClass = TestData_DataProvider.class, groups = {"all", "fxRates"})
    public void fxRates_2_currencyPrint(String currencyNrForTestList, String currencyNameForTestList) {
        Log4Test.info("Starting printing for currency: " + currencyNameForTestList);
        //Print information
        Log4Test.info(newline +
                "Currency " + currencyNameForTestList + newline +
                "brRate: " + fxRatesListResponse.path(currencyNrForTestList + ".multiplier") + newline +
                "nbrRate: " + fxRatesListResponse.path(currencyNrForTestList + ".current.nbrRate") + newline +
                "buyingRate: " + fxRatesListResponse.path(currencyNrForTestList + ".current.buyingRate") + newline +
                "sellingRate: " + fxRatesListResponse.path(currencyNrForTestList + ".current.sellingRate") + newline +
                "History: " + newline +
                "   date1: " + fxRatesListResponse.path(currencyNrForTestList + ".history[0].date") + newline +
                "   nbrRate1: " + fxRatesListResponse.path(currencyNrForTestList + ".history[0].nbrRate") + newline +
                "   date2: " + fxRatesListResponse.path(currencyNrForTestList + ".history[1].date") + newline +
                "   nbrRate2: " + fxRatesListResponse.path(currencyNrForTestList + ".history[1].nbrRate") + newline +
                "   date3: " + fxRatesListResponse.path(currencyNrForTestList + ".history[2].date") + newline +
                "   nbrRate3: " + fxRatesListResponse.path(currencyNrForTestList + ".history[2].nbrRate") + newline +
                "   date4: " + fxRatesListResponse.path(currencyNrForTestList + ".history[3].date") + newline +
                "   nbrRate4: " + fxRatesListResponse.path(currencyNrForTestList + ".history[3].nbrRate") + newline +
                "   date5: " + fxRatesListResponse.path(currencyNrForTestList + ".history[4].date") + newline +
                "   nbrRate5: " + fxRatesListResponse.path(currencyNrForTestList + ".history[4].nbrRate"));
    }

    /**
     * This test executes exchanges rates of SELL 1 RON to fcy
     */
    @Test(dataProvider = "currenciesSell_Buy_RON", dataProviderClass = TestData_DataProvider.class, groups = {"all", "fxRates"})
    public void fxRates_3_sellPrint_RON(String currencyNameForTestSellBuyRON) {
        Log4Test.info("Starting sell print for currency: " + "RON / " + currencyNameForTestSellBuyRON);

        //Call sell WS
        FX_RatesWS fxRatesSellResponseTest = new FX_RatesWS();
        fxRatesSellResponseTest.fxRatesGETsell_buy(fxRatsSellWSpath, "RON", currencyNameForTestSellBuyRON);
        //Print information
        fxRates_Print(currencyNameForTestSellBuyRON, "RON");
    }

    /**
     * This test executes exchanges rates of BUY 1 RON to fcy
     */
    @Test(dataProvider = "currenciesSell_Buy_RON", dataProviderClass = TestData_DataProvider.class, groups = {"all", "fxRates"})
    public void fxRates_4_buyPrint_RON(String currencyNameForTestSellBuyRON) {
        Log4Test.info("Starting buy print for currency: " + "RON / " + currencyNameForTestSellBuyRON);

        //Call buy WS
        FX_RatesWS fxRatesBuyResponseTest = new FX_RatesWS();
        fxRatesBuyResponseTest.fxRatesGETsell_buy(fxRatsBuyWSpath, "RON", currencyNameForTestSellBuyRON);
        //Print information
        fxRates_Print(currencyNameForTestSellBuyRON, "RON");
    }

    /**
     * This method generates fcy exchange rates combinations test FCY to FCY - Sell
     */
    @Test(dataProvider = "currenciesSell_Buy_FCY", dataProviderClass = TestData_DataProvider.class)
    public void fxRates_5_sellPrint_FCY(String currency1, String currency2) {
        Log4Test.info("Starting sell print for currency: " + currency1 + "/ " + currency2);

        //Call sell WS
        FX_RatesWS fxRatesSellResponseTest = new FX_RatesWS();
        fxRatesSellResponseTest.fxRatesGETsell_buy(fxRatsSellWSpath, currency1, currency2);
        //Print information
        fxRates_Print(currency1, currency2);
    }

    /**
     * This method generates fcy exchange rates combinations test FCY to FCY - Buy
     */
    @Test(dataProvider = "currenciesSell_Buy_FCY", dataProviderClass = TestData_DataProvider.class, groups = {"all", "fxRates"})
    public void fxRates_6_buyPrint_FCY(String currency1, String currency2) {
        Log4Test.info("Starting buy print for currency: " + currency1 + "/ " + currency2);

        //Call buy WS
        FX_RatesWS fxRatesBuyResponseTest = new FX_RatesWS();
        fxRatesBuyResponseTest.fxRatesGETsell_buy(fxRatsBuyWSpath, currency1, currency2);
        //Print information
        fxRates_Print(currency1, currency2);
    }


}
