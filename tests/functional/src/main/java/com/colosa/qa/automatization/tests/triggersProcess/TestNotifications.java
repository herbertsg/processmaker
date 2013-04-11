package com.colosa.qa.automatization.tests.triggersProcess;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class TestNotifications extends com.colosa.qa.automatization.tests.common.Test{

    public TestNotifications(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        // login the PM
        pages.Login().gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");

        pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        int numberNewCase = pages.Home().startCase("Process Notification Email (Task 1)");
        
        // get button submit
	pages.DynaformExecution().intoDynaform();
        WebElement buttonSUBMIT = pages.DynaformExecution().getField("submit");

        // click to button submit
        buttonSUBMIT.click();

        // get button continue
        WebElement buttonContinueSubmit = pages.DynaformExecution().getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();

        pages.Home().gotoInbox();
        pages.Home().openCase(numberNewCase);
	pages.DynaformExecution().intoDynaform();
        String valorEnviado = pages.DynaformExecution().getFieldValue("ENVIADO");

        // verify if the field CELULAR is validate
        Assert.assertEquals("No sent the notification :'(", valorEnviado, "sent");
        // case was create with field CELULAR correctly
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
    }

/*    @After
    public void cleanup(){
        Browser.close();
    }*/
}