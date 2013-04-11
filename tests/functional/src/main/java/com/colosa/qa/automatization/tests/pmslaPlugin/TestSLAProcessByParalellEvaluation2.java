package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLAProcessByParalellEvaluation2 extends com.colosa.qa.automatization.tests.common.Test{

	protected static int numCase;

    public TestSLAProcessByParalellEvaluation2(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		
		numCase = pages.Home().openFirstCase();
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("ci", "15648796");
		pages.DynaformExecution().setFieldValue("verificar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().openCase(numCase);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().openCase(numCase);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoReports();

		pages.PmslaReport().generateReport("SLA Entire Process Paralell by Evaluation with pen", "All", "All", "All");
		pages.PmslaReport().displayCases("SLA Entire Process Paralell by Evaluation with pen");
		String[] caseInfo = pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo[5], "Closed");
		Assert.assertNotNull(caseInfo[4]);
		pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo = pages.PmslaReport().getTaskInfo("Task 5");
		Assert.assertEquals(taskInfo[5], "CLOSED");

        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

	}
/*    @After
    public void cleanup(){
        Browser.close();
    }*/
}