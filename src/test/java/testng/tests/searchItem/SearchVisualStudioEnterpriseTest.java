package testng.tests.searchItem;

import testng.pages.MainPage;
import testng.pages.SearchPage;
import testng.services.TestBase;

public class SearchVisualStudioEnterpriseTest extends TestBase {

	@Override
	public void test() {
		String itemName = "Visual Studio Enterprise";

		MainPage mp = new MainPage();
		mp.searchForItem(itemName);

		SearchPage sp = new SearchPage();
		sp.changeToShop();
		sp.selectSearchedItem(itemName);
		
		
	}
}
