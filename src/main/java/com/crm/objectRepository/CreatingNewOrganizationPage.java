package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class CreatingNewOrganizationPage 
{
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement CreateOrganizationimg;
	@FindBy(name = "accountname")
	private WebElement Organizationname;
	@FindBy(xpath = "//input[@name='assigntype' and @value='T']")
	private WebElement AssignTo;
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement Savebtn;
	@FindBy(id = "dtlview_Organization Name")
	private WebElement org_Name;
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreateOrganizationimg() {
		return CreateOrganizationimg;
	}

	public WebElement getOrganizationname() {
		return Organizationname;
	}

	public WebElement getAssignTo() {
		return AssignTo;
	}
	public WebElement getSavebtn() {
		return Savebtn;
	}
	public  String createOrganizationPage(WebDriver driver,String orgName)
	{
		WebDriverUtility.javaScriptClick(driver, CreateOrganizationimg);
		WebDriverUtility.javaScriptSendKeys(driver, Organizationname, orgName);
		WebDriverUtility.javaScriptClick(driver, AssignTo);
		WebDriverUtility.javaScriptClick(driver, Savebtn);
		String Act_org = org_Name.getText();
		return Act_org;
	}
}
