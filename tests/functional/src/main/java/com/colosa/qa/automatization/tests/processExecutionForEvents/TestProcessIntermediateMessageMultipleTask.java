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

public class TestProcessIntermediateMessageMultipleTask extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestProcessIntermediateMessageMultipleTask(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("iver", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Event Process - Intermediate Message_Multiple Task (Task 1)");
		FormFieldData[] arrayData = new FormFieldData[4];
		arrayData[0] = new FormFieldData();
		arrayData[1] = new FormFieldData();
		arrayData[2] = new FormFieldData();
		arrayData[3] = new FormFieldData();
		
		arrayData[0].fieldPath = "form[nombre]";
		arrayData[0].fieldFindType = FieldKeyType.ID;
		arrayData[0].fieldType = FieldType.TEXTBOX;
		arrayData[0].fieldValue = "Ernesto";
		arrayData[1].fieldPath = "form[edad]";
		arrayData[1].fieldFindType = FieldKeyType.ID;
		arrayData[1].fieldType = FieldType.TEXTBOX;
		arrayData[1].fieldValue = "24";
		arrayData[2].fieldPath = "form[observaciones]";
		arrayData[2].fieldFindType = FieldKeyType.ID;
		arrayData[2].fieldType = FieldType.TEXTBOX;
		arrayData[2].fieldValue = "Prueba.....";
		arrayData[3].fieldPath = "form[guardar]";
		arrayData[3].fieldFindType = FieldKeyType.ID;
		arrayData[3].fieldType = FieldType.BUTTON;
		arrayData[3].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements(browserInstance, arrayData));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();

	/*}

	@Test
	public void continueCase() throws FileNotFoundException, IOException, Exception{*/
		String eventStatus= "";
		pages.CronExecute().execute("workflow");
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("hector", "sample", "workflow", "English");
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
		pages.Main().logout();
	/*}

	@Test
	public void continueCase2() throws FileNotFoundException, IOException, Exception{*/

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().openCase(caseNum);
		FormFieldData[] arrayData3 = new FormFieldData[1];
		arrayData3[0] = new FormFieldData();
		arrayData3[0].fieldPath = "form[guardar]";
		arrayData3[0].fieldFindType = FieldKeyType.ID;
		arrayData3[0].fieldType = FieldType.BUTTON;
		arrayData3[0].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements(browserInstance, arrayData3));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();
	/*}


	@Test
	public void continueCase3() throws FileNotFoundException, IOException, Exception{*/

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("ronald", "sample", "workflow", "English");
		pages.Main().goHome();
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