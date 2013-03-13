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

public class TestPMFPauseCase{

	protected static int numCase;

	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		numCase = Pages.Home().startCase("Test PMFPauseCase (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("Nombre", "Felipe");
		Pages.DynaformExecution().setFieldValue("Apellido", "Hernandez");
		Pages.DynaformExecution().setFieldValue("Ingreso", "2004-08-17");
		Pages.DynaformExecution().setFieldValue("Salario", "2,687,886.9976");
		Pages.DynaformExecution().setFieldValue("Descripcion", "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba");
		Pages.DynaformExecution().setFieldValue("Enviar", "");
		Pages.Home().gotoPaused();
		Assert.assertTrue(Pages.Home().selectCase(numCase));
        Pages.InputDocProcess().switchToDefault();
        Pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}