package com.crm.genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * This class is a collection webdriver actions
 * @author Prakruthi
 *
 */
public class WebDriverUtility 
{
	/** 
	 * This method is used for implicit wait
	 * @param driver
	 */
	public static void waitforPageLoad(WebDriver driver,long timeouts)
	{
		driver.manage().timeouts().implicitlyWait(timeouts, TimeUnit.SECONDS);
	}
	
	/**
	 * This method is used wait untill element to visible
	 * @param driver
	 * @param tomeouts
	 * @param element
	 */
	public static void waitUntilelementVisible(WebDriver driver,long timeouts,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	/**
	 * This method is used wait until element to visible with specific polling time
	 * @param driver
	 * @param tomeouts
	 * @param element
	 * @param pollingtime
	 */
	public static void waitUntillElementVisiblewithCustomPoll(WebDriver driver,long timeouts,WebElement element,long pollingtime)
	{
		WebDriverWait wait = new WebDriverWait(driver, timeouts);
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
		wait.pollingEvery(Duration.ofSeconds(pollingtime));
		wait.ignoring(Throwable.class);
	}
	
	/**
	 * This method is used wait until element is clickable with customize time and polling period
	 * @param element
	 * @param timeouts
	 * @param pollingtime
	 * @throws InterruptedException
	 */
	public static void customWaitTillElementClickable(WebElement element,int timeouts,int pollingtime) throws InterruptedException  
	{
		int count=0;
		while(count<=timeouts)
		{
			try 
			{
				element.click();
				break;
			}
			catch(NoSuchElementException e)
			{
				Thread.sleep(pollingtime);
				count++;
			}
		}
	}
	
	/**
	 * This method is use to maximize browser
	 * @param driver
	 */
	public static void maximizeBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is use to open the application with maximize
	 */
	public static void launchApplicationWithMaximize(WebDriver driver,String url,long timeouts)
	{
		driver.get(url);
		maximizeBrowser(driver);
		waitforPageLoad(driver,timeouts);
	}
	
	/**
	 * This method is use to open the application
	 * @param driver
	 * @param url
	 * @param timeouts
	 */
	public static void launchApplication(WebDriver driver,String url,long timeouts)
	{
		driver.get(url);
		waitforPageLoad(driver,timeouts);
	}
	
	/**
	 * This method is use switch to particular window
	 */
	public static void switchToWindow(WebDriver driver,String partialTitleText)
	{
		Set<String> winId = driver.getWindowHandles();
		for(String id:winId)
		{
			driver.switchTo().window(id);
			if(driver.getTitle().contains(partialTitleText))
			{
				break;
			}
		}
	}
	
	/**
	 * This method is used to move to the cursor on element
	 */
	public static void moveToElement(WebDriver driver,WebElement element)
	{
		Actions action= new Actions(driver);
		action.moveToElement(element).perform();;
	}
	
	/**
	 * This method is used to right click on element
	 */
	public static void rightClick(WebDriver driver,WebElement element)
	{
		Actions action= new Actions(driver);
		action.contextClick(element).perform();
	}
	
	/**
	 * This method is used to double click on element
	 * @param driver
	 * @param element
	 */
	public static void doubleClick(WebDriver driver,WebElement element)
	{
		Actions action= new Actions(driver);
		action.doubleClick(element).perform();
	}
	
	/**
	 * This method is use to select the drop down option by index
	 */
	public static void select(WebElement element, int index)
	{
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	

	/**
	 * This method is use to select the drop down option by Visibletext
	 */
	public static void select(WebElement element, String Visibletext)
	{
		Select select = new Select(element);
		select.selectByVisibleText(Visibletext);
	}
	

	/**
	 * This method is use to select the drop down option by value
	 */
	public static void select(String Value,WebElement element)
	{
		Select select = new Select(element);
		select.selectByValue(Value);
	}
	
	/**
	 * This method is use to quit browser instance
	 */
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	
	/**
	 * This method is used to swtich to frame by index
	 */
	public static void frame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to swtich to frame by nameorid
	 */
	public static void frame(WebDriver driver,String nameOrid)
	{
		driver.switchTo().frame(nameOrid);
	}
	
	/**
	 * This method is used to swtich to frame by element
	 */
	public static void frame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	
	/**
	 * This method is use to take the screnn shot of the failed script
	 * @param driver
	 * @param Filename
	 * @throws IOException 
	 */
	public static void takeScreenShotofFailedScript(WebDriver driver,String Filename) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenShot/"+Filename+"_"+JavaUtility.getCurrentDateandTime()+".png");
		//Files.copy(src, dst);
		FileUtils.copyFile(src,dst);
	}
	
	/**
	 * This method is use to take the screnn shot of the failed script and it will return back to absolute path of screen shot where it is stored
	 * @param driver
	 * @param Filename
	 * @return
	 * @throws IOException
	 */
	public static String takeScreenShotandGetPath(WebDriver driver,String Filename) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenShot/"+Filename+"_"+JavaUtility.getCurrentDateandTime()+".png");
		FileUtils.copyFile(src,dst);
		String absolutePath = dst.getAbsolutePath();
		return absolutePath;
	}	
	
	
	/**
	 * This method is use click on elemet using javascript executor
	 * @param driver
	 * @param element
	 */
	public static void javaScriptClick(WebDriver driver,WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].click();", element);
	}
	
	
	/**
	 * This method is use to open the application using JSExecutor
	 * @param driver
	 * @param url
	 */
	public static void  openApplicationThroughJs(WebDriver driver,String url) 
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.location='"+url+"'");
	}
	
	/**
	 * This method is use send text to text field
	 * @param driver
	 * @param element
	 * @param value
	 */
	public static void javaScriptSendKeys(WebDriver driver, WebElement element,String value)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='"+value+"'", element);
	}
	
	/**
	 * This method is use scroll th webpage to target element
	 * @param driver
	 * @param element
	 */
	public static void scrollToElement(WebDriver driver,WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("argument[0].scrollIntoView();",element);
	}
	
	/**
	 * This method is use to scroll the web page down
	 * @param driver
	 */
	public static void srollDownToPage(WebDriver driver,String upOrdown)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.srollTo(0,document.body.scrollHeight);" );
	}
	public static String getTextAlertPopup(WebDriver driver)
	{
		String popuptext=driver.switchTo().alert().getText();
		return popuptext;
	}
	public static void acceptAlertPopup(WebDriver driver)
	{
		driver.switchTo().alert().accept();
		
	}
	public static void dissmisstAlertPopup(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();	
	}
}
