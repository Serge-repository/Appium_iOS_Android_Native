package views.ios;

import GeneralSetup.DataManagement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyElement;

public class TextFieldsViewIOS extends DataManagement {

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeTextField[`value == \"Placeholder text\"`][1]")
    private MobileElement defaultTextBox;

    private final MyElement textFieldsTab = new MyElement("Text Fields tab", MobileBy.AccessibilityId("Text Fields"));
    private final MyElement defaultField = new MyElement("Default text field", MobileBy.iOSClassChain("**/XCUIElementTypeTextField[`value == \"Placeholder text\"`][1]"));
    private final MyElement qKeyboardButton = new MyElement("q keyboard button", MobileBy.AccessibilityId("q"));
    private final MyElement doneKeyboardButton = new MyElement("Done keyboard button", MobileBy.AccessibilityId("Done"));

    public TextFieldsViewIOS(AppiumDriver<MobileElement> appiumDriver, WebDriverWait wait) {
        super(appiumDriver, wait);
    }

    public void openTextFieldsViewIos() {
        textFieldsTab.click();
    }

    public void enterTextIntoDefaultTextBox() {
        defaultField.click();
        qKeyboardButton.click();
        doneKeyboardButton.click();
    }

    public void enterTextUsingSendKeys() {
        defaultField.sendKeys("test");
        doneKeyboardButton.click();
    }
}
