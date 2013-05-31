package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import org.openqa.selenium.WebElement;

public class InputDocProcess extends Page{


    public InputDocProcess(BrowserInstance browser) throws Exception {
        super(browser);
        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void uploadFile(String filePath, String description) throws Exception{

		browser.switchToDefaultContent();
		browser.switchToFrame("casesFrame");
		browser.switchToFrame("casesSubFrame");
		browser.switchToFrame("openCaseFrame");
		browser.findElement("inputDocProcess.webelement.new").click();
	
		browser.findElement("inputDocProcess.webelement.path").sendKeys(filePath);
		browser.findElement("inputDocProcess.webelement.comment").sendKeys(description);
		browser.findElement("inputDocProcess.webelement.save").click();
		browser.findElement("inputDocProcess.webelement.submit").click();
	

	}

	public boolean continuebtn() throws Exception{

		WebElement nextBtn = browser.findElement("inputDocProcess.webelement.continue");

		if(nextBtn==null)
			throw new Exception("The element is not found");
		else{
			nextBtn.click();
			return true;
			}
			
	}


	public void openCaseFrame() throws Exception{

		browser.switchToDefaultContent();
		browser.switchToFrame("casesFrame");
		browser.switchToFrame("casesSubFrame");
		browser.switchToFrame("openCaseFrame");
		
	 }

	public void switchToDefault() throws Exception{

		browser.switchToDefaultContent();
		
	 }

}