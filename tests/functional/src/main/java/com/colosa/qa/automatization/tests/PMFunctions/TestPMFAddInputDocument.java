package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFAddInputDocument extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFAddInputDocument(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("Bug 8283 - PMFAddInputDocument function request (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("Nombre", "Felipe");
		pages.DynaformExecution().setFieldValue("Apellido", "Hernandez");
		pages.DynaformExecution().setFieldValue("Descripcion", "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba");
		pages.DynaformExecution().setFieldValue("send", "");
		Assert.assertTrue(pages.InputDocumentList().fileExists("bug8283.txt"));

		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}