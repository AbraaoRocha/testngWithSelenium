package testng.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testng.utils.DriverUtils;

public class SearchPage {

	public SearchPage() {
		PageFactory.initElements(DriverUtils.getDriver(), this);
	}

	final String xpathBtnShop = "//*[@id=\"rootContainer_Search\"]/div/div[2]/div[1]/nav/ul/li[1]/a";
	final String xpathBtnElementsSearched = "//*[@id=\"product-cards-carousel-71klxko07qs\"]/div/div[2]/section[1]/div/div/div";
	final String xpathLabelItemName = "/div/div[2]/h3";

	@FindBy(xpath = xpathBtnShop)
	WebElement btnShop;

	@FindBy(xpath = xpathBtnElementsSearched)
	List<WebElement> btnElementsSearched;

	public void changeToShop() {

		DriverUtils.esperarPor(By.xpath(xpathBtnShop));
		DriverUtils.clicar(btnShop);

	}

	public void selectSearchedItem(String itemName) {

		changeToShop();

		DriverUtils.esperarPor(By.xpath(xpathBtnElementsSearched));

		int i = 0;
		for (i = 0; btnElementsSearched.get(i).findElement(By.xpath(xpathLabelItemName)).equals(itemName); i++)
			;
		{
			DriverUtils.clicar(btnElementsSearched.get(i));
		}

	}

}
