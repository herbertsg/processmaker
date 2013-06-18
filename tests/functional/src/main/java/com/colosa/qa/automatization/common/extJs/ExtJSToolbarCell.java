package com.colosa.qa.automatization.common.extJs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 6/10/13
 * Time: 1:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExtJSToolbarCell {
    private WebDriver driver;
    private WebElement toolbarCell; //element with class x-toolbar-cell
    private WebElement toolbarCellButton;
    private WebElement toolbarCellText;
    private WebElement toolbarCellSeparator;
    private WebElement toolbarCellWrap;

    /**
     * Initialize with an element that represent a Toolbar Cell, that has the class x-toolbar-cell
     * @param toolbarCell webelement with class x-toolbar-cell
     * @param driver Webdriver
     */
    public ExtJSToolbarCell(WebElement toolbarCell, WebDriver driver) {
        //this.headerToolbar = this.grid.findElement(By.cssSelector("div.x-panel-tbar"));
        //this.headerToolbarCells = this.headerToolbar.findElements(By.cssSelector("td.x-toolbar-cell"));
        this.toolbarCell = toolbarCell;
        this.driver = driver;

        //detect what type of cell is
        this.toolbarCellButton = null;
        //click the element if is a button
        this.toolbarCellButton = this.toolbarCell.findElement(By.className("x-btn"));

        //if is a cell text
        this.toolbarCellText = null;
        this.toolbarCellText = this.toolbarCell.findElement(By.className("xtb-text"));

        //if is a separator
        //xtb-sep
        this.toolbarCellSeparator = null;
        this.toolbarCellSeparator = this.toolbarCell.findElement(By.className("xtb-sep"));

        //if is a wrapper
        this.toolbarCellWrap = null;
        //x-form-field-wrap x-form-field-trigger-wrap
        this.toolbarCellWrap = this.toolbarCell.findElement(By.className("x-form-field-wrap"));
    }

    public void click(){
        //x-btn x-btn-text-icon
        //click the element if is a button
        if(this.toolbarCellButton != null){
            this.toolbarCellButton.findElement(By.cssSelector("button")).click();
        }
        //html.ext-strict body#ext-gen3.ext-gecko div#navPanel.x-panel div#ext-gen16.x-panel-bwrap div#ext-gen17.x-panel-tbar div#ext-comp-1004.x-toolbar table.x-toolbar-ct tbody tr td.x-toolbar-left table tbody tr.x-toolbar-left-row td#ext-gen27.x-toolbar-cell table#caseNotes.x-btn tbody.x-btn-small tr td.x-btn-mc em button#ext-gen28.x-btn-text
    }

    public String getCellText(){
        String cellText = "";
        //if is a button or a text get the text
        // x-btn-text
        if(this.toolbarCellText != null){
            cellText = this.toolbarCellText.getText();
        }

        if(this.toolbarCellButton != null){
            cellText =  this.toolbarCellButton.findElement(By.className("x-btn-text")).getText();
        }

        return cellText;
    }
}
