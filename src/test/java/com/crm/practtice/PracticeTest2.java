package com.crm.practtice;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class PracticeTest2 
{
	@Test(groups={"smoke","regression"})
	public void testRun1()
	{
		Reporter.log("testrun-1", true);
	}


	@Test(groups= {"smoke","regression"})
	public void testRun2()
	{
		String s="abcd";
		//Assert.assertEquals(s, "idf");
		Reporter.log("testrun-2", true);
	}
	
	
	@Test(groups="smoke")
	public void testRun3()
	{
		Reporter.log("testrun-3", true);
	}
	
}
