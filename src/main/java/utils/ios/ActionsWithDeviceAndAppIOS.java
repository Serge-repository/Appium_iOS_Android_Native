package utils.ios;

import GeneralSetup.TestBasisMobile;
import io.appium.java_client.appmanagement.ApplicationState;

import java.time.Duration;

import static GeneralSetup.TestBasisMobile.appiumDriver;

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
}
