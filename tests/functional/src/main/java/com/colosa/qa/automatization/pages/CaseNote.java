package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.Logger;
import com.colosa.qa.automatization.common.extJs.ExtJSToolbar;
import com.colosa.qa.automatization.common.extJs.ExtJSToolbarCell;
import com.colosa.qa.automatization.common.extJs.ExtJSWindowToolbar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 10/4/13
 * Time: 9:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class CaseNote extends Page {
    private WebElement windowCaseNotes;
    private WebElement windowToolbar;
    private ExtJSWindowToolbar extJSWindowToolbar;
    private WebElement caseNotesPanel;
    private WebElement caseNotesStatusPanel;

    public CaseNote(BrowserInstance browser) throws Exception {
        super(browser);

        Logger.addLog("New case note page class");
        this.verifyPage();

        Logger.addLog("search window cases notes by id caseNotesWindowPanel");

        this.windowCaseNotes = browser.findElementById("caseNotesWindowPanel");
        Logger.addLog("window cases notes found caseNotesWindowPanel");
        this.windowToolbar = this.windowCaseNotes.findElement(By.className("x-window-tbar"));
        Logger.addLog("xwindow tbar found x-window-tbar");

        this.extJSWindowToolbar = new ExtJSWindowToolbar(this.windowToolbar, browser);

        this.caseNotesPanel = this.windowCaseNotes.findElement(By.id("notesPanel"));
        this.caseNotesStatusPanel = this.windowCaseNotes.findElement(By.id("notesStatusPanel"));
    }

    @Override
    public void verifyPage() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        browser.switchToDefaultContent();
        browser.switchToFrame("casesFrame");
        browser.switchToFrame("casesSubFrame");
        //browser.switchToFrame("openCaseFrame");

        Logger.addLog("wait for cases notes window panel");
        browser.waitForElement(By.id("caseNotesWindowPanel"), 20);
    }

    public void addCaseNote(String caseNote) throws Exception{

        Logger.addLog("AddCaseNote");

        //browser.waitForElement(By.id("addCancelBtn"), 15);

        //Logger.addLog("Toolbar find addButton");
        //WebElement addButton = browser.findElementById("addCancelBtn");

        //search navPanel
        //if(addButton == null){
        //    Logger.addLog("addButton not found");
        //}
        //addButton.click();

        //click new case note button
        this.extJSWindowToolbar.findToolbarCell(6).clickButton();

        /*WebElement caseNoteTextArea = browser.findElementById("caseNoteText");

        if(caseNoteTextArea == null){
            Logger.addLog("caseNoteTextArea not found");
        }

        caseNoteTextArea.sendKeys(caseNote);*/

        browser.waitForElement(By.id("caseNoteText"), 10);

        //write message in text area
        //this.extJSWindowToolbar.findToolbarCell(0).setCellText(caseNote);
        WebElement caseNoteTextArea = this.windowCaseNotes.findElement(By.id("caseNoteText"));
        if(caseNoteTextArea == null){
            Logger.addLog("caseNote TextArea not found");
            throw new Exception("Case Note Text Area not found");
        }
        caseNoteTextArea.sendKeys(caseNote);
        Logger.addLog("Note set");

        //get the number of case notes:
        //Integer numCaseNotes = getCaseNotesCount();
        //post note
        this.extJSWindowToolbar.findToolbarCell(4).clickButton();
        //
        /*
        WebElement postCaseNote = this.windowCaseNotes.findElement(By.id("sendBtn"));
        if(postCaseNote == null){
            Logger.addLog("caseNote TextArea not found");
            throw new Exception("Case Note post button not found");
        }
        postCaseNote.findElement(By.cssSelector("button.x-btn-text")).click();
        */
        Logger.addLog("Press post button");
        //wait for note to appear
        //WebElement notesStatus =  this.caseNotesStatusPanel.findElement(By.className("x-status-text"));
        //browser.waitForElement(By.cssSelector(":nth-child("+(numCaseNotes+1)+")"), 20);
        browser.waitForTextToBePresent(By.cssSelector("#notesStatusPanel .x-status-text"), "Note successfully posted", 20);

        Logger.addLog("Note inserted");
    }

    /**
     * Get the number of case notes
     * @return
     */
    public int getCaseNotesCount(){
        return this.caseNotesPanel.findElements(By.cssSelector(".x-cnotes-source")).size();
    }

    public void closeWindow(){
        this.windowCaseNotes.findElement(By.cssSelector(".x-tool-close")).click();
    }

}
