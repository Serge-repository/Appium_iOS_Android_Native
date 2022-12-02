package android_tests;

import GeneralSetup.AppActivities;
import GeneralSetup.TestBasisMobile;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ViewsTest extends TestBasisMobile {
    @BeforeMethod
    public void beforeMethod(){
        ((AndroidDriver) appiumDriver).startActivity(new Activity(appPackage, AppActivities.GALLERY_ONE_VIEW.getActivityPath()));
    }

    @Test
    public void pressActionDemo(){
        viewsView.pressAction();
    }

    @Test
    public void longPressDemo(){
        viewsView.longPressAction();
    }

    @Test
    public void terminateAndInstallAppDemo(){
        viewsView.longPressAction();
        viewsView.getActionsWithDeviceAndApp().terminateApp(Duration.ofMillis(2000));
        viewsView.getActionsWithDeviceAndApp().installApp();
        viewsView.getActionsWithDeviceAndApp().checkIfAppInstalled();
    }

    @Test
    public void switchBetweenApps(){
        viewsView.getActionsWithDeviceAndApp().terminateApp(Duration.ofMillis(2000));
        viewsView.getActionsWithDeviceAndApp().activateApp("com.android.settings");
        viewsView.getActionsWithDeviceAndApp().terminateApp(Duration.ofMillis(2000));
        viewsView.getActionsWithDeviceAndApp().activateApp(appPackage);
    }
}
