package com.crm.genericUtility;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 
{
	public WebDriver driver;
	public HomePage homepage;
	public static WebDriver sdriver;
	@BeforeSuite
	public void dataBaseConnection() throws Throwable
	{
		PropertyUtility.toLoadPropertyFile(ConstantPath.PropertyFilePath);
		DataBaseUtility.getDatabaseConnection(ConstantPath.DatabasePath,PropertyUtility.TogetValue("DatabaseName"),PropertyUtility.TogetValue("DataBasePassword"));
	}
	
	//@Parameters(value="browser")
	
	@BeforeClass
	//String browser
	public void openBrowser()
	{
		//String browser=PropertyUtility.TogetValue("browser");
		String browser=System.getProperty("browser");
		long timeout=JavaUtility.stringTolong(PropertyUtility.TogetValue("timeOuts"));
		if (browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if (browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			Reporter.log("no browser option is provided", true);
		}
		String url=System.getProperty("url");
		/*PropertyUtility.TogetValue("url")*/
		WebDriverUtility.launchApplicationWithMaximize(driver,url,timeout);
		sdriver=driver;
	}
	@BeforeMethod
	public void Login() throws Throwable
	{
		LoginPage loginpage= new LoginPage(driver);
		loginpage.loginAction(PropertyUtility.TogetValue("userName"), PropertyUtility.TogetValue("password"));
		homepage=new HomePage(driver);
		ExcelUtility.openExcel(ConstantPath.excelPath1);
		
	}
	@AfterSuite
	public void closeDataBase() throws SQLException
	{
		DataBaseUtility.closeDataBase();
	}
	@AfterClass
	public void closeBrowser()
	{
		WebDriverUtility.closeBrowser(driver);
	}
	@AfterMethod
	public void Logout() throws Throwable
	{
		homepage.signOut(driver);
		ExcelUtility.closeExcel();
	}
}
