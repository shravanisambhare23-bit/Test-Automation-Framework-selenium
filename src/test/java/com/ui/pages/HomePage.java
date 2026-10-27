package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Browser;

import com.constants.Env;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.propertiesUtil;


public final class HomePage extends BrowserUtility {
	
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[@class='login']");
	// its static - so it is class variable 
	// if variable is final that will be static also
	
public HomePage(Browser browser, boolean isHeadless ) {
		super(browser,isHeadless);
		// super to call parent class constructor to child class	}
		
	// goToWebsite(propertiesUtil.readProperty(Env.QA,"URL")); //= for config.properties
		goToWebsite(JSONUtility.readJson(Env.QA).getUrl());
}
public HomePage(WebDriver driver ) {
	super(driver);// to call the Parent Class constructor from the child constructor
	goToWebsite(JSONUtility.readJson(Env.QA).getUrl());
}

public  LoginPage goTologinpage() { // page function ---> can't return void
	clickOn(SIGN_IN_LINK_LOCATOR);
	LoginPage loginPage = new LoginPage(getDriver());
	
	return loginPage;
}

}
