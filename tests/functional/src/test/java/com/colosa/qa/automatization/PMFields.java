package com.colosa.qa.automatization;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = { com.colosa.qa.automatization.tests.PMFields.AllFieldsTest.class,
                        com.colosa.qa.automatization.tests.PMFields.CurrencyFieldTest.class,
                        com.colosa.qa.automatization.tests.PMFields.MaskFieldTest.class,
                        com.colosa.qa.automatization.tests.PMFields.DateFieldTest.class
                        })
public class PMFields {
    @AfterClass public static void tearDownClass() {
        //Browser.quit();
    }

}
