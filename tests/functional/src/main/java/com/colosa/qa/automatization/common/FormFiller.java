package com.colosa.qa.automatization.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class FormFiller{

	public static boolean formFillElements(BrowserInstance browser,  FormFieldData[] fieldData) throws FileNotFoundException, IOException, Exception{
		WebElement elem = null;
		System.out.println("Tamaño del elemento: "+fieldData.length);

		for(int i = 0;i<fieldData.length;i++)
		{
			switch(fieldData[i].fieldFindType)
			{

				case ID: 		elem = browser.findElementById(fieldData[i].fieldPath);
										break;
				
				case XPATH: 	elem = browser.findElementByXPath(fieldData[i].fieldPath);
											break;
				
				case CSSSELECTOR:	elem =  browser.findElementByCssSelector(fieldData[i].fieldPath);
												break;
				
				case LINKTEXT:	elem = browser.findElementByLinkText(fieldData[i].fieldPath);
											break;
				
				case PARTIALLINKTEXT:	elem = browser.findElementByPartialLinkText(fieldData[i].fieldPath);
													break;
				
				case TAGNAME: 	elem = browser.findElementByTagName(fieldData[i].fieldPath);
											break;
				
				default:	break;

			}

			switch(fieldData[i].fieldType)
			{
				case TEXTBOX: elem.sendKeys(fieldData[i].fieldValue);
										break;

				case BUTTON: 	elem.click();
										break;	

				case TEXTAREA: elem.sendKeys(fieldData[i].fieldValue);
										 break;	

				case DROPDOWN: Select droplist = new Select(elem);
										 droplist.selectByVisibleText(fieldData[i].fieldValue);
										 break;

				case RADIOBUTTON:	elem.click();
											break;

				case CHECK: 	elem.click();
										break;	
										
				case READONLY:  ((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", elem, fieldData[i].fieldValue);
          						break;

          		case SUGGEST: 	String cadIns = fieldData[i].fieldValue;
          						char c;
          						WebElement elem2 = null;
          						WebElement sugElem = null;
          						List<WebElement> listEl;          			
          						for(int lon=0;lon<cadIns.length();lon++)	
          						{
          							c = cadIns.charAt(0);
      								elem.sendKeys(Character.toString(c));
      								browser.waitForElement(By.xpath("//div[1]/ul/li"),5);
  									elem2 = browser.findElementByXPath("//div[1]/ul/li");
      								listEl = elem2.findElements(By.xpath("a"));
      								for(WebElement we2:listEl)
      								{
      									if(we2.findElement(By.xpath("span[3]")).getText().equals(fieldData[i].fieldValue))
      									{
      										sugElem = we2;
      										we2.click();
      										break;
      									}      									
      								}

	      							if(sugElem!=null)
	      							{
	      								break;
	      							}
          							cadIns = cadIns.substring(1, cadIns.length());

          						}
          						Thread.sleep(1000);
          						break;		
         
				default: 	break;																																						
			}
			
		}

		return true;		

	}

}