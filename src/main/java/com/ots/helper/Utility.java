package com.ots.helper;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ots.dataProvider.ConfigReader;

public class Utility 
{
	

	public static WebElement getElement(WebDriver driver,By locator) 
	{
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(locator));
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
		if(ConfigReader.getProperty("elementHighlight").equalsIgnoreCase("true"))
		{
			
			highLightElement(driver, element);
		}
		
		return element;
	}
	
	public static WebElement highLightElement(WebDriver driver, WebElement ele)
	{
		
			JavascriptExecutor js=(JavascriptExecutor)driver;
			
			js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;')",ele);
			
			try 
			{
				Thread.sleep(500);
			} catch (InterruptedException e) 
			{
				
			}
			
			js.executeScript("arguments[0].setAttribute('style','border: 2px solid black;')",ele);
		
		
		return ele;
	}
	
	

	public static int getResponseCode(String url)
	{
		HttpURLConnection urlConn;
		
		int code=0;
		try 
		{
			System.out.println("*************************************");
			
			urlConn = (HttpURLConnection)(new URL(url)).openConnection();
			
			System.out.println("LOG:INFO- Trying to connect "+url);
			
			urlConn.connect();
			
			System.out.println("LOG:INFO- Connection Done");
			
			if(!url.contains("twitter"))
			{
				code=urlConn.getResponseCode();
			}
			else
			{
				code=200;
			}
			
			 
			System.out.println("*************************************");
			
		} catch (IOException e) 
		{
			System.out.println("Could not connect to server "+e.getMessage());
		}
	
		return code;
	}
	
	
	public static String captureScreenshotAsBase64(WebDriver driver)
	{
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		String screenshot=ts.getScreenshotAs(OutputType.BASE64);
		
		return screenshot;
	}

	
	public static void captureScreenshot(WebDriver driver)
	{
		try 
		{
			FileHandler.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File("./screenshot/Selenium_"+Utility.getCurrentDateTime()+".png"));
			
		} catch (IOException e) 
		{
			System.out.println("Screenshot Failed "+e.getMessage());
		}
	}
	
	public static void captureScreenshot(WebDriver driver,WebElement element)
	{
		try 
		{
			FileHandler.copy(element.getScreenshotAs(OutputType.FILE), new File("./screenshot/Selenium_"+Utility.getCurrentDateTime()+".png"));
			
		} catch (IOException e) 
		{
			System.out.println("Screenshot Failed "+e.getMessage());
		}
	}
	
	
	
	public static String getCurrentDateTime()
	{
		Date date=new Date();
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy");
		
		String newDate=dateFormat.format(date);
		
		return newDate;
	}
	
	public static String getCurrentDateTimeNew()
	{
		return new SimpleDateFormat("HH_mm_ss_dd_MM_yyyy").format(new Date());
	}
	
	public static void switchToParentWindow(WebDriver driver)
	{
		driver.switchTo().window(driver.getWindowHandle());
	
	}
	
	public static void switchToChildWindow(WebDriver driver,String applicationTitle)
	{
	
		Set<String> allWindowHandles= driver.getWindowHandles();
		
		for(String handle:allWindowHandles)
		{
			
			driver.switchTo().window(handle);
			
			String title=driver.getTitle();
			
			if(title.contains(applicationTitle))
			{
				break;
			}
			
		}
		
	}
	
	public static void switchToChildWindow(WebDriver driver,int indexOfTabs)
	{
		
		Set<String> allWindowHandles=driver.getWindowHandles();
		
		List<String> allWindowHandlesInList=new ArrayList<String>(allWindowHandles);
		
		String childWindow=allWindowHandlesInList.get(indexOfTabs);
		
		driver.switchTo().window(childWindow);
		
	}
	
	
	public static void switchToChildWindow(WebDriver driver)
	{
		
		String parentWindowHandle=driver.getWindowHandle();
		
		Set<String> allWindowHandles= driver.getWindowHandles();
		
		Iterator<String> itr=allWindowHandles.iterator();
		
		while(itr.hasNext())
		{
			String child=itr.next();
			
			if(!parentWindowHandle.equals(child))
			{
				driver.switchTo().window(child);
			
			}
		}
		
	}
	
	
	public static void selectValueFromList(WebDriver driver, By locator,String value)
	{
		
		System.out.println("LOG:INFO - Finding all element");
		
		List<WebElement> allElements=driver.findElements(locator);
		
		System.out.println("LOG:INFO - Total Elements Found "+allElements.size());
		
		for(WebElement element:allElements)
		{
				String textFromElement=element.getText();
				
				if(textFromElement.contains(value))
				{
					System.out.println("LOG:INFO - Found "+value);
					element.click();
					break;
				}
		}
		
		
	}
		
	

}
