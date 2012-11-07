package com.colosa.qa.automatization.common;

//import org.openqa.selenium.IWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser{
	public static WebDriver driver = new ChromeDriver();

	public static void gotoUrl(String url){
		//System.out.println("Before browser get:" + url); 
		driver.get(url);		
	}

	public static String title(){
		return driver.getTitle();
	}

	public static void close(){
		driver.close();	
	}
}