package com.crm.mailmerge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriverUtility;
import com.crm.objectRepository.EmailTemplatePage;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.MailMergeTemplatePage;
import com.crm.objectRepository.NewTemplatePage;
import com.crm.objectRepository.SettingPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MailMerge {

	public static void main(String[] args) throws Throwable 
	{
		String propertyfilepath = ConstantPath.PropertyFilePath;
		PropertyUtility.toLoadPropertyFile(propertyfilepath);
		String url = PropertyUtility.TogetValue("url");
		String userName = PropertyUtility.TogetValue("userName");
		String password = PropertyUtility.TogetValue("password");
		String browser = PropertyUtility.TogetValue("browser");
		String timeOuts = PropertyUtility.TogetValue("timeOuts");
		long timeout = Long.parseLong(timeOuts);
		WebDriver driver=null;
		if(browser.contentEquals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.contentEquals("firefox"))
		{

			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			System.out.println("browser is not specified");
		}
		WebDriverUtility.launchApplicationWithMaximize(driver, url, timeout);
		LoginPage loginpage = new LoginPage(driver);
		HomePage homepage = new HomePage(driver);
		SettingPage settingspage= new SettingPage(driver);
		EmailTemplatePage emailtemplatepage = new EmailTemplatePage(driver);
		MailMergeTemplatePage mailmergetemplatepage = new MailMergeTemplatePage(driver);
		NewTemplatePage newtemplatepage= new NewTemplatePage(driver);
		loginpage.loginAction(userName, password);
		homepage.settingsImagClick(driver);
		settingspage.EmailTemplateLink(driver);
		emailtemplatepage.emailTemplatePage(driver);
		mailmergetemplatepage.addTemplate(driver);
		newtemplatepage.newTemplatePage(driver, "C:\\poorvitha.docx");
		
	}

}
