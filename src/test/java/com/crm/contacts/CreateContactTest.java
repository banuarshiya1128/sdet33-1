package com.crm.contacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest 
{
	public static void main(String[] args) throws SQLException, InterruptedException {
		Connection con =null;
		WebDriver driver1;
		try {
		
		WebDriverManager.chromedriver().setup();
	
	Driver driver = new Driver();
	DriverManager.registerDriver(driver);
	 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet3","root","root");
	Statement stat = con.createStatement();
	String s ="select * from vtiger3 ;";
	ResultSet result = stat.executeQuery(s);
	driver1 = new ChromeDriver();
	driver1.manage().window().maximize();
	driver1.get("http://localhost:8888/");
	while(result.next())
	{
	driver1.get(result.getString(1));
	driver1.findElement(By.xpath("//input[@name='user_name']")).sendKeys(result.getString(2));
	driver1.findElement(By.xpath("//input[@name='user_password']")).sendKeys(result.getString(3));
	driver1.findElement(By.id("submitButton")).click();
	driver1.findElement(By.linkText("Contacts")).click();
	driver1.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	driver1.findElement(By.name("lastname")).sendKeys(result.getString(4));
	driver1.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
	driver1.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
	driver1.findElement(By.linkText("Sign Out")).click();
	Thread.sleep(2000);
	
	}
		}
		finally
		{
			con.close();
			System.out.println("database is closed");
		}
		driver1.quit();
}
}
