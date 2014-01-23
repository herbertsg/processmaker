package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.pages.DynaformExecution;
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
        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
        
        form.setFieldValue("USER_FIRST_NAME", firstname);
		form.setFieldValue("USER_LASTNAME", lastname);
		form.setFieldValue("USER_EMAIL", email);
        form.setFieldValue("USER_DUE_DATE", dueDate);
		form.setFieldValue("USER_STATUS", status);
        form.setFieldValue("USER_ROLE", role);
		form.setFieldValue("USER_PASSWORD", password);
		form.clickButton("SUBMIT");

        //pages.AssignTask().pressContinueButton();
        DynaformExecution form2 = pages.DynaformExecution();
        form2.intoDynaform();
        String fieldUSER_FIRST_NAME = form2.getFieldValue("USER_FIRST_NAME");
        String fieldUSER_LASTNAME = form2.getFieldValue("USER_LASTNAME");
        String fieldUSER_EMAIL = form2.getFieldValue("USER_EMAIL");
        String fieldUSER_DUE_DATE = form2.getFieldValue("USER_DUE_DATE");
        String fieldUSER_STATUS = form2.getFieldValue("USER_STATUS");
        String fieldUSER_ROLE = form2.getFieldValue("USER_ROLE");
        String fieldUSER_PASSWORD = form2.getFieldValue("USER_PASSWORD");
        String fieldRESULT_UPDATE = form2.getFieldValue("RESULT_UPDATE");

        Assert.assertEquals("PMFUpdateUser function does not function correctly.", firstname, fieldUSER_FIRST_NAME);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", lastname, fieldUSER_LASTNAME);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", email, fieldUSER_EMAIL);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", dueDate, fieldUSER_DUE_DATE);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", status, fieldUSER_STATUS);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", role, fieldUSER_ROLE);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", "656b38f3402a1e8b4211fac826efd433", fieldUSER_PASSWORD);
        Assert.assertEquals("PMFUpdateUser function does not function correctly.", "1", fieldRESULT_UPDATE);

        //reset values
        form2.setFieldValue("USER_FIRST_NAME", originalFirstname);
        form2.setFieldValue("USER_LASTNAME", originalLastname);
        form2.setFieldValue("USER_EMAIL", originalEmail);
        form2.setFieldValue("USER_DUE_DATE", originalDueDate);
        form2.setFieldValue("USER_STATUS", originalStatus);
        form2.setFieldValue("USER_ROLE", originalRole);
        form2.setFieldValue("USER_PASSWORD", originalPassword);

        form2.clickButton("SUBMIT");
        //form.setFieldValue("SUBMIT", "", FieldType.BUTTON);
        //pages.AssignTask().pressContinueButton();
        //form.outDynaform();
        //pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
    
}