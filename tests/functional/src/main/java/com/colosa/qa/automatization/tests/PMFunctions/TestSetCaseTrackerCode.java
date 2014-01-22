package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.pages.DynaformExecution;
import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Logger;
import com.colosa.qa.automatization.common.Value;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSetCaseTrackerCode extends com.colosa.qa.automatization.tests.common.Test{

	protected int caseNum;
	protected String pin;
	protected String code;

    public TestSetCaseTrackerCode(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeSetCaseTrackerCode() throws FileNotFoundException, IOException, Exception{
		//Init case
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("setCaseTrackerCode (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
	    pages.AssignTask().pressContinueButton();
		pages.Main().goHome();
		pages.InputDocProcess().switchToDefault();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		//init Case tracker
		pages.Home().gotoInbox().openCase(caseNum);
		DynaformExecution form2 = pages.DynaformExecution();
        form2.intoDynaform();
		pin = Value.getValue(browserInstance, FieldKeyType.ID, "form[pin]");
		code = Value.getValue(browserInstance, FieldKeyType.ID, "form[code]");
		form.setFieldValue("continue", "");
		form.intoDynaform();
	    pages.AssignTask().pressContinueButton();
		pages.InputDocProcess().switchToDefault();
		pages.CaseTracker().goTo("workflow");
		form.setFieldValue("CASE", code);
		form.setFieldValue("PIN", pin);
		form.setFieldValue("BSUBMIT", "");
		Logger.addLog(pages.Designer().getTaskColorStatus("Task 1"));
		Assert.assertEquals("Completed Task", pages.Designer().getTaskColorStatus("Task 1"));
		Logger.addLog(pages.Designer().getTaskColorStatus("Task 2"));
		Assert.assertEquals("Pending Task / Not Executed", pages.Designer().getTaskColorStatus("Task 2"));

		form.outDynaform();
		pages.Main().logout();
	}


    @After
    public void cleanup(){
        browserInstance.quit();
    }


}