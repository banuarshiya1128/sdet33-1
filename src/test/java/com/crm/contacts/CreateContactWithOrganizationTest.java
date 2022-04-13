package com.crm.contacts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

public class CreateContactWithOrganizationTest {

	public static void main(String[] args) throws Throwable {
		FileInputStream fin = new FileInputStream("./src/test/resources/commonData.properties");
		Properties prop = new Properties();
		prop.load(fin);
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
		driver1.findElement(By.linkText("Organizations")).click();
		driver1.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		Random rn = new Random();
		int random=rn.nextInt(1000);
		FileInputStream fin1 = new FileInputStream(sheeturl);
		Workbook workbook=WorkbookFactory.create(fin1);
		Sheet sheetname1 = workbook.getSheet(sheetname);
		Row row = sheetname1.getRow(8);
		Cell cell = row.getCell(1);
		String organization_name = cell.getStringCellValue();
		Cell cell1 = row.getCell(2);
		String last_name = cell1.getStringCellValue();
		String org = organization_name+random;
		driver1.findElement(By.name("accountname")).sendKeys(org);
		driver1.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String Actual_org=driver1.findElement(By.id("dtlview_Organization Name")).getText();
		if(organization_name.equalsIgnoreCase(Actual_org))
		{
			System.out.println("new organization is created");
		}
		driver1.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();
		driver1.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		String Expected_contact=last_name;
		driver1.findElement(By.name("lastname")).sendKeys(Expected_contact);
		driver1.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td[1] //img[@src='themes/softed/images/select.gif']")).click();
		Set<String> wid = driver1.getWindowHandles();
		for(String id:wid)
		{
			driver1.switchTo().window(id);
			if(driver1.getTitle().contains("Accounts"))
			{
				break;
				
			}
		}
		driver1.findElement(By.id("search_txt")).sendKeys(org);
		driver1.findElement(By.name("search")).click();
		driver1.findElement(By.linkText(Actual_org)).click();
		Set<String> wid1 = driver1.getWindowHandles();
		for(String id1:wid1)
		{
			driver1.switchTo().window(id1);
			if(driver1.getTitle().contains("Contacts"))
			{
				break;
				
			}
			
		}
		driver1.findElement(By.xpath("(//input[@title='Save [Alt+S]'])")).click();
		String Act_ls = driver1.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		String act_add_name = driver1.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td[@id='mouseArea_Organization Name']/a")).getText();
		if(last_name.equalsIgnoreCase(Act_ls)&& org.equalsIgnoreCase(act_add_name) ) {
			System.out.println("contact created successfully with organization");
			Cell cell2=row.createCell(3);
			cell2.setCellValue("pass");
			FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData.xlsx");
			workbook.write(fos);
			workbook.close();
		}
		
		
		driver1.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver1.findElement(By.linkText("Sign Out")).click();
	}

}
