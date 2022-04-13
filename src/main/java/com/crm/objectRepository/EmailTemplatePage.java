package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class EmailTemplatePage 
{
	@FindBy(linkText = "Mail Merge")
	private WebElement MailMergeLink;
	
	public EmailTemplatePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getMailMergeLink() 
	{
		return MailMergeLink;
	}
	public void emailTemplatePage(WebDriver driver)
	{
		WebDriverUtility.javaScriptClick(driver, MailMergeLink);
	}
}

