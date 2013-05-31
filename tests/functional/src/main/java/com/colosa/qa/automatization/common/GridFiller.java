package com.colosa.qa.automatization.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GridFiller{

	public static boolean gridFillElements(BrowserInstance browser, FormFieldData[][] fieldData) throws FileNotFoundException, IOException, Exception{
		WebElement elem = null;
		System.out.println("Tamaño del elemento: "+fieldData.length);

		for(int i = 0;i<fieldData.length;i++)
		{
			for(int j = 0; j<fieldData[i].length;j++)
			{
				
				switch(fieldData[i][j].fieldFindType)
				{

					case ID: 	elem = browser.findElementById(fieldData[i][j].fieldPath);
											break;
					
					case XPATH: 	elem = browser.findElementByXPath(fieldData[i][j].fieldPath);
												break;
					
					case CSSSELECTOR:	elem = browser.findElementByCssSelector(fieldData[i][j].fieldPath);
													break;
					
					case LINKTEXT:	elem = browser.findElementByLinkText(fieldData[i][j].fieldPath);
												break;
					
					case PARTIALLINKTEXT:	elem = browser.findElementByPartialLinkText(fieldData[i][j].fieldPath);
														break;
					
					case TAGNAME: 	elem = browser.findElementByTagName(fieldData[i][j].fieldPath);
												break;
					
					default:	break;

				}

				switch(fieldData[i][j].fieldType)
				{
					case TEXTBOX: elem.sendKeys(fieldData[i][j].fieldValue);
											break;

					case BUTTON: 	elem.click();
											break;	

					case TEXTAREA: elem.sendKeys(fieldData[i][j].fieldValue);
											 break;	

					case DROPDOWN: Select droplist = new Select(elem);
											 droplist.selectByVisibleText(fieldData[i][j].fieldValue);;
											 break;

					case RADIOBUTTON:	elem.click();
												break;

					case CHECK: 	elem.click();
											break;	

					case READONLY:  ((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", elem, fieldData[i][j].fieldValue);
          							break;						

					default: 	break;																																						
				}
				
			}			
		}

		return true;		

	}

}