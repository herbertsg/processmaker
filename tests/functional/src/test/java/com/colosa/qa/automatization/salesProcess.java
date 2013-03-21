package com.colosa.qa.automatization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.colosa.qa.automatization.common.Browser;

@RunWith(value = Suite.class)
@SuiteClasses(value = { //com.colosa.qa.automatization.tests.salesProcess.TestEmployeeOffboarding.class,
                        //com.colosa.qa.automatization.tests.salesProcess.TestEmployeeOnboarding.class,
                        //com.colosa.qa.automatization.tests.salesProcess.TestEmploymentApplicationProcess.class,
                       //com.colosa.qa.automatization.tests.salesProcess.TestExpenseReport.class,
                       //com.colosa.qa.automatization.tests.salesProcess.TestHiringProcess.class,
                        //com.colosa.qa.automatization.tests.salesProcess.TestNewHireRequest.class
})
public class salesProcess {
    @AfterClass public static void tearDownClass() {
        Browser.close();
    }

}
