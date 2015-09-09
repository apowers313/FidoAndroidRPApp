package ms.ato.fidoRpApp;

import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class ms.ato.fidoRpApp.rpAppMainTest \
 * ms.ato.fidoRpApp.tests/android.test.InstrumentationTestRunner
 */
public class rpAppMainTest extends ActivityInstrumentationTestCase2<rpAppMain> {

    public rpAppMainTest() {
        super("ms.ato.fidoRpApp", rpAppMain.class);
    }

}
