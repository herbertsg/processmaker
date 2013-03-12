package com.colosa.qa.automatization.tests.triggersProcess;

import java.lang.Exception;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;

import com.colosa.qa.automatization.common.controlOptions.input.*;
import com.colosa.qa.automatization.common.controlOptions.selection.*;
import com.colosa.qa.automatization.common.controlOptions.ControlOptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestWebEntry{

    @Test
    public void runCase() throws Exception {
        // login the PM
        Pages.WebEntry().goWebEntry("workflow" , "51139890850c52522d53dd5003076507");

        // set value the field NAME
        Pages.WebEntry().setFieldValue("NAME", "Brayan");

        // set value the field LASTNAME
        Pages.WebEntry().setFieldValue("LAST_NAME", "Pereyra");

        // set value the field LASTNAME
        Pages.WebEntry().setFieldValue("Submit", "click");

        Integer numberNewCase = Pages.WebEntry().getNumberCase();



        Pages.Login().gotoUrl();
        Pages.Login().loginUser("admin","admin","cochalo");
        Pages.Main().goHome();
        Pages.Home().gotoInbox();
        Pages.Home().openCase(numberNewCase);

        // get the value of field NAME after the form
        String fielNAME = Pages.DynaformExecution().getFieldValue("NAME");

        // get the value of field LAST_NAME after the form
        String fielLAST_NAME = Pages.DynaformExecution().getFieldValue("LAST_NAME");

        // verify if the field NAME is validate
        Assert.assertEquals("The variables the webentry not work :'(", fielNAME, "Brayan");
        // trigger after the form working
        
        // verify if the field LAST_NAME is validate
        Assert.assertEquals("The variables the webentry not work :'(", fielLAST_NAME, "Pereyra");
        // trigger after the form working

        // click to button submit
        Pages.DynaformExecution().setFieldValue("Submit", "click");

        // get button continue
        WebElement buttonContinueSubmit = Pages.DynaformExecution().getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();
        Pages.InputDocProcess().switchToDefault();
        Pages.Main().logout();
    }

    @After
    public void cleanup(){
        Browser.close();
    }
}