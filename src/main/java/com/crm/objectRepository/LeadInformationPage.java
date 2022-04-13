package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class LeadInformationPage 
{
	@FindBy(xpath = "//input[@title='Delete [Alt+D]']")
	private WebElement Deletebtn;
	@FindBy(linkText = "Last Name")
	private WebElement lastname;
	
	public LeadInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getDeletebtn() {
		return Deletebtn;
	}
	public WebElement getlastname() {
		return lastname;
	}
	public String deleteLead(WebDriver driver)
	{
		WebDriverUtility.javaScriptClick(driver, Deletebtn);
		String alertmessage = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return alertmessage;
	}
	public void checkForDeletedlastname(String lastname1)
	{
		if (lastname.equals(lastname1))
		{
			System.out.println("new last name is added");
		}
		else
		{
			System.out.println("new last name is not added");
		}
	}

}
