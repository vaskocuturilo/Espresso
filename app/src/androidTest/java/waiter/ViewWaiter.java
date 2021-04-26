package waiter;

import android.util.Log;
import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Matcher;

import java.util.concurrent.CountDownLatch;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

public class ViewWaiter {

    private static final String TAG = "MC_Synchronizer";

    public static boolean viewExists(final Matcher<View> viewMatcher, final long millis) throws InterruptedException {
        final Boolean[] found = new Boolean[1];

        final CountDownLatch latch = new CountDownLatch(1);
        ViewAction action = new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewMatcher.toString() + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;


                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {

                        if (viewMatcher.matches(child)) {
                            Log.d(TAG, "perform: found match");
                            found[0] = true;
                            latch.countDown();
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                found[0] = false;
                latch.countDown();
            }
        };
        onView(isRoot()).perform(action);

        latch.await();
        return found[0];
    }
}
