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

public class TestPMFProcessList{

	protected static int caseNum;

	@Test
	public void testPMFProcessList() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("PMFProcessList (Process list)");
		Pages.DynaformExecution().intoDynaform();
		int numProcessList = Integer.parseInt(Value.getValue(FieldKeyType.ID, "form[counter]"));
		for(int i=1; i<numProcessList; i++){
			Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[gridProcess][" + i + "][guid]"), Value.getValue(FieldKeyType.ID, "form[processQuery][" + i + "][PRO_UID]"));	
        }
		Pages.DynaformExecution().setFieldValue("send", "");
	    Pages.InputDocProcess().continuebtn();
		Pages.DynaformExecution().outDynaform();
		Pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/


}