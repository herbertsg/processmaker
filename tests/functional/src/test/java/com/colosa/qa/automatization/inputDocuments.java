package com.colosa.qa.automatization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.colosa.qa.automatization.common.Browser;

@RunWith(value = Suite.class)
@SuiteClasses(value = { com.colosa.qa.automatization.tests.inputDocuments.TestInputDocProcess.class,
                        com.colosa.qa.automatization.tests.inputDocuments.TestInputDocument.class,
                        com.colosa.qa.automatization.tests.inputDocuments.TestInputDocumentList.class})
public class inputDocuments {
    @AfterClass public static void tearDownClass() {
        Browser.close();
    }

}
