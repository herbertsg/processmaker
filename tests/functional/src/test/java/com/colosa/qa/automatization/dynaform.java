package com.colosa.qa.automatization;

import com.colosa.qa.automatization.tests.dynaform.TestConditionalShowHide;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 5/3/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {
        TestConditionalShowHide.class,
        })
public class dynaform {
}
