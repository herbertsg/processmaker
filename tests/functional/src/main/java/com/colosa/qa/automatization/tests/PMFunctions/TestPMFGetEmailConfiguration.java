package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFGetEmailConfiguration extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFGetEmailConfiguration(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				
				pages.Main().goHome();
				
				int casenumber = pages.Home().gotoNewCase().startCase("Test PMFGetEmailConfiguration (Task 1)");
        pages.DynaformExecution().intoDynaform();
        
        String fieldPASSWORD = pages.DynaformExecution().getFieldProperty("PASSWORD","value");
		    String fieldMESS_ENABLED = pages.DynaformExecution().getFieldProperty("MESS_ENABLED","value");
		    String fieldMESS_ENGINE = pages.DynaformExecution().getFieldProperty("MESS_ENGINE","value");
		    String fieldMESS_SERVER = pages.DynaformExecution().getFieldProperty("MESS_SERVER","value");
		    String fieldMESS_RAUTH = pages.DynaformExecution().getFieldProperty("MESS_RAUTH","value");
		    String fieldMESS_PORT = pages.DynaformExecution().getFieldProperty("MESS_PORT","value");
		    String fieldMESS_ACCOUNT = pages.DynaformExecution().getFieldProperty("MESS_ACCOUNT","value");
		    String fieldMESS_BACKGROUND = pages.DynaformExecution().getFieldProperty("MESS_BACKGROUND","value");
		    String fieldMESS_EXECUTE_EVERY = pages.DynaformExecution().getFieldProperty("MESS_EXECUTE_EVERY","value");
				String fieldMESS_SEND_MAX = pages.DynaformExecution().getFieldProperty("MESS_SEND_MAX","value");
		    String fieldSMTPSecure = pages.DynaformExecution().getFieldProperty("SMTPSecure","value");
		    String fieldMAIL_TO = pages.DynaformExecution().getFieldProperty("MAIL_TO","value");
		    String fieldMESS_TRY_SEND_INMEDIATLY = pages.DynaformExecution().getFieldProperty("MESS_TRY_SEND_INMEDIATLY","value");
		    
		    String fieldCONFIG_PASSWORD = pages.DynaformExecution().getFieldProperty("CONFIG_PASSWORD","value");
		    String fieldCONFIG_MESS_ENABLED = pages.DynaformExecution().getFieldProperty("CONFIG_MESS_ENABLED","value");
		    String fieldCONFIG_MESS_ENGINE = pages.DynaformExecution().getFieldProperty("CONFIG_MESS_ENGINE","value");
		    String fieldCONFIG_MESS_SERVER = pages.DynaformExecution().getFieldProperty("CONFIG_MESS_SERVER","value");
		    String fieldCONFIG_MESS_RAUTH = pages.DynaformExecution().getFieldProperty("CONFIG_MESS_RAUTH","value");
		    String fieldCONFIG_MESS_PORT = pages.DynaformExecution().getFieldProperty("CONFIG_MESS_PORT","value");
		    String fieldCONFIG_MESS_ACCOUNT = pages.DynaformExecution().getFieldProperty("CONFIG_MESS_ACCOUNT","value");
		    String fieldCONFIG_MESS_BACKGROUND = pages.DynaformExecution().getFieldProperty("CONFIG_MESS_BACKGROUND","value");
		    String fieldCONFIG_MESS_EXECUTE_EVERY = pages.DynaformExecution().getFieldProperty("CONFIG_MESS_EXECUTE_EVERY","value");
				String fieldCONFIG_MESS_SEND_MAX = pages.DynaformExecution().getFieldProperty("CONFIG_MESS_SEND_MAX","value");
		    String fieldCONFIG_SMTPSecure = pages.DynaformExecution().getFieldProperty("CONFIG_SMTPSecure","value");
		    String fieldCONFIG_MAIL_TO = pages.DynaformExecution().getFieldProperty("CONFIG_MAIL_TO","value");
		    String fieldCONFIG_MESS_TRY_SEND_INMEDIATLY = pages.DynaformExecution().getFieldProperty("CONFIG_MESS_TRY_SEND_INMEDIATLY","value");
		    
		    
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
		    		    
		    pages.DynaformExecution().setFieldValue("SUBMIT", "", FieldType.BUTTON);
				pages.AssignTask().pressContinueButton();
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
    
}