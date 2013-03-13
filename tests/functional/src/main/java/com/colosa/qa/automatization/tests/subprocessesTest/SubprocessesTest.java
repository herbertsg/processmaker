package com.colosa.qa.automatization.tests.subprocessesTest;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SubprocessesTest{
	protected static int caseNum;
	protected static String name = "Ernesto";
	protected static String lastName = "Vega";
	protected static String salary = "23,564.00";
	protected static String desc = "Prueba...";

	@Test
	public void runProcess() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("Subprocess Test 1 (Task 1)");
       	caseNum++;
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("Nombre", "");
        Pages.DynaformExecution().setFieldValue("Nombre", lastName);
        Pages.DynaformExecution().setFieldValue("Salario", salary);
        Pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());
		Pages.Main().logout();
	/*}

	@Test
	public void openCaseNum() throws FileNotFoundException, IOException, Exception{*/

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("iver", "sample", "");
		Pages.Main().goHome();
		Pages.Home().openCase(caseNum);
        Pages.DynaformExecution().intoDynaform();

        Assert.assertEquals(lastName, Pages.DynaformExecution().getFieldValue("lastname"));
        Assert.assertEquals(salary, Pages.DynaformExecution().getFieldValue("salary"));
        Assert.assertEquals(desc, Pages.DynaformExecution().getField("descripcion"));
		Pages.DynaformExecution().setFieldValue("Send", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());
		Pages.Main().logout();
	/*}

	@Test
	public void endCase() throws FileNotFoundException, IOException, Exception{*/
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("hector", "sample", "");
		Pages.Main().goHome();
		Pages.Home().openCase(caseNum);

		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("Send", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/
}