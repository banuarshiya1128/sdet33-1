package com.crm.practtice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {

	public static void main(String[] args) throws IOException {
		//Step1:convert physical file into java readable object
		FileInputStream fis = new FileInputStream("./src/test/resources/commonData.properties");
		//step2:create the object of properties
		Properties prop = new Properties();
		//step3:load all keys
		prop.load(fis);
		//step4:fetch dat by using properties
		String url=prop.getProperty("url");
		System.out.println(url);
		String userName=prop.getProperty("userName");
		System.out.println(userName);
		String password=prop.getProperty("password");
		System.out.println(password);
	}

}
