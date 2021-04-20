package smoke;

import org.junit.Test;

import base.BaseClass;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class SimpleTest extends BaseClass {

    @Test
    public void simpleTest() {
        onView(withText("Login/Register Test Application")).check(matches(isDisplayed()));
    }
}
