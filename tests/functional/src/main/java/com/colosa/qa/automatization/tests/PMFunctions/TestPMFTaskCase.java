package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
import com.colosa.qa.automatization.pages.DynaformExecution;
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
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("name", "Charles Puyol");
		form.setFieldValue("amount", "3000");
        form.clickButton("send");

        pages.AssignTask().selectManualAssignedUser(2, "Swan, William");
	    pages.AssignTask().pressContinueButton();

    	pages.Home().gotoInbox().openCase(caseNum);

        DynaformExecution form2 = pages.DynaformExecution();
        form2.intoDynaform();
		//int numListCases = Integer.parseInt(Value.getValue(browserInstance, FieldKeyType.ID, "form[longTasksCases]"));
        int numListCases = Integer.parseInt(form2.getFieldValue("longTasksCases"));
		for(int i=1; i<numListCases; i++){
			//Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[taskList]["+ i + "][guid]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[tasksQuery][" + i + "][TAS_UID]"));
            Assert.assertEquals(form2.getGridFieldValue("taskList",i,"guid"),
                    form2.getGridFieldValue("tasksQuery", i, "TAS_UID"));
        }
		//form.clickButton("continue");

	    //pages.AssignTask().pressContinueButton();
		//form.outDynaform();
		//pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}