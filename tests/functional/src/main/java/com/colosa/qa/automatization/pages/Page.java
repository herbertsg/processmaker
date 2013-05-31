package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;

public abstract class Page{
	protected String url;
	protected String pageTitle;
    protected BrowserInstance browser;

	public Page(BrowserInstance browser) throws Exception  {
        this.browser = browser;

		//init implicit wait time
        browser.setImplicitWait(30);

		url = "";
		pageTitle = "";	
		//System.out.println("Page contructor....:" + url);
	}

    /**
     * Go to default URL server
     */
	public void gotoUrl(String url){
        this.url = url;
		//System.out.println("Page.Goto url:" + url); 
        this.browser.gotoUrl(url);
		//System.out.println("Browser.goto url:" + url); 
	}

	public boolean isAt(){
		return (this.browser.title() == pageTitle);
	}

    public abstract void verifyPage() throws Exception;
}