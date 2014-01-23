package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.pages.DynaformExecution;
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
        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();

        form.setFieldValue("numcase", Integer.toString(caseNum));

        form.clickButton("send");

        DynaformExecution form2 = pages.DynaformExecution();
        form2.intoDynaform();
        Assert.assertEquals("Error Case not deleted.", form2.getFieldValue("result"), "BORRADO");

        //pages.Main().logout();
    }

    public TestPMFDeleteCase(String browserName) throws IOException {
        super(browserName);
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}