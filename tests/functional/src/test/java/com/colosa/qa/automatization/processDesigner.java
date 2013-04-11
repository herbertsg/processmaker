package com.colosa.qa.automatization;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = { com.colosa.qa.automatization.tests.processDesigner.TestDesigner.class,
                        com.colosa.qa.automatization.tests.processDesigner.TestProcessExc.class,
                        com.colosa.qa.automatization.tests.processDesigner.TestTaskProperties.class,
                        //com.colosa.qa.automatization.tests.processDesigner.TestWithEvents.class,
                        com.colosa.qa.automatization.tests.processDesigner.TestWithParallelCondition.class
                        //com.colosa.qa.automatization.tests.processDesigner.TestWithSelectCondition.class,
                        //com.colosa.qa.automatization.tests.processDesigner.TestWithParallelByEvalCondition.class
                         })

public class processDesigner {

    @AfterClass public static void tearDownClass() {
        //Browser.close();
    }

}
