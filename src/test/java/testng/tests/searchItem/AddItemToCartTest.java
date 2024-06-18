package testng.tests.searchItem;

import testng.pages.CartPage;
import testng.pages.ItemPage;
import testng.services.TestBase;

public class AddItemToCartTest extends TestBase {

	@Override
	public void test() {
		SearchVisualStudioEnterpriseTest svse = new SearchVisualStudioEnterpriseTest();
		svse.test();

		ItemPage ip = new ItemPage();
		ip.validateItemSelected("Visual Studio Enterprise");

		CartPage cp = new CartPage();
		cp.validItemValue();
		cp.changeQuantity(20);
		cp.validItemValue();
	}
}
