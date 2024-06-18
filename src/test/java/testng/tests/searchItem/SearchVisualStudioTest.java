package testng.tests.searchItem;

import testng.pages.MainPage;
import testng.services.TestBase;

public class SearchVisualStudioTest extends TestBase{

	
	@Override
	public void test() {
		MainPage mp = new MainPage();
		mp.searchForItem("Visual Studio");
	}
}
