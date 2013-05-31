package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;
import com.colosa.qa.automatization.common.extJs.ExtJSGridRow;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 5/20/13
 * Time: 8:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class SupervisorReview extends Page {
    public SupervisorReview(BrowserInstance browser) throws Exception {
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
        System.out.println("wait for casesgrid ...");
        //browser.waitForElement(By.id("processesFilter"), 10);

            browser.findElementById("casesGrid");
    }

    public void openCase(Integer numCase)throws Exception{
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("casesFrame"),120);
        browser.switchToFrame("casesFrame");
        browser.switchToFrame("casesSubFrame");

        CasesListAction casesListAction = new CasesListAction(browser, browser.findElementById("casesGrid"));

        casesListAction.openCase(numCase, 8);
    }

    public boolean existCase(Integer numCase)throws Exception{

        browser.switchToDefaultContent();
        browser.switchToFrame("casesFrame");
        browser.switchToFrame("casesSubFrame");

        CasesListAction casesListAction = new CasesListAction(browser, browser.findElementById("casesGrid"));

        return casesListAction.existCase(numCase, 8);
    }

}
