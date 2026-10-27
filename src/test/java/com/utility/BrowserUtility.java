package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;


public abstract class BrowserUtility {
// parent classes are always mark as abstract keyword
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(); // instance variable - store in Heap
																					// memory
	// webdriver is non premitive data type - so it will be initialize with null
	// value

	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) { // parameterize constructor to initialize instance variable
		super();
		this.driver.set(driver);
	}
//	public BrowserUtility(String browserName){
//		if(browserName.equalsIgnoreCase("chrome")) {
//		 driver = new  ChromeDriver();
//		}
//		else if (browserName.equalsIgnoreCase("edge")) {
//			driver = new EdgeDriver();
//		}
//		else {
//			System.err.println("invalid browser name please select - chrome or edge");
//		}
//	}

	public BrowserUtility(Browser browserName) {
		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
			;
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
			;
		} else {
			System.err.println("invalid browser name please select - chrome or edge");
		}
	}

	// to run suite in Headless mode
	public BrowserUtility(Browser browserName, boolean isHeadless) {
		if (browserName == Browser.CHROME ) {
			if(isHeadless) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=old");
			options.addArguments("--window-size=1920,1080");// even in headless mode it will ensure that resolution of window will be
			driver.set(new ChromeDriver(options));
			}
			else {
				driver.set(new ChromeDriver());
			}
		}
		else if (browserName == Browser.EDGE) {
			if(isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				driver.set(new EdgeDriver(options));
			}else {
				driver.set(new EdgeDriver());
			}		
			
		} else if (browserName == Browser.FIREFOX) {
			if(isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				driver.set(new FirefoxDriver(options));
			}
			else {
				driver.set(new FirefoxDriver());
			}
			
		} else {
			System.err.println("invalid browser name please select - chrome or edge");
		}
	}

	public void goToWebsite(String url) {
		driver.get().get(url);
	}

	public void maximizeWindow() {
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		WebElement element = driver.get().findElement(locator);
		element.click();
	}

	public void enterText(By Locator, String value) {
		WebElement element = driver.get().findElement(Locator);
		element.sendKeys(value);
	}

	public String getVisibleText(By Locator) {
		WebElement element = driver.get().findElement(Locator);
		String text = element.getText();
		return text;
	}

	
	public String takeScreenshot(String name) {
		File src = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);

		// we are running tests in parallel mode so better to take screenshot with
		// timestamp for better understanding
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String Timestamp = format.format(date);

		String path = System.getProperty("user.dir") + "//screenshots//" + name + "_" + Timestamp + ".png";
		File target = new File(path);
		try {
			FileUtils.copyFile(src, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	public void quit() {
	    if (driver.get() != null) {
	        driver.get().quit();
	        driver.remove();
	    }
	}
}

// what is abstract 
//can't create object of parent class always create object of child class.
// Can abstract class have constructor 
// yes, abstract class can have constructor that will be called by constructor of child class.