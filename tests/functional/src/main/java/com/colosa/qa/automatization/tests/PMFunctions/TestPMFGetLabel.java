package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;
import java.util.*;
import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;
import java.text.DecimalFormat;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFGetLabel{

	protected static String dropdwn = "Valor3";
	protected static String list = "Valor4";
	protected static String radio = "Valor5";
	protected static String check = "Valor2";
	protected static String	check2 = "Valor4";
	protected static String check3 = "Valor5";
	protected static String checkGroup = "";


	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		Pages.Home().startCase("PMF GetLabel (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("Nombre", "Felipe");
		Pages.DynaformExecution().setFieldValue("Apellido", "Hernandez");
		Pages.DynaformExecution().setFieldValue("Ingreso", "2004-08-17");
		Pages.DynaformExecution().setFieldValue("Salario", "2,687,886.9976");
		Pages.DynaformExecution().setFieldValue("dropdwn", dropdwn);
		Pages.DynaformExecution().setFieldValue("Descripcion", "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba");
		Pages.DynaformExecution().setFieldValue("List", list);
		Pages.DynaformExecution().setCheckBoxGroup("radio1", radio);
		Pages.DynaformExecution().setCheckBoxGroup("check1", check);
		Pages.DynaformExecution().setCheckBoxGroup("check1", check2);
		Pages.DynaformExecution().setCheckBoxGroup("check1", check3);		
		Pages.DynaformExecution().setFieldValue("Enviar", "");
		checkGroup = check + " " + check2 + " " + check3 + " ";

		Assert.assertEquals(Pages.DynaformExecution().getFieldValue("Recover"), dropdwn);
		Assert.assertEquals(Pages.DynaformExecution().getFieldValue("lstValue"), list);
		Assert.assertEquals(Pages.DynaformExecution().getFieldValue("rdValue"), radio);
		Assert.assertEquals(Pages.DynaformExecution().getFieldValue("chkValue"), checkGroup);
		Pages.DynaformExecution().sleep(15000);
		Browser.close();

	}

}