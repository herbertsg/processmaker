package com.colosa.qa.automatization.tests.pmslaPlugin;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLAProcessByParalellEvaluation extends com.colosa.qa.automatization.tests.common.Test{

	protected static int numCase;

    public TestSLAProcessByParalellEvaluation(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		numCase = pages.Home().gotoNewCase().startCase("SLA Process Paralell by Evaluation (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("nombre", "Roberto Hernandez");
		form.setFieldValue("seleccion", "tarea 3");
		form.setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoInbox().openCase(numCase);

        form.intoDynaform();
		form.setFieldValue("ci", "15648796");
		form.setFieldValue("verificar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().gotoInbox().openCase(numCase);

        form.intoDynaform();
		form.setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().gotoInbox().openCase(numCase);

        form.intoDynaform();
		form.setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");

		pages.gotoDefaultUrl();
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
    @After
    public void cleanup(){
        browserInstance.quit();
    }
}