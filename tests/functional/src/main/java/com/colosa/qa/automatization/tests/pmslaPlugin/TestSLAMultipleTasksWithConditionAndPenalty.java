package com.colosa.qa.automatization.tests.pmslaPlugin;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLAMultipleTasksWithConditionAndPenalty extends com.colosa.qa.automatization.tests.common.Test{

	protected static int numCase;

    public TestSLAMultipleTasksWithConditionAndPenalty(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		numCase = pages.Home().gotoNewCase().startCase("SLA Process - Multiple Task with Condition and Penalty (Requerimiento de Personal)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("eleccion", "first");
		form.setFieldValue("save", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().gotoInbox().openCase(numCase);

        form.intoDynaform();
		form.setFieldValue("save", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().gotoInbox().openCase(numCase);

        form.intoDynaform();
		pages.OutputDocProcess().downloadDocFile();
		//pages.OutputDocProcess().downloadPdfFile();
		form.setFieldValue("NEXT_STEP", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().gotoInbox().openCase(numCase);

        form.intoDynaform();
		form.setFieldValue("finalizacion", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");
		form.sleep(5000);

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoReports();

		pages.PmslaReport().generateReport("SLA Process - Multiple Task with Condiion and Pen", "All", "All", "All");
		pages.PmslaReport().displayCases("SLA Process - Multiple Task with Condiion and Pen");
		String[] caseInfo = pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo[5], "In progress");
		pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo = pages.PmslaReport().getTaskInfo("Documento Generado");
		Assert.assertEquals(taskInfo[5], "OPEN");

        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

	
	}
    @After
    public void cleanup(){
        browserInstance.quit();
    }

}