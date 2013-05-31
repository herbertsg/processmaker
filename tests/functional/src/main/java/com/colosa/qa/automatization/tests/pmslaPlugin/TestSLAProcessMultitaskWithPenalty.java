package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLAProcessMultitaskWithPenalty extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestSLAProcessMultitaskWithPenalty(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeSLAProcessMultitaskWithPenalty() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("SLA Process - Multitask with penalty (Solicitud)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("nombre", "John");
		pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("nombres", "John Due");
		pages.DynaformExecution().setFieldValue("ci", "3333333");
		pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");
		pages.DynaformExecution().sleep(5000);

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoReports();

		pages.PmslaReport().generateReport("SLA - multitask with penality", "All", "All", "All");
		pages.PmslaReport().displayCases("SLA - multitask with penality");
		String[] caseInfo = pages.PmslaReport().getCaseInfo(caseNum);
		Assert.assertEquals(caseInfo[5], "In progress");
		pages.PmslaReport().displayTasks(caseNum);
		String[] taskInfo = pages.PmslaReport().getTaskInfo("Solicitud");
		Assert.assertEquals(taskInfo[5], "OPEN");
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}