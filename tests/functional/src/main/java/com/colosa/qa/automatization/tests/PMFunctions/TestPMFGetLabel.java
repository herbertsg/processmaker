package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFGetLabel extends com.colosa.qa.automatization.tests.common.Test{

	protected static String dropdwn = "Valor3";
	protected static String list = "Valor4";
	protected static String radio = "Valor5";
	protected static String check = "Valor2";
	protected static String	check2 = "Valor4";
    protected static String check3 = "Valor5";
    protected static String checkGroup = "";

    public TestPMFGetLabel(String browserName) throws IOException {
        super(browserName);
    }
    @Before
    public void setup(){

    }

    @After
    public void cleanup(){
        browserInstance.quit();
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("PMF GetLabel (Task 1)");
		pages.DynaformExecution().intoDynaform();
		//pages.DynaformExecution().setFieldValue("Nombre", "Felipe");
		//pages.DynaformExecution().setFieldValue("Apellido", "Hernandez");
		//pages.DynaformExecution().setFieldValue("Ingreso", "2004-08-17");
		//pages.DynaformExecution().setFieldValue("Salario", "2,687,886.9976");
		pages.DynaformExecution().setFieldValue("dropdwn", dropdwn);
		//pages.DynaformExecution().setFieldValue("Descripcion", "Prueba Prueba Prueba ");
		pages.DynaformExecution().setFieldValue("List", list);
		pages.DynaformExecution().setCheckBoxGroup("radio1", radio);
        Thread.sleep(5000);
		pages.DynaformExecution().setCheckBoxGroup("check1", check);
        Thread.sleep(5000);
		pages.DynaformExecution().setCheckBoxGroup("check1", check2);
        Thread.sleep(5000);
		pages.DynaformExecution().setCheckBoxGroup("check1", check3);
        Thread.sleep(5000);
		pages.DynaformExecution().setFieldValue("Enviar", "");
        Thread.sleep(5000);
		checkGroup = check + " " + check2 + " " + check3 + " ";
        Thread.sleep(5000);

		/*Assert.assertEquals(pages.DynaformExecution().getFieldValue("Recover"), dropdwn);
		Assert.assertEquals(pages.DynaformExecution().getFieldValue("lstValue"), list);
		Assert.assertEquals(pages.DynaformExecution().getFieldValue("rdValue"), radio);
		Assert.assertEquals(pages.DynaformExecution().getFieldValue("chkValue"), checkGroup);
		//pages.DynaformExecution().sleep(15000);
		//pages.InputDocProcess().switchToDefault();
		pages.Main().logout(); */
	}


}