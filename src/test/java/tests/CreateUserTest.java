package tests;

import dto.UserFull;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserTest extends BaseTest{

    @Test
    public void successCreateUser(){
        UserFull requestBody = UserFull.builder()
                .firstName("John")
                .lastName("Black")
                .email(getRandomEmail())
                .gender("male")
                .title("mr")
                .build();

        UserFull userFromResponse = postRequest("/user/create", 200, requestBody)
                .body().jsonPath().getObject("", UserFull.class);
   //name, last name, email from the response are the same with these fields in body of request
        assertEquals(userFromResponse.getFirstName(), requestBody.getFirstName());
        assertEquals(userFromResponse.getLastName(), requestBody.getLastName());
        assertEquals(userFromResponse.getEmail().toLowerCase(), requestBody.getEmail().toLowerCase());
   //check that id is not empty
        assertFalse(userFromResponse.getId().isEmpty());
    }

    //
}
