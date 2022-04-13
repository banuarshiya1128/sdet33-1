package com.crm.genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerImplementation implements ITestListener
{

	public void onTestStart(ITestResult result)
	{
		
	}

	public void onTestSuccess(ITestResult result) 
	{
		
		
	}

	public void onTestFailure(ITestResult result) 
	{
		String filename = result.getMethod().getMethodName();
		try {
			WebDriverUtility.takeScreenShotofFailedScript(BaseClass.sdriver, filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result)
	{
		
	}

	public void onStart(ITestContext context)
	{
		
	}

	public void onFinish(ITestContext context)
	{
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		
		
	}
	
}
