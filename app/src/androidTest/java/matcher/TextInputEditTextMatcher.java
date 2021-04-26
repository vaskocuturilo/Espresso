package matcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import android.view.View;

import androidx.test.espresso.matcher.BoundedMatcher;

import com.google.android.material.textfield.TextInputEditText;

public class TextInputEditTextMatcher {

    public static Matcher<View> hasErrorText(final Matcher<String> stringMatcher) {
        return new BoundedMatcher<View, TextInputEditText>(TextInputEditText.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with error text: ");
                stringMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(TextInputEditText item) {
                return stringMatcher.matches(item.getError());
            }
        };
    }
}