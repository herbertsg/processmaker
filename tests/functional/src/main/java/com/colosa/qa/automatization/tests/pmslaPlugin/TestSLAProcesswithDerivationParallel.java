package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLAProcesswithDerivationParallel extends com.colosa.qa.automatization.tests.common.Test{

	protected static int numCase;

    public TestSLAProcesswithDerivationParallel(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		numCase = pages.Home().gotoNewCase().startCase("SLA Process with Derivation Parallel (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("elegir", "segundo");
		pages.DynaformExecution().setFieldValue("guardar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().gotoInbox().openCase(numCase);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("guardar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().gotoInbox().openCase(numCase);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("guardar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().gotoInbox().openCase(numCase);
		pages.DynaformExecution().intoDynaform();
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");
		pages.DynaformExecution().sleep(5000);

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoReports();

		pages.PmslaReport().generateReport("SLA-Entire Process with Derivation Pararell", "All", "All", "All");
		pages.PmslaReport().displayCases("SLA-Entire Process with Derivation Pararell");
		String[] caseInfo = pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo[5], "In progress");		
		pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo = pages.PmslaReport().getTaskInfo("Task 3");
		Assert.assertEquals(taskInfo[5], "OPEN");

        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}