package com.colosa.qa.automatization.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import com.colosa.qa.automatization.common.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Login extends Page{
	WebElement systemInformationLink;
	WebElement user;
	WebElement password;
	WebElement workspace;
	WebElement submitButton;
	WebElement logOutLink;

	public Login() throws FileNotFoundException, IOException{
		url = ConfigurationSettings.getInstance().getSetting("server.url");
		pageTitle = "";

		//System.out.println("Login contructor....:" + url); 
	}

	public void initWebElements() throws Exception{
		this.systemInformationLink = Browser.getElement("login.webElement.systemInformationLink");
		this.user = Browser.getElement("login.webElement.userName");
		this.password = Browser.getElement("login.webElement.password");
		this.workspace = Browser.getElement("login.webElement.workspace");
		this.submitButton = Browser.getElement("login.webElement.submitButton");
	}

	public boolean isAtLoginPage() throws Exception{
		this.initWebElements();
		return (this.systemInformationLink != null); 
	}

	public void loginDefaultUser() throws Exception{		
		this.initWebElements();
		this.user.sendKeys(ConfigurationSettings.getInstance().getSetting("login.defaultUserName"));
		this.password.sendKeys(ConfigurationSettings.getInstance().getSetting("login.defaultPassword"));
		this.workspace.sendKeys(ConfigurationSettings.getInstance().getSetting("login.defaultWorkspace"));
		this.submitButton.click();
	}

	public void loginUser(String userName, String password, String workspace) throws Exception{
		this.initWebElements();

		this.user.sendKeys(userName);
		this.password.sendKeys(password);
		this.workspace.sendKeys(workspace);
		this.submitButton.click();
	}

	public boolean isUserLogged() throws Exception{
		this.logOutLink = Browser.getElement("login.WebElement.logoutButton");

		return (this.logOutLink != null);
	}
}