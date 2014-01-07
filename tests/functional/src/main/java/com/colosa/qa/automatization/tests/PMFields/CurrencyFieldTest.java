package com.colosa.qa.automatization.tests.PMFields;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import com.colosa.qa.automatization.pages.Inbox;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CurrencyFieldTest extends com.colosa.qa.automatization.tests.common.Test{

    public CurrencyFieldTest(String browserName) throws IOException {
        super(browserName);
    }

    @Test
 	public void runProcess() throws Exception{
 		pages.gotoDefaultUrl();
   	    pages.Login().loginUser("admin","admin","workflow", "English");
        pages.Main().goHome();
        int casenumber=pages.Home().gotoNewCase().startCase("TestCurrency (Task 1)");
        pages.DynaformExecution().intoDynaform();

        //point separator
        pages.DynaformExecution().setFieldValue("CURRENCY_BEFORE", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_AFTER", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_BOTH", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_OTHERB", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_OTHERA", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_OTHER", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_NEGATIVE", "-123456789.02");

        //comma separator
        pages.DynaformExecution().setFieldValue("CURRENCY_BEFORE2", "123456789,02");
        pages.DynaformExecution().setFieldValue("CURRENCY_AFTER2", "123456789,02");
        pages.DynaformExecution().setFieldValue("CURRENCY_BOTH2", "123456789,02");
        pages.DynaformExecution().setFieldValue("CURRENCY_OTHERB2", "123456789,02");
        pages.DynaformExecution().setFieldValue("CURRENCY_OTHERA2", "123456789,02");
        pages.DynaformExecution().setFieldValue("CURRENCY_OTHER2", "123456789,02");
        pages.DynaformExecution().setFieldValue("CURRENCY_NEGATIVE2", "-123456789,02");

        pages.DynaformExecution().clickButton("SUBMIT");

        pages.AssignTask().pressContinueButton();

        //Inbox inboxPage = pages.Home().gotoInbox();
        //Assert.assertTrue("The case does not exist in inbox", pages.Home().gotoInbox().existCase(casenumber));
        pages.Home().gotoInbox().openCase(casenumber);
        pages.DynaformExecution().intoDynaform();

        Assert.assertEquals("Mask Before does not exist", "$us 123,456,789.02", pages.DynaformExecution().getFieldProperty("CURRENCY_BEFORE","value"));
        Assert.assertEquals("Mask After does not exist", "123,456,789.02 $us", pages.DynaformExecution().getFieldProperty("CURRENCY_AFTER","value"));
        Assert.assertEquals("Mask Both does not exist", "$us 123,456,789.02 $us", pages.DynaformExecution().getFieldProperty("CURRENCY_BOTH","value"));
        Assert.assertEquals("Numeric Mask does not exist", "€ 123,456,789.02", pages.DynaformExecution().getFieldProperty("CURRENCY_OTHERB","value"));
        Assert.assertEquals("Symbol Mask does not exist", "123,456,789.02 @@", pages.DynaformExecution().getFieldProperty("CURRENCY_OTHERA","value"));
        Assert.assertEquals("String Mask does not exist", "$us 123,456,789.02 AB", pages.DynaformExecution().getFieldProperty("CURRENCY_OTHER","value"));
        Assert.assertEquals("Negative Mask does not exist", "-123,456,789.02 $", pages.DynaformExecution().getFieldProperty("CURRENCY_NEGATIVE","value"));

        Assert.assertEquals("Mask Before does not exist", "$us 123.456.789,02", pages.DynaformExecution().getFieldProperty("CURRENCY_BEFORE2","value"));
        Assert.assertEquals("Mask After does not exist", "123.456.789,02 $us", pages.DynaformExecution().getFieldProperty("CURRENCY_AFTER2","value"));
        Assert.assertEquals("Mask Both does not exist", "$us 123.456.789,02 $us", pages.DynaformExecution().getFieldProperty("CURRENCY_BOTH2","value"));
        Assert.assertEquals("Numeric Mask does not exist", "€ 123.456.789,02", pages.DynaformExecution().getFieldProperty("CURRENCY_OTHERB2","value"));
        Assert.assertEquals("Symbol Mask does not exist", "123.456.789,02 @@", pages.DynaformExecution().getFieldProperty("CURRENCY_OTHERA2","value"));
        Assert.assertEquals("String Mask does not exist", "$us 123.456.789,02 AB", pages.DynaformExecution().getFieldProperty("CURRENCY_OTHER2","value"));
        Assert.assertEquals("Negative Mask does not exist", "-123.456.789,02 $", pages.DynaformExecution().getFieldProperty("CURRENCY_NEGATIVE2","value"));

        //pages.DynaformExecution().clickButton("SUBMIT");

        //pages.AssignTask().pressContinueButton();

        //pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}