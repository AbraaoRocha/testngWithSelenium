package testng.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.common.base.Verify;

import testng.utils.DriverUtils;

public class ItemPage {

	public ItemPage() {
		PageFactory.initElements(DriverUtils.getDriver(), this);
	}

	final String xpathLabelItemName = "/html/body/div[3]/div/div[2]/main/div/div[3]/div/div/div/div/div/div/section/div/div[2]/div/h1";
	final String idBtnAddToCart = "buybox-cta-proper";
	
	
	@FindBy(xpath = xpathLabelItemName)
	WebElement labelItemName;
	
	@FindBy(id = idBtnAddToCart)
	WebElement btnAddToCart;
	
	
	public void validateItemSelected(String itemName) {
		
		DriverUtils.esperarPor(By.id(idBtnAddToCart));
		String itemNameLabel = DriverUtils.getText(By.id(idBtnAddToCart));
		
		Verify.verify(itemNameLabel.contains(itemName));
		
	}
	
	public void addItemToCart() {
		
		DriverUtils.clicar(btnAddToCart);
		
	}
	
}
