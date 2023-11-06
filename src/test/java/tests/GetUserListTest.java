package tests;

import dto.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserListTest extends BaseTest{
    @Test
    public void getUserList(){
   /*given().baseUri("https://dummyapi.io/data/v1/")
           .header("app-id", "6380c63b2e6f5682c64dd368")
                .when().log().all()
                .get("/user")
                .then().log().all().statusCode(200);*/


   List<User> users = getRequest("/user", 200).body().jsonPath()
           .getList("data", User.class);
        System.out.println("First user id" + users.get(0).getId());
        //check that users collection has 20 items
            assertEquals(20, users.size());
        //check that all fields of user are NOT empty


    }
}
