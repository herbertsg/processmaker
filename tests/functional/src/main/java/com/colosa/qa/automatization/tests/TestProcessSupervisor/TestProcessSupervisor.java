package com.colosa.qa.automatization.tests.TestProcessSupervisor;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestProcessSupervisor{

	protected static int caseNum;

	@Test
	public void executeProcess() throws FileNotFoundException, IOException, Exception{
		//Init case
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("Process Supervisors (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("text", "text");
		Pages.DynaformExecution().setFieldValue("enviar", "");
		Pages.InputDocProcess().uploadFile("test.pdf", "Test File");
	    Pages.InputDocProcess().continuebtn();
		Pages.Main().goHome();
		Pages.Home().gotoReview();
		Assert.assertTrue("The case does not exist in Inbox", Pages.Home().existCase(caseNum));
		Pages.Home().openCase(caseNum);
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("text", "text-add");
		Pages.DynaformExecution().setFieldValue("enviar", "");
		Pages.InputDocProcess().switchToDefault();
		Pages.DynaformExecution().outDynaform();
		Pages.Main().logout();
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
    	Pages.Home().gotoInbox();	
		Assert.assertTrue("The case does not exist in Inbox", Pages.Home().existCase(caseNum));
		Pages.Home().openCase(caseNum);
		Pages.DynaformExecution().intoDynaform();
		Assert.assertEquals("text-add", Value.getValue(FieldKeyType.ID, "form[text]"));
		Pages.DynaformExecution().setFieldValue("enviar", "");
		Pages.Main().logout();
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
		Pages.Home().gotoReassign();
		Pages.DynaformExecution().outDynaform();
		Pages.Home().selectCase(caseNum);
        FormFieldData[] fieldArray=new FormFieldData[1];
		fieldArray[0]=new FormFieldData();
		fieldArray[0].fieldPath="//*[@id='casesGrid']/div[1]/div[1]/div[1]/table/tbody/tr/td/table/tbody/tr/td[6]/table/tbody/tr[2]/td[2]/em/button";
		fieldArray[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray[0].fieldType=FieldType.BUTTON;
		fieldArray[0].fieldValue="";
        FormFiller.formFillElements(fieldArray);
        FormFieldData[] fieldArray2=new FormFieldData[1];
		fieldArray2[0]=new FormFieldData();
		fieldArray2[0].fieldPath="//*[@id='TasksToReassign']/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td[1]";
		fieldArray2[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray2[0].fieldType=FieldType.BUTTON;
		fieldArray2[0].fieldValue="";
        FormFiller.formFillElements(fieldArray2);
        FormFieldData[] fieldArray3=new FormFieldData[1];
		fieldArray3[0]=new FormFieldData();
		fieldArray3[0].fieldPath="//*[@id='reassign-form']/div[2]/div[1]/div[1]/div[1]/form/div[1]/fieldset/div[1]/div[1]/div[1]/div[1]/div[1]/input";
		fieldArray3[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray3[0].fieldType=FieldType.TEXTBOX;
		fieldArray3[0].fieldValue="B";
        FormFiller.formFillElements(fieldArray3);
 		Pages.Main().logout();
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
		Pages.Home().gotoDocuments();
		Pages.Home().goCaseSubFrame();
        FormFieldData[] fieldArray22=new FormFieldData[1];
		fieldArray22[0]=new FormFieldData();
		fieldArray22[0].fieldPath="//*[@id='gridpanel']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]";
		fieldArray22[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray22[0].fieldType=FieldType.BUTTON;
		fieldArray22[0].fieldValue="";
        FormFiller.formFillElements(fieldArray22);
        FormFieldData[] fieldArray33=new FormFieldData[1];
		fieldArray33[0]=new FormFieldData();
		fieldArray33[0].fieldPath="//*[@id='tb_delete']/tbody/tr[2]/td[2]/em/button";
		fieldArray33[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray33[0].fieldType=FieldType.BUTTON;
		fieldArray33[0].fieldValue="";
        FormFiller.formFillElements(fieldArray33);
 		Pages.Main().logout();
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
		caseNum = Pages.Home().startCase("Process Supervisors (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("text", "text");
		Pages.DynaformExecution().setFieldValue("enviar", "");
		Pages.InputDocProcess().uploadFile("test.pdf", "Test File");
	    Pages.InputDocProcess().continuebtn();
       	Pages.Home().gotoInbox();	
		Pages.Main().goHome();
		caseNum = Pages.Home().startCase("Process Supervisors (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("text", "text");
		Pages.DynaformExecution().setFieldValue("enviar", "");
		Pages.InputDocProcess().uploadFile("test.pdf", "Test File");
	    Pages.InputDocProcess().continuebtn();
       	Pages.Home().gotoInbox(); 
 		Pages.Main().logout(); 

	}

}