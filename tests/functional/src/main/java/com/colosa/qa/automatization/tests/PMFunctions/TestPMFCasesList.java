package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFCasesList extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFCasesList(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
				pages.Main().goHome();
        
        int casenumber = pages.Home().gotoNewCase().startCase("Test PMFCaseList (Task 1)");
        pages.DynaformExecution().intoDynaform();
        pages.DynaformExecution().clickButton("SUBMIT");
        pages.AssignTask().pressContinueButton();
        
        //pages.Home().gotoInbox();
        pages.Home().gotoInbox().openCase(casenumber);
		Assert.assertTrue("The case does not exist in inbox", pages.Home().existCase(casenumber));
        pages.Home().gotoInbox().openCase(casenumber);
		 //pages.Home().openCase(casenumber);
		 pages.DynaformExecution().intoDynaform();


        String fieldSTATUS = pages.DynaformExecution().getFieldProperty("STATUS","value");
        Integer fieldNAME = Integer.parseInt(pages.DynaformExecution().getFieldProperty("NAME","value"));
        
        pages.DynaformExecution().outDynaform();
        
        pages.Home().gotoParticipated();
        Assert.assertTrue("The case does not exist in Participated", pages.Home().existCase(fieldNAME));
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
        Thread.sleep(10000);

    }
    @After
    public void cleanup(){
        browserInstance.quit();
    }
    
}