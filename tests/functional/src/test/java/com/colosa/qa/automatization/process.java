package com.colosa.qa.automatization;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 5/3/13
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */

import com.colosa.qa.automatization.tests.process.TestHideShowCaseTitle;
import com.colosa.qa.automatization.tests.process.TestScreenDerivation;
import com.colosa.qa.automatization.tests.process.TestTriggersSteps;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {
        TestHideShowCaseTitle.class,
        TestScreenDerivation.class,
        TestTriggersSteps.class
    })
public class process {
}
