package com.crm.organization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationDropdownTest {

	public static void main(String[] args) throws Throwable {
		FileInputStream fin = new FileInputStream("./src/test/resources/commonData1.properties");
		Properties prop = new Properties();
		prop.load(fin);
		String url=prop.getProperty("url");
		String username=prop.getProperty("username");
		String password=prop.getProperty("password");
		String timeOuts=prop.getProperty("timeout");
		long timeout1=Long.parseLong(timeOuts);
		String browser=prop.getProperty("browser");		
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
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(timeout1, TimeUnit.SECONDS);
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		String Expected_org = "sdet1008";
		driver.findElement(By.name("accountname")).sendKeys(Expected_org);
		WebElement industry = driver.findElement(By.xpath("//select[@name='industry']"));
		String Actual_ind = "Education";
		Select s = new Select(industry);
		s.selectByValue("Education");
		WebElement type = driver.findElement(By.xpath("//select[@name='accounttype']"));
		String Actual_Type="Analyst";
		Select s1 = new Select(type);
		s1.selectByValue("Analyst");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		WebElement Acutal_org = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']"));
		String A_O=Acutal_org.getText();
		if(Expected_org.equalsIgnoreCase(A_O))
		{
			System.out.println("organization name is created");	
		}
		WebElement Expected1 = driver.findElement(By.id("dtlview_Industry"));
		String Expected_ind=Expected1.getText();
		if(Actual_ind.equalsIgnoreCase(Expected_ind))
		{
			System.out.println("industry is created");	
		}
		String Expect_Title=driver.findElement(By.xpath("//select[@name='assigned_user_id']")).getText();
		if(Expected_org.equalsIgnoreCase(A_O))
		{
			System.out.println("organization type is created");	
		}
		driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}

}
