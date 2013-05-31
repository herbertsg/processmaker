package com.colosa.qa.automatization;

import com.colosa.qa.automatization.tests.plugins.TestExternalStep;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 5/3/13
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {
        TestExternalStep.class,
        })
public class plugin {
}
