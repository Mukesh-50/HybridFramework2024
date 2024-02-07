package com.ots.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ots.helper.Utility;

public class LoginPage 
{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private By username=By.id("email1");
	
	private By password=By.id("password1");
	
	private By loginButton=By.xpath("//button[normalize-space()='Sign in']");
	
	private By newUserLink=By.xpath("//a[normalize-space()='New user? Signup']");
	
	
	public HomePage loginToApplication(String user,String pass)
	{
		
		Utility.getElement(driver, username).sendKeys(user);
		
		Utility.getElement(driver, password).sendKeys(pass);
		
		Utility.getElement(driver, loginButton).click();
				
		HomePage page=new HomePage(driver);
		
		return page;
		
	}
	
	public boolean isNewUserLinkVisible()
	{
		return driver.findElement(newUserLink).isDisplayed();
	}
	

}
