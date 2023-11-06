package tests;

import dto.User;
import dto.UserFull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tests.BaseTest.getRequest;

public class GetUserById {

    static String requestUserId = "60d0fe4f5311236168a109e1";

  @BeforeAll
    public static void prepareRequestUserId(){
        List<User> users = getRequest("/user", 200).body().jsonPath()
                .getList("data", User.class);
        requestUserId = users.get(0).getId();
    }
    @Test
    public void getUserByValidId(){
    UserFull userFull = getRequest("/user/" + requestUserId, 200)
            .body().jsonPath().getObject("", UserFull.class);
        System.out.println("Id from userFull: " + userFull.getId());
        //check that id from response equals to id from request
        assertEquals(userFull.getId(), requestUserId);
        //picture starts with "https:"
        assertTrue(userFull.getPicture().startsWith("https:"));
        //check that 1996 in dateOfBirth
        assertTrue(userFull.getDateOfBirth().contains("1996"));
        //check that email ends with "@example.com"
        assertTrue(userFull.getEmail().endsWith("@example.com"));
    }
}
