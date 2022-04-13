package com.crm.genericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyserImplementation implements IRetryAnalyzer
{
	int count=0;
	int maxLimit=3;

	public boolean retry(ITestResult result) 
	{
		
		if(count<maxLimit)
		{
			count++;
			return true;
			
		}
		
		return false;
	}

}
