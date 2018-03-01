package REST_WS;

import REST_Core.TestBase;
import REST_utils.Log4Test;
import com.jayway.restassured.response.Response;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

import static REST_Framework.CommonTask.jsonPathStringBuilder;
import static REST_Framework.CommonTask.wsGETnoAuth;

public class FX_RatesWS extends TestBase {

    public static Response fxRatesListResponse;
    public static Response fxRatesSellBuyResponse;

    /**
     * This method is used for printing information from WS result
     */
    public static void fxRates_Print(String currency1, String Currency2) {
        //Print information
        Log4Test.info(NEW_LINE +
                "PrimaryCurrency " + currency1 + NEW_LINE +
                "SecondaryCurrency " + Currency2 + NEW_LINE +
                "fxRate: " + fxRatesSellBuyResponse.path("fxRate") + NEW_LINE +
                "multiplier: " + fxRatesSellBuyResponse.path("multiplier"));
    }

    /**
     * This method is used to calculate the FCY fx rate based on formula:
     * FROM currency <buyingRate> / TO currency <sellingRate> (ARO-11)
     * multiplier is used only for front end display
     */
    public static String fcyConversionFormulaResult_sell(String currency1, String currency2) {

        try {
            //setting the values from fxRatesListResponse for the specific currency
            String primaryCurrency_buyingRate = jsonPathStringBuilder(fxRatesListResponse, "currencies.findAll {it.currency == \"" + setPrimaryCurrency(currency1, currency2) + "\"}.current." + setPrimaryOperation_sell(currency1, currency2));
            String secondaryCurrency_sellingRate = jsonPathStringBuilder(fxRatesListResponse, "currencies.findAll {it.currency == \"" + setSecondaryCurrency(currency1, currency2) + "\"}.current." + setSecondaryOperation_sell(currency1, currency2));
            //setting decimal format
            DecimalFormat df = new DecimalFormat("0.0###");
            //converting values to BigDecimal
            BigDecimal primaryCurrency_buyingRate_bd = new BigDecimal(primaryCurrency_buyingRate);
            BigDecimal secondaryCurrency_sellingRate_bd = new BigDecimal(secondaryCurrency_sellingRate);
            //calculating the formula
            BigDecimal fcyFormula = primaryCurrency_buyingRate_bd.divide(secondaryCurrency_sellingRate_bd, 4, RoundingMode.HALF_UP);

            return df.format(fcyFormula);

        } catch (Exception fcyConversionFormulaResult_sellException) {
            Log4Test.error("fcyConversionFormulaResult_sell error");
            throw fcyConversionFormulaResult_sellException;
        }
    }

    /**
     * This method is used to calculate the FCY fx rate based on formula:
     * TO currency <sellingRate> / FROM currency <buyingRate> (ARO-11)
     * multiplier is used only for front end display
     */

    public static String fcyConversionFormulaResult_buy(String currency1, String currency2) {

        try {
            //setting the values from fxRatesListResponse for the specific currency
            String primaryCurrency_buyingRate = jsonPathStringBuilder(fxRatesListResponse, "currencies.findAll {it.currency == \"" + setPrimaryCurrency(currency1, currency2) + "\"}.current." + setPrimaryOperation_buy(currency1, currency2));
            String secondaryCurrency_sellingRate = jsonPathStringBuilder(fxRatesListResponse, "currencies.findAll {it.currency == \"" + setSecondaryCurrency(currency1, currency2) + "\"}.current." + setSecondaryOperation_buy(currency1, currency2));
            //setting decimal format
            DecimalFormat df = new DecimalFormat("0.0###");
            //converting values to BigDecimal
            BigDecimal primaryCurrency_buyingRate_bd = new BigDecimal(primaryCurrency_buyingRate);
            BigDecimal secondaryCurrency_sellingRate_bd = new BigDecimal(secondaryCurrency_sellingRate);
            //calculating the formula
            BigDecimal fcyFormula = primaryCurrency_buyingRate_bd.divide(secondaryCurrency_sellingRate_bd, 4, RoundingMode.HALF_UP);
            return df.format(fcyFormula);

        } catch (Exception fcyConversionFormulaResult_buyException) {
            Log4Test.error("fcyConversionFormulaResult_buy error");
            throw fcyConversionFormulaResult_buyException;
        }
    }

    /**
     * Rules for setting principal currency (first rule available will be applied):
     * <p>
     * RON - FCY both ways possible -> 1 FCY = x RON
     * EUR - FCY both ways possible -> 1 EUR = x FCY
     * USD - AUD both ways possible -> 1 AUD = x USD
     * USD - FCY both ways possible -> 1 USD = x FCY
     * FCY1 - FCY2 both ways possible:
     * * if  curs bnr FCY1 > curs bnr FCY2 -> 1 FCY1 = x FCY2
     * * if  curs bnr FCY2 > curs bnr FCY1 -> 1 FCY2 = x FCY1
     * /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * /**
     * This method is used to set the primary currency based on rules above
     */
    public static String setPrimaryCurrency(String currency1, String currency2) {

        float currency1_nbrRate = Float.parseFloat(jsonPathStringBuilder(fxRatesListResponse, "currencies.findAll {it.currency == \"" + currency1 + "\"}.current.nbrRate"));
        float currency2_nbrRate = Float.parseFloat(jsonPathStringBuilder(fxRatesListResponse, "currencies.findAll {it.currency == \"" + currency2 + "\"}.current.nbrRate"));

        if (
                (Objects.equals(currency1, "USD") && Objects.equals(currency2, "AUD")) ||
                        Objects.equals(currency2, "EUR") ||
                        (Objects.equals(currency2, "USD") && !Objects.equals(currency1, "EUR") && !Objects.equals(currency1, "AUD"))) {
            return currency2;
        } else if (
                Objects.equals(currency1, "EUR") ||
                        (Objects.equals(currency1, "USD") && !Objects.equals(currency2, "EUR")) ||
                        (Objects.equals(currency1, "AUD") && Objects.equals(currency2, "USD")) ||
                        currency1_nbrRate > currency2_nbrRate) {
            return currency1;
        } else {
            return currency2;
        }
    }

