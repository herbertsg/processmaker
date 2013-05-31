package com.colosa.qa.automatization.common;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserInstance {

	private WebDriver _instanceDriver = null;

    public static BrowserSettings getDefaultBrowserSettings() throws IOException {
        BrowserSettings browserSettings = new BrowserSettings();

        //set browser information from configuration file
        browserSettings.setBrowserMode(ConfigurationSettings.getInstance().getSetting("browser.mode"));
        browserSettings.setBrowserName(ConfigurationSettings.getInstance().getSetting("browser.name"));
        browserSettings.setBrowserVersion(ConfigurationSettings.getInstance().getSetting("browser.version"));
        browserSettings.setBrowserPlatform(ConfigurationSettings.getInstance().getSetting("browser.platform"));
        browserSettings.setRemoteServerUrl(ConfigurationSettings.getInstance().getSetting("remote.server.url"));

        //Read browser information from registry and update in object
        //-Dbrowser.settings=1 -Dbrowser.mode=local -Dbrowser.name=firefox
        //-Dbrowser.settings=1 -Dbrowser.mode=remote -Dbrowser.name=firefox -Dremote.server.url=http:// browser.version=
        //if variable set from system properties overrride configuration of browser
        String setBrowserSettings = System.getProperty("browser.settings");

        if(setBrowserSettings != null){
            System.out.printf("User Browser Settings detected\n");
            browserSettings.setBrowserMode(System.getProperty("browser.mode"));
            browserSettings.setBrowserName(System.getProperty("browser.name"));
            browserSettings.setBrowserVersion(System.getProperty("browser.version"));
            browserSettings.setBrowserPlatform(System.getProperty("browser.platform"));
            browserSettings.setRemoteServerUrl(System.getProperty("remote.server.url"));
        }

        return browserSettings;

    }

    public BrowserInstance(BrowserSettings browserSettings) throws MalformedURLException {
        this(browserSettings.getBrowserMode(), browserSettings.getBrowserName(),
                browserSettings.getBrowserVersion(), browserSettings.getBrowserPlatform(),
                browserSettings.getRemoteServerUrl());

    }

	public BrowserInstance(String browserMode, String browserName, String browserVersion, String browserPlatform, String remoteServerUrl) throws MalformedURLException {
		//create a new instance of the Browser

		if(browserMode.equals("local")){
            if(browserName.equals("chrome")){
                //start chrome maximized by default,
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--start-maximized");
//
//                _instanceDriver = new ChromeDriver(options);
                _instanceDriver = new ChromeDriver();
            }
            if(browserName.equals("ie")){
                _instanceDriver = new InternetExplorerDriver();
            }
            if(browserName.equals("firefox")){
			    _instanceDriver = new FirefoxDriver();
            }

		}

		if(browserMode.equals("remote")){

			System.out.printf("Instance Remote browser:%s, version:%s, platform:%s, url:%s \n",
				browserName, browserVersion, browserPlatform, remoteServerUrl); 

			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

			if(browserName.equals("chrome")){
                //start chrome maximized by default
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--start-maximized");

				desiredCapabilities = DesiredCapabilities.chrome();

//                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
			}else if(browserName.equals("ie")){
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

			_instanceDriver = new RemoteWebDriver(url, desiredCapabilities);
		}

        //maximize browser by default
        maximize();
    }

    public WebDriver getInstanceDriver(){
        return _instanceDriver;
    }

	public void gotoUrl(String url){
		_instanceDriver.get(url);		
	}	

	public String title(){
		return _instanceDriver.getTitle();
	}

	public void close(){
		_instanceDriver.close();	
	}

    public void quit(){
        _instanceDriver.quit();
    }

    public void maximize(){

        _instanceDriver.manage().window().maximize();

    }

    public void switchToDefaultContent(){
        _instanceDriver.switchTo().defaultContent();
    }

    public void switchToFrame(String frame){
        _instanceDriver.switchTo().frame(frame);
    }

    public Alert switchToAlert(){
        return _instanceDriver.switchTo().alert();
    }

    public void setImplicitWait(int seconds){
        _instanceDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

	public By getBySearchCriteria(String str, Object... args) throws Exception{
		By by = null;

		String key = str;
		if(str==null)
			throw new Exception("The the search criteria must be specified");

		if(str.lastIndexOf(Constant.SEARCH_CRITERIA_SEPARATOR)==-1) {
			str = ConfigurationSettings.getInstance().getSetting(str);

			if(str == null)
				throw new Exception("There's no value for the key: "+key);

			str = String.format(str, args);

			if(str.lastIndexOf(Constant.SEARCH_CRITERIA_SEPARATOR)==-1)
				throw new Exception("The search prefix to find the element must be specified");
		}

		by = this.getBySearchCriteriaUsingCriteria(str);

		return by;
	}

	public By getBySearchCriteriaUsingCriteria(String searchCriteria) throws Exception{
		By by = null;

		System.out.println("searching element using criteria => "+ searchCriteria);

		if(searchCriteria==null)
			throw new Exception("The the search criteria must be specified");

		if(searchCriteria.lastIndexOf(Constant.SEARCH_CRITERIA_SEPARATOR)==-1)
			throw new Exception("The search prefix to find the element must be specified");

		String[] criteria = searchCriteria.split(Constant.SEARCH_CRITERIA_SEPARATOR, 2);

		System.out.println("searching element => criteria: "+ criteria[0] + " value:" + criteria[1]);

		if(criteria[0].equals("id"))
			by = By.id(criteria[1]);
		else if(criteria[0].equals("cssSelector"))
			by = By.cssSelector(criteria[1]);
		else if(criteria[0].equals("className"))
			by = By.className(criteria[1]);
		else if(criteria[0].equals("linkText"))
			by = By.linkText(criteria[1]);
		else if(criteria[0].equals("name"))
			by = By.name(criteria[1]);
		else if(criteria[0].equals("partialLinkText"))
			by = By.partialLinkText(criteria[1]);
		else if(criteria[0].equals("tagName"))
			by = By.tagName(criteria[1]);
		else if(criteria[0].equals("xpath"))
			by = By.xpath(criteria[1]);
		else
			throw new Exception("Invalid search prefix");
		return by;		
	}

    public By getBySearchCriteria(FieldKeyType criteria, String key) throws Exception {
        By by = null;

        if(criteria == FieldKeyType.ID)
            by = By.id(key);
        else if(criteria == FieldKeyType.CSSSELECTOR)
            by = By.cssSelector(key);
        else if(criteria == FieldKeyType.CLASSNAME)
            by = By.className(key);
        else if(criteria == FieldKeyType.LINKTEXT)
            by = By.linkText(key);
        else if(criteria == FieldKeyType.NAME)
            by = By.name(key);
        else if(criteria == FieldKeyType.PARTIALLINKTEXT)
            by = By.partialLinkText(key);
        else if(criteria == FieldKeyType.TAGNAME)
            by = By.tagName(key);
        else if(criteria == FieldKeyType.XPATH)
            by = By.xpath(key);
        else
            throw new Exception("Invalid search prefix");
        return by;
    }

	public WebElement getParent(WebElement element) throws Exception{		
		return element.findElement(By.xpath(".."));
	}

	public WebElement findElement(String str) throws Exception{
		return this.findElement(this.getBySearchCriteria(str));
	}

	private WebElement findElement(By searchCriteria) throws Exception{
		WebElement we = null;

		we = _instanceDriver.findElement(searchCriteria);
		
		return we;
	}

    public WebElement findElementById(String elementId) throws Exception{
        WebElement we = null;

        we = _instanceDriver.findElement(By.id(elementId));

        return we;
    }

    public WebElement findElementByXPath(String elementXPath) throws Exception{
        WebElement we = null;

        we = findElementsByXPath(elementXPath).get(0);

        return we;
    }

    public WebElement findElementByClassName(String elementClassName) throws Exception{
        WebElement we = null;

        we = findElementsByClassName(elementClassName).get(0);

        return we;
    }

    public WebElement findElementByCssSelector(String cssSelector) throws Exception{
        WebElement we = null;

        we = findElementsByCssSelector(cssSelector).get(0);

        return we;
    }

    public WebElement findElementByLinkText(String linkText) throws Exception{
        WebElement we = null;

        we = findElementsByLinkText(linkText).get(0);

        return we;
    }

    public WebElement findElementByPartialLinkText(String partialLinkText) throws Exception{
        WebElement we = null;

        we = findElementsByPartialLinkText(partialLinkText).get(0);

        return we;
    }

    public WebElement findElementByTagName(String tagName) throws Exception{
        WebElement we = null;

        we = findElementsByTagName(tagName).get(0);

        return we;
    }

    public List<WebElement> findElements(String str) throws Exception{
        return this.findElements(this.getBySearchCriteria(str));
    }

	private List<WebElement> findElements(By searchCriteria) throws Exception{
		List<WebElement> we = null;

		we = _instanceDriver.findElements(searchCriteria);
		
		return we;
	}

    public List<WebElement> findElementsByXPath(String elementXPath) throws Exception{
        List<WebElement> lwe = null;

        lwe = _instanceDriver.findElements(By.xpath(elementXPath));

        return lwe;
    }

    public List<WebElement> findElementsByClassName(String elementClassName) throws Exception{
        List<WebElement> lwe = null;

        lwe = _instanceDriver.findElements(By.className(elementClassName));

        return lwe;
    }

    public List<WebElement> findElementsByName(String elementName) throws Exception{
        List<WebElement> lwe = null;

        lwe = _instanceDriver.findElements(By.name(elementName));

        return lwe;
    }

    public List<WebElement> findElementsByCssSelector(String cssSelector) throws Exception{
        List<WebElement> lwe = null;

        lwe = _instanceDriver.findElements(By.cssSelector(cssSelector));

        return lwe;
    }

    public List<WebElement> findElementsByLinkText(String linkText) throws Exception{
        List<WebElement> lwe = null;

        lwe = _instanceDriver.findElements(By.linkText(linkText));

        return lwe;
    }

    public List<WebElement> findElementsByPartialLinkText(String partialLinkText) throws Exception{
        List<WebElement> lwe = null;

        lwe = _instanceDriver.findElements(By.partialLinkText(partialLinkText));

        return lwe;
    }

    public List<WebElement> findElementsByTagName(String tagName) throws Exception{
        List<WebElement> lwe = null;

        lwe = _instanceDriver.findElements(By.tagName(tagName));

        return lwe;
    }

    public WebElement getElementf(String str, Object... args) throws Exception{
		return this.findElement(this.getBySearchCriteria(str, args));
	}

	public Boolean elementExists(String key, int ocurrences) throws Exception{
		return (this.findElements(this.getBySearchCriteria(key)).size()) == ocurrences;
	}

	public Boolean elementExists(String key) throws Exception{
		return this.elementExists(key, 1);
	}

	public Boolean elementExistsSearchCriteria(String searchCriteria, int ocurrences) throws Exception{
		return (this.findElements(this.getBySearchCriteriaUsingCriteria(searchCriteria)).size()) == ocurrences;
	}

	public Boolean elementExistsSearchCriteria(String searchCriteria) throws Exception{
		return this.elementExistsSearchCriteria(searchCriteria, 1);
	}

	public boolean waitForElement(By elementLocator, long timeoutSeconds) throws Exception{
        
        final By elem = elementLocator;

		WebElement myDynamicElement = (new WebDriverWait(_instanceDriver, timeoutSeconds))
  		.until(new ExpectedCondition<WebElement>(){
        	@Override
		        public WebElement apply(WebDriver d) {
		        	return d.findElement(elem);
				}
			}

		); 
		
		return true;
    }

    /**
     * Wait for page state to be completed
     * @param timeoutSeconds seconds to wait for page to change status to completed
     * @throws Exception
     */
    public void waitForDocumentCompleted(long timeoutSeconds) throws Exception{

        Boolean returnExpectedCondition = (new WebDriverWait(_instanceDriver, timeoutSeconds))
                .until(new ExpectedCondition<Boolean>(){
                    @Override
                    public Boolean apply(WebDriver d) {
                        return (((JavascriptExecutor)_instanceDriver).executeScript("return document.readyState;")).equals("complete");
                        //return ((JavascriptExecutor)_instanceDriver).executeScript("return jQuery.active;") == 0;
                        }
                    }
                );
    }


	public void waitForElement(String key, long timeoutSeconds) throws Exception{

		WebDriverWait wait = new WebDriverWait(_instanceDriver, timeoutSeconds); // wait for timeoutSeconds
		wait.until(ExpectedConditions.presenceOfElementLocated(this.getBySearchCriteria(key)));
    }
    public void waitForElementToBeClickable(FieldKeyType criteria, String key, long timeoutSeconds) throws Exception{

        waitForElementToBeClickable(this.getBySearchCriteria(criteria,key), timeoutSeconds);
    }

    public void waitForElementToBeClickable(By searchCriteria, long timeoutSeconds) throws Exception{

        WebDriverWait wait = new WebDriverWait(_instanceDriver, timeoutSeconds); // wait for timeoutSeconds
        wait.until(ExpectedConditions.elementToBeClickable(searchCriteria));
    }


    public List<WebElement> getPreviousSimblingElements(WebElement currentElement){
		List<WebElement> resultElements = currentElement.findElements(By.xpath("preceding-sibling"));
		
		return resultElements;
     }

    public void executeScript(){
        //((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", elem, fieldData[i][j].fieldValue);

    }
}