package testng.tests.locateItens;

import testng.pages.MainPage;
import testng.services.TestBase;

public class LocateWindowsInHomePageTest extends TestBase{

	@Override
	public void test() {
		MainPage mp = new MainPage();
		mp.findElementInNavBarByName("Windows");
	}
	
}
