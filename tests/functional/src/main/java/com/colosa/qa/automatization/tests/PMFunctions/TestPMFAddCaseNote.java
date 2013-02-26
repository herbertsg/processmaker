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

public class TestPMFAddCaseNote{

    @Test
    public void runCase() throws Exception {
        
        Pages.Login().gotoUrl();
        Pages.Login().loginUser("admin","admin","workflow");
				
				Pages.Main().goDesigner();
        Pages.ProcessList().openProcess("Test PMFAddCaseNote");
        Pages.Designer().assignedPermission("Administrators","All");
        
        Pages.Login().gotoUrl();
        Pages.Login().loginUser("admin","admin","");

        Pages.Main().goHome();
        int casenumber = Pages.Home().startCase("Test PMFAddCaseNote (Task 1)");
        Pages.DynaformExecution().intoDynaform();
        
        FormFieldData[] fieldArray=new FormFieldData[2];
		    fieldArray[0]=new FormFieldData();
				fieldArray[1]=new FormFieldData();
				
				fieldArray[0].fieldPath="form[CASE_NOTE]";
		    fieldArray[0].fieldFindType=FieldKeyType.ID;
		    fieldArray[0].fieldType=FieldType.TEXTBOX;
		    fieldArray[0].fieldValue="Test Case Note";
		    
				fieldArray[1].fieldPath="form[SUBMIT]";
		    fieldArray[1].fieldFindType=FieldKeyType.ID;
		    fieldArray[1].fieldType=FieldType.BUTTON;
		    fieldArray[1].fieldValue="";
    		
    		String fieldCASE_NOTE = "Test Case Note";
    		
    		System.out.println("CASE NOTE "+fieldCASE_NOTE);
    		
    		FormFiller.formFillElements(fieldArray);
		    Pages.InputDocProcess().continuebtn();
		    
		    Pages.Home().gotoInbox();
		    Assert.assertTrue("The case does not exist in Inbox", Pages.Home().existCase(casenumber));
		    Pages.Home().openCase(casenumber);
		    Pages.DynaformExecution().intoDynaform();
		    
		    String fieldRESULT_CASE_NOTE = Pages.DynaformExecution().getFieldProperty("RESULT_CASE_NOTE","value");
		    String fieldTEST_RESULT = Pages.DynaformExecution().getFieldProperty("TEST_RESULT","value");
		    
		    FormFieldData[] fieldArray1=new FormFieldData[1];
		    fieldArray1[0]=new FormFieldData();
		              
		    fieldArray1[0].fieldPath="form[SUBMIT]";
		    fieldArray1[0].fieldFindType=FieldKeyType.ID;
		    fieldArray1[0].fieldType=FieldType.BUTTON;
		    fieldArray1[0].fieldValue="";
		    		    
		    Assert.assertEquals("PMFAddCaseNote function not working properly", fieldRESULT_CASE_NOTE, fieldCASE_NOTE);
		    
		    FormFiller.formFillElements(fieldArray1);
		    Pages.InputDocProcess().continuebtn();
			Pages.InputDocProcess().switchToDefault();
			Pages.Main().logout();
}

//    @After
//    public void cleanup(){
//        Browser.close();
//    }

}