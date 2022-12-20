package views.ios;

import GeneralSetup.DataManagement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class WebViewIOS extends DataManagement {

    @iOSXCUITFindBy(accessibility = "Web View")
    private MobileElement webViewButton;

    @FindBy(css = "body>h1")
    private MobileElement webViewText;

    public WebViewIOS(AppiumDriver<MobileElement> appiumDriver, WebDriverWait wait) {
        super(appiumDriver, wait);
    }

    public void openWebViewIos() {
        webViewButton.click();
    }

    public void chooseAppContextByNumber(int contextNumberInArray) {
        changeAppContext(appiumDriver.getContextHandles().toArray()[contextNumberInArray].toString());
    }

    public void verifyStatement() {
        assertEquals(webViewText.getText(), "This is HTML content inside a WKWebView.", "This is not the expected text");
    }
}
