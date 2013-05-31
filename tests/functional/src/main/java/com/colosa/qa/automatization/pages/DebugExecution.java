package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DebugExecution extends Page {


    public DebugExecution(BrowserInstance browserInstance) throws Exception {
        super(browserInstance);

        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    // into level of debug
    public void intoDebug() throws Exception {
        browser.switchToDefaultContent();
        browser.switchToFrame("casesFrame");
    }

    // verify debug
    public boolean verifyDebug() throws Exception {
    	intoDebug();
    	WebElement debugPanel = browser.findElementById("debugPanel");
    	String debugPanelClass = debugPanel.getAttribute("class").trim();

    	if (debugPanelClass.indexOf ("x-hide-display") > -1) {
    		return false;
    	} else {
    		return true;
    	}
    }

    // into tab variables
    public void showAllVariables() throws Exception {
    	intoDebug();
    	WebElement divDebugVariables = browser.findElementById("debugVariables");

    	List<WebElement> gridDebugButtons = divDebugVariables.findElements(By.tagName("button"));
    	String valueField = "";

        for(WebElement divs:gridDebugButtons)
        {
        	if (divs.getAttribute("innerHTML").trim() == "All") {
        		divs.click();
				break;
        	}
        }
    }

    // into tab variables
    public void goTabVariables() throws Exception {
    	intoDebug();
    	WebElement tab = browser.findElementById("debugPanelTabs__debugVariables");
    	showAllVariables();
    	tab.click();
    }

    // get Value
    public String getValue(String nameVariable) throws Exception{
    	intoDebug();
        Thread.sleep(2000);
        WebElement gridDebug = browser.findElementById("debugVariables");

        List<WebElement> gridDebugTrs = gridDebug.findElements(By.tagName("td"));
        String valueField = "";
        
        System.out.println("Looking for variable: " + nameVariable + "... ");
        for(WebElement divs:gridDebugTrs)
        {
            //System.out.println("Contador: " + con + " -> " + divs.getAttribute("innerHTML"));
            if ( (divs.getAttribute("class").indexOf ("x-grid3-td-name") > -1) && 
            	 (divs.getAttribute("innerHTML").indexOf (nameVariable) > -1) ) {

                //System.out.println("Exists variable '" + nameVariable + "' :) ");
                WebElement trElements = divs.findElement(By.xpath("..")).findElement(By.xpath(".."));
            	
            	List<WebElement> gridDebugDivs = trElements.findElements(By.tagName("div"));
		        for(WebElement tds:gridDebugDivs)
		        {
		        	if ( (tds.getAttribute("class").indexOf ("x-grid3-col-value") > -1) ) {
		            	System.out.println("Valor de variable es :'" + tds.getAttribute("innerHTML").trim() + "' :) ");
		            	valueField = tds.getAttribute("innerHTML").trim();
		            	break;
		        	}
		        }
        	}
        }
        return valueField;
    }
}