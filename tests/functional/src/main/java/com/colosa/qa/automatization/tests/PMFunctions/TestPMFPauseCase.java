package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
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

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		numCase = pages.Home().gotoNewCase().startCase("Test PMFPauseCase (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("Nombre", "Felipe");
		form.setFieldValue("Apellido", "Hernandez");
		form.setFieldValue("Ingreso", "2004-08-17");
		form.setFieldValue("Salario", "2,687,886.9976");
		form.setFieldValue("Descripcion", "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba");
		form.setFieldValue("Enviar", "");
		pages.Home().gotoPaused();
		Assert.assertTrue(pages.Home().selectCase(numCase));
        //pages.InputDocProcess().switchToDefault();
        //pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}