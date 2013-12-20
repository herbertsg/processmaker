package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFSendMessageTest extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFSendMessageTest(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				pages.Main().goHome();
        
        int casenumber = pages.Home().gotoNewCase().startCase("TestPMFSendMessage (Task 1)");
        pages.DynaformExecution().intoDynaform();
        
        FormFieldData[] fieldArray=new FormFieldData[4];
		    fieldArray[0]=new FormFieldData();
		    fieldArray[1]=new FormFieldData();
		    fieldArray[2]=new FormFieldData();
		    fieldArray[3]=new FormFieldData();
				
				fieldArray[0].fieldPath="form[FROM]";
		    fieldArray[0].fieldFindType=FieldKeyType.ID;
		    fieldArray[0].fieldType=FieldType.TEXTBOX;
		    fieldArray[0].fieldValue="qatest@colosa.com";
		    
		    fieldArray[1].fieldPath="form[TO]";
		    fieldArray[1].fieldFindType=FieldKeyType.ID;
		    fieldArray[1].fieldType=FieldType.TEXTBOX;
		    fieldArray[1].fieldValue="norah@colosa.com";
		    
		    fieldArray[2].fieldPath="form[CC]";
		    fieldArray[2].fieldFindType=FieldKeyType.ID;
		    fieldArray[2].fieldType=FieldType.TEXTBOX;
		    fieldArray[2].fieldValue="qatest@colosa.com";
				
				fieldArray[3].fieldPath="form[SUBMIT]";
		    fieldArray[3].fieldFindType=FieldKeyType.ID;
		    fieldArray[3].fieldType=FieldType.BUTTON;
		    fieldArray[3].fieldValue="";
    		
    		FormFiller.formFillElements(browserInstance, fieldArray);
		    pages.AssignTask().pressContinueButton();
        pages.DynaformExecution().outDynaform();

    		pages.Home().gotoInbox();
		    Assert.assertTrue("The case does not exist in inbox", pages.Home().existCase(casenumber));
		    pages.Home().gotoInbox().openCase(casenumber);
		    pages.DynaformExecution().intoDynaform();
		    FormFieldData[] fieldArray1=new FormFieldData[1];
		    fieldArray1[0]=new FormFieldData();
				fieldArray1[0].fieldPath="form[SUBMIT]";
		    fieldArray1[0].fieldFindType=FieldKeyType.ID;
		    fieldArray1[0].fieldType=FieldType.BUTTON;
		    fieldArray1[0].fieldValue="";
								
				String fieldSEND = pages.DynaformExecution().getFieldProperty("SEND","value");
				Assert.assertEquals("Mail not sent", fieldSEND, "1");
		    
		    FormFiller.formFillElements(browserInstance, fieldArray1);
		    pages.AssignTask().pressContinueButton();
        pages.DynaformExecution().outDynaform();
		    pages.Main().goAdmin();
				pages.Admin().goToLogs();
				pages.Admin().showEmailLogs();
				
		    String emStatus = pages.Admin().emailStatus(casenumber);
		    Assert.assertEquals("Mail not sent", emStatus, "Sent");
			pages.DynaformExecution().outDynaform();
			pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}