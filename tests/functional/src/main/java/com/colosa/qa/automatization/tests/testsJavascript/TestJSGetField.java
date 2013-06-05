package com.colosa.qa.automatization.tests.testsJavascript;

import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestJSGetField extends com.colosa.qa.automatization.tests.common.Test{

    public TestJSGetField(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("JS getField (Task 1)");
		pages.DynaformExecution().intoDynaform();

		pages.DynaformExecution().setFieldValue("Nombre", "Favian");
		pages.DynaformExecution().setFieldValue("Salario", "231,321,321,321,231,321,321.4564");
		pages.DynaformExecution().setFieldValue("Dropdwn", "Valor4");
		pages.DynaformExecution().setFieldValue("Descripcion", "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba");
		pages.DynaformExecution().setFieldValue("cargar", "");

		Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("grd1", 1, "Nombre"), pages.DynaformExecution().getFieldValue("Nombre"));
		Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("grd1", 1, "Salario"), pages.DynaformExecution().getFieldValue("Salario"));
		Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("grd1", 1, "Dropdwn"), pages.DynaformExecution().getFieldValue("Dropdwn"));
		Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("grd1", 1, "Descripcion"), pages.DynaformExecution().getFieldValue("Descripcion"));
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}


    @After
    public void cleanup(){
        browserInstance.quit();
    }


}