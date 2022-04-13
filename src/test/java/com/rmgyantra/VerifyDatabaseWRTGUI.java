package com.rmgyantra;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyDatabaseWRTGUI {

	public static void main(String[] args) throws SQLException, Throwable {
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		WebDriverManager.chromedriver().setup();
		WebDriver driver1 = new ChromeDriver();
		 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet3","root","root");
		
			FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
			Properties prop = new Properties();
			prop.load(fis);
			String url=prop.getProperty("url");
			String userName=prop.getProperty("userName");
			String password=prop.getProperty("password");
			String browser=prop.getProperty("browser");
			String timeOuts=prop.getProperty("timeOuts");
			driver1.manage().window().maximize();
			driver1.get("http://localhost:8084/");
			driver1.findElement(By.id("usernmae")).sendKeys(userName);
			driver1.findElement(By.id("inputPassword")).sendKeys(password);
			driver1.findElement(By.xpath("//button[.='Sign in']")).click();
			driver1.findElement(By.linkText("Projects")).click();
			
			
	}

}
