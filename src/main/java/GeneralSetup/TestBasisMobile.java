package GeneralSetup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import views.HomeView;
import views.TextFieldsView;
import views.ViewsView;

import java.io.File;
import java.net.URL;

public class TestBasisMobile {
    public static AppiumDriver<MobileElement> appiumDriver;
    public static WebDriverWait wait;

    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private URL serverAddress;
    private AppiumDriverLocalService service;

    public HomeView homeView;
    public ViewsView viewsView;
    public TextFieldsView textFieldsView;

    public static String appPackage = "io.appium.android.apis";
    public static String appPath;

///////////////// для рана через сьюты паралельно на нескольких девайсах ////////////////////////
//    @Parameters({"emulator", "udid", "deviceName", "avd", "port"})
//    @BeforeTest(alwaysRun = true)
//    public void beforeTest(@Optional("androidOnly") String emulator, String udid, String deviceName,
//                           @Optional("androidOnly") String avd, @Optional("androidOnly") String port) throws MalformedURLException, MalformedURLException {
//        if (emulator.equalsIgnoreCase("true")) {
//            capabilities.setCapability("avd", avd);
//            capabilities.setCapability("avdLaunchTimeout", 160000);
//        }
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
//        capabilities.setCapability(MobileCapabilityType.UDID, udid);
//        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
//        appPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
//                + File.separator + "resources" + File.separator + "ApiDemos-debug.apk";
//        capabilities.setCapability(MobileCapabilityType.APP, appPath);
//        capabilities.setCapability("newCommandTimeout", 300);
//        capabilities.setCapability("appPackage", appPackage);
//
//// Если не запускает appiumDriverLocalService автоматически, настраиваем запуск
////    public AppiumDriverLocalService getAppiumDriverLocalService() {
//        //HashMap<String,String> environment = new HashMap<String,String>;
//        //environment.put("PATH", "cmd->echo $PATH");
//        //environment.put("ANDROID_HOME", "Android SDK manager - see path");
////        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
////                .usingDriverExecutable(new File("cmd->where node"))
////                .withAppiumJS(new File("... where appium -> node_modules/appium/build/lib/main.js"))
////                .usingPort(4723)
////                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
////                .withEnvironment(environment);
////    }
//        //appiumDriverLocalService = getAppiumDriverLocalService();
//
//        service = new AppiumServiceBuilder().usingPort(Integer.valueOf(port)).build();
//        serverAddress = new URL("http://127.0.0.1:" + port + "/wd/hub");
//        service.start();
//    }
//
//    @AfterTest(alwaysRun = true)
//    public void afterTest() {
//        appiumDriver.quit();
//        service.stop();
//    }


    ///////////// для локального рана на одном девайсе  //////////////////////
    @BeforeClass(alwaysRun = true)
    public void beforeClassSingleDeviceRun() throws Exception {
        service = AppiumDriverLocalService.buildDefaultService();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4 API 30");
        capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1000);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        // (не обязательно) для того чтобы УСТАНОВИТЬ ПРИЛОЖЕНИЕ (каждый раз при запуске кода) как на андроид так и на айос
        appPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "ApiDemos-debug.apk";
        capabilities.setCapability(MobileCapabilityType.APP, appPath);
        // для автоматического запуска эмулятора
        capabilities.setCapability("avd", "Pixel_4_API_30");
        capabilities.setCapability("avdLaunchTimeout", 180000);  //3 minutes
        // сколько сохранять активность сессии в дебаге
        capabilities.setCapability("newCommandTimeout", 300);  //5 minutes
        capabilities.setCapability("appPackage", appPackage);
        // для разблокировки экрана
        capabilities.setCapability("unlockType", "pin");
        capabilities.setCapability("unlockKey", "0000");

        serverAddress = new URL("http://127.0.0.1:4723/wd/hub");
        service.start();
    }

    @AfterClass(alwaysRun = true)
    public void afterClassSingleDeviceRun() {
        appiumDriver.quit();
        service.stop();
    }

    private void initializeDriver() {
        appiumDriver = new AndroidDriver<>(serverAddress, capabilities);
        wait = new WebDriverWait(appiumDriver, 10);
        initializeClasses();
    }

    private void initializeClasses() {
        homeView = new HomeView(appiumDriver, wait);
        viewsView = new ViewsView(appiumDriver, wait);
        textFieldsView = new TextFieldsView(appiumDriver, wait);
    }

    protected void setAppActivity(AppActivities appActivity) {
        capabilities.setCapability("appActivity", appActivity.getActivityPath());
        initializeDriver();
    }
}
