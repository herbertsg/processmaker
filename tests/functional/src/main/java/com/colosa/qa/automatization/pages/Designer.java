package com.colosa.qa.automatization.pages;

import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.Select;
import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.common.extJs.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Designer{

	protected static int divCount = 1;
	protected static String taskPath;
	protected static String divBelowPath;
	protected static String divUpPath;
	protected static String divRightBottomPath;
	protected static int taskNum = 1;
	protected static int taskN = 0;
	protected static String[][] elements = new String[20][4];

	public void Designer() throws IOException, FileNotFoundException, Exception{

		
	}

	public boolean createTask() throws Exception{

		String taskName = "Task ";
		int tNum = taskNum + 1; 
		Actions action = new Actions(Browser.driver());
		Thread.sleep(5000);
		WebElement gridPanel = Browser.driver().findElement(By.id("pm_target"));
		action.contextClick(gridPanel).perform();

		WebElement taskElem = Browser.driver().findElement(By.xpath("//div[2]/div[4]/div[2]"));

		if(taskElem.getText().equals("Add task"))
		{
			taskElem.click();
		}
		taskName = taskName + tNum;

		taskList();
		taskN++;

		return true; 

	}

	public boolean initialTask(String taskName) throws Exception{

		WebElement task = getTask(taskName);

		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("frameMain");

		Actions action = new Actions(Browser.driver());
				
		(new Actions(Browser.driver())).dragAndDrop(Browser.driver().findElement(By.xpath("//*[@id='pm_target']/div[2]/div[1]/div[3]/div/img[8]")), task).build().perform();

		return true;
	}

	public boolean sequential(String taskName1, String taskName2) throws Exception{

		WebElement task1 = getTask(taskName1);
		WebElement task2 = getTask(taskName2);

		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("frameMain");

		Actions action = new Actions(Browser.driver());
				
		(new Actions(Browser.driver())).dragAndDrop(Browser.driver().findElement(By.xpath("//*[@id='pm_target']/div[2]/div[1]/div[3]/div/img[1]")), task1).build().perform();

		(new Actions(Browser.driver())).dragAndDropBy(task2, 0, 0).build().perform(); 

		return true;

	}

	public boolean selection(String taskName1, String[] taskName2) throws Exception{

		WebElement task1 = getTask(taskName1);
		WebElement task2 = getTask(taskName2[0]);

		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("frameMain");

		Actions action = new Actions(Browser.driver());
				
		(new Actions(Browser.driver())).dragAndDrop(Browser.driver().findElement(By.xpath("//*[@id='pm_target']/div[2]/div[1]/div[3]/div/img[2]")), task1).build().perform();

		(new Actions(Browser.driver())).dragAndDropBy(task2, 0, 0).build().perform(); 

		editCondition(taskName1, taskName2, 1);

		return true;

	}

	public boolean evaluation(String taskName1, String[] taskName2) throws Exception{

		WebElement task1 = getTask(taskName1);
		WebElement task2 = getTask(taskName2[0]);

		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("frameMain");

		Actions action = new Actions(Browser.driver());
				
		(new Actions(Browser.driver())).dragAndDrop(Browser.driver().findElement(By.xpath("//*[@id='pm_target']/div[2]/div[1]/div[3]/div/img[3]")), task1).build().perform();

		(new Actions(Browser.driver())).dragAndDropBy(task2, 0, 0).build().perform(); 

		editCondition(taskName1, taskName2, 2);

		return true;

	}

	public boolean parallelFork(String taskName1, String[] taskName2) throws Exception{

		WebElement task1 = getTask(taskName1);
		WebElement task2 = getTask(taskName2[0]);

		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("frameMain");

		Actions action = new Actions(Browser.driver());
				
		(new Actions(Browser.driver())).dragAndDrop(Browser.driver().findElement(By.xpath("//*[@id='pm_target']/div[2]/div[1]/div[3]/div/img[4]")), task1).build().perform();

		(new Actions(Browser.driver())).dragAndDropBy(task2, 0, 0).build().perform();

		editCondition(taskName1, taskName2, 3);

		return true;
	}

	public boolean parallelByEvaluation(String taskName1, String[] taskName2)  throws Exception{

		WebElement task1 = getTask(taskName1);
		WebElement task2 = getTask(taskName2[0]);

		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("frameMain");

		Actions action = new Actions(Browser.driver());
				
		(new Actions(Browser.driver())).dragAndDrop(Browser.driver().findElement(By.xpath("//*[@id='pm_target']/div[2]/div[1]/div[3]/div/img[5]")), task1).build().perform();

		(new Actions(Browser.driver())).dragAndDropBy(task2, 0, 0).build().perform();

		editCondition(taskName1, taskName2, 4);

		return true;

	}

	public boolean parallelJoin(String taskName1, String taskName2) throws Exception{

		WebElement task1 = getTask(taskName1);
		WebElement task2 = getTask(taskName2);

		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("frameMain");

		Actions action = new Actions(Browser.driver());
				
		(new Actions(Browser.driver())).dragAndDrop(Browser.driver().findElement(By.xpath("//*[@id='pm_target']/div[2]/div[1]/div[3]/div/img[6]")), task1).build().perform();

		(new Actions(Browser.driver())).dragAndDropBy(task2, 0, 0).build().perform();


		return true;
	}

	public boolean endTask(String taskName) throws Exception{

		WebElement task = getTask(taskName);

		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("frameMain");

		Actions action = new Actions(Browser.driver());
				
		(new Actions(Browser.driver())).dragAndDrop(Browser.driver().findElement(By.xpath("//*[@id='pm_target']/div[2]/div[1]/div[3]/div/img[7]")), task).build().perform();
	
		return true;
	}

	public void moveTask(String taskName, int positionX, int positionY) throws Exception{

		WebElement task = getTask(taskName);
		(new Actions(Browser.driver())).dragAndDropBy(task, positionX, positionY).build().perform();

	}


	public WebElement getTask(String taskName) throws Exception{
		WebElement designPanel = Browser.driver().findElement(By.xpath("//div[@id='pm_target']/div[1]/div[1]/div[3]"));
		return designPanel.findElement(By.xpath("div[@class='processmap_task___processmaker'][div[1]='"+taskName.trim()+"']"));
	}

	public void taskList() throws Exception{
		taskNum++;
		taskPath = "//div[@id='pm_target']/div[1]/div[1]/div[3]/div["+taskNum+"]";
		taskNum++;
		divBelowPath = "//div[@id='pm_target']/div[1]/div[1]/div[3]/div["+taskNum+"]";
		taskNum++;
		divRightBottomPath = "//div[@id='pm_target']/div[1]/div[1]/div[3]/div["+taskNum+"]";
		taskNum++;
		divUpPath = "//div[@id='pm_target']/div[1]/div[1]/div[3]/div["+taskNum+"]";


		elements[taskN][0] = taskPath;
		elements[taskN][1] = divBelowPath;
		elements[taskN][2] = divRightBottomPath;
		elements[taskN][3] = divUpPath;

	}

	public void editCondition(String taskName, String[] arrayTasks, int conditionType) throws Exception{


		int i = 0;			
		boolean founded = false;
		WebElement elem = null;
		WebElement btnAdd = null;
		WebElement btnSend = null;
		Select droplist;
		int dropdownCount = 0;
		String namePath = "";

		if(conditionType==1){
			namePath = "GRID_SELECT_TYPE";
		}
		else
		{
			if(conditionType==2){
				namePath = "GRID_EVALUATE_TYPE";
			}
			else
			{
				if (conditionType==3) {
					namePath = "GRID_PARALLEL_TYPE";
				}
				else
				{
					if (conditionType==4) {
						namePath = "GRID_PARALLEL_EVALUATION_TYPE";
					}
				}
			}
		}
		while(!founded && i < elements.length) 
		{
			elem = Browser.driver().findElement(By.xpath(elements[i][0]));
			if(elem.getText().equals(taskName))
			{
				founded = true;
			}
			else
			{
				i++;
			}
		}

		if(founded == true)
		{
			System.out.println("Xpath: "+elements[i][1]);
			WebElement el = Browser.driver().findElement(By.xpath(elements[i][1]));
			Thread.sleep(5000);
			el.click();
		}
		else
		{
			System.out.println("No se encontro la tarea "+taskName);
		}

		if(arrayTasks.length>0)
		{
			for(int l=0;l<arrayTasks.length-1;l++)
			{
				System.out.println("Path: "+namePath);
				
					
				btnAdd = Browser.driver().findElement(By.id("form["+namePath+"][addLink]"));
				Thread.sleep(5000);
				btnAdd.click();
			}

			for(int j=0;j<arrayTasks.length;j++)
			{
				dropdownCount = j + 1;
				elem = Browser.driver().findElement(By.id("form["+namePath+"]["+dropdownCount+"][ROU_NEXT_TASK]"));
				droplist = new Select(elem);
				droplist.selectByVisibleText(arrayTasks[j]);
			}

			btnSend = Browser.driver().findElement(By.id("form[SAVE]"));
			btnSend.click();
		}			

	}


}