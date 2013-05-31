package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLAProcessEntireProcessWithConditionAndPenalty extends com.colosa.qa.automatization.tests.common.Test{

	protected static int numCase;

    public TestSLAProcessEntireProcessWithConditionAndPenalty(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		numCase = pages.Home().gotoNewCase().startCase("SLA Process - Entire Process with condition and Penalty (Solicitud de Solucion de Ticket)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("eleccion", "Immediate");
		pages.DynaformExecution().setFieldValue("guadar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().openCase(numCase);
		pages.DynaformExecution().intoDynaform();
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");
		pages.DynaformExecution().sleep(5000);

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoReports();

		pages.PmslaReport().generateReport("SLA-Entire Process with condition and Penalty", "All", "All", "All");
		pages.PmslaReport().displayCases("SLA-Entire Process with condition and Penalty");
		String[] caseInfo = pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo[5], "In progress");
		pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo = pages.PmslaReport().getTaskInfo("Solucion");
		Assert.assertEquals(taskInfo[5], "OPEN");

        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

	}
    @After
    public void cleanup(){
        browserInstance.quit();
    }
}