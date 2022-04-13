package com.crm.organization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.Select;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationUsingRandom {

	public static void main(String[] args) throws Throwable 
	{
		String propertyfilePath = ConstantPath.PropertyFilePath;
		PropertyUtility.toLoadPropertyFile(propertyfilePath);
		String url = PropertyUtility.TogetValue("url");
		String userName = PropertyUtility.TogetValue("userName");
		String password = PropertyUtility.TogetValue("password");
		String timeOuts = PropertyUtility.TogetValue("timeOuts");
		String browser = PropertyUtility.TogetValue("browser");
		String sheetname = PropertyUtility.TogetValue("sheetname");
		long timeout1=Long.parseLong(timeOuts);
		int random = JavaUtility.generateRandomNumber(1000);
		String excelPath1 = ConstantPath.excelPath;
		ExcelUtility.openExcel(excelPath1);
		String organization_name = ExcelUtility.fetchData(sheetname, 14, 1);
		String industry = ExcelUtility.fetchData(sheetname, 14, 2);
		String Type = ExcelUtility.fetchData(sheetname, 14, 3);	
		WebDriver driver=null;
		if(browser.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();	
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();	
		}
		else
		{
			System.out.println("browser name is not specified");
		}
		WebDriverUtility.launchApplicationWithMaximize(driver, url, timeout1);

		WebElement user_name = driver.findElement(By.name("user_name"));
		WebDriverUtility.javaScriptSendKeys(driver, user_name, userName);

		WebElement user_password = driver.findElement(By.name("user_password"));
		WebDriverUtility.javaScriptSendKeys(driver, user_password, password);

		WebElement submitButton = driver.findElement(By.id("submitButton"));
		WebDriverUtility.javaScriptClick(driver, submitButton);

		WebElement Organizations = driver.findElement(By.linkText("Organizations"));
		WebDriverUtility.javaScriptClick(driver, Organizations);

		WebElement createorg = driver.findElement(By.xpath("//img[@title='Create Organization...']"));
		WebDriverUtility.javaScriptClick(driver, createorg);

		String Expected_org = organization_name+random;
		WebElement accountname = driver.findElement(By.name("accountname"));
		WebDriverUtility.javaScriptSendKeys(driver, accountname, Expected_org);
		WebElement industry1 = driver.findElement(By.xpath("//select[@name='industry']"));
		WebDriverUtility.select(industry1, industry);
		WebElement type = driver.findElement(By.xpath("//select[@name='accounttype']"));
		WebDriverUtility.select(type, Type);
		WebElement savebutton = driver.findElement(By.xpath("//input[@title='Save [Alt+S]']"));
		WebDriverUtility.javaScriptClick(driver, savebutton);
		WebElement Acutal_org = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']"));
		String A_O=Acutal_org.getText();
		if(Expected_org.equalsIgnoreCase(A_O))
		{
			System.out.println("organization name is created");	
		}
		WebElement Expected1 = driver.findElement(By.id("dtlview_Industry"));
		String Expected_ind=Expected1.getText();
		if(industry.equalsIgnoreCase(Expected_ind))
		{
			System.out.println("industry is created");	
		}
		WebElement Expect_Type = driver.findElement(By.id("dtlview_Type"));
		String ET = Expect_Type.getText();
		if(Type.equalsIgnoreCase(ET))
		{
			System.out.println("organization type is created");	
			ExcelUtility.writeDataInExistingrow(sheetname, 14, 4, "fail", excelPath1);
		}
		ExcelUtility.closeExcel();
		WebElement signimg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		WebDriverUtility.moveToElement(driver, signimg);
		WebElement signout = driver.findElement(By.linkText("Sign Out"));
		WebDriverUtility.javaScriptClick(driver, signout);
		WebDriverUtility.closeBrowser(driver);

	}

}
