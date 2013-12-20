package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.Logger;
import com.colosa.qa.automatization.common.WaitTool;
import com.colosa.qa.automatization.common.extJs.ExtJSWindow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 12/18/13
 * Time: 11:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class PauseCase extends Page {
    ExtJSWindow pauseCaseWindow = null;
    WebElement pauseButton = null;
    List<WebElement> auxSearchList;
    public PauseCase(BrowserInstance browser) throws Exception {
        super(browser);

        browser.switchToDefaultContent();
        browser.switchToFrame("casesFrame");
        browser.switchToFrame("casesSubFrame");

        this.pauseCaseWindow = new ExtJSWindow(browser);

        this.verifyPage();

        //get button pause button
        auxSearchList = this.pauseCaseWindow.getWindowElement().findElements(By.id("submitPauseCase"));
        if(auxSearchList.size() > 0){
            //use the first x-toolbar found
            this.pauseButton = auxSearchList.get(0).findElement(By.tagName("button"));
            Logger.addLog("pause button found submitPauseCase");
        }else
        {
            throw new Exception("xForm not found in specified element.");
        }

    }

    @Override
    public void verifyPage() throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.

    }

    public void clickPauseButton(){
        this.pauseButton.click();
        //wait for case to pause, the list of cases is display again
        WaitTool.waitForElementPresent(browser.getInstanceDriver(), By.id("casesGrid"), 10);
    }


}
