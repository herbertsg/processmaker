package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.ConfigurationSettings;
import com.colosa.qa.automatization.common.BrowserInstance;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;

public class Login extends Page{
	WebElement systemInformationLink;
	WebElement user;
	WebElement password;
	WebElement workspace;
	WebElement submitButton;
	WebElement logOutLink;
	Select languageDropDown;

	public Login(BrowserInstance browserInstance) throws Exception {
        super(browserInstance);

        verifyPage();
	}

    @Override
    public void verifyPage() throws Exception {
        WebElement logoutLink = null;

        browser.switchToDefaultContent();

        this.user = browser.findElementById("form[USR_USERNAME]");
        this.password = browser.findElementById("form[USR_PASSWORD]");

        if(this.user == null || this.password == null){
            System.out.println("Invalid login page ...");
        }
        //this.logOutLink = browser.findElement("login.WebElement.logoutButton");

        //return (this.logOutLink != null);
        //return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void initWebElements() throws Exception{
		this.systemInformationLink = browser.findElement("login.webElement.systemInformationLink");
		this.user = browser.findElement("login.webElement.userName");
		this.password = browser.findElement("login.webElement.password");
		this.workspace = browser.findElement("login.webElement.workspace");
		this.submitButton = browser.findElement("login.webElement.submitButton");

		this.languageDropDown = new Select(browser.findElement("login.webElement.language"));
	}

	public boolean isAtLoginPage() throws Exception{
		this.initWebElements();
		return (this.systemInformationLink != null); 
	}

    /*public  void gotoDefaultUrl() throws IOException {
        String url;
        //default url
        url = ConfigurationSettings.getInstance().getSetting("server.url");

        this.gotoUrl(url);
    } */

	public void loginDefaultUser() throws Exception{		
		this.initWebElements();
		this.user.sendKeys(ConfigurationSettings.getInstance().getSetting("login.defaultUserName"));
		this.workspace.sendKeys(ConfigurationSettings.getInstance().getSetting("login.defaultWorkspace"));
		
		this.languageDropDown.selectByVisibleText(ConfigurationSettings.getInstance().getSetting("login.defaultLanguage"));

		this.submitButton.click();
	}

	public void loginUser(String userName, String password, String workspace, String language) throws Exception{
        browser.waitForElement("login.webElement.userName", 40);
		
		this.initWebElements();

		this.user.sendKeys(userName);
		this.password.sendKeys(password);
		String valueWs = this.workspace.getAttribute("value");
		if(!valueWs.contains("workflow") )
		{
			this.workspace.sendKeys(workspace);
		}else
		{
			this.workspace.sendKeys("");
		}
		this.languageDropDown.selectByVisibleText(language);

		this.submitButton.click();
	}

/*	public void loginUser(String userName, String password, String workspace) throws Exception{

		this.loginUser(userName, password,  workspace, ConfigurationSettings.getInstance().getSetting("login.defaultLanguage"));

	}*/

	public boolean isUserLogged() throws Exception{
        //correct!!!
		this.logOutLink = browser.findElement("login.WebElement.logoutButton");

		return (this.logOutLink != null);
	}
}