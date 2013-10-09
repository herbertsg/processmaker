package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.Logger;
import org.openqa.selenium.By;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 10/2/13
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class OpenCase extends Page {
    public OpenCase(BrowserInstance browser) throws Exception {
        super(browser);

        //verify page
        this.verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {
        //Verify if the case is open

        //wait for page
        browser.switchToDefaultContent();
        //browser.waitForElement(By.id("casesFrame"),120);
        browser.switchToFrame("casesFrame");
        //Logger.addLog("goto subcaseFrame ...");
        browser.switchToFrame("casesSubFrame");
        //browser.waitForElement(By.id("casesSubFrame"), 10);
        //browser.switchToFrame("openCaseFrame");
        Logger.addLog("wait for openCaseFrame ...");
        //browser.waitForElement(By.id("processesFilter"), 10);

        browser.waitForElement(By.id("openCaseFrame"), 10);
    }

    public void clickToolBarButton(Integer numCase)throws Exception{
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("casesFrame"),120);
        browser.switchToFrame("casesFrame");
        browser.switchToFrame("casesSubFrame");

        CasesListAction casesListAction = new CasesListAction(browser, browser.findElementById("casesGrid"));

        casesListAction.openCase(numCase, 15);
    }

}
