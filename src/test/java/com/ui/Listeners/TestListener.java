package com.ui.Listeners;

import java.util.Arrays;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.test.TestBase;
import com.utility.BrowserUtility;
import com.utility.ExtentReporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {
	Logger logger = LoggerUtility.getLogger(this.getClass());

	ExtentSparkReporter extentSparkReporter;// provide functionality to create html report
	ExtentReports extentReports;// Do heavy lifting - data to be dump in html is done by this class
	ExtentTest extentTest;// store information about test

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName()); // method name
		logger.info(result.getMethod().getDescription()); // description about test
		logger.info(Arrays.toString(result.getMethod().getGroups())); // array of various groups it belongs to
		ExtentReporterUtility.createExtentTest(result.getMethod().getMethodName());
	}

	public void onStart(ITestContext context) {
		logger.info("Test suit Started");
ExtentReporterUtility.setupSparkReporter("report.html");
	}
                      
	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
		ExtentReporterUtility.getTest().log(Status.SKIP, result.getMethod().getMethodName() + " " + "SKIPPED");
	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " " + "PASSED");
		
		ExtentReporterUtility.getTest().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + "FAILED");
		logger.error(result.getThrowable().getMessage());
		ExtentReporterUtility.getTest().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
		ExtentReporterUtility.getTest().log(Status.FAIL,result.getThrowable().getMessage());
		
/*
 here we want to access browserUtility class for screenshot method
 */
		Object testClass = result.getInstance();
		BrowserUtility browserUtility = ((TestBase)testClass).getInstance();
		logger.info("Capturing screenshot for the failed tests");
		String screenshotPath = browserUtility.takeScreenshot(result.getMethod().getMethodName());
		logger.info("attaching screenshot to html report");
		ExtentReporterUtility.getTest().addScreenCaptureFromPath(screenshotPath);
	}

	public void onFinish(ITestContext context) {
		logger.info("Test suit Completed");
		ExtentReporterUtility.flushReport();
	}

}
