package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PMSLAConfiguration extends Page{


    public PMSLAConfiguration(BrowserInstance browser) {
        super(browser);
    }

    public void importPlugin() throws FileNotFoundException, IOException{
		browser.switchToFrame("adminFrame");
		browser.switchToFrame("setup-frame");


	}

	public void newSLA() throws FileNotFoundException, IOException, Exception{
		browser.switchToDefaultContent();
		browser.switchToFrame("adminFrame");
		browser.switchToFrame("setup-frame");
		browser.waitForElement(By.id("gridLisSLA"), 3000);
		WebElement el = browser.findElement("PmslaConfiguration.WebElement.gridSLA");
		WebElement elem = el.findElement(By.xpath("div[2]/div[1]/div/table/tbody/tr/td[1]/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/em/button"));
		
		if(elem.getText().equals("New"))
			elem.click();
		else
			 throw new Exception("Button not found");

	}

	public void penalization() throws FileNotFoundException, IOException, Exception{

		WebElement elem = browser.findElement("PmslaConfiguration.WebElement.penalizationCheck");
		WebElement el = elem.findElement(By.xpath("legend/input"));
		el.click();
	}

	public void btnSave() throws FileNotFoundException, IOException, Exception{
		WebElement elem = browser.findElement("PmslaConfiguration.WebElement.btnSave");
		if(elem.getText().equals("Save")){
			elem.click();
		}
		else
			 throw new Exception("Button not found");

	}

	public void btnSelect(String element) throws FileNotFoundException, IOException, Exception{
		WebElement elem = browser.findElement(element);
		elem.click();	
	
	}

	public void selectOption(String findText) throws FileNotFoundException, IOException, Exception{
		WebElement elem = browser.findElementByXPath("div[12]/div");
		List<WebElement> el = elem.findElements(By.xpath("div"));
		for(WebElement elem2:el)
		{
			if(elem2.getText().equals(findText))
		    {
		        elem2.click();
		    }
		}		
		
	}

}