package com.colosa.qa.automatization.common;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class InstanceBrowser {

	private WebDriver _instanceDriver = null;

	public InstanceBrowser(String browserMode, String browserName, String browserVersion, String browserPlatform, String remoteServerUrl) throws MalformedURLException {
		//create a new instance of the Browser
		if(browserMode.equals("local")){
			_instanceDriver = (browserName.equals("chrome"))?new ChromeDriver():
				((browserName.equals("ie"))?new InternetExplorerDriver():
					new FirefoxDriver());
		}

		if(browserMode.equals("remote")){

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

			_instanceDriver = new RemoteWebDriver(url, desiredCapabilities);
		}

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

	public By getBySearchCriteria(String str, Object... args) throws Exception{
		By by = null;

		String key = str;
		if(str==null)
			throw new Exception("The the search criteria must be specified");

		if(str.lastIndexOf("__&&__")==-1) {
			str = ConfigurationSettings.getInstance().getSetting(str);

			if(str == null)
				throw new Exception("There's no value for the key: "+key);

			str = String.format(str, args);

			if(str.lastIndexOf("__&&__")==-1)
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

		if(searchCriteria.lastIndexOf("__&&__")==-1)
			throw new Exception("The search prefix to find the element must be specified");

		String[] criteria = searchCriteria.split("__&&__", 2);

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

	public WebElement getParent(WebElement element) throws Exception{		
		return element.findElement(By.xpath(".."));
	}

	public WebElement getElement(String str) throws Exception{
		return this.findElement(this.getBySearchCriteria(str));
	}

	public List<WebElement> getElements(String str) throws Exception{
		return this.findElements(this.getBySearchCriteria(str));
	}

	private WebElement findElement(By searchCriteria) throws Exception{
		WebElement we = null;

		we = _instanceDriver.findElement(searchCriteria);
		
		return we;
	}

	private List<WebElement> findElements(By searchCriteria) throws Exception{
		List<WebElement> we = null;

		we = _instanceDriver.findElements(searchCriteria);
		
		return we;
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

	public void waitForElement(String key, long timeoutSeconds) throws Exception{

		WebDriverWait wait = new WebDriverWait(_instanceDriver, timeoutSeconds); // wait for timeoutSeconds
		wait.until(ExpectedConditions.presenceOfElementLocated(this.getBySearchCriteria(key)));
     }     

     public List<WebElement> getPreviousSimblingElements(WebElement currentElement){
		List<WebElement> resultElements = currentElement.findElements(By.xpath("preceding-sibling"));
		
		return resultElements;
     }
}