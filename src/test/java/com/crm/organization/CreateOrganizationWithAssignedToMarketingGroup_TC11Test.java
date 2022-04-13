package com.crm.organization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.CreatingNewOrganizationPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;


public class CreateOrganizationWithAssignedToMarketingGroup_TC11Test extends BaseClass{
	
	
	@Test
	public  void createOrganizationWithAssignedToMarketingGroup_TC11() throws Throwable 
	{
		int random = JavaUtility.generateRandomNumber(1000);
		CreatingNewOrganizationPage createneworg= new CreatingNewOrganizationPage(driver);
		String data = ExcelUtility.fetchData(PropertyUtility.TogetValue("sheetname"), 11,1);
		String Expected_org = data+random;
		homepage.organizationClick();
		String act_org = createneworg.createOrganizationPage(driver, Expected_org);
		if (Expected_org.equals(act_org))
		{
			Reporter.log("new org is created successfully");
		}
		System.out.println(Thread.currentThread().getId());
	}

}
