package ios_tests;

import GeneralSetup.TestBasisMobile;
import org.testng.annotations.Test;

public class DemoAppTests extends TestBasisMobile {

    @Test
    public void selectorsStrategiesTest() {
        homeViewIos.differentSelectorsStrategies();
    }

    @Test
    public void verifyingElementAttributes() {
        homeViewIos.verifyElementAttributes();
    }

    @Test
    public void tapOnElement() {
        homeViewIos.tapOnElement();
    }

    @Test
    public void tapOnElementByCoordinates() {
        homeViewIos.tapOnElementByCoordinates();
    }

    @Test
    public void tapOnElementByExactCoordinates() {
        homeViewIos.tapOnElementByExactCoordinates(20, 272);
    }

    @Test
    public void scrollsTestUsingElements() {
        homeViewIos.scrollFromSlidersToActivityIndicators();
    }

    @Test
    public void scrollUsingCoordinates() {
        homeViewIos.getTouchAction().scrollAction(0, 91, 0, 839);
    }

    @Test
    public void hybridAndContext() {
        webViewIOS.openWebViewIos();
        webViewIOS.chooseAppContextByNumber(1);
        webViewIOS.verifyStatement();
        webViewIOS.changeAppContext("NATIVE_APP");
    }

}