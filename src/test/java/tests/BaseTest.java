package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BaseTest {

    final static String BASE_URI = "https://dummyapi.io/data/v1/";

    final static String APP_ID_VALUE = "6380c63b2e6f5682c64dd368";

    static RequestSpecification specification = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .setContentType(ContentType.JSON)
            .addHeader("app-id", APP_ID_VALUE)
            .build();

    public static Response getRequest(String endpoint, Integer responseCode){
        Response response = given()
                .spec(specification)
                .when()
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .statusCode(responseCode)
                .extract().response();
        return response;
    }

    public static Response postRequest(String endpoint, Integer responseCode, Object body){
        Response response = given()
                .spec(specification)
                .body(body)
                .when()
                .log().all()
                .post(endpoint)
                .then()
                .log().all()
                .statusCode(responseCode)
                .extract().response();
        return response;
    }

    public static Response deleteRequest(String endpoint, Integer responseCode){
        Response response = given()
                .spec(specification)
                .when()
                .log().all()
                .delete(endpoint)
                .then()
                .log().all()
                .statusCode(responseCode)
                .extract().response();
        return response;
    }

    public static String getRandomEmail() {
        String SALTCHARS =
                "abcdefghijklmopqrstuwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr + "@gmail.com";
    }
}
