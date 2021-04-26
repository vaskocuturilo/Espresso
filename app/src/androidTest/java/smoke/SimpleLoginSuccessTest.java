package smoke;

import org.junit.Before;
import org.junit.Test;

import base.BaseClass;
import screen.LoginScreen;

public class SimpleLoginSuccessTest extends BaseClass {

    private LoginScreen loginScreen;

    private static final String EMAIL = "test@test.ru";
    private static final String PASSWORD = "qwerty123456";
    private static final String STATUS_SUCCESS = "Welcome to my app";

    @Before
    public void setupTest() {
        loginScreen = new LoginScreen(activityRule);
    }

    @Test
    public void simpleLoginSuccessTest() {
        loginScreen.login(EMAIL, PASSWORD)
                .expectStatusWithMessage(STATUS_SUCCESS);
    }
}
