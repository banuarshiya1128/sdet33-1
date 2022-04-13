package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.WebDriverUtility;

public class NewTemplatePage 
{
	@FindBy(name  = "binFile")
	private WebElement ChooseFile;
	@FindBy(xpath = "//input[@title='Save']")
	private WebElement SaveButton;
	public NewTemplatePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	public WebElement getChooseFile() {
		return ChooseFile;
	}
	public WebElement getSaveButton() {
		return SaveButton;
	}
	public void newTemplatePage(WebDriver driver,String path)
	{
		ChooseFile.sendKeys(path);
		WebDriverUtility.javaScriptClick(driver, SaveButton);
	}
}
