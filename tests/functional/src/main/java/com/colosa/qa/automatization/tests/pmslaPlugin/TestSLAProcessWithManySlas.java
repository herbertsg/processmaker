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

public class TestSLAProcessWithManySlas{

	protected static int numCase;
	protected static String seleccion = "Ir a la Tarea 3";
	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		numCase = Pages.Home().startCase("SLA Process with many sla's (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("elegir", "segundo");
		Pages.DynaformExecution().setFieldValue("guardar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.Home().openCase(numCase);
		Pages.DynaformExecution().intoDynaform();

		Pages.DynaformExecution().setFieldValue("seleccion", seleccion);
		Pages.DynaformExecution().setFieldValue("guardar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.CronExecute().execute("workflow");
		Pages.DynaformExecution().sleep(5000);

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
		Pages.Home().openCase(numCase);
		Pages.DynaformExecution().intoDynaform();
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.Home().openCase(numCase);
		Pages.DynaformExecution().intoDynaform();
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());
		
		Pages.CronExecute().execute("workflow");

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
		Pages.Home().gotoReports();

		Pages.PmslaReport().generateReport("SLA-Entire Process-with many SLA's", "All", "All", "All");
		Pages.PmslaReport().displayCases("SLA-Entire Process-with many SLA's");
		String[] caseInfo = Pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo[5], "In progress");
		Pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo = Pages.PmslaReport().getTaskInfo("Task 3");
		Assert.assertEquals(taskInfo[5], "OPEN");

		Pages.Main().goHome();
		Pages.Home().gotoReports();

		Pages.PmslaReport().generateReport("SLA-Task 3 Process-with many SLA's", "All", "All", "All");
		Pages.PmslaReport().displayCases("SLA-Task 3 Process-with many SLA's");
		String[] caseInfo2 = Pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo2[5], "In progress");
		Pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo2 = Pages.PmslaReport().getTaskInfo("Task 3");
		Assert.assertEquals(taskInfo2[5], "OPEN");

		Pages.Main().goHome();
		Pages.Home().gotoReports();

		Pages.PmslaReport().generateReport("SLA-Multiple Task 1-5 Process-with many SLA's", "All", "All", "All");
		Pages.PmslaReport().displayCases("SLA-Multiple Task 1-5 Process-with many SLA's");
		String[] caseInfo3 = Pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo3[5], "In progress");
		Pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo3 = Pages.PmslaReport().getTaskInfo("Task 3");
		Assert.assertEquals(taskInfo3[5], "OPEN");

		Pages.Main().logout();

	}
    @After
    public void cleanup(){
        Browser.close();
    }

}