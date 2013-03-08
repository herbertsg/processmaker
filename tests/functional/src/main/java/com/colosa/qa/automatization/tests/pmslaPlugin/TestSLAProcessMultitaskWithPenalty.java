package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public class TestSLAProcessMultitaskWithPenalty{

	protected static int caseNum;

	@Test
	public void executeSLAProcessMultitaskWithPenalty() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("SLA Process - Multitask with penalty (Solicitud)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("nombre", "John");
		Pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.Home().openCase(caseNum);
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("nombres", "John Due");
		Pages.DynaformExecution().setFieldValue("ci", "3333333");				
		Pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.CronExecute().execute("workflow");
		Pages.DynaformExecution().sleep(5000);

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
		Pages.Home().gotoReports();

		Pages.PmslaReport().generateReport("SLA - multitask with penality", "All", "All", "All");
		Pages.PmslaReport().displayCases("SLA - multitask with penality");
		String[] caseInfo = Pages.PmslaReport().getCaseInfo(caseNum);
		Assert.assertEquals(caseInfo[5], "In progress");
		Pages.PmslaReport().displayTasks(caseNum);
		String[] taskInfo = Pages.PmslaReport().getTaskInfo("Solicitud");
		Assert.assertEquals(taskInfo[5], "OPEN");


}

//    @After
//    public void cleanup(){
//        Browser.close();
//    }

}