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


    public static void checkBrowserSettingsFromSystemProperties() throws IOException {
        //Read browser information from registry
        //if variable set from system properties overrride configuration of browser
        String setBrowserSettings = System.getProperty("browser.settings");

        if(setBrowserSettings != null){
            System.out.printf("User Browser Settings detected\n");
            String browserMode = System.getProperty("browser.mode");
            BrowserConfiguration.getInstance().setBrowserMode(browserMode);
            String browserName = System.getProperty("browser.name");
            BrowserConfiguration.getInstance().setBrowserName(browserName);

            System.out.printf("   Browser Mode: %s, Browser Name: %s \n",
                    browserMode, browserName);

            if(browserMode.equals("remote")){
                String browserVersion = System.getProperty("browser.version");
                BrowserConfiguration.getInstance().setBrowserVersion(browserVersion);
                String browserPlatform = System.getProperty("browser.platform");
                BrowserConfiguration.getInstance().setBrowserPlatform(browserPlatform);
                String remoteServerUrl = System.getProperty("remote.server.url");
                BrowserConfiguration.getInstance().setRemoteServerUrl(remoteServerUrl);

                System.out.printf("   Browser Version: %s, Browser Platform: %s, Remote Server URL:%s\n",
                        browserVersion, browserPlatform, remoteServerUrl );

            }
        }

    }

    /**
     * Create a default instance of the browser using default configuration from registry
     * @return Return the instance of the browser created by default
     * @throws FileNotFoundException
     * @throws IOException
     */
	public static WebDriver driver() throws FileNotFoundException, IOException{
		if(_driver == null){
            System.out.printf("Create New Browser instance.\n");

            checkBrowserSettingsFromSystemProperties();


			String browserMode = BrowserConfiguration.getInstance().getBrowserMode(); //ConfigurationSettings.getInstance().getSetting("browser.mode");
			String browserName = "firefox";

			browserName = BrowserConfiguration.getInstance().getBrowserName();

			if(browserMode.equals("local")){
                _instanceBrowser = new InstanceBrowser(browserMode, browserName, "", "", "");
                _driver = _instanceBrowser.getInstanceDriver();
			}

			if(browserMode.equals("remote")){
				String browserVersion = BrowserConfiguration.getInstance().getBrowserVersion();//ConfigurationSettings.getInstance().getSetting("browser.version");
				String browserPlatform = BrowserConfiguration.getInstance().getBrowserPlatform();//ConfigurationSettings.getInstance().getSetting("browser.platform");
				String remoteServerUrl = BrowserConfiguration.getInstance().getRemoteServerUrl();//ConfigurationSettings.getInstance().getSetting("remote.server.url");

				System.out.printf("Remote browser:%s, version:%s, platform:%s, url:%s \n", 
					browserName, browserVersion, browserPlatform, remoteServerUrl); 
				
				_instanceBrowser = new InstanceBrowser(browserMode, browserName, browserVersion, browserPlatform, remoteServerUrl);

                _driver = _instanceBrowser.getInstanceDriver();
			}
		}
		return _driver;
	}

    /*
     * Create local instance of Browser using specified browser
     * @param browserName The browser identifier used to initiate it "firefox, chrome, internet explorer"
     * @return return the WebDriver instance that is created in base to the specified browser
     * @throws FileNotFoundException
     * @throws IOException

    public static WebDriver driver(String browserName) throws FileNotFoundException, IOException{
        if(_driver == null){

            _instanceBrowser = new InstanceBrowser("local", browserName, "", "", "");
            _driver = _instanceBrowser.getInstanceDriver();
        }
        return _driver;
    }
     * Create a remote instance of Browser with the specified parameters
     * @param browserName The browser identifier used to initiate it "firefox, chrome, internet explorer"
     * @param browserVersion  The target browser version 2.0, etc..
     * @param browserPlatform The platform where must be executed the Browser: "WINDOWS, LINUX, MAC"
     * @param remoteServerUrl The remote selenium server URL: http://192.168.11.24:4444/wd/hub
     * @return return the WebDriver instance that is created in base to the specified browser
     * @throws FileNotFoundException
     * @throws IOException
     *
    public static WebDriver driver(String browserName, String browserVersion, String browserPlatform, String remoteServerUrl) throws FileNotFoundException, IOException{
        if(_driver == null){
            System.out.printf("Remote browser:%s, version:%s, platform:%s, url:%s \n",
                    browserName, browserVersion, browserPlatform, remoteServerUrl);

            _instanceBrowser = new InstanceBrowser("remote", browserName, browserVersion, browserPlatform, remoteServerUrl);

            _driver = _instanceBrowser.getInstanceDriver();
        }
        return _driver;
    }*/

    public static void maximize(){
        _instanceBrowser.maximize();
    }

	public static void gotoUrl(String url){
		_instanceBrowser.gotoUrl(url);
	}

	public static String title(){
		return _instanceBrowser.title();
	}

	public static void close(){
		_instanceBrowser.close();
        _driver = null;
        _instanceBrowser = null;
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