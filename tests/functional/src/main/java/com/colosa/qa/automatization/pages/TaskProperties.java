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

public class TaskProperties{


	public void properties(TaskFieldData taskProperties)  throws Exception{

		Actions action = new Actions(Browser.driver());
		WebElement task = Pages.Designer().getTask(taskProperties.taskName);
		action.contextClick(task).perform();

		WebElement property = Browser.driver().findElement(By.xpath("//div[2]/div[7]/div[2]"));

		if(property.getText().equals("Properties"))
		{
			property.click();
		}

		definition(taskProperties);
		assignmentRules(taskProperties);
		timingControl(taskProperties);
		permissions(taskProperties);
		caseLabels(taskProperties);
		notifications(taskProperties);
		save();
	}

	public boolean definition(TaskFieldData taskProperties)  throws Exception{

		WebElement def = Browser.driver().findElement(By.xpath("//div[1]/div[1]/div[4]/div[1]"));
		if(def.getText().equals("Definition"))
		{
			def.click();
		}
		else
		{
			throw new Exception("The element is not found");
		}


		WebElement title = Browser.driver().findElement(By.id("form[TAS_TITLE]"));
		WebElement description = Browser.driver().findElement(By.id("form[TAS_DESCRIPTION]"));
		WebElement casePriority = Browser.driver().findElement(By.id("form[TAS_PRIORITY_VARIABLE]"));
		WebElement routingScreen = Browser.driver().findElement(By.id("form[TAS_DERIVATION_SCREEN_TPL]"));
		WebElement starting = Browser.driver().findElement(By.id("form[TAS_START]"));

		title.sendKeys(taskProperties.title);
		description.sendKeys(taskProperties.description);
		casePriority.sendKeys(taskProperties.casePriority);
		Select droplist = new Select(routingScreen);
		droplist.selectByVisibleText(taskProperties.routingScreenTemplate);
		if(taskProperties.startingTask==true){
			starting.click();
		}
		return true;

	}

	public boolean assignmentRules(TaskFieldData taskProperties)  throws Exception{
		WebElement assign = Browser.driver().findElement(By.xpath("//div[1]/div[1]/div[4]/div[2]"));
		if(assign.getText().equals("Assignment rules"))
		{
			assign.click();
		}
		else
		{
			throw new Exception("The element is not found");
		}
		if(taskProperties.caseAssignedBy=="")
		{
			taskProperties.caseAssignedBy = "BALANCED";
		}
		WebElement assignment = Browser.driver().findElement(By.id("form[TAS_ASSIGN_TYPE]["+taskProperties.caseAssignedBy+"]"));
		assignment.click();

		return true;
	}

	public void timingControl(TaskFieldData taskProperties)  throws Exception{
		WebElement timing = Browser.driver().findElement(By.xpath("//div[1]/div[1]/div[4]/div[3]"));
		if(timing.getText().equals("Timing control"))
		{
			timing.click();
		}		
		WebElement allowUser = Browser.driver().findElement(By.id("form[TAS_TRANSFER_FLY]"));
		WebElement duration = Browser.driver().findElement(By.id("form[TAS_DURATION]"));
		WebElement tUnit = Browser.driver().findElement(By.id("form[TAS_TIMEUNIT]"));
		WebElement countD = Browser.driver().findElement(By.id("form[TAS_TYPE_DAY]"));
		WebElement clndr = Browser.driver().findElement(By.id("form[TAS_CALENDAR]"));
		if(taskProperties.allowUserDefined==true)
		{
			allowUser.click();
			duration.sendKeys(taskProperties.taskDuration);
			Select droplist = new Select(tUnit);
			droplist.selectByVisibleText(taskProperties.timeUnit);
			Select droplist2 = new Select(countD);
			droplist2.selectByVisibleText(taskProperties.countDays);
			Select droplist3 = new Select(clndr);
			droplist3.selectByVisibleText(taskProperties.calendar);

		}
	}

	public void permissions(TaskFieldData taskProperties)  throws Exception{
		WebElement perm = Browser.driver().findElement(By.xpath("//div[1]/div[1]/div[4]/div[4]"));
		if(perm.getText().equals("Permissions"))
		{
			perm.click();
		}
		WebElement allowArb = Browser.driver().findElement(By.id("form[TAS_TYPE]"));

		if(taskProperties.allowArbitrary==true)
			allowArb.click();

	}

	public void caseLabels(TaskFieldData taskProperties)  throws Exception{
		WebElement caselbl = Browser.driver().findElement(By.xpath("//div[1]/div[1]/div[4]/div[5]"));
		if(caselbl.getText().equals("Case Labels"))
		{
			caselbl.click();
		}
		WebElement cTitle = Browser.driver().findElement(By.id("form[TAS_DEF_TITLE]"));
		WebElement cDescription = Browser.driver().findElement(By.id("form[TAS_DEF_DESCRIPTION]"));
		cTitle.sendKeys(taskProperties.caseTitle);
		cDescription.sendKeys(taskProperties.caseDescription);
	}

	public void notifications(TaskFieldData taskProperties)  throws Exception{
		WebElement notif = Browser.driver().findElement(By.xpath("//div[1]/div[1]/div[4]/div[6]"));
		if(notif.getText().equals("Notifications"))
		{
			notif.click();
		}
		WebElement afterRoutDer =Browser.driver().findElement(By.id("form[SEND_EMAIL]"));
		WebElement sbjct = Browser.driver().findElement(By.id("form[TAS_DEF_SUBJECT_MESSAGE]"));
		WebElement content = Browser.driver().findElement(By.id("form[TAS_DEF_MESSAGE_TYPE]"));
		WebElement msg = Browser.driver().findElement(By.id("form[TAS_DEF_MESSAGE]"));
		if(taskProperties.afterRouting==true){
			afterRoutDer.click();
			sbjct.sendKeys(taskProperties.subject);
			Select droplist = new Select(content);
			droplist.selectByVisibleText(taskProperties.contentType);
			msg.sendKeys(taskProperties.message);
		}

	}

	public void save()  throws Exception{
		WebElement btnSave = Browser.driver().findElement(By.xpath("//div[1]/div[1]/div[6]/div[1]/input[1]"));
		btnSave.click();
		Thread.sleep(1000);
		WebElement btnAccept = Browser.driver().findElement(By.xpath("//div[1]/div[1]/div[6]/div[1]/input"));
		btnAccept.click();
		
	}

}