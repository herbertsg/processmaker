package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.Logger;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 5/21/13
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssignTask extends Page {

    public AssignTask(BrowserInstance browser) throws Exception {
        super(browser);

        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception{
        //
        //wait for page
        browser.switchToDefaultContent();
        //browser.waitForElement(By.id("casesFrame"),120);
        browser.switchToFrame("casesFrame");
        //Logger.addLog("goto subcaseFrame ...");
        browser.switchToFrame("casesSubFrame");
        //browser.waitForElement(By.id("casesSubFrame"), 10);
        browser.switchToFrame("openCaseFrame");

        Logger.addLog("wait for casesgrid ...");
        //browser.waitForElement(By.id("processesFilter"), 10);

        WebElement frmDerivation = browser.findElementById("frmDerivation");

        String assignTaskText = browser.findElementByCssSelector("td.FormTitle").getText();

        if(!assignTaskText.equals("Assign Task")){
            throw new  Exception("Not valid page, we are not in Assign Task page");
        }
    }

    public void pressContinueButton() throws Exception{
        WebElement nextBtn = browser.findElementById("btnContinue");

        if(nextBtn==null)
            throw new Exception("The element is not found");
        else{
            nextBtn.click();
        }
    }
}
