package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFProcessList extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestPMFProcessList(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testPMFProcessList() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("PMFProcessList (Process list)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		int numProcessList = Integer.parseInt(Value.getValue(browserInstance, FieldKeyType.ID, "form[counter]"));
		for(int i=1; i<numProcessList; i++){
			Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[gridProcess][" + i + "][guid]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[processQuery][" + i + "][PRO_UID]"));
        }
		//form.setFieldValue("send", "");
	    //pages.AssignTask().pressContinueButton();
		//form.outDynaform();
		//pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}