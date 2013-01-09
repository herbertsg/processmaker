package com.colosa.qa.automatization.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.colosa.qa.automatization.common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;

public class Admin extends Main{

    public Admin() throws Exception{
    }

    public static void goToLogs() throws Exception{
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        WebElement we = Browser.driver().findElement(By.xpath("//*[@id='west-panel']/div[1]/div[2]/ul/li[@id='west-panel__logs']/a[2]"));
        we.click();
        //Browser.driver().switchTo().defaultContent();
    }

    public static void goToPlugins() throws Exception{
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        WebElement we = Browser.driver().findElement(By.xpath("//*[@id='west-panel__plugins']/a[2]"));
        we.click();
    }

    public static void activePlugin(String namePlugin, Boolean flagActive) throws Exception{
        goToPlugins();
        Thread.sleep(3000);
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        Browser.driver().switchTo().frame("setup-frame");
        WebElement divFils = Browser.driver().findElement(By.xpath("//*[@id='processesGrid']/div/div[2]/div/div/div[2]"));

        List<WebElement> divsGrid = divFils.findElements(By.tagName("div"));
        Boolean flagExist = false;
        for(WebElement divs:divsGrid)
        {
            if ( (divs.getAttribute("class").indexOf ("x-grid3-cell-inner") > -1) && 
                 (divs.getAttribute("innerHTML").indexOf (namePlugin) > -1) ) {
                WebElement tablePlugin = divs.findElement(By.xpath("..")).findElement(By.xpath("..")).findElement(By.xpath(".."));
                tablePlugin.click();
                flagExist = true;
                break;
            }
        }

        if (flagExist) {
            WebElement buttonActive = Browser.driver().findElement(By.id("activator"));
            WebElement buttonLabel = buttonActive.findElement(By.tagName("button"));
            
            if ( buttonLabel.getAttribute("innerHTML").trim().indexOf ("Enable") > -1 && (flagActive) ) {
                buttonLabel.click();
                Thread.sleep(3000);
                System.out.println("Plugin " + namePlugin + " active :)");
            } else if ( buttonLabel.getAttribute("innerHTML").trim().indexOf ("Disable") > -1 && (flagActive) ) {
                System.out.println("Plugin " + namePlugin + " is active :|");
            } else if ( buttonLabel.getAttribute("innerHTML").trim().indexOf ("Enable") > -1 && (!(flagActive)) ) {
                System.out.println("Plugin " + namePlugin + " is desactive :|");
            } else if ( buttonLabel.getAttribute("innerHTML").trim().indexOf ("Disable") > -1 && (!(flagActive)) ) {
                buttonLabel.click();
                Thread.sleep(3000);
                System.out.println("Plugin " + namePlugin + " desactive :)");
            }
            
        } else {
            System.out.println("Plugin " + namePlugin + " not exist :(");
        }
    }

    public static String eventStatus(Integer numCase) throws Exception{

        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        Browser.driver().switchTo().frame("setup-frame");
        ExtJSGrid grid = new ExtJSGrid(Browser.driver().findElement(By.id("eventsGrid")), Browser.driver());
        String status;
        WebElement row = grid.getRowByColumnValue("Case Title", "#" + Integer.toString(numCase));
        if(row==null)
            throw new Exception("Case # "+Integer.toString(numCase)+" not found in Event Logs");
        status = row.findElement(By.xpath("table/tbody/tr/td[13]/div")).getText().trim();
        Browser.driver().switchTo().defaultContent();
        return status;
    }



}