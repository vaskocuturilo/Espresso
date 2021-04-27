package sanity;

import androidx.test.rule.ActivityTestRule;

import com.example.espresso.SignUpActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import screen.SignUpScreen;

public class SimpleEmptySignUpTest {

    private SignUpScreen signUpScreen;
    private static final String STATUS_SUCCESS = "Please, insert an Full Name an Email  and a Password and your password again.";

    @Rule
    public ActivityTestRule<SignUpActivity> activityRule = new ActivityTestRule<>(SignUpActivity.class);

    @Before
    public void setUp() {
        signUpScreen = new SignUpScreen(activityRule);
    }

    @Test
    public void simpleSignUpTest() {
        signUpScreen
                .create("", "", "")
                .expectEmptyErrorMessage(STATUS_SUCCESS);
    }
}
