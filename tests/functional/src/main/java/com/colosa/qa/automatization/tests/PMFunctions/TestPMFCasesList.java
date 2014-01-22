package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import com.colosa.qa.automatization.pages.DynaformExecution;
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
        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();

        Integer totalCases = Integer.parseInt(form.getFieldValue("totalCases"));
        Assert.assertTrue("The list of cases was not found", (totalCases > 0));
        //pages.InputDocProcess().switchToDefault();
        //pages.Main().logout();
    }
    @After
    public void cleanup(){
        browserInstance.quit();
    }
    
}