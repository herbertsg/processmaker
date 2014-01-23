package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.Logger;
import com.colosa.qa.automatization.common.extJs.ExtJSFloatingMenu;
import com.colosa.qa.automatization.common.extJs.ExtJSToolbar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 1/23/14
 * Time: 2:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class EndOfProcess extends Page  {
    List<WebElement> auxListElements = null;
    WebElement casesSubFrameNavPanel = null;
    WebElement frmDerivation = null;
    ExtJSToolbar endOfProcessToolbar = null;

    public EndOfProcess(BrowserInstance browser) throws Exception {
        super(browser);

        verifyPage();

        this.frmDerivation = browser.findElementById("frmDerivation");

        //detect toolbar
        intoCasesSubFrame();
        //toolbar
        this.endOfProcessToolbar = new ExtJSToolbar(casesSubFrameNavPanel, browser);

        //goto opencase frame
        intoOpenCaseFrame();
    }

    @Override
    public void verifyPage() throws Exception {


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
            if(!auxListElements.get(0).getText().equals("End of process")){
                throw new  Exception("Not valid page, we are not in End of process page");
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

    public void pressFinishButton() throws Exception{
        intoOpenCaseFrame();

        WebElement nextBtn = frmDerivation.findElement(By.id("btnContinue"));

        if(nextBtn==null)
            throw new Exception("pressFinishButton: Finish button not found");
        else{
            nextBtn.click();
        }
    }

    public PauseCase pauseCase() throws Exception {
        intoCasesSubFrame();

        //click toolbar actions/pause
        endOfProcessToolbar.findToolbarCell(2).clickButton();


        //find float menu
        ExtJSFloatingMenu actionsMenu = new ExtJSFloatingMenu(browser);
        //wait for case notes window to appear
        if(actionsMenu == null){
            throw new Exception("Error opening Actions Float Menu.");
        }

        Logger.addLog("Actions Float Menu open");

        actionsMenu.findMenuItem("Pause").click();

        //detect pause window
        PauseCase pauseWindow = new PauseCase(browser);

        return pauseWindow;
    }
}
