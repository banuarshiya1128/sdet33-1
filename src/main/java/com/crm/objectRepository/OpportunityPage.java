package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class OpportunityPage 
{
	@FindBy(xpath = "//img[@title='Create Opportunity...']")
	private WebElement CreaetOpprtunityicon;
	public OpportunityPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getCreaetOpprtunityicon() {
		return CreaetOpprtunityicon;
	}
	
	public void CreaetOpprtunityiconclick(WebDriver driver)
	{
		WebDriverUtility.javaScriptClick(driver, CreaetOpprtunityicon);
	}
}
