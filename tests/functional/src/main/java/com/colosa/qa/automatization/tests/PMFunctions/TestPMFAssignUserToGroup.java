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

public class TestPMFAssignUserToGroup{

	protected static int caseNum;

	@Test
	public void executePMFAssignUserToGroup() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("PMFAssignUserToGroup (Assign to group)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("user", "zachary");
		Pages.DynaformExecution().setFieldValue("group", "Accounting");
		Pages.DynaformExecution().setFieldValue("send", "");
		Assert.assertEquals("User assigned", Value.getValue(FieldKeyType.ID, "form[verifyuser]"));
		System.out.println(Value.getValue(FieldKeyType.ID, "form[verifyuser]"));
		Assert.assertEquals("1", Value.getValue(FieldKeyType.ID, "form[statusFunction]"));		
		Pages.DynaformExecution().setFieldValue("continue", "");
	    Pages.InputDocProcess().continuebtn();
		Pages.DynaformExecution().outDynaform();
		Pages.Main().logout();
		//Open report task for check
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("zachary", "sample", "");				
		Pages.Main().goHome();	
		Pages.Home().gotoUnassigned();
		Assert.assertTrue("The case does not exist in Unassigned", Pages.Home().existCase(caseNum));
		Pages.Home().openCase(caseNum);
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("BTN_CATCH", "");
		Pages.DynaformExecution().outDynaform();
		Pages.DynaformExecution().intoDynaform();
	    Pages.InputDocProcess().continuebtn();
		Pages.DynaformExecution().outDynaform();
		Pages.Main().logout();

}

    @After
    public void cleanup(){
        Browser.close();
    }

}