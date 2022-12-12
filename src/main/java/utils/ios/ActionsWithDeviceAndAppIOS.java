package utils.ios;

import GeneralSetup.TestBasisMobile;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.appmanagement.AndroidInstallApplicationOptions;
import io.appium.java_client.appmanagement.ApplicationState;

import java.time.Duration;

import static GeneralSetup.TestBasisMobile.appiumDriver;
import static org.testng.AssertJUnit.assertTrue;

public class ActionsWithDeviceAndAppIOS {
    public void terminateIosApp(){
        appiumDriver.terminateApp(TestBasisMobile.getDeviceSettings().get("wdaBundleId"));
        // closes the app
    }

    public void installIosApp(String applicationPath){
        appiumDriver.installApp(applicationPath);
        // allows to update apps with new versions
    }

    public void runIosAppInBackground() {
        appiumDriver.runAppInBackground(Duration.ofMillis(5000));
        // put app in background for 5 seconds and then open it again
    }

    public void activateIosApp(String bundleId) {
        appiumDriver.activateApp(bundleId); // switch between apps
    }

    public ApplicationState queryIosAppState(String bundleId) {
        return appiumDriver.queryAppState(bundleId); // shows state of the app
    }

    public void checkIfAppInstalled() {
        assertTrue(appiumDriver.isAppInstalled(TestBasisMobile.appPackage));
    }

    public void lockDevice(Integer seconds) {
        // lock device for some time, then unlock
        ((AndroidDriver) appiumDriver).lockDevice(Duration.ofSeconds(seconds)); // only available in AndroidDriver class
    }

    public void lockDeviceForever() {
        // lock device forever
        ((AndroidDriver) appiumDriver).lockDevice(); // only available in AndroidDriver class
        System.out.println(((AndroidDriver) appiumDriver).isDeviceLocked());
    }

    public void unlockDevice() {
        ((AndroidDriver) appiumDriver).unlockDevice(); // only available in AndroidDriver class
    }
}
