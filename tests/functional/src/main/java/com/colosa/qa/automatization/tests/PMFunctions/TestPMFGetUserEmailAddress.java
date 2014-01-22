package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFGetUserEmailAddress extends com.colosa.qa.automatization.tests.common.Test{


    public TestPMFGetUserEmailAddress(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("Test PMFGetUserEmailAddress (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("Nombre", "Felipe");
		form.setFieldValue("Apellido", "Hernandez");
		form.setFieldValue("Email", "felipe@empresaxxx.com");
				
		form.setFieldValue("Enviar", "");
		Assert.assertEquals(form.getFieldValue("Envio"), "qatest@colosa.com");
		form.setFieldValue("Enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		//form.sleep(15000);
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}