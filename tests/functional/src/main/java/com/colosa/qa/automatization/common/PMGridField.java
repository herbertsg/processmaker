package com.colosa.qa.automatization.common;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 1/14/14
 * Time: 9:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class PMGridField {
    WebElement parentElement;
    WebElement suggestLabelPMGridFirstFieldElement;
    WebElement webElementPMGridFirstField;
    BrowserInstance browser = null;
    String gridName;
    String fieldName;
    //WebElement webElementPMGridField = null;
    FieldType pmFieldType = null;
    List<WebElement> auxSearchList;

    public PMGridField(WebElement parentElement, String gridName, String fieldName, BrowserInstance browserInstance) throws Exception {
        this.parentElement = parentElement;
        this.browser = browserInstance;
        this.gridName = gridName;
        this.fieldName = fieldName;

        //id="form[grd1][1][Campo1]"
        //there's always the first element
        auxSearchList = parentElement.findElements(By.id("form["+gridName+"][1]["+fieldName+"]"));
        //this.pmFieldType = this.detectFieldType(pmField);
        if(auxSearchList.size() > 0){
            //use the first x-toolbar found
            this.webElementPMGridFirstField = auxSearchList.get(0);
            Logger.addLog("GridField found form["+gridName+"][1]["+fieldName+"]");
        }else
        {
            throw new Exception("GridField not found:form["+gridName+"][1]["+fieldName+"]");
        }

        //detect PMGridField type
        //pm:gridtype
        this.pmFieldType = detectGridFieldType(this.webElementPMGridFirstField);
    }

    public FieldType detectGridFieldType(WebElement element) throws Exception{
        //intoDynaform();
        FieldType elementFieldType = null;

        Logger.addLog("detectFieldType:");

        //try to get field type using tagname
        String fieldTagName = element.getTagName();
        String fieldPMGridTypeAttribute = element.getAttribute("pm:gridtype");
        String fieldTypeAttribute = element.getAttribute("type");
        //String readonlyAttribute = element.getAttribute("readonly");

        Logger.addLog(" element tagName:" + fieldTagName + " pm:gridtype : " + fieldPMGridTypeAttribute +
        " Type attribute:" + fieldTypeAttribute ); //+ " readonly Attribute:" + readonlyAttribute

        if(fieldPMGridTypeAttribute != null){
            switch (fieldPMGridTypeAttribute){
                case "text":
                    Logger.addLog("detectPMGridFieldType:TEXTBOX");
                    return FieldType.TEXTBOX;
                case "currency":
                    Logger.addLog("detectPMGridFieldType:CURRENCY");
                    return FieldType.CURRENCY;
                case "percentage":
                    Logger.addLog("detectPMGridFieldType:PERCENTAGE");
                    return FieldType.PERCENTAGE;
                case "suggest":
                    Logger.addLog("detectPMGridFieldType:SUGGEST");
                    return FieldType.SUGGEST; //not detected because field _label is used
                case "textarea":
                    Logger.addLog("detectPMGridFieldType:TEXTAREA");
                    return FieldType.TEXTAREA;
                case "dropdown":
                    Logger.addLog("detectPMGridFieldType:DROPDOWN");
                    return FieldType.DROPDOWN;
                case "yesno":
                    Logger.addLog("detectPMGridFieldType:YESNO");
                    return FieldType.YESNO;
                case "checkbox":
                    Logger.addLog("detectPMGridFieldType:CHECK");
                    return FieldType.CHECK;
                default: // valid grid fields are: date, hidden, link, file
                    break;
            }
        }

        if(fieldTagName.equals("input")){
            Logger.addLog(" HTML tag: input");
            Logger.addLog(" HTML type: " + fieldTypeAttribute);
            if(fieldTypeAttribute.equals("hidden")){
                //check if it's a suggest field
                browser.turnOffImplicitWaits();
                auxSearchList = this.parentElement.findElements(By.id("form[" + gridName + "][1][" + fieldName + "_label]"));   //form[gridField][1][gridSuggestField_label]
                browser.turnOnImplicitWaits();
                if(auxSearchList.size()>0){ //auxSearchList.size()>0
                    //then it's a suggest field
                    suggestLabelPMGridFirstFieldElement = auxSearchList.get(0);
                    Logger.addLog("detectPMFieldType:SUGGEST");
                    return FieldType.SUGGEST;
                }
                Logger.addLog("detectPMGridFieldType:HIDDEN");
                //hidden field
                return FieldType.HIDDEN;
            }
            if(fieldTypeAttribute.equals("text") ){ // && readonlyAttribute!=null && readonlyAttribute.equals("true")
                String pmTime = element.getAttribute("pm:time");
                String pmStart = element.getAttribute("pm:start");
                String pmEnd = element.getAttribute("pm:end");
                if(pmTime != null && pmStart!=null && pmEnd!=null){
                    Logger.addLog("detectPMGridFieldType:DATEPICKER");
                    return FieldType.DATEPICKER;
                }
            }
            if(fieldTypeAttribute.equals("file")){
                Logger.addLog(" detectPMGridFieldType: FILE");
                return FieldType.FILE;
            }
        }else if(fieldTagName.equals("a")){
                Logger.addLog(" detectPMGridFieldType: LINK");
                return FieldType.LINK;
        }

        //if the field type was note detected
        throw new Exception("GridField type not valid :form["+gridName+"][1]["+fieldName+"]");

    }

    public String getGridFieldName(){
        return this.fieldName;
    }

    public void setPMGridFieldValue(int row, String value) throws Exception{
        //Logger.addLog("setFieldValue (WebElement): " + value + " type:" + fieldType.toString());
        WebElement pmGridFieldElement = this.parentElement.findElement(By.id("form["+gridName+"][" + row + "]["+fieldName+"]"));
        switch(this.pmFieldType)
        {
            case LINK:
                pmGridFieldElement.click();
                break;
            case FILE:
                //this.clear(element);
                pmGridFieldElement.sendKeys(value);
                break;
            case TEXTBOX:
            case CURRENCY:
            case PERCENTAGE:
            case TEXTAREA:
                pmGridFieldElement.clear();
                pmGridFieldElement.sendKeys(value);
                break;
            case DROPDOWN:
            case YESNO:
                Select selectList = new Select(pmGridFieldElement);
                selectList.selectByVisibleText(value);
                break;
            case CHECK:
                pmGridFieldElement.click();
                break;
            case DATEPICKER:
            case HIDDEN:
                ((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", pmGridFieldElement, value);
                break;
            case SUGGEST:   //using label textbox
                WebElement elem2 = null;
                List<WebElement> listEl;
                //WebElement sugElem = null;
                //get the label element

                //if suggest a label element is used to select option

                //String idElementAttribute = pmGridFieldElement.getAttribute("id");
                //String elementId = idElementAttribute.substring(idElementAttribute.indexOf('[')+1,idElementAttribute.lastIndexOf(']'));
                //Logger.addLog(" HTML element id: " + elementId);

                //get label element
                WebElement labelElement = this.parentElement.findElement(By.id("form["+gridName+"][" + row + "]["+fieldName+"_label]"));

                labelElement.clear();

                labelElement.sendKeys(value);

                browser.waitForElement(By.xpath("//div[1]/ul/li"),2);
                elem2 = browser.findElementByXPath("//div[1]/ul/li");
                listEl = elem2.findElements(By.xpath("a"));
                for(WebElement we2:listEl)
                {
                    if(we2.findElement(By.xpath("span[3]")).getText().equals(value))
                    {
                        //sugElem = we2;
                        we2.click();
                        break;
                    }
                }
                break;

            default:    break;
        }

        return;
    }

    public String getPMGridFieldValue(int row) throws Exception{
        //intoDynaform();
        Logger.addLog("getGridFieldValue: " + gridName + "[" + row + "][" + fieldName + "]");

        WebElement pmGridFieldElement = this.parentElement.findElement(By.id("form["+gridName+"][" + row + "]["+fieldName+"]"));

        switch(this.pmFieldType)
        {
            case TEXTBOX:
            case CURRENCY:
            case PERCENTAGE:
            case TEXTAREA:
            case DATEPICKER:
            case HIDDEN:
            case SUGGEST:
            case FILE:
            case LINK:
                return pmGridFieldElement.getAttribute("value");
            case DROPDOWN:
            case YESNO:
                Select selectList = new Select(pmGridFieldElement);
                return selectList.getFirstSelectedOption().getAttribute("value");
            case CHECK:
                return new Boolean(pmGridFieldElement.isSelected()).toString();
            default:
                throw new Exception("Invalid GridField type:form["+gridName+"][" + row +"]["+fieldName+"]");
        }
    }

    public String getPMGridFieldText(int row){
        Logger.addLog("getPMGridFieldText field: form[" + gridName + "][" + row + "][" + fieldName + "]" + " type:" + pmFieldType.toString());
        String elementText = "";

        WebElement pmGridFieldElement = this.parentElement.findElement(By.id("form["+gridName+"][" + row + "]["+fieldName+"]"));

        switch(this.pmFieldType)
        {
            case TEXTBOX:
            case CURRENCY:
            case PERCENTAGE:
            case TEXTAREA:
            case DATEPICKER:
            case HIDDEN:
            case FILE:
            case LINK:
                elementText = pmGridFieldElement.getText();
                break;
            case DROPDOWN:
            case YESNO:
                //get the first selected element, if not selected return empty
                Select selectList = new Select(pmGridFieldElement);
                try{
                    WebElement selectedOption = selectList.getFirstSelectedOption();
                    elementText = selectedOption.getText();
                }catch (NoSuchElementException ex){
                    //no selected element
                    elementText = "";
                }

                //WebElement selectedElement = selectList.getFirstSelectedOption(); //if not selected fires exception "NoSuchElementException: No options are selected"
                //if(selectedElement != null){
                //    elementText = selectList.getFirstSelectedOption().getText();

                //}
                //Logger.addLog("first element selected: " + elementText);
                break;
            case SUGGEST:   //get text of label attribute of field ????
                WebElement labelElement = this.parentElement.findElement(By.id("form["+gridName+"][" + row + "]["+fieldName+"_label]"));
                if(labelElement != null){
                    elementText = labelElement.getAttribute("value");
                }
                break;

            case CHECK: //?????
                elementText = new Boolean(pmGridFieldElement.isSelected()).toString();
                break;
            default:
                break;
        }

        Logger.addLog(" getPMGridFieldText text: " + elementText);

        return elementText;
    }
}
