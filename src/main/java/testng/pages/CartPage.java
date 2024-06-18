package testng.pages;

import org.apache.poi.ss.formula.functions.Value;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testng.utils.DriverUtils;
import testng.utils.ValueUtils;

public class CartPage {

	public CartPage() {
		PageFactory.initElements(DriverUtils.getDriver(), this);
	}

	final String xpathLabelValue = "/html/body/main/div/div/div/div/section[1]/div/div/div/div/div/div/div[2]/div[1]/div[2]/div/div/span/span[2]/span";
	final String xpathComboItemQuantity = "/html/body/main/div/div/div/div/section[1]/div/div/div/div/div/div/div[2]/div[2]/div[1]/select";

	@FindBy(xpath = xpathLabelValue)
	WebElement labelValue;

	@FindBy(xpath = xpathLabelValue)
	WebElement comboQuatity;

	int quantity = 1;

	private String itemValue() {

		String itemValue;
		DriverUtils.esperarPor(By.xpath(xpathLabelValue));
		itemValue = DriverUtils.getText(By.xpath(xpathLabelValue));
		return itemValue;

	}

	public void changeQuantity(int newQuantity) {

		quantity = newQuantity;

		DriverUtils.esperarPor(By.xpath(xpathComboItemQuantity));
		DriverUtils.selecionarComboPeloValor(comboQuatity, newQuantity);

	}

	public void validItemValue() {

		DriverUtils.esperarPor(By.xpath(xpathLabelValue));
		String itemValue = itemValue();

		ValueUtils vu = new ValueUtils();

		vu.calculateValueChanged(itemValue, quantity);

	}

}
