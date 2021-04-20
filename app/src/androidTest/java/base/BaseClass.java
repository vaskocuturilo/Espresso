package base;

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4ClassRunner.class)
public class BaseClass {
    public static UiDevice device;

    @BeforeClass
    public static void initDevice() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Before
    public void start() {
        StartMainActivityFromHomeScreen.startMainActivityFromHomeScreen();
    }
}
