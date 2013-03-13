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

public class TestPMFCancelCase{

    @Test
    public void runCase() throws Exception {
        
        Pages.Login().gotoUrl();
        Pages.Login().loginUser("admin","admin","workflow");
				
				Pages.Main().goHome();
				
				int casenumber = Pages.Home().startCase("Test PMFCancelCase (Task 1)");
        Pages.DynaformExecution().intoDynaform();
        
        FormFieldData[] fieldArray=new FormFieldData[1];
		    fieldArray[0]=new FormFieldData();
				
				fieldArray[0].fieldPath="form[SUBMIT]";
		    fieldArray[0].fieldFindType=FieldKeyType.ID;
		    fieldArray[0].fieldType=FieldType.BUTTON;
		    fieldArray[0].fieldValue="";
    		
    		String fieldAPPLICATION = Pages.DynaformExecution().getFieldProperty("APPLICATION","value");
    		String status = "Cancelled";
    		
    		FormFiller.formFillElements(fieldArray);
		    
		    Pages.Home().gotoParticipated();
		    Assert.assertTrue("The case with Cancelled status does not exist in Participated", Pages.Home().caseStatus(casenumber, status));
            Pages.InputDocProcess().switchToDefault();
            Pages.Main().logout();
}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/
    
}