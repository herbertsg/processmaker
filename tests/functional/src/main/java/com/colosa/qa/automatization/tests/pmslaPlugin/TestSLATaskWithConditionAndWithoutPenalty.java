package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import java.util.*;
import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLATaskWithConditionAndWithoutPenalty{

	protected static int numCase;
	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		numCase = Pages.Home().startCase("SLA Process - Task with Condition and without Penalty (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("elegir", "Segundo");
		Pages.DynaformExecution().setFieldValue("guardar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		
		Pages.Home().openCase(numCase);
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("guardar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.Home().openCase(numCase);
		Pages.DynaformExecution().intoDynaform();
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.CronExecute().execute("workflow");
		Pages.DynaformExecution().sleep(5000);

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
		Pages.Home().gotoReports();

		Pages.PmslaReport().generateReport("SLA-Task with condition and without Penalty", "All", "All", "All");
		Pages.PmslaReport().displayCases("SLA-Task with condition and without Penalty");
		String[] caseInfo = Pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo[5], "In progress");
		Pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo = Pages.PmslaReport().getTaskInfo("Task 2");
		Assert.assertEquals(taskInfo[5], "OPEN");

        Pages.InputDocProcess().switchToDefault();
        Pages.Main().logout();

	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}