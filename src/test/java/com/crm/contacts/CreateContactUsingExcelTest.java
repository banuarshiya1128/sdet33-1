package com.crm.contacts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactUsingExcelTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fin1 = new FileInputStream("./src/test/resources/commonData.properties");
		Properties prop = new Properties();
		prop.load(fin1);
		String url=prop.getProperty("url");
		String username=prop.getProperty("userName");
		String password=prop.getProperty("password");
		String browser=prop.getProperty("browser");
		String timeout=prop.getProperty("timeOuts");
		String sheetname=prop.getProperty("sheetname");
		String sheeturl=prop.getProperty("sheeturl");
		
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
		driver1.manage().window().maximize();
		driver1.manage().timeouts().implicitlyWait(timeout2, TimeUnit.SECONDS);
		driver1.get(url);
		driver1.findElement(By.name("user_name")).sendKeys(username);
		driver1.findElement(By.name("user_password")).sendKeys(password);
		driver1.findElement(By.id("submitButton")).click();
		driver1.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
		driver1.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		FileInputStream fin = new FileInputStream(sheeturl);
		Workbook workbook=WorkbookFactory.create(fin);
		Sheet sheetname1 = workbook.getSheet(sheetname);
		Row row = sheetname1.getRow(5);
		Cell cell = row.getCell(1);
		String lastname = cell.getStringCellValue();
		Cell cell1 = row.createCell(2);
		cell1.setCellValue("pass");
		FileOutputStream fos = new FileOutputStream(sheeturl);
		workbook.write(fos);
		workbook.close();
		driver1.findElement(By.name("lastname")).sendKeys(lastname);
		driver1.findElement(By.xpath("(//input[@title='Save [Alt+S]'])")).click();
		driver1.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
		driver1.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver1.findElement(By.linkText("Sign Out")).click();
		driver1.quit();
	}

}
