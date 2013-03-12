package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.Browser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Page{
	protected String url;
	protected String pageTitle;

	public Page() throws FileNotFoundException, IOException{
        //create the Browser instance with browser configuration set in the registry

        Browser.driver();

		//init implicit wait time
		Browser.driver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		url = "";
		pageTitle = "";	
		//System.out.println("Page contructor....:" + url); 	
	}

    /*
     * Initialize page with the specified browser, the browser is created locally.
     * @param browserName The browser instance name: "firefox, chrome, internet explorer"
     * @throws FileNotFoundException
     * @throws IOException

    public Page(String browserName ) throws FileNotFoundException, IOException{
        //create the Browser instance with default browser configuration set in the default.conf file
        Browser.driver(browserName);

        //init implicit wait time
        Browser.driver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        url = "";
        pageTitle = "";
        //System.out.println("Page contructor....:" + url);
    }

     * Initialize a page creating a remote browser with the specified configuration.
     * @param browserName The browser identifier: "firefox, chrome, internet explorer"
     * @param browserVersion The browser version.
     * @param browserPlatform The browser platform: "WINDOWS, MAC, LINUX"
     * @param remoteServerUrl The remote server url
     * @throws FileNotFoundException
     * @throws IOException

    public Page(String browserName, String browserVersion, String browserPlatform, String remoteServerUrl) throws FileNotFoundException, IOException{
        //create the remote Browser instance
        Browser.driver(browserName, browserVersion, browserPlatform, remoteServerUrl);

        //init implicit wait time
        Browser.driver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        url = "";
        pageTitle = "";
        //System.out.println("Page contructor....:" + url);
    }*/

	public void gotoUrl(){
		//System.out.println("Page.Goto url:" + url); 
		Browser.gotoUrl(url);
		//System.out.println("Browser.goto url:" + url); 
	}

	public boolean isAt(){
		return (Browser.title() == pageTitle);
	}

}