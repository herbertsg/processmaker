package com.colosa.qa.automatization.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import com.colosa.qa.automatization.common.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Page{

	WebElement weSectionButton;
	WebElement weLogout;
	List<WebElement> wel;

	public Main() throws FileNotFoundException, IOException{

	}

	public void goHome() throws FileNotFoundException, IOException, Exception{
		this.weSectionButton = Browser.getElement("main.WebElement.HomeMenu");
		
		this.weSectionButton.click();
			
	}

	public void goDesigner() throws FileNotFoundException, IOException, Exception{
		this.weSectionButton = Browser.getElement("main.WebElement.DesignerMenu");
		
		this.weSectionButton.click();
	}

	public void goDashboards() throws FileNotFoundException, IOException, Exception{
		this.weSectionButton = Browser.getElement("main.WebElement.DashboardMenu");

		this.weSectionButton.click();
	}

	public void goAdmin() throws FileNotFoundException, IOException, Exception{
		this.weSectionButton = Browser.getElement("main.WebElement.AdminMenu");
		
		this.weSectionButton.click();
	}

	public void logout() throws FileNotFoundException, IOException, Exception{
		
		Browser.getElement("main.WebElement.Logout").click();
	}

}