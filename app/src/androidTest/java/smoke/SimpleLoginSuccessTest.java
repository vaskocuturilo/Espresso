package smoke;

import androidx.test.rule.ActivityTestRule;

import com.example.espresso.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import screen.LoginScreen;

@RunWith(AllureAndroidJUnit4.class)
public class SimpleLoginSuccessTest {

    private LoginScreen loginScreen;

    private static final String EMAIL = "test@test.ru";
    private static final String PASSWORD = "qwerty123456";
    private static final String STATUS_SUCCESS = "Welcome to my app";

    @Rule
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setupTest() {
        loginScreen = new LoginScreen(activityRule);
    }

    @Test
    public void simpleLoginSuccessTest() {
        loginScreen.login(EMAIL, PASSWORD)
                .expectStatusWithMessage(STATUS_SUCCESS);
    }

    @Test
    public void simpleLoginSuccessModelTest() {
        loginScreen.login()
                .expectStatusWithMessage(STATUS_SUCCESS);
    }
}
