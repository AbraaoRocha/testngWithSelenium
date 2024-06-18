package testng.services;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import testng.utils.DriverUtils;
import testng.utils.Properties;

public class TestBase {

	@BeforeClass
	public void setup() {
		DriverUtils.getDriver().get(Properties.BASE_URL);
	}

	@Test
	public void test() {

	}

	@AfterTest
	public void teardown() {
		DriverUtils.fecharDriver();
	}

}
