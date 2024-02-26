package com.ots.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ots.dataProvider.ConfigReader;

public class BrowserFactory {
	
	public static WebDriver driver;
	
	public static WebDriver getDriver()
	{
		return driver;
	}

	public static WebDriver startBrowser(String browserName,String url)
	{
	
		
		if(ConfigReader.getProperty("local").equalsIgnoreCase("true"))
		{
			System.out.println("LOG:INFO - Local Set To True- Test Running On Local System");
			
			if(browserName.contains("Chrome") || browserName.contains("Google Chrome") || browserName.contains("GC"))
			{
				
				 ChromeOptions opt=new ChromeOptions();	
				 
				 if(ConfigReader.getProperty("headless").equalsIgnoreCase("true"))
				 {
					 System.out.println("LOG:INFO - Test will be running in headless mode");
					 
					 opt.addArguments("--headless=new");
				 }
				 if(ConfigReader.getProperty("acceptSSLCertificate").equalsIgnoreCase("true"))
				 {
					 opt.setAcceptInsecureCerts(true);
				 }
				
				 driver=new ChromeDriver(opt);
			}
			else if(browserName.contains("Firefox") || browserName.contains("Mozila Firefox") || browserName.contains("FF"))
			{
				 driver=new FirefoxDriver();
			}
			else if(browserName.contains("Edge") || browserName.contains("Microsoft Edge") || browserName.contains("ME"))
			{
				 driver=new EdgeDriver();
			}
			else 
			{
				System.out.println("Sorry: We do not support "+browserName+ " Please use Chrome,Firefox and Edge For Test Execution");
			}
		}
		else
		{
			String hubURL=ConfigReader.getProperty("gridURL")+":"+ConfigReader.getProperty("gridPort")+"/wd/hub"; 
			
			System.out.println("LOG:INFO - Local Set To False- Test Running On Selenium Grid "+hubURL);
			
			if(browserName.contains("Chrome") || browserName.contains("Google Chrome") || browserName.contains("GC"))
			{
				
				 ChromeOptions opt=new ChromeOptions();	
				 
				 if(ConfigReader.getProperty("headless").equalsIgnoreCase("true"))
				 {
					 System.out.println("LOG:INFO - Test will be running in headless mode");
					 
					 opt.addArguments("--headless=new");
				 }
				 if(ConfigReader.getProperty("acceptSSLCertificate").equalsIgnoreCase("true"))
				 {
					 opt.setAcceptInsecureCerts(true);
				 }
				
				 try 
				 {
					
					 
					driver=new RemoteWebDriver(new URL(hubURL), opt);
					
				} catch (MalformedURLException e) {
					
					System.out.println("Could not connect to grid "+e.getMessage());
				}
			}
			else if(browserName.contains("Firefox") || browserName.contains("Mozila Firefox") || browserName.contains("FF"))
			{
				 driver=new FirefoxDriver();
			}
			else if(browserName.contains("Edge") || browserName.contains("Microsoft Edge") || browserName.contains("ME"))
			{
				 driver=new EdgeDriver();
			}
			else 
			{
				System.out.println("Sorry: We do not support "+browserName+ " Please use Chrome,Firefox and Edge For Test Execution");
			}

			
		}
		

		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("pageLoadTimeOut"))));
		
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("scriptTimeOut"))));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait"))));
		
		driver.get(url);
		
		driver.manage().window().maximize();
		
		return driver;
		
	}
	
	
	


}
