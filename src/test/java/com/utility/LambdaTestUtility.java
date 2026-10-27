package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LambdaTestUtility {

	 public static final String HUB_URL = "https://hub.lambdatest.com/wd/hub";// constant 
	 private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<WebDriver>();
	 private static ThreadLocal<DesiredCapabilities> capabilitiesLocal = new ThreadLocal<DesiredCapabilities>(); 
	 // desiredCapability is a class
	// we want to do parallel testing need to make these variables as ThreadLocal 
	 
	 public static WebDriver initializeLambdaTestSession(String browser, String testName) {
		 DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("browserName",browser );// will take from test
	        capabilities.setCapability("browserVersion", "latest");
	        Map<String, Object> ltOptions = new HashMap<>();
	        ltOptions.put("user", "shravanisambhare23");
	        ltOptions.put("accessKey","LT_wSpcZqlxz5EHVXw4n96qN3Ux8YHC6RUvASIxjprdMkyNsuI");
	        ltOptions.put("build", "Selenium 4");
	        ltOptions.put("name",testName );// will take test name from parameter
	        ltOptions.put("platformName", "Windows 10");
	        ltOptions.put("seCdp", true);
	        ltOptions.put("selenium_version", "latest");
	        capabilities.setCapability("LT:Options", ltOptions);
	        capabilitiesLocal.set(capabilities);
	        WebDriver driver = null;
			try {
				driver = new RemoteWebDriver(new URL(HUB_URL), capabilitiesLocal.get());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        driverLocal.set(driver); 
	        return driverLocal.get();
	 }
	 
	 // method to quit session 
	 public static void quitSession() {
		 if(driverLocal.get() != null) {
			 driverLocal.get().quit();
		 }
	 }
}
