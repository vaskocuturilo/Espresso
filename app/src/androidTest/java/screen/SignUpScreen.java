package screen;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.example.espresso.R;
import com.example.espresso.SignUpActivity;

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

public class SignUpScreen {

    ActivityTestRule<SignUpActivity> rule;

    private final static int LAUNCH_TIME = 5000;

    public SignUpScreen(ActivityTestRule<SignUpActivity> rule) {
        this.rule = rule;
    }

    public SignUpScreen create(String name, String email, String password) {

        return
                enterFullName(name)
                        .enterEmail(email)
                        .enterPassword(password)
                        .enterRePassword(password)
                        .tapRegisterButton()
                        .waitForLoginStatus();
    }

    private SignUpScreen enterFullName(String name) {
        onView(withId(R.id.et_name))
                .perform(typeText(name), closeSoftKeyboard());

        return this;
    }

    private SignUpScreen enterEmail(String email) {
        onView(withId(R.id.et_email))
                .perform(typeText(email), closeSoftKeyboard());

        return this;
    }

    private SignUpScreen enterPassword(String password) {
        onView(withId(R.id.et_password))
                .perform(typeText(password), closeSoftKeyboard());

        return this;
    }

    private SignUpScreen enterRePassword(String password) {
        onView(withId(R.id.et_repassword))
                .perform(typeText(password), closeSoftKeyboard());

        return this;
    }

    private SignUpScreen tapRegisterButton() {
        onView(withId(R.id.btn_register))
                .perform(click());

        return this;
    }

    private SignUpScreen waitForLoginStatus() {
        try {
            viewExists(allOf(withId(R.id.logout_button), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)), LAUNCH_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public SignUpScreen expectStatusWithMessage(String message) {
        onView(withId(R.id.textView)).check(matches(withText(message)));

        return this;
    }

    public SignUpScreen expectEmptyErrorMessage(String message) {
        onView(withId(android.R.id.message)).check(matches(withText(message)));

        return this;
    }
}
