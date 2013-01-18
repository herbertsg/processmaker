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

public class TestPermissions{

    @Test
    public void runCase() throws Exception {

        // login the PM
        Pages.Login().gotoUrl();
        Pages.Login().loginUser("admin","admin","cochalo");

        Pages.Main().goDesigner();
        Pages.ProcessList().openProcess("Process Permissions");
        Pages.Designer().deleteAllPermission();

        Pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        int numberNewCase = Pages.Home().startCase("Process Permissions (Task 1)");

        // get button submit
        WebElement buttonSUBMIT = Pages.DynaformExecution().getField("Submit");

        // click to button submit
        buttonSUBMIT.click();

        Pages.DynaformExecution().setFieldValue("MNU_NEW", "click");
        Pages.DynaformExecution().setFieldValue("APP_DOC_FILENAME", "/media/Part1/win/julchus tarea.txt");
        Pages.DynaformExecution().setFieldValue("SAVE", "click");
        Pages.DynaformExecution().setFieldValue("BTN_SUBMIT", "click");
        Pages.DynaformExecution().setFieldValue("NEXT_STEP", "click");

        // get button continue
        WebElement buttonContinueSubmit = Pages.DynaformExecution().getObject("//*[@id='btnContinue']");

        // click to button continue
        buttonContinueSubmit.click();

        /////////
        
        
        // login the PM
        Pages.Login().gotoUrl();
        Pages.Login().loginUser("cochalo","sample","");
        
        Pages.Main().goHome();
        Pages.Home().gotoParticipated();
        Pages.Home().openCase(numberNewCase);

        Boolean existDynaforms = Pages.DynaformExecution().openInformationDynaforms();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in dynaforms not work :'(", false, existDynaforms);
        // case was create with field CELULAR correctly
        
        Boolean existUploaded = Pages.DynaformExecution().openInformationUploaded();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in inputs not work :'(", false, existUploaded);
        // case was create with field CELULAR correctly
        
        Boolean existGenerated = Pages.DynaformExecution().openInformationGenerated();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in outputs not work :'(", false, existGenerated);
        // case was create with field CELULAR correctly

        Boolean existCaseNote = Pages.DynaformExecution().openCasesNotes();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in case notes not work :'(", false, existCaseNote);
        // case was create with field CELULAR correctly
        
        Pages.Main().goDesigner();
        Pages.ProcessList().openProcess("Process Permissions");
        Pages.Designer().assignedPermission("cochalo cochalo (cochalo)", "All");

        Pages.Main().goHome();
        Pages.Home().gotoParticipated();
        Pages.Home().openCase(numberNewCase);

        existDynaforms = Pages.DynaformExecution().openInformationDynaforms();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in dynaforms not work :'(", true, existDynaforms);
        // case was create with field CELULAR correctly
        
        existUploaded = Pages.DynaformExecution().openInformationUploaded();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in inputs not work :'(", true, existUploaded);
        // case was create with field CELULAR correctly
        
        existGenerated = Pages.DynaformExecution().openInformationGenerated();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in outputs not work :'(", true, existGenerated);
        // case was create with field CELULAR correctly

        existCaseNote = Pages.DynaformExecution().openCasesNotes();

        // verify if the field CELULAR is validate
        Assert.assertEquals("The Permissions in case notes not work :'(", true, existCaseNote);
        // case was create with field CELULAR correctly
    }

    @After
    public void cleanup(){
        Browser.close();
    }
}