package testng.tests.locateItens;

import testng.pages.MainPage;
import testng.services.TestBase;

public class LocateSupportInHomePageTest extends TestBase{

	@Override
	public void test() {
		MainPage mp = new MainPage();
		mp.findElementInNavBarByName("Support");
	}
	
}
