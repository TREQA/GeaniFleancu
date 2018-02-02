package REST_Print;

import REST_Core.TestBase;
import REST_Framework.TestData_DataProvider;
import REST_WS.InterestRatesWS;
import REST_utils.Log4Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static REST_WS.InterestRatesWS.interestRatesHistoryValuesPrintTest;
import static REST_WS.InterestRatesWS.interestRatesListResponse;

public class InterestRates_Print extends TestBase {

    private static final InterestRatesWS interestRatesListResponseTest = new InterestRatesWS();

    @BeforeClass(alwaysRun = true)
    public static void interestRatesListResponseTest() {
        interestRatesListResponseTest.interestRatesGETlist();
    }

    /**
     * This method is used to print interest rates last value for each interest rate type ROBOR/EUROBOR/LIBOR
     */
    @Test(dataProvider = "interestRates", dataProviderClass = TestData_DataProvider.class, groups = {"all", "interestRates"})
    public void interestRates_1_lastValue_Print(String interestRateNR, String interestRateName) {
        Log4Test.info("Starting last value printing for interest rate: " + interestRateName);

        //Print information
        Log4Test.info(newline +
                "Interest Rate Name " + interestRateName + newline +
                "Period: " + interestRatesListResponse.path(interestRateNR + ".rates[0].period.value") + " " + interestRatesListResponse.path(interestRateNR + ".rates[0].period.unit") + newline +
                " last value: " + interestRatesListResponse.path(interestRateNR + ".rates[0].value.value") + newline +
                "Period: " + interestRatesListResponse.path(interestRateNR + ".rates[1].period.value") + " " + interestRatesListResponse.path(interestRateNR + ".rates[1].period.unit") + newline +
                " last value: " + interestRatesListResponse.path(interestRateNR + ".rates[1].value.value") + newline +
                "Period: " + interestRatesListResponse.path(interestRateNR + ".rates[2].period.value") + " " + interestRatesListResponse.path(interestRateNR + ".rates[2].period.unit") + newline +
                " last value: " + interestRatesListResponse.path(interestRateNR + ".rates[2].value.value") + newline +
                "Period: " + interestRatesListResponse.path(interestRateNR + ".rates[3].period.value") + " " + interestRatesListResponse.path(interestRateNR + ".rates[3].period.unit") + newline +
                " last value: " + interestRatesListResponse.path(interestRateNR + ".rates[3].value.value") + newline);
    }

    /**
     * This method is used to print each history value (should be 10) for each period (1 month, 3 months, 6 months, 12 months) for each interest rate type ROBOR/EUROBOR/LIBOR
     */
    @Test(dataProvider = "interestRates", dataProviderClass = TestData_DataProvider.class, groups = {"all", "interestRates"})
    public void interestRates_2_interestRatesHistory_Print(String interestRateNR, String interestRateName) {
        String interestRatesHistoryPeriodPath = interestRateNR + ".rates";

        Log4Test.info("Starting history printing information for interest rate: " + interestRateName);
        //Information printing method call
        interestRatesHistoryValuesPrintTest(interestRatesListResponse, interestRatesHistoryPeriodPath, interestRateName, interestRateNR);
    }
}
