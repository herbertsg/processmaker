package com.colosa.qa.automatization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.colosa.qa.automatization.common.Browser;

@RunWith(value = Suite.class)
@SuiteClasses(value = { com.colosa.qa.automatization.tests.triggersProcess.TestConditionalShowHide.class,
                        com.colosa.qa.automatization.tests.triggersProcess.TestExternalStep.class,
                        com.colosa.qa.automatization.tests.triggersProcess.TestHideShowCaseTitle.class,
                        com.colosa.qa.automatization.tests.triggersProcess.TestNotifications.class,
                        com.colosa.qa.automatization.tests.triggersProcess.TestPermissions.class,
                        com.colosa.qa.automatization.tests.triggersProcess.TestScreenDerivation.class,
                        com.colosa.qa.automatization.tests.triggersProcess.TestTriggersSteps.class})
public class triggersProcess {
    @AfterClass public static void tearDownClass() {
        Browser.close();
    }

}
