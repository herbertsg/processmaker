package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;
import java.util.*;
import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLAProcessSelectionRoutingRule{

	protected static int numCase;
	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		numCase = Pages.Home().startCase("SLA Process Selection Routing Rule (Monto)");
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("monto", "123165465");
		Pages.DynaformExecution().setFieldValue("convertir", "");
		
		FormFieldData[] fieldArray2 = new FormFieldData[1];
		fieldArray2[0] = new FormFieldData();
		fieldArray2[0].fieldPath = "//*[@id='frmDerivation']/div/div[2]/table/tbody/tr/td/table/tbody/tr[14]/td/input";
		fieldArray2[0].fieldFindType = FieldKeyType.XPATH;
		fieldArray2[0].fieldType = FieldType.BUTTON;
		fieldArray2[0].fieldValue = "";
		FormFiller.formFillElements(fieldArray2);


		Pages.Home().openCase(numCase);
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("convertir", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.Home().openCase(numCase);
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.CronExecute().execute("workflow");

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
		Pages.Home().gotoReports();

		Pages.PmslaReport().generateReport("SLA Task with Penality Selection Rule", "All", "All", "All");
		Pages.PmslaReport().displayCases("SLA Task with Penality Selection Rule");
		String[] caseInfo = Pages.PmslaReport().getCaseInfo(numCase);
		Assert.assertEquals(caseInfo[5], "In progress");
		Pages.PmslaReport().displayTasks(numCase);
		String[] taskInfo = Pages.PmslaReport().getTaskInfo("Tipo de Cambio");
		Assert.assertEquals(taskInfo[5], "OPEN");

		Pages.Main().logout();

	}

}