package com.colosa.qa.automatization.common;

//import org.openqa.selenium.IWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Browser {

	private static WebDriver _driver = null;

	public static WebDriver driver() throws FileNotFoundException, IOException{
		if(_driver == null)
			_driver = (ConfigurationSettings.getInstance().getSetting("browse.name")=="chrome")?new ChromeDriver():
					((ConfigurationSettings.getInstance().getSetting("browser.name")=="ie")?new InternetExplorerDriver():
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
}