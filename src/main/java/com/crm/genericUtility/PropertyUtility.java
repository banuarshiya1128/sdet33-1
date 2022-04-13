package com.crm.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * Used to Fetch the common data
 * @author Prakruthi
 *
 */
public class PropertyUtility 
{
	static Properties prop;
	
	
	/**
	 * 
	 * Used to load the Propertyfile
	 * @throws Throwable
	 */
	public static void toLoadPropertyFile(String path) throws Throwable
	{
		FileInputStream fin = new FileInputStream(path);
		prop = new Properties();
		prop.load(fin);
	}
	
	/*
	 * used to get common data
	 * 
	 */
	public static String TogetValue(String key)
	{
		String data = prop.getProperty(key);	
		return data;
	}
	
	
}
	
