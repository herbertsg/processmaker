package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.Logger;
import com.colosa.qa.automatization.common.WaitTool;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;
import com.colosa.qa.automatization.common.extJs.ExtJSGridRow;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 5/20/13
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class CasesListAction {
    BrowserInstance browser = null;
    WebElement grid  = null;

    /**
     * Create a CasesListAction class
     * @param browser The browser instance
     * @param grid The wel element that points to the grid
     */
    public CasesListAction(BrowserInstance browser, WebElement grid){
        this.browser = browser;
        this.grid = grid;

        //wait for list of cases to update
        WaitTool.waitForJavaScriptCondition(this.browser.getInstanceDriver(), "return (document.readyState == 'complete');", 15);
    }

    public void openCase(Integer numCase, Integer searchFieldNumber)throws Exception{

        Logger.addLog("CasesListAction opencase:" + numCase.toString() + " field number:" + searchFieldNumber.toString());

        ExtJSGrid grid;
        Actions action = new Actions(browser.getInstanceDriver());
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("casesFrame"),120);
        browser.switchToFrame("casesFrame");
        browser.switchToFrame("casesSubFrame");

        grid = new ExtJSGrid(this.grid, browser);
        grid.setSearchFieldGrid(true, searchFieldNumber);
        ExtJSGridRow foundRow = grid.searchAndReturnRow(numCase.toString(), "#");

        action.doubleClick(foundRow.getGridRow());
        action.perform();

    }

    public boolean existCase(Integer numCase, Integer searchFieldNumber)throws Exception{
        ExtJSGrid grid;
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("casesFrame"),120);
        browser.switchToFrame("casesFrame");
        browser.switchToFrame("casesSubFrame");

        grid = new ExtJSGrid(this.grid, browser);
        grid.setSearchFieldGrid(true, searchFieldNumber);
        ExtJSGridRow foundRow = grid.searchAndReturnRow(numCase.toString(), "#");

        if(foundRow==null)
            return false;
        else
            return true;
    }
}
