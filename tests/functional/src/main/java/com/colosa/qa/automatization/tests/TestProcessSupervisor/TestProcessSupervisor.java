package com.colosa.qa.automatization.tests.TestProcessSupervisor;

import com.colosa.qa.automatization.common.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestProcessSupervisor extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestProcessSupervisor(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeProcess() throws FileNotFoundException, IOException, Exception{
		//Init case
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Process Supervisors (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("text", "text");
		pages.DynaformExecution().setFieldValue("enviar", "");
		pages.InputDocProcess().uploadFile("c:\\test.pdf", "Test File");
	    pages.AssignTask().pressContinueButton();
		pages.Main().goHome();
		//pages.Home().gotoReview();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().gotoReview().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("text", "text-add");
		pages.DynaformExecution().setFieldValue("enviar", "");
		pages.InputDocProcess().switchToDefault();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
    	//pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().gotoInbox().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		Assert.assertEquals("text-add", Value.getValue(browserInstance, FieldKeyType.ID, "form[text]"));
		pages.DynaformExecution().setFieldValue("enviar", "");
		pages.Main().logout();
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoReassign();
		pages.DynaformExecution().outDynaform();
		pages.Home().selectCase(caseNum);
        FormFieldData[] fieldArray=new FormFieldData[1];
		fieldArray[0]=new FormFieldData();
		fieldArray[0].fieldPath="//*[@id='casesGrid']/div[1]/div[1]/div[1]/table/tbody/tr/td/table/tbody/tr/td[6]/table/tbody/tr[2]/td[2]/em/button";
		fieldArray[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray[0].fieldType=FieldType.BUTTON;
		fieldArray[0].fieldValue="";
        FormFiller.formFillElements(browserInstance, fieldArray);
        FormFieldData[] fieldArray2=new FormFieldData[1];
		fieldArray2[0]=new FormFieldData();
		fieldArray2[0].fieldPath="//*[@id='TasksToReassign']/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/table/tbody/tr/td[1]";
		fieldArray2[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray2[0].fieldType=FieldType.BUTTON;
		fieldArray2[0].fieldValue="";
        FormFiller.formFillElements(browserInstance, fieldArray2);
        FormFieldData[] fieldArray3=new FormFieldData[1];
		fieldArray3[0]=new FormFieldData();
		fieldArray3[0].fieldPath="//*[@id='reassign-form']/div[2]/div[1]/div[1]/div[1]/form/div[1]/fieldset/div[1]/div[1]/div[1]/div[1]/div[1]/input";
		fieldArray3[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray3[0].fieldType=FieldType.TEXTBOX;
		fieldArray3[0].fieldValue="B";
        FormFiller.formFillElements(browserInstance, fieldArray3);
 		pages.Main().logout();
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoDocuments();
		pages.Home().goCaseSubFrame();
        FormFieldData[] fieldArray22=new FormFieldData[1];
		fieldArray22[0]=new FormFieldData();
		fieldArray22[0].fieldPath="//*[@id='gridpanel']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]";
		fieldArray22[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray22[0].fieldType=FieldType.BUTTON;
		fieldArray22[0].fieldValue="";
        FormFiller.formFillElements(browserInstance, fieldArray22);
        FormFieldData[] fieldArray33=new FormFieldData[1];
		fieldArray33[0]=new FormFieldData();
		fieldArray33[0].fieldPath="//*[@id='tb_delete']/tbody/tr[2]/td[2]/em/button";
		fieldArray33[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray33[0].fieldType=FieldType.BUTTON;
		fieldArray33[0].fieldValue="";
        FormFiller.formFillElements(browserInstance, fieldArray33);
 		pages.Main().logout();
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Process Supervisors (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("text", "text");
		pages.DynaformExecution().setFieldValue("enviar", "");
		pages.InputDocProcess().uploadFile("c:\\test.pdf", "Test File");
	    pages.AssignTask().pressContinueButton();
       	pages.Home().gotoInbox();
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Process Supervisors (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("text", "text");
		pages.DynaformExecution().setFieldValue("enviar", "");
		pages.InputDocProcess().uploadFile("c:\\test.pdf", "Test File");
	    pages.AssignTask().pressContinueButton();
       	pages.Home().gotoInbox();
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}