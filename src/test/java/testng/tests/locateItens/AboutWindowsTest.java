package testng.tests.locateItens;

import testng.pages.WindowsPage;
import testng.services.TestBase;

public class AboutWindowsTest extends TestBase {

	@Override
	public void test() {
		LocateWindowsInHomePageTest wtp = new LocateWindowsInHomePageTest();
		wtp.test();

		WindowsPage ap = new WindowsPage();
		ap.selectAboutWindows();

	}
}
