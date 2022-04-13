package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.WebDriverUtility;

public class SettingPage 
{
	@FindBy(linkText = "E-mail Templates")
	private WebElement EmailTemplatelink;

	public SettingPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getEmailTemplatelink() {
		return EmailTemplatelink;
	}
	public void EmailTemplateLink(WebDriver driver)
	{
		WebDriverUtility.javaScriptClick(driver, EmailTemplatelink);
	}
}

