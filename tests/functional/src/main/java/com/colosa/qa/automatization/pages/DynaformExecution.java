package com.colosa.qa.automatization.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
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

    // get field of dynaform
    public WebElement getField(String nameField) throws Exception{
        intoDynaform();
        String str = "";
        str = ConfigurationSettings.getInstance().getSetting("DynaformExecution.webElement.fieldDynaform");
        str = str.replace("replaceNameFieldDynaform", nameField);
        return Browser.getElement(str);
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
}