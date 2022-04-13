package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.crm.genericUtility.WebDriverUtility;

public class CreatingNewLeadPage 
{
	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement Leadsimg;
	@FindBy(name = "lastname")
	private WebElement LastNameTxt;
	@FindBy(name = "company")
	private WebElement companyTxt;
	@FindBy(xpath = "//span[@id='dtlview_Last Name']")
	//@FindBy(xpath = "//input[@name='lastname' and @class='detailedViewTextBox']")
	//@FindBy(id = "mouseArea_Last Name")
	private WebElement Ac_lastnameTxt;
	@FindBy(id = "email")
	private WebElement EmailTxt;
	@FindBy(id = "secondaryemail")
	private WebElement SecondaryemailTxt;
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement Savebtn;
	
	public CreatingNewLeadPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getLeadsimg() {
		return Leadsimg;
	}

	public WebElement getLastNameTxt() {
		return LastNameTxt;
	}

	public WebElement getCompanyTxt() {
		return companyTxt;
	}

	public WebElement getAc_lastnameTxt() {
		return Ac_lastnameTxt;
	}

	public WebElement getEmailTxt() {
		return EmailTxt;
	}

	public WebElement getSecondaryemailTxt() {
		return SecondaryemailTxt;
	}

	public WebElement getSavebtn() {
		return Savebtn;
	}
	public String createNewLead(WebDriver driver,String lastname,String companyname,String email,String secondaryemail)
	{
		WebDriverUtility.javaScriptClick(driver, Leadsimg);
		//Leadsimg.click();
		WebDriverUtility.javaScriptSendKeys(driver, LastNameTxt, lastname);
		WebDriverUtility.javaScriptSendKeys(driver, companyTxt, companyname);
		WebDriverUtility.javaScriptSendKeys(driver, EmailTxt, email);
		WebDriverUtility.javaScriptSendKeys(driver, SecondaryemailTxt, secondaryemail);
		WebDriverUtility.javaScriptClick(driver, Savebtn);
		String Aclastname = Ac_lastnameTxt.getText();
		return Aclastname;
	}
}
