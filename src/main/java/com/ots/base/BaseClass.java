package com.ots.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ots.dataProvider.ConfigReader;
import com.ots.factory.BrowserFactory;

public class BaseClass {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void setup()
	{
		System.out.println("LOG:INFO : Setting up browser and application");
		
		driver=BrowserFactory.startBrowser(ConfigReader.getProperty("browser"),ConfigReader.getProperty("qaURL"));
		
		System.out.println("LOG:INFO : Application is up and running");
	}
	
	@AfterMethod
	public void tearDown()
	{
		System.out.println("LOG:INFO : Closing browser");
		
		driver.quit();
		
		System.out.println("LOG:INFO : Session Terminated");
	}

}
