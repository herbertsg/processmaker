package com.colosa.qa.automatization.tests.TestCaseTracker;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestCaseTracker extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;
	protected static String pin;

    public TestCaseTracker(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeProcess() throws FileNotFoundException, IOException, Exception{
		//Init case
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Process Case Tracker - Process Map (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("nombre", "Ian");
		pin = Value.getValue(browserInstance, FieldKeyType.ID, "form[pin]");
		pages.DynaformExecution().setFieldValue("save", "");
	    pages.AssignTask().pressContinueButton();
		pages.Main().goHome();
		pages.InputDocProcess().switchToDefault();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("BTN_SUBMIT", "");
		pages.InputDocProcess().switchToDefault();
		pages.CaseTracker().goTo("workflow");
		pages.DynaformExecution().setFieldValue("CASE", String.valueOf(caseNum));
		pages.DynaformExecution().setFieldValue("PIN", pin);
		pages.DynaformExecution().setFieldValue("BSUBMIT", "");
		System.out.println(pages.Designer().getTaskColorStatus("Task 1"));
		Assert.assertEquals("Completed Task", pages.Designer().getTaskColorStatus("Task 1"));
		System.out.println(pages.Designer().getTaskColorStatus("Task 2"));
		Assert.assertEquals("Pending Task / Not Executed", pages.Designer().getTaskColorStatus("Task 2"));
		System.out.println(pages.Designer().getTaskColorStatus("Task 3"));
		Assert.assertEquals("Pending Task / Not Executed", pages.Designer().getTaskColorStatus("Task 3"));
		pages.CaseTracker().goFormDocs();
		pages.CaseTracker().openFirstForm();
		Assert.assertEquals("Ian", Value.getValue(browserInstance, FieldKeyType.ID, "form[nombre]"));
		pages.CaseTracker().goHistory();
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
		String fecha = sdf.format(date);
		Assert.assertEquals(fecha, Value.getValue(browserInstance, FieldKeyType.XPATH, "//*[@id='publisherContent[1]']/table/tbody/tr/td/div[2]/table/tbody/tr/td/table[2]/tbody/tr[2]/td[8]/input"));

		pages.InputDocProcess().switchToDefault();
		//pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}