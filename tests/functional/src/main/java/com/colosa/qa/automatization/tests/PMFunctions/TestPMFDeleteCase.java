package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;


public class TestPMFDeleteCase extends com.colosa.qa.automatization.tests.common.Test{

    protected int caseNum;
    @Test
    public void executePMFDeleteCase() throws FileNotFoundException, IOException, Exception{

        //Init case
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.Main().goHome();
        caseNum = pages.Home().gotoNewCase().startCase("PMFDeleteCase (Create case)");

        pages.Home().gotoNewCase().startCase("PMFDeleteCase (Delete case)");
        pages.DynaformExecution().intoDynaform();

        pages.DynaformExecution().setFieldValue("numcase", Integer.toString(caseNum));

        pages.DynaformExecution().clickButton("send");

        Assert.assertEquals("Error Case not deleted.", pages.DynaformExecution().getFieldValue("result"), "BORRADO");

        pages.Main().logout();
    }

    public TestPMFDeleteCase(String browserName) throws IOException {
        super(browserName);
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}