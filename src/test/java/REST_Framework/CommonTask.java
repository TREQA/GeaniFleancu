package REST_Framework;

import REST_Core.TestBase;
import REST_utils.Log4Test;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.testng.Assert;

import java.io.*;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class CommonTask extends TestBase {

    /**
     * This method is used for all GET calls using Authorization token
     */
    public static Response wsGET(String wsPath, Map<String, String> cookies) {

        Response wsGetResponse;
        try {
            wsGetResponse = given()
                    .urlEncodingEnabled(false)
                    .header("Content-Type", appJSON)
                    .cookies(cookies)
                    //.log().body()
                    //.log().all()
                    .log().path()
                    //.log().body()
                    //.log().parameters()
                    .log().method()

                    .when()
                    .get(baseURI + basePath + wsPath)

                    .then()
                    .log().status()
                    //.log().all()
                    .extract().response();

            if (wsGetResponse.statusCode() != 200) {
                Log4Test.error(baseURI + basePath + wsPath + " status not 200. Response is :");
                wsGetResponse.prettyPrint();
                fail();
            }
        } catch (Exception wsGetException) {
            Log4Test.error("wsGET error");
            throw wsGetException;
        }

        return wsGetResponse;
    }

    /**
     * This method is used for all GET calls using Authorization token &
     * it also has parameters function
     */
    public static Response wsGETwithParam(String wsPath, Map<String, String> cookies, String params) {


        Response wsGetResponse;
        try {
            wsGetResponse = given()
                    .urlEncodingEnabled(false)
                    .header("Content-Type", appJSON)
                    .cookies(cookies)
                    .param(params)
                    //.log().body()
                    //.log().headers()
                    //.log().all()
                    .log().path()
                    //.log().body()
                    .log().parameters()
                    .log().method()


                    .when()
                    .get(baseURI + basePath + wsPath)

                    .then()
                    .log().status()
                    //.log().all()
                    .extract().response();

            if (wsGetResponse.statusCode() != 200) {
                Log4Test.error(baseURI + basePath + wsPath + "?" + params + " status not 200. Response is :");
                wsGetResponse.prettyPrint();
                fail();
            }
        } catch (Exception wsGetException) {
            Log4Test.error("wsGETwithParam error");
            throw wsGetException;
        }

        return wsGetResponse;
    }

    /**
     * This method is used for all GET calls without Authorization token
     */
    public static Response wsGETnoAuth(String wsPath) {


        Response wsGetNoAuthResponse;
        try {
            wsGetNoAuthResponse = given()
                    .urlEncodingEnabled(false)
                    .header("Content-Type", appJSON)
                    //.log().body()
                    //.log().all()
                    .log().path()
                    //.log().body()
                    //.log().parameters()
                    .log().method()

                    .when()
                    .get(baseURI + basePath + wsPath)

                    .then()
                    .log().status()
                    //.log().all()
                    .extract().response();

            if (wsGetNoAuthResponse.statusCode() != 200) {
                Log4Test.error(baseURI + basePath + wsPath + " status not 200. Response is :");
                wsGetNoAuthResponse.prettyPrint();
                fail();
            }
        } catch (Exception wsGetNoAuthException) {
            Log4Test.error("wsGET error");
            throw wsGetNoAuthException;
        }

        return wsGetNoAuthResponse;
    }

    /**
     * This method is used for all POST calls using Authorization token
     */
    public static Response wsPOST(String contentType, String wsPostRequestBody, String wsPath, Map<String, String> cookies) {


        Response wsPostResponse;
        try {
            wsPostResponse = given()
                    .urlEncodingEnabled(false)
                    .header("Content-Type", contentType)
                    .cookies(cookies)
                    .body(wsPostRequestBody)
                    //.log().body()
                    //.log().all()
                    .log().path()
                    .log().body()
                    //.log().parameters()
                    .log().method()

                    .when()
                    .post(baseURI + basePath + wsPath)

                    .then()
                    .log().status()
                    //.log().all()
                    .extract().response();

            if (wsPostResponse.statusCode() != 200) {
                Log4Test.error(baseURI + basePath + wsPath + " status not 200. Response is :");
                wsPostResponse.prettyPrint();
                fail();
            }
        } catch (
                Exception wsPostException)

        {
            Log4Test.error("wsPOST error");
            throw wsPostException;
        }

        return wsPostResponse;
    }

    /**
     * This method is used for all POT calls using Authorization token &
     * multi part form but only file upload
     */
    public static Response wsPOSTmultiPartOnlyFile(String contentType, String wsPath, Map<String, String> cookies, String fileFormKEY, String filePath, String mimeTYPE) {

        Response wsPostResponse;
        try {
            wsPostResponse = given()
                    .urlEncodingEnabled(false)
                    .header("Content-Type", contentType)
                    .cookies(cookies)
                    //.body(wsPostRequestBody)
                    .multiPart(fileFormKEY, new File(filePath), mimeTYPE)
                    //.log().body()
                    //.log().all()
                    .log().path()
                    //.log().body()
                    .log().parameters()
                    .log().method()

                    .when()
                    .post(baseURI + basePath + wsPath)

                    .then()
                    .log().status()
                    //.log().all()
                    .extract().response();

            if (wsPostResponse.statusCode() != 200) {
                Log4Test.error(baseURI + basePath + wsPath + " status not 200. Response is :");
                wsPostResponse.prettyPrint();
                fail();
            }
        } catch (
                Exception wsPostException)

        {
            Log4Test.error("wsPOSTmultiPartOnlyFile error");
            throw wsPostException;
        }

        return wsPostResponse;
    }

    /**
     * This method is used for all POTS calls using Authorization token &
     * multi part form file + body
     */

    public static Response wsPOSTmultiPartBodyAndFile(String contentType, String wsPath, Map<String, String> cookies, Map wsPostRequestBody, String bodyFormKEY, String fileFormKEY, String filePath, String mimeTYPE) {

        Response wsPostResponse;
        try {
            wsPostResponse = given()
                    .urlEncodingEnabled(false)
                    .header("Content-Type", contentType)
                    .cookies(cookies)
                    .multiPart(bodyFormKEY, wsPostRequestBody)
                    .multiPart(fileFormKEY, new File(filePath), mimeTYPE)
                    //.log().body()
                    //.log().all()
                    .log().path()
                    //.log().body()
                    .log().parameters()
                    .log().method()

                    .when()
                    .post(baseURI + basePath + wsPath)

                    .then()
                    .log().status()
                    //.log().all()
                    .extract().response();

            if (wsPostResponse.statusCode() != 200) {
                Log4Test.error(baseURI + basePath + wsPath + " status not 200. Response is :");
                wsPostResponse.prettyPrint();
                fail();
            }
        } catch (
                Exception wsPostException)

        {
            Log4Test.error("wsPOSTmultiPartBodyAndFile error");
            throw wsPostException;
        }

        return wsPostResponse;
    }

    /**
     * This method is used for all POST calls without Authorization token
     */

    public static Response wsPOSTnoAuth(String contentType, Map wsPostNoAuthRequestBody, String wsPath) {

        Response wsPostNoAuthResponse;
        try {
            wsPostNoAuthResponse = given()
                    .urlEncodingEnabled(false)
                    .header("Content-Type", contentType)
                    .body(wsPostNoAuthRequestBody)
                    //.log().body()
                    //.log().all()
                    .log().path()
                    .log().body()
                    //.log().parameters()
                    .log().method()

                    .when()
                    .post(baseURI + basePath + wsPath)

                    .then()
                    .log().status()
                    //.log().all()
                    .extract().response();

            if (wsPostNoAuthRequestBody.containsValue(loginUsername + "1") && wsPostNoAuthResponse.statusCode() == 401) {
                Log4Test.info("Wrong Username Test");
                wsPostNoAuthResponse.prettyPrint();
            } else if (wsPostNoAuthResponse.statusCode() != 200) {
                Log4Test.error(baseURI + basePath + wsPath + " status not 200. Response is :");
                wsPostNoAuthResponse.prettyPrint();
                fail();
            }
        } catch (Exception wsPostNoAuthException) {
            Log4Test.error("wsPOSTnoAuth error");
            throw wsPostNoAuthException;
        }

        return wsPostNoAuthResponse;
    }

    /**
     * This method is used for getting the size of list / group inside a response
     */
    public static int responseElementListSize(Response wsResponse, String elementListPath) {


        try {

            return wsResponse.jsonPath().getList(elementListPath).size();

        } catch (Exception responseElementListSizeException) {
            Log4Test.error("responseElementListSize error at: " + elementListPath);
            throw responseElementListSizeException;
        }
    }

    /**
     * This method is used to assert, if it is not null, each element of a list inside of a response
     */
    public static void responseListAssert(Response wsResponse, String elementListPathComplete, String elementListPath1, String elementListPath2, String message) {


        try {
            int responseElementList = wsResponse.jsonPath().getList(elementListPathComplete).size();


            for (int i = 0; i < responseElementList; i++) {

                Assert.assertNotNull(wsResponse.path(elementListPath1 + i + elementListPath2));
            }

            System.out.println(newline);

        } catch (Exception responseListAssertException) {
            Log4Test.error("responseListAssert error at: " + message + ", assertion test for list element ");
            throw responseListAssertException;
        }
    }

    /**
     * This method is used to print values from each element of a list inside of a response
     */
    public static void responseListPrintValues(Response wsResponse, String elementListPathComplete, String elementListPath1, String elementListPath2, String wsName) {


        int i = 0;
        try {
            int responseElementList = wsResponse.jsonPath().getList(elementListPathComplete).size();

            for (i = 0; i < responseElementList; i++) {

                Log4Test.error(wsName + ", value for list element " + i + " is: " + wsResponse.path(elementListPath1 + i + elementListPath2));
            }

            System.out.println(newline);

        } catch (Exception responseListPrintValuesException) {
            Log4Test.error("responseListPrintValues error at: " + wsName + ", value for list element " + i + " is: " + wsResponse.path(elementListPath1 + i + elementListPath2));
            throw responseListPrintValuesException;
        }
    }

    /**
     * This method is used to set the response.path for each element of a list inside of a response
     */
    public static String responseListValueSimplePath(Response wsResponse, String elementListPathComplete, String elementListPath1, String elementListPath2) {


        String responseListValueSimplePath = null;
        int i = 0;
        try {
            int responseElementList = wsResponse.jsonPath().getList(elementListPathComplete).size();

            for (i = 0; i < responseElementList; i++) {

                responseListValueSimplePath = wsResponse.path(elementListPath1 + i + elementListPath2).toString();
                // debug:
                // System.out.println(elementListPath1 + i + elementListPath2);
            }

            //System.out.println(newline);

        } catch (Exception responseListValueSimpleException) {
            Log4Test.error("responseListValueSimple error at: " + elementListPath1 + i + elementListPath2);
            throw responseListValueSimpleException;
        }
        return responseListValueSimplePath;
    }

    /**
     * This method is used to set the string for each element of a list inside of a response
     */
    public static String responseListValueSimpleString(Response wsResponse, String elementListPathComplete, String elementListPath1, String elementListPath2) {


        String responseListValueSimpleString = "This is a default message. This should never be displayed!";
        try {
            int responseElementList = wsResponse.jsonPath().getList(elementListPathComplete).size();

            for (int i = 0; i < responseElementList; i++) {

                responseListValueSimpleString = elementListPath1 + i + elementListPath2;
                //debug:
                //System.out.println(responseListValueSimpleString);
            }

        } catch (Exception responseListValueSimpleException) {
            Log4Test.error("responseListValueSimple error at: " + responseListValueSimpleString);
            throw responseListValueSimpleException;
        }
        return responseListValueSimpleString;
    }

    /**
     * This method is used to assert the existence of an json key
     */
    public static void assertJsonKeyExistence(Response wsResponse, String wsResponsePath) {
        try {
            assertThat(wsResponse.body().asString(), hasJsonPath(wsResponsePath));
        } catch (Exception assertJsonKeyExistenceException) {
            Log4Test.error("Path: " + wsResponsePath + " -> DOES NOT EXIST!" + newline);
            Log4Test.error("assertJsonKeyExistence error at: " + wsResponsePath);
            throw assertJsonKeyExistenceException;
        }
    }

    /**
     * This method is used to assert an element if it is null
     */
    public static boolean assertNotNull(Response wsResponse, String wsResponsePath) {
        try {
            if (wsResponse.path(wsResponsePath) != null) {
                return false;
            }

            Log4Test.error("Path: " + wsResponsePath + " -> is null!" + newline);
            return true;

        } catch (Exception assertNotNullException) {
            Log4Test.error("assertNotNull error at: " + wsResponsePath);
            throw assertNotNullException;
        }
    }

    /**
     * This method is used to compare a response path to a string
     */

    public static boolean assertEqualsPathToString(Response wsResponse, String wsResponsePath, String compareVar) {
        try {
            if (Objects.equals(wsResponse.path(wsResponsePath).toString(), compareVar)) {

                return true;
            }
            Log4Test.error("Path: " + wsResponsePath + " != " + compareVar + newline + "Actual: " + wsResponse.path(wsResponsePath).toString() + " =! " + compareVar);
            return false;

        } catch (Exception assertEqualsPathToStringException) {
            Log4Test.error("assertEqualsPathToString error at: " + wsResponsePath);
            throw assertEqualsPathToStringException;
        }

    }

    /**
     * This method is used to compare a response path to response path
     */
    public static boolean assertEqualsPathToPath(Response wsResponse, String wsResponsePath, String compareVar) {

        try {
            if (Objects.equals(wsResponse.path(wsResponsePath).toString(), wsResponse.path(compareVar).toString())) {
                return true;
            }
            Log4Test.error("Path: " + wsResponsePath + " != " + compareVar + newline + "Actual: " + wsResponse.path(wsResponsePath).toString() + " =! " + compareVar);
            return false;

        } catch (Exception assertEqualsPathToPathException) {
            Log4Test.error("assertEqualsPathToPath error at: " + wsResponsePath);
            throw assertEqualsPathToPathException;
        }
    }


    /**
     * This method is used for downloading files from WS
     */

    public static void downloadFileFromWS(Response response, String downloadPathAndFileName) throws Exception {
        try {
            InputStream inputStream = new ByteArrayInputStream(response.body().asByteArray());
            OutputStream os = new FileOutputStream(downloadPathAndFileName);
            byte[] buffer = new byte[1024];
            int bytesRead;
            //read from inputStream to buffer
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            //flush OutputStream to write any buffered data to file
            os.flush();
            os.close();

        } catch (Exception downloadFileFromWSException) {
            Log4Test.error("downloadFileFromWS error creating file: " + downloadPathAndFileName);
            throw downloadFileFromWSException;
        }
    }

    /**
     * This method is used create a json path string
     */
    public static String jsonPathStringBuilder(Response wsResponse, String jsonPath) {
        try {
            //System.out.println("+++++++++++++++" + JsonPath.from(wsResponse.body().asString()).getString(jsonPath).replace("[", "").replace("]", ""));
            return JsonPath.from(wsResponse.body().asString()).getString(jsonPath).replace("[", "").replace("]", "");

        } catch (Exception jsonPathStringBuilderException) {
            Log4Test.error("jsonPathStringBuilder error for: " + jsonPath);
            throw jsonPathStringBuilderException;
        }
    }

    /**
     * This method is used as a wait counter for document status check
     */
    public static void documentStatusCheckCounter(String wsPath, Map<String, String> cookies, String statusPath, String documentID) throws InterruptedException {

        String status;
        Log4Test.info("Checking document status");
        try {
            for (int i = 0; i < documentRetryTimes; i++) {
                Response response = wsGET(wsPath, cookies);
                status = response.path(statusPath);
                Log4Test.info("Document id status check try: " + (i + 1) + " out of: " + documentRetryTimes);
                if (!status.equals("COMPLETED")) {
                    Log4Test.info("Document id: " + documentID + " status still not completed. Status is: " + response.path(statusPath) + ". Wait some more");
                    TimeUnit.SECONDS.sleep(1);
                    if (i == (documentRetryTimes - 1)) {
                        Log4Test.info("Document id: " + documentID + " status check ended.");
                        if (status.equals("UNAVAILABLE")){
                            response.prettyPrint();
                            fail();
                        }
                    }
                } else {
                    Log4Test.info("Document id: " + documentID + " status check ended.");
                    i = documentRetryTimes;
                }
            }
        } catch (Exception documentStatusCheckCounterException) {
            Log4Test.error("documentStatusCheckCounter error");
            throw documentStatusCheckCounterException;
        }
    }
}

