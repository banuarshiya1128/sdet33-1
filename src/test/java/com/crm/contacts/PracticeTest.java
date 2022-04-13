package com.crm.contacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PracticeTest {
	public static void main(String[] args) throws SQLException {		
		Driver driverref=new Driver();
		DriverManager.registerDriver(driverref);
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/poorvitha","root","root");
		Statement statement = con.createStatement();
		String Query = "select * from prakuthi;";
		ResultSet result = statement.executeQuery(Query);
		while(result.next())
		{
			System.out.println(result.getInt(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+result.getString(4));
		}
		
		
			con.close();
			
}
}

