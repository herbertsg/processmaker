package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFAddCaseNote extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFAddCaseNote(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.Login().gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				
				pages.Main().goDesigner();
        pages.ProcessList().openProcess("Test PMFAddCaseNote");
        pages.Designer().assignedPermission("Administrators","All");
        
        pages.Login().gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");

        pages.Main().goHome();
        int casenumber = pages.Home().gotoNewCase().startCase("Test PMFAddCaseNote (Task 1)");
        pages.DynaformExecution().intoDynaform();
        
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
    		
    		FormFiller.formFillElements( browserInstance, fieldArray);
		    pages.AssignTask().pressContinueButton();
		    
		    pages.Home().gotoInbox();
		    Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(casenumber));
		    pages.Home().openCase(casenumber);
		    pages.DynaformExecution().intoDynaform();
		    
		    String fieldRESULT_CASE_NOTE = pages.DynaformExecution().getFieldProperty("RESULT_CASE_NOTE","value");
		    String fieldTEST_RESULT = pages.DynaformExecution().getFieldProperty("TEST_RESULT","value");
		    
		    FormFieldData[] fieldArray1=new FormFieldData[1];
		    fieldArray1[0]=new FormFieldData();
		              
		    fieldArray1[0].fieldPath="form[SUBMIT]";
		    fieldArray1[0].fieldFindType=FieldKeyType.ID;
		    fieldArray1[0].fieldType=FieldType.BUTTON;
		    fieldArray1[0].fieldValue="";
		    		    
		    Assert.assertEquals("PMFAddCaseNote function not working properly", fieldRESULT_CASE_NOTE, fieldCASE_NOTE);
		    
		    FormFiller.formFillElements( browserInstance, fieldArray1);
		    pages.AssignTask().pressContinueButton();
			pages.InputDocProcess().switchToDefault();
			pages.Main().logout();
    }


    @After
    public void cleanup(){
        browserInstance.quit();
    }


}