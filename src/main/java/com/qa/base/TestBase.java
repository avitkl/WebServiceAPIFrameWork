package com.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_201 = 201;
	
	public static Properties prop;
	public TestBase() 
	{
		try 
		{
			prop=new Properties();
			FileInputStream fis=new FileInputStream("F:\\JavaSeleniumFramework\\restapi\\src\\main\\java\\com\\qa\\configuration\\config.properties");
			prop.load(fis);
		}
		catch(IOException e) 
		{
			e.getMessage();
		}
	}
	
	
}
