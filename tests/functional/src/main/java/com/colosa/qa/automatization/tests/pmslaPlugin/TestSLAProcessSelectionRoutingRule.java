package com.colosa.qa.automatization.tests.pmslaPlugin;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLAProcessSelectionRoutingRule extends com.colosa.qa.automatization.tests.common.Test{

	protected static int numCase;

    public TestSLAProcessSelectionRoutingRule(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		numCase = pages.Home().startCase("SLA Process Selection Routing Rule (Monto)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("monto", "123165465");
		pages.DynaformExecution().setFieldValue("convertir", "");
		
		FormFieldData[] fieldArray2 = new FormFieldData[1];
		fieldArray2[0] = new FormFieldData();
		fieldArray2[0].fieldPath = "//*[@id='frmDerivation']/div/div[2]/table/tbody/tr/td/table/tbody/tr[14]/td/input";
		fieldArray2[0].fieldFindType = FieldKeyType.XPATH;
		fieldArray2[0].fieldType = FieldType.BUTTON;
		fieldArray2[0].fieldValue = "";
		FormFiller.formFillElements(browserInstance, fieldArray2);


		pages.Home().openCase(numCase);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("convertir", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.Home().openCase(numCase);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoReports();

		pages.PmslaReport().generateReport("SLA Task with Penality Selection Rule", "All", "All", "All");
		pages.PmslaReport().displayCases("SLA Task with Penality Selection Rule");
		String[] caseInfo = pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo[5], "In progress");
		pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo = pages.PmslaReport().getTaskInfo("Tipo de cambio");
		Assert.assertEquals(taskInfo[5], "OPEN");

        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

	}
 /*   @After
    public void cleanup(){
        Browser.close();
    }*/
}