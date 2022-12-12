package ios_tests;

import GeneralSetup.TestBasisMobile;
import org.testng.annotations.Test;

import java.io.File;

public class ActionsIos extends TestBasisMobile {

    @Test
    public void iosTerminateApp() {
        homeViewIos.getActionsWithDeviceAndAppIos().terminateIosApp();
    }

    @Test
    public void iosInstallApp() {
        homeViewIos.getActionsWithDeviceAndAppIos().installIosApp(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + TestBasisMobile.getDeviceSettings().get("appName"));
    }

    @Test
    public void iosRunAppInBackground() {
        homeViewIos.getActionsWithDeviceAndAppIos().runIosAppInBackground();
        System.out.println(homeViewIos.getActionsWithDeviceAndAppIos().queryIosAppState(TestBasisMobile.getDeviceSettings().get("wdaBundleId")));
    }

    @Test
    public void iosSwitchBetweenApps() {
        homeViewIos.getActionsWithDeviceAndAppIos().activateIosApp(TestBasisMobile.getDeviceSettings().get("wdaBundleId"));
        homeViewIos.getActionsWithDeviceAndAppIos().activateIosApp("com.apple.Preferences");
    }


}