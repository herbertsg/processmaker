package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.After;
import org.junit.Test;
import java.util.*;
import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;
import java.text.DecimalFormat;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFGetUserEmailAddress{

	
	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		Pages.Home().startCase("Test PMFGetUserEmailAddress (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("Nombre", "Felipe");
		Pages.DynaformExecution().setFieldValue("Apellido", "Hernandez");
		Pages.DynaformExecution().setFieldValue("Email", "felipe@empresaxxx.com");
				
		Pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertEquals(Pages.DynaformExecution().getFieldValue("Envio"), "qatest@colosa.com");
		Pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());
		Pages.DynaformExecution().sleep(15000);
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}