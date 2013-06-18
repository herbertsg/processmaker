package com.colosa.qa.automatization.common.extJs;

import com.colosa.qa.automatization.common.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 6/10/13
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExtJSToolbar {
    private WebDriver driver;
    private WebElement toolbar;
    private WebElement toolbarContent;
    private List<ExtJSToolbarCell> listExtJSToolbarCells;


    /**
     * Represents a ExtJs Toolbar
     * @param toolbar element with class x-panel-tbar
     * @param driver web driver
     */
    public ExtJSToolbar(WebElement toolbar, WebDriver driver){
        this.driver = driver;
        this.toolbar = toolbar;

        this.toolbar = toolbar.findElement(By.className("x-panel-tbar"));

        this.toolbarContent.findElement(By.className("x-toolbar-ct"));

        this.listExtJSToolbarCells = queryListToolbarCells();

    }

    private List<ExtJSToolbarCell> queryListToolbarCells(){

        List<WebElement> listCells = this.toolbarContent.findElements(By.cssSelector("td.x-toolbar-cell"));
        //Logger.addLog("Get current list of rows total: " + listRows.size());


        List<ExtJSToolbarCell> listToolbarCells = new ArrayList<ExtJSToolbarCell>(listCells.size());

        for (WebElement toolbarCell : listCells) {
            //Logger.addLog("   row data: " + gridRow.getText());

            listToolbarCells.add(new ExtJSToolbarCell(toolbarCell, this.driver));
        }

        return listToolbarCells;
    }

    public List<ExtJSToolbarCell> getListToolbarCells(){
        return queryListToolbarCells();
    }

    public ExtJSToolbarCell findCellButton(String buttonText){
        ExtJSToolbarCell resultToolbarCell = null;

        for (ExtJSToolbarCell extjsToolbarCell : this.listExtJSToolbarCells) {
            if(extjsToolbarCell.getCellText().equals(buttonText)){
                resultToolbarCell = extjsToolbarCell;
                break;
            }
        }

        return resultToolbarCell;
    }
}
