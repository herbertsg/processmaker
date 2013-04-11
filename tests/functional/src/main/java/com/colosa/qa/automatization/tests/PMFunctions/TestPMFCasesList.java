package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFCasesList extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFCasesList(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.Login().gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				pages.Main().goHome();
        
        int casenumber = pages.Home().startCase("Test PMFCaseList (Task 1)");
        pages.DynaformExecution().intoDynaform();
        
        FormFieldData[] fieldArray=new FormFieldData[1];
		    fieldArray[0]=new FormFieldData();
				
		    fieldArray[0].fieldPath="form[SUBMIT]";
		    fieldArray[0].fieldFindType=FieldKeyType.ID;
		    fieldArray[0].fieldType=FieldType.BUTTON;
		    fieldArray[0].fieldValue="";
    
        FormFiller.formFillElements(browserInstance, fieldArray);
        pages.InputDocProcess().continuebtn();
        
        pages.Home().gotoInbox();
		   // Assert.assertTrue("The case does not exist in inbox", pages.Home().existCase(casenumber));
		    pages.Home().openCase(casenumber);
		    pages.DynaformExecution().intoDynaform();
        
        String fieldSTATUS = pages.DynaformExecution().getFieldProperty("STATUS","value");
        Integer fieldNAME = Integer.parseInt(pages.DynaformExecution().getFieldProperty("NAME","value"));
        
        pages.DynaformExecution().outDynaform();
        
        pages.Home().gotoParticipated();
        Assert.assertTrue("The case does not exist in Participated", pages.Home().existCase(fieldNAME));
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
}
/*    @After
    public void cleanup(){
        Browser.close();
    }*/
    
}