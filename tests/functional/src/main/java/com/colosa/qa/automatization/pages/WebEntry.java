package com.colosa.qa.automatization.pages;

import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Keys;

import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.common.extJs.*;
import java.io.FileNotFoundException;
import java.io.IOException;


public class WebEntry extends Page {

    public WebEntry() throws FileNotFoundException, IOException {

    }

    // into level of debug
    public void goWebEntry(String workSpace, String idWebEntry) throws Exception {
        String myServer = ConfigurationSettings.getInstance().getSetting("server.url");
        String myUrl = myServer + "/sys" + workSpace + "/en/classic/" + idWebEntry + "/WebForm.php";
        System.out.println(myUrl);
        Browser.gotoUrl(myUrl);
    }

    public Integer getNumberCase() throws Exception {
        Thread.sleep(2000);
        WebElement fieldContent = Browser.driver().findElement(By.className("FormFieldContent"));
        String labelAll = fieldContent.getAttribute("innerHTML").trim();
        String[] labelPart = labelAll.split("<br>");
        String[] numberPart = labelPart[2].split(":");
        System.out.println("New Case of webentry: " + numberPart[1].trim());
        return Integer.parseInt(numberPart[1].trim());
    }

    

    public void clear(WebElement element) throws Exception {
        element.clear();
    }

    public void clear(String fieldName) throws Exception {
        
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        WebElement element = Browser.getElement(str);

        this.clear(element);
    }

    // get field of dynaform
    public WebElement getField(String fieldName) throws Exception{
        System.out.println("getField: " + fieldName);

        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        System.out.println(" Element to search for: " + str);

        return Browser.getElement(str);
    }

    public FieldType detectFieldType(WebElement element) throws Exception{
        FieldType elementFieldType = null;

        System.out.println("detectFieldType:");

        //try to get field type using tagname
        String elementTagName = element.getTagName();
        System.out.println(" element tagName:" + elementTagName);

        switch(elementTagName){
            case "select": 
                String multipleAttribute = element.getAttribute("multiple");
                if(multipleAttribute != null && multipleAttribute.equals("multiple")){ //listbox
                    System.out.println(" Element Type: ListBox");
                    elementFieldType = FieldType.LISTBOX;
                }
                else { //Dropdown, yesno (no way to differentiate)
                    System.out.println(" Element Type: DropDown");
                    elementFieldType = FieldType.DROPDOWN;
                }
                break;
            case "input": //text (type=text)=>pm.textField, pm.currencyField, pm.percentageField
                        // suggest (type=hidden), 
                System.out.println(" HTML tag: input");
                String typeAttribute = element.getAttribute("type");
                System.out.println(" HTML type: " + typeAttribute);
                if(typeAttribute.equals("hidden")){
                    //this can be a suggest field, find previous simbling
                    //if suggest a label element is present
                    String idElementAttribute = element.getAttribute("id");
                    System.out.println(" HTML id: " + idElementAttribute);
                    //get sub_string
                    String elementId;
                    elementId = idElementAttribute.substring(idElementAttribute.indexOf('[')+1,idElementAttribute.lastIndexOf(']'));
                    System.out.println(" HTML element id: " + elementId);
                    Boolean suggestElementExists = Browser.elementExistsSearchCriteria("id__&&__form[" + elementId + "_label]");

                    if(suggestElementExists){
                        System.out.println(" Element Type: SUGGEST");
                        elementFieldType = FieldType.SUGGEST;
                    }
                    else {
                        //else return hidden field
                        System.out.println(" Element Type: HIDDEN");
                        elementFieldType = FieldType.HIDDEN;
                    }
                }
                if(typeAttribute.equals("text")){ // textbox, currency, percentage, 
                   //datepicker??????
                    String readonlyAttribute = element.getAttribute("readonly");
                    System.out.println(" HTML readonly Attribute: " + readonlyAttribute);
                    if(readonlyAttribute != null && readonlyAttribute.equals("true"))
                    {
                        System.out.println(" Element Type: DATEPICKER");
                        elementFieldType = FieldType.DATEPICKER;
                    }else{
                        //text field
                        System.out.println(" Element Type: TEXTBOX");
                        elementFieldType = FieldType.TEXTBOX;                        
                    }
                }
                if(typeAttribute.equals("password")){ //password
                    System.out.println(" Element Type: TEXTBOX");
                    elementFieldType = FieldType.TEXTBOX;
                }
                if(typeAttribute.equals("radio")){
                    System.out.println(" Element Type: RADIOBUTTON");
                    elementFieldType = FieldType.RADIOBUTTON;
                }
                if(typeAttribute.equals("checkbox")){
                    System.out.println(" Element Type: CHECK");
                    elementFieldType = FieldType.CHECK;
                }
                if(typeAttribute.equals("button") || typeAttribute.equals("submit") || typeAttribute.equals("reset")){
                    System.out.println(" Element Type: BUTTON");
                    elementFieldType = FieldType.BUTTON;
                } 
                if(typeAttribute.equals("file")){
                    System.out.println(" Element Type: FILE");
                    elementFieldType = FieldType.FILE;
                } 
                if(typeAttribute.equals("")){ //datepicker ???
                    //System.out.println("Element Type: CHECK");
                    String readonlyAttribute = element.getAttribute("readonly");
                    if(readonlyAttribute.equals("readonly"))
                    {
                        elementFieldType = FieldType.DATEPICKER;
                    }
                }
                break;
            case "textarea":
                System.out.println(" Element Type: TEXTAREA");
                elementFieldType = FieldType.TEXTAREA;
                break;
            case "span": //title, subtitle
                System.out.println(" Element Type: TITLE");
                elementFieldType = FieldType.TITLE;                
                break;
            case "a": //link
                System.out.println(" Element Type: LINK");
                elementFieldType = FieldType.LINK;                 
                break;
            default:
                elementFieldType = null;
                break;
        }
        return elementFieldType;
    }

