package REST_WS;

import REST_Core.TestBase;
import REST_utils.Log4Test;
import com.jayway.restassured.response.Response;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.collections.map.MultiKeyMap;

import static REST_Framework.CommonTask.*;

public class InterestRatesWS extends TestBase {

    public static Response interestRatesListResponse;

    /**
     * Method to expose interest rates period history path for assertion
     */

    public static MultiKeyMap interestRatesHistoryAssertTest(Response wsResponse, String interestRateNR, String interestRateName) {
    /*
           categories.findAll {it.name == "ROBOR"}.rates[0].interestRateChangeList[0].value.value
                  $.categories[?(@.name=="ROBOR")].rates[0].interestRateChangeList[0].value.value
     */
        String interestPeriod = null;
        try {
            Log4Test.test("Start history assertion for period " + interestRateName);
            //setting list
            MultiKeyMap.decorate(new LinkedMap());
            MultiKeyMap interestRatesHistoryListPath = MultiKeyMap.decorate(new LinkedMap());

            Log4Test.info("Get periods size for assert");
            int periodList = wsResponse.jsonPath().getList(interestRateNR + ".rates").size();

            for (int i = 0; i < periodList; i++) {
                System.out.println(newline);
                interestPeriod = jsonPathStringBuilder(interestRatesListResponse, "categories.find {it.name == \"" + interestRateName + "\"}.rates[" + i + "].period.value");
                Log4Test.info("Setting period list " + interestPeriod + " Month.");

                int historyListSize = responseElementListSize(interestRatesListResponse,
                        responseListValueSimpleString(interestRatesListResponse, "categories.find {it.name == \"" + interestRateName + "\"}.rates", "categories.find {it.name == \"" + interestRateName + "\"}.rates[", "].interestRateChangeList"));

                for (int x = 0; x < historyListSize; x++) {
                    Log4Test.info("Setting period list " + interestPeriod + " Month " + "history nr: " + x);
                    interestRatesHistoryListPath.put(i, x,
                            "categories.find {it.name == \"" + interestRateName + "\"}.rates[" + i + "].interestRateChangeList[" + x + "].value.value");
                }
            }
            return interestRatesHistoryListPath;
        } catch (Exception interestRatesHistoryTestException) {
            Log4Test.test("interestRatesHistoryTest error at: " + interestRateName + " " + interestPeriod + " Month.");
            throw interestRatesHistoryTestException;
        }
    }

    /**
     * Method to expose interest rates period history for print
     */
    public static void interestRatesHistoryValuesPrintTest(Response wsResponse, String elementListPathComplete, String interestRateName, String interestRateNR) {

        String interestRatesHistoryListPeriod = null;
        try {

            Log4Test.test("Get periods size");
            int responseElementList = wsResponse.jsonPath().getList(elementListPathComplete).size();

            for (int x = 0; x < responseElementList; x++) {

                //define variables
                String interestRatesHistoryPathFullPeriod = interestRateNR + ".rates[" + x + "].interestRateChangeList";
                String interestRatesHistoryValuePath1Period = interestRateNR + ".rates[" + x + "].interestRateChangeList[";
                String interestRatesHistoryValuePath2Period = "].value.value";
                String interestRatesHistoryValuePath2PeriodDate = "].date";

                Log4Test.test("Set periods message");
                interestRatesHistoryListPeriod = "Interest rates " + interestRateName + " history for period " +
                        wsResponse.path(interestRateNR + ".rates[" + x + "].period.value") + " " +
                        wsResponse.path(interestRateNR + ".rates[" + x + "].period.unit") + ", date: " +
                        responseListValueSimplePath(interestRatesListResponse, interestRatesHistoryPathFullPeriod, interestRatesHistoryValuePath1Period, interestRatesHistoryValuePath2PeriodDate);

                //Start print method for period x
                Log4Test.test("Start printing");
                responseListPrintValues(interestRatesListResponse, interestRatesHistoryPathFullPeriod, interestRatesHistoryValuePath1Period, interestRatesHistoryValuePath2Period, interestRatesHistoryListPeriod);
            }

            Log4Test.info(newline);

        } catch (Exception interestRatesHistoryTestException) {
            Log4Test.test("interestRatesHistoryTest error at:" + interestRatesHistoryListPeriod);
            throw interestRatesHistoryTestException;
        }
    }

    /**
     * General method to call interest rates WS and store teh response in interestRatesListResponse
     */
    public void interestRatesGETlist() {

        Log4Test.info("Environment used for Interest Rates is: " + env);
        try {

            interestRatesListResponse = wsGETnoAuth(interestRatsListWSpath);

        } catch (Exception interestRatesListException) {
            Log4Test.test("interestRatesGETlist error");
            throw interestRatesListException;
        }
    }


    /*
     * Old method, no longer used but kept as reference for future use
     */
    /*public static void interestRatesHistoryAssertTest(Response wsResponse, String interestRateNR) {

        String interestRatesHistoryListPeriod = null;
        try {

            String elementListPathComplete = interestRateNR + ".rates";


            Log4Test.test("Get periods size");
            int responseElementList = wsResponse.jsonPath().getList(elementListPathComplete).size();

            for (int x = 0; x < responseElementList; x++) {

                //define variables

                String interestRatesHistoryPathFullPeriod = interestRateNR + ".rates[" + x + "].interestRateChangeList";
                String interestRatesHistoryValuePath1Period = interestRateNR + ".rates[" + x + "].interestRateChangeList[";
                String interestRatesHistoryValuePath2Period = "]";
                interestRatesHistoryListPeriod = "Interest rates " + interestRateNR + " history for period " + wsResponse.path(interestRateNR + ".rates[" + x + "].period.value") + " " + wsResponse.path(interestRateNR + ".rates[" + x + "].period.unit");

                //Start assert method for period x
                Log4Test.test("Start history assertion for period " + wsResponse.path(interestRateNR + ".rates[" + x + "].period.value") + " " + wsResponse.path(interestRateNR + ".rates[" + x + "].period.unit"));
                responseListAssert(interestRatesListResponse, interestRatesHistoryPathFullPeriod, interestRatesHistoryValuePath1Period, interestRatesHistoryValuePath2Period, interestRatesHistoryListPeriod);
            }
            Log4Test.info(newline);

        } catch (Exception interestRatesHistoryTestException) {
            Log4Test.test("interestRatesHistoryTest error at: " + interestRatesHistoryListPeriod);
            throw interestRatesHistoryTestException;
        }
    }*/


}