package com.colosa.qa.automatization.tests.PMFunctions;

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

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().startCase("Test PMFGetUserEmailAddress (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("Nombre", "Felipe");
		pages.DynaformExecution().setFieldValue("Apellido", "Hernandez");
		pages.DynaformExecution().setFieldValue("Email", "felipe@empresaxxx.com");
				
		pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertEquals(pages.DynaformExecution().getFieldValue("Envio"), "qatest@colosa.com");
		pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.DynaformExecution().sleep(15000);
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}