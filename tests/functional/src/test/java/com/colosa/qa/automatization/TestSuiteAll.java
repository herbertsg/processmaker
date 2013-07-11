package com.colosa.qa.automatization;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Created with IntelliJ IDEA.
 * User: Herbert Saal
 * Date: 3/4/13
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.

 */
/*
                casesLists.class,
                derivationRulesCombinations.class, //error timeout  5
                documents.class, //error
                dynaform.class //mejorar test, simplificar validación
                gridFunctions.class, //faltan asserts
                gridFunctionsBetweenColumns.class,  //faltan asserts
                inputDocuments.class, //errors & faltan asserts  3
                ,
                ,
                ,
                plugin.class //validación de plugins propios, external step


                pmslaPlugin.class, //validar el plugin SLA luego del release  12

                processDesigner.class, //se probará con mas detalle adelante  7
                processExecutionForEvents.class,//revisar, no funciona     6
                processlist.class //revisar
                processPermissions.class //error verificar que pasa

                redirectLogin.class //revisar preubas, no funcionan
                ,
                ,
                ,
                salesProcess.class, 6
                subprocessesTest.class //revisar, falla
                ,
                ,
                ,
                testFieldModeView.class, //mejorar el test
                ,
                ,
                ,TestPMTable.class //error mejorar pages.Admin().newPMTable("TESTTABLE","description Table");
                ,
                TestProcessSupervisor.class //mejorar esta quemado el dir pages.InputDocProcess().uploadFile("c:\\test.pdf", "Test File");
                testRadioButton.class //no tiene asserts, no prueba nada
                testsJavascript.class, //es necesario mejorar las pruebas   2
                ,
                ,
                ,
                triggersProcess.class //revisar, mejorar test
                webEntry.class  //mejorar Integer numberNewCase = pages.WebEntry().getNumberCase();
                  */
@RunWith(Suite.class)
@SuiteClasses({ /*
                dependentFields.class,
                javascriptExecution.class, //mejorar asserts, javascript no funcionaba
                notifications.class, //mejorar asserts
                ////outputDocuments.class, //mejorar asserts  3
                PMFields.class,   //3 se deben añadir mas tests de mascaras fechas, etc
                PMFunctions.class/*, //18 fallados=15
                /*pmGridFunctions.class, //mejorar test, añadir formato comas, order grid, evaluate function
                pmStringFunctions.class, //mejorar asserts, no tiene, mejorar las cadenas de pruebas
                process.class, //3
                suggestDependentFields.class,
                suggestFields.class, //mismo código que  suggestDependentFields? revisar
                TestCaseTracker.class,  //2  fallado=1
                TestMultipleGridsDependentFields.class, //mejorar test
                TestValidate.class */

                //com.colosa.qa.automatization.tests.PMFields.CurrencyFieldTest.class
        com.colosa.qa.automatization.tests.test.Example.class

                })
public class TestSuiteAll {
    @BeforeClass
    public static void setUpClass() {
        //Logger.addLog("Master setup");

    }

    @AfterClass public static void tearDownClass() {
       // Logger.addLog("Master tearDown");
    }
}
