package com.ots.dataProvider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider 
{
	
	@DataProvider(name="loginCredentialsExcel")
	public static Object[][] testDataNew() 
	{
		System.out.println("Running Data Provider From Excel - Creating Test Data");
		
		Object [][]arr=ExcelReader.getDataFromExcel("loginCredentials");
		
		System.out.println("Running Data Provider - Test Data Created");
		
		return arr;
	}

}
