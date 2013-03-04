package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSetCaseTrackerCode{

	protected static int caseNum;
	protected static String pin;
	protected static String code;	

	@Test
	public void executeSetCaseTrackerCode() throws FileNotFoundException, IOException, Exception{
		//Init case
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("setCaseTrackerCode (Task 1)");
		Pages.DynaformExecution().intoDynaform();
	    Pages.InputDocProcess().continuebtn();
		Pages.Main().goHome();
		Pages.InputDocProcess().switchToDefault();
		Assert.assertTrue("The case does not exist in Inbox", Pages.Home().existCase(caseNum));
		//init Case tracker
		Pages.Home().openCase(caseNum);
		Pages.DynaformExecution().intoDynaform();
		pin = Value.getValue(FieldKeyType.ID, "form[pin]");	
		code = Value.getValue(FieldKeyType.ID, "form[code]");	
		Pages.DynaformExecution().setFieldValue("continue", "");
		Pages.DynaformExecution().intoDynaform();
	    Pages.InputDocProcess().continuebtn();	
		Pages.InputDocProcess().switchToDefault();
		Pages.CaseTracker().goTo();
		Pages.DynaformExecution().setFieldValue("CASE", code);
		Pages.DynaformExecution().setFieldValue("PIN", pin);
		Pages.DynaformExecution().setFieldValue("BSUBMIT", "");
		System.out.println(Pages.Designer().getTaskColorStatus("Task 1"));
		Assert.assertEquals("Completed Task", Pages.Designer().getTaskColorStatus("Task 1"));
		System.out.println(Pages.Designer().getTaskColorStatus("Task 2"));
		Assert.assertEquals("Pending Task / Not Executed", Pages.Designer().getTaskColorStatus("Task 2"));

		Pages.DynaformExecution().outDynaform();
		Pages.Main().logout();
	}

//    @After
//    public void cleanup(){
//        Browser.close();
//    }

}