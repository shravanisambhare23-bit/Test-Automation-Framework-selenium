package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {
	private static ExtentReports extentReports; // class variable, 
// want only 1 reeport so created static 	
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	// for parallel execution will use thread local variable and create object of theread.local
	
	public static void setupSparkReporter(String reportNama) {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//"+reportNama);
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
	}
	public static void createExtentTest(String testName) {
		ExtentTest test = extentReports.createTest(testName);
		extentTest.set(test);
	}
	
	public static ExtentTest getTest() {
		return extentTest.get();
		
	}
	
	public static void flushReport() {
		extentReports.flush();
	}
}
