package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestPMFGetNextAssignedUser extends com.colosa.qa.automatization.tests.common.Test{
    protected int caseNum;
    public TestPMFGetNextAssignedUser(String browserName) throws IOException {
        super(browserName);
    }
    @Before
    public void setup(){

    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

    @Test
    public void runCase() throws Exception {
        pages.gotoDefaultUrl();

        Logger.addLog("Test testDependentFieldsCase with browserName:" + this.browserName);

        pages.gotoDefaultUrl();

        pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.Main().goHome();

        caseNum = pages.Home().gotoNewCase().startCase("Test PMFGetNextAssignedUser (Task 1)");

        pages.DynaformExecution().intoDynaform();
        //FormFiller.formFillElements(browserInstance, fieldArray);

        pages.DynaformExecution().clickButton("SUBMIT");

        pages.AssignTask().pressContinueButton();
        //pages.Home().gotoInbox();
        // pages.Home().openCase(caseNum);
        // pages.DynaformExecution().intoDynaform();
        pages.gotoDefaultUrl();
        pages.Login().loginUser("aaron ", "sample", "workflow", "English");
        pages.Main().goHome();
        pages.Home().gotoInbox().openCase(caseNum);
        pages.DynaformExecution().intoDynaform();

        String fieldNEXT_USER_UID = pages.DynaformExecution().getFieldProperty("NEXT_USER_UID","value");
        String fieldQUERY_NEXT_USER = pages.DynaformExecution().getFieldProperty("QUERY_NEXT_USER","value");

        Assert.assertEquals("Different user id detected. Could not get the next Assigned user Aaron.", fieldNEXT_USER_UID, fieldQUERY_NEXT_USER);

        pages.Main().logout();

	}


}