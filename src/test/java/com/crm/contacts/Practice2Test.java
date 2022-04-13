package com.crm.contacts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Practice2Test {

	public static void main(String[] args) throws SQLException {
		Driver driverref=new Driver();
		DriverManager.registerDriver(driverref);
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/poorvitha","root","root");
		Statement statement = con.createStatement();
		String Query = "insert into prakuthi values(30,'vara',8894000432,'delhi');";
		int result = statement.executeUpdate(Query);
		if(result==1)
		{
			System.out.println("Test case is pass");
		}
		else
		{
			System.out.println("Test case is fail");
		}
			con.close();
	}

}
