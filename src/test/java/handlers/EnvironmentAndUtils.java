package handlers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.text.MessageFormat.format;

public class EnvironmentAndUtils {
    public static final String BASE_URL = "https://deckofcardsapi.com/api";
    public static final String PATH_TO_LOGS = "logging.txt";
    public static final String ALL_CARDS = "AS,2S,3S,4S,5S,6S,7S,8S,9S,0S,JS,QS,KS,AD,2D,3D,4D,5D,6D,7D,8D,9D,0D,JD,QD,KD,AC,2C,3C,4C,5C,6C,7C,8C,9C,0C,JC,QC,KC,AH,2H,3H,4H,5H,6H,7H,8H,9H,0H,JH,QH,KH";

    public static PrintStream log;

    public static String deckID;
    public static Response response;
    public static String responseJSON;

    static {
        try {
            Path path = Paths.get(PATH_TO_LOGS);
            Files.writeString(path, "", CREATE);
            Files.writeString(path, "", TRUNCATE_EXISTING);

            log = new PrintStream(new FileOutputStream(PATH_TO_LOGS, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getParameterValueFromResponse(String pathToParameter) {
        JsonPath js = new JsonPath(responseJSON);
        return js.getString(pathToParameter);
    }

    public static RequestSpecification getRequestSpecification(String parameterName, String parameterValue) {
        return given().spec(new RequestSpecBuilder().setBaseUri(BASE_URL)
                .addQueryParam(parameterName, parameterValue)
                .setContentType(ContentType.JSON)
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .build());
    }

    public static Response getRequest(String endPoint, String queryKey, String queryValue, String... pathParams) {
        response = getRequestSpecification(queryKey, queryValue)
                .when()
                .get(format(endPoint, pathParams));
        responseJSON = response.then().assertThat().statusCode(200).extract().response().asString();
        return response;
    }

    public static Response putRequest(String endPoint, String queryKey, String queryValue, String... pathParams) {
        response = getRequestSpecification(queryKey, queryValue)
                .when()
                .get(format(endPoint, pathParams));
        responseJSON = response.then().assertThat().statusCode(200).extract().response().asString();
        return response;
    }
    public static int getCardsCountInThePile(String pileName) {
        return Integer.parseInt(getParameterValueFromResponse(format("piles.{0}.remaining", pileName)));
    }

    public static String getCardsInThePile(String pileName) {
        int cardsCount = getCardsCountInThePile(pileName);
        String cardsInPile = "";
        for (int i = 0; i < cardsCount; i++) {
            cardsInPile += getParameterValueFromResponse(format("piles.{0}.cards[{1}].code", pileName, i)) + ",";
        }
        return cardsInPile;
    }

}
