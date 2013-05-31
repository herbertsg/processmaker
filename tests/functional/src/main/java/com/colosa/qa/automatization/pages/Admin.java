package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Page{


    public Admin(BrowserInstance browserInstance) throws Exception {
        super(browserInstance);

        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void goToLogs() throws Exception{
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("adminFrame"),120);
        browser.switchToFrame("adminFrame");
        WebElement we = browser.findElementByXPath("//*[@id='west-panel__logs']/a[2]");
        we.click();
        //browser.switchToDefaultContent();
    }

    public void goToPlugins() throws Exception{
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("adminFrame"),120);
        browser.switchToFrame("adminFrame");
        WebElement we = browser.findElementByXPath("//*[@id='west-panel__plugins']/a[2]");
        we.click();
    }
   
    public void goToUsers() throws Exception{
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("adminFrame"),120);
        browser.switchToFrame("adminFrame");
        WebElement we = browser.findElementByXPath("//*[@id='west-panel__users']/a[2]");
        we.click();
    }
   
    public void showCaseScheduler() throws Exception{
        WebElement we = browser.findElementByXPath("//div[@id='logs']/div/div/ul/div/li[2]");
        we.click();
    }

    public void showEmailLogs() throws Exception{
        WebElement we = browser.findElementByXPath("//div[@id='logs']/div/div/ul/div/li[4]");
        we.click();
    }
    
    public void goToSettings() throws Exception{
        browser.switchToDefaultContent();
        browser.switchToFrame("adminFrame");
        WebElement we = browser.findElementByXPath("//*[@id='west-panel__settings']/a[2]");
        we.click();
    }
    public void newPMTable(String nameTable, String descTable)throws Exception{
  			goToSettings();
  			Thread.sleep(3000);
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("adminFrame"),120);
        browser.switchToFrame("adminFrame");
        WebElement divPMT = browser.findElementById("REP_TAB_DSC");
  			WebElement PMT = divPMT.findElement(By.xpath("//div/div/ul/div/li[11]"));
  			PMT.click();
  			
  			Thread.sleep(3000);
  			browser.switchToFrame("setup-frame");
            browser.waitForElement(By.id("infoGrid"),45);
  			WebElement divGridPMT = browser.findElementById("REP_TAB_DSC");
  			WebElement newPMT = divGridPMT.findElement(By.xpath("div[2]/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button"));
  			newPMT.click();
  			//html/body/div[13]/ul/li/a
  			Thread.sleep(4000);
  			WebElement newPMTable = browser.findElementByXPath("html/body/div[8]/ul/li");
  			WebElement newPMTables = newPMTable.findElement(By.tagName("span"));
				newPMTables.click();
				
				Thread.sleep(4000);
				WebElement REP_TAB_NAME = browser.findElementById("REP_TAB_DSC");
  			REP_TAB_NAME.sendKeys(nameTable);
  			
  			WebElement REP_TAB_DSC = browser.findElementById("REP_TAB_DSC");
  			REP_TAB_DSC.sendKeys(descTable);
  	}
  		
  	public void addField(String fieldName, String fieldLabel, String fieldType, String fieldSize, Boolean fieldPrimaryKey, Boolean fieldNull, Boolean fieldAutoincrement) throws Exception{
  			WebElement assignedGrid = browser.findElementById("assignedGrid");
  			WebElement addField = assignedGrid.findElement(By.xpath("div/div/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/em/button"));
  			addField.click();
  			
  			WebElement addFields = browser.findElementByXPath("/html/body/div[2]");
  			
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
  			
  	public void createPMTable() throws Exception{
  			WebElement createTable = browser.findElementByXPath("/html/body/div[3]");
  			WebElement createTableButton = createTable.findElement(By.xpath("div/div/div/table/tbody/tr/td[2]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]"));
  			createTableButton.click();
  	}
  	
  	public Boolean verifyPMTable(String nameTable) throws Exception{		
  			goToSettings();
  			Thread.sleep(3000);
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("adminFrame"),120);
        browser.switchToFrame("adminFrame");
        
        WebElement divPMT = browser.findElementById("settings");
  			WebElement PMT = divPMT.findElement(By.xpath("//div/div/ul/div/li[11]"));
  			PMT.click();
  			
        browser.switchToFrame("setup-frame");
        
  			WebElement divFils = browser.findElementByClassName("x-grid3-body");
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
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("adminFrame"),120);
        browser.switchToFrame("adminFrame");
        browser.waitForElement(By.id("users"),60);
        WebElement divRoles = browser.findElementById("users");
        WebElement liRoles = divRoles.findElement(By.xpath("div/div/ul/div/li[4]/div"));
        liRoles.click();
        
        browser.switchToFrame("setup-frame");
        
        WebElement divFils = browser.findElementByClassName("x-grid3-body");
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
    
    public void activePlugin(String namePlugin, Boolean flagActive) throws Exception{
        goToPlugins();
        Thread.sleep(3000);
        browser.switchToDefaultContent();
        browser.waitForElement(By.id("adminFrame"),120);
        browser.switchToFrame("adminFrame");
        browser.switchToFrame("setup-frame");
        WebElement divFils = browser.findElementByXPath("//*[@id='processesGrid']/div/div[2]/div/div/div[2]");

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
            WebElement buttonActive = browser.findElementById("activator");
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

    public String eventStatus(Integer numCase) throws Exception{

        browser.switchToDefaultContent();
        browser.switchToFrame("adminFrame");
        browser.switchToFrame("setup-frame");
        browser.waitForElement(By.id("eventsGrid"),60);
        ExtJSGrid grid = new ExtJSGrid(browser.findElementById("eventsGrid"), browser.getInstanceDriver());
        String status;
        WebElement row = grid.getRowByColumnValue("Case Title", "#" + Integer.toString(numCase));
        if(row==null)
            throw new Exception("Case # "+Integer.toString(numCase)+" not found in Event Logs");
        status = row.findElement(By.xpath("table/tbody/tr/td[13]/div")).getText().trim();
        browser.switchToDefaultContent();
        return status;
    }

    public String lastCreateCaseStatus() throws Exception{
        List<WebElement> rows = new ArrayList<WebElement>();
        browser.switchToDefaultContent();
        browser.switchToFrame("adminFrame");
        browser.switchToFrame("setup-frame");
        browser.waitForElement(By.id("infoGrid"),60);
        ExtJSGrid grid = new ExtJSGrid(browser.findElementById("infoGrid"), browser.getInstanceDriver());
        String status;
        rows = grid.getRows();
        if(rows==null)
            throw new Exception("The case Scheduler log is Empty");
        status = rows.get(0).findElement(By.xpath("table/tbody/tr/td[6]/div")).getText().trim();
        browser.switchToDefaultContent();
        return status;
    }

    public String emailStatus(Integer numCase) throws Exception{

        browser.switchToDefaultContent();
        browser.switchToFrame("adminFrame");
        browser.switchToFrame("setup-frame");
        browser.waitForElement(By.id("emailsGrid"),60);
        ExtJSGrid grid = new ExtJSGrid(browser.findElementById("emailsGrid"), browser.getInstanceDriver());
        String emailStatus;
        WebElement row = grid.getRowByColumnValue("#", Integer.toString(numCase));
        if(row==null)
            throw new Exception("Case # "+Integer.toString(numCase)+" not found in Email Logs");
        emailStatus = row.findElement(By.xpath("table/tbody/tr/td[16]/div")).getText().trim();
        browser.switchToDefaultContent();
        return emailStatus;
    }

    public boolean userExists(String userName) throws Exception{

        browser.switchToDefaultContent();
        browser.switchToFrame("adminFrame");
        browser.switchToFrame("setup-frame");
        browser.waitForElement(By.id("infoGrid"),360);
        ExtJSGrid grid = new ExtJSGrid(browser.findElementById("infoGrid"), browser.getInstanceDriver());
        String emailStatus;
        WebElement row = grid.getRowByColumnValue("User Name", userName);

        if(row==null)
            throw new Exception("User "+userName+" not found in Users List");
        else
            row.click();
            return true;   

    }

    public void pmSLA() throws Exception{
        List<WebElement> wel;
        boolean flag = false;
        browser.switchToDefaultContent();
        browser.switchToFrame("adminFrame");
        WebElement we = browser.findElementById("settings");
        wel = we.findElements(By.xpath(" div/div/ul/div/li"));
            for(WebElement we2:wel)
            {
                we = we2.findElement(By.xpath("div/a/span"));
                if(we.getText().equals("SLA"))
                {
                    we.click();
                    flag = true;
                    break;
                }
            }
            if(!flag)
                throw new Exception("Option not found");
       
    }


}