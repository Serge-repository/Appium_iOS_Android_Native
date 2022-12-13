package views.ios;

import GeneralSetup.DataManagement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyElement;

public class TextFieldsViewIOS extends DataManagement {

//    @iOSXCUITFindBy(accessibility = "Activity Indicators")
//    private MobileElement activityIndicatorsButton;
//    @iOSXCUITFindBy(className = "XCUIElementTypeStaticText")
//    private List<MobileElement> classNameElement;

    private final MyElement textFieldsTab = new MyElement("Text Fields tab", MobileBy.AccessibilityId("Text Fields"));

    public TextFieldsViewIOS(AppiumDriver<MobileElement> appiumDriver, WebDriverWait wait) {
        super(appiumDriver, wait);
    }

    public void openTextFieldsViewIos() {
        textFieldsTab.click();
    }
}
