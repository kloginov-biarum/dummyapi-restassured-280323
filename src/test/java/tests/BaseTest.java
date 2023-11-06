package tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

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
}
