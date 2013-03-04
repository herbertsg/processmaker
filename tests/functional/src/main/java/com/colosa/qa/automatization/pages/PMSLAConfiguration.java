package com.colosa.qa.automatization.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import com.colosa.qa.automatization.common.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PMSLAConfiguration extends Page{
	
	public PMSLAConfiguration() throws Exception{
    }

	public static void importPlugin() throws FileNotFoundException, IOException{
		Browser.driver().switchTo().frame("adminFrame");
		Browser.driver().switchTo().frame("setup-frame"); 


	}

	public static void newSLA() throws FileNotFoundException, IOException, Exception{
		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("adminFrame");
		Browser.driver().switchTo().frame("setup-frame");
		Browser.waitForElement(By.id("gridLisSLA"), 3000);
		WebElement el = Browser.getElement("PmslaConfiguration.WebElement.gridSLA");
		WebElement elem = el.findElement(By.xpath("div[2]/div[1]/div/table/tbody/tr/td[1]/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/em/button"));
		
		if(elem.getText().equals("New"))
			elem.click();
		else
			 throw new Exception("Button not found");

	}

	public static void penalization() throws FileNotFoundException, IOException, Exception{

		WebElement elem = Browser.getElement("PmslaConfiguration.WebElement.penalizationCheck");
		WebElement el = elem.findElement(By.xpath("legend/input"));
		el.click();
	}

	public static void btnSave() throws FileNotFoundException, IOException, Exception{
		WebElement elem = Browser.getElement("PmslaConfiguration.WebElement.btnSave");
		if(elem.getText().equals("Save")){
			elem.click();
		}
		else
			 throw new Exception("Button not found");

	}

	public static void btnSelect(String element) throws FileNotFoundException, IOException, Exception{
		WebElement elem = Browser.getElement(element);
		elem.click();	
	
	}

	public static void selectOption(String findText) throws FileNotFoundException, IOException, Exception{
		WebElement elem = Browser.driver().findElement(By.xpath("div[12]/div"));
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