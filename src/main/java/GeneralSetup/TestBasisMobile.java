package GeneralSetup;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import views.android.TextFieldsView;
import views.android.ViewsView;
import views.android.HomeView;
import views.ios.HomeViewIOS;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestBasisMobile {
    public static AppiumDriver<MobileElement> appiumDriver;
    public static WebDriverWait wait;

    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private URL serverAddress;
    private AppiumDriverLocalService service;
    private static Map<String, String> deviceSettings;
    private String deviceName;
    private Map<String, Map<String, String>> deviceMaps;

    public HomeView homeView;
    public ViewsView viewsView;
    public TextFieldsView textFieldsView;

    public HomeViewIOS homeViewIos;

    public static String appPath;
    public static String appPackage = "io.appium.android.apis";

    private final String platformSelector = System.getProperty("platform", "iOS_emulator");

    ///////////// uncomment for local single device run //////////////////////
    @BeforeClass(alwaysRun = true)
    public void beforeClass() throws IOException {

        ///////////// uncomment for parallel device run via xml suite //////////////////////
//    @Parameters({"device"})
//    @BeforeClass(alwaysRun = true)
//    public void beforeClassSingleDeviceRun(String device) throws IOException {
//    deviceName = System.getProperty("device", device);

        initDeviceMaps();
        switch (platformSelector) {
            case "Android_emulator":
                deviceName = System.getProperty("device", "android_Pixel4_local");
                service = AppiumDriverLocalService.buildDefaultService();
                setCapabilities();
                appPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                        + File.separator + "resources" + File.separator + deviceSettings.get("appName");
                capabilities.setCapability(MobileCapabilityType.APP, appPath);
                capabilities.setCapability("noReset", "true");
                capabilities.setCapability("fullReset", "false");
                // для автоматического запуска эмулятора
                capabilities.setCapability("avd", deviceSettings.get("avdName"));
                capabilities.setCapability("avdLaunchTimeout", 200000);
                // сколько сохранять активность сессии в дебаге
                capabilities.setCapability("newCommandTimeout", 300);  //5 minutes
                capabilities.setCapability("appPackage", deviceSettings.get("appPackage"));
                // для разблокировки экрана
                capabilities.setCapability("unlockType", "pin");
                capabilities.setCapability("unlockKey", "0000");
                break;
            case "iOS_emulator":
                deviceName = System.getProperty("device", "iPhone_13_local");
                service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                        .usingDriverExecutable(new File("/usr/local/bin/node"))
                        .withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                        .usingPort(4724)
                        .withArgument(GeneralServerFlag.SESSION_OVERRIDE));
                setCapabilities();
                // (не обязательно) для того чтобы УСТАНОВИТЬ ПРИЛОЖЕНИЕ (каждый раз при запуске кода)
//        appPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
//                + File.separator + "resources" + File.separator + deviceSettings.get("appName");
//        capabilities.setCapability(MobileCapabilityType.APP, appPath);
                capabilities.setCapability("simulatorStartupTimeout", 180000);  //3 minutes
                capabilities.setCapability("bundleId", deviceSettings.get("wdaBundleId"));
                break;
        }
        serverAddress = new URL("http://127.0.0.1:4723/wd/hub");
        service.start();
        initializeDriver();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        appiumDriver.quit();
        service.stop();
    }

    public static Map<String, String> getDeviceSettings() {
        return deviceSettings;
    }

    private void initializeAndroidClasses() {
        homeView = new HomeView(appiumDriver, wait);
        viewsView = new ViewsView(appiumDriver, wait);
        textFieldsView = new TextFieldsView(appiumDriver, wait);
    }

    private void initializeIOSClasses() {
        homeViewIos = new HomeViewIOS(appiumDriver, wait);
    }

    private void initializeDriver() {
        switch (platformSelector) {
            case "Android_emulator":
                appiumDriver = new AndroidDriver<>(serverAddress, capabilities);
                initializeAndroidClasses();
                break;
            case "iOS_emulator":
                appiumDriver = new IOSDriver<>(serverAddress, capabilities);
                initializeIOSClasses();
                break;
        }
        wait = new WebDriverWait(appiumDriver, 10);
    }

    private void initDeviceMaps() throws IOException {
        InputStream stream = TestBasisMobile.class.getResourceAsStream("/Devices.json");
        if (stream == null) {
            throw new RuntimeException("Could not find Devices.json");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        deviceMaps = new ObjectMapper().enable(JsonParser.Feature.ALLOW_COMMENTS).readValue(reader, HashMap.class);
        stream.close();
    }

    private void setCapabilities() {
        deviceSettings = deviceMaps.get(deviceName);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceSettings.get("deviceName"));
        capabilities.setCapability(MobileCapabilityType.UDID, deviceSettings.get("udid"));
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, deviceSettings.get("platformName"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, deviceSettings.get("automationName"));
    }
}
