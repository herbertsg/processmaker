package com.colosa.qa.automatization.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import java.net.URL;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;

public class FormFiller{

	public static void formFillElements(FormFieldData[] fieldData) throws FileNotFoundException, IOException, Exception{
		WebElement elem = null;
		System.out.println("Tamaño del elemento: "+fieldData.length);

		for(int i = 0;i<fieldData.length;i++)
		{
			if(fieldData[i].fieldPath=="")
			{
				break;
			}
			else
			{
				if(fieldData[i].fieldFindType=="id")
				{
					elem = Browser.driver().findElement(By.id(fieldData[i].fieldPath));
				}
				else
				{
					if(fieldData[i].fieldFindType=="xpath")
					{
						elem = Browser.driver().findElement(By.xpath(fieldData[i].fieldPath));
					}
					else
					{
						if(fieldData[i].fieldFindType=="cssSelector")
						{
							elem = Browser.driver().findElement(By.cssSelector(fieldData[i].fieldPath));
						}
						else
						{
							if(fieldData[i].fieldFindType=="linkText")
							{
								elem = Browser.driver().findElement(By.linkText(fieldData[i].fieldPath));
							}
							else
							{
								if(fieldData[i].fieldFindType=="partialLinkText")
								{
									elem = Browser.driver().findElement(By.partialLinkText(fieldData[i].fieldPath));
								}
								else
								{
									if(fieldData[i].fieldFindType=="tagName")
									{
										elem = Browser.driver().findElement(By.tagName(fieldData[i].fieldPath));
									}
									else
									{
										throw new Exception("Invalid search prefix");
									}
								}
							}
						}
					}
				}
			}

			if(fieldData[i].fieldType=="textbox")
			{
				elem.sendKeys(fieldData[i].fieldValue);
			}
			else
			{
				if(fieldData[i].fieldType=="button")
				{
					elem.click();
				}
				else
				{
					if(fieldData[i].fieldType=="textarea")
					{
						elem.sendKeys(fieldData[i].fieldValue);
					}
					else
					{
						if(fieldData[i].fieldType=="dropdown")
						{
							Select droplist = new Select(elem);
							droplist.selectByVisibleText(fieldData[i].fieldValue);
						}
						else
						{
							if(fieldData[i].fieldType=="radiobutton")
							{
								elem.click();
							}
							else
							{
								if(fieldData[i].fieldType=="check")
								{
									elem.click();
								}
								else
								{
									if(fieldData[i].fieldType=="readonly")
									{
										((JavascriptExecutor)Browser.driver()).executeScript("arguments[0].value=arguments[1]", elem, fieldData[i].fieldValue);
										
									}
								}							
							}
						}
					}
				}
			}
		}		

	}

}