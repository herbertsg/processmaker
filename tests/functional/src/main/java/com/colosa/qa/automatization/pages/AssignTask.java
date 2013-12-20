package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 5/21/13
 * Time: 2:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssignTask extends Page {
    WebElement frmDerivation;

    public AssignTask(BrowserInstance browser) throws Exception {
        super(browser);

        verifyPage();

        this.frmDerivation = browser.findElementById("frmDerivation");
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

    public void selectManualAssignedUser(Integer taskNumber, String value) throws Exception{
        //find user element
        //List<WebElement> listTaskLabels = browser.findElementsByClassName("FormLabel");
        List<WebElement> listTaskContent = this.frmDerivation.findElements(By.cssSelector("td.FormFieldContent"));

        Logger.addLog("List of tasks content:" + listTaskContent.size());

        //get the specified task content
        WebElement tdTaskSelect = listTaskContent.get((taskNumber*2) - 1);
        if(tdTaskSelect == null){
            throw new Exception("Specified manual task selection not found.");
        }

        WebElement taskSelect = tdTaskSelect.findElement(By.tagName("select"));
        if(taskSelect == null){
            throw new Exception("Specified manual task selection not found 2.");
        }

        Select selectTask = new Select(taskSelect);

        Logger.addLog("Select task:" + selectTask.toString());

        selectTask.selectByVisibleText(value);

    }
}
