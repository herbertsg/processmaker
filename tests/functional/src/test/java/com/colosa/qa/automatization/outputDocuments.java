package com.colosa.qa.automatization;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = { //com.colosa.qa.automatization.tests.outputDocuments.TestOutputDocProcess.class, //que se esta testeando???,
                        //com.colosa.qa.automatization.tests.outputDocuments.TestOutputDocument.class
                        //com.colosa.qa.automatization.tests.outputDocuments.TestOutputDocumentList.class //asserts???
})
public class outputDocuments {
    @AfterClass public static void tearDownClass() {
        //Browser.quit();
    }

}
