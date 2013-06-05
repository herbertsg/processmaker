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

        pages.DynaformExecution().setFieldValue("CURRENCY_BEFORE", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_AFTER", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_BOTH", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_OTHERB", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_OTHERA", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_OTHER", "123456789.02");
        pages.DynaformExecution().setFieldValue("CURRENCY_NEGATIVE", "-123456789.02");
        pages.DynaformExecution().clickButton("SUBMIT");

        pages.AssignTask().pressContinueButton();

        //Inbox inboxPage = pages.Home().gotoInbox();
        //Assert.assertTrue("The case does not exist in inbox", pages.Home().gotoInbox().existCase(casenumber));
        pages.Home().gotoInbox().openCase(casenumber);
        pages.DynaformExecution().intoDynaform();

		String fieldCURRENCY_BEFORE = pages.DynaformExecution().getFieldProperty("CURRENCY_BEFORE","value");
		String fieldCURRENCY_AFTER = pages.DynaformExecution().getFieldProperty("CURRENCY_AFTER","value");
		String fieldCURRENCY_BOTH = pages.DynaformExecution().getFieldProperty("CURRENCY_BOTH","value");
		
		String fieldOTHER_BEFORE = pages.DynaformExecution().getFieldProperty("CURRENCY_OTHERB","value");
		String fieldOTHER_AFTER = pages.DynaformExecution().getFieldProperty("CURRENCY_OTHERA","value");
		String fieldOTHER_BOTH = pages.DynaformExecution().getFieldProperty("CURRENCY_OTHER","value");
		
		String fieldCURRENCY_NEGATIVE = pages.DynaformExecution().getFieldProperty("CURRENCY_NEGATIVE","value");

        Assert.assertEquals("Mask Before does not exist", "$us 123,456,789.02", fieldCURRENCY_BEFORE);
        Assert.assertEquals("Mask After does not exist", "123,456,789.02 $us", fieldCURRENCY_AFTER);
        Assert.assertEquals("Mask Both does not exist", "$us 123,456,789.02 $us", fieldCURRENCY_BOTH);

        Assert.assertEquals("Numeric Mask does not exist", "€ 123,456,789.02", fieldOTHER_BEFORE);
        Assert.assertEquals("Symbol Mask does not exist", "123,456,789.02 @@", fieldOTHER_AFTER);
        Assert.assertEquals("String Mask does not exist", "$us 123,456,789.02 AB", fieldOTHER_BOTH);

        Assert.assertEquals("Negative Mask does not exist", "-123,456,789.02 $", fieldCURRENCY_NEGATIVE);

        pages.DynaformExecution().clickButton("SUBMIT");

        pages.AssignTask().pressContinueButton();

        pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}