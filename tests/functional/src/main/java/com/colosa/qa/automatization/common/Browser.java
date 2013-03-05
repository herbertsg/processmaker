package com.colosa.qa.automatization.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Browser {

	private static WebDriver _driver = null;
    private static InstanceBrowser _instanceBrowser = null;
	private static long _timeoutSeconds = 0;

	public static WebDriver driver() throws FileNotFoundException, IOException{
		if(_driver == null){

			String browserMode = ConfigurationSettings.getInstance().getSetting("browser.mode");
			String browserName = "firefox";

			browserName = ConfigurationSettings.getInstance().getSetting("browser.name");

			if(browserMode.equals("local")){
                _instanceBrowser = new InstanceBrowser(browserMode, browserName, "", "", "");
                _driver = _instanceBrowser.getInstanceDriver();
			}

			if(browserMode.equals("remote")){
				String browserVersion = ConfigurationSettings.getInstance().getSetting("browser.version");
				String browserPlatform = ConfigurationSettings.getInstance().getSetting("browser.platform");
				String remoteServerUrl = ConfigurationSettings.getInstance().getSetting("remote.server.url");

				System.out.printf("Remote browser:%s, version:%s, platform:%s, url:%s \n", 
					browserName, browserVersion, browserPlatform, remoteServerUrl); 
				
				_instanceBrowser = new InstanceBrowser(browserMode, browserName, browserVersion, browserPlatform, remoteServerUrl);

                _driver = _instanceBrowser.getInstanceDriver();
			}
		}
		return _driver;
	}

	public static void gotoUrl(String url){
		_instanceBrowser.gotoUrl(url);
	}

	public static String title(){
		return _instanceBrowser.title();
	}

	public static void close(){
		_instanceBrowser.close();
	}

	public static By getBySearchCriteria(String str, Object... args) throws Exception{
		return _instanceBrowser.getBySearchCriteria(str, args);
	}	

	public static By getBySearchCriteriaUsingCriteria(String searchCriteria) throws Exception{
		return _instanceBrowser.getBySearchCriteriaUsingCriteria(searchCriteria);
	}	

	public static WebElement getParent(WebElement element) throws Exception{
		return _instanceBrowser.getParent(element);
	}

	public static WebElement getElement(String str) throws Exception{
		return  _instanceBrowser.getElement(str);
	}

	public static List<WebElement> getElements(String str) throws Exception{
		return _instanceBrowser.getElements(str);
	}

	public static WebElement getElementf(String str, Object... args) throws Exception{
		return _instanceBrowser.getElementf(str, args);
	}

	public static Boolean elementExists(String key, int occurrences) throws Exception{
		return _instanceBrowser.elementExists(key, occurrences);
	}

	public static Boolean elementExists(String key) throws Exception{
		return _instanceBrowser.elementExists(key);
	}

	public static Boolean elementExistsSearchCriteria(String searchCriteria, int occurrences) throws Exception{
		return _instanceBrowser.elementExistsSearchCriteria(searchCriteria, occurrences);
	}

	public static Boolean elementExistsSearchCriteria(String searchCriteria) throws Exception{
		return _instanceBrowser.elementExistsSearchCriteria(searchCriteria);
	}

	public static Boolean waitForElement(By elementLocator, long timeoutSeconds) throws Exception{

		return _instanceBrowser.waitForElement(elementLocator, timeoutSeconds);
     }

	public static void waitForElement(String key, long timeoutSeconds) throws Exception{
        _instanceBrowser.waitForElement(key, timeoutSeconds);
     }

     public static List<WebElement> getPreviousSimblingElements(WebElement currentElement){
		return _instanceBrowser.getPreviousSimblingElements(currentElement);
     }
}