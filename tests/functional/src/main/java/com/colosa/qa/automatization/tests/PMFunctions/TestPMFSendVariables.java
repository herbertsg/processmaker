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

public class TestPMFSendVariables{

	protected static int caseNum;
	protected static String dir = "Calle A esq B #FFF";
	protected static String tel = "564-651-32165465241564651";
	protected static String mail = "angela@empresa.com";

	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum=Pages.Home().startCase("Proceso1 (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("Nombre", "Angela");
		Pages.DynaformExecution().setFieldValue("Apellido", "Villegas");
		Pages.DynaformExecution().setFieldValue("Salario", "1,321,323,131,313,213.312313213");
		Pages.DynaformExecution().setFieldValue("Descripcion", "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba  Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba");
		Pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.Home().startCase("Proceso2 (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("CaseNum", Integer.toString(caseNum));
		Pages.DynaformExecution().setFieldValue("Direccion", dir);
		Pages.DynaformExecution().setFieldValue("Telefono", tel);
		Pages.DynaformExecution().setFieldValue("Email", mail);
		Pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.Home().openCase(caseNum);
		Pages.DynaformExecution().intoDynaform();
		Assert.assertEquals(Pages.DynaformExecution().getFieldValue("Direccion"), dir);
		Assert.assertEquals(Pages.DynaformExecution().getFieldValue("Telefono"), tel);
		Assert.assertEquals(Pages.DynaformExecution().getFieldValue("Email"), mail);
		Pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.DynaformExecution().outDynaform();
		Pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}