package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFPauseCase extends com.colosa.qa.automatization.tests.common.Test{

	protected static int numCase;

    public TestPMFPauseCase(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		numCase = pages.Home().startCase("Test PMFPauseCase (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("Nombre", "Felipe");
		pages.DynaformExecution().setFieldValue("Apellido", "Hernandez");
		pages.DynaformExecution().setFieldValue("Ingreso", "2004-08-17");
		pages.DynaformExecution().setFieldValue("Salario", "2,687,886.9976");
		pages.DynaformExecution().setFieldValue("Descripcion", "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba");
		pages.DynaformExecution().setFieldValue("Enviar", "");
		pages.Home().gotoPaused();
		Assert.assertTrue(pages.Home().selectCase(numCase));
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}