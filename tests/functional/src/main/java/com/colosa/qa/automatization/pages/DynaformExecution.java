package com.colosa.qa.automatization.pages;

//import java.util.List;
//import java.lang.Boolean;
import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.common.extJs.ExtJSFloatingMenu;
import com.colosa.qa.automatization.common.extJs.ExtJSToolbar;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DynaformExecution extends Page {
    List<WebElement> auxSearchList;
    ExtJSToolbar openCaseToolbar = null;
    BrowserInstance browser = null;
    WebElement casesSubFrame = null;
    private List<PMField> listPMFields;
    private List<PMGrid> listPMGrids;
    private WebElement formTitle = null; //id = publisherContent[1]
    private WebElement formContent = null; //id = publisherContent[2]
    private WebElement formDefault = null;
    List<WebElement> listGrids = null;

    public DynaformExecution(BrowserInstance browser) throws Exception {
        super(browser);
        this.browser = browser;

        verifyPage();

        //switch again to frame to get toolbar
        browser.switchToDefaultContent();
        browser.switchToFrame("casesFrame");
        this.casesSubFrame = browser.findElementById("casesSubFrame");

        browser.switchToFrame("casesSubFrame");
        //get toolbar for open dynaform
        WebElement toolbarParent = browser.findElementById("navPanel");

        //search navPanel
        if(toolbarParent == null){
            Logger.addLog("Nav Panel not found");
        }
        Logger.addLog("Nav Panel found");

        //toolbar
        this.openCaseToolbar = new  ExtJSToolbar(toolbarParent, browser);

        browser.switchToFrame("openCaseFrame");
        //detect form title
        auxSearchList = this.browser.getInstanceDriver().findElements(By.id("publisherContent[1]"));
        if(auxSearchList.size() > 0){
            //use the first x-toolbar found
            this.formTitle = auxSearchList.get(0);
            Logger.addLog("PMForm title found");
        }else
        {
            throw new Exception("PMForm title not found.");
        }
        //detect form content
        auxSearchList = this.browser.getInstanceDriver().findElements(By.id("publisherContent[2]"));
        if(auxSearchList.size() > 0){
            //use the first x-toolbar found
            this.formContent = auxSearchList.get(0);
            Logger.addLog("PMForm content found");
        }else
        {
            throw new Exception("PMForm content not found.");
        }

        //detect form Form
        auxSearchList = this.browser.getInstanceDriver().findElements(By.className("formDefault"));
        if(auxSearchList.size() > 0){
            //use the first x-toolbar found
            this.formDefault = auxSearchList.get(0);
            Logger.addLog("PMForm Default found");
        }else
        {
            throw new Exception("PMForm Default not found.");
        }

        //detect PMFields in Dynaform
        listPMFields = this.queryListPMFields();


        //detect the list of grids
        listGrids = this.formDefault.findElements(By.className("grid"));
        Logger.addLog("Detected Grids:" + listGrids.size());
        if(listGrids.size() > 0){
            //foreach grid load grids
            listPMGrids = new ArrayList<PMGrid>(listGrids.size());

            for (WebElement grid : listGrids) {
                Logger.addLog("Grid Found.");
                listPMGrids.add(new PMGrid(grid, this.browser));
            }
        }

    }

    @Override
    public void verifyPage() throws Exception {
        //verify that we are in a dynaform (execution) status
        //wait for page
        browser.switchToDefaultContent();
        //browser.waitForElement(By.id("casesFrame"),120);
        browser.switchToFrame("casesFrame");
        //Logger.addLog("goto subcaseFrame ...");
        browser.switchToFrame("casesSubFrame");
        //browser.waitForElement(By.id("casesSubFrame"), 10);


        //browser.switchToFrame("casesSubFrame");
        browser.switchToFrame("openCaseFrame");

        browser.waitForElement(By.cssSelector("form div.content"), 5);

        Logger.addLog("wait form of open case ...");
        //browser.waitForElement(By.id("processesFilter"), 10);

        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private List<PMField> queryListPMFields() throws Exception {
        List<WebElement> listElementsFinal = new ArrayList<WebElement>();

        browser.turnOffImplicitWaits();
        //[id^='form[']:not([id*=']['])
        List<WebElement> listElementsContent = this.formDefault.findElements(By.className("FormFieldContent"));//By.cssSelector("object[^='form[']"));  By.cssSelector("[id|=form]")
        List<WebElement> listElementsButtons = this.formDefault.findElements(By.className("FormButton"));
        List<WebElement> listElementsTitle = this.formDefault.findElements(By.className("FormTitle"));
        List<WebElement> listElementsSubtittle = this.formDefault.findElements(By.className("FormSubTitle"));
        //List<WebElement> listElementsHidden = this.formDefault.findElements(By.cssSelector("input[id^='form'][type='hidden']"));
        browser.turnOnImplicitWaits();

        Logger.addLog("Get current list of PMFields: formFieldContent:" + listElementsContent.size() +
                " buttons:" +  listElementsButtons.size() + " Titles:" + listElementsTitle.size() +
                " SubTitles:" + listElementsSubtittle.size() ); //+ " Hidden:" + listElementsHidden.size()

        //add fields filter the form PMfields
        for(WebElement elementContent : listElementsContent) {
            Logger.addLog("Field element content: " + elementContent.getAttribute("class"));
            //check if has additional elements inside
            List<WebElement> listElementsContentChilds = elementContent.findElements(By.cssSelector("[id^='form']"));//^=form //By.cssSelector("object[^='form[']"));  By.cssSelector("[id|=form]")   .//*[starts-with(@id,'form')]
            if(listElementsContentChilds.size() > 0){
                //add detected field
                Logger.addLog("Found subElements count: " + listElementsContentChilds.size());
                for(WebElement elementForm : listElementsContentChilds){
                    //detect if form element is an auxiliar element  _label or
                    //check if is a datetime field button
                    String idAttribute = elementForm.getAttribute("id");
                    if((idAttribute.indexOf("_label")==-1) && (idAttribute.indexOf("[btn]")==-1)){
                        listElementsFinal.add(elementForm);
                        Logger.addLog("Field element detected: " + elementForm.getAttribute("id"));
                    }
                }
                //listElementsFinal.add(listElementsContentChilds.get(0));
            }else
            {
                throw new Exception("PM Field not found.");
            }
        }

        //add buttons
        for(WebElement elementButtonContent : listElementsButtons) {
            Logger.addLog("Field element button: " + elementButtonContent.getAttribute("class"));
            //check if has additional elements inside
            List<WebElement> listElementsButtonContentChilds = elementButtonContent.findElements(By.cssSelector("[id^='form']"));//^=form //By.cssSelector("object[^='form[']"));  By.cssSelector("[id|=form]")   .//*[starts-with(@id,'form')]
            if(listElementsButtonContentChilds.size() > 0){
                //add detected field
                Logger.addLog("Found subElements count: " + listElementsButtonContentChilds.size());
                for(WebElement elementForm : listElementsButtonContentChilds){
                    //detect if form element is an auxiliar element
                    String idAttribute = elementForm.getAttribute("id");
                    if(idAttribute.indexOf("_label")==-1){
                        listElementsFinal.add(elementForm);
                        Logger.addLog("Field element detected: " + elementForm.getAttribute("id"));
                    }
                }
                //listElementsFinal.add(listElementsContentChilds.get(0));
            }else
            {
                throw new Exception("PM Field not found.");
            }
        }

        //add titles
        for(WebElement elementTitleContent : listElementsTitle) {
            Logger.addLog("Field element titles: " + elementTitleContent.getAttribute("class"));
            //check if has additional elements inside
            List<WebElement> listElementsTitleContentChilds = elementTitleContent.findElements(By.cssSelector("[id^='form']"));//^=form //By.cssSelector("object[^='form[']"));  By.cssSelector("[id|=form]")   .//*[starts-with(@id,'form')]
            if(listElementsTitleContentChilds.size() > 0){
                //add detected field
                Logger.addLog("Found subElements count: " + listElementsTitleContentChilds.size());
                for(WebElement elementForm : listElementsTitleContentChilds){
                    //detect if form element is an auxiliar element
                    String idAttribute = elementForm.getAttribute("id");
                    if(idAttribute.indexOf("_label")==-1){
                        listElementsFinal.add(elementForm);
                        Logger.addLog("Field element detected: " + elementForm.getAttribute("id"));
                    }
                }
                //listElementsFinal.add(listElementsContentChilds.get(0));
            }else
            {
                throw new Exception("PM Field not found.");
            }
        }

        //add subtitles
        for(WebElement elementSubTitleContent : listElementsSubtittle) {
            Logger.addLog("Field element subtitles: " + elementSubTitleContent.getAttribute("class"));
            //check if has additional elements inside
            List<WebElement> listElementsSubTitleContentChilds = elementSubTitleContent.findElements(By.cssSelector("[id^='form']"));//^=form //By.cssSelector("object[^='form[']"));  By.cssSelector("[id|=form]")   .//*[starts-with(@id,'form')]
            if(listElementsSubTitleContentChilds.size() > 0){
                //add detected field
                Logger.addLog("Found subElements count: " + listElementsSubTitleContentChilds.size());
                for(WebElement elementForm : listElementsSubTitleContentChilds){
                    //detect if form element is an auxiliar element
                    String idAttribute = elementForm.getAttribute("id");
                    if(idAttribute.indexOf("_label")==-1){
                        listElementsFinal.add(elementForm);
                        Logger.addLog("Field element detected: " + elementForm.getAttribute("id"));
                    }
                }
                //listElementsFinal.add(listElementsContentChilds.get(0));
            }else
            {
                throw new Exception("PM Field not found.");
            }
        }

        //add hidden
        //they must be added manually there's no other way to detect them
        /*
        for(WebElement elementHiddenContent : listElementsHidden) {
            Logger.addLog("Field element hidden: " + elementHiddenContent.getAttribute("class"));
            listElementsFinal.add(elementHiddenContent);
        } */


        List<PMField> listPMFields = new ArrayList<PMField>(listElementsFinal.size());

        for (WebElement pmField : listElementsFinal) {
            PMField newPMField = new PMField(pmField, this.browser);
            listPMFields.add(newPMField);

            //too slow
            //Logger.addLog("new PMField text: " + newPMField.getText());
        }

        return listPMFields;
    }

    public PMGrid getPMGrid(String gridName){
        for (PMGrid pmGrid:listPMGrids){
             if(pmGrid.getGridName().equals(gridName)){
                 return pmGrid;
             }
        }
        return null;
    }

    public PMField getPMField(String fieldName){
        for(PMField pmField:listPMFields){
            if(pmField.getFieldName().equals(fieldName)){
                return pmField;
            }
        }
        return null;
    }


    public void addPMFieldToForm(String fieldName) throws Exception {
        Logger.addLog("addPMFieldToForm try to add PMfield:" + fieldName);
        //used to add pmfields to list of pmfields manually if they are not detected automatically
        //note hidden fields of forms are not detected automatically
        //find field element
        //WebElement newElement = this.formDefault.findElements(By.className("FormFieldContent"));
        auxSearchList = this.formDefault.findElements(By.id("form[" + fieldName + "]"));
        if(auxSearchList.size() > 0){
            PMField newPMField = new PMField(auxSearchList.get(0), this.browser);
            listPMFields.add(newPMField);
            Logger.addLog("addPMFieldToForm add PMfield:" + fieldName);
        }else
        {
            throw new Exception("PMForm specified field not found in form.");
        }
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

        //click case note button
        //toolbar.findToolbarCell("  Case Notes").click();
        this.openCaseToolbar.findToolbarCell(3).clickButton();

        //wait for case notes window to appear
        CaseNote caseNote = new CaseNote(browser);
        if(caseNote == null){
            throw new Exception("Error opening Case Notes window.");
        }

        Logger.addLog("Case Note window open");

        return caseNote;
    }

    public PauseCase pauseCase() throws Exception {
        //click toolbar actions/pause
        openCaseToolbar.findToolbarCell(2).clickButton();

        //intoCasesSubFrame();
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


    /*
    // get field of dynaform
    public WebElement getField(String fieldName) throws Exception{
        Logger.addLog("getField: " + fieldName);

        intoDynaform();
        String str = "";
        //str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        //str = str.replace("replaceNameFieldDynaform", fieldName);

        //Logger.addLog(" Element to search for: " + str);

        //WebElement resultWebElement = browser.findElement(str);
        WebElement resultWebElement = this.browser.getInstanceDriver().findElement(By.id("form["+fieldName+"]"));

        //scroll to element
        Actions actions = new Actions(browser.getInstanceDriver());

        actions.moveToElement(resultWebElement);

        return resultWebElement;

    } */

    /*public WebElement getFieldWithoutForm(String fieldName) throws Exception{
        Logger.addLog("getField: " + fieldName);

       // intoDynaform();
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaformWithoutForm");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        Logger.addLog(" Element to search for: " + str);

        return browser.findElement(str);
    }*/

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

        getPMGrid(gridName).setGridFieldValue(fieldName, row, value);
    }

    public void setCheckBoxGroupField(String checkGroup, String checkName) throws Exception{
        getPMField(checkGroup + "][" + checkName).click();
    }

    public String getCheckBoxGroupFieldValue(String checkGroup, String checkName) throws Exception {
        return getPMField(checkGroup + "][" + checkName).getValue();
        //elementValue = new Boolean(this.webElementPMField.isSelected()).toString();
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

    public void setRadioButtonGroupField(String radioGroup, String radioName) throws Exception{
        getPMField(radioGroup + "][" + radioName).click();
    }

    public String getRadioButtonGroupFieldValue(String radioGroup, String radioName) throws Exception {
        return getPMField(radioGroup + "][" + radioName).getValue();
        //elementValue = new Boolean(this.webElementPMField.isSelected()).toString();
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



    public void clickButton(String buttonName) throws Exception {
        getPMField(buttonName).click();
    }

    public void clickLink(String linkName) throws Exception {
        getPMField(linkName).click();
    }

    public void setFieldValue(String fieldName, String value) throws Exception{
        intoDynaform();
        getPMField(fieldName).setValue(value);
    }

    public String getFieldValue(String fieldName) throws Exception{
        intoDynaform();
        return getPMField(fieldName).getValue();
    }

    public String getFieldText(String fieldName) throws Exception{
        return getPMField(fieldName).getText();
    }

    public String getGridFieldValue(String gridName, int row, String fieldName) throws Exception{
        return getPMGrid(gridName).getPMGridField(fieldName).getPMGridFieldValue(row);
    }

    public String getGridFieldtext(String gridName, int row, String fieldName) throws Exception{
        return getPMGrid(gridName).getPMGridField(fieldName).getPMGridFieldText(row);
    }
    /*
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
        Logger.addLog("setFieldValue (WebElement): " + value + " type:" + fieldType.toString());

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

                break;  

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

                break;  

            default:    break;                                                                                                                                                      
        }

        return;
    }*/



    /*
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
    }*/



    /*
    public String getDropdownFieldText(String fieldName) throws Exception{
        intoDynaform();
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        WebElement element = browser.findElement(str);
        
        Select droplist = new Select(element);
        //verify if the dropdown is filled
        return droplist.getFirstSelectedOption().getText();
    }*/



    /*public void nextStepLinkWithMessage() throws Exception{
        getPMField("DYN_FORWARD").click();

        //setFieldValue("DYN_FORWARD", "", FieldType.BUTTON);
        //if(browser.findElementByXPath("//div[1]/div[1]/div[6]/div[1]/input[1]").isDisplayed())
        //{
        //    this.btnAceptar();
        //}
    }*/
    /*
    public void nextStepLink() throws Exception{
        setFieldValue("DYN_FORWARD", "", FieldType.BUTTON);
    }

    public void previousStepLink() throws Exception{
        setFieldValue("DYN_BACKWARD", "", FieldType.BUTTON);
    } */

    public void btnAceptar() throws Exception{
        WebElement elem = browser.findElementByXPath("//div[1]/div[1]/div[6]/div[1]/input[1]");
        elem.click();

    }

    public void btnCancelar() throws Exception{
        WebElement elem = browser.findElementByXPath("//div[1]/div[1]/div[6]/div[1]/input[2]");
        elem.click();
    }
    /*
    public String getGridFieldText(String gridName, int row, String fieldName) throws Exception{
        intoDynaform();
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
    }*/



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

    public void gridAddNewRow(String gridName) throws Exception{
        getPMGrid(gridName).addNewRow();
    }

    public int getFieldCount(String fieldName) throws Exception{
        return getPMField(fieldName).getOptionsCount();
    }

    // get property of field
    public String getFieldAttribute(String fieldName, String attribute) throws Exception{
        return getPMField(fieldName).getAttribute(attribute);
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
         getPMField(fieldName).sendTab();
     }

    public void click(String fieldName) throws Exception {
        getPMField(fieldName).click();
    }

    public void clear(String fieldName) throws Exception {
        getPMField(fieldName).clear();
    }

    public void waitForFieldToBeClickable(String fieldName, long timeoutSeconds) throws Exception {
        intoDynaform();
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        By bySearchCriteria = browser.getBySearchCriteria(str);

        browser.waitForElementToBeClickable(bySearchCriteria, timeoutSeconds);
    }

    public void waitForFieldToChangeText(String fieldName, String currentText,  int timeoutSeconds) throws Exception {
        PMField pmField = getPMField(fieldName);
        FieldType fieldType = pmField.getFieldType();

        if((fieldType == FieldType.TEXTBOX) || (fieldType == FieldType.CURRENCY) || (fieldType == FieldType.PERCENTAGE)){
            WaitTool.waitForTextToChange(browser.getInstanceDriver(), pmField.getWebElement(), currentText, timeoutSeconds);
        }

        if((fieldType == FieldType.LISTBOX)||(fieldType == FieldType.DROPDOWN)||(fieldType == FieldType.YESNO)){
            WaitTool.waitForSelectedTextToChange(browser.getInstanceDriver(), pmField.getWebElement(), currentText, timeoutSeconds);
        }

        //WaitTool.waitForTextToChange(browser.getInstanceDriver(), myElement, currentText, timeoutSeconds);

        //browser.waitForTextNotEqual(myElement, currentText, timeoutSeconds);

        //browser.waitForElementToBeClickable(bySearchCriteria, timeoutSeconds);
    }

    public void waitForFieldToChangeValue(String fieldName, String currentValue,  int timeoutSeconds) throws Exception {
        PMField pmField = getPMField(fieldName);
        FieldType fieldType = pmField.getFieldType();

        if((fieldType == FieldType.TEXTBOX) || (fieldType == FieldType.CURRENCY) || (fieldType == FieldType.PERCENTAGE)){
            WaitTool.waitForValueToChange(browser.getInstanceDriver(), pmField.getWebElement(), currentValue, timeoutSeconds);
        }

        if((fieldType == FieldType.LISTBOX)||(fieldType == FieldType.DROPDOWN)||(fieldType == FieldType.YESNO)){
            WaitTool.waitForSelectedValueToChange(browser.getInstanceDriver(), pmField.getWebElement(), currentValue, timeoutSeconds);
        }

        //WaitTool.waitForTextToChange(browser.getInstanceDriver(), myElement, currentText, timeoutSeconds);

        //browser.waitForTextNotEqual(myElement, currentText, timeoutSeconds);

        //browser.waitForElementToBeClickable(bySearchCriteria, timeoutSeconds);
    }
}