package com.colosa.qa.automatization;

import com.colosa.qa.automatization.tests.webEntry.TestWebEntry;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 5/3/13
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {
        TestWebEntry.class})
public class webEntry {
}
