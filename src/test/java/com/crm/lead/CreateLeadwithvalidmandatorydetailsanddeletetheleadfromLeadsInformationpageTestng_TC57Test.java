package com.crm.lead;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.CreatingNewLeadPage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LeadInformationPage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLeadwithvalidmandatorydetailsanddeletetheleadfromLeadsInformationpageTestng_TC57Test extends BaseClass
{
	@Test
	public  void createLeadwithvalidmandatorydetailsanddeletetheleadfromLeadsInformationpageTestng_TC57() throws Throwable 
	{
		
		CreatingNewLeadPage createnewlead = new CreatingNewLeadPage(driver);
		LeadInformationPage leadinformationpage = new LeadInformationPage(driver);
		String last_name = ExcelUtility.fetchData(PropertyUtility.TogetValue("sheetname"), 30, 1);
		String company_name = ExcelUtility.fetchData(PropertyUtility.TogetValue("sheetname"), 30, 2);
		String emailid = ExcelUtility.fetchData(PropertyUtility.TogetValue("sheetname"), 30, 3);
		int random= JavaUtility.generateRandomNumber(1000);
		String lastname1=last_name+random;
		homepage.leadClick();
		String actuallastname = createnewlead.createNewLead(driver, lastname1, company_name, emailid, emailid);
		if (actuallastname.equals(lastname1))
		{
			System.out.println("last name added");
		}
		String alertmsg=leadinformationpage.deleteLead(driver);
		System.out.println(alertmsg);
		leadinformationpage.checkForDeletedlastname(actuallastname);
	}

}
