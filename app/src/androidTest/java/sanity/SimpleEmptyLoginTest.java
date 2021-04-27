package sanity;

import androidx.test.rule.ActivityTestRule;

import com.example.espresso.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import screen.LoginScreen;

public class SimpleEmptyLoginTest {

    private LoginScreen loginScreen;
    private static final String ERROR_MESSAGE = "Please, insert an Email and a Password.";

    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setupTest() {
        loginScreen = new LoginScreen(activityRule);
    }

    @Test
    public void simpleEmptyCredentialTest() {
        loginScreen.
                login("",  "")
                .expectEmptyEmailErrorMessage(ERROR_MESSAGE);

    }
}
