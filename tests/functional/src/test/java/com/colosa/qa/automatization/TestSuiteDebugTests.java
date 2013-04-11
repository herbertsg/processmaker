package com.colosa.qa.automatization;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created with IntelliJ IDEA.
 * User: Herbert Saal
 * Date: 3/21/13
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        /*
        casesLists.class
        ,*/
        dependentFields.class
        /*,derivationRulesCombinations.class
        ,documents.class
        ,gridFunctions.class
        ,gridFunctionsBetweenColumns.class
        ,inputDocuments.class
        ,outputDocuments.class
        ,javascriptExecution.class
        ,PMFields.class
        ,PMFunctions.class
        ,pmGridFunctions.class
        ,pmslaPlugin.class
        ,pmStringFunctions.class,
        processDesigner.class,
        processExecutionForEvents.class,
        processlist.class,
        redirectLogin.class,
        salesProcess.class,
        subprocessesTest.class,
        suggestDependentFields.class,
        suggestFields.class,
        TestCaseTracker.class,
        ////testFieldModeView.class,
        TestMultipleGridsDependentFields.class,
        TestPMTable.class,
        TestProcessSupervisor.class,
        testRadioButton.class,
        testsJavascript.class,
        TestValidate.class,
        triggersProcess.class*/
})
public class TestSuiteDebugTests {
    @BeforeClass
    public static void setUpClass() {
        //System.out.println("Master setup");

    }

    @AfterClass
    public static void tearDownClass() {
        // System.out.println("Master tearDown");
    }
}

