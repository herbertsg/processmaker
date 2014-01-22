package com.colosa.qa.automatization.tests.casesLists;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;

public class TestCasesLists extends com.colosa.qa.automatization.tests.common.Test{

    public TestCasesLists(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
		pages.Main().goHome();
        
        int casenumber = pages.Home().gotoNewCase().startCase("TestCasesStatus (Task 1)");
        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
                
        form.outDynaform();
        
        pages.Home().gotoDraft();
        pages.Home().gotoInbox().openCase(casenumber);

        form.intoDynaform();
    
        FormFieldData[] fieldArray1=new FormFieldData[2];
		    fieldArray1[0]=new FormFieldData();
		    fieldArray1[1]=new FormFieldData();
		    
				fieldArray1[0].fieldPath="form[EVALUATION]";
		    fieldArray1[0].fieldFindType=FieldKeyType.ID;
		    fieldArray1[0].fieldType=FieldType.TEXTBOX;
		    fieldArray1[0].fieldValue="66";
		    
		    fieldArray1[1].fieldPath="form[SUBMIT]";
		    fieldArray1[1].fieldFindType=FieldKeyType.ID;
		    fieldArray1[1].fieldType=FieldType.BUTTON;
		    fieldArray1[1].fieldValue="";
    		
    		FormFiller.formFillElements(browserInstance, fieldArray1);
		    pages.AssignTask().pressContinueButton();
    		
    		pages.Home().gotoParticipated();
		   // Assert.assertTrue("The case does not exist in Participated", pages.Home().existCase(casenumber));
		    pages.Home().gotoInbox().openCase(casenumber);
		    form.outDynaform();
		        		
    		pages.Home().gotoInbox();
		   // Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(casenumber));
		    
		    pages.Home().gotoInbox().openCase(casenumber);

        form.intoDynaform();
		    
		    FormFieldData[] fieldArray3=new FormFieldData[2];
		    fieldArray3[0]=new FormFieldData();
		    fieldArray3[1]=new FormFieldData();
		    
		    fieldArray3[0].fieldPath="form[EVALUATION]";
		    fieldArray3[0].fieldFindType=FieldKeyType.ID;
		    fieldArray3[0].fieldType=FieldType.TEXTBOX;
		    fieldArray3[0].fieldValue="77";
		              
		    fieldArray3[1].fieldPath="form[SUBMIT]";
		    fieldArray3[1].fieldFindType=FieldKeyType.ID;
		    fieldArray3[1].fieldType=FieldType.BUTTON;
		    fieldArray3[1].fieldValue="";
		    
		    FormFiller.formFillElements(browserInstance, fieldArray3);
		    pages.AssignTask().pressContinueButton();
		    
		    pages.Home().gotoUnassigned();
		    //Assert.assertTrue("The case does not exist in Unassigned", pages.Home().existCase(casenumber));
		    pages.Home().gotoInbox().openCase(casenumber);

        form.intoDynaform();
		    
		    FormFieldData[] fieldArray4=new FormFieldData[1];
		    fieldArray4[0]=new FormFieldData();
		    
		    fieldArray4[0].fieldPath="form[BTN_CATCH]";
		    fieldArray4[0].fieldFindType=FieldKeyType.ID;
		    fieldArray4[0].fieldType=FieldType.BUTTON;
		    fieldArray4[0].fieldValue="";
		    
		    FormFiller.formFillElements(browserInstance, fieldArray4);

        form.intoDynaform();
		    pages.Home().pauseCase(casenumber);
		    
		    form.outDynaform();
		    pages.Home().gotoPaused();
		   // Assert.assertTrue("The case does not exist in Paused", pages.Home().existCase(casenumber));
			pages.InputDocProcess().switchToDefault();
			pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}