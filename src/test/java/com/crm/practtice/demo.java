package com.crm.practtice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class demo {
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverUtility.openApplicationThroughJs(driver, "http://localhost:8888");
	}

}
