package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestPMFGetEmailConfiguration extends com.colosa.qa.automatization.tests.common.Test{

    protected int caseNum;

    public TestPMFGetEmailConfiguration(String browserName) throws IOException {
        super(browserName);
    }


    @Before
    public void setup(){

    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }


    @Test
    public void runCase() throws Exception {

        pages.gotoDefaultUrl();

        pages.Login().loginUser("admin","admin","workflow", "English");
		pages.Main().goHome();

		caseNum = pages.Home().gotoNewCase().startCase("Test PMFGetEmailConfiguration (Task 1)");
        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
        
        String fieldPASSWORD = form.getFieldAttribute("PASSWORD", "value");

        String fieldMESS_ENABLED = form.getFieldAttribute("MESS_ENABLED", "value");
        String fieldMESS_ENGINE = form.getFieldAttribute("MESS_ENGINE", "value");
        String fieldMESS_SERVER = form.getFieldAttribute("MESS_SERVER", "value");
        String fieldMESS_RAUTH = form.getFieldAttribute("MESS_RAUTH", "value");
        String fieldMESS_PORT = form.getFieldAttribute("MESS_PORT", "value");
        String fieldMESS_ACCOUNT = form.getFieldAttribute("MESS_ACCOUNT", "value");
        String fieldMESS_BACKGROUND = form.getFieldAttribute("MESS_BACKGROUND", "value");
        String fieldMESS_EXECUTE_EVERY = form.getFieldAttribute("MESS_EXECUTE_EVERY", "value");
        String fieldMESS_SEND_MAX = form.getFieldAttribute("MESS_SEND_MAX", "value");
        String fieldSMTPSecure = form.getFieldAttribute("SMTPSecure", "value");
        String fieldMAIL_TO = form.getFieldAttribute("MAIL_TO", "value");
        String fieldMESS_TRY_SEND_INMEDIATLY = form.getFieldAttribute("MESS_TRY_SEND_INMEDIATLY", "value");

        String fieldCONFIG_PASSWORD = form.getFieldAttribute("CONFIG_PASSWORD", "value");
        String fieldCONFIG_MESS_ENABLED = form.getFieldAttribute("CONFIG_MESS_ENABLED", "value");
        String fieldCONFIG_MESS_ENGINE = form.getFieldAttribute("CONFIG_MESS_ENGINE", "value");
        String fieldCONFIG_MESS_SERVER = form.getFieldAttribute("CONFIG_MESS_SERVER", "value");
        String fieldCONFIG_MESS_RAUTH = form.getFieldAttribute("CONFIG_MESS_RAUTH", "value");
        String fieldCONFIG_MESS_PORT = form.getFieldAttribute("CONFIG_MESS_PORT", "value");
        String fieldCONFIG_MESS_ACCOUNT = form.getFieldAttribute("CONFIG_MESS_ACCOUNT", "value");
        String fieldCONFIG_MESS_BACKGROUND = form.getFieldAttribute("CONFIG_MESS_BACKGROUND", "value");
        String fieldCONFIG_MESS_EXECUTE_EVERY = form.getFieldAttribute("CONFIG_MESS_EXECUTE_EVERY", "value");
        String fieldCONFIG_MESS_SEND_MAX = form.getFieldAttribute("CONFIG_MESS_SEND_MAX", "value");
        String fieldCONFIG_SMTPSecure = form.getFieldAttribute("CONFIG_SMTPSecure", "value");
        String fieldCONFIG_MAIL_TO = form.getFieldAttribute("CONFIG_MAIL_TO", "value");
        String fieldCONFIG_MESS_TRY_SEND_INMEDIATLY = form.getFieldAttribute("CONFIG_MESS_TRY_SEND_INMEDIATLY", "value");


        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldPASSWORD, fieldCONFIG_PASSWORD);
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldMESS_ENABLED, fieldCONFIG_MESS_ENABLED);
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldMESS_ENGINE, fieldCONFIG_MESS_ENGINE);
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldMESS_SERVER, fieldCONFIG_MESS_SERVER);
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldMESS_RAUTH, fieldCONFIG_MESS_RAUTH);
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldMESS_PORT, fieldCONFIG_MESS_PORT);
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldMESS_ACCOUNT, fieldCONFIG_MESS_ACCOUNT);
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldMESS_BACKGROUND, fieldCONFIG_MESS_BACKGROUND);
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldMESS_EXECUTE_EVERY, fieldCONFIG_MESS_EXECUTE_EVERY);
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldMESS_SEND_MAX, fieldCONFIG_MESS_SEND_MAX);
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldMAIL_TO, fieldCONFIG_MAIL_TO);
        Assert.assertEquals("PMFAddCaseNote function not working properly", fieldMESS_TRY_SEND_INMEDIATLY, fieldCONFIG_MESS_TRY_SEND_INMEDIATLY);

        //form.clickButton("SUBMIT"); //.setFieldValue("SUBMIT", "", FieldType.BUTTON);

        //pages.AssignTask().pressContinueButton();
		//pages.InputDocProcess().switchToDefault();
		//pages.Main().logout();
	}


}