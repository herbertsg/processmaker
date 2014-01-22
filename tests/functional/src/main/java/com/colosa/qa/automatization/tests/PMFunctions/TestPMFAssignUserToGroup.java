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

public class TestPMFAssignUserToGroup extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestPMFAssignUserToGroup(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executePMFAssignUserToGroup() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("PMFAssignUserToGroup (Assign to group)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("user", "zachary");
		form.setFieldValue("group", "Accounting");
		form.setFieldValue("send", "");
		Assert.assertEquals("User assigned", Value.getValue(browserInstance, FieldKeyType.ID, "form[verifyuser]"));
		Logger.addLog(Value.getValue(browserInstance, FieldKeyType.ID, "form[verifyuser]"));
		Assert.assertEquals("1", Value.getValue(browserInstance, FieldKeyType.ID, "form[statusFunction]"));
		form.setFieldValue("continue", "");
	    pages.AssignTask().pressContinueButton();
		form.outDynaform();
		pages.Main().logout();
		//Open report task for check
		pages.gotoDefaultUrl();
		pages.Login().loginUser("zachary", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoUnassigned();
		Assert.assertTrue("The case does not exist in Unassigned", pages.Home().existCase(caseNum));
		pages.Home().gotoInbox().openCase(caseNum);

        form.intoDynaform();
		form.setFieldValue("BTN_CATCH", "");
		form.outDynaform();
		form.intoDynaform();
	    pages.AssignTask().pressContinueButton();
		form.outDynaform();
		pages.Main().logout();

    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}