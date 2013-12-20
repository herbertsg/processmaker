package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFUpdateUser extends com.colosa.qa.automatization.tests.common.Test{

    protected static int caseNum;
    protected static String firstname = "new First Name";
    protected static String lastname = "new Last Name";
    protected static String email = "qatest@colosa.com";
    protected static String dueDate = "2015-01-01";
    protected static String status = "INACTIVE";
    protected static String password = "sample2"; // 656b38f3402a1e8b4211fac826efd433
    protected static String role = "PROCESSMAKER_ADMIN";

    protected static String originalFirstname = "Dylan";
    protected static String originalLastname = "Burns";
    protected static String originalEmail = "colosaqatest@gmail.com";
    protected static String originalDueDate = "2016-12-12";
    protected static String originalStatus = "ACTIVE";
    protected static String originalPassword = "sample"; //5e8ff9bf55ba3508199d22e984129be6
    protected static String originalRole = "PROCESSMAKER_OPERATOR";

    public TestPMFUpdateUser(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				
		pages.Main().goHome();
				
		int casenumber = pages.Home().gotoNewCase().startCase("Test PMFUpdateUser (Task 1)");
        pages.DynaformExecution().intoDynaform();
        
        pages.DynaformExecution().setFieldValue("USER_FIRST_NAME", firstname);
		pages.DynaformExecution().setFieldValue("USER_LASTNAME", lastname);
		pages.DynaformExecution().setFieldValue("USER_EMAIL", email);
        pages.DynaformExecution().setFieldValue("USER_DUE_DATE", dueDate);
		pages.DynaformExecution().setFieldValue("USER_STATUS", status);
        pages.DynaformExecution().setFieldValue("USER_ROLE", role);
		pages.DynaformExecution().setFieldValue("USER_PASSWORD", password);
		pages.DynaformExecution().clickButton("SUBMIT");

        //pages.AssignTask().pressContinueButton();

        pages.DynaformExecution().intoDynaform();
        String fieldUSER_FIRST_NAME = pages.DynaformExecution().getFieldValue("USER_FIRST_NAME");
        String fieldUSER_LASTNAME = pages.DynaformExecution().getFieldValue("USER_LASTNAME");
        String fieldUSER_EMAIL = pages.DynaformExecution().getFieldValue("USER_EMAIL");
        String fieldUSER_DUE_DATE = pages.DynaformExecution().getFieldValue("USER_DUE_DATE");
        String fieldUSER_STATUS = pages.DynaformExecution().getFieldValue("USER_STATUS");
        String fieldUSER_ROLE = pages.DynaformExecution().getFieldValue("USER_ROLE");
        String fieldUSER_PASSWORD = pages.DynaformExecution().getFieldValue("USER_PASSWORD");
        String fieldRESULT_UPDATE = pages.DynaformExecution().getFieldValue("RESULT_UPDATE");

        Assert.assertEquals("PMFUpdateUser function does not function correctly.", firstname, fieldUSER_FIRST_NAME);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", lastname, fieldUSER_LASTNAME);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", email, fieldUSER_EMAIL);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", dueDate, fieldUSER_DUE_DATE);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", status, fieldUSER_STATUS);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", role, fieldUSER_ROLE);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", "656b38f3402a1e8b4211fac826efd433", fieldUSER_PASSWORD);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", "1", fieldRESULT_UPDATE);

        //reset values
        pages.DynaformExecution().setFieldValue("USER_FIRST_NAME", originalFirstname);
        pages.DynaformExecution().setFieldValue("USER_LASTNAME", originalLastname);
        pages.DynaformExecution().setFieldValue("USER_EMAIL", originalEmail);
        pages.DynaformExecution().setFieldValue("USER_DUE_DATE", originalDueDate);
        pages.DynaformExecution().setFieldValue("USER_STATUS", originalStatus);
        pages.DynaformExecution().setFieldValue("USER_ROLE", originalRole);
        pages.DynaformExecution().setFieldValue("USER_PASSWORD", originalPassword);

        pages.DynaformExecution().clickButton("SUBMIT");
        //pages.DynaformExecution().setFieldValue("SUBMIT", "", FieldType.BUTTON);
        //pages.AssignTask().pressContinueButton();
        //pages.DynaformExecution().outDynaform();
        //pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
    
}