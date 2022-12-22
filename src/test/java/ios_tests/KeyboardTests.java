package ios_tests;

import GeneralSetup.TestBasisMobile;
import org.testng.annotations.Test;

public class KeyboardTests extends TestBasisMobile {

    @Test
    public void enterTextUsingKeyboardButtonsSelectorsTest() {
        textFieldsViewIOS.openTextFieldsViewIos();
        textFieldsViewIOS.enterTextIntoDefaultTextBox();
    }

    @Test
    public void enterTextMethod() {
        textFieldsViewIOS.openTextFieldsViewIos();
        textFieldsViewIOS.enterTextUsingSendKeys();
    }

}