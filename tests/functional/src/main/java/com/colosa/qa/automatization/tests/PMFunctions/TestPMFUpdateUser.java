package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFUpdateUser extends com.colosa.qa.automatization.tests.common.Test{

		protected static int caseNum;
		protected static String firstname = "iver";
		protected static String lastname = "mejia";
		protected static String email = "qatest@colosa.com";
		protected static String status = "ACTIVE";
		protected static String password = "sample";

    public TestPMFUpdateUser(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.Login().gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				
				pages.Main().goHome();
				
				int casenumber = pages.Home().gotoNewCase().startCase("Test PMFUpdateUser (Task 1)");
        pages.DynaformExecution().intoDynaform();
        
        pages.DynaformExecution().setFieldValue("USER_NAME", firstname);
				pages.DynaformExecution().setFieldValue("USER_LASTNAME", lastname);
				pages.DynaformExecution().setFieldValue("USER_EMAIL", email);
				pages.DynaformExecution().setFieldValue("USER_STATUS", status);
				pages.DynaformExecution().setFieldValue("PASSWORD", password);
				pages.DynaformExecution().setFieldValue("SUBMIT", "", FieldType.BUTTON);
		
				pages.AssignTask().pressContinueButton();
		    
		    pages.Home().gotoInbox();
		    Assert.assertTrue("The case does not exist in inbox", pages.Home().existCase(casenumber));
		    pages.Home().openCase(casenumber);
		    pages.DynaformExecution().intoDynaform();
		    
		    String fieldUSER_NAME = pages.DynaformExecution().getFieldProperty("USER_NAME","value");
		    String fieldUSER_LASTNAME = pages.DynaformExecution().getFieldProperty("USER_LASTNAME","value");
		    String fieldUSER_EMAIL = pages.DynaformExecution().getFieldProperty("USER_EMAIL","value");
		    String fieldUSER_STATUS = pages.DynaformExecution().getFieldProperty("USER_STATUS","value");
		    String fieldRESULT_UPDATE = pages.DynaformExecution().getFieldProperty("RESULT_UPDATE","value");
		    
		    Assert.assertEquals("PMFUpdateUser function does not function correctly.", firstname, fieldUSER_NAME);
		    Assert.assertEquals("PMFUpdateUser function does not function correctly.", lastname, fieldUSER_LASTNAME);
		    Assert.assertEquals("PMFUpdateUser function does not function correctly.", email, fieldUSER_EMAIL);
		    Assert.assertEquals("PMFUpdateUser function does not function correctly.", status, fieldUSER_STATUS);
		    Assert.assertEquals("PMFUpdateUser function does not function correctly.", "1", fieldRESULT_UPDATE);
		    
		    pages.DynaformExecution().setFieldValue("SUBMIT", "", FieldType.BUTTON);
		    pages.AssignTask().pressContinueButton();
			pages.DynaformExecution().outDynaform();
			pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
    
}