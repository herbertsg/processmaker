package com.colosa.qa.automatization.tests.testsJavascript;

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

public class TestJSGetField{

	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		Pages.Home().startCase("JS getField (Task 1)");
		Pages.DynaformExecution().intoDynaform();

		Pages.DynaformExecution().setFieldValue("Nombre", "Favian");
		Pages.DynaformExecution().setFieldValue("Salario", "231,321,321,321,231,321,321.4564");
		Pages.DynaformExecution().setFieldValue("Dropdwn", "Valor4");
		Pages.DynaformExecution().setFieldValue("Descripcion", "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba");
		Pages.DynaformExecution().setFieldValue("cargar", "");

		Assert.assertEquals(Pages.DynaformExecution().getGridFieldValue("grd1", 1, "Nombre"), Pages.DynaformExecution().getFieldValue("Nombre"));
		Assert.assertEquals(Pages.DynaformExecution().getGridFieldValue("grd1", 1, "Salario"), Pages.DynaformExecution().getFieldValue("Salario"));
		Assert.assertEquals(Pages.DynaformExecution().getGridFieldValue("grd1", 1, "Dropdwn"), Pages.DynaformExecution().getFieldValue("Dropdwn"));
		Assert.assertEquals(Pages.DynaformExecution().getGridFieldValue("grd1", 1, "Descripcion"), Pages.DynaformExecution().getFieldValue("Descripcion"));
		Pages.DynaformExecution().sleep(15000);
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

    @After
    public void cleanup(){
        Browser.close();
    }

}