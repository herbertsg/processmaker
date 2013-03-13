package com.colosa.qa.automatization.tests.PMFunctions;

import java.lang.Exception;
import java.util.List;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
    
import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.NoSuchElementException;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.common.controlOptions.input.*;
import com.colosa.qa.automatization.common.controlOptions.selection.*;
import com.colosa.qa.automatization.common.controlOptions.ControlOptions;
import com.colosa.qa.automatization.common.Browser;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;

public class TestPMFGetEmailConfiguration{

		@Test
    public void runCase() throws Exception {
        
        Pages.Login().gotoUrl();
        Pages.Login().loginUser("admin","admin","workflow");
				
				Pages.Main().goHome();
				
				int casenumber = Pages.Home().startCase("Test PMFGetEmailConfiguration (Task 1)");
        Pages.DynaformExecution().intoDynaform();
        
        String fieldPASSWORD = Pages.DynaformExecution().getFieldProperty("PASSWORD","value");
		    String fieldMESS_ENABLED = Pages.DynaformExecution().getFieldProperty("MESS_ENABLED","value");
		    String fieldMESS_ENGINE = Pages.DynaformExecution().getFieldProperty("MESS_ENGINE","value");
		    String fieldMESS_SERVER = Pages.DynaformExecution().getFieldProperty("MESS_SERVER","value");
		    String fieldMESS_RAUTH = Pages.DynaformExecution().getFieldProperty("MESS_RAUTH","value");
		    String fieldMESS_PORT = Pages.DynaformExecution().getFieldProperty("MESS_PORT","value");
		    String fieldMESS_ACCOUNT = Pages.DynaformExecution().getFieldProperty("MESS_ACCOUNT","value");
		    String fieldMESS_BACKGROUND = Pages.DynaformExecution().getFieldProperty("MESS_BACKGROUND","value");
		    String fieldMESS_EXECUTE_EVERY = Pages.DynaformExecution().getFieldProperty("MESS_EXECUTE_EVERY","value");
				String fieldMESS_SEND_MAX = Pages.DynaformExecution().getFieldProperty("MESS_SEND_MAX","value");
		    String fieldSMTPSecure = Pages.DynaformExecution().getFieldProperty("SMTPSecure","value");
		    String fieldMAIL_TO = Pages.DynaformExecution().getFieldProperty("MAIL_TO","value");
		    String fieldMESS_TRY_SEND_INMEDIATLY = Pages.DynaformExecution().getFieldProperty("MESS_TRY_SEND_INMEDIATLY","value");
		    
		    String fieldCONFIG_PASSWORD = Pages.DynaformExecution().getFieldProperty("CONFIG_PASSWORD","value");
		    String fieldCONFIG_MESS_ENABLED = Pages.DynaformExecution().getFieldProperty("CONFIG_MESS_ENABLED","value");
		    String fieldCONFIG_MESS_ENGINE = Pages.DynaformExecution().getFieldProperty("CONFIG_MESS_ENGINE","value");
		    String fieldCONFIG_MESS_SERVER = Pages.DynaformExecution().getFieldProperty("CONFIG_MESS_SERVER","value");
		    String fieldCONFIG_MESS_RAUTH = Pages.DynaformExecution().getFieldProperty("CONFIG_MESS_RAUTH","value");
		    String fieldCONFIG_MESS_PORT = Pages.DynaformExecution().getFieldProperty("CONFIG_MESS_PORT","value");
		    String fieldCONFIG_MESS_ACCOUNT = Pages.DynaformExecution().getFieldProperty("CONFIG_MESS_ACCOUNT","value");
		    String fieldCONFIG_MESS_BACKGROUND = Pages.DynaformExecution().getFieldProperty("CONFIG_MESS_BACKGROUND","value");
		    String fieldCONFIG_MESS_EXECUTE_EVERY = Pages.DynaformExecution().getFieldProperty("CONFIG_MESS_EXECUTE_EVERY","value");
				String fieldCONFIG_MESS_SEND_MAX = Pages.DynaformExecution().getFieldProperty("CONFIG_MESS_SEND_MAX","value");
		    String fieldCONFIG_SMTPSecure = Pages.DynaformExecution().getFieldProperty("CONFIG_SMTPSecure","value");
		    String fieldCONFIG_MAIL_TO = Pages.DynaformExecution().getFieldProperty("CONFIG_MAIL_TO","value");
		    String fieldCONFIG_MESS_TRY_SEND_INMEDIATLY = Pages.DynaformExecution().getFieldProperty("CONFIG_MESS_TRY_SEND_INMEDIATLY","value");
		    
		    
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
		    		    
		    Pages.DynaformExecution().setFieldValue("SUBMIT", "", FieldType.BUTTON);
				Pages.InputDocProcess().continuebtn();
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/
    
}