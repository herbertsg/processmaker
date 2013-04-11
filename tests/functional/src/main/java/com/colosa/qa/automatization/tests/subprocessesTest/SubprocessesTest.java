package com.colosa.qa.automatization.tests.subprocessesTest;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SubprocessesTest extends com.colosa.qa.automatization.tests.common.Test{
	protected static int caseNum;
	protected static String name = "Ernesto";
	protected static String lastName = "Vega";
	protected static String salary = "23,564.00";
	protected static String desc = "Prueba...";

    public SubprocessesTest(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().startCase("Subprocess Test 1 (Task 1)");
       	caseNum++;
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("Nombre", "");
        pages.DynaformExecution().setFieldValue("Nombre", lastName);
        pages.DynaformExecution().setFieldValue("Salario", salary);
        pages.DynaformExecution().setFieldValue("Enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();
	/*}

	@Test
	public void openCaseNum() throws FileNotFoundException, IOException, Exception{*/

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("iver", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().openCase(caseNum);
        pages.DynaformExecution().intoDynaform();

        Assert.assertEquals(lastName, pages.DynaformExecution().getFieldValue("lastname"));
        Assert.assertEquals(salary, pages.DynaformExecution().getFieldValue("salary"));
        Assert.assertEquals(desc, pages.DynaformExecution().getField("descripcion"));
		pages.DynaformExecution().setFieldValue("Send", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();
	/*}

	@Test
	public void endCase() throws FileNotFoundException, IOException, Exception{*/
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("hector", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().openCase(caseNum);

		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("Send", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/
}