package com.colosa.qa.automatization.tests.process;

import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.IOException;

public class TestHideShowCaseTitle extends com.colosa.qa.automatization.tests.common.Test{

    public TestHideShowCaseTitle(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        // login the PM
        pages.Login().gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");

        pages.Main().goDesigner();
        pages.ProcessList().openProcess("Process Hide Title and case number");
        pages.Designer().showTitleCase(true);

        // go tab home
        pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        int numberNewCase = pages.Home().gotoNewCase().startCase("Process Hide Title and case number (Task 1)");
        
        // switch to frame the dynaform
        //pages.DynaformExecution().intoDynaform();
        boolean showCaseTitle = pages.DynaformExecution().activeCaseTitle();

        // verify if the case title is show
        Assert.assertEquals("The case title is hidden :'(", showCaseTitle, true);
        // trigger after the form working
        

        pages.Main().goDesigner();
        pages.ProcessList().openProcess("Process Hide Title and case number");
        pages.Designer().showTitleCase(false);

        // go tab home
        pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        numberNewCase = pages.Home().gotoNewCase().startCase("Process Hide Title and case number (Task 1)");
        
        // switch to frame the dynaform
        //pages.DynaformExecution().intoDynaform();
        showCaseTitle = pages.DynaformExecution().activeCaseTitle();

        // verify if the case title is show
        Assert.assertEquals("The case title is show :'(", showCaseTitle, false);
        // trigger after the form working
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}
