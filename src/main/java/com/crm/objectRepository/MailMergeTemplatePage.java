package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class MailMergeTemplatePage 
{
	@FindBy(xpath = "//input[@value='Add Template']")
	private WebElement AddTemplate;
	public MailMergeTemplatePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getAddTemplate() {
		return AddTemplate;
	}
	public void addTemplate(WebDriver driver) 
	{
		WebDriverUtility.javaScriptClick(driver, AddTemplate);
	}
}
