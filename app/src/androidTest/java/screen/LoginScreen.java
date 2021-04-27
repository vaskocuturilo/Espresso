package screen;

import android.util.Log;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.espresso.LoginActivity;
import com.example.espresso.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static waiter.ViewWaiter.viewExists;

public class LoginScreen {

    public ActivityTestRule<LoginActivity> rule;

    private final static int LAUNCH_TIME = 5000;

    public LoginScreen(ActivityTestRule<LoginActivity> rule) {
        this.rule = rule;
    }

    public LoginScreen login(String email, String password) {
        return enterEmail(email)
                .enterPassword(password)
                .tapLoginButton()
                .waitForLoginStatus();
    }

    private LoginScreen enterEmail(String email) {
        onView(withId(R.id.et_email))
                .perform(typeText(email), closeSoftKeyboard());

        return this;
    }

    private LoginScreen enterPassword(String password) {
        onView(withId(R.id.et_password))
                .perform(typeText(password), closeSoftKeyboard());

        return this;
    }

    private LoginScreen tapLoginButton() {
        onView(withId(R.id.btn_login))
                .perform(click());

        return this;
    }

    public LoginScreen waitForLoginStatus() {
        try {
            viewExists(allOf(withId(R.id.logout_button), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)), LAUNCH_TIME);
        } catch (InterruptedException ex) {
            Log.d("MDC-waitForLoginStatus", " The method waitForLoginStatus is down." + ex);
            throw new RuntimeException("The method waitForLoginStatus is down." + ex);
        }

        return this;
    }

    public LoginScreen expectStatusWithMessage(String message) {
        onView(withId(R.id.textView)).check(matches(withText(message)));

        return this;
    }

    public LoginScreen expectEmptyEmailErrorMessage(String message) {
        onView(withId(android.R.id.message)).check(matches(withText(message)));

        return this;
    }
}
