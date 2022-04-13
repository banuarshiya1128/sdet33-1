package com.crm.Opportunities;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class Createopportunitywithmappingorganization_TC05 {

	public static void main(String[] args) throws Throwable 
	{
		String propertyfilepath = ConstantPath.PropertyFilePath;
		String excelPath = ConstantPath.excelPath1;
		PropertyUtility.toLoadPropertyFile(propertyfilepath);
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
		WebElement username = driver.findElement(By.name("user_name"));
		WebDriverUtility.javaScriptSendKeys(driver, username, userName);
		WebElement Password = driver.findElement(By.name("user_password"));
		WebDriverUtility.javaScriptSendKeys(driver, Password, password);
		WebElement submitbutton = driver.findElement(By.id("submitButton"));
		WebDriverUtility.javaScriptClick(driver, submitbutton);
		WebElement opportunities = driver.findElement(By.linkText("Opportunities"));
		WebDriverUtility.javaScriptClick(driver, opportunities);
		WebElement createopportunity = driver.findElement(By.xpath("//img[@title='Create Opportunity...']"));
		WebDriverUtility.javaScriptClick(driver, createopportunity);
		ExcelUtility.openExcel(excelPath);
		int random = JavaUtility.generateRandomNumber(1000);
		String OpportunityName = ExcelUtility.fetchData(sheetname, 27, 1);
		String O_N = OpportunityName+random;
		WebElement opp_name = driver.findElement(By.name("potentialname"));
		WebDriverUtility.javaScriptSendKeys(driver, opp_name, O_N);
		String RelatedTo = ExcelUtility.fetchData(sheetname, 27, 2);
		System.out.println(RelatedTo);
		//WebElement relateto = driver.findElement(By.xpath("//select[@name='related_to_type']"));
		//WebDriverUtility.select(relateto, RelatedTo);
		WebElement assigntype = driver.findElement(By.xpath("//input[@value='T']"));
		WebDriverUtility.javaScriptClick(driver, assigntype);
		String sales_stage = ExcelUtility.fetchData(sheetname, 27, 3);
		WebElement salesstage = driver.findElement(By.name("sales_stage"));
		WebDriverUtility.select(salesstage, sales_stage);
		String assign_to = ExcelUtility.fetchData(sheetname, 27, 4);
		String partialT = ExcelUtility.fetchData(sheetname, 27, 6);
		WebElement assignto = driver.findElement(By.name("assigned_group_id"));
		WebDriverUtility.select(assignto, assign_to);
		//WebElement add_org = driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]"));
		//WebDriverUtility.javaScriptClick(driver, add_org);
		//WebDriverUtility.switchToWindow(driver, partialT);
		String srch = ExcelUtility.fetchData(sheetname, 27, 5);
		
		//WebElement searchtxt = driver.findElement(By.id("search_txt"));
		//WebDriverUtility.javaScriptSendKeys(driver, searchtxt, srch);
		//WebElement searchbutton = driver.findElement(By.name("search"));
		//WebDriverUtility.javaScriptClick(driver, searchbutton);
		//WebElement sr = driver.findElement(By.linkText("SDET_35"));
		//WebDriverUtility.javaScriptClick(driver, sr);
		driver.findElement(By.id("jscal_trigger_closingdate")).click();
		
	}

}
