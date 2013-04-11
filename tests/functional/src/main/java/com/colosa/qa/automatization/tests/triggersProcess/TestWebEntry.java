package com.colosa.qa.automatization.tests.triggersProcess;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.IOException;

public class TestWebEntry extends com.colosa.qa.automatization.tests.common.Test{

    public TestWebEntry(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        // login the PM
        pages.WebEntry().goWebEntry("workflow" , "51139890850c52522d53dd5003076507");

        // set value the field NAME
        pages.WebEntry().setFieldValue("NAME", "Brayan");

        // set value the field LASTNAME
        pages.WebEntry().setFieldValue("LAST_NAME", "Pereyra");

        // set value the field LASTNAME
        pages.WebEntry().setFieldValue("Submit", "click");

        Integer numberNewCase = pages.WebEntry().getNumberCase();



        pages.Login().gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English"); //"cochalo");
        pages.Main().goHome();
        pages.Home().gotoInbox();
        pages.Home().openCase(numberNewCase);

        // get the value of field NAME after the form
        String fielNAME = pages.DynaformExecution().getFieldValue("NAME");

        // get the value of field LAST_NAME after the form
        String fielLAST_NAME = pages.DynaformExecution().getFieldValue("LAST_NAME");

        // verify if the field NAME is validate
        Assert.assertEquals("The variables the webentry not work :'(", fielNAME, "Brayan");
        // trigger after the form working
        
        // verify if the field LAST_NAME is validate
        Assert.assertEquals("The variables the webentry not work :'(", fielLAST_NAME, "Pereyra");
        // trigger after the form working

        // click to button submit
        pages.DynaformExecution().setFieldValue("Submit", "click");

        // get button continue
        WebElement buttonContinueSubmit = pages.DynaformExecution().getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
    }

/*    @After
    public void cleanup(){
        Browser.close();
    }*/
}