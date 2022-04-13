package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage //step1:we should create the public class and give the class name as web page name
{
	//step2:declaration---> we have to declare elements as private using private keyword
	@FindBy(name = "user_name")
	private WebElement userTxtFld;
	@FindBy(name = "user_password")
	private WebElement passowrdTxtFld;
	@FindBy(id = "submitButton")
	private WebElement submitbtn;
	
	//step3:create public constructor and initialize the elements /variables
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//step4:Utilization-----> by developing public getters and business library
	//way1:by creating public getters
		public WebElement getUserTxtFld()
		{
		return userTxtFld;
		}

		public WebElement getPassowrdTxtFld() 
		{
		return passowrdTxtFld;
		}

		public WebElement getSubmitbtn() 
		{
		return submitbtn;
		}
		
		//way2: by creating business library
		public void loginAction(String username,String password)
		{
			userTxtFld.sendKeys(username);
			passowrdTxtFld.sendKeys(password);
			submitbtn.click();
		}
	}
