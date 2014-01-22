package com.colosa.qa.automatization.tests.dynaform;

import com.colosa.qa.automatization.pages.DynaformExecution;
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
	DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
        form.setFieldValue("opcion", "opcion A");
        form.setFieldValue("profesion", "Yes");
        form.setFieldValue("fecha", "201399");
        form.setFieldValue("porcentaje", "35");

        // get button submit
        form.clickButton("send");

        // get button continue
        WebElement buttonContinueSubmit = form.getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();

        pages.Home().gotoInbox();
        pages.Home().gotoInbox().openCase(numberNewCase);

        // click to button submit

        form.intoDynaform();
        form.setFieldValue("send", "click");

        // get button continue
        buttonContinueSubmit = form.getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();

        ///////////////////////////
        

        pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        numberNewCase = pages.Home().gotoNewCase().startCase("Process Conditional Show Hide (Task 1)");

        form.intoDynaform();
        form.setFieldValue("opcion", "opcion B");
        form.setFieldValue("titulacion][sistemas", "click");
        
        form.setFieldValue("beca][conv", "click");
        form.setFieldValue("beca][paa", "click");

        form.setFieldValue("salario", "1000");

        // click to button submit
        form.setFieldValue("send", "click");

        // get button continue
        buttonContinueSubmit = form.getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();

        pages.Home().gotoInbox();
        pages.Home().gotoInbox().openCase(numberNewCase);

        // click to button submit

        form.intoDynaform();
        form.setFieldValue("send", "click");

        // get button continue
        buttonContinueSubmit = form.getObject("//*[@id='btnContinue']");

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