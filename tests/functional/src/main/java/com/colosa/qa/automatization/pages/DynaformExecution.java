package com.colosa.qa.automatization.pages;

//import java.util.List;
//import java.lang.Boolean;
import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.common.extJs.ExtJSToolbar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DynaformExecution extends Page {

    public DynaformExecution(BrowserInstance browser) throws Exception {
        super(browser);

        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    // pages.Main().goHome();

    // into level of debug
    public void intoMainFrame() throws Exception {
        browser.switchToDefaultContent();
        browser.switchToFrame("frameMain");
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
    public void intoDynaform() throws Exception {
        browser.switchToDefaultContent();
        browser.switchToFrame("casesFrame");
        browser.switchToFrame("casesSubFrame");
        browser.switchToFrame("openCaseFrame");
    }

    // out level of dynaform    
    public void outDynaform() throws Exception {
        browser.switchToDefaultContent();
    }

    public CaseNote openCasesNotes() throws Exception {
        intoCasesSubFrame();


        WebElement ttoolbar = browser.findElementById("navPanel");

        //search navPanel
        if(ttoolbar == null){
            Logger.addLog("Nav Panel not found");
        }
        Logger.addLog("Nav Panel found");

        //toolbar
        ExtJSToolbar toolbar = new  ExtJSToolbar(ttoolbar, browser);

        //toolbar.findToolbarCell("  Case Notes").click();
        toolbar.findToolbarCell(3).click();

        //wait for case notes window to appear
        CaseNote caseNote = new CaseNote(browser);
        if(caseNote == null){
            throw new Exception("Error opening Case Notes window.");
        }

        Logger.addLog("Case Note window open");

        return caseNote;

        //Thread.sleep(2000);
        /*
        Thread.sleep(2000);
        WebElement tableMenus = browser.findElementById("caseNotes");
        WebElement buttonInformation = tableMenus.findElement(By.tagName("button"));
        buttonInformation.click();
          */

        /*List<WebElement> windowCaseNotes = browser.findElements(By.id("caseNotesWindowPanel"));
        
        for(WebElement myWindow:windowCaseNotes)
        {
            return true;
        }*/
        /*
        WebElement windowCaseNote = browser.findElementById("caseNotesWindowPanel");
        if(windowCaseNote != null){
            return true;
        }*/

        //return false;
    }

    public Boolean openInformationDynaforms() throws Exception {
        intoCasesSubFrame();
        Thread.sleep(2000);
        WebElement tableMenus = browser.findElementById("informationMenu");
        WebElement buttonInformation = tableMenus.findElement(By.tagName("button"));
        buttonInformation.click();

        List<WebElement> menusInfomations = browser.findElementsByClassName("x-menu-item-text");
        
        for(WebElement menuInfo:menusInfomations)
        {
            if ( menuInfo.getAttribute("innerHTML").indexOf ("DynaForms") > -1 ) {
                menuInfo.click();
                Thread.sleep(2000);
                break;
            }
        }

        browser.switchToFrame("dynaformHistoryFrame");

        WebElement divDynaforms = browser.findElementByClassName("x-grid3-body");
        String innerDiv = divDynaforms.getAttribute("innerHTML").trim();
        innerDiv = innerDiv.replace("&nbsp;", "");
        if (innerDiv.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean openInformationUploaded() throws Exception {
        intoCasesSubFrame();
        Thread.sleep(2000);
        WebElement tableMenus = browser.findElementById("informationMenu");
        WebElement buttonInformation = tableMenus.findElement(By.tagName("button"));
        buttonInformation.click();

        List<WebElement> menusInfomations = browser.findElementsByClassName("x-menu-item-text");
        
        for(WebElement menuInfo:menusInfomations)
        {
            if ( menuInfo.getAttribute("innerHTML").indexOf ("Uploaded Documents") > -1 ) {
                menuInfo.click();
                Thread.sleep(2000);
                break;
            }
        }

        browser.switchToFrame("uploadedDocumentsFrame");

        WebElement divDynaforms = browser.findElementByClassName("x-grid3-body");
        String innerDiv = divDynaforms.getAttribute("innerHTML").trim();
        innerDiv = innerDiv.replace("&nbsp;", "");
        if (innerDiv.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean openInformationGenerated() throws Exception {
        intoCasesSubFrame();
        Thread.sleep(2000);
        WebElement tableMenus = browser.findElementById("informationMenu");
        WebElement buttonInformation = tableMenus.findElement(By.tagName("button"));
        buttonInformation.click();

        List<WebElement> menusInfomations = browser.findElementsByClassName("x-menu-item-text");
        
        for(WebElement menuInfo:menusInfomations)
        {
            if ( menuInfo.getAttribute("innerHTML").indexOf ("Generated Documents") > -1 ) {
                menuInfo.click();
                Thread.sleep(2000);
                break;
            }
        }

        browser.switchToFrame("generatedDocumentsFrame");

        WebElement divDynaforms = browser.findElementByClassName("x-grid3-body");
        String innerDiv = divDynaforms.getAttribute("innerHTML").trim();
        innerDiv = innerDiv.replace("&nbsp;", "");
        if (innerDiv.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public FieldType detectFieldType(WebElement element) throws Exception{
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
    }

    // get field of dynaform
    public WebElement getField(String fieldName) throws Exception{
        Logger.addLog("getField: " + fieldName);

       // intoDynaform();
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        Logger.addLog(" Element to search for: " + str);

        return browser.findElement(str);
    }

    public WebElement getFieldWithoutForm(String fieldName) throws Exception{
        Logger.addLog("getField: " + fieldName);

       // intoDynaform();
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaformWithoutForm");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        Logger.addLog(" Element to search for: " + str);

        return browser.findElement(str);
    }

    public Boolean activeCaseTitle() throws Exception {
        intoDynaform();
        List<WebElement> trCaseTitle = browser.findElementsByClassName("userGroupTitle");

        for(WebElement trTitle:trCaseTitle)
        {
            return true;
        }
        return false;
    }

    public void setGridFieldValue(String gridName, int row, String fieldName, String value) throws Exception{
        Logger.addLog("setGridFieldValue: " + gridName + "[" + row + "][" + fieldName + "] = " + value);

        String gridFieldName = gridName + "][" + row + "][" + fieldName;
        FieldType fieldType;

        //search element
        WebElement element = this.getField(gridFieldName);

        fieldType = this.detectFieldType(element);

        this.setFieldValue(element, value, fieldType);

        return;
    }    

    public void setCheckBoxGroup(String checkGroup, String checkName) throws Exception{
        Logger.addLog("setCheckbox: " + checkGroup + "[" + checkName + "]");
        String checkFieldName = checkGroup + "][" + checkName;
        FieldType fieldType;

        WebElement element = this.getField(checkFieldName);

        fieldType = this.detectFieldType(element);

        this.setFieldValue(element, "", fieldType);
    }   

    public void setRadioButtonGroup(String radioGroup, String radioName) throws Exception{
        Logger.addLog("setRadioButton: " + radioGroup + "[" + radioName + "]");
        String radioButtonName = radioGroup + "][" + radioName;
        FieldType fieldType;

        WebElement element = this.getField(radioButtonName);

        fieldType = this.detectFieldType(element);

        this.setFieldValue(element, "", fieldType);

    }

    public void clickButton(String buttonName) throws Exception {
        String str = "";
        FieldType fieldType;

        //search element
        WebElement element = this.getField(buttonName);

        Logger.addLog("element : " + element.getAttribute("value"));

        fieldType = this.detectFieldType(element);

        if(fieldType != FieldType.BUTTON){
            throw new Exception("Invalid button field.");
        }

        this.setFieldValue(element, "", fieldType); //empty string is enough to click button

        return;
    }

    public void clickLink(String linkName) throws Exception {
        String str = "";
        FieldType fieldType;

        //search element
        WebElement element = this.getField(linkName);

        Logger.addLog("element : " + element.getAttribute("value"));

        fieldType = this.detectFieldType(element);

        if(fieldType != FieldType.LINK){
            throw new Exception("Invalid button field.");
        }

        this.setFieldValue(element, "", fieldType); //empty string is enough to click button

        return;
    }

    public void setFieldValue(String fieldName, String value) throws Exception{
       // intoDynaform();
        String str = "";
        FieldType fieldType;

        //search element
        WebElement element = this.getField(fieldName);

        Logger.addLog("element : " + element.getAttribute("value"));

        fieldType = this.detectFieldType(element);

        this.setFieldValue(element, value, fieldType);

        return;
    }

    public void setFieldValue(String fieldName, String value, FieldType fieldType) throws Exception{
        Logger.addLog("setFieldValue (String fieldName): ");

        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");

        str = str.replace("replaceNameFieldDynaform", fieldName);

        //search element
        WebElement element = browser.findElement(str);

        this.setFieldValue(element, value, fieldType);

        return;
    }

    public void setFieldValue(WebElement element, String value, FieldType fieldType) throws Exception{
        Logger.addLog("setFieldValue (WebElement): ");

        switch(fieldType)
        {
            case LINK:
                element.click();
                break;
            case FILE:
                //this.clear(element);
                element.sendKeys(value);
                break;
            case TEXTBOX:
                this.clear(element);
                element.sendKeys(value);
                break;
            case BUTTON:    
                element.click();
                break;  
            case TEXTAREA: 
                this.clear(element);
                element.sendKeys(value);
                break; 
            case DROPDOWN:
            case LISTBOX:
                Select selectList = new Select(element);
                selectList.selectByVisibleText(value);
                break;
            case RADIOBUTTON:   
                element.click();
                break;
            case CHECK:     
                element.click();
                break;
            case DATEPICKER:     
                ((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", element, value);
                break;
            case READONLY:  
                ((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", element, value);
                break;
            case SUGGEST:   //using label textbox
                WebElement elem2 = null;
                List<WebElement> listEl;
                //WebElement sugElem = null;
                //get the label element
                
                //if suggest a label element is used to select option
                String idElementAttribute = element.getAttribute("id");
                String elementId = idElementAttribute.substring(idElementAttribute.indexOf('[')+1,idElementAttribute.lastIndexOf(']'));
                Logger.addLog(" HTML element id: " + elementId);

                //get label element 
                WebElement labelElement = this.getField(elementId + "_label");                

                this.clear(labelElement);

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
                /*
                String cadIns = value;
                char c;
                WebElement elem2 = null;
                WebElement sugElem = null;
                List<WebElement> listEl;                    
                for(int lon=0;lon<cadIns.length();lon++)    
                {
                    c = cadIns.charAt(0);
                    element.sendKeys(Character.toString(c));
                    try {
                        browser.waitForElement(By.xpath("//div[1]/ul/li"),2);
                    } catch(Exception ex){
                        //element not found
                        cadIns = cadIns.substring(1, cadIns.length());
                        continue;
                    }
                    elem2 = browser.findElementByXPath("//div[1]/ul/li");
                    listEl = elem2.findElements(By.xpath("a"));
                    for(WebElement we2:listEl)
                    {
                        if(we2.findElement(By.xpath("span[3]")).getText().equals(value))
                        {
                            sugElem = we2;
                            we2.click();
                            break;
                        }                                       
                    }

                    if(sugElem!=null)
                    {
                        break;
                    }
                    cadIns = cadIns.substring(1, cadIns.length());
                }
                //Thread.sleep(1000);
                */
                break;  
            /*    
            case HIDDEN: //??? can't set values
                this.clear(element);
                element.sendKeys(value); */
            default:    break;                                                                                                                                                      
        }

        return;
    }

    public void setFieldValueWithoutForm(String fieldName, String value) throws Exception{
       // intoDynaform();
        String str = "";
        FieldType fieldType;

        //search element
        WebElement element = this.getFieldWithoutForm(fieldName);

        Logger.addLog("element : " + element.getAttribute("value"));

        fieldType = this.detectFieldType(element);

        this.setFieldValueWithoutForm(element, value, fieldType);

        return;
    }

    public void setFieldValueWithoutForm(String fieldName, String value, FieldType fieldType) throws Exception{
        Logger.addLog("setFieldValue (String fieldName): ");

        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaformWithoutForm");

        str = str.replace("replaceNameFieldDynaform", fieldName);

        //search element
        WebElement element = browser.findElement(str);

        this.setFieldValueWithoutForm(element, value, fieldType);

        return;
    }

    public void setFieldValueWithoutForm(WebElement element, String value, FieldType fieldType) throws Exception{
        Logger.addLog("setFieldValue (WebElement): ");

        switch(fieldType)
        {
            case LINK:
                element.click();
                break;
            case FILE:
                //this.clear(element);
                element.sendKeys(value);
                break;
            case TEXTBOX:
                this.clear(element);
                element.sendKeys(value);
                break;
            case BUTTON:    
                element.click();
                break;  
            case TEXTAREA: 
                this.clear(element);
                element.sendKeys(value);
                break; 
            case DROPDOWN:
            case LISTBOX:
                Select selectList = new Select(element);
                selectList.selectByVisibleText(value);
                break;
            case RADIOBUTTON:   
                element.click();
                break;
            case CHECK:     
                element.click();
                break;
            case DATEPICKER:     
                ((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", element, value);
                break;
            case READONLY:  
                ((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", element, value);
                break;
            case SUGGEST:   //using label textbox
                WebElement elem2 = null;
                List<WebElement> listEl;
                //WebElement sugElem = null;
                //get the label element
                
                //if suggest a label element is used to select option
                String idElementAttribute = element.getAttribute("id");
                String elementId = idElementAttribute.substring(idElementAttribute.indexOf('[')+1,idElementAttribute.lastIndexOf(']'));
                Logger.addLog(" HTML element id: " + elementId);

                //get label element 
                WebElement labelElement = this.getField(elementId + "_label");                

                this.clear(labelElement);

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
                /*
                String cadIns = value;
                char c;
                WebElement elem2 = null;
                WebElement sugElem = null;
                List<WebElement> listEl;                    
                for(int lon=0;lon<cadIns.length();lon++)    
                {
                    c = cadIns.charAt(0);
                    element.sendKeys(Character.toString(c));
                    try {
                        browser.waitForElement(By.xpath("//div[1]/ul/li"),2);
                    } catch(Exception ex){
                        //element not found
                        cadIns = cadIns.substring(1, cadIns.length());
                        continue;
                    }
                    elem2 = browser.findElementByXPath("//div[1]/ul/li"));
                    listEl = elem2.findElements(By.xpath("a"));
                    for(WebElement we2:listEl)
                    {
                        if(we2.findElement(By.xpath("span[3]")).getText().equals(value))
                        {
                            sugElem = we2;
                            we2.click();
                            break;
                        }                                       
                    }

                    if(sugElem!=null)
                    {
                        break;
                    }
                    cadIns = cadIns.substring(1, cadIns.length());
                }
                //Thread.sleep(1000);
                */
                break;  
            /*    
            case HIDDEN: //??? can't set values
                this.clear(element);
                element.sendKeys(value); */
            default:    break;                                                                                                                                                      
        }

        return;
    }

    public String getFieldValue(String fieldName) throws Exception{
        intoDynaform();
        Logger.addLog("getFieldValue: " + fieldName);

        FieldType fieldType;
        String elementValue = "";

        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);
        WebElement element = browser.findElement(str);

        fieldType = this.detectFieldType(element);

        switch(fieldType)
        {
            case TEXTBOX:
                elementValue = element.getAttribute("value");
                break; 
            case TEXTAREA:
                elementValue = element.getAttribute("value");
                break; 
            case DROPDOWN:
            case LISTBOX:
                Select selectList = new Select(element);
                elementValue = selectList.getFirstSelectedOption().getAttribute("value");
                break;
            case DATEPICKER:
                elementValue = element.getAttribute("value");
                break;
            case SUGGEST:   //get value attribute of field
                elementValue = element.getAttribute("value");
                break;      
            case HIDDEN: // get value
                elementValue = element.getAttribute("value");
                break;
            case CHECK:
                elementValue = new Boolean(element.isSelected()).toString();
                break;        
            default:    
                break;                                                                                                                                                      
        }
        
        Logger.addLog(" field value:" + elementValue);

        return elementValue;
    }

    public String getFieldValueWithoutForm(String fieldName) throws Exception{
        intoDynaform();
        Logger.addLog("getFieldValue: " + fieldName);

        FieldType fieldType;
        String elementValue = "";

        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaformWithoutForm");
        str = str.replace("replaceNameFieldDynaform", fieldName);
        WebElement element = browser.findElement(str);

        fieldType = this.detectFieldType(element);

        switch(fieldType)
        {
            case TEXTBOX:
                elementValue = element.getAttribute("value");
                break; 
            case TEXTAREA:
                elementValue = element.getAttribute("value");
                break; 
            case DROPDOWN:
            case LISTBOX:
                Select selectList = new Select(element);
                elementValue = selectList.getFirstSelectedOption().getAttribute("value");
                break;
            case DATEPICKER:
                elementValue = element.getAttribute("value");
                break;
            case SUGGEST:   //get value attribute of field
                elementValue = element.getAttribute("value");
                break;      
            case HIDDEN: // get value
                elementValue = element.getAttribute("value");
                break;
            case CHECK:
                elementValue = new Boolean(element.isSelected()).toString();
                break;        
            default:    
                break;                                                                                                                                                      
        }
        
        Logger.addLog(" field value:" + elementValue);

        return elementValue;
    }

    public String getGridFieldValue(String gridName, int row, String fieldName) throws Exception{
        Logger.addLog("getGridFieldValue: " + gridName + "[" + row + "][" + fieldName + "]");

        String elementValue = "";
        String gridFieldName = gridName + "][" + row + "][" + fieldName;
        FieldType fieldType;

        //search element
        WebElement element = this.getField(gridFieldName);

        fieldType = this.detectFieldType(element);

        switch(fieldType)
        {
            case TEXTBOX:
                elementValue = element.getAttribute("value");
                break; 
            case TEXTAREA:
                elementValue = element.getAttribute("value");
                break; 
            case DROPDOWN:
            case LISTBOX:
                Select selectList = new Select(element);
                elementValue = selectList.getFirstSelectedOption().getAttribute("value");
                break;
            case DATEPICKER:
                elementValue = element.getAttribute("value");
                break;
            case SUGGEST:   //get value attribute of field
                elementValue = element.getAttribute("value");
                break;
            case CHECK:
                elementValue = new Boolean(element.isSelected()).toString();
                break; 
            case HIDDEN: // get value
                elementValue = element.getAttribute("value");
                break;            
            default:    
                break;                                                                                                                                                      
        }

        Logger.addLog(" field value:" + elementValue);

        return elementValue;
    }


    public String getDropdownFieldText(String fieldName) throws Exception{
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        WebElement element = browser.findElement(str);
        
        Select droplist = new Select(element);
        //verify if the dropdown is filled
        return droplist.getFirstSelectedOption().getText();
    }

    public String getFieldText(String fieldName) throws Exception{
        Logger.addLog("getFieldText: " + fieldName);

        FieldType fieldType;
        String elementText = "";

        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        WebElement element = browser.findElement(str);
        
        fieldType = this.detectFieldType(element);

        switch(fieldType)
        {
            case TEXTBOX:
                elementText = element.getText();
                break; 
            case TEXTAREA:
                elementText = element.getText();
                break; 
            case DROPDOWN:
            case LISTBOX:
                Select selectList = new Select(element);
                elementText = selectList.getFirstSelectedOption().getText();
                break;
            case DATEPICKER:
                elementText = element.getText();
                break;
            case SUGGEST:   //get text of label attribute of field ????
                //if suggest a label element is used to select option
                String idElementAttribute = element.getAttribute("id");
                String elementId = idElementAttribute.substring(idElementAttribute.indexOf('[')+1,idElementAttribute.lastIndexOf(']'));
                Logger.addLog(" HTML element id: " + elementId);

                //get label element 
                WebElement labelElement = this.getField(elementId + "_label");

                elementText = labelElement.getAttribute("value");
                break;
            case HIDDEN: // get value
                elementText = element.getText();
                break;
            case CHECK: //?????
                elementText = new Boolean(element.isSelected()).toString();
                break; 
            default:    
                break;                                                                                                                                                      
        }

        Logger.addLog(" field text: " + elementText);

        return elementText;        
    }

    public void nextStepLinkWithMessage() throws Exception{
        setFieldValue("DYN_FORWARD", "", FieldType.BUTTON);
        if(browser.findElementByXPath("//div[1]/div[1]/div[6]/div[1]/input[1]").isDisplayed())
        {
            this.btnAceptar();
        }
    }

    public void nextStepLink() throws Exception{
        setFieldValue("DYN_FORWARD", "", FieldType.BUTTON);
    }

    public void previousStepLink() throws Exception{
        setFieldValue("DYN_BACKWARD", "", FieldType.BUTTON);
    }

    public void btnAceptar() throws Exception{
        WebElement elem = browser.findElementByXPath("//div[1]/div[1]/div[6]/div[1]/input[1]");
        elem.click();

    }

    public void btnCancelar() throws Exception{
        WebElement elem = browser.findElementByXPath("//div[1]/div[1]/div[6]/div[1]/input[2]");
        elem.click();
    }

    public String getGridFieldText(String gridName, int row, String fieldName) throws Exception{
        Logger.addLog("getGridFieldValue: " + gridName + "[" + row + "][" + fieldName + "]");

        String elementText = "";
        String gridFieldName = gridName + "][" + row + "][" + fieldName;
        FieldType fieldType;

        //search element
        WebElement element = this.getField(gridFieldName);

        fieldType = this.detectFieldType(element);

        switch(fieldType)
        {
            case TEXTBOX:
                elementText = element.getText();
                break; 
            case TEXTAREA:
                elementText = element.getText();
                break; 
            case DROPDOWN:
            case LISTBOX:
                Select selectList = new Select(element);
                elementText = selectList.getFirstSelectedOption().getText();
                break;
            case DATEPICKER:
                elementText = element.getText();
                break;
            case SUGGEST:   //get text of label attribute of field ????
                elementText = element.getText();
                break;      
            case HIDDEN: // get value
                elementText = element.getText();
                break;
            case CHECK: //?????
                elementText = new Boolean(element.isSelected()).toString();
                break; 
            default:    
                break;                                                                                                                                                      
        }

        Logger.addLog(" field text:" + elementText);

        return elementText;
    }

    public String getRadioButtonGroupSelected(String radioGroup) throws Exception{
        Logger.addLog("getRadioButtonSelected: " + radioGroup);
        String radioGroupName = "form["+ radioGroup + "]";
        FieldType fieldType;

        List<WebElement> element;
        String radioSelected = "";
        element = browser.findElementsByName(radioGroupName);
        Logger.addLog("Numero de radioButton: " + element.size());
        for(WebElement we2:element)
            if(we2.isSelected())
            {
                radioSelected = we2.getAttribute("value");
            }
            
        return radioSelected;

    }

    public List<String> getCheckBoxGroupSelected(String checkGroup) throws Exception{

        Logger.addLog("getCheckBoxSelected: " + checkGroup);
        String checkGroupName = "form["+ checkGroup + "][]";
        FieldType fieldType;

        List<WebElement> element;
        List<String> checkSelected = new ArrayList<String>();
        element = browser.findElementsByName(checkGroupName);
        Logger.addLog("Numero de checkbox: " + element.size());
        for(WebElement we2:element)
            if(we2.isSelected())
            {
                checkSelected.add(we2.getAttribute("value"));
            }
            
        return checkSelected;   
    }

    public int gridCountRows(String gridName) throws Exception{

        String str = "";
        List<WebElement> wel;
        int numRow=0;
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.gridDeleteLink");
        str = str.replace("REPLACE_GRIDNAME", gridName);

        WebElement elem = browser.findElement(str);
        wel = elem.findElements(By.xpath("tbody/tr"));
        WebElement we = null;
        for(WebElement we2:wel)
            numRow++;
            
        numRow=numRow-2;    
        return numRow;

    }


    public int gridDeleteRow(String gridName, int numRow) throws Exception{

        String str = "";
        List<WebElement> wel;
        WebElement we = null;
        numRow++;
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.gridDeleteLink");
        str = str.replace("REPLACE_GRIDNAME", gridName);

        WebElement elem = browser.findElement(str);
        we = elem.findElement(By.xpath("tbody/tr[" + numRow + "]"));
        we =we.findElement(By.linkText("Delete"));
        we.click();

        this.btnAceptar();          

        return 0;

    }

    public int gridAddNewRow(String gridName) throws Exception{
        //return the total number of rows
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.gridNewRowLink");
        str = str.replace("REPLACE_GRIDNAME", gridName);

        WebElement element = browser.findElement(str);

        element.click();

        return 0;
    }

    public int getFieldCount(String fieldName) throws Exception{
        Logger.addLog("getFieldCount: " + fieldName);

        FieldType fieldType;
        int elementCount = 0;

        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        WebElement element = browser.findElement(str);
        
        fieldType = this.detectFieldType(element);

        Logger.addLog("getFieldCount: field type detected.");


        switch(fieldType)
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
                Select selectList = new Select(element);
                elementCount = selectList.getOptions().size();
                break;
            default: 
                elementCount = 0;   
                break;                                                                                                                                                      
        }

        Logger.addLog(" field count: " + elementCount);

        return elementCount;        
    }    

    // get property of field
    public String getFieldProperty(String nameField, String property) throws Exception{
        intoDynaform();
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", nameField);
        return browser.findElement(str).getAttribute(property);
    }

    // get object of dynaform
    public WebElement getObject(String xpathField) throws Exception{
        intoDynaform();
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDebug");
        str = str.replace("replaceXpath", xpathField);
        return browser.findElement(str);
    }

     public void sleep(long timeMilliSeconds) throws Exception {
        Thread.sleep(timeMilliSeconds);
     }

     public void sendTab(String fieldName) throws Exception {
        
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);
        WebElement element = browser.findElement(str);
        element.sendKeys(Keys.TAB);
     }

    public void click(String fieldName) throws Exception {

        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);
        WebElement element = browser.findElement(str);
        element.click();
    }

     public void clear(WebElement element) throws Exception {
        element.clear();
     }

     public void clear(String fieldName) throws Exception {
        
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        WebElement element = browser.findElement(str);

        this.clear(element);
    }

    public void waitForFieldToBeClickable(String fieldName, long timeoutSeconds) throws Exception {
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        By bySearchCriteria = browser.getBySearchCriteria(str);

        browser.waitForElementToBeClickable(bySearchCriteria, timeoutSeconds);
    }
}