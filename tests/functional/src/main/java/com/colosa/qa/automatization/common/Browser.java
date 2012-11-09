package com.colosa.qa.automatization.common;

//import org.openqa.selenium.IWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Browser {

	private static WebDriver _driver = null;

	public static WebDriver driver() throws FileNotFoundException, IOException{
		if(_driver == null)
			_driver = (ConfigurationSettings.getInstance().getSetting("browser.name").equals("chrome"))?new ChromeDriver():
					((ConfigurationSettings.getInstance().getSetting("browser.name").equals("ie"))?new InternetExplorerDriver():
						new FirefoxDriver());
		return _driver;
	}
	//private String cs = ConfigurationSettings.getInstance().getSetting("browse.name");

	public static void gotoUrl(String url){
		//System.out.println("Before browser get:" + url); 
		_driver.get(url);		
	}

	public static String title(){
		return _driver.getTitle();
	}

	public static void close(){
		_driver.close();	
	}

	public static WebElement getElement(String str) throws Exception{
		if(str==null)
			throw new Exception("The the search criteria must be specified");
		str = ConfigurationSettings.getInstance().getSetting(str);
		if(str.lastIndexOf("___")==-1)
			throw new Exception("The search prefix to find the element must be specified");
		String[] searchCriteria = str.split("___");
		WebElement we = null;
		switch(searchCriteria[0])
		{
			case "id":
				we = Browser.driver().findElement(By.id(searchCriteria[1]));
				break;
			case "cssSelector":
				we = Browser.driver().findElement(By.cssSelector(searchCriteria[1]));
				break;
			case "className":
				we = Browser.driver().findElement(By.className(searchCriteria[1]));
				break;
			case "linkText":
				we = Browser.driver().findElement(By.linkText(searchCriteria[1]));
				break;
			case "name":
				we = Browser.driver().findElement(By.name(searchCriteria[1]));
				break;
			case "partialLinkText":
				we = Browser.driver().findElement(By.partialLinkText(searchCriteria[1]));
				break;
			case "tagName":
				we = Browser.driver().findElement(By.tagName(searchCriteria[1]));
				break;
			case "xpath":
				we = Browser.driver().findElement(By.xpath(searchCriteria[1]));
				break;
			default:
				throw new Exception("Invalid search prefix");
		}
		return we;
	}
}