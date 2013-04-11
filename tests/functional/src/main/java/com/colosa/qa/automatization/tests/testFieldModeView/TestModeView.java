package com.colosa.qa.automatization.tests.testFieldModeView;

import com.colosa.qa.automatization.common.FieldType;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestModeView extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;
	protected static String textVal = "Ernesto";
	protected static String prec = "123,132,132,123.45";
	protected static String perc = "213.13 %";
	protected static String textArea = "Prueba";
	protected static String drpdwn = "Valor4";
	protected static String lstBx = "Valor3";

    public TestModeView(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testCase() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().startCase("Testeo Modo Vista (Task 1)");

	
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("Campo1", textVal);
		pages.DynaformExecution().setFieldValue("Precio1", prec);
		pages.DynaformExecution().setFieldValue("Porcentaje1", perc);
		//pages.DynaformExecution().setFieldValue("Text1", textArea);
		pages.DynaformExecution().setFieldValue("Combo1", drpdwn);
		pages.DynaformExecution().setFieldValue("Lista1", lstBx);
		pages.DynaformExecution().setFieldValue("Enviar", "", FieldType.BUTTON);

		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();


	/*}

	@Test
	public void continueTestCase() throws FileNotFoundException, IOException, Exception{*/

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("iver", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().openCase(caseNum);

		pages.DynaformExecution().intoDynaform();
		
		Assert.assertEquals(textVal, pages.DynaformExecution().getFieldValue("Campo1"));
		Assert.assertEquals(prec, pages.DynaformExecution().getFieldValue("Precio1"));
		Assert.assertEquals(perc, pages.DynaformExecution().getFieldValue("Porcentaje1"));
		//Assert.assertEquals(textArea, pages.DynaformExecution().getFieldValue("Text1"));
		//Assert.assertEquals(drpdwn, pages.DynaformExecution().getDropdownFieldText("Combo1"));
		//Assert.assertEquals(lstBx, pages.DynaformExecution().getDropdownFieldText("List1"));
		pages.DynaformExecution().setFieldValue("Enviar", "", FieldType.BUTTON);

		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/


}