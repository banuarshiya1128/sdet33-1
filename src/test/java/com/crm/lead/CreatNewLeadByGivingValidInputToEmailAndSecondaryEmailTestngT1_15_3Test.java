package com.crm.lead;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.CreatingNewLeadPage;
import com.crm.objectRepository.HomePage;

import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreatNewLeadByGivingValidInputToEmailAndSecondaryEmailTestngT1_15_3Test extends BaseClass{

	@Test
	public  void creatNewLeadByGivingValidInputToEmailAndSecondaryEmailTestngT1_15_3() throws Throwable 
	{
		
		int random= JavaUtility.generateRandomNumber(1000);
		String last_name = ExcelUtility.fetchData(PropertyUtility.TogetValue("sheetname"), 30, 1);
		String lastname=last_name+random;
		String company_name = ExcelUtility.fetchData(PropertyUtility.TogetValue("sheetname"), 30, 2);
		String emailid = ExcelUtility.fetchData(PropertyUtility.TogetValue("sheetname"), 30, 3);
		CreatingNewLeadPage createnewlead = new CreatingNewLeadPage(driver);
		homepage.leadClick();
		String actuallastname = createnewlead.createNewLead(driver, lastname, company_name, emailid, emailid);
		//if (actuallastname.equals(lastname))
		//{
			Assert.assertEquals(actuallastname, lastname);
		
		//SoftAssert sa = new SoftAssert();
		//sa.assertEquals(actuallastname, lastname);
			Reporter.log("last name added",true);
			//sa.assertAll();
		//}
		System.out.println(Thread.currentThread().getId());
		
		
	}

}
