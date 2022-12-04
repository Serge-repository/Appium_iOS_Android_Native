package ios_tests;

import GeneralSetup.TestBasisMobile;
import org.testng.annotations.Test;

public class DemoAppTests extends TestBasisMobile {

    @Test
    public void selectorsStrategiesTest() {
        homeViewIos.differentSelectorsStrategies();
    }

}