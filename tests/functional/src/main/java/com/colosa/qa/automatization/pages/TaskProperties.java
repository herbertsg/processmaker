package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.TaskFieldData;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TaskProperties extends Page{

    public TaskProperties(BrowserInstance browser) throws Exception {
        super(browser);

        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void properties(TaskFieldData taskProperties)  throws Exception{

		Actions action = new Actions(browser.getInstanceDriver());
        Pages pages = new Pages(browser);
		WebElement task = pages.Designer().getTask(taskProperties.taskName);
		action.contextClick(task).perform();

		WebElement property = browser.findElement("taskProperties.webElement.properties");

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

		WebElement def = browser.findElement("taskProperties.webElement.definition");
		if(def.getText().equals("Definition"))
		{
			def.click();
		}
		else
		{
			throw new Exception("The element is not found");
		}


		WebElement title = browser.findElement("taskProperties.webElement.taskTitle");
		WebElement description = browser.findElement("taskProperties.webElement.taskDescription");
		WebElement casePriority = browser.findElement("taskProperties.webElement.taskPriority");
		WebElement routingScreen = browser.findElement("taskProperties.webElement.taskDerivation");
		WebElement starting = browser.findElement("taskProperties.webElement.taskStart");

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
		WebElement assign = browser.findElement("taskProperties.webElement.assignmentRules");
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
		WebElement assignment = browser.findElementById("form[TAS_ASSIGN_TYPE][" + taskProperties.caseAssignedBy + "]");
		assignment.click();

		return true;
	}

	public void timingControl(TaskFieldData taskProperties)  throws Exception{
		WebElement timing = browser.findElement("taskProperties.webElement.timingControl");
		if(timing.getText().equals("Timing control"))
		{
			timing.click();
		}		
		WebElement allowUser = browser.findElement("taskProperties.webElement.allowUser");
		WebElement duration = browser.findElement("taskProperties.webElement.taskDuration");
		WebElement tUnit = browser.findElement("taskProperties.webElement.timeUnit");
		WebElement countD = browser.findElement("taskProperties.webElement.typeDay");
		WebElement clndr = browser.findElement("taskProperties.webElement.calendar");
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
		WebElement perm = browser.findElement("taskProperties.webElement.permisions");
		if(perm.getText().equals("Permissions"))
		{
			perm.click();
		}
		WebElement allowArb = browser.findElement("taskProperties.webElement.allowArbitrary");

		if(taskProperties.allowArbitrary==true)
			allowArb.click();

	}

	public void caseLabels(TaskFieldData taskProperties)  throws Exception{
		WebElement caselbl = browser.findElement("taskProperties.webElement.caseLabels");
		if(caselbl.getText().equals("Case Labels"))
		{
			caselbl.click();
		}
		WebElement cTitle = browser.findElement("taskProperties.webElement.caseTitle");
		WebElement cDescription = browser.findElement("taskProperties.webElement.caseDescription");
		cTitle.sendKeys(taskProperties.caseTitle);
		cDescription.sendKeys(taskProperties.caseDescription);
	}

	public void notifications(TaskFieldData taskProperties)  throws Exception{
		WebElement notif = browser.findElement("taskProperties.webElement.notifications");
		if(notif.getText().equals("Notifications"))
		{
			notif.click();
		}
		WebElement afterRoutDer =browser.findElement("taskProperties.webElement.afterRoute");
		WebElement sbjct = browser.findElement("taskProperties.webElement.subject");
		WebElement content = browser.findElement("taskProperties.webElement.content");
		WebElement msg = browser.findElement("taskProperties.webElement.message");
		if(taskProperties.afterRouting==true){
			afterRoutDer.click();
			sbjct.sendKeys(taskProperties.subject);
			Select droplist = new Select(content);
			droplist.selectByVisibleText(taskProperties.contentType);
			msg.sendKeys(taskProperties.message);
		}

	}

	public void save()  throws Exception{
		WebElement btnSave = browser.findElement("taskProperties.webElement.btnSave");
		btnSave.click();
		Thread.sleep(1000);
		WebElement btnAccept = browser.findElement("taskProperties.webElement.btnAccept");
		btnAccept.click();
		
	}

}