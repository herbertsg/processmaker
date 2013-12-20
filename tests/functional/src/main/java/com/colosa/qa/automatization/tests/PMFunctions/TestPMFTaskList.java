package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFTaskList extends com.colosa.qa.automatization.tests.common.Test{

	protected int caseNum;

    public TestPMFTaskList(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testPMFTaskList() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("PMFTaskList (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
		pages.DynaformExecution().setFieldValue("send", "");
		pages.DynaformExecution().setFieldValue("TASKS][2][USR_UID", "Swan, William");
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Open report task for check
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().gotoInbox().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		int numTaskList = Integer.parseInt(Value.getValue(browserInstance, FieldKeyType.ID, "form[longTaskList]"));
		for(int i=1; i<numTaskList; i++){
			Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userTaskList][" + i + "][guid]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[gridTaskListQuery][" + i + "][TAS_UID]"));
        }
		pages.DynaformExecution().setFieldValue("continue", "");
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();

	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}