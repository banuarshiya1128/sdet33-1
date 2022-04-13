package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class CreatingNewOpportunity 
{
	@FindBy(name = "potentialname")
	private WebElement OpportunityName;
	@FindBy(xpath = "//input[@value='T']")
	private WebElement AssignToType;
	@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[1]")
	private WebElement OrganizationLookUpIcon;
	@FindBy(id = "search_txt")
	private WebElement SearchTxtFld;
	@FindBy(name = "search")
	private WebElement Searchbtn;
	@FindBy(linkText = "Organization Name ")
	private WebElement AddedOrganization;

	public CreatingNewOpportunity(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getOpportunityName() {
		return OpportunityName;
	}

	public WebElement getAssignToType() {
		return AssignToType;
	}

	public WebElement getOrganizationLookUpIcon() {
		return OrganizationLookUpIcon;
	}

	public WebElement getSearchTxtFld() {
		return SearchTxtFld;
	}

	public WebElement getSearchbtn() {
		return Searchbtn;
	}

	public WebElement getAddedOrganization() {
		return AddedOrganization;
	}
	public void createOrganization(WebDriver driver,String Partialtext)
	{
		WebDriverUtility.javaScriptClick(driver, OpportunityName);
		WebDriverUtility.javaScriptClick(driver, OrganizationLookUpIcon);
		WebDriverUtility.switchToWindow(driver, Partialtext);
		
	}
	
}
