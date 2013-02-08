package com.colosa.qa.automatization.pages;

import java.util.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.colosa.qa.automatization.common.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;
import org.openqa.selenium.support.ui.Select;

public class Admin extends Main{

    public Admin() throws Exception{
    }

    public static void goToLogs() throws Exception{
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        WebElement we = Browser.driver().findElement(By.xpath("//*[@id='west-panel__logs']/a[2]"));
        we.click();
        //Browser.driver().switchTo().defaultContent();
    }

    public static void goToPlugins() throws Exception{
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        WebElement we = Browser.driver().findElement(By.xpath("//*[@id='west-panel__plugins']/a[2]"));
        we.click();
    }
   
    public static void goToUsers() throws Exception{
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        WebElement we = Browser.driver().findElement(By.xpath("//*[@id='west-panel__users']/a[2]"));
        we.click();
    }
   
    public static void showCaseScheduler() throws Exception{
        WebElement we = Browser.driver().findElement(By.xpath("//div[@id='logs']/div/div/ul/div/li[2]"));
        we.click();
    }

    public static void showEmailLogs() throws Exception{
        WebElement we = Browser.driver().findElement(By.xpath("//div[@id='logs']/div/div/ul/div/li[4]"));
        we.click();
    }
    
    public static void goToSettings() throws Exception{
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        WebElement we = Browser.driver().findElement(By.xpath("//*[@id='west-panel__settings']/a[2]"));
        we.click();
    }
    public static void newPMTable(String nameTable, String descTable)throws Exception{
  			goToSettings();
  			Thread.sleep(3000);
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        WebElement divPMT = Browser.driver().findElement(By.id("settings"));
  			WebElement PMT = divPMT.findElement(By.xpath("//div/div/ul/div/li[11]"));
  			PMT.click();
  			
  			Thread.sleep(3000);
  			Browser.driver().switchTo().frame("setup-frame");
  			WebElement divGridPMT = Browser.driver().findElement(By.id("infoGrid"));
  			WebElement newPMT = divGridPMT.findElement(By.xpath("div[2]/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button"));
  			newPMT.click();
  			//html/body/div[13]/ul/li/a
  			Thread.sleep(4000);
  			WebElement newPMTable = Browser.driver().findElement(By.xpath("html/body/div[8]/ul/li"));
  			WebElement newPMTables = newPMTable.findElement(By.tagName("span"));
				newPMTables.click();
				
				Thread.sleep(4000);
				WebElement REP_TAB_NAME = Browser.driver().findElement(By.id("REP_TAB_NAME"));
  			REP_TAB_NAME.sendKeys(nameTable);
  			
  			WebElement REP_TAB_DSC = Browser.driver().findElement(By.id("REP_TAB_DSC"));
  			REP_TAB_DSC.sendKeys(descTable);
  	}
  		
  	public static void addField(String fieldName, String fieldLabel, String fieldType, String fieldSize, Boolean fieldPrimaryKey, Boolean fieldNull, Boolean fieldAutoincrement) throws Exception{		  			
  			WebElement assignedGrid = Browser.driver().findElement(By.id("assignedGrid"));
  			WebElement addField = assignedGrid.findElement(By.xpath("div/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button"));
  			addField.click();
  			
  			WebElement addFields = Browser.driver().findElement(By.xpath("/html/body/div[2]"));
  			
  			WebElement setName = addFields.findElement(By.xpath("div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div/div/input"));
  			setName.sendKeys(fieldName);
  			Thread.sleep(1000);
  			WebElement setLabel = addFields.findElement(By.xpath("div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div/div/input[2]"));
  			setLabel.sendKeys(fieldLabel);
  			Thread.sleep(1000);
  			WebElement setType = addFields.findElement(By.xpath("div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[6]/input"));
  			setType.sendKeys(fieldType);
  			Thread.sleep(1000);
  			 
        WebElement setSize = addFields.findElement(By.xpath("div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div/div/input[3]"));
  			setSize.sendKeys(fieldSize);
  			
  			if(fieldPrimaryKey != false){
	  			WebElement setPrimaryKey = addFields.findElement(By.xpath("div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[8]/input"));
	  			setPrimaryKey.click();
  		  }
  		  
  			if(fieldNull == false){
	  			WebElement setNull = addFields.findElement(By.xpath("div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[7]/input"));
	  			setNull.click();
  		  }
  		  
  		  if(fieldAutoincrement != false){
	  			WebElement setAutoincrement = addFields.findElement(By.xpath("div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div/div/div[9]/input"));
	  			setAutoincrement.click();
  		  }
  			  			
  			WebElement updateField = addFields.findElement(By.xpath("div/div/div/div/div/div[2]/div/div/div[2]/div[2]/div[2]/div[3]/div/div/table/tbody/tr/td/table/tbody/tr[2]/td[2]"));
  			updateField.click();
  			Thread.sleep(3000);
  	}
  			
