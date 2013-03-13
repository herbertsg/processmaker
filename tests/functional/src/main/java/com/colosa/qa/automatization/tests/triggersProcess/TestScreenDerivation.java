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

public class TestScreenDerivation{

    @Test
    public void runCase() throws Exception {
        // login the PM
        Pages.Login().gotoUrl();
        Pages.Login().loginUser("admin","admin","workflow");

        // go tab home
        Pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        int numberNewCase = Pages.Home().startCase("Process Derivation Screen (Task 1)");
        
        // click to button submit
	Pages.DynaformExecution().intoDynaform();
        Pages.DynaformExecution().setFieldValue("submit", "click");

        // get the value of field AFTER_DYNAFORM after the form
        String valueScreenDerivation = Pages.DynaformExecution().getFieldValue("screenDerivation");

        // verify if the field AFTER_DYNAFORM is validate
        Assert.assertEquals("The screen derivation not show", valueScreenDerivation, "screenDerivation");
        // trigger after the form working

        // get button continue
        WebElement buttonContinueSubmit = Pages.DynaformExecution().getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();
        Pages.InputDocProcess().switchToDefault();
        Pages.Main().logout();
    }

/*    @After
    public void cleanup(){
        Browser.close();
    }*/
}