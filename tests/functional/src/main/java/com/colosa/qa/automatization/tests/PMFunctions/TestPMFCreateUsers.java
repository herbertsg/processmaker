package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import java.util.*;
import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;
import java.text.DecimalFormat;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFCreateUsers{

	protected static int numCase;

	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		numCase = Pages.Home().startCase("Test PMFCreateUsers (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("userName", "felipe");		
		Pages.DynaformExecution().setFieldValue("Nombre", "Felipe");
		Pages.DynaformExecution().setFieldValue("Apellido", "Hernandez");
		Pages.DynaformExecution().setFieldValue("Email", "felipe@empresa.com");
		Pages.DynaformExecution().setFieldValue("Password", "azsxdcfv");
		Pages.DynaformExecution().setFieldValue("Enviar", "");	
			
		Pages.Main().goAdmin();
		Pages.Admin().goToUsers();
		Assert.assertTrue(Pages.Admin().userExists("felipe"));	

		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

    @After
    public void cleanup(){
        Browser.close();
    }

}