  	public static void createPMTable() throws Exception{		
  			WebElement createTable = Browser.driver().findElement(By.xpath("/html/body/div[3]"));
  			WebElement createTableButton = createTable.findElement(By.xpath("div/div/div/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]"));
  			createTableButton.click();
  	}
  	
  	public Boolean verifyPMTable(String nameTable) throws Exception{		
  			goToSettings();
  			Thread.sleep(3000);
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        
        WebElement divPMT = Browser.driver().findElement(By.id("settings"));
  			WebElement PMT = divPMT.findElement(By.xpath("//div/div/ul/div/li[11]"));
  			PMT.click();
  			
        Browser.driver().switchTo().frame("setup-frame");
        
  			WebElement divFils = Browser.driver().findElement(By.className("x-grid3-body"));
        List<WebElement> divsRow = divFils.findElements(By.tagName("div"));
        
  			Boolean flagExist = false;
        for(WebElement divs:divsRow)
        {
            if ( (divs.getAttribute("class").indexOf ("x-grid3-row") > -1) && 
                 (divs.getAttribute("innerHTML").indexOf (nameTable) > -1) ) {
                flagExist = true;
                break;
            }
        }
  			
  			return flagExist;
  	}
  	
  	//System.out.println("HTML " + newww.getAttribute("innerHTML"));
  	public int countRoles() throws Exception{
        goToUsers();
        Thread.sleep(3000);
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        
        WebElement divRoles = Browser.driver().findElement(By.id("users"));
        WebElement liRoles = divRoles.findElement(By.xpath("div/div/ul/div/li[4]/div"));
        liRoles.click();
        
        Browser.driver().switchTo().frame("setup-frame");
        
        WebElement divFils = Browser.driver().findElement(By.className("x-grid3-body"));
        List<WebElement> divsGrid = divFils.findElements(By.tagName("div"));
        
        Integer count = 0;
        
        for(WebElement divs:divsGrid)
        {
            if ( (divs.getAttribute("class").indexOf ("x-grid3-row") > -1) ) {
				       	count = count+1;
				    }
				}
				return count;
        //System.out.println("LAS FILAS  "+count);
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

    public static String lastCreateCaseStatus() throws Exception{
        List<WebElement> rows = new ArrayList<WebElement>();
        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        Browser.driver().switchTo().frame("setup-frame");
        ExtJSGrid grid = new ExtJSGrid(Browser.driver().findElement(By.id("infoGrid")), Browser.driver());
        String status;
        rows = grid.getRows();
        if(rows==null)
            throw new Exception("The case Scheduler log is Empty");
        status = rows.get(0).findElement(By.xpath("table/tbody/tr/td[6]/div")).getText().trim();
        Browser.driver().switchTo().defaultContent();
        return status;
    }

    public static String emailStatus(Integer numCase) throws Exception{

        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        Browser.driver().switchTo().frame("setup-frame");
        ExtJSGrid grid = new ExtJSGrid(Browser.driver().findElement(By.id("emailsGrid")), Browser.driver());
        String emailStatus;
        WebElement row = grid.getRowByColumnValue("#", Integer.toString(numCase));
        if(row==null)
            throw new Exception("Case # "+Integer.toString(numCase)+" not found in Email Logs");
        emailStatus = row.findElement(By.xpath("table/tbody/tr/td[16]/div")).getText().trim();
        Browser.driver().switchTo().defaultContent();
        return emailStatus;
    }

    public static boolean userExists(String userName) throws Exception{

        Browser.driver().switchTo().defaultContent();
        Browser.driver().switchTo().frame("adminFrame");
        Browser.driver().switchTo().frame("setup-frame");

        ExtJSGrid grid = new ExtJSGrid(Browser.driver().findElement(By.id("infoGrid")), Browser.driver());
        String emailStatus;
        WebElement row = grid.getRowByColumnValue("User Name", userName);

        if(row==null)
            throw new Exception("User "+userName+" not found in Users List");
        else
            row.click();
            return true;   

    }
}