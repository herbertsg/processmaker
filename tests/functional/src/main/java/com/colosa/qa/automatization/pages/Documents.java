package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Documents extends Page{

	WebElement dirRoot;
	List<WebElement> dirs;
	WebElement panel;
	List<WebElement> files;

    public Documents(BrowserInstance browser) throws Exception {
        super(browser);

        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {

        //wait for page
        browser.switchToDefaultContent();
        //browser.waitForElement(By.id("casesFrame"),120);
        browser.switchToFrame("casesFrame");
        //System.out.println("goto subcaseFrame ...");
        browser.switchToFrame("casesSubFrame");
        //browser.waitForElement(By.id("casesSubFrame"), 10);
        //browser.switchToFrame("casesSubFrame");
        System.out.println("wait for documents ...");
        //browser.waitForElement(By.id("processesFilter"), 10);

            browser.findElementById("documents");
    }

    public void selectFolder(String folderName) throws FileNotFoundException, IOException, Exception{


		browser.switchToFrame("casesSubFrame");
		this.dirRoot = browser.findElement("documents.WebElement.Root");
        
        this.dirs = this.dirRoot.findElements(By.xpath("div[2]/div/ul/li/ul/li"));

        for(WebElement elementdir:this.dirs)
        if(elementdir.findElement(By.xpath("div/a/span")).getText().equals(folderName))
        {
            elementdir.click();
            break;
        }
        else
        {
        	System.out.println("No se encontró el directorio");
        	break;
        }


	}

	public void downloadDocument(String fileName) throws FileNotFoundException, IOException, Exception{

		ExtJSGrid grid = new ExtJSGrid(browser.findElement("documents.WebElement.Panel"),browser.getInstanceDriver());
		
		this.panel =  grid.getRowByColumnValue("Name", fileName);
		this.panel.click();
		browser.findElement("documents.WebElement.Download").click();
		
	}
}