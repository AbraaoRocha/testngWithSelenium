package testng.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testng.utils.DriverUtils;

public class WindowsPage {

	public WindowsPage() {
		PageFactory.initElements(DriverUtils.getDriver(), this);
	}

	final String xpathBtnAboutWindows = "/html/body/div[2]/div/div/header/div/div/nav/ul/li[2]/div/button";

	@FindBy(xpath = xpathBtnAboutWindows)
	WebElement btnAboutWindowns;
	
	public void selectAboutWindows() {

		DriverUtils.esperarPor(By.xpath(xpathBtnAboutWindows));
		DriverUtils.clicar(btnAboutWindowns);
	}

}