    /**
     * This method is used to set the secondary currency based on rules above
     */
    public static String setSecondaryCurrency(String currency1, String currency2) {

        float currency1_nbrRate = Float.parseFloat(jsonPathStringBuilder(fxRatesListResponse, "currencies.findAll {it.currency == \"" + currency1 + "\"}.current.nbrRate"));
        float currency2_nbrRate = Float.parseFloat(jsonPathStringBuilder(fxRatesListResponse, "currencies.findAll {it.currency == \"" + currency2 + "\"}.current.nbrRate"));

        if (
                (Objects.equals(currency1, "USD") && Objects.equals(currency2, "AUD")) ||
                        Objects.equals(currency2, "EUR") ||
                        (Objects.equals(currency2, "USD") && !Objects.equals(currency1, "EUR") && !Objects.equals(currency1, "AUD"))) {
            return currency1;
        } else if (
                Objects.equals(currency1, "EUR") ||
                        (Objects.equals(currency1, "USD") && !Objects.equals(currency2, "EUR")) ||
                        (Objects.equals(currency1, "AUD") && Objects.equals(currency2, "USD")) ||
                        currency1_nbrRate > currency2_nbrRate) {
            return currency2;
        } else {
            return currency1;
        }
    }

    /**
     * This method is used to set the primary value (<buyingRate> or <sellingRate>) for operation (SELL) based on rules above
     */
    private static String setPrimaryOperation_sell(String currency1, String currency2) {
        String setPrimaryOperation = setPrimaryCurrency(currency1, currency2);

        if (setPrimaryOperation.equals(currency1)) {
            return "buyingRate";
        } else {
            return "sellingRate";
        }
    }

    /**
     * This method is used to set the secondary value (<buyingRate> or <sellingRate>) for operation (SELL) based on rules above
     */
    private static String setSecondaryOperation_sell(String currency1, String currency2) {
        String setSecondaryOperation = setSecondaryCurrency(currency1, currency2);

        if (setSecondaryOperation.equals(currency2)) {
            return "sellingRate";
        } else {
            return "buyingRate";
        }
    }

    /**
     * This method is used to set the primary value (<buyingRate> or <sellingRate>) for operation (BUY) based on rules above
     */
    private static String setPrimaryOperation_buy(String currency1, String currency2) {
        String setPrimaryOperation = setPrimaryCurrency(currency1, currency2);

        if (setPrimaryOperation.equals(currency1)) {
            return "sellingRate";
        } else {
            return "buyingRate";
        }
    }

    /**
     * This method is used to set the secondary value (<buyingRate> or <sellingRate>) for operation (BUY) based on rules above
     */
    private static String setSecondaryOperation_buy(String currency1, String currency2) {
        String setSecondaryOperation = setSecondaryCurrency(currency1, currency2);

        if (setSecondaryOperation.equals(currency2)) {
            return "buyingRate";
        } else {
            return "sellingRate";
        }
    }

    /**
     * This method is used to call the WS and stores the response in "fxRatesListResponse"
     */

    public void fxRatesGETlist() {

        Log4Test.info("Environment used for FX Rates is: " + ENV);
        try {

            fxRatesListResponse = wsGETnoAuth(FX_RATS_LIST_WS_PATH);

        } catch (Exception fxRatesListException) {
            Log4Test.error("fxRatesGETlist error");
            throw fxRatesListException;

        }
    }

    /**
     * This method is used to call the WS for BUY or SELL useing various currency
     * A check is made to see if "currency1" is equal to "currency2"
     * if the result is YES then the test will be skipped and marked as passed     *
     */
    public void fxRatesGETsell_buy(String wsPath, String currency1, String currency2) {

        Log4Test.info("Environment used for FX Rates Sell is: " + ENV);
        try {
            if (currency1.equals(currency2)) {
                throw new SkipException("Skipped because same currency");
            }
            fxRatesSellBuyResponse = wsGETnoAuth(wsPath + currency1 + "/" + currency2);

        } catch (SkipException SkipException) {
            Log4Test.info("Skipped this test because same currency! Also marking as success.");
            Reporter.getCurrentTestResult().setStatus(ITestResult.SUCCESS);
            throw SkipException;
        } catch (Exception fxRatesSellException) {
            Log4Test.error("fxRatesGETsell/_buy error");
            throw fxRatesSellException;

        }
    }
}
