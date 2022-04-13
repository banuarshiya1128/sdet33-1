package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class HomePage 
{
	@FindBy(linkText = "Organizations")
	private WebElement OrganizationTab;
	@FindBy(linkText = "Contacts")
	private WebElement contactsTab;
	@FindBy(linkText = "Leads")
	private WebElement LeadLTab;
	@FindBy(linkText = "Opportunities")
	private WebElement opportunitiesTab;
	@FindBy(xpath = "//img[@src='themes/softed/images/mainSettings.PNG']")
	private WebElement SettingsImag;
	@FindBy(linkText = "CRM Settings")
	private WebElement Crm_SettingsIcon;
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement Signoutimg;
	@FindBy(xpath   = "//a[@href='index.php?module=Users&action=Logout']")
	private WebElement signOutbtn;
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrganizationTab() {
		return OrganizationTab;
	}
	public WebElement getCrm_SettingsIcon() {
		return Crm_SettingsIcon;
	}

	public WebElement getContactsTab() {
		return contactsTab;
	}

	public WebElement getLeadLTab() {
		return LeadLTab;
	}
	public WebElement getSettingsImag() {
		return SettingsImag;
	}

	public WebElement getopportunitiesTab() {
		return opportunitiesTab;
	}

	public WebElement getSignoutimg() {
		return Signoutimg;
	}
	public WebElement getSignOutbtn() {
		return signOutbtn;
	}
	public void organizationClick()
	{
		OrganizationTab.click();
	}
	public void opportunitiesClick()
	{
		opportunitiesTab.click();
	}
	public void leadClick()
	{
		LeadLTab.click();
	}
	public void contactsClick()
	{
		contactsTab.click();
	}
	public void settingsImagClick(WebDriver driver)
	{
		WebDriverUtility.moveToElement(driver, SettingsImag);
		WebDriverUtility.javaScriptClick(driver, Crm_SettingsIcon);	
	}
	public void signOut(WebDriver driver)
	{
		WebDriverUtility.moveToElement(driver, Signoutimg);
		WebDriverUtility.javaScriptClick(driver, signOutbtn);
	}
}
