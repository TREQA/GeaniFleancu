package REST_Tests;

import REST_Core.TestBase;
import REST_Framework.TestData_DataProvider;
import REST_WS.InterestRatesWS;
import REST_utils.Log4Test;
import org.apache.commons.collections.map.MultiKeyMap;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static REST_Framework.CommonTask.*;
import static REST_WS.InterestRatesWS.interestRatesHistoryAssertTest;
import static REST_WS.InterestRatesWS.interestRatesListResponse;

public class InterestRates_Tests extends TestBase {

    private static final InterestRatesWS interestRatesListResponseForTest = new InterestRatesWS();

    @BeforeClass(alwaysRun = true)
    public static void interestRatesListResponseTest() {
        interestRatesListResponseForTest.interestRatesGETlist();
    }


    /**
     * This method asserts the main elements of interest rates
     */
    @Test(dataProvider = "interestRates", dataProviderClass = TestData_DataProvider.class, groups = {"all", "interestRates"})
    public void interestRates_1_mainAssertTest(String interestRateNR, String interestRateName) {

        Log4Test.info("Starting Fx Rates main assert test for: " + interestRateName + NEW_LINE);

        //Start assertion
        Log4Test.test("Assert that categories exist.");
        assertJsonKeyExistence(interestRatesListResponse, "categories");
        softAssert.assertFalse(assertNotNull(interestRatesListResponse, "categories"), "Null at " + interestRateName + "categories");

        Log4Test.test("Assert that interest rate name exist.");
        assertJsonKeyExistence(interestRatesListResponse, interestRateNR + ".name");
        softAssert.assertFalse(assertNotNull(interestRatesListResponse, interestRateNR + ".name"), "Null at " + interestRateName + interestRateNR + ".name");

        Log4Test.test("Assert that interest rates exist.");
        assertJsonKeyExistence(interestRatesListResponse, interestRateNR + ".rates");
        softAssert.assertFalse(assertNotNull(interestRatesListResponse, interestRateNR + ".rates"), "Null at " + interestRateName + interestRateNR + ".rates");

        softAssert.assertAll();

        Log4Test.info("Interest Rates main assert test completed for " + interestRateName + "." + NEW_LINE);

    }


