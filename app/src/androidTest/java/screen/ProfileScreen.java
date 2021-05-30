package screen;

import androidx.test.rule.ActivityTestRule;

import com.example.espresso.ProfileActivity;
import com.example.espresso.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ProfileScreen {
    public ActivityTestRule<ProfileActivity> rule;

    public ProfileScreen() {

    }

    public ProfileScreen tapLogOutButton() {
        onView(withId(R.id.logout_button)).perform(click());

        return this;
    }

    public ProfileScreen checkThatAllElementsAvailable() {

        onView(withId(R.id.pr_name)).check(matches(isDisplayed()));

        onView(withId(R.id.pr_phone_number)).check(matches(isDisplayed()));

        onView(withId(R.id.pr_email)).check(matches(isDisplayed()));

        onView(withId(R.id.btn_save)).check(matches(isClickable()));

        return new ProfileScreen();
    }
}
