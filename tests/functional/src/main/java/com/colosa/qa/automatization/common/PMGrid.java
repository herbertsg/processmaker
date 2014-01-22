package com.colosa.qa.automatization.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 1/14/14
 * Time: 10:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class PMGrid {
    WebElement webElementPMGrid;
    WebElement webElementPMGridContainer;
    WebElement webElementPMGridTable;
    WebElement webElementPMGridFirstRow;
    WebElement webElementLinkAddNewRow;
    BrowserInstance browser = null;
    String gridName;
    List<WebElement> auxSearchList;
    WebElement auxWebElement = null;
    WebElement auxWebElement2 = null;
    List<PMGridField> listPMGridFields = null;



    public PMGrid(WebElement pmGrid, BrowserInstance browserInstance) throws Exception {
        this.webElementPMGrid = pmGrid; //div class="grid"
        this.browser = browserInstance;

        //get grid name
        //find table element with tableGrid class
        auxSearchList = this.webElementPMGrid.findElements(By.cssSelector("table.tableGrid[id^='form[']"));
        if(auxSearchList.size() > 0){
            //use the table.tableGrid found
            //usually there are two get the one with name
            webElementPMGridContainer = auxSearchList.get(0);
            String idAttribute = webElementPMGridContainer.getAttribute("id");
            //get grid name
            this.gridName = idAttribute.substring(idAttribute.indexOf('[')+1,idAttribute.lastIndexOf(']'));
            Logger.addLog("Grid Container found Name:"+ gridName+ " Id:" + idAttribute + " class:" + webElementPMGridContainer.getAttribute("class"));

            //get gridtable
            webElementPMGridTable = webElementPMGridContainer.findElement(By.id(gridName));

            /*
            this.auxWebElement = auxSearchList.get(0);
            String attributeId = auxWebElement.getAttribute("id");
            String attributeName = auxWebElement.getAttribute("name");
            String attributeId2 = "";
            String attributeName2 = "";

            if(auxSearchList.size() > 1){
                this.auxWebElement2 = auxSearchList.get(1);
                attributeId2 = auxWebElement2.getAttribute("id");
                attributeName2 = auxWebElement2.getAttribute("name");
            }

            Logger.addLog("Grid attributes: AttributeId1:" + attributeId + " attributeName1:" + attributeName +
                    " AttributeId2:" + attributeId2 + " attributeName2:" + attributeName2);

            if(attributeId.contains("form[") && attributeName2 != null){
                //it's the container table
                webElementPMGridContainer = this.auxWebElement;
                webElementPMGridTable = this.auxWebElement2;
                this.gridName = attributeName2;
            }else if(attributeName != null){
                webElementPMGridContainer = this.auxWebElement2;
                webElementPMGridTable = this.auxWebElement;
                this.gridName = attributeName;
            }*/

            Logger.addLog("Grid Container found. Name:" + this.gridName +
                    " gridContainer:" + webElementPMGridContainer.getAttribute("id") +
                    " gridTable:" + webElementPMGridTable.getAttribute("id"));

            this.webElementPMGridFirstRow = webElementPMGridTable.findElement(By.id("firstRow_" + this.gridName));
            if(this.webElementPMGridFirstRow == null){
                throw new Exception("First row Grid not found in specified grid.");
            }
        }else
        {
            throw new Exception("Grid not found in specified element.");
        }

        //get grid add new row link
        this.webElementLinkAddNewRow = this.webElementPMGrid.findElement(By.id("form["+this.gridName+"][addLink]"));

        //get list of gridfields
        this.listPMGridFields = queryListGridFields();
    }

    private List<PMGridField> queryListGridFields() throws Exception {
        String cssSelector = "[id^='form["+this.gridName+"][1][']";//"[id^='form\\["+this.gridName+"\\]\\[1\\]\\[']";  //[id^='form']
        Logger.addLog("Get current list of PMGridFields selector: " + cssSelector);
        browser.turnOffImplicitWaits();
        List<WebElement> listPMFields = this.webElementPMGridFirstRow.findElements(By.cssSelector(cssSelector)); //form[gridField][1][gridTextField]
        browser.turnOnImplicitWaits();
        Logger.addLog("Get current list of PMGridFields: " + listPMFields.size());


        List<PMGridField> listPMGridFields = new ArrayList<PMGridField>(listPMFields.size());

        for (WebElement pmGridField : listPMFields) {
            //find fieldname
            String attributeId = pmGridField.getAttribute("id");   //form[gridField][1][gridTextField]
            //int beginIndex = 5 + this.gridName.length() + 5;
            //int endIndex = attributeId.length() - 1;
            String gridFieldName = attributeId.substring(attributeId.lastIndexOf('[')+1,attributeId.lastIndexOf(']'));//attributeId.substring(beginIndex, endIndex);

            //exclude suggest label element and datepicker button
            if((attributeId.indexOf("_label")==-1) && (attributeId.indexOf("[btn]")==-1)){
                Logger.addLog("gridField detected: " + gridName + "[1][" + gridFieldName + "]");

                listPMGridFields.add(new PMGridField(this.webElementPMGridTable, this.gridName, gridFieldName, this.browser));

                Logger.addLog("   gridField data: " + gridName + "[" + gridFieldName + "]");
            }
        }

        return listPMGridFields;
    }

    public String getGridName(){
        return gridName;
    }

    public PMGridField getPMGridField(String pmGridFieldName){
        for(PMGridField pmGridField : listPMGridFields){
            if(pmGridField.getGridFieldName().equals(pmGridFieldName)){
                return pmGridField;
            }
        }
        return null;
    }

    public void setGridFieldValue(String gridFieldName, int row, String value) throws Exception {
        getPMGridField(gridFieldName).setPMGridFieldValue(row, value);
    }

    public void addNewRow(){
        //id__&&__form[REPLACE_GRIDNAME][addLink]

        this.webElementLinkAddNewRow.click();
    }
}
