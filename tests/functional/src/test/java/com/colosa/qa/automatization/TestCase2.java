package com.colosa.qa.automatization;

import com.colosa.qa.automatization.common.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: herbert
 * Date: 3/4/13
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestCase2 {
    @BeforeClass
    public static void setUpClass() {
        Logger.addLog("TestCase2 setup");
    }

    @Test
    public void test1() {
        assertEquals(2 , 2);
    }
}
