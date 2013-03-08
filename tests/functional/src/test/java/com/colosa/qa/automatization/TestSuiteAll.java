package com.colosa.qa.automatization;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Created with IntelliJ IDEA.
 * User: Herbert Saal
 * Date: 3/4/13
 * Time: 2:27 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Suite.class)
@SuiteClasses({ TestSuite1.class, TestSuite2.class })
public class TestSuiteAll {
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Master setup");

    }

    @AfterClass public static void tearDownClass() {
        System.out.println("Master tearDown");
    }
}
