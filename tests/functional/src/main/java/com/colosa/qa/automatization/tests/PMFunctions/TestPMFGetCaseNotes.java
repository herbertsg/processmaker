package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFGetCaseNotes{

	protected static int caseNum;

	@Test
	public void executePMFGetCaseNotes() throws FileNotFoundException, IOException, Exception{

		//Init case
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("PMFGetCaseNotes (Add notes)");
		Pages.DynaformExecution().intoPmtrack();
        FormFieldData[] fieldArray=new FormFieldData[1];
		fieldArray[0]=new FormFieldData();
		fieldArray[0].fieldPath="//*[@id='caseNotes']/tbody/tr[2]/td[2]/em/button";
		fieldArray[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray[0].fieldType=FieldType.BUTTON;
		fieldArray[0].fieldValue="";
		Pages.DynaformExecution().sleep(10000);  		
        FormFiller.formFillElements(fieldArray);	
        FormFieldData[] fieldArray2=new FormFieldData[1];
		fieldArray2[0]=new FormFieldData();
		fieldArray2[0].fieldPath="//*[@id='addCancelBtn']/tbody/tr[2]/td[2]/em/button";
		fieldArray2[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray2[0].fieldType=FieldType.BUTTON;
		fieldArray2[0].fieldValue="";
        FormFiller.formFillElements(fieldArray2);	
         FormFieldData[] fieldArray3=new FormFieldData[1];
		fieldArray3[0]=new FormFieldData();
		fieldArray3[0].fieldPath="caseNoteText";
		fieldArray3[0].fieldFindType=FieldKeyType.ID;
		fieldArray3[0].fieldType=FieldType.TEXTAREA;
		fieldArray3[0].fieldValue="Test1";
        FormFiller.formFillElements(fieldArray3);  
        FormFieldData[] fieldArray33=new FormFieldData[1];
		fieldArray33[0]=new FormFieldData();
		fieldArray33[0].fieldPath="//*[@id='sendBtn']/tbody/tr[2]/td[2]/em/button";
		fieldArray33[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray33[0].fieldType=FieldType.BUTTON;
		fieldArray33[0].fieldValue="";
        FormFiller.formFillElements(fieldArray33);  
		Pages.DynaformExecution().sleep(10000);        
        FormFiller.formFillElements(fieldArray2);              
         FormFieldData[] fieldArray4=new FormFieldData[1];
		fieldArray4[0]=new FormFieldData();
		fieldArray4[0].fieldPath="caseNoteText";
		fieldArray4[0].fieldFindType=FieldKeyType.ID;
		fieldArray4[0].fieldType=FieldType.TEXTAREA;
		fieldArray4[0].fieldValue="Test2";
        FormFiller.formFillElements(fieldArray4);
		Pages.DynaformExecution().sleep(5000); 
        FormFiller.formFillElements(fieldArray33);  
 		Pages.DynaformExecution().sleep(10000);         
        FormFiller.formFillElements(fieldArray2);                   
        FormFieldData[] fieldArray5=new FormFieldData[1];
		fieldArray5[0]=new FormFieldData();
		fieldArray5[0].fieldPath="caseNoteText";
		fieldArray5[0].fieldFindType=FieldKeyType.ID;
		fieldArray5[0].fieldType=FieldType.TEXTAREA;
		fieldArray5[0].fieldValue="Test3";
        FormFiller.formFillElements(fieldArray5);
		Pages.DynaformExecution().sleep(5000); 
        FormFiller.formFillElements(fieldArray33);  
 		Pages.DynaformExecution().sleep(10000);         
        FormFiller.formFillElements(fieldArray2);              
        FormFieldData[] fieldArray6=new FormFieldData[1];
		fieldArray6[0]=new FormFieldData();
		fieldArray6[0].fieldPath="caseNoteText";
		fieldArray6[0].fieldFindType=FieldKeyType.ID;
		fieldArray6[0].fieldType=FieldType.TEXTAREA;
		fieldArray6[0].fieldValue="Test4";
        FormFiller.formFillElements(fieldArray6);
		Pages.DynaformExecution().sleep(5000); 
        FormFiller.formFillElements(fieldArray33); 
		Pages.DynaformExecution().sleep(6000);           
        FormFieldData[] fieldArray44=new FormFieldData[1];
		fieldArray44[0]=new FormFieldData();
		fieldArray44[0].fieldPath="//*[@id='caseNotesWindowPanel']/div[1]/div[1]/div[1]/div[1]/div[1]";
		fieldArray44[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray44[0].fieldType=FieldType.BUTTON;
		fieldArray44[0].fieldValue="";
        FormFiller.formFillElements(fieldArray44);          
		Pages.DynaformExecution().intoDynaform();        
 		Assert.assertTrue("The button Continue does not exit in this form", Pages.InputDocProcess().continuebtn());	     
 		Pages.DynaformExecution().outDynaform();
		Pages.Main().logout();
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
    	Pages.Home().gotoInbox();	
		Assert.assertTrue("The case does not exist in Inbox", Pages.Home().existCase(caseNum));
		Pages.Home().openCase(caseNum);	
		Pages.DynaformExecution().intoDynaform(); 			           		
		//Verify results
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[gridNotes][1][NOTE_CONTENT]"), Value.getValue(FieldKeyType.ID, "form[gridQuery][4][NOTE_CONTENT]"));		
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[gridNotes][2][NOTE_CONTENT]"), Value.getValue(FieldKeyType.ID, "form[gridQuery][3][NOTE_CONTENT]"));
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[gridNotes][3][NOTE_CONTENT]"), Value.getValue(FieldKeyType.ID, "form[gridQuery][2][NOTE_CONTENT]"));
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[gridNotes][4][NOTE_CONTENT]"), Value.getValue(FieldKeyType.ID, "form[gridQuery][1][NOTE_CONTENT]"));
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("send", "");
		Assert.assertTrue("The button Continue does not exit in this form", Pages.InputDocProcess().continuebtn());	
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

    @After
    public void cleanup(){
        Browser.close();
    }

}