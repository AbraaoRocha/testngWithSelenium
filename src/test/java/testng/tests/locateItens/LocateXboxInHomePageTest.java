package testng.tests.locateItens;

import testng.pages.MainPage;
import testng.services.TestBase;

public class LocateXboxInHomePageTest extends TestBase{

	@Override
	public void test() {
		MainPage mp = new MainPage();
		mp.findElementInNavBarByName("Xbox");
	}
	
}
