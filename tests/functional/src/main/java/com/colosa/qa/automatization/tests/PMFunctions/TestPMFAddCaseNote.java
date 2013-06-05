package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
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
				
		pages.Main().goDesigner();
        pages.ProcessList().openProcess("Test PMFAddCaseNote");
        pages.Designer().assignedPermission("Administrators","All");
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");

        pages.Main().goHome();
        int casenumber = pages.Home().gotoNewCase().startCase("Test PMFAddCaseNote (Task 1)");
        pages.DynaformExecution().intoDynaform();

        pages.DynaformExecution().setFieldValue("CASE_NOTE", "Test Case Note");
        pages.DynaformExecution().clickButton("SUBMIT");
        

        String fieldCASE_NOTE = "Test Case Note";

        System.out.println("CASE NOTE "+fieldCASE_NOTE);

        pages.AssignTask().pressContinueButton();

        //pages.Home().gotoInbox().existCase(casenumber);
        pages.Home().gotoInbox().openCase(casenumber);
        pages.DynaformExecution().intoDynaform();

        String fieldRESULT_CASE_NOTE = pages.DynaformExecution().getFieldProperty("RESULT_CASE_NOTE","value");
        String fieldTEST_RESULT = pages.DynaformExecution().getFieldProperty("TEST_RESULT","value");

        pages.DynaformExecution().clickButton("SUBMIT");
		    		    
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldRESULT_CASE_NOTE, fieldCASE_NOTE);

        pages.AssignTask().pressContinueButton();

        pages.Main().logout();
    }


    @After
    public void cleanup(){
        browserInstance.quit();
    }


}