package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFTaskCase extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestPMFTaskCase(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testPMFTaskCase() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().startCase("PMFTaskCase (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
		pages.DynaformExecution().setFieldValue("send", "");
		pages.DynaformExecution().setFieldValue("TASKS][2][USR_UID", "Swan, William");
	    pages.InputDocProcess().continuebtn();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Open report task for check
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		int numListCases = Integer.parseInt(Value.getValue(browserInstance, FieldKeyType.ID, "form[longTasksCases]"));
		for(int i=1; i<numListCases; i++){
			Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[taskList]["+ i + "][guid]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[tasksQuery][" + i + "][TAS_UID]"));
        }
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("continue", "");
		//int numTaskList = Integer.parseInt(Value.getValue(FieldKeyType.ID, "form[longTaskList]"));
		//for(int i=1; i<numTaskList; i++){
		//	Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[userTaskList][" + i + "][guid]"), Value.getValue(FieldKeyType.ID, "form[gridTaskListQuery][" + i + "][TAS_UID]"));	
       // }
	    pages.InputDocProcess().continuebtn();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/


}