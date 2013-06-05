package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFSendVariables extends com.colosa.qa.automatization.tests.common.Test{

	protected int caseNum;
	protected String dir = "Calle A esq B #FFF";
	protected String tel = "564-651-32165465241564651";
	protected String mail = "angela@empresa.com";

    public TestPMFSendVariables(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum=pages.Home().gotoNewCase().startCase("Proceso1 (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("Nombre", "Angela");
		pages.DynaformExecution().setFieldValue("Apellido", "Villegas");
		pages.DynaformExecution().setFieldValue("Salario", "1,321,323,131,313,213.312313213");
		pages.DynaformExecution().setFieldValue("Descripcion", "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba  Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba");
		pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().gotoNewCase().startCase("Proceso2 (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("CaseNum", Integer.toString(caseNum));
		pages.DynaformExecution().setFieldValue("Direccion", dir);
		pages.DynaformExecution().setFieldValue("Telefono", tel);
		pages.DynaformExecution().setFieldValue("Email", mail);
		pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		Assert.assertEquals(pages.DynaformExecution().getFieldValue("Direccion"), dir);
		Assert.assertEquals(pages.DynaformExecution().getFieldValue("Telefono"), tel);
		Assert.assertEquals(pages.DynaformExecution().getFieldValue("Email"), mail);
		pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}