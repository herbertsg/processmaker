package com.colosa.qa.automatization.tests.plugins;

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

public class TestExternalStep extends com.colosa.qa.automatization.tests.common.Test{

    public TestExternalStep(String browserName) throws IOException {
        super(browserName);
    }

    @Test
    public void runCase() throws Exception {
        // login the PM
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");

        // go tab home
        pages.Main().goAdmin();

        pages.Admin().activePlugin("external", true);
        
        // go tab home
        pages.Main().goHome();

        // create case the process and task : Testeo de funciones PMFNewCase (Creador de casos)
        int numberNewCase = pages.Home().gotoNewCase().startCase("Testeo external step (Tarea Inicial)");
        
        pages.DynaformExecution().intoDynaform();

        FormFieldData[] fieldArray = new FormFieldData[2];
        fieldArray[0]=new FormFieldData();
        fieldArray[1]=new FormFieldData();
        
        fieldArray[0].fieldPath="form[NONBRE]";
        fieldArray[0].fieldFindType=FieldKeyType.ID;
        fieldArray[0].fieldType=FieldType.TEXTBOX;
        fieldArray[0].fieldValue="BRAYAN";
      
        fieldArray[1].fieldPath="form[APELLIDO]";
        fieldArray[1].fieldFindType=FieldKeyType.ID;
        fieldArray[1].fieldType=FieldType.TEXTBOX;
        fieldArray[1].fieldValue="PEREYRA";
        FormFiller.formFillElements( browserInstance, fieldArray);

        // get button submit
        WebElement linkSiguiente = pages.DynaformExecution().getField("SIGUIENTE");
        
        // click to button submit
        linkSiguiente.click();
        
        pages.DynaformExecution().intoDynaform();

        fieldArray[0]=new FormFieldData();
        fieldArray[1]=new FormFieldData();
        
        fieldArray[0].fieldPath="form[NONBRE]";
        fieldArray[0].fieldFindType=FieldKeyType.ID;
        fieldArray[0].fieldType=FieldType.TEXTBOX;
        fieldArray[0].fieldValue="BRAYAN";
      
        fieldArray[1].fieldPath="form[APELLIDO]";
        fieldArray[1].fieldFindType=FieldKeyType.ID;
        fieldArray[1].fieldType=FieldType.TEXTBOX;
        fieldArray[1].fieldValue="PEREYRA";
        FormFiller.formFillElements( browserInstance, fieldArray);

        // get button submit
        WebElement buttonSUBMIT = pages.DynaformExecution().getField("SUBMIT");

        // click to button submit
        buttonSUBMIT.click();

        pages.DynaformExecution().intoDynaform();

        // get value of case generated in field NUM_CASE
        String fieldNOMBRE = pages.DynaformExecution().getFieldProperty("NONBRE","value");
        
        // get value of case generated in field APELLIDO
        String fieldAPELLIDO = pages.DynaformExecution().getFieldProperty("APELLIDO","value");
        
        // verify if the field NOMBRE is validate
        Assert.assertEquals("No case has created with field NOMBRE ", fieldNOMBRE, "BRAYAN");
        // case was create with field NOMBRE correctly
        
        // verify if the field APELLIDO is validate
        Assert.assertEquals("No case has created with field APELLIDO ", fieldAPELLIDO, "PEREYRA");
        // case was create with field APELLIDO correctly
        
        pages.Main().goAdmin();
        pages.Admin().activePlugin("external", false);
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}