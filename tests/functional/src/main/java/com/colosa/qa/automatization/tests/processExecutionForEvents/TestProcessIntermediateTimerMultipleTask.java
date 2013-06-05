package com.colosa.qa.automatization.tests.processExecutionForEvents;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestProcessIntermediateTimerMultipleTask extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestProcessIntermediateTimerMultipleTask(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void initProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("Brianna", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Event Process - Intermediate Timer_Multiple Task (Task 1)");
		FormFieldData[] arrayData = new FormFieldData[4];
		arrayData[0] = new FormFieldData();
		arrayData[1] = new FormFieldData();
		arrayData[2] = new FormFieldData();
		arrayData[3] = new FormFieldData();
		
		arrayData[0].fieldPath = "form[nombre]";
		arrayData[0].fieldFindType = FieldKeyType.ID;
		arrayData[0].fieldType = FieldType.TEXTBOX;
		arrayData[0].fieldValue = "Ademar";
		arrayData[1].fieldPath = "form[apellido]";
		arrayData[1].fieldFindType = FieldKeyType.ID;
		arrayData[1].fieldType = FieldType.TEXTBOX;
		arrayData[1].fieldValue = "Hurtado";
		arrayData[2].fieldPath = "form[elegir][valor3]";
		arrayData[2].fieldFindType = FieldKeyType.ID;
		arrayData[2].fieldType = FieldType.CHECK;
		arrayData[2].fieldValue = "";
		arrayData[3].fieldPath = "form[guardar]";
		arrayData[3].fieldFindType = FieldKeyType.ID;
		arrayData[3].fieldType = FieldType.BUTTON;
		arrayData[3].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements( browserInstance, arrayData));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();

//		run cron
		pages.CronExecute().execute("workflow");
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		String eventStatus= "";

		//Check if event is Closed
		pages.Main().goHome();
		pages.Main().goAdmin();
		pages.Admin().goToLogs();
		Thread.sleep(5000);	
		eventStatus = pages.Admin().eventStatus(caseNum);
		Assert.assertEquals("CLOSE", eventStatus);
		pages.Main().logout();

		//open task 2
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().openCase(caseNum);
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		//Open task 3
		//pages.Main().goHome();
		pages.Home().openCase(caseNum);
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		//Open task 4
		//pages.Main().goHome();
		pages.Home().openCase(caseNum);
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}