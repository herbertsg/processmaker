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

public class TestSLAMultipleTasksWithConditionAndPenalty{

	protected static int numCase;
	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		numCase = Pages.Home().startCase("SLA Process - Multiple Task with Condition and Penalty (Requerimiento de Personal)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("eleccion", "first");
		Pages.DynaformExecution().setFieldValue("save", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.Home().openCase(numCase);
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("save", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.Home().openCase(numCase);
		Pages.DynaformExecution().intoDynaform();
		Pages.OutputDocProcess().downloadDocFile();
		//Pages.OutputDocProcess().downloadPdfFile();
		Pages.DynaformExecution().setFieldValue("NEXT_STEP", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.Home().openCase(numCase);
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("finalizacion", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.CronExecute().execute("workflow");
		Pages.DynaformExecution().sleep(5000);

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
		Pages.Home().gotoReports();

		Pages.PmslaReport().generateReport("SLA Process - Multiple Task with Condiion and Pen", "All", "All", "All");
		Pages.PmslaReport().displayCases("SLA Process - Multiple Task with Condiion and Pen");
		String[] caseInfo = Pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo[5], "In progress");
		Pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo = Pages.PmslaReport().getTaskInfo("Documento Generado");
		Assert.assertEquals(taskInfo[5], "OPEN");

		Pages.Main().logout();

	
	}
//    @After
//    public void cleanup(){
//        Browser.close();
//    }

}