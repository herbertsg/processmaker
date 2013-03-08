package com.colosa.qa.automatization.tests.javascriptExecution;

import com.colosa.qa.automatization.common.Browser;
import com.colosa.qa.automatization.pages.Pages;
import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestJavascriptExecution{

	protected static String val1 = "142424234142342412412442414242341414242414";
	protected static String val2 = "~¬·~½¬@·~|·~·";

	@Test
	public void testCase() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		Pages.Home().startCase("Proceso con java script (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("num1", val1);
		Pages.DynaformExecution().setFieldValue("num2", val2);
		Pages.DynaformExecution().setFieldValue("aceptar", "");
		Pages.DynaformExecution().sleep(15000);

	}

//    @After
//    public void cleanup(){
//        Browser.close();
//    }


}