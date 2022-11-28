package android_tests;

import GeneralSetup.TestBasisMobile;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.testng.annotations.Test;

public class DirectGetViaAppActivitiesTest extends TestBasisMobile {
//    @BeforeMethod
//    public void beforeMethod(){
//        setAppActivity(AppActivities.TEXT_FIELDS_VIEW);
//    }

    @Test
    public void textInput(){
        textFieldsView.enterTextIntoHintField();
    }

    @Test
    public void lockDeviceDemo(){
        textFieldsView.getActionsWithDeviceAndApp().lockDevice(5);
        textFieldsView.enterTextIntoHintField();
        textFieldsView.getActionsWithDeviceAndApp().lockDeviceForever();
        textFieldsView.getActionsWithDeviceAndApp().unlockDevice();
    }

    @Test
    public void typeTextUsingAndroidKeyboard(){
        textFieldsView.enterKeyUsingKeyboard(AndroidKey.A, AndroidKey.B, AndroidKey.C);
        textFieldsView.hideKeyboard();
    }
}
