package com.colosa.qa.automatization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.colosa.qa.automatization.common.Browser;

@RunWith(value = Suite.class)
@SuiteClasses(value = { com.colosa.qa.automatization.tests.processExecutionForEvents.TestEventIntermediate.class,
                        com.colosa.qa.automatization.tests.processExecutionForEvents.TestEventSingleTask.class,
                        com.colosa.qa.automatization.tests.processExecutionForEvents.TestProcessIntermediateMessageMultipleTask.class,
                        com.colosa.qa.automatization.tests.processExecutionForEvents.TestProcessIntermediateMessageSingleTask.class,
                        com.colosa.qa.automatization.tests.processExecutionForEvents.TestProcessIntermediateTimerMultipleTask.class,
                        com.colosa.qa.automatization.tests.processExecutionForEvents.TestProcessIntermediateTimerSingleTask.class})
public class processExecutionForEvents {
    @AfterClass public static void tearDownClass() {
        Browser.close();
    }

}
