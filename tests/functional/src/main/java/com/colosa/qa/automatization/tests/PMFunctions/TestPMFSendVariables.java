package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.pages.DynaformExecution;
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
		caseNum=pages.Home().gotoNewCase().startCase("Test PMFSendVariables 1 (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("Nombre", "Angela");
		form.setFieldValue("Apellido", "Villegas");
		form.setFieldValue("Salario", "1,321,323,131,313,213.312313213");
		form.setFieldValue("Descripcion", "Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba  Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba Prueba");
		form.clickButton("Enviar");
		pages.AssignTask().pressContinueButton();

		pages.Home().gotoNewCase().startCase("Test PMFSendVariables 2 (Task 1)");

        form.intoDynaform();
		form.setFieldValue("CaseNum", Integer.toString(caseNum));
		form.setFieldValue("Direccion", dir);
		form.setFieldValue("Telefono", tel);
		form.setFieldValue("Email", mail);
        form.clickButton("Enviar");
		pages.AssignTask().pressContinueButton();

		pages.Home().gotoInbox().openCase(caseNum);

        form.intoDynaform();
		Assert.assertEquals(form.getFieldValue("Direccion"), dir);
		Assert.assertEquals(form.getFieldValue("Telefono"), tel);
		Assert.assertEquals(form.getFieldValue("Email"), mail);
		//form.setFieldValue("Enviar", "");
		//Assert.assertTrue(pages.InputDocProcess().continuebtn());

		//form.outDynaform();
		//pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}