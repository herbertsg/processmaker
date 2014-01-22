package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.pages.DynaformExecution;
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
        //DynaformExecution form = pages.DynaformExecution();
        //form.intoDynaform();
        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
        
        // get count value of field UID_CASE
        int countIdCase = form.getFieldAttribute("UID_CASE", "value").length();

        // get number of case generated in field NUM_CASE
        String numberCaseGenerated = form.getFieldAttribute("NUM_CASE", "value");

        // verify if the code is validate
        Assert.assertTrue("No case has created ", (countIdCase == 32) );
        // case was create correctly
        
        // out of frame of dynaform
        form.outDynaform();
        
        // go submenu draft
        pages.Home().gotoDraft();

        // open the case generated
        pages.Home().gotoInbox().openCase(Integer.parseInt(numberCaseGenerated));

        // switch to frame the dynaform
        //DynaformExecution form = pages.DynaformExecution();
        //form.intoDynaform();

        // get value of case generated in field NUM_CASE
        String fieldNOMBRE = form.getFieldAttribute("NOMBRE", "value");

        // get value of case generated in field APELLIDO
        String fieldAPELLIDO = form.getFieldAttribute("APELLIDO", "value");

        // get value of case generated in field CELULAR
        String fieldCELULAR = form.getFieldAttribute("CELULAR", "value");

        // get value of case generated in field OBSERVACIONES
        String fieldOBSERVACIONES = form.getFieldAttribute("OBSERVACIONES", "value");


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