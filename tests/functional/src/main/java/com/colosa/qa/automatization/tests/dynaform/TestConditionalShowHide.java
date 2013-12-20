package com.colosa.qa.automatization.tests.dynaform;

import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.junit.After;

import java.io.IOException;

public class TestConditionalShowHide extends com.colosa.qa.automatization.tests.common.Test{

    public TestConditionalShowHide(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        // login the PM
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");

        pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        int numberNewCase = pages.Home().gotoNewCase().startCase("Process Conditional Show Hide (Task 1)");
	pages.DynaformExecution().intoDynaform();
        pages.DynaformExecution().setFieldValue("opcion", "opcion A");
        pages.DynaformExecution().setFieldValue("profesion", "Yes");
        pages.DynaformExecution().setFieldValue("fecha", "201399");
        pages.DynaformExecution().setFieldValue("porcentaje", "35");

        // get button submit
        WebElement buttonSUBMIT = pages.DynaformExecution().getField("send");

        // click to button submit
        buttonSUBMIT.click();

        // get button continue
        WebElement buttonContinueSubmit = pages.DynaformExecution().getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();

        pages.Home().gotoInbox();
        pages.Home().gotoInbox().openCase(numberNewCase);

        // click to button submit
	pages.DynaformExecution().intoDynaform();
        pages.DynaformExecution().setFieldValue("send", "click");

        // get button continue
        buttonContinueSubmit = pages.DynaformExecution().getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();

        ///////////////////////////
        

        pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        numberNewCase = pages.Home().gotoNewCase().startCase("Process Conditional Show Hide (Task 1)");
	pages.DynaformExecution().intoDynaform();
        pages.DynaformExecution().setFieldValue("opcion", "opcion B");
        pages.DynaformExecution().setFieldValue("titulacion][sistemas", "click");
        
        pages.DynaformExecution().setFieldValue("beca][conv", "click");
        pages.DynaformExecution().setFieldValue("beca][paa", "click");

        pages.DynaformExecution().setFieldValue("salario", "1000");

        // click to button submit
        pages.DynaformExecution().setFieldValue("send", "click");

        // get button continue
        buttonContinueSubmit = pages.DynaformExecution().getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();

        pages.Home().gotoInbox();
        pages.Home().gotoInbox().openCase(numberNewCase);

        // click to button submit
	pages.DynaformExecution().intoDynaform();
        pages.DynaformExecution().setFieldValue("send", "click");

        // get button continue
        buttonContinueSubmit = pages.DynaformExecution().getObject("//*[@id='btnContinue']");

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