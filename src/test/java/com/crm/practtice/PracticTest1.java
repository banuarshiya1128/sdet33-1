package com.crm.practtice;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.genericUtility.ExcelUtility;
public class PracticTest1 
{
	
	@Test(dataProvider="exceldataprovider")
	public void test1(String username,String password)
	{
		
	Reporter.log(username+"======"+password,true);
	}
	@Test(priority=2,enabled=false)
	public void test2()
	{
		Reporter.log("test2 is running", true);;
	}
	@Test(priority=-2,invocationCount = 5)
	public void test3()
	{
		Reporter.log("test3 is running", true);
	}
	@Test(priority=3,dependsOnMethods = "test3")
	public void test4()
	{
		Reporter.log("test4 is running", true);
		//Assert.fail();
	}
	@DataProvider(name="exceldataprovider")
	public Object[][] dataProvider() throws Throwable
	{
		
		ExcelUtility.openExcel("./src/test/resources/ExcelData.xlsx");
		Object [][]arr=ExcelUtility.fetchMulttilpedata("sheet1");
		return arr;
	}
}
