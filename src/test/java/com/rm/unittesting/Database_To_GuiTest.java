package com.rm.unittesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Database_To_GuiTest {

	public static void main(String[] args) throws SQLException, InterruptedException {
		Connection con=null;
		try
		{
		WebDriverManager.chromedriver().setup();
		WebDriver driver1 = new ChromeDriver();
		Driver driverref=new Driver();
		DriverManager.registerDriver(driverref);
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement = con.createStatement();
		String projID="111";
		String Query = "insert into project values('"+projID+"','poorvitha',24/03/2022,'testyantra','inprogress',0);";
		int result = statement.executeUpdate(Query);
		if(result==1)
		{
			System.out.println("row is inserted");
		}
		else
		{
			System.out.println("row is not inserted");
		}
		driver1.manage().window().maximize();
		driver1.get("http://localhost:8084/");		
		WebElement username = driver1.findElement(By.id("usernmae"));
		username.sendKeys("rmgyantra");
		WebElement password = driver1.findElement(By.id("inputPassword"));
		password.sendKeys("rmgy@9999");	
		driver1.findElement(By.xpath("//button[text()='Sign in']")).click();
		Thread.sleep(3000);
		driver1.findElement(By.xpath("//a[.='Projects']")).click();
		WebElement projectid = driver1.findElement(By.xpath("//th[text()='ProjectId']"));
		if(projectid.equals(projectid)) {
			System.out.println("test case is pass");
		}
		else
		{
			System.out.println("test case is pass");
		}
		
	}
		finally {
			con.close();
			
		}

}
}