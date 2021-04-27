package smoke;

import androidx.test.rule.ActivityTestRule;

import com.example.espresso.SignUpActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import screen.SignUpScreen;

public class SimpleSignUpTest {

    private SignUpScreen signUpScreen;

    private static final String NAME = "TEST CLIENT";
    private static final String EMAIL = "test11111@test.ru";
    private static final String PASSWORD = "qwerty123456";
    private static final String STATUS_SUCCESS = "Welcome to my app";

    @Rule
    public ActivityTestRule<SignUpActivity> activityRule = new ActivityTestRule<>(SignUpActivity.class);

    @Before
    public void setUp() {
        signUpScreen = new SignUpScreen(activityRule);
    }

    @Test
    public void simpleSignUpTest() {
        signUpScreen
                .create(NAME, EMAIL, PASSWORD)
                .expectStatusWithMessage(STATUS_SUCCESS);
    }
}
