package com.crm.lead;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

public class CreateLeadwithvalidmandatorydetailsanddeletetheleadfromLeadsInformationpage_TC56 {

	public static void main(String[] args) throws Throwable 
	{
		String Propertyfilepath = ConstantPath.PropertyFilePath;	
		String excelpath = ConstantPath.excelPath1;
		PropertyUtility.toLoadPropertyFile(Propertyfilepath);
		String url = PropertyUtility.TogetValue("url");
		String userName = PropertyUtility.TogetValue("userName");
		String password = PropertyUtility.TogetValue("password");
		String browser = PropertyUtility.TogetValue("browser");
		String timeOuts = PropertyUtility.TogetValue("timeOuts");
		String sheetname = PropertyUtility.TogetValue("sheetname");
		long timeout = Long.parseLong(timeOuts);
		WebDriver driver=null;
		if(browser.contentEquals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.contentEquals("firefox"))
		{

			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("browser is not specified");
		}
		WebDriverUtility.launchApplicationWithMaximize(driver, url, timeout);
		ExcelUtility.openExcel(excelpath);
		String last_name = ExcelUtility.fetchData(sheetname, 30, 1);
		int random= JavaUtility.generateRandomNumber(1000);
		String lastname1=last_name+random;
		String company_name = ExcelUtility.fetchData(sheetname, 30, 2);
		String emailid = ExcelUtility.fetchData(sheetname, 30, 3);
		LoginPage loginpage = new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		CreatingNewLeadPage createnewlead = new CreatingNewLeadPage(driver);
		
		LeadInformationPage leadinformationpage = new LeadInformationPage(driver);
		loginpage.loginAction(userName, password);
		homepage.leadClick();
		String actuallastname = createnewlead.createNewLead(driver, lastname1, company_name, emailid, emailid);
		if (actuallastname.equals(last_name))
		{
			System.out.println("last name added");
		}
		String alertmsg=leadinformationpage.deleteLead(driver);
		System.out.println(alertmsg);
		homepage.leadClick();
		leadinformationpage.checkForDeletedlastname(actuallastname);
		ExcelUtility.closeExcel();
		homepage.signOut(driver);
		WebDriverUtility.closeBrowser(driver);
		
	}

}
