package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFTaskCase extends com.colosa.qa.automatization.tests.common.Test{

	protected int caseNum;

    public TestPMFTaskCase(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testPMFTaskCase() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("PMFTaskCase (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
        pages.DynaformExecution().clickButton("send");

        pages.AssignTask().selectManualAssignedUser(2, "Swan, William");
	    pages.AssignTask().pressContinueButton();

    	pages.Home().gotoInbox().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		//int numListCases = Integer.parseInt(Value.getValue(browserInstance, FieldKeyType.ID, "form[longTasksCases]"));
        int numListCases = Integer.parseInt(pages.DynaformExecution().getFieldValue("longTasksCases"));
		for(int i=1; i<numListCases; i++){
			//Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[taskList]["+ i + "][guid]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[tasksQuery][" + i + "][TAS_UID]"));
            Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("taskList",i,"guid"),
                    pages.DynaformExecution().getGridFieldValue("tasksQuery", i, "TAS_UID"));
        }
		//pages.DynaformExecution().clickButton("continue");

	    //pages.AssignTask().pressContinueButton();
		//pages.DynaformExecution().outDynaform();
		//pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}