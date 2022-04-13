package com.rm.unittesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Gui_To_DatabaseTest {

	public static void main(String[] args) throws InterruptedException, SQLException, Throwable {
		Connection con=null;
		try
		
		{
			Driver driverref=new Driver();
		
		FileInputStream fis = new FileInputStream("./src/test/resources/commonData1.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String url=prop.getProperty("url");
		String userName=prop.getProperty("userName");
		String password=prop.getProperty("password");
		String timeOuts=prop.getProperty("timeOuts");
		String browser=prop.getProperty("browser");
		String sheeturl=prop.getProperty("sheeturl");
		String sheetname=prop.getProperty("sheetname");
		FileInputStream fin = new FileInputStream(sheeturl);
		Workbook workbook = WorkbookFactory.create(fin);
		Sheet sheet = workbook.getSheet(sheetname);
		Row row = sheet.getRow(17);
		Cell cell = row.getCell(1);
		String ProjectName=cell.getStringCellValue();
		Cell cell1 = row.getCell(2);
		String ProjectManager = cell1.getStringCellValue();
		Cell cell2 = row.getCell(3);
		String ProjectSatus = cell2.getStringCellValue();

		long timeout1=Long.parseLong(timeOuts);
		
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
		driver1.manage().timeouts().implicitlyWait(timeout1, TimeUnit.SECONDS);
		driver1.get(url);
		WebElement username = driver1.findElement(By.id("usernmae"));
		username.sendKeys(userName);
		WebElement password1 = driver1.findElement(By.id("inputPassword"));
		password1.sendKeys(password);	
		driver1.findElement(By.xpath("//button[.='Sign in']")).click();
	
		driver1.findElement(By.linkText("Projects")).click();
		driver1.findElement(By.xpath("//span[.='Create Project']")).click();
		driver1.findElement(By.name("projectName")).sendKeys(ProjectName);
	
		driver1.findElement(By.name("createdBy")).sendKeys(ProjectManager);
		WebElement status=driver1.findElement(By.xpath("//label[.='Project Status ']/following-sibling::select"));
		Select s=new Select(status);
		s.selectByVisibleText(ProjectSatus);
		driver1.findElement(By.xpath("//input[@value='Add Project']")).click();
		
		
		DriverManager.registerDriver(driverref);
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement = con.createStatement();
		
		String Query = "select * from project;";
		ResultSet result = statement.executeQuery(Query);
		System.out.println(ProjectName);
		while(result.next())
		{
		String s1 =result.getString("project_name");
		
		if(s1.equals(ProjectName))
		{
			System.out.println("in database one row is created");
			break;
		}
		}
		
		Row row1 = workbook.getSheet(sheetname).getRow(17);
		Cell cell4 = row1.createCell(4);
		cell4.setCellValue("pass");
		FileOutputStream fos = new FileOutputStream(sheeturl);
		workbook.write(fos);
		workbook.close();
		}
		
	
		finally 
		{
			con.close();
			
		}

	}
}



