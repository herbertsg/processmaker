package com.colosa.qa.automatization.common;

import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Value{

	public static String getValue(BrowserInstance browser,  FieldKeyType keyType, String pathElem) throws FileNotFoundException, IOException, Exception{
		WebElement elem = null;
		switch(keyType)
			{

				case ID: 		elem = browser.findElementById(pathElem);
										break;
				
				case XPATH: 	elem = browser.findElementByXPath(pathElem);
											break;
				
				case CSSSELECTOR:	elem = browser.findElementByCssSelector(pathElem);
												break;
				
				case LINKTEXT:	elem = browser.findElementByLinkText(pathElem);
											break;
				
				case PARTIALLINKTEXT:	elem = browser.findElementByPartialLinkText(pathElem);
													break;
				
				case TAGNAME: 	elem = browser.findElementByTagName(pathElem);
											break;
				
				default:	break;

			}

		return elem.getAttribute("value");	

	}
}
