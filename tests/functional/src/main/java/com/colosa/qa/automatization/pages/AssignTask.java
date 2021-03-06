package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.Logger;
import com.colosa.qa.automatization.common.extJs.ExtJSToolbar;
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
    List<WebElement> auxListElements = null;
    ExtJSToolbar assignTaskToolbar = null;
    WebElement casesSubFrameNavPanel = null;

    public AssignTask(BrowserInstance browser) throws Exception {
        super(browser);

        verifyPage();

        this.frmDerivation = browser.findElementById("frmDerivation");

        //detect toolbar
        intoCasesSubFrame();
        //toolbar
        this.assignTaskToolbar = new ExtJSToolbar(casesSubFrameNavPanel, browser);

        //goto opencase frame
        intoOpenCaseFrame();
    }

    @Override
    public void verifyPage() throws Exception{
        //
        //wait for page
        intoCasesSubFrame();
        //browser.turnOffImplicitWaits();
        casesSubFrameNavPanel = browser.findElementById("navPanel");
        //browser.turnOnImplicitWaits();
        //search navPanel
        if(casesSubFrameNavPanel == null){
            throw new  Exception("Nav Panel not found");
        }

        //browser.waitForElement(By.id("casesSubFrame"), 10);
        browser.switchToFrame("openCaseFrame");

        Logger.addLog("wait for casesgrid ...");
        //browser.waitForElement(By.id("processesFilter"), 10);

        WebElement frmDerivation = browser.getInstanceDriver().findElement(By.id("frmDerivation"));

        auxListElements = frmDerivation.findElements(By.cssSelector("td.FormTitle"));
        if(auxListElements.size() > 0){
            if(!auxListElements.get(0).getText().equals("Assign Task")){
                throw new  Exception("Not valid page, we are not in Assign Task page");
            }
        }
    }

    public void intoCasesFrame() throws Exception {
        browser.switchToDefaultContent();
        browser.switchToFrame("casesFrame");
    }

    // into level of pmTrack
    public void intoCasesSubFrame() throws Exception {
        browser.switchToDefaultContent();
        browser.switchToFrame("casesFrame");
        browser.switchToFrame("casesSubFrame");
    }

    // into level of dynaform
    public void intoOpenCaseFrame() throws Exception {
        browser.switchToDefaultContent();
        browser.switchToFrame("casesFrame");
        browser.switchToFrame("casesSubFrame");
        browser.switchToFrame("openCaseFrame");
    }

    public void pressContinueButton() throws Exception{
        intoOpenCaseFrame();

        WebElement nextBtn = this.frmDerivation.findElement(By.id("btnContinue"));

        if(nextBtn==null)
            throw new Exception("The element is not found");
        else{
            nextBtn.click();
        }
    }

    public void selectManualAssignedUser(Integer taskNumber, String value) throws Exception{
        intoOpenCaseFrame();

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

    public CaseNote openCasesNotes() throws Exception {
        intoCasesSubFrame();
        //click case note button
        //toolbar.findToolbarCell("  Case Notes").click();
        this.assignTaskToolbar.findToolbarCell(3).clickButton();

        //wait for case notes window to appear
        CaseNote caseNote = new CaseNote(browser);
        if(caseNote == null){
            throw new Exception("Error opening Case Notes window.");
        }

        Logger.addLog("Case Note window open");

        return caseNote;
    }
}
