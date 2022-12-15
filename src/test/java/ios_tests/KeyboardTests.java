package ios_tests;

import GeneralSetup.TestBasisMobile;
import org.testng.annotations.Test;

public class KeyboardTests extends TestBasisMobile {

    @Test
    public void selectorsStrategiesTest() {
        textFieldsViewIOS.openTextFieldsViewIos();
        textFieldsViewIOS.enterTextIntoDefaultTextBox();
    }

    @Test
    public void useSeleniumKeyboardMethod() {
        textFieldsViewIOS.openTextFieldsViewIos();
        textFieldsViewIOS.enterTextUsingSendKeys();
    }

}