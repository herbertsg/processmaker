package com.colosa.qa.automatization.tests.process;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.junit.After;

import java.io.IOException;

public class TestScreenDerivation extends com.colosa.qa.automatization.tests.common.Test{

    public TestScreenDerivation(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        // login the PM
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");

        // go tab home
        pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        int numberNewCase = pages.Home().gotoNewCase().startCase("Process Derivation Screen (Task 1)");
        
        // click to button submit
	pages.DynaformExecution().intoDynaform();
        pages.DynaformExecution().setFieldValue("submit", "click");

        // get the value of field AFTER_DYNAFORM after the form
        String valueScreenDerivation = pages.DynaformExecution().getFieldValue("screenDerivation");

        // verify if the field AFTER_DYNAFORM is validate
        Assert.assertEquals("The screen derivation not show", valueScreenDerivation, "screenDerivation");
        // trigger after the form working

        // get button continue
        WebElement buttonContinueSubmit = pages.DynaformExecution().getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}