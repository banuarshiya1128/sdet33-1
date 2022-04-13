
package com.crm.organization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.CreatingNewOrganizationPage;
import com.crm.objectRepository.HomePage;

import com.crm.objectRepository.LoginPage;
import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationTest
{
	public static void main(String [] args) throws Throwable
	{
		String propertypath = ConstantPath.PropertyFilePath;
		PropertyUtility.toLoadPropertyFile(propertypath);
		String url = PropertyUtility.TogetValue("url");
		String userName = PropertyUtility.TogetValue("userName");
		String password = PropertyUtility.TogetValue("password");
		String browser = PropertyUtility.TogetValue("browser");
		String timeOuts = PropertyUtility.TogetValue("timeOuts");
		String sheetname = PropertyUtility.TogetValue("sheetname");
		String sheeturl = ConstantPath.excelPath;
		long timeout1=Long.parseLong(timeOuts);
		int random = JavaUtility.generateRandomNumber(1000);
		ExcelUtility.openExcel(sheeturl);
		String data = ExcelUtility.fetchData(sheetname, 11, 1);
		WebDriver driver1=null;
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			  driver1 = new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			  driver1 = new FirefoxDriver();
		}
		else
		{
			System.out.println("browser is specified");
		}
		WebDriverUtility.launchApplicationWithMaximize(driver1, url, timeout1);
		WebDriverUtility.waitforPageLoad(driver1, timeout1);
		LoginPage loginpage = new LoginPage(driver1);
		HomePage homepage = new HomePage(driver1);
		CreatingNewOrganizationPage createOrganaizationPage = new CreatingNewOrganizationPage(driver1);
		loginpage.loginAction(userName, password);
		homepage.organizationClick();
		String Expected_org = data+random;
		String Actual_org =createOrganaizationPage.createOrganizationPage(driver1, Expected_org);
		if(Expected_org.equalsIgnoreCase(Actual_org))
		{
			System.out.println("new organization is created");
			ExcelUtility.writeDataInExistingrow(sheetname, 11, 2, "pass", sheeturl);
			ExcelUtility.closeExcel();
		}
		homepage.signOut(driver1);
		WebDriverUtility.closeBrowser(driver1);
	}
}
