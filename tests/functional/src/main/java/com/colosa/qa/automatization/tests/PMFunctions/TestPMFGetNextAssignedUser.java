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

public class TestPMFGetNextAssignedUser{

    @Test
    public void runCase() throws Exception {
        
        Pages.Login().gotoUrl();
        Pages.Login().loginUser("admin","admin","workflow");
				
				Pages.Main().goHome();
				
				int casenumber = Pages.Home().startCase("Test PMFGetNextAssignedUser (Task 1)");
        Pages.DynaformExecution().intoDynaform();
        
        FormFieldData[] fieldArray=new FormFieldData[1];
		    fieldArray[0]=new FormFieldData();
				
				fieldArray[0].fieldPath="form[SUBMIT]";
		    fieldArray[0].fieldFindType=FieldKeyType.ID;
		    fieldArray[0].fieldType=FieldType.BUTTON;
		    fieldArray[0].fieldValue="";
    		
    		FormFiller.formFillElements(fieldArray);
		    Pages.InputDocProcess().continuebtn();
		    
		    Pages.Home().gotoInbox();
		    Assert.assertTrue("The case does not exist in inbox", Pages.Home().existCase(casenumber));
		    Pages.Home().openCase(casenumber);
		    Pages.DynaformExecution().intoDynaform();
		    
		    FormFieldData[] fieldArray1=new FormFieldData[1];
		    
		    fieldArray1[0]=new FormFieldData();
				fieldArray1[0].fieldPath="form[SUBMIT]";
		    fieldArray1[0].fieldFindType=FieldKeyType.ID;
		    fieldArray1[0].fieldType=FieldType.BUTTON;
		    fieldArray1[0].fieldValue="";
		    
		    String fieldNEXT_USER_UID = Pages.DynaformExecution().getFieldProperty("NEXT_USER_UID","value");
		    String fieldQUERY_NEXT_USER = Pages.DynaformExecution().getFieldProperty("QUERY_NEXT_USER","value");
		    
		    Assert.assertEquals("Mail not sent", fieldNEXT_USER_UID, fieldQUERY_NEXT_USER);
		    
		    FormFiller.formFillElements(fieldArray1);
		    Pages.InputDocProcess().continuebtn();
			Pages.InputDocProcess().switchToDefault();
			Pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/
    
}