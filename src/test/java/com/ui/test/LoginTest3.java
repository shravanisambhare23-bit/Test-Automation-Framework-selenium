package com.ui.test;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.Browser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;


// hoe to attache listener to test 
@Listeners(com.ui.Listeners.TestListener.class)
public class LoginTest3 extends TestBase {
	
//	@BeforeMethod (description="load the homepage of the website")
//	public void setup() {
//		 homePage = new HomePage(Browser.CHROME);
//	}
//@Test(description="verifies if the if the valid user is able to login to application",groups= {"e2e","sanity"},
//dataProviderClass = com.ui.dataProvider.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
//	public void loginTest(User user) {
//
//		String text = homePage.goTologinpage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();
//		Assert.assertEquals(text, "Renu Sambhare");
//		System.out.println(text);
//	}
//@Test(description="verifies if the if the valid user is able to login to application",groups= {"e2e","sanity"},
//dataProviderClass = com.ui.dataProvider.LoginDataProvider.class, dataProvider = "LoginTestCSVDataProvider")
//	public void CSVLoginTest(User user) {
//
//		String text = homePage.goTologinpage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();
//		Assert.assertEquals(text, "Renu Sambhare");
//		System.out.println(text);
//	}
//reading excel Sheet 
@Test(description="verifies if the if the valid user is able to login to application",groups= {"e2e","sanity"},
dataProviderClass = com.ui.dataProvider.LoginDataProvider.class, dataProvider = "LoginTestExcelDataProvider",retryAnalyzer = com.ui.Listeners.MyRetryAnalyzer.class)
	public void ExcelLoginTest(User user) {


		String text = homePage.goTologinpage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName();
		Assert.assertEquals(text, "Renu Sambhare");
		System.out.println(text);
	
	}
//LoginTestExcelDataProvider
}
