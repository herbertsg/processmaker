package com.colosa.qa.automatization;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = {
                        //com.colosa.qa.automatization.tests.pmslaPlugin.TestSLAEntireProcessWithPenalty.class,
                        com.colosa.qa.automatization.tests.pmslaPlugin.TestSLAMultipleTasksWithConditionAndPenalty.class,
                        //com.colosa.qa.automatization.tests.pmslaPlugin.TestSLAProcessByParalellEvaluation.class,
                        //com.colosa.qa.automatization.tests.pmslaPlugin.TestSLAProcessByParalellEvaluation2.class,
                        com.colosa.qa.automatization.tests.pmslaPlugin.TestSLAProcessEntireProcessWithConditionAndPenalty.class,
                        com.colosa.qa.automatization.tests.pmslaPlugin.TestSLAProcessMultitaskWithPenalty.class,
                        com.colosa.qa.automatization.tests.pmslaPlugin.TestSLAProcessSelectionRoutingRule.class,
                        //com.colosa.qa.automatization.tests.pmslaPlugin.TestSLAProcesstaskWithPenalty.class,
                        //com.colosa.qa.automatization.tests.pmslaPlugin.TestSLAProcesstaskWithPenalty2.class,
                        com.colosa.qa.automatization.tests.pmslaPlugin.TestSLAProcesswithDerivationParallel.class,
                        //com.colosa.qa.automatization.tests.pmslaPlugin.TestSLAProcessWithManySlas.class,
                        com.colosa.qa.automatization.tests.pmslaPlugin.TestSLATaskWithConditionAndWithoutPenalty.class})
public class pmslaPlugin {
    @AfterClass public static void tearDownClass() {
        //Browser.quit();
    }

}
