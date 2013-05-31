package com.colosa.qa.automatization.tests.process;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.junit.After;

import java.io.IOException;

public class TestTriggersSteps extends com.colosa.qa.automatization.tests.common.Test{

    public TestTriggersSteps(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        // login the PM
        pages.Login().gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");

        pages.Main().goDesigner();
        pages.ProcessList().openProcess("Process Debug");
        pages.Designer().activeDebug(true);

        // go tab home
        pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        int numberNewCase = pages.Home().gotoNewCase().startCase("Process Debug (Task 1)");
        
        // switch to frame the dynaform
        pages.DynaformExecution().intoDynaform();
        pages.DebugExecution().goTabVariables();

        String beforeDynaform = pages.DebugExecution().getValue("BEFORE_DYNAFORM");

        // verify if the field BEFORE_DYNAFORM is validate
        Assert.assertEquals("The trigger beforeDynaform form not work", beforeDynaform, "BEFORE_DYNAFORM");
        // trigger after the form working
        
        // get button submit
        pages.DynaformExecution().intoDynaform();
        WebElement buttonSUBMIT = pages.DynaformExecution().getField("Submit");

        // click to button submit
        buttonSUBMIT.click();

        // get the value of field AFTER_DYNAFORM after the form
        String afterDynaform = pages.DebugExecution().getValue("AFTER_DYNAFORM");
        
        // verify if the field AFTER_DYNAFORM is validate
        Assert.assertEquals("The trigger afterDynaform form not work", afterDynaform, "AFTER_DYNAFORM");
        // trigger after the form working
        
        // get button continue
        pages.DynaformExecution().intoDynaform();
        WebElement buttonContinue = pages.DynaformExecution().getObject("//*[@id='publisherContent[2]']/div/input");

        // click to button continue
        buttonContinue.click();

        // get the value of field BEFORE_ASSIGNMENT after the form
        String beforeAssignment = pages.DebugExecution().getValue("BEFORE_ASSIGNMENT");
        
        // verify if the field VARIABLE is validate
        Assert.assertEquals("The trigger beforeAssignment form not work", beforeAssignment, "BEFORE_ASSIGNMENT");
        // trigger after the form working
        
        // get button continue
        pages.DynaformExecution().intoDynaform();
        WebElement buttonContinueSubmit = pages.DynaformExecution().getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();

        // get the value of field AFTER_ROUTING after the form
        String afterRouting = pages.DebugExecution().getValue("AFTER_ROUTING");
        
        // verify if the field VARIABLE is validate
        Assert.assertEquals("The trigger afterRouting form not work", afterRouting, "AFTER_ROUTING");
        // trigger after the form working
        
        // get the value of field BEFORE_ROUTING after the form
        String beforeRouting = pages.DebugExecution().getValue("BEFORE_ROUTING");
        
        // verify if the field VARIABLE is validate
        Assert.assertEquals("The trigger beforeRouting form not work", beforeRouting, "BEFORE_ROUTING");
        // trigger after the form working
        
        // get button continue
        buttonContinue = pages.DynaformExecution().getObject("//*[@id='publisherContent[2]']/div/input");

        // click to button continue
        buttonContinue.click(); 
        
        pages.Main().goDesigner();
        pages.ProcessList().openProcess("Process Debug");
        pages.Designer().activeDebug(false);
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}