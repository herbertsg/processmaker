package com.colosa.qa.automatization.tests.outputDocuments;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestOutputDocument{


	@Test
	public void downloadOutputDoc() throws Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();
		
		int casenumber = Pages.Home().startCase("Test OutputDocument (Task 1)");

		Pages.OutputDocProcess().downloadDocFile();
		Pages.OutputDocProcess().downloadPdfFile();
		Pages.OutputDocProcess().nextbtn();
		
		Pages.DynaformExecution().intoDynaform();
		Pages.OutputDocProcess().downloadDocFile();
		Pages.OutputDocProcess().downloadPdfFile();
		Pages.OutputDocProcess().nextbtn();    
		
		Pages.OutputDocProcess().continuebtn();
		
		Pages.Home().gotoInbox();
    Assert.assertTrue("The case does not exist in inbox", Pages.Home().existCase(casenumber));
		Pages.Home().openCase(casenumber);
		Pages.DynaformExecution().intoDynaform();	
		FormFieldData[] fieldArray=new FormFieldData[1];
    fieldArray[0]=new FormFieldData();
		
    fieldArray[0].fieldPath="form[SUBMIT]";
    fieldArray[0].fieldFindType=FieldKeyType.ID;
    fieldArray[0].fieldType=FieldType.BUTTON;
    fieldArray[0].fieldValue="";
		
		Pages.OutputDocProcess().downloadDocFile();
		Pages.OutputDocProcess().downloadPdfFile();
		Pages.OutputDocProcess().nextbtn();
		
		Pages.OutputDocProcess().downloadDocFile();
		Pages.OutputDocProcess().downloadPdfFile();
		Pages.OutputDocProcess().nextbtn();
		
		String fieldOUTPUT_CONTENT = Pages.DynaformExecution().getFieldProperty("OUTPUT_CONTENT","value");
		String fieldOUTPUT_TEMPLATE = Pages.DynaformExecution().getFieldProperty("OUTPUT_TEMPLATE","value");
		String fieldNOT_VERSION = Pages.DynaformExecution().getFieldProperty("NOT_VERSION","value");
		String fieldVERSION = Pages.DynaformExecution().getFieldProperty("VERSION","value");
		
		Assert.assertEquals("Output Content generated is distinct to Output Template", fieldOUTPUT_CONTENT, fieldOUTPUT_TEMPLATE);
    Assert.assertEquals("Exist Output Document Version", "DOES NOT EXIST VERSION", fieldNOT_VERSION);
    Assert.assertEquals("Does not exist Output Document Version", "EXIST VERSION", fieldVERSION);

		FormFiller.formFillElements(fieldArray);
    Pages.OutputDocProcess().continuebtn();
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

//    @After
//    public void cleanup(){
//        Browser.close();
//    }
}