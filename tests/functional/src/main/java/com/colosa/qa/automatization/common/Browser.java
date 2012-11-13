package com.colosa.qa.automatization.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Browser {

	private static WebDriver _driver = null;

	public static WebDriver driver() throws FileNotFoundException, IOException{
		if(_driver == null){
			String browserMode = ConfigurationSettings.getInstance().getSetting("browser.mode");
			String browserName = "firefox";

			browserName = ConfigurationSettings.getInstance().getSetting("browser.name");

			if(browserMode.equals("local")){
				_driver = (browserName.equals("chrome"))?new ChromeDriver():
					((browserName.equals("ie"))?new InternetExplorerDriver():
						new FirefoxDriver());
			}

			if(browserMode.equals("remote")){
				String browserVersion = ConfigurationSettings.getInstance().getSetting("browser.version");
				String browserPlatform = ConfigurationSettings.getInstance().getSetting("browser.platform");
				String remoteServerUrl = ConfigurationSettings.getInstance().getSetting("remote.server.url");

				System.out.printf("Remote browser:%s, version:%s, platform:%s, url:%s \n", 
					browserName, browserVersion, browserPlatform, remoteServerUrl); 

				DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

				if(browserName.equals("chrome")){
					desiredCapabilities = DesiredCapabilities.chrome();				
				}else if(browserName.equals("internet explorer")){
					desiredCapabilities = DesiredCapabilities.internetExplorer();					
				}else if(browserName.equals("firefox")){
					desiredCapabilities = DesiredCapabilities.firefox();					
				}

				if(browserVersion != null && !browserVersion.equals("")){
					desiredCapabilities.setVersion(browserVersion);
				}

				if(browserPlatform != null && !browserPlatform.equals("")){
					if(browserPlatform.equals("WINDOWS")){
						desiredCapabilities.setPlatform(Platform.WINDOWS);				
					}else if(browserPlatform.equals("LINUX")){
						desiredCapabilities.setPlatform(Platform.LINUX);					
					}else if(browserPlatform.equals("MAC")){
						desiredCapabilities.setPlatform(Platform.MAC);					
					}
				}else{
					desiredCapabilities.setPlatform(Platform.ANY);
				}

				URL url=new URL(remoteServerUrl);

				_driver = new RemoteWebDriver(url, desiredCapabilities);

			}
		}
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
		if(str == null || str.lastIndexOf("___")==-1)
			throw new Exception("The search prefix to find the element must be specified");
		String[] searchCriteria = str.split("___");
		WebElement we = null;

		if(searchCriteria[0].equals("id"))
			we = Browser.driver().findElement(By.id(searchCriteria[1]));
		else if(searchCriteria[0].equals("cssSelector"))
			we = Browser.driver().findElement(By.cssSelector(searchCriteria[1]));
		else if(searchCriteria[0].equals("className"))
			we = Browser.driver().findElement(By.className(searchCriteria[1]));
		else if(searchCriteria[0].equals("linkText"))
			we = Browser.driver().findElement(By.linkText(searchCriteria[1]));
		else if(searchCriteria[0].equals("name"))
			we = Browser.driver().findElement(By.name(searchCriteria[1]));
		else if(searchCriteria[0].equals("partialLinkText"))
			we = Browser.driver().findElement(By.partialLinkText(searchCriteria[1]));
		else if(searchCriteria[0].equals("tagName"))
			we = Browser.driver().findElement(By.tagName(searchCriteria[1]));
		else if(searchCriteria[0].equals("xpathh"))
			we = Browser.driver().findElement(By.xpath(searchCriteria[1]));
		else
			throw new Exception("Invalid search prefix");

		return we;
	}
}