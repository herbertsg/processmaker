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

public class TestProcessIntermediateMessageSingleTask extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestProcessIntermediateMessageSingleTask(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("iver", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Event Process - Intermediate Message_Single Task (Task 1)");
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
		arrayData[3].fieldType = FieldType.READONLY;
		arrayData[3].fieldValue = "2012-12-04";
		arrayData[4].fieldPath = "form[guardar]";
		arrayData[4].fieldFindType = FieldKeyType.ID;
		arrayData[4].fieldType = FieldType.BUTTON;
		arrayData[4].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements(browserInstance, arrayData));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();

	/*} 

	@Test
	public void continueCase() throws FileNotFoundException, IOException, Exception{*/
		// Run cron 
		pages.CronExecute().execute("workflow");
		String eventStatus= "";
		pages.gotoDefaultUrl();
		pages.Login().loginUser("pablo", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Main().goAdmin();
		pages.Admin().goToLogs();
		eventStatus = pages.Admin().eventStatus(caseNum);
		Assert.assertEquals("CLOSE", eventStatus);
		pages.Main().goHome();
		pages.Home().openCase(caseNum);
		FormFieldData[] arrayData2 = new FormFieldData[1];
		arrayData2[0] = new FormFieldData();
		arrayData2[0].fieldPath = "form[guardar]";
		arrayData2[0].fieldFindType = FieldKeyType.ID;
		arrayData2[0].fieldType = FieldType.BUTTON;
		arrayData2[0].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements(browserInstance, arrayData2));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();
	}


    @After
    public void cleanup(){
        browserInstance.quit();
    }

}