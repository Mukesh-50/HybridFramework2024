package com.ots.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ots.helper.Utility;

public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private By manageOption=By.xpath("//span[normalize-space()='Manage']");
	
	private By menuIcon=By.xpath("//img[@alt='menu']");
	
	private By signOut=By.xpath("//button[normalize-space()='Sign out']");
	
	public boolean isManageOptionDisplayed()
	{
		return Utility.getElement(driver, manageOption).isDisplayed();
	}
	
	public void logoutFromApplication()
	{
		driver.findElement(menuIcon).click();
		driver.findElement(signOut).click();
	}
	
	
}
