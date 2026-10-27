package com.ui.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.constants.Env;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;

public class TestBase {
	protected HomePage homePage;

	private boolean isLambdaTest;

	@Parameters({ "browser", "isLambdaTest","isHeadless" })
	@BeforeMethod(description = "load the homepage of the website")
	public void setup(
			@Optional("chrome") String browser, 
			@Optional("false") boolean isLambdaTest, 
			@Optional("true")boolean isHeadless, ITestResult result) {
		WebDriver lambdaDriver = null; // remote driver
		
		this.isLambdaTest = isLambdaTest;
		
		if (isLambdaTest) {
			lambdaDriver = LambdaTestUtility.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver); // object of homepage
		} else {
			// running test in local macjine
		}
		homePage = new HomePage(Browser.CHROME, isHeadless);
		
	}
	public BrowserUtility getInstance() {
		return homePage;

		// as it is returning HomePage so return type should be HomePage but
		// if we can create object of parent class similarly we can create return type
		// of parent class
	}

	@AfterMethod(description = "Tear Down the Browser")
	public void tearDown() {
		if (isLambdaTest) {
			LambdaTestUtility.quitSession();// close or quit the session on lambda test
		} else {
			homePage.quit();
		}
	}
}
