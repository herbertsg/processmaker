package com.colosa.qa.automatization.tests.TestCaseTracker;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestCaseTracker{

	protected static int caseNum;
	protected static String pin;

	@Test
	public void executeProcess() throws FileNotFoundException, IOException, Exception{
		//Init case
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("Process Case Tracker - Process Map (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("nombre", "Ian");
		pin = Value.getValue(FieldKeyType.ID, "form[pin]");
		Pages.DynaformExecution().setFieldValue("save", "");
	    Pages.InputDocProcess().continuebtn();
		Pages.Main().goHome();
		Pages.InputDocProcess().switchToDefault();
		Assert.assertTrue("The case does not exist in Inbox", Pages.Home().existCase(caseNum));
		Pages.Home().openCase(caseNum);
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("BTN_SUBMIT", "");
		Pages.InputDocProcess().switchToDefault();
		Pages.CaseTracker().goTo();
		Pages.DynaformExecution().setFieldValue("CASE", String.valueOf(caseNum));
		Pages.DynaformExecution().setFieldValue("PIN", pin);
		Pages.DynaformExecution().setFieldValue("BSUBMIT", "");
		System.out.println(Pages.Designer().getTaskColorStatus("Task 1"));
		Assert.assertEquals("Completed Task", Pages.Designer().getTaskColorStatus("Task 1"));
		System.out.println(Pages.Designer().getTaskColorStatus("Task 2"));
		Assert.assertEquals("Pending Task / Not Executed", Pages.Designer().getTaskColorStatus("Task 2"));
		System.out.println(Pages.Designer().getTaskColorStatus("Task 3"));
		Assert.assertEquals("Pending Task / Not Executed", Pages.Designer().getTaskColorStatus("Task 3"));
		Pages.CaseTracker().goFormDocs();
		Pages.CaseTracker().openFirstForm();
		Assert.assertEquals("Ian", Value.getValue(FieldKeyType.ID, "form[nombre]"));
		Pages.CaseTracker().goHistory();
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
		String fecha = sdf.format(date);
		Assert.assertEquals(fecha, Value.getValue(FieldKeyType.XPATH, "//*[@id='publisherContent[1]']/table/tbody/tr/td/div[2]/table/tbody/tr/td/table[2]/tbody/tr[2]/td[8]/input"));

		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}