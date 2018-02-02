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

        accountsForTransactions[0][0] = accountID_20; // account id
        accountsForTransactions[0][1] = "20"; // account type

        accountsForTransactions[1][0] = accountID_26; // account id
        accountsForTransactions[1][1] = "26"; // account type

        accountsForTransactions[2][0] = accountID_30; // account id
        accountsForTransactions[2][1] = "30"; // account type

        accountsForTransactions[3][0] = accountID_50; // account id
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
