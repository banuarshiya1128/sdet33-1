package com.crm.contacts;

import java.io.FileOutputStream;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
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

public class CreateContactWithOrganization1 {

	public static void main(String[] args) throws Throwable  {
		String propertyfilepath = ConstantPath.PropertyFilePath;
		PropertyUtility.toLoadPropertyFile(propertyfilepath);
		String url = PropertyUtility.TogetValue("url");
		String username = PropertyUtility.TogetValue("userName");
		String password = PropertyUtility.TogetValue("password");
		String browser= PropertyUtility.TogetValue("browser");
		String timeout = PropertyUtility.TogetValue("timeOuts");
		String sheetname = PropertyUtility.TogetValue("sheetname");
		String excelpath = ConstantPath.excelPath;
		long timeout2 = Long.parseLong(timeout);
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
		WebDriverUtility.launchApplicationWithMaximize(driver1, url, timeout2);
		WebDriverUtility.waitforPageLoad(driver1, timeout2);
		WebElement user_name = driver1.findElement(By.name("user_name"));
		WebDriverUtility.javaScriptSendKeys(driver1, user_name, username);
		WebElement user_pass = driver1.findElement(By.name("user_password"));
		WebDriverUtility.javaScriptSendKeys(driver1, user_pass, password);
		WebElement submitbutton = driver1.findElement(By.id("submitButton"));
		WebDriverUtility.javaScriptClick(driver1, submitbutton);
		WebElement organization = driver1.findElement(By.linkText("Organizations"));
		WebDriverUtility.javaScriptClick(driver1, organization);
		WebElement createorg = driver1.findElement(By.xpath("//img[@title='Create Organization...']"));
		WebDriverUtility.javaScriptClick(driver1, createorg);
		int randomnum=JavaUtility.generateRandomNumber(1000);
		ExcelUtility.openExcel(excelpath);
		String organizationname = ExcelUtility.fetchData(sheetname, 8, 1);
		String lastname = ExcelUtility.fetchData(sheetname, 8, 2);
		String Org_Name = organizationname+randomnum;
		WebElement account_name = driver1.findElement(By.name("accountname"));
		WebDriverUtility.javaScriptSendKeys(driver1, account_name, Org_Name);
		WebElement savebutton = driver1.findElement(By.xpath("//input[@title='Save [Alt+S]']"));
		WebDriverUtility.javaScriptClick(driver1, savebutton);
		String Actual_org=driver1.findElement(By.id("dtlview_Organization Name")).getText();
		if(Org_Name.equalsIgnoreCase(Actual_org))
		{
			System.out.println("new organization is created");
		}
		WebElement createdorg = driver1.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']"));
		WebDriverUtility.javaScriptClick(driver1, createdorg);
		WebElement createcontact = driver1.findElement(By.xpath("//img[@alt='Create Contact...']"));
		WebDriverUtility.javaScriptClick(driver1, createcontact);
		String Expected_contact=lastname;
		WebElement last_name = driver1.findElement(By.name("lastname"));
		WebDriverUtility.javaScriptSendKeys(driver1, last_name, Expected_contact);
		createdorg=driver1.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td[1] //img[@src='themes/softed/images/select.gif']"));
		WebDriverUtility.javaScriptClick(driver1, createdorg);
		WebDriverUtility.switchToWindow(driver1, "Accounts");
		WebElement search = driver1.findElement(By.id("search_txt"));
		WebDriverUtility.javaScriptSendKeys(driver1, search, Org_Name);
		WebElement search1 = driver1.findElement(By.name("search"));
		WebDriverUtility.javaScriptClick(driver1, search1);
		WebElement ac_org = driver1.findElement(By.linkText(Actual_org));
		WebDriverUtility.javaScriptClick(driver1, ac_org);
		WebDriverUtility.switchToWindow(driver1, "Contacts");
		WebElement save1 = driver1.findElement(By.xpath("(//input[@title='Save [Alt+S]'])"));
		WebDriverUtility.javaScriptClick(driver1, save1);
		String Act_ls = driver1.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		System.out.println(Act_ls);
		String act_add_name = driver1.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td[@id='mouseArea_Organization Name']/a")).getText();
		if(lastname.equalsIgnoreCase(Act_ls)&& Org_Name.equalsIgnoreCase(act_add_name) ) {
			System.out.println("contact created successfully with organization");
			ExcelUtility.closeExcel();
			WebElement signimg = driver1.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			WebDriverUtility.moveToElement(driver1, signimg);
			WebElement signout = driver1.findElement(By.linkText("Sign Out"));
			WebDriverUtility.javaScriptClick(driver1, signout);
		}


	}

}
