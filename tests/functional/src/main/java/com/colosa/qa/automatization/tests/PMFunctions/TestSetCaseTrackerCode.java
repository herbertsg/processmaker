package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSetCaseTrackerCode extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;
	protected static String pin;
	protected static String code;

    public TestSetCaseTrackerCode(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeSetCaseTrackerCode() throws FileNotFoundException, IOException, Exception{
		//Init case
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().startCase("setCaseTrackerCode (Task 1)");
		pages.DynaformExecution().intoDynaform();
	    pages.InputDocProcess().continuebtn();
		pages.Main().goHome();
		pages.InputDocProcess().switchToDefault();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		//init Case tracker
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		pin = Value.getValue(browserInstance, FieldKeyType.ID, "form[pin]");
		code = Value.getValue(browserInstance, FieldKeyType.ID, "form[code]");
		pages.DynaformExecution().setFieldValue("continue", "");
		pages.DynaformExecution().intoDynaform();
	    pages.InputDocProcess().continuebtn();
		pages.InputDocProcess().switchToDefault();
		pages.CaseTracker().goTo("workflow");
		pages.DynaformExecution().setFieldValue("CASE", code);
		pages.DynaformExecution().setFieldValue("PIN", pin);
		pages.DynaformExecution().setFieldValue("BSUBMIT", "");
		System.out.println(pages.Designer().getTaskColorStatus("Task 1"));
		Assert.assertEquals("Completed Task", pages.Designer().getTaskColorStatus("Task 1"));
		System.out.println(pages.Designer().getTaskColorStatus("Task 2"));
		Assert.assertEquals("Pending Task / Not Executed", pages.Designer().getTaskColorStatus("Task 2"));

		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
	}

/*
    @After
    public void cleanup(){
        Browser.close();
    }
*/

}