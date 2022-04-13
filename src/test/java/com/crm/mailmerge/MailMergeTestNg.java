package com.crm.mailmerge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
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

public class MailMergeTestNg extends BaseClass{

	@Test
	public  void mailMergeTestNg() throws Throwable 
	{
		
		SettingPage settingspage= new SettingPage(driver);
		EmailTemplatePage emailtemplatepage = new EmailTemplatePage(driver);
		MailMergeTemplatePage mailmergetemplatepage = new MailMergeTemplatePage(driver);
		NewTemplatePage newtemplatepage= new NewTemplatePage(driver);
		homepage.settingsImagClick(driver);
		settingspage.EmailTemplateLink(driver);
		emailtemplatepage.emailTemplatePage(driver);
		mailmergetemplatepage.addTemplate(driver);
		newtemplatepage.newTemplatePage(driver, "C:\\poorvitha.docx");
		
	}
}
