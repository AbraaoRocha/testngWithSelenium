
package testng.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testng.utils.DriverUtils;

public class MainPage {

	public MainPage() {
		PageFactory.initElements(DriverUtils.getDriver(), this);
	}

	final String xpathTopElementNavBar = "/html/body/div[3]/div[1]/div/div/header/div/div/nav/ul/li[1]/a";
	final String idBtnSearch = "search";
	final String idInputSearch = "cli_shellHeaderSearchInput";
	final String xpathBtnAutoSugest = "//*[@id=\"universal-header-search-auto-suggest-ul\"]/li/a/div/span";

	@FindBy(xpath = xpathTopElementNavBar)
	List<WebElement> topElementsNavBar;

	@FindBy(id = idBtnSearch)
	WebElement btnSearch;

	@FindBy(id = idInputSearch)
	WebElement inputSearch;

	@FindBy(xpath = xpathBtnAutoSugest)
	List<WebElement> searchAutoSuggest;

	public void findElementInNavBarByName(String elementName) {

		boolean hasElement = false;

		for (int i = 0; topElementsNavBar.get(i).getText().equals(elementName); i++) {
			hasElement = true;
		}

		if (Boolean.FALSE.equals(hasElement)) {
			throw new NoSuchElementException();
		}

	}

	public void searchForItem(String item) {

		DriverUtils.clicar(btnSearch);
		DriverUtils.esperarPor(By.id(idInputSearch));
		DriverUtils.sendKeys(inputSearch, item);
		DriverUtils.esperarPor(By.id(xpathBtnAutoSugest));
		DriverUtils.sendKeys(inputSearch, Keys.ENTER);
	}

}
