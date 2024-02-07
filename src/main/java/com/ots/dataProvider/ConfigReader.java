package com.ots.dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader 
{
	public static String getProperty(String key)
	{
		Properties pro=new Properties();
		
		try 
		{
			pro.load(new FileInputStream(new File("./Configurations/config.properties")));
			
		} catch (FileNotFoundException e) 
		{
			System.out.println("LOG:FAIL - Could not located property file");
			
		} catch (IOException e) 
		{
			System.out.println("LOG:FAIL - Could not read property file");
		}
		
		String value=pro.getProperty(key);
		
		return value;
	}

}
