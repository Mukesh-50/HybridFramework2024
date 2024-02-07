package com.ots.testcases;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import com.ots.base.BaseClass;
import com.ots.dataProvider.CustomDataProvider;
import com.ots.pages.HomePage;
import com.ots.pages.LoginPage;

public class LoginTest extends BaseClass
{

	@Test(dataProvider = "loginCredentialsExcel",dataProviderClass = CustomDataProvider.class)
	public void loginToApplicationWithValidCredentials(String username,String password)
	{
	
		LoginPage login=new LoginPage(driver);
		
		HomePage home= login.loginToApplication(username, password);
		
		AssertJUnit.assertTrue(home.isManageOptionDisplayed());
		
	}
	
}
