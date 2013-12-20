package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFNewCaseImpersonate extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFNewCaseImpersonate(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				
				pages.Main().goHome();
				
				int casenumber = pages.Home().gotoNewCase().startCase("Test PMFNewCaseImpersonate (Task 1)");
        pages.DynaformExecution().intoDynaform();
        
        FormFieldData[] fieldArray=new FormFieldData[1];
		    fieldArray[0]=new FormFieldData();
				
				fieldArray[0].fieldPath="form[SUBMIT]";
		    fieldArray[0].fieldFindType=FieldKeyType.ID;
		    fieldArray[0].fieldType=FieldType.BUTTON;
		    fieldArray[0].fieldValue="";
    		
    		FormFiller.formFillElements( browserInstance, fieldArray);
		    pages.AssignTask().pressContinueButton();
		    
		    pages.Home().gotoInbox();
		    //Assert.assertTrue("The case does not exist in inbox", pages.Home().existCase(casenumber));
		    pages.Home().gotoInbox().openCase(casenumber);
		    pages.DynaformExecution().intoDynaform();
		    
		    FormFieldData[] fieldArray1=new FormFieldData[1];
		    
		    fieldArray1[0]=new FormFieldData();
				fieldArray1[0].fieldPath="form[SUBMIT]";
		    fieldArray1[0].fieldFindType=FieldKeyType.ID;
		    fieldArray1[0].fieldType=FieldType.BUTTON;
		    fieldArray1[0].fieldValue="";
		    
		    String fieldRESULT = pages.DynaformExecution().getFieldProperty("RESULT","value");
		    
		    Assert.assertEquals("The function does not work", "1", fieldRESULT);
		    
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