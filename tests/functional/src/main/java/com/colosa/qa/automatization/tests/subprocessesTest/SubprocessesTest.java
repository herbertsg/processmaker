package com.colosa.qa.automatization.tests.subprocessesTest;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SubprocessesTest extends com.colosa.qa.automatization.tests.common.Test{
	protected int caseNum;
	protected String name = "Ernesto";
	protected String lastName = "Vega";
	protected String salary = "23,564.00";
	protected String desc = "Prueba...";

    public SubprocessesTest(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Subprocess Test 1 (Task 1)");
       	caseNum++;
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("Nombre", "");
        form.setFieldValue("Nombre", lastName);
        form.setFieldValue("Salario", salary);
        form.setFieldValue("Enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();
	/*}

	@Test
	public void openCaseNum() throws FileNotFoundException, IOException, Exception{*/

		pages.gotoDefaultUrl();
		pages.Login().loginUser("iver", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoInbox().openCase(caseNum);

        form.intoDynaform();

        Assert.assertEquals(lastName, form.getFieldValue("lastname"));
        Assert.assertEquals(salary, form.getFieldValue("salary"));
        Assert.assertEquals(desc, form.getFieldValue("descripcion"));
		form.setFieldValue("Send", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();
	/*}

	@Test
	public void endCase() throws FileNotFoundException, IOException, Exception{*/
		pages.gotoDefaultUrl();
		pages.Login().loginUser("hector", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoInbox().openCase(caseNum);


        form.intoDynaform();
		form.setFieldValue("Send", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}