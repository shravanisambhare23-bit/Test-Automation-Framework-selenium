package com.ui.Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.constants.Env;
import com.utility.JSONUtility;
import com.utility.propertiesUtil;

public class MyRetryAnalyzer implements IRetryAnalyzer {
	
	private static final int MAX_NUMBER_OF_ATTEMPTS = Integer.parseInt(propertiesUtil.readProperty(Env.QA,"MAX_NUMBER_OF_ATTEMPTS"));
	private static int current_attempt = 1;
	@Override
	public boolean retry(ITestResult result) {
	if(current_attempt <= MAX_NUMBER_OF_ATTEMPTS  ) {
		current_attempt++;
		return true;
	}	
		return false;
	}

}


//private static final int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJson(Env.QA).getMAX_NUMBER_OF_ATTEMPTS();
