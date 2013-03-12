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

public class TestPMFUpdateUser{

		protected static int caseNum;
		protected static String firstname = "iver";
		protected static String lastname = "mejia";
		protected static String email = "qatest@colosa.com";
		protected static String status = "ACTIVE";
		protected static String password = "sample";
		
    @Test
    public void runCase() throws Exception {
        
        Pages.Login().gotoUrl();
        Pages.Login().loginUser("admin","admin","workflow");
				
				Pages.Main().goHome();
				
				int casenumber = Pages.Home().startCase("Test PMFUpdateUser (Task 1)");
        Pages.DynaformExecution().intoDynaform();
        
        Pages.DynaformExecution().setFieldValue("USER_NAME", firstname);
				Pages.DynaformExecution().setFieldValue("USER_LASTNAME", lastname);
				Pages.DynaformExecution().setFieldValue("USER_EMAIL", email);
				Pages.DynaformExecution().setFieldValue("USER_STATUS", status);
				Pages.DynaformExecution().setFieldValue("PASSWORD", password);
				Pages.DynaformExecution().setFieldValue("SUBMIT", "", FieldType.BUTTON);
		
				Pages.InputDocProcess().continuebtn();
		    
		    Pages.Home().gotoInbox();
		    Assert.assertTrue("The case does not exist in inbox", Pages.Home().existCase(casenumber));
		    Pages.Home().openCase(casenumber);
		    Pages.DynaformExecution().intoDynaform();
		    
		    String fieldUSER_NAME = Pages.DynaformExecution().getFieldProperty("USER_NAME","value");
		    String fieldUSER_LASTNAME = Pages.DynaformExecution().getFieldProperty("USER_LASTNAME","value");
		    String fieldUSER_EMAIL = Pages.DynaformExecution().getFieldProperty("USER_EMAIL","value");
		    String fieldUSER_STATUS = Pages.DynaformExecution().getFieldProperty("USER_STATUS","value");
		    String fieldRESULT_UPDATE = Pages.DynaformExecution().getFieldProperty("RESULT_UPDATE","value");
		    
		    Assert.assertEquals("PMFUpdateUser function does not function correctly.", firstname, fieldUSER_NAME);
		    Assert.assertEquals("PMFUpdateUser function does not function correctly.", lastname, fieldUSER_LASTNAME);
		    Assert.assertEquals("PMFUpdateUser function does not function correctly.", email, fieldUSER_EMAIL);
		    Assert.assertEquals("PMFUpdateUser function does not function correctly.", status, fieldUSER_STATUS);
		    Assert.assertEquals("PMFUpdateUser function does not function correctly.", "1", fieldRESULT_UPDATE);
		    
		    Pages.DynaformExecution().setFieldValue("SUBMIT", "", FieldType.BUTTON);
		    Pages.InputDocProcess().continuebtn();
			Pages.DynaformExecution().outDynaform();
			Pages.Main().logout();
	}

    @After
    public void cleanup(){
        Browser.close();
    }
    
}