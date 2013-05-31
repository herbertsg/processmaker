package com.colosa.qa.automatization;

import com.colosa.qa.automatization.tests.process.TestTriggersSteps;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = {
                        TestTriggersSteps.class})
public class triggersProcess {
    @AfterClass public static void tearDownClass() {
        //browserInstance.quit();
    }

}
