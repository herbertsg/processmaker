package com.colosa.qa.automatization.pages;

import java.util.List;
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

public class DynaformExecution extends Page {

    public DynaformExecution() throws FileNotFoundException, IOException {

    }

    // Pages.Main().goHome();

    // into level of debug
    public void intoDebug() throws Exception {
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("casesFrame");
    }

    // into level of pmTrack
    public void intoPmtrack() throws Exception {
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("casesFrame");
        Browser.driver().switchTo().frame("casesSubFrame");
    }

    // into level of dynaform    
    public void intoDynaform() throws Exception {
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("casesFrame");
        Browser.driver().switchTo().frame("casesSubFrame");
        Browser.driver().switchTo().frame("openCaseFrame");
    }

    // out level of dynaform    
    public void outDynaform() throws Exception {
        Browser.driver().switchTo().defaultContent();
    }

    public FieldType detectFieldType(WebElement element) throws Exception{
        FieldType elementFieldType = null;

        System.out.println("Detect element type");

        //try to get field type using tagname
        String elementTagName = element.getTagName();

        switch(elementTagName){
            case "select": //Dropdown, yesno (no way to differentiate)
                System.out.println("Element Type: DropDown");
                elementFieldType = FieldType.DROPDOWN;
                break;
            case "input": //text (type=text)=>pm.textField, pm.currencyField, pm.percentageField
                        // suggest (type=hidden), 
                System.out.println("HTML tag: input");
                String typeAttribute = element.getAttribute("type");
                System.out.println("HTML type: " + typeAttribute);
                if(typeAttribute.equals("hidden")){
                    //this can be a suggest field, find previous simbling
                    //if suggest a label element is present
                    String idElementAttribute = element.getAttribute("id");
                    System.out.println("HTML id: " + idElementAttribute);
                    //get sub_string
                    String elementId;
                    elementId = idElementAttribute.substring(idElementAttribute.indexOf('[')+1,idElementAttribute.lastIndexOf(']'));
                    System.out.println("HTML element id: " + elementId);
                    Boolean suggestElementExists = Browser.elementExistsSearchCriteria("id___form[" + elementId + "_label]");

                    if(suggestElementExists){
                        System.out.println("Element Type: SUGGEST");
                        elementFieldType = FieldType.SUGGEST;
                    }
                    else {
                        //else return hidden field
                        System.out.println("Element Type: HIDDEN");
                        elementFieldType = FieldType.HIDDEN;
                    }
                }
                if(typeAttribute.equals("text")){
                    //text field
                    System.out.println("Element Type: TEXTBOX");
                    elementFieldType = FieldType.TEXTBOX;
                }
                if(typeAttribute.equals("password")){
                    System.out.println("Element Type: TEXTBOX");
                    elementFieldType = FieldType.TEXTBOX;
                }
                if(typeAttribute.equals("radio")){
                    System.out.println("Element Type: RADIOBUTTON");
                    elementFieldType = FieldType.RADIOBUTTON;
                }
                if(typeAttribute.equals("checkbox")){
                    System.out.println("Element Type: CHECK");
                    elementFieldType = FieldType.CHECK;
                }
                break;
            case "textarea":
                System.out.println("Element Type: TEXTAREA");
                elementFieldType = FieldType.TEXTAREA;
            default:
                elementFieldType = null;
        }


        return elementFieldType;
    }

    // get field of dynaform
    public WebElement getField(String nameField) throws Exception{
        intoDynaform();
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", nameField);

        System.out.println("element to search for: " + str);

        return Browser.getElement(str);
    }

    public void setFieldValue(String fieldName, String value) throws Exception{
        String str = "";
        FieldType fieldType;

        //search element
        WebElement element = this.getField(fieldName);

        System.out.println("element : " + element.getAttribute("value"));

        fieldType = this.detectFieldType(element);

        this.setFieldValue(fieldName, value, fieldType);

        return;
    }


    public void setFieldValue(String fieldName, String value, FieldType fieldType) throws Exception{
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");

        if(fieldType == FieldType.SUGGEST){
           fieldName = fieldName + "_label"; 
        }

        str = str.replace("replaceNameFieldDynaform", fieldName);

        System.out.println("element to search for: " + str);

        //search element
        WebElement element = Browser.getElement(str);

        System.out.println("element : "+element.getAttribute("value"));

        switch(fieldType)
        {
            case TEXTBOX: 
                this.clear(element);
                element.sendKeys(value);
                break;

            case BUTTON:    element.click();
                                    break;  

            case TEXTAREA: 
                this.clear(element);
                element.sendKeys(value);
                break; 

            case DROPDOWN: 
                Select droplist = new Select(element);
                droplist.selectByVisibleText(value);
                break;

            case RADIOBUTTON:   element.click();
                                        break;

            case CHECK:     element.click();
                                    break;  
                                    
            case READONLY:  ((JavascriptExecutor)Browser.driver()).executeScript("arguments[0].value=arguments[1]", element, value);
                            break;

            case SUGGEST:   //using label textbox
                WebElement elem2 = null;
                List<WebElement> listEl;
                //WebElement sugElem = null;
                this.clear(element);

                element.sendKeys(value);
                
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
     
            default:    break;                                                                                                                                                      
        }

        return;
    } 

    public String getFieldValue(String fieldName) throws Exception{
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);
        WebElement element = Browser.getElement(str);

        return element.getAttribute("value");
    }

    public String getDropdownFieldText(String fieldName) throws Exception{
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        WebElement element = Browser.getElement(str);
        
        Select droplist = new Select(element);
        //verify if the dropdown is filled
        return droplist.getFirstSelectedOption().getText();
    }

    public String getFieldText(String fieldName) throws Exception{
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);

        WebElement element = Browser.getElement(str);
        
        return element.getText();
    }

    // get property of field
    public String getFieldProperty(String nameField, String property) throws Exception{
        intoDynaform();
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", nameField);
        return Browser.getElement(str).getAttribute(property);
    }

    // get object of dynaform
    public WebElement getObject(String xpathField) throws Exception{
        intoDynaform();
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDebug");
        str = str.replace("replaceXpath", xpathField);
        return Browser.getElement(str);
    }

     public void sleep(long timeMilliSeconds) throws Exception {
        Thread.sleep(timeMilliSeconds);
     }

     public void sendTab(String fieldName) throws Exception {
        
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", fieldName);
        WebElement element = Browser.getElement(str);
        element.sendKeys(Keys.TAB);
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

}