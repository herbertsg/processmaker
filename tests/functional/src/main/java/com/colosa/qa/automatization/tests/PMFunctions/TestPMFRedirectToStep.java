package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestPMFRedirectToStep extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFRedirectToStep(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
        pages.DynaformExecution().outDynaform();
				pages.Main().goHome();
				
				int casenumber = pages.Home().gotoNewCase().startCase("Test PMFRedirectToStep (Task 1)");
        pages.DynaformExecution().intoDynaform();
        pages.DynaformExecution().clickButton("SUBMIT");
        pages.DynaformExecution().intoDynaform();
       pages.DynaformExecution().clickButton("SUBMIT");
		    String fieldSTEP = pages.DynaformExecution().getFieldProperty("STEP","value");
		    Assert.assertEquals("The function does not work properly", "3", fieldSTEP);
		    pages.AssignTask().pressContinueButton();
			pages.DynaformExecution().outDynaform();
			pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}