package utils.deep_links;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

import static GeneralSetup.TestBasisMobile.appiumDriver;
import static GeneralSetup.TestBasisMobile.platformSelector;

public class DeepLink {
    // This is deep links class which allows to start application from the required screen. Mainly is actual for iOS.
    // NOTE! We must ask developers if they have such deep links implemented inside Android/iOS app AND provide us the deep links structure
    public static void OpenAppOnView(String url) {

        switch (platformSelector) {
            case "Android_emulator":
                HashMap<String, String> deepUrl = new HashMap<>();
                deepUrl.put("url", url);
                deepUrl.put("package", "com.swaglabsmobileapp");
                appiumDriver.executeScript("mobile: deepLink", deepUrl);
                break;
            case "iOS_emulator":
                // Safari browser elements
                // url field marked as button
                By urlBtn = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' && name CONTAINS 'URL'");
                // url button becomes field after click on it
                By urlFld = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeTextField' && name CONTAINS 'URL'");
                // open button on the popup after entering url address
                By openBtn = MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeButton' && name CONTAINS 'Open'");
                // open Safari
                appiumDriver.activateApp("com.apple.mobilesafari");
                WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
                wait.until(ExpectedConditions.visibilityOfElementLocated(urlBtn)).click();
                // uE007 means pressing Enter button in another interpretation
                wait.until(ExpectedConditions.visibilityOfElementLocated(urlFld)).sendKeys("" + url + "\uE007");
                wait.until(ExpectedConditions.visibilityOfElementLocated(openBtn)).click();
                break;
        }
    }
}
