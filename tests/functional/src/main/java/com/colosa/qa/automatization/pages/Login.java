package com.colosa.qa.automatization.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import com.colosa.qa.automatization.common.*;

public class Login extends Page{
	WebElement systemInformationLink;
	WebElement user;
	WebElement password;
	WebElement workspace;
	WebElement submitButton;
	WebElement logOutLink;

	public Login(){
		url = "http://192.168.11.132";
		pageTitle = "";

		//System.out.println("Login contructor....:" + url); 
	}

	public void initWebElements(){
		this.systemInformationLink = Browser.driver.findElement(By.linkText("| System information |"));
		this.user = Browser.driver.findElement(By.id("form[USR_USERNAME]"));
		this.password = Browser.driver.findElement(By.id("form[USR_PASSWORD]"));
		this.workspace = Browser.driver.findElement(By.id("form[USER_ENV]"));
		this.submitButton = Browser.driver.findElement(By.id("form[BSUBMIT]"));
		
	}

	public boolean isAtLoginPage(){
		this.initWebElements();
		return (this.systemInformationLink != null); 
	}

	public void loginDefaultUser(){

		
	}

	public void loginUser(String userName, String password, String workspace){
		this.initWebElements();

		this.user.sendKeys(userName);
		this.password.sendKeys(password);
		this.workspace.sendKeys(workspace);
		this.submitButton.click();
	}


	public boolean isUserLogged(){
		this.logOutLink = Browser.driver.findElement(By.linkText("Logout"));

		return (this.logOutLink != null);	
	}


}