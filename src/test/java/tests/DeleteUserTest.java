package tests;

import dto.User;
import dto.UserFull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteUserTest extends BaseTest{

    @Test
    public void deletedExistingUser(){
        //Pre-steps:
        UserFull requestBody = UserFull.builder()
                .firstName("John")
                .lastName("Black")
                .email(getRandomEmail())
                .gender("male")
                .title("mr")
                .build();
        UserFull userFromCreateResponse = postRequest("/user/create", 200, requestBody)
                .body().jsonPath().getObject("", UserFull.class);
        //Steps:
        User deletedUser = deleteRequest("/user/" + userFromCreateResponse.getId(), 200)
                .body().jsonPath().getObject("", User.class);
        //check that id from response equals to id from request endpoint
        assertEquals(deletedUser.getId(), userFromCreateResponse.getId());
    }


    //delete deleted user
    @Test
    public void deleteDeletedUser(){
    //Pre-steps:
        UserFull requestBody = UserFull.builder()
                .firstName("John")
                .lastName("Black")
                .email(getRandomEmail())
                .gender("male")
                .title("mr")
                .build();
        UserFull userFromCreateResponse = postRequest("/user/create", 200, requestBody)
                .body().jsonPath().getObject("", UserFull.class);
        deleteRequest("/user/" + userFromCreateResponse.getId(), 200);

        String  errorMessage  =
                deleteRequest("/user/" + userFromCreateResponse.getId(), 404).body().jsonPath().get("error");
        assertEquals("RESOURCE_NOT_FOUND", errorMessage);
    }

    //delete invalid user
    @Test
    public void deleteInvalidUser(){
        String  errorMessage  =
                deleteRequest("/user/" + "2345678987654323456780865678", 400).body().jsonPath().get("error");
        assertEquals("PARAMS_NOT_VALID", errorMessage);
    }
}