    /**
     * This method asserts the details of each interest rate type ROBOR/EUROBOR/LIBOR
     */
    @Test(dataProvider = "interestRates", dataProviderClass = TestData_DataProvider.class, groups = {"all", "interestRates"})
    public void interestRates_2_detailAssert(String interestRateNR, String interestRateName) {

        Log4Test.info("Starting detail assertion for interest rate: " + interestRateName);

        //Start assertion
        Log4Test.test("Assert interest rate name is correct");
        assertJsonKeyExistence(interestRatesListResponse, interestRateNR + ".rates");
        softAssert.assertTrue(assertEqualsPathToString(interestRatesListResponse, interestRateNR + ".name", interestRateName));

        Log4Test.info(NEW_LINE);
        Log4Test.info("Assert rates category is not null");

        Log4Test.test("Assert period list size");
        assertJsonKeyExistence(interestRatesListResponse, interestRateNR + ".rates");
        softAssert.assertEquals(responseElementListSize(interestRatesListResponse, interestRateNR + ".rates"), 4, "Interest rates period list should contain 4 elements");

        Log4Test.test("Assertion test for each period containing fields to not be null");
        assertJsonKeyExistence(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "]"));
        softAssert.assertFalse(assertNotNull(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "]")), "Null on " + responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "]"));

        Log4Test.test("Assertion test for each period.period containing fields to not be null");
        assertJsonKeyExistence(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period"));
        softAssert.assertFalse(assertNotNull(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period")), "Null on " + responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period"));

        Log4Test.test("Assertion test for each period.period.value containing fields to not be null");
        assertJsonKeyExistence(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period.value"));
        softAssert.assertFalse(assertNotNull(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period.value")), "Null on " + responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period.value"));

        Log4Test.test("Assertion test for each period.period.unit containing fields to not be null");
        assertJsonKeyExistence(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period.unit"));
        softAssert.assertFalse(assertNotNull(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period.unit")), "Null on " + responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period.unit"));

        Log4Test.test("Assertion test for each period.value containing fields to not be null");
        assertJsonKeyExistence(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].value"));
        softAssert.assertFalse(assertNotNull(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].value")), "Null on " + responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].value"));

        Log4Test.test("Assertion test for each period.value.value containing fields to not be null");
        assertJsonKeyExistence(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].value.value"));
        softAssert.assertFalse(assertNotNull(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].value.value")), "Null on " + responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].value.value"));

        Log4Test.test("Assertion test for each period.value.unit containing fields to not be null");
        assertJsonKeyExistence(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].value.unit"));
        softAssert.assertFalse(assertNotNull(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].value.unit")), "Null on " + responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].value.unit"));

        Log4Test.test("Assertion test for each period.interestRateChangeList containing fields to not be null");
        assertJsonKeyExistence(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].interestRateChangeList"));
        softAssert.assertFalse(assertNotNull(interestRatesListResponse, responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].interestRateChangeList")), "Null on " + responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].interestRateChangeList"));

        Log4Test.info(NEW_LINE);

        softAssert.assertAll();

        Log4Test.info("Ending detail assertion for interest rate: " + interestRateName + NEW_LINE);
    }

    /**
     * This method asserts the last rate value of each interest rate type ROBOR/EUROBOR/LIBOR for each period (1 month, 3 month, 6 month, 12 month)
     */
    @Test(dataProvider = "interestRates", dataProviderClass = TestData_DataProvider.class, groups = {"all", "interestRates"})
    public void interestRates_3_lastValueTest(String interestRateNR, String interestRateName) {

        Log4Test.info("Starting last value test for interest rate: " + interestRateName);

        //Start assertion
        Log4Test.test("Verify period value = last interest rate value");
        Assert.assertTrue(assertEqualsPathToPath(interestRatesListResponse,
                responseListValueSimpleString(interestRatesListResponse,
                        interestRateNR + ".rates",
                        interestRateNR + ".rates[",
                        "].value.value"),
                responseListValueSimpleString(interestRatesListResponse,
                        interestRateNR + ".rates",
                        interestRateNR + ".rates[",
                        "].interestRateChangeList[0].value.value")),
                "Period value != last interest rate value on " + responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].value.value"));

        Log4Test.info("Ending last value test for interest rate: " + interestRateName + NEW_LINE);
    }


    /**
     * This method asserts the each interest rate history value (should be 10) of each interest rate type ROBOR/EUROBOR/LIBOR for each period 1 month, 3 month, 6 month, 12 month) to not be null
     */
    @Test(dataProvider = "interestRates", dataProviderClass = TestData_DataProvider.class, groups = {"all", "interestRates"})
    public void interestRates_4_interestRatesHistoryTest(String interestRateNR, String interestRateName) {

        Log4Test.info("Starting history test for interest rate: " + interestRateName + NEW_LINE);

        Log4Test.test("Assert interest rates history list size, it should be 10");
        Assert.assertEquals(responseElementListSize(interestRatesListResponse,
                responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].interestRateChangeList")), 10, "Interest rates " + interestRateName + " history list period " + responseListValueSimplePath(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period.value") + " " + responseListValueSimplePath(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period.unit") + " should contain 10 elements!");

        Log4Test.test("Assert ea interest rates period each history content to not be null");
        MultiKeyMap InterestRatesHistoryPeriodMap = interestRatesHistoryAssertTest(interestRatesListResponse, interestRateNR, interestRateName);

        //period 1
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(0, 0))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(0, 0)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(0, 1))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(0, 1)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(0, 2))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(0, 2)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(0, 3))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(0, 3)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(0, 4))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(0, 4)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(0, 5))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(0, 5)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(0, 6))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(0, 6)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(0, 7))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(0, 7)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(0, 8))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(0, 8)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(0, 9))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(0, 9)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(0, 10))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(0, 10)));
        //period 2
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(1, 0))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(1, 0)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(1, 1))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(1, 1)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(1, 2))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(1, 2)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(1, 3))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(1, 3)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(1, 4))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(1, 4)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(1, 5))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(1, 5)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(1, 6))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(1, 6)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(1, 7))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(1, 7)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(1, 8))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(1, 8)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(1, 9))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(1, 9)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(1, 10))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(1, 10)));
        //period 3
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(2, 0))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(2, 0)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(2, 1))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(2, 1)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(2, 2))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(2, 2)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(2, 3))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(2, 3)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(2, 4))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(2, 4)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(2, 5))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(2, 5)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(2, 6))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(2, 6)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(2, 7))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(2, 7)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(2, 8))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(2, 8)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(2, 9))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(2, 9)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(2, 10))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(2, 10)));
        //period 4
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(3, 0))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(3, 0)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(3, 1))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(3, 1)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(3, 2))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(3, 2)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(3, 3))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(3, 3)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(3, 4))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(3, 4)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(3, 5))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(3, 5)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(3, 6))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(3, 6)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(3, 7))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(3, 7)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(3, 8))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(3, 8)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(3, 9))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(3, 9)));
        Assert.assertFalse(assertNotNull(interestRatesListResponse, String.valueOf(InterestRatesHistoryPeriodMap.get(3, 10))), "NULL error at: " + String.valueOf(InterestRatesHistoryPeriodMap.get(3, 10)));

        Log4Test.info("Ending history test for interest rate: " + interestRateName + NEW_LINE);
    }

    /*
     * Old method, no longer used but kept as reference for future use
     */
    /*@Test(dataProvider = "interestRates", dataProviderClass = TestData_DataProvider.class, groups = {"all", "interestRates"})
    public void interestRates_x_interestRatesHistoryTest(String interestRateNR, String interestRateName) {

        Log4Test.info("Starting history test for interest rate: " + interestRateName + NEW_LINE);

        Log4Test.test("Assert ea interest rates period each history content to not be null");

        interestRatesHistoryAssertTest(interestRatesListResponse, interestRateNR);

        Log4Test.test("Assert interest rates history list size, it should be 10");
        Assert.assertEquals(responseElementListSize(interestRatesListResponse,
                responseListValueSimpleString(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].interestRateChangeList")), 10, "Interest rates " + interestRateName + " history list period " + responseListValueSimplePath(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period.value") + " " + responseListValueSimplePath(interestRatesListResponse, interestRateNR + ".rates", interestRateNR + ".rates[", "].period.unit") + " should contain 10 elements!");

        Log4Test.info("Ending history test for interest rate: " + interestRateName + NEW_LINE);
    }*/
}

