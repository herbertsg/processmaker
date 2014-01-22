package com.colosa.qa.automatization.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 1/13/14
 * Time: 12:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class PMField {
    BrowserInstance browser = null;
    WebElement webElementPMField = null;
    WebElement webLabelElementPMField = null;
    FieldType pmFieldType = null;
    String fieldName = "";
    List<WebElement> auxFindElements = null;

    public PMField(WebElement pmField, BrowserInstance browserInstance) throws Exception {
        this.webElementPMField =pmField;
        this.browser = browserInstance;

        //get field name
        String idAttribute = this.webElementPMField.getAttribute("id");
        fieldName = idAttribute.substring(idAttribute.indexOf('[')+1,idAttribute.lastIndexOf(']'));

        //detect PMField type
        this.pmFieldType = this.detectPMFieldType(pmField);

        Logger.addLog("PMField:" + fieldName);
    }

    public String getFieldName(){
        return fieldName;
    }

    public WebElement getWebElement(){
        return this.webElementPMField;
    }

    public FieldType detectPMFieldType(WebElement element) throws Exception{
        //intoDynaform();
        FieldType elementFieldType = null;

        Logger.addLog("detectPMFieldType:");

        //try to get field type using tagname
        String fieldTagName = element.getTagName();
        String fieldPMFieldTypeAttribute = element.getAttribute("pm:fieldtype");
        String fieldTypeAttribute = element.getAttribute("type");
        //String readonlyAttribute = element.getAttribute("readonly");

        Logger.addLog(" element tagName:" + fieldTagName + " pm:type : " + fieldPMFieldTypeAttribute + " typeAttribute:" + fieldTypeAttribute );//+ " readOnlyAttribute:" + readonlyAttribute

        if(fieldPMFieldTypeAttribute != null){
            switch (fieldPMFieldTypeAttribute){
                case "text":
                    Logger.addLog("detectPMFieldType:TEXTBOX ");
                    return FieldType.TEXTBOX;
                case "currency":
                    Logger.addLog("detectPMFieldType:CURRENCY ");
                    return FieldType.CURRENCY;
                case "percentage":
                    Logger.addLog("detectPMFieldType:PERCENTAGE");
                    return FieldType.PERCENTAGE;
                case "suggest": //not detected because the original field is not found the _label field is used
                    Logger.addLog("detectPMFieldType:SUGGEST");
                    return FieldType.SUGGEST;
                case "dropdown":
                    Logger.addLog("detectPMFieldType:DROPDOWN");
                    return FieldType.DROPDOWN;
                case "yesno":
                    Logger.addLog("detectPMFieldType:YESNO");
                    return FieldType.YESNO;
                case "listbox":
                    Logger.addLog("detectPMFieldType:LISTBOX");
                    return FieldType.LISTBOX;
                //case "checkbox":
                //    return FieldType.CHECK;
                default: // valid grid fields are: date, hidden, link, file
                    break;
            }
        }

        if(fieldTagName.equals("input")){
            Logger.addLog(" HTML tag: input");
            Logger.addLog(" HTML type: " + fieldTypeAttribute);
            if(fieldTypeAttribute.equals("button")){
                Logger.addLog("detectPMFieldType:BUTTON");
                return FieldType.BUTTON;
            }
            if(fieldTypeAttribute.equals("submit")){
                Logger.addLog("detectPMFieldType:SUBMITBUTTON");
                return FieldType.SUBMITBUTTON;
            }
            if(fieldTypeAttribute.equals("reset")){
                Logger.addLog("detectPMFieldType:RESETBUTTON");
                return FieldType.RESETBUTTON;
            }
            if(fieldTypeAttribute.equals("hidden")){
                //hidden field

                //check if it's a suggest field
                browser.turnOffImplicitWaits();
                auxFindElements = this.browser.getInstanceDriver().findElements(By.id("form["+fieldName+"_label]"));
                browser.turnOnImplicitWaits();
                if(auxFindElements.size()>0){
                    //then it's a suggest field
                    webLabelElementPMField = auxFindElements.get(0);
                    Logger.addLog("detectPMFieldType:SUGGEST");
                    return FieldType.SUGGEST;
                }

                Logger.addLog("detectPMFieldType:HIDDEN");
                //it's a hidden field
                return FieldType.HIDDEN;
            }
            if(fieldTypeAttribute.equals("text")){ // && readonlyAttribute!=null && readonlyAttribute.equals("true")
                String pmTime = element.getAttribute("pm:time");
                String pmStart = element.getAttribute("pm:start");
                String pmEnd = element.getAttribute("pm:end");
                if(pmTime != null && pmStart!=null && pmEnd!=null){
                    Logger.addLog("detectPMFieldType:DATEPICKER");
                    return FieldType.DATEPICKER;
                }
            }
            if(fieldTypeAttribute.equals("file")){
                Logger.addLog("detectPMFieldType: FILE");
                return FieldType.FILE;
            }
            if(fieldTypeAttribute.equals("checkbox")){
                Logger.addLog("detectPMFieldType: CHECK");
                return FieldType.CHECK;
            }
            if(fieldTypeAttribute.equals("radio")){
                Logger.addLog("detectPMFieldType: RADIOBUTTON");
                return FieldType.RADIOBUTTON;
            }
            if(fieldTypeAttribute.equals("password")){
                Logger.addLog("detectPMFieldType: PASSWORD");
                return FieldType.PASSWORD;
            }
        }else if(fieldTagName.equals("a")){
            Logger.addLog(" detectPMFieldType: LINK");
            return FieldType.LINK;
        }else if(fieldTagName.equals("textarea")){
            Logger.addLog("detectPMFieldType: TEXTAREA");
            return FieldType.TEXTAREA;
        }else if(fieldTagName.equals("span")){
            Logger.addLog("detectPMFieldType: TITLE");
            return FieldType.TITLE; //subtitle imposible to detect
        }

        //if the field type was note detected
        throw new Exception("PMField type not valid: form[" + fieldName +"]");

    }

    public FieldType getFieldType(){
        return pmFieldType;
    }

    public void setValue(String value) throws Exception{
        Logger.addLog("setFieldValue field: " + fieldName + " value:" + value + " type:" + pmFieldType.toString());

        switch(this.pmFieldType)
        {
            case LINK:
                this.webElementPMField.click();
                break;
            case FILE:
                //this.clear(element);
                this.webElementPMField.sendKeys(value);
                break;
            case CURRENCY:
            case TEXTBOX:
            case PASSWORD:
            case PERCENTAGE:
                this.webElementPMField.clear();
                this.webElementPMField.sendKeys(value);
                break;
            case BUTTON:
            case SUBMITBUTTON:
            case RESETBUTTON:
                this.webElementPMField.click();
                break;
            case TEXTAREA:
                this.webElementPMField.clear();
                this.webElementPMField.sendKeys(value);
                break;
            case DROPDOWN:
            case LISTBOX:
            case YESNO:
                Select selectList = new Select(this.webElementPMField);
                selectList.selectByVisibleText(value);
                break;
            case RADIOBUTTON:
                this.webElementPMField.click();
                break;
            case CHECK:
                this.webElementPMField.click();
                break;
            case DATEPICKER:
                ((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", this.webElementPMField, value);
                break;
            case READONLY:
                ((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", this.webElementPMField, value);
                break;
            case SUGGEST:   //using label textbox
                WebElement elem2 = null;
                List<WebElement> listEl;
                //WebElement sugElem = null;
                //get the label element

                //get label element
                //WebElement labelElement = this.browser.getInstanceDriver().findElement(By.id("form["+fieldName+"_label]"));
                //this.getField(elementId + "_label");
                Logger.addLog("Suggest label found..");
                this.webLabelElementPMField.clear();

                this.webLabelElementPMField.sendKeys(value);
                Logger.addLog("Suggest label send keys..");
                //id: as_form[suggest_country_label]
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

    public String getValue() throws Exception{
        Logger.addLog("getFieldValue field: " + fieldName + " type:" + pmFieldType.toString());
        String elementValue = "";

        switch(this.pmFieldType)
        {
            case BUTTON:
            case SUBMITBUTTON:
            case RESETBUTTON:
            case TITLE:
            case SUBTITLE:
            case TEXTBOX:
            case PASSWORD:
            case CURRENCY:
            case PERCENTAGE:
                elementValue = this.webElementPMField.getAttribute("value");
                break;
            case TEXTAREA:
                elementValue = this.webElementPMField.getAttribute("value");
                break;
            case DROPDOWN:
            case LISTBOX:
            case YESNO:
                Select selectList = new Select(this.webElementPMField);
                elementValue = selectList.getFirstSelectedOption().getAttribute("value");
                break;
            case DATEPICKER:
                elementValue = this.webElementPMField.getAttribute("value");
                break;
            case SUGGEST:   //get value attribute of field
                elementValue = this.webElementPMField.getAttribute("value");
                break;
            case HIDDEN: // get value
                elementValue = this.webElementPMField.getAttribute("value");
                break;
            case CHECK:
            case RADIOBUTTON:
                elementValue = new Boolean(this.webElementPMField.isSelected()).toString();
                break;
            default:
                break;
        }

        Logger.addLog(" field value:" + elementValue);

        return elementValue;
    }

    public void click(){
        switch(this.pmFieldType)
        {
            case LINK:
            case FILE:
            case BUTTON:
            case SUBMITBUTTON:
            case RESETBUTTON:
            case RADIOBUTTON:
            case CHECK:
                this.webElementPMField.click();
                break;
            default:    break;
        }
    }

    public void clear(){
        //has no effect in no input web elements
        this.webElementPMField.clear();
    }

    public void sendTab(){
        this.webElementPMField.sendKeys(Keys.TAB);
    }

    public String getAttribute(String attribute){
        return this.webElementPMField.getAttribute(attribute);
    }

    public int getOptionsCount(){
        int elementCount = 1;
        switch(this.pmFieldType)
        {
            case TEXTBOX:
            case TEXTAREA:
            case DATEPICKER:
            case SUGGEST:
            case HIDDEN:
                elementCount = 1;
                break;
            case DROPDOWN:
            case LISTBOX:
                Select selectList = new Select(this.webElementPMField);
                elementCount = selectList.getOptions().size();
                break;
            default:
                elementCount = 1;
                break;
        }

        return elementCount;
    }

    public String getText(){
        Logger.addLog("getFieldText field: " + fieldName + " type:" + pmFieldType.toString());
        String elementText = "";

        switch(this.pmFieldType)
        {
            case BUTTON:
            case SUBMITBUTTON:
            case RESETBUTTON:
            case TITLE:
            case SUBTITLE:
            case TEXTBOX:
            case PASSWORD:
            case CURRENCY:
            case PERCENTAGE:
                elementText = this.webElementPMField.getText();
                break;
            case TEXTAREA:
                elementText = this.webElementPMField.getText();
                break;
            case DROPDOWN:
            case LISTBOX:
            case YESNO:
                //get the first selected element, if not selected return empty
                Select selectList = new Select(this.webElementPMField);
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
            case DATEPICKER:
                elementText = this.webElementPMField.getText();
                break;
            case SUGGEST:   //get text of label attribute of field ????

                if(this.webLabelElementPMField != null){
                    elementText = this.webLabelElementPMField.getAttribute("value");
                }
                break;
            case HIDDEN: // get value
                elementText = this.webElementPMField.getText();
                break;
            case CHECK: //?????
                elementText = new Boolean(this.webElementPMField.isSelected()).toString();
                break;
            default:
                break;
        }

        Logger.addLog(" field text: " + elementText);

        return elementText;
    }

    /*
    public FieldType detectFieldType(WebElement element) throws Exception{
        //intoDynaform();
        FieldType elementFieldType = null;

        Logger.addLog("detectFieldType:");

        //try to get field type using tagname
        String elementTagName = element.getTagName();
        Logger.addLog(" element tagName:" + elementTagName);

        switch(elementTagName){
            case "select":
                String multipleAttribute = element.getAttribute("multiple");
                if(multipleAttribute != null && multipleAttribute.equals("multiple")){ //listbox
                    Logger.addLog(" Element Type: ListBox");
                    elementFieldType = FieldType.LISTBOX;
                }
                else { //Dropdown, yesno (no way to differentiate)
                    Logger.addLog(" Element Type: DropDown");
                    elementFieldType = FieldType.DROPDOWN;
                }
                break;
            case "input": //text (type=text)=>pm.textField, pm.currencyField, pm.percentageField
                // suggest (type=hidden),
                Logger.addLog("Ingresa al case");
                Logger.addLog(" HTML tag: input");
                String typeAttribute = element.getAttribute("type");
                Logger.addLog(" HTML type: " + typeAttribute);
                if(typeAttribute.equals("hidden")){
                    //this can be a suggest field, find previous simbling
                    //if suggest a label element is present
                    String idElementAttribute = element.getAttribute("id");
                    Logger.addLog(" HTML id: " + idElementAttribute);
                    //get sub_string
                    String elementId;
                    elementId = idElementAttribute.substring(idElementAttribute.indexOf('[')+1,idElementAttribute.lastIndexOf(']'));
                    Logger.addLog(" HTML element id: " + elementId);
                    Boolean suggestElementExists = browser.elementExistsSearchCriteria("id"+ Constant.SEARCH_CRITERIA_SEPARATOR +"form[" + elementId + "_label]");

                    if(suggestElementExists){
                        Logger.addLog(" Element Type: SUGGEST");
                        elementFieldType = FieldType.SUGGEST;
                    }
                    else {
                        //else return hidden field
                        Logger.addLog(" Element Type: HIDDEN");
                        elementFieldType = FieldType.HIDDEN;
                    }
                }
                if(typeAttribute.equals("text")){ // textbox, currency, percentage,
                    //datepicker??????
                    String readonlyAttribute = element.getAttribute("readonly");
                    Logger.addLog(" HTML readonly Attribute: " + readonlyAttribute);
                    if(readonlyAttribute != null && readonlyAttribute.equals("true"))
                    {
                        Logger.addLog(" Element Type: DATEPICKER");
                        elementFieldType = FieldType.DATEPICKER;
                    }else{
                        //text field
                        Logger.addLog(" Element Type: TEXTBOX");
                        elementFieldType = FieldType.TEXTBOX;
                    }
                }
                if(typeAttribute.equals("password")){ //password
                    Logger.addLog(" Element Type: TEXTBOX");
                    elementFieldType = FieldType.TEXTBOX;
                }
                if(typeAttribute.equals("radio")){
                    Logger.addLog(" Element Type: RADIOBUTTON");
                    elementFieldType = FieldType.RADIOBUTTON;
                }
                if(typeAttribute.equals("checkbox")){
                    Logger.addLog(" Element Type: CHECK");
                    elementFieldType = FieldType.CHECK;
                }
                if(typeAttribute.equals("button") || typeAttribute.equals("submit") || typeAttribute.equals("reset")){
                    Logger.addLog(" Element Type: BUTTON");
                    elementFieldType = FieldType.BUTTON;
                }
                if(typeAttribute.equals("file")){
                    Logger.addLog(" Element Type: FILE");
                    elementFieldType = FieldType.FILE;
                }
                if(typeAttribute.equals("")){ //datepicker ???
                    //Logger.addLog("Element Type: CHECK");
                    String readonlyAttribute = element.getAttribute("readonly");
                    if(readonlyAttribute.equals("readonly"))
                    {
                        elementFieldType = FieldType.DATEPICKER;
                    }
                }
                break;
            case "textarea":
                Logger.addLog(" Element Type: TEXTAREA");
                elementFieldType = FieldType.TEXTAREA;
                break;
            case "span": //title, subtitle
                Logger.addLog(" Element Type: TITLE");
                elementFieldType = FieldType.TITLE;
                break;
            case "a": //link
                Logger.addLog(" Element Type: LINK");
                elementFieldType = FieldType.LINK;
                break;
            default:
                elementFieldType = null;
                break;
        }
        return elementFieldType;
    }*/



}
