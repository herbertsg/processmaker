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

public class TestPMFCreateUsers extends com.colosa.qa.automatization.tests.common.Test{

	protected static int numCase;

    public TestPMFCreateUsers(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		numCase = pages.Home().gotoNewCase().startCase("Test PMFCreateUsers (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("userName", "felipe");
		form.setFieldValue("Nombre", "Felipe");
		form.setFieldValue("Apellido", "Hernandez");
		form.setFieldValue("Email", "felipe@empresa.com");
		form.setFieldValue("Password", "azsxdcfv");
		form.setFieldValue("Enviar", "");
			
		pages.Main().goAdmin();
		pages.Admin().goToUsers();
		Assert.assertTrue(pages.Admin().userExists("felipe"));

		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}