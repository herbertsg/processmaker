package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.IOException;

public class TestPMFNewCaseTest extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFNewCaseTest(String browserName) throws IOException {
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
        int numberNewCase = pages.Home().gotoNewCase().startCase("Testeo de funciones PMFNewCase (Creador de casos)");
        
        // switch to frame the dynaform
        //pages.DynaformExecution().intoDynaform();
        
        // get count value of field UID_CASE
        int countIdCase = pages.DynaformExecution().getFieldProperty("UID_CASE","value").length();

        // get number of case generated in field NUM_CASE
        String numberCaseGenerated = pages.DynaformExecution().getFieldProperty("NUM_CASE","value");

        // verify if the code is validate
        Assert.assertTrue("No case has created ", (countIdCase == 32) );
        // case was create correctly
        
        // out of frame of dynaform
        pages.DynaformExecution().outDynaform();
        
        // go submenu draft
        pages.Home().gotoDraft();

        // open the case generated
        pages.Home().openCase(Integer.parseInt(numberCaseGenerated));

        // switch to frame the dynaform
        //pages.DynaformExecution().intoDynaform();

        // get value of case generated in field NUM_CASE
        String fieldNOMBRE = pages.DynaformExecution().getFieldProperty("NOMBRE","value");

        // get value of case generated in field APELLIDO
        String fieldAPELLIDO = pages.DynaformExecution().getFieldProperty("APELLIDO","value");

        // get value of case generated in field CELULAR
        String fieldCELULAR = pages.DynaformExecution().getFieldProperty("CELULAR","value");

        // get value of case generated in field OBSERVACIONES
        String fieldOBSERVACIONES = pages.DynaformExecution().getFieldProperty("OBSERVACIONES","value");


        // verify if the field NOMBRE is validate
        Assert.assertEquals("No case has created with field NOMBRE ", fieldNOMBRE, "BRAYAN");
        // case was create with field NOMBRE correctly
        
        // verify if the field APELLIDO is validate
        Assert.assertEquals("No case has created with field APELLIDO ", fieldAPELLIDO, "PEREYRA");
        // case was create with field APELLIDO correctly
        
        // verify if the field CELULAR is validate
        Assert.assertEquals("No case has created with field CELULAR ", fieldCELULAR, "70622818");
        // case was create with field CELULAR correctly
         
        // verify if the field OBSERVACIONES is validate
        Assert.assertEquals("No case has created with field OBSERVACIONES ", fieldOBSERVACIONES, "ESTA ES UNA CREACION DE CASOS VIA TRIGGER");
        // case was create with field OBSERVACIONES correctly
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}