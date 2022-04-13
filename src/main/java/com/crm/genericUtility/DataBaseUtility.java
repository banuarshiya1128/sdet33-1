package com.crm.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;
/**
 * This class contains all generic methods database
 * @author Prakruthi
 *
 */
public class DataBaseUtility 
{
	
	static Connection con;
	
	
	/**
	 * This Method use establish mysql database connection
	 * @param dbUrl
	 * @param dbpuserName
	 */
	public static void getDatabaseConnection(String dbUrl,String dbpuserName,String dbPassword) throws SQLException 
	{
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(dbUrl,dbpuserName,dbPassword);
	}
	/**
	 * This method is use to get the data from the database using column name
	 * @param Query
	 * @param colNumber
	 * @return
	 * @throws Throwable
	 */
	public static ArrayList<String> ExecuteQuery(String Query,int colNumber) throws SQLException
	{ArrayList<String> list = new ArrayList<String>();
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(Query);
	
		while(result.next())
		{
			list.add(result.getString(colNumber));
		}
		statement.close();
		return list;
	}
	public static ArrayList<String> ExecuteQuery(String Query,String colNameorcolNum) throws SQLException
	{ArrayList<String> list = new ArrayList<String>();
		String num="";
		for(int i=0;i<colNameorcolNum.length();i++)
		{
			char ch = colNameorcolNum.charAt(i);
			if(Character.isDigit(ch))
			{
				num=num+colNameorcolNum.charAt(i);
			}
			else
			{
				break;
			}
		}
		Statement statement=con.createStatement();
		ResultSet result = statement.executeQuery(Query);
		int colNum=0;
		String colName=null;
		if(num.equalsIgnoreCase(colNameorcolNum))
		{
			colNum=Integer.parseInt(num);
			while(result.next())
			{
				list.add(result.getString(colNum));
			}
		}
		else
		{
			colName=colNameorcolNum;
			while(result.next())
			{
				list.add(result.getString(colName));
			}
		}
		statement.close();
		return list;
	}
	
	/**
	 * This method is use to get the data from the database using column number
	 * @param Query
	 * @param colName
	 * @return
	 * @throws SQLException
	 */
	/*public static String ExecuteQuery(String Query,String colName) throws SQLException
	{
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(Query);
		String data=null;
		while(result.next())
		{
			System.out.println(result.getString(colName));
		}
		statement.close();
		return data;	
	}
	*/
	/**
	 * This method is use to update the data in the database
	 * @param Query
	 * @throws SQLException
	 */
	public static void ExecuteUpdateQuery(String Query) throws SQLException
	{
		Statement statement = con.createStatement();
		 int result = statement.executeUpdate(Query);
		if(result==1)
		{
			System.out.println("user is created");
		}
		else
		{
			System.out.println("user is  not created");
		}
		statement.close();
	}
	/**
	 * This method is use to close the database connection
	 */
	public static void closeDataBase() throws SQLException
	{
		con.close();
		
	}
	/**
	 * This method is use to verify whether the data is present in database or not
	 * @param Query
	 * @param colName
	 * @param Expddata
	 * @return 
	 * @throws SQLException
	 */
	public static boolean verifyData(String Query,String colNameorcolNum,String Expddata) throws SQLException
	{
	ArrayList<String> listdata = ExecuteQuery(Query,colNameorcolNum);
	boolean flag=false;
	for(String data:listdata)
	{
	if(data.equalsIgnoreCase(Expddata))
	{
		flag=true;
		break;
	}
	}
	return flag;
	}
}
