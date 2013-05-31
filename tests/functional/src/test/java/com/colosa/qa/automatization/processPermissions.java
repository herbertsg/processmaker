package com.colosa.qa.automatization;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 5/3/13
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */

import com.colosa.qa.automatization.tests.processPermissions.TestPermissions;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {
        TestPermissions.class})
public class processPermissions {
}
