package com.crm.contacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.DataBaseUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest1 
{
	public static void main(String[] args) throws Throwable {
		
		WebDriver driver1;
		
		try {

			WebDriverManager.chromedriver().setup();
			String propertyfilepath = ConstantPath.PropertyFilePath;
			String databasepath = ConstantPath.DatabasePath;
			PropertyUtility.toLoadPropertyFile(propertyfilepath);
			String sqlusername = PropertyUtility.TogetValue("sqlusername");
			String sqlpassword = PropertyUtility.TogetValue("sqlpassword");
			String applicationUrl = PropertyUtility.TogetValue("url");
			String timeOut = PropertyUtility.TogetValue("timeOuts");
			long longTimeout = Long.parseLong(timeOut);
			DataBaseUtility.getDatabaseConnection(databasepath,sqlusername,sqlpassword );
			String Query ="select * from vtiger3;";
			ArrayList<String> url = DataBaseUtility.ExecuteQuery(Query, "url");
			ArrayList<String> username = DataBaseUtility.ExecuteQuery(Query, "username");	
			ArrayList<String> password = DataBaseUtility.ExecuteQuery(Query, "password");
			ArrayList<String> lastname = DataBaseUtility.ExecuteQuery(Query, "lastnamen");
			driver1 = new ChromeDriver();
			WebDriverUtility.launchApplicationWithMaximize(driver1, applicationUrl,longTimeout );
			int i=0;
			while(i<url.size())
			{
				driver1.get(url.get(i));
				WebElement userName = driver1.findElement(By.xpath("//input[@name='user_name']"));
				WebDriverUtility.javaScriptSendKeys(driver1, userName, username.get(i));
				WebElement Password = driver1.findElement(By.xpath("//input[@name='user_password']"));
				WebDriverUtility.javaScriptSendKeys(driver1, Password, password.get(i));
				WebElement submitbutton = driver1.findElement(By.id("submitButton"));
				WebDriverUtility.javaScriptClick(driver1, submitbutton);
				WebElement contacticon = driver1.findElement(By.linkText("Contacts"));
				WebDriverUtility.javaScriptClick(driver1, contacticon);
				WebElement createcontact = driver1.findElement(By.xpath("//img[@title='Create Contact...']"));
				WebDriverUtility.javaScriptClick(driver1, createcontact);
				WebElement lastname1 = driver1.findElement(By.name("lastname"));
				WebDriverUtility.javaScriptSendKeys(driver1, lastname1, lastname.get(i));
				WebElement savebutton = driver1.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]"));
				WebDriverUtility.javaScriptClick(driver1, savebutton);
				WebElement signimg = driver1.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				WebDriverUtility.moveToElement(driver1, signimg);
				WebElement signout = driver1.findElement(By.linkText("Sign Out"));
				WebDriverUtility.javaScriptClick(driver1, signout);
				i++;
			}
		
	}
		finally
		{
		DataBaseUtility.closeDataBase();
			System.out.println("database is closed");
		}
		
		WebDriverUtility.closeBrowser(driver1);

	

	
}

}
