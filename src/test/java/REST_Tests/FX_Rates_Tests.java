package REST_Tests;

import REST_Core.TestBase;
import REST_Framework.TestData_DataProvider;
import REST_WS.FX_RatesWS;
import REST_utils.Log4Test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static REST_Framework.CommonTask.*;
import static REST_WS.FX_RatesWS.*;

public class FX_Rates_Tests extends TestBase {

    private static final FX_RatesWS fxRatesListResponseTest = new FX_RatesWS();

    @BeforeClass(alwaysRun = true)
    public static void fxRatesListResponseTest() {
        fxRatesListResponseTest.fxRatesGETlist();
    }


    /**
     * This test asserts the main elements for FX rates
     */
    @Test(groups = {"all", "fxRates"})
    public void fxRates_1_mainAssert() {

        Log4Test.info("Starting Fx Rates main test, checking last update time, currency not null and in correct order ");

        try {
            //Start assertion
            //existence assertions
            assertJsonKeyExistence(fxRatesListResponse, "updateTimestamp");
            assertJsonKeyExistence(fxRatesListResponse, "currencies");
            //assertJsonKeyExistence(fxRatesListResponse, "currencies.currency");
            //null assertion
            Assert.assertFalse(assertNotNull(fxRatesListResponse, "updateTimestamp"), "No updateTimestamp!");
            Assert.assertFalse(assertNotNull(fxRatesListResponse, "currencies"), "No currencies!");
            Assert.assertTrue(assertEqualsPathToString(fxRatesListResponse, "currencies.currency",
                    "[EUR, USD, GBP, CHF, AUD, BGN, CAD, DKK, HUF, JPY, NOK, PLN, SEK]"), "Wrong Currency order!");

        } catch (IllegalArgumentException IllegalArgumentException) {
            fxRatesListResponse.prettyPeek();
            throw IllegalArgumentException;
        }
        Log4Test.info("Fx Rates main assert test completed.");

    }

    /**
     * This test asserts the currency list content
     */
    @Test(dataProvider = "currenciesList", dataProviderClass = TestData_DataProvider.class, groups = {"all", "fxRates"})
    public void fxRates_2_currencyTest(String currencyNrForTestList, String currencyNameForTestList) {
        Log4Test.info("Starting currency test for currency: " + currencyNameForTestList);

        try {
            //Start assertion
            //existence assertions
            assertJsonKeyExistence(fxRatesListResponse, currencyNrForTestList + ".currency");
            assertJsonKeyExistence(fxRatesListResponse, currencyNrForTestList + ".multiplier");
            assertJsonKeyExistence(fxRatesListResponse, currencyNrForTestList + ".current");
            assertJsonKeyExistence(fxRatesListResponse, currencyNrForTestList + ".current.nbrRate");
            assertJsonKeyExistence(fxRatesListResponse, currencyNrForTestList + ".current.sellingRate");
            assertJsonKeyExistence(fxRatesListResponse, currencyNrForTestList + ".current.buyingRate");
            assertJsonKeyExistence(fxRatesListResponse, currencyNrForTestList + ".history");
            //null assertion
            Log4Test.test("FX Rates assert test for main currency fields for currency: " + currencyNameForTestList);
            Assert.assertEquals(fxRatesListResponse.path(currencyNrForTestList + ".currency").toString(), currencyNameForTestList);
            Assert.assertFalse(assertNotNull(fxRatesListResponse, currencyNrForTestList + ".multiplier"), "Null on " + currencyNrForTestList + ".multiplier");
            Assert.assertFalse(assertNotNull(fxRatesListResponse, currencyNrForTestList + ".current"), "Null on " + currencyNrForTestList + ".current");
            Assert.assertFalse(assertNotNull(fxRatesListResponse, currencyNrForTestList + ".current.nbrRate"), "Null on " + currencyNrForTestList + ".current.nbrRate");
            Assert.assertFalse(assertNotNull(fxRatesListResponse, currencyNrForTestList + ".current.buyingRate"), "Null on " + currencyNrForTestList + ".current.buyingRate");
            Assert.assertFalse(assertNotNull(fxRatesListResponse, currencyNrForTestList + ".current.sellingRate"), "Null on " + currencyNrForTestList + ".current.sellingRate");
            Assert.assertFalse(assertNotNull(fxRatesListResponse, currencyNrForTestList + ".history"), "Null on " + currencyNrForTestList + ".history");
            Log4Test.test("FX Rates history test, checking if date is null for each history value");
            Assert.assertFalse(assertNotNull(fxRatesListResponse, responseListValueSimpleString(fxRatesListResponse, currencyNrForTestList + ".history", currencyNrForTestList + ".history[", "].date")), "Null on " + responseListValueSimpleString(fxRatesListResponse, currencyNrForTestList + ".history", currencyNrForTestList + ".history[", "].date"));
            Log4Test.test("FX Rates history test, checking if nbrRate is null for each history value");
            Assert.assertFalse(assertNotNull(fxRatesListResponse, responseListValueSimpleString(fxRatesListResponse, currencyNrForTestList + ".history", currencyNrForTestList + ".history[", "].nbrRate")), "Null on " + responseListValueSimpleString(fxRatesListResponse, currencyNrForTestList + ".history", currencyNrForTestList + ".history[", "].nbrRate"));

        } catch (IllegalArgumentException IllegalArgumentException) {
            fxRatesListResponse.prettyPeek();
            throw IllegalArgumentException;
        }
        Log4Test.info("Ending currency test for currency: " + currencyNameForTestList);

    }

    /**
     * This test executes exchanges rates of SELL 1 RON to fcy
     */
    @Test(dataProvider = "currenciesSell_Buy_RON", dataProviderClass = TestData_DataProvider.class, groups = {"all", "fxRates"})
    public void fxRates_3_sellTest_RON(String currencyNameForTestSellBuyRON) {
        Log4Test.info("Starting sell test for currency: " + "RON / " + currencyNameForTestSellBuyRON);
        try {
            //Call sell WS
            FX_RatesWS fxRatesSellResponseTest = new FX_RatesWS();
            fxRatesSellResponseTest.fxRatesGETsell_buy(FX_RATS_SELL_WS_PATH, "RON", currencyNameForTestSellBuyRON);

            //Start Assertion
            //existence assertions
            assertJsonKeyExistence(fxRatesSellBuyResponse, "primaryCurrency");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "secondaryCurrency");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "fxRate");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "multiplier");
            //null assertion
            Log4Test.test("FX Rates assert test for sell: " + "RON / " + currencyNameForTestSellBuyRON);
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "primaryCurrency", currencyNameForTestSellBuyRON), "Null on " + "primaryCurrency");
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "secondaryCurrency", "RON"), "Null on " + "secondaryCurrency");
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "fxRate"), "Null on " + "fxRate");
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "multiplier"), "Null on " + "multiplier");

            //Assert FX rate = list rate
            String sellingRate = jsonPathStringBuilder(fxRatesListResponse, "currencies.findAll {it.currency == \"" + currencyNameForTestSellBuyRON + "\"}.current.sellingRate");
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "fxRate", sellingRate), "Wrong fxRate! Expected: " + sellingRate + ", but found: " + fxRatesSellBuyResponse.path("fxRate"));

        } catch (IllegalArgumentException IllegalArgumentException) {
            fxRatesSellBuyResponse.prettyPeek();
            throw IllegalArgumentException;
        }
        Log4Test.info("Ending sell test for currency: " + "RON / " + currencyNameForTestSellBuyRON);

    }

    /**
     * This test executes exchanges rates of BUY 1 RON to fcy
     */
    @Test(dataProvider = "currenciesSell_Buy_RON", dataProviderClass = TestData_DataProvider.class, groups = {"all", "fxRates"})
    public void fxRates_4_buyTest_RON(String currencyNameForTestSellBuyRON) {
        Log4Test.info("Starting buy test for currency: " + "RON / " + currencyNameForTestSellBuyRON);

        try {
            //Call buy WS
            FX_RatesWS fxRatesBuyResponseTest = new FX_RatesWS();
            fxRatesBuyResponseTest.fxRatesGETsell_buy(FX_RATS_BUY_WS_PATH, "RON", currencyNameForTestSellBuyRON);
            //Start Assertion
            //existence assertions
            assertJsonKeyExistence(fxRatesSellBuyResponse, "primaryCurrency");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "secondaryCurrency");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "fxRate");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "multiplier");
            //null assertion
            Log4Test.test("FX Rates assert test for buy: " + "RON / " + currencyNameForTestSellBuyRON);
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "primaryCurrency", currencyNameForTestSellBuyRON), "Equality error on " + "primaryCurrency");
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "secondaryCurrency", "RON"), "Equality error on " + "secondaryCurrency");
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "fxRate"), "Null on " + "fxRate");
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "multiplier"), "Null on " + "multiplier");

            //Assert FX rate = list rate
            String buyingRate = jsonPathStringBuilder(fxRatesListResponse, "currencies.findAll {it.currency == \"" + currencyNameForTestSellBuyRON + "\"}.current.buyingRate");
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "fxRate", buyingRate), "Wrong fxRate! Expected: " + buyingRate + ", but found: " + FX_RatesWS.fxRatesSellBuyResponse.path("fxRate"));

        } catch (IllegalArgumentException IllegalArgumentException) {
            FX_RatesWS.fxRatesSellBuyResponse.prettyPeek();
            throw IllegalArgumentException;
        }
        Log4Test.info("Ending buy test for currency: " + "RON / " + currencyNameForTestSellBuyRON);

    }

    /**
     * This method generates fcy exchange rates combinations test FCY to FCY - Sell
     */
    @Test(dataProvider = "currenciesSell_Buy_FCY", dataProviderClass = TestData_DataProvider.class, groups = {"all", "fxRates"})
    public void fxRates_5_sellTest_FCY(String currency1, String currency2) {
        Log4Test.info("Starting sell test for currency: " + currency1 + "/ " + currency2);

        try { //Call sell WS
            FX_RatesWS fxRatesSellResponseTest = new FX_RatesWS();
            fxRatesSellResponseTest.fxRatesGETsell_buy(FX_RATS_SELL_WS_PATH, currency1, currency2);
            //Start Assertion
            //existence assertions
            assertJsonKeyExistence(fxRatesSellBuyResponse, "primaryCurrency");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "secondaryCurrency");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "fxRate");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "multiplier");
            //null assertion
            Log4Test.test("FX Rates assert test for sell: " + currency1 + "/ " + currency2);
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "primaryCurrency"), "Null on " + "primaryCurrency");
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "secondaryCurrency"), "Null on " + "secondaryCurrency");
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "primaryCurrency", setPrimaryCurrency(currency1, currency2)), "Equality error on " + "primaryCurrency");
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "secondaryCurrency", setSecondaryCurrency(currency1, currency2)), "Equality error on " + "secondaryCurrency");
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "fxRate"), "Null on " + "fxRate");
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "multiplier"), "Null on " + "multiplier");

            //Assert FCY Conversion Formula FX Rate result
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "fxRate", fcyConversionFormulaResult_sell(currency1, currency2)), "Wrong fxRate! Expected: " + fcyConversionFormulaResult_sell(currency1, currency2) + ", but found: " + fxRatesSellBuyResponse.path("fxRate"));

        } catch (IllegalArgumentException IllegalArgumentException) {
            fxRatesSellBuyResponse.prettyPeek();
            throw IllegalArgumentException;
        }
        Log4Test.info("Ending sell test for currency: " + currency1 + "/ " + currency2);
    }

    /**
     * This method generates fcy exchange rates combinations test FCY to FCY - Buy
     */
    @Test(dataProvider = "currenciesSell_Buy_FCY", dataProviderClass = TestData_DataProvider.class, groups = {"all", "fxRates"})
    public void fxRates_6_buyTest_FCY(String currency1, String currency2) {
        Log4Test.info("Starting buy test for currency: " + currency1 + "/ " + currency2);

        try {
            //Call buy WS
            FX_RatesWS fxRatesBuyResponseTest = new FX_RatesWS();
            fxRatesBuyResponseTest.fxRatesGETsell_buy(FX_RATS_BUY_WS_PATH, currency1, currency2);
            //Start Assertion
            Log4Test.test("FX Rates assert test for buy: " + currency1 + "/ " + currency2);
            //existence assertions
            assertJsonKeyExistence(fxRatesSellBuyResponse, "primaryCurrency");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "secondaryCurrency");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "fxRate");
            assertJsonKeyExistence(fxRatesSellBuyResponse, "multiplier");
            //null assertion
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "primaryCurrency"), "Null on " + "primaryCurrency");
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "secondaryCurrency"), "Null on " + "secondaryCurrency");
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "primaryCurrency", setPrimaryCurrency(currency1, currency2)), "Equality error on " + "primaryCurrency");
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "secondaryCurrency", setSecondaryCurrency(currency1, currency2)), "Equality error on " + "secondaryCurrency");
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "fxRate"), "Null on " + "fxRate");
            Assert.assertFalse(assertNotNull(fxRatesSellBuyResponse, "multiplier"), "Null on " + "multiplier");

            //Assert FCY Conversion Formula FX Rate result
            Assert.assertTrue(assertEqualsPathToString(fxRatesSellBuyResponse, "fxRate", fcyConversionFormulaResult_buy(currency1, currency2)), "Wrong fxRate! Expected: " + fcyConversionFormulaResult_buy(currency1, currency2) + ", but found: " + fxRatesSellBuyResponse.path("fxRate"));

        } catch (IllegalArgumentException IllegalArgumentException) {
            FX_RatesWS.fxRatesSellBuyResponse.prettyPeek();
            throw IllegalArgumentException;
        }
        Log4Test.info("Ending buy test for currency: " + currency1 + "/ " + currency2);
    }

}

