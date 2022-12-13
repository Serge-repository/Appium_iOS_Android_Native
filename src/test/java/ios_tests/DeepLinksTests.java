package ios_tests;

import GeneralSetup.TestBasisMobile;
import org.testng.annotations.Test;
import utils.deep_links.DeepLink;

public class DeepLinksTests extends TestBasisMobile {

    @Test
    public void validateProductOnProductsPage() {
        DeepLink.OpenAppOnView("swaglabs://swag-overview/0,5");
//        sa.assertEquals(textFieldsViewIOS.getSLBTitle(), strings.get("products_page_slb_title"));
    }
}
