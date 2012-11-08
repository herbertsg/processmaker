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

	public void initWebElements() throws FileNotFoundException, IOException{
		this.systemInformationLink = Browser.driver().findElement(By.linkText(ConfigurationSettings.getInstance().getSetting("login.webElement.systemInformationLink.linkText")));
		this.user = Browser.driver().findElement(By.id(ConfigurationSettings.getInstance().getSetting("login.webElement.userName.id")));
		this.password = Browser.driver().findElement(By.id(ConfigurationSettings.getInstance().getSetting("login.webElement.password.id")));
		this.workspace = Browser.driver().findElement(By.id(ConfigurationSettings.getInstance().getSetting("login.webElement.workspace.id")));
		this.submitButton = Browser.driver().findElement(By.id(ConfigurationSettings.getInstance().getSetting("login.webElement.submitButton.id")));
	}

	public boolean isAtLoginPage() throws FileNotFoundException, IOException{
		this.initWebElements();
		return (this.systemInformationLink != null); 
	}

	public void loginDefaultUser() throws FileNotFoundException, IOException{		
		this.initWebElements();
		this.user.sendKeys(ConfigurationSettings.getInstance().getSetting("login.defaultUserName"));
		this.password.sendKeys(ConfigurationSettings.getInstance().getSetting("login.defaultPassword"));
		this.workspace.sendKeys(ConfigurationSettings.getInstance().getSetting("login.defaultWorkspace"));
		this.submitButton.click();
	}

	public void loginUser(String userName, String password, String workspace) throws FileNotFoundException, IOException{
		this.initWebElements();

		this.user.sendKeys(userName);
		this.password.sendKeys(password);
		this.workspace.sendKeys(workspace);
		this.submitButton.click();
	}

	public boolean isUserLogged() throws FileNotFoundException, IOException{
		this.logOutLink = Browser.driver().findElement(By.linkText("Logout"));

		return (this.logOutLink != null);
	}
}