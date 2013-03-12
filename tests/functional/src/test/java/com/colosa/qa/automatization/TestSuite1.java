package com.colosa.qa.automatization;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 3/4/13
 * Time: 2:32 PM
 * To change this template use File | Settings | File Templates.
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses(value = { com.colosa.qa.automatization.tests.dependentFields.TestDependentFields.class, com.colosa.qa.automatization.tests.testRadioButton.TestRadioButton.class })
public class TestSuite1 {

}
