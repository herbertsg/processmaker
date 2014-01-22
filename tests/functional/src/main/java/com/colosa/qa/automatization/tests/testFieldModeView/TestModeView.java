package com.colosa.qa.automatization.tests.testFieldModeView;

import com.colosa.qa.automatization.pages.DynaformExecution;
import com.colosa.qa.automatization.common.FieldType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestModeView extends com.colosa.qa.automatization.tests.common.Test{

	protected int caseNum;
	protected String textVal = "Ernesto";
	protected String prec = "123,132,132,123.45";
	protected String perc = "213.13 %";
	protected String textArea = "Prueba";
	protected String drpdwn = "Valor4";
	protected String lstBx = "Valor3";

    public TestModeView(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testCase() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
        DynaformExecution form = pages.DynaformExecution();
        //form.intoDynaform();
        form.outDynaform();
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Testeo Modo Vista (Task 1)");

	

        form.intoDynaform();
		form.setFieldValue("Campo1", textVal);
		form.setFieldValue("Precio1", prec);
		form.setFieldValue("Porcentaje1", perc);
		//form.setFieldValue("Text1", textArea);
		form.setFieldValue("Combo1", drpdwn);
		form.setFieldValue("Lista1", lstBx);
		form.clickButton("Enviar");

		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();


	/*}

	@Test
	public void continueTestCase() throws FileNotFoundException, IOException, Exception{*/

		pages.gotoDefaultUrl();
		pages.Login().loginUser("iver", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoInbox().openCase(caseNum);


        form.intoDynaform();
		
		Assert.assertEquals(textVal, form.getFieldValue("Campo1"));
		Assert.assertEquals(prec, form.getFieldValue("Precio1"));
		Assert.assertEquals(perc, form.getFieldValue("Porcentaje1"));
		//Assert.assertEquals(textArea, form.getFieldValue("Text1"));
		//Assert.assertEquals(drpdwn, form.getDropdownFieldText("Combo1"));
		//Assert.assertEquals(lstBx, form.getDropdownFieldText("List1"));
		form.clickButton("Enviar");

		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}