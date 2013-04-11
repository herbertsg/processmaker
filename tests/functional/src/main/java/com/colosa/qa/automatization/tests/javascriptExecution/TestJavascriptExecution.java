package com.colosa.qa.automatization.tests.javascriptExecution;

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

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().startCase("Proceso con java script (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("num1", val1);
		pages.DynaformExecution().setFieldValue("num2", val2);
		pages.DynaformExecution().setFieldValue("aceptar", "");
		pages.DynaformExecution().sleep(15000);
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/


}