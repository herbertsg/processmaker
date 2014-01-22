package com.colosa.qa.automatization.tests.notifications;

import com.colosa.qa.automatization.pages.DynaformExecution;
import com.colosa.qa.automatization.pages.Home;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.junit.After;

import java.io.IOException;

public class TestNotifications extends com.colosa.qa.automatization.tests.common.Test{

    public TestNotifications(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        // login the PM
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");

        Home home = pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        int numberNewCase = home.gotoNewCase().startCase("Process Notification Email (Task 1)");
        
        // get button submit
	    DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();

        form.clickButton("submit");

        //WebElement buttonSUBMIT = form.getField("submit");

        // click to button submit
        //buttonSUBMIT.click();

        pages.AssignTask().pressContinueButton();

        /*// get button continue
        WebElement buttonContinueSubmit = form.getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();*/

        pages.Home().gotoInbox().openCase(numberNewCase);

        //pages.Home().openCase(numberNewCase);
        DynaformExecution form2 = pages.DynaformExecution();
        form2.intoDynaform();
        String valorEnviado = form2.getFieldValue("ENVIADO");

        // verify if the field CELULAR is validate
        Assert.assertEquals("Notification not sent.:'(", valorEnviado, "sent");
        // case was create with field CELULAR correctly
        //pages.InputDocProcess().switchToDefault();
        //pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}