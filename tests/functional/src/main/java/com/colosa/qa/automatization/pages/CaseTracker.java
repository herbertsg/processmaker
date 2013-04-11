package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.ConfigurationSettings;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CaseTracker extends Page{

	WebElement weSectionButton;

    public CaseTracker(BrowserInstance browserInstance) {
        super(browserInstance);
    }

    public void goTo(String workspace) throws FileNotFoundException, IOException, Exception{

        String url = ConfigurationSettings.getInstance().getSetting("server.url");
        String defaultWorkspace = ConfigurationSettings.getInstance().getSetting("login.defaultWorkspace");
        url = url + "/sys" + workspace+ "/en/classic/tracker/login";
		browser.gotoUrl(url);

	}

	public void goWhereCase() throws FileNotFoundException, IOException, Exception{
        browser.switchToDefaultContent();
        this.weSectionButton = browser.findElementById("MAP");
        
        this.weSectionButton.click();
    }

	public void goFormDocs() throws FileNotFoundException, IOException, Exception{
        browser.switchToDefaultContent();
        this.weSectionButton = browser.findElementById("DYNADOC");
        
        this.weSectionButton.click();
    }

	public void goHistory() throws FileNotFoundException, IOException, Exception{
        browser.switchToDefaultContent();
        this.weSectionButton = browser.findElementById("HISTORY");
        
        this.weSectionButton.click();
    }

	public void goMessages() throws FileNotFoundException, IOException, Exception{
        browser.switchToDefaultContent();
        this.weSectionButton = browser.findElementById("MESSAGES");
        
        this.weSectionButton.click();
    }

	public void openFirstForm() throws FileNotFoundException, IOException, Exception{
        browser.switchToDefaultContent();
        this.weSectionButton = browser.findElementByXPath("//*[@id='publisherContent[1]']/table/tbody/tr/td/div/table/tbody/tr/td/table/tbody/tr[2]/td[6]/a");
        
        this.weSectionButton.click();
    }

}