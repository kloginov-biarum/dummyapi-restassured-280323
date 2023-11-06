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
    //delete invalid user
}
