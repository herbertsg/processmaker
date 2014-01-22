package com.colosa.qa.automatization.tests.testsJavascript;

import com.colosa.qa.automatization.pages.DynaformExecution;
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
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();

		form.setFieldValue("Nombre", "Favian");
		form.setFieldValue("Salario", "231,321,321,321,231,321,321.4564");
		form.setFieldValue("Dropdwn", "Valor4");
		form.setFieldValue("Descripcion", "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba");
		form.setFieldValue("cargar", "");

		Assert.assertEquals(form.getGridFieldValue("grd1", 1, "Nombre"), form.getFieldValue("Nombre"));
		Assert.assertEquals(form.getGridFieldValue("grd1", 1, "Salario"), form.getFieldValue("Salario"));
		Assert.assertEquals(form.getGridFieldValue("grd1", 1, "Dropdwn"), form.getFieldValue("Dropdwn"));
		Assert.assertEquals(form.getGridFieldValue("grd1", 1, "Descripcion"), form.getFieldValue("Descripcion"));
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}


    @After
    public void cleanup(){
        browserInstance.quit();
    }


}