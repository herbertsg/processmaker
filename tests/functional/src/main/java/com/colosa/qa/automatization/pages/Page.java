package com.colosa.qa.automatization.pages;

import java.util.concurrent.TimeUnit;
import com.colosa.qa.automatization.common.*;

public class Page{
	protected String url;
	protected String pageTitle;

	public Page(){
		//init implicit wait time
		Browser.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		url = "";
		pageTitle = "";	
		//System.out.println("Page contructor....:" + url); 	
	}

	public void gotoUrl(){
		//System.out.println("Page.Goto url:" + url); 
		Browser.gotoUrl(url);
		//System.out.println("Browser.goto url:" + url); 
	}

	public boolean isAt(){
		return (Browser.title() == pageTitle);
	}

}