    public void setFieldValue(String fieldName, String value) throws Exception{
        String str = "";
        FieldType fieldType;

        //search element
        WebElement element = this.getField(fieldName);

        System.out.println("element : " + element.getAttribute("value"));

        fieldType = this.detectFieldType(element);

        this.setFieldValue(element, value, fieldType);

        return;
    }

    public void setFieldValue(String fieldName, String value, FieldType fieldType) throws Exception{
        System.out.println("setFieldValue (String fieldName): ");

        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");

        str = str.replace("replaceNameFieldDynaform", fieldName);

        //search element
        WebElement element = Browser.getElement(str);

        this.setFieldValue(element, value, fieldType);

        return;
    }

    public void setFieldValue(WebElement element, String value, FieldType fieldType) throws Exception{
        System.out.println("setFieldValue (WebElement): ");

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
                ((JavascriptExecutor)Browser.driver()).executeScript("arguments[0].value=arguments[1]", element, value);
                break;
            case READONLY:  
                ((JavascriptExecutor)Browser.driver()).executeScript("arguments[0].value=arguments[1]", element, value);
                break;
            case SUGGEST:   //using label textbox
                WebElement elem2 = null;
                List<WebElement> listEl;
                //WebElement sugElem = null;
                //get the label element
                
                //if suggest a label element is used to select option
                String idElementAttribute = element.getAttribute("id");
                String elementId = idElementAttribute.substring(idElementAttribute.indexOf('[')+1,idElementAttribute.lastIndexOf(']'));
                System.out.println(" HTML element id: " + elementId);

                //get label element 
                WebElement labelElement = this.getField(elementId + "_label");                

                this.clear(labelElement);

                labelElement.sendKeys(value);
                
                Browser.waitForElement(By.xpath("//div[1]/ul/li"),2);
                elem2 = Browser.driver().findElement(By.xpath("//div[1]/ul/li"));
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
                        Browser.waitForElement(By.xpath("//div[1]/ul/li"),2);                                   
                    } catch(Exception ex){
                        //element not found
                        cadIns = cadIns.substring(1, cadIns.length());
                        continue;
                    }
                    elem2 = Browser.driver().findElement(By.xpath("//div[1]/ul/li"));
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
}