package com.crm.practtice;

import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class PracticeTest 
{
	@Test
	public  void test1()
	{
		Reporter.log("Welcome to Testyantra", true);
	}
	@Test
	public  void test2()
	{
		Reporter.log("Welcome to Testyantra1", true);
	}
	@Test
	public  void test3()
	{
		Reporter.log("Welcome to Testyantra2", true);
	}
	@BeforeSuite
	public  void beforeSuit()
	{
		Reporter.log("before suit", true);
	}
	@AfterSuite
	public  void afterSuit()
	{
		Reporter.log("aftersuit", true);
	}
	
}
