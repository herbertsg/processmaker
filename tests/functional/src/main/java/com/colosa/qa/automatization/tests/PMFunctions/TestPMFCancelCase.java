package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.After;
import org.junit.Test;

import java.io.IOException;

public class TestPMFCancelCase extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFCancelCase(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.Login().gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				
				pages.Main().goHome();
				
				int casenumber = pages.Home().gotoNewCase().startCase("Test PMFCancelCase (Task 1)");
        pages.DynaformExecution().intoDynaform();
        
        FormFieldData[] fieldArray=new FormFieldData[1];
		    fieldArray[0]=new FormFieldData();
				
				fieldArray[0].fieldPath="form[SUBMIT]";
		    fieldArray[0].fieldFindType=FieldKeyType.ID;
		    fieldArray[0].fieldType=FieldType.BUTTON;
		    fieldArray[0].fieldValue="";
    		
    		String fieldAPPLICATION = pages.DynaformExecution().getFieldProperty("APPLICATION","value");
    		String status = "Cancelled";
    		
    		FormFiller.formFillElements(browserInstance, fieldArray);
		    
		    pages.Home().gotoParticipated();
		    //Assert.assertTrue("The case with Cancelled status does not exist in Participated", pages.Home().caseStatus(casenumber, status));
            pages.InputDocProcess().switchToDefault();
            pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
    
}