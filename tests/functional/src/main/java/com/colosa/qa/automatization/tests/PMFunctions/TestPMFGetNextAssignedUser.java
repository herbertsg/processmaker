package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFGetNextAssignedUser extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFGetNextAssignedUser(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				
				pages.Main().goHome();
				
				int casenumber = pages.Home().gotoNewCase().startCase("Test PMFGetNextAssignedUser (Task 1)");
        pages.DynaformExecution().intoDynaform();
        
        FormFieldData[] fieldArray=new FormFieldData[1];
		    fieldArray[0]=new FormFieldData();
				
				fieldArray[0].fieldPath="form[SUBMIT]";
		    fieldArray[0].fieldFindType=FieldKeyType.ID;
		    fieldArray[0].fieldType=FieldType.BUTTON;
		    fieldArray[0].fieldValue="";
    		
    		FormFiller.formFillElements(browserInstance, fieldArray);
		    pages.AssignTask().pressContinueButton();
		    
		    pages.Home().gotoInbox();
		    //Assert.assertTrue("The case does not exist in inbox", pages.Home().existCase(casenumber));
		    pages.Home().openCase(casenumber);
		    pages.DynaformExecution().intoDynaform();
		    
		    FormFieldData[] fieldArray1=new FormFieldData[1];
		    
		    fieldArray1[0]=new FormFieldData();
				fieldArray1[0].fieldPath="form[SUBMIT]";
		    fieldArray1[0].fieldFindType=FieldKeyType.ID;
		    fieldArray1[0].fieldType=FieldType.BUTTON;
		    fieldArray1[0].fieldValue="";
		    
		    String fieldNEXT_USER_UID = pages.DynaformExecution().getFieldProperty("NEXT_USER_UID","value");
		    String fieldQUERY_NEXT_USER = pages.DynaformExecution().getFieldProperty("QUERY_NEXT_USER","value");
		    
		    Assert.assertEquals("Mail not sent", fieldNEXT_USER_UID, fieldQUERY_NEXT_USER);
		    
		    FormFiller.formFillElements(browserInstance, fieldArray1);
		    pages.AssignTask().pressContinueButton();
			pages.InputDocProcess().switchToDefault();
			pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}