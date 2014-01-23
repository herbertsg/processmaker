package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFAddCaseNote extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFAddCaseNote(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				
		//pages.Main().goDesigner();
        //pages.ProcessList().openProcess("Test PMFAddCaseNote");
        //pages.Designer().assignedPermission("Administrators","All");
        
        //pages.gotoDefaultUrl();
        //pages.Login().loginUser("admin","admin","workflow", "English");

        pages.Main().goHome();
        int casenumber = pages.Home().gotoNewCase().startCase("Test PMFAddCaseNote (Task 1)");
        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();

        form.setFieldValue("CASE_NOTE", "Test Case Note");
        form.clickButton("SUBMIT");

        String fieldCASE_NOTE = "Test Case Note";

        Logger.addLog("CASE NOTE " + fieldCASE_NOTE);

        pages.AssignTask().pressContinueButton();

        //pages.Home().gotoInbox().existCase(casenumber);
        pages.Home().gotoInbox().openCase(casenumber);
        DynaformExecution form2 = pages.DynaformExecution();
        form2.intoDynaform();

        String fieldRESULT_CASE_NOTE = form2.getFieldAttribute("RESULT_CASE_NOTE", "value");
        String fieldTEST_RESULT = form2.getFieldAttribute("TEST_RESULT", "value");

        //form.clickButton("SUBMIT");

        Assert.assertEquals("PMFAddCaseNote function return 1", fieldTEST_RESULT, "1");
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldRESULT_CASE_NOTE, fieldCASE_NOTE);

        //pages.AssignTask().pressContinueButton();

        //pages.Main().logout();
    }


    @After
    public void cleanup(){
        browserInstance.quit();
    }


}