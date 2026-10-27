package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class LoginPage extends BrowserUtility{
	
	private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
	private static final By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
	private static final By SUBMIT_BUTTON_LOCATOR = By.xpath("//*[@id=\"SubmitLogin\"]/span");
	
	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public MyAccountPage doLoginWith( String emailAddress, String password) {
		enterText(EMAIL_TEXT_BOX_LOCATOR, emailAddress );
		enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
		clickOn(SUBMIT_BUTTON_LOCATOR);
		
		MyAccountPage myAccout = new MyAccountPage(getDriver());
		return myAccout;
	}
}
