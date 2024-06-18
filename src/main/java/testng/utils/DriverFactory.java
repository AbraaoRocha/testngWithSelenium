package testng.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

	public class DriverFactory {

	    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
	   	 @Override
	   	 protected synchronized WebDriver initialValue() {
	   		 return initDriver();
	   	 }
	    };

	    private DriverFactory() {

	    }

	    public static WebDriver getDriver() {
	   	 return threadDriver.get();
	    }

	    @SuppressWarnings("unused")
	    public static WebDriver initDriver() {
	   	 WebDriver driver = null;
	   	 FirefoxOptions options = new FirefoxOptions();
	   	 ChromeOptions opt = new ChromeOptions();

	   		 switch (Properties.browser) {
	   			 case FIREFOX:
	   				 driver = new FirefoxDriver();
	   				 break;
	   			 case CHROME:
	   				 driver = new ChromeDriver();
	   				 break;
	   	 }

	   	 return driver;
	    }

	    public static void killDriver() {
	   	 
	   		 WebDriver driver = getDriver();
	   		 if (driver != null) {
	   			 driver.quit();
	   			 driver = null;
	   		 }
	   		 if (threadDriver != null) {
	   			 threadDriver.remove();
	   	 }

	    }
	}
