package tests;

import org.junit.jupiter.api.Test;

import static tests.BaseTest.getRequest;

public class GetUserById {

    @Test
    public void getUserByValidId(){

        getRequest("/user/60d0fe4f5311236168a109e1", 200);
        //picture starts with "https:"
    }
}
