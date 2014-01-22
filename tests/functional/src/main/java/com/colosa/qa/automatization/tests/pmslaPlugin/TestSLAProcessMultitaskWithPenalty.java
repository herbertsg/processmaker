package com.colosa.qa.automatization.tests.pmslaPlugin;

import com.colosa.qa.automatization.pages.DynaformExecution;
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

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("SLA Process - Multitask with penalty (Solicitud)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("nombre", "John");
		form.setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().gotoInbox().openCase(caseNum);

        form.intoDynaform();
		form.setFieldValue("nombres", "John Due");
		form.setFieldValue("ci", "3333333");
		form.setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");
		form.sleep(5000);

		pages.gotoDefaultUrl();
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