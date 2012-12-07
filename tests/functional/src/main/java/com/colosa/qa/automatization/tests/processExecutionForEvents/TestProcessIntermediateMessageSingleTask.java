package com.colosa.qa.automatization.tests.processExecutionForEvents;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestProcessIntermediateMessageSingleTask{

	protected static int caseNum;

	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("iver", "sample", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("Event Process - Intermediate Message_Single Task (Task 1)");
		FormFieldData[] arrayData = new FormFieldData[5];
		arrayData[0] = new FormFieldData();
		arrayData[1] = new FormFieldData();
		arrayData[2] = new FormFieldData();
		arrayData[3] = new FormFieldData();
		arrayData[4] = new FormFieldData();
		
		arrayData[0].fieldPath = "form[nombre]";
		arrayData[0].fieldFindType = FieldKeyType.ID;
		arrayData[0].fieldType = FieldType.TEXTBOX;
		arrayData[0].fieldValue = "Ernesto";
		arrayData[1].fieldPath = "form[apellido]";
		arrayData[1].fieldFindType = FieldKeyType.ID;
		arrayData[1].fieldType = FieldType.TEXTBOX;
		arrayData[1].fieldValue = "Vega";
		arrayData[2].fieldPath = "form[monto]";
		arrayData[2].fieldFindType = FieldKeyType.ID;
		arrayData[2].fieldType = FieldType.TEXTBOX;
		arrayData[2].fieldValue = "12323";
		arrayData[3].fieldPath = "form[fecha]";
		arrayData[3].fieldFindType = FieldKeyType.ID;
		arrayData[3].fieldType = FieldType.TEXTBOX;
		arrayData[3].fieldValue = "2012-12-04";
		arrayData[4].fieldPath = "form[guardar]";
		arrayData[4].fieldFindType = FieldKeyType.ID;
		arrayData[4].fieldType = FieldType.BUTTON;
		arrayData[4].fieldValue = "";
		Pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements(arrayData));
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());
		Pages.Main().logout();

	} 

	@Test
	public void continueCase() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("pablo", "sample", "");
		Pages.Main().goHome();
		Pages.Home().openCase(caseNum);
		FormFieldData[] arrayData = new FormFieldData[1];
		arrayData[0] = new FormFieldData();
		arrayData[0].fieldPath = "form[guardar]";
		arrayData[0].fieldFindType = FieldKeyType.ID;
		arrayData[0].fieldType = FieldType.BUTTON;
		arrayData[0].fieldValue = "";
		Pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements(arrayData));
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());
		Pages.Main().logout();
	}

	@Test
	public void continueCase2() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
		Pages.Home().openCase(caseNum);
		Pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());
		Pages.Main().logout();
	}

}