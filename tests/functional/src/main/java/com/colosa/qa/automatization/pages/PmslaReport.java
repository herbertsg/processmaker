package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.NumberFormat;

public class PmslaReport extends Page{


	WebElement slaName;
	WebElement datesSla;
	WebElement exceededSla;
	WebElement statusSla;
	WebElement submit;

    public PmslaReport(BrowserInstance browser) throws Exception {
        super(browser);
        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {

    }

    public void generateReport(String sla,String dates,String exceeded,String status) throws Exception{
			browser.switchToDefaultContent();
			browser.switchToFrame("casesFrame");
			browser.switchToFrame("casesSubFrame");
			browser.switchToFrame("iframe-id_tab_pmsla");
		 this.slaName = browser.findElementById("VAL_SLA");
			if(sla != "")
			{
				((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", slaName, sla);

			}
			this.datesSla = browser.findElementById("VAL_DATES");
			if(dates != "")
			{
				((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", datesSla, dates);
			}
			this.exceededSla = browser.findElementById("VAL_EXCEEDED");
			if(exceeded != "")
			{
				((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", exceededSla, exceeded);
			}
			this.statusSla = browser.findElementById("VAL_CASE_TYPE");
			if(status != "")
			{
				((JavascriptExecutor)browser.getInstanceDriver()).executeScript("arguments[0].value=arguments[1]", statusSla, status);
			}
			
			this.submit = browser.findElementByXPath("//button[@class=' x-btn-text button_menu_ext ss_sprite ss_report']");
			this.submit.click();   
	 
		}

		// return [0]=timesExecuted, [1]=timesExceeded, [2]=averangeExceeded, [3]=penalty
		public String[] getSlaInfo(String slaName) throws Exception{
			browser.switchToDefaultContent();
			browser.switchToFrame("casesFrame");
			browser.switchToFrame("casesSubFrame");
			browser.switchToFrame("iframe-id_tab_pmsla");
			ExtJSGrid grid = new ExtJSGrid(browser.findElementByXPath("//div[@class='x-panel x-grid-panel']"), browser);
			String timesExecuted;
			String timesExceeded;
			String averangeExceeded;
			String penalty;
			WebElement row = grid.getRowByColumnValue("SLA", slaName);
			if(row==null)
					throw new Exception("SLA not found");
			timesExecuted = row.findElement(By.xpath("table/tbody/tr/td[2]/div")).getText().trim();      
			timesExceeded = row.findElement(By.xpath("table/tbody/tr/td[3]/div")).getText().trim();   
			averangeExceeded = row.findElement(By.xpath("table/tbody/tr/td[4]/div")).getText().trim();   
			penalty = row.findElement(By.xpath("table/tbody/tr/td[5]/div")).getText().trim();    
			String[] array = new String[] {timesExecuted, timesExceeded, averangeExceeded, penalty};
			return array;
		}


		public void displayCases(String slaName) throws Exception{
			Actions action = new Actions(browser.getInstanceDriver());
			browser.switchToDefaultContent();
			browser.switchToFrame("casesFrame");
			browser.switchToFrame("casesSubFrame");
			browser.switchToFrame("iframe-id_tab_pmsla");
			ExtJSGrid grid = new ExtJSGrid(browser.findElementByXPath("//div[@class='x-panel x-grid-panel']"), browser);
			WebElement row = grid.getRowByColumnValue("SLA", slaName);
			if(row==null)
					throw new Exception("SLA not found");
			action.doubleClick(row.findElement(By.xpath("table/tbody/tr/td[2]/div")));
			action.perform();

		}  

		//return [0]=exceeded, [1]=startDate, [2]=dueDate, [3]=finishDate, [4]=penalty, [5]=status
		public String[] getCaseInfo(int caseNum) throws Exception{
			browser.switchToDefaultContent();
			browser.switchToFrame("casesFrame");
			browser.switchToFrame("casesSubFrame");
			browser.switchToFrame("iframe-id_tab_pmsla");
			ExtJSGrid grid = new ExtJSGrid(browser.findElementByXPath("//div[@class='x-panel x-grid-panel']"), browser);
			String exceeded;
			String startDate;
			String dueDate;
			String finishDate;
			String status;           
			String penalty;
			String caseNumString = NumberFormat.getIntegerInstance().format(caseNum);
			if(caseNumString.contains("."))
			{
				caseNumString = caseNumString.replace(".", ",");
			}
			WebElement row = grid.getRowByColumnValue("Cases #", "Case # " + caseNumString);
			if(row==null)
					throw new Exception("Case not found");
			exceeded = row.findElement(By.xpath("table/tbody/tr/td[3]/div")).getText().trim();      
			startDate = row.findElement(By.xpath("table/tbody/tr/td[4]/div")).getText().trim();   
			dueDate = row.findElement(By.xpath("table/tbody/tr/td[5]/div")).getText().trim();   
			finishDate = row.findElement(By.xpath("table/tbody/tr/td[6]/div")).getText().trim();   
			penalty = row.findElement(By.xpath("table/tbody/tr/td[7]/div")).getText().trim();  
			status = row.findElement(By.xpath("table/tbody/tr/td[8]/div")).getText().trim();          
			String[] array = new String[] {exceeded, startDate, dueDate, finishDate, penalty, status};
			return array;
		}   

		public void displayTasks(int caseNum) throws Exception{
			Actions action = new Actions(browser.getInstanceDriver());
			browser.switchToDefaultContent();
			browser.switchToFrame("casesFrame");
			browser.switchToFrame("casesSubFrame");
			browser.switchToFrame("iframe-id_tab_pmsla");
			ExtJSGrid grid = new ExtJSGrid(browser.findElementByXPath("//div[@class='x-panel x-grid-panel']"), browser);
			String caseNumString = NumberFormat.getIntegerInstance().format(caseNum);
			if(caseNumString.contains("."))
			{
				caseNumString = caseNumString.replace(".", ",");
			}
			WebElement row = grid.getRowByColumnValue("Cases #", "Case # " + caseNumString);
			if(row==null)
					throw new Exception("Case not found");
			action.doubleClick(row.findElement(By.xpath("table/tbody/tr/td[2]/div")));
			action.perform();
		} 

		//return [0]=delegatedUser, [1]=transferDate, [2]=startDate, [3]=endDate, [4]=duration, [5]=action
		public String[] getTaskInfo(String taskName) throws Exception{
			browser.switchToDefaultContent();
			browser.switchToFrame("casesFrame");
			browser.switchToFrame("casesSubFrame");
			browser.switchToFrame("iframe-id_tab_pmsla");
			String delegatedUser;
			String transferDate;
			String startDate;
			String endDate;
			String duration;           
			String action;
			WebElement we = browser.findElementByXPath("//td[div='" + taskName + "']/..");
			delegatedUser = we.findElement(By.xpath("td[2]/div")).getText().trim();      
			transferDate = we.findElement(By.xpath("td[3]/div")).getText().trim();   
			startDate = we.findElement(By.xpath("td[4]/div")).getText().trim();   
			endDate = we.findElement(By.xpath("td[5]/div")).getText().trim();   
			duration = we.findElement(By.xpath("td[6]/div")).getText().trim();  
			action = we.findElement(By.xpath("td[7]/div")).getText().trim();          
			String[] array = new String[] {delegatedUser, transferDate, startDate, endDate, duration, action};
			 return array;
		}   



}