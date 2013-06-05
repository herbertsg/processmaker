package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLAProcessWithManySlas extends com.colosa.qa.automatization.tests.common.Test{

	protected static int numCase;
	protected static String seleccion = "Ir a la Tarea 3";

    public TestSLAProcessWithManySlas(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		numCase = pages.Home().gotoNewCase().startCase("SLA Process with many sla's (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("elegir", "segundo");
		pages.DynaformExecution().setFieldValue("guardar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().openCase(numCase);
		pages.DynaformExecution().intoDynaform();

		pages.DynaformExecution().setFieldValue("seleccion", seleccion);
		pages.DynaformExecution().setFieldValue("guardar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");
		pages.DynaformExecution().sleep(5000);

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().openCase(numCase);
		pages.DynaformExecution().intoDynaform();
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().openCase(numCase);
		pages.DynaformExecution().intoDynaform();
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		
		pages.CronExecute().execute("workflow");

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoReports();

		pages.PmslaReport().generateReport("SLA-Entire Process-with many SLA's", "All", "All", "All");
		pages.PmslaReport().displayCases("SLA-Entire Process-with many SLA's");
		String[] caseInfo = pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo[5], "In progress");
		pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo = pages.PmslaReport().getTaskInfo("Task 3");
		Assert.assertEquals(taskInfo[5], "OPEN");

		pages.Main().goHome();
		pages.Home().gotoReports();

		pages.PmslaReport().generateReport("SLA-Task 3 Process-with many SLA's", "All", "All", "All");
		pages.PmslaReport().displayCases("SLA-Task 3 Process-with many SLA's");
		String[] caseInfo2 = pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo2[5], "In progress");
		pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo2 = pages.PmslaReport().getTaskInfo("Task 3");
		Assert.assertEquals(taskInfo2[5], "OPEN");

		pages.Main().goHome();
		pages.Home().gotoReports();

		pages.PmslaReport().generateReport("SLA-Multiple Task 1-5 Process-with many SLA's", "All", "All", "All");
		pages.PmslaReport().displayCases("SLA-Multiple Task 1-5 Process-with many SLA's");
		String[] caseInfo3 = pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo3[5], "In progress");
		pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo3 = pages.PmslaReport().getTaskInfo("Task 3");
		Assert.assertEquals(taskInfo3[5], "OPEN");

        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

	}
    @After
    public void cleanup(){
        browserInstance.quit();
    }

}