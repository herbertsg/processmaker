package com.colosa.qa.automatization.tests.javascriptExecution;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestJavascriptExecution extends com.colosa.qa.automatization.tests.common.Test{

	protected static String val1 = "142424234142342412412442414242341414242414";
	protected static String val2 = "~¬·~½¬@·~|·~·";

    public TestJavascriptExecution(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testCase() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("Proceso con java script (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("num1", val1);
		form.setFieldValue("num2", val2);
        form.clickButton("aceptar");
        Assert.assertEquals(form.getFieldValue("res"), "NaN");
        form.setFieldValue("num1", "1000");
        form.setFieldValue("num2", "123");
        form.clickButton("aceptar");
        Assert.assertEquals(form.getFieldValue("res"), "1123");
        form.setFieldValue("num1", "2000");
        form.setFieldValue("num2", "-1");
        form.clickButton("aceptar");
        Assert.assertEquals(form.getFieldValue("res"), "1999");
        form.setFieldValue("num1", "200");
        form.setFieldValue("num2", "-1500");
        form.clickButton("aceptar");
        Assert.assertEquals(form.getFieldValue("res"), "-1300");
        form.setFieldValue("num1", "-100");
        form.setFieldValue("num2", "-100");
        form.clickButton("aceptar");
        Assert.assertEquals(form.getFieldValue("res"), "-200");

		//form.sleep(15000);
        //pages.InputDocProcess().switchToDefault();
        //pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}