package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestFunctionPMFDerivateCase extends com.colosa.qa.automatization.tests.common.Test{

    public TestFunctionPMFDerivateCase(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeProcess() throws FileNotFoundException, IOException, Exception{

		//Open process
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase();
		int caseNumber = pages.Home().startCase("PMFDerivateCase (Task 1)");
		pages.InputDocProcess().openCaseFrame();
		FormFieldData[] fieldArray = new FormFieldData[3];
		fieldArray[0] = new FormFieldData();
		fieldArray[1] = new FormFieldData();
		fieldArray[2] = new FormFieldData();
		fieldArray[0].fieldPath = "form[nombre]";
		fieldArray[0].fieldFindType = FieldKeyType.ID;
		fieldArray[0].fieldType = FieldType.TEXTBOX;
		fieldArray[0].fieldValue = "Ademar";
		fieldArray[1].fieldPath = "form[monto]";
		fieldArray[1].fieldFindType = FieldKeyType.ID;
		fieldArray[1].fieldType = FieldType.TEXTBOX;
		fieldArray[1].fieldValue = "3000";
		fieldArray[2].fieldPath = "form[send]";
		fieldArray[2].fieldFindType = FieldKeyType.ID;
		fieldArray[2].fieldType = FieldType.BUTTON;
		fieldArray[2].fieldValue = "";
		FormFiller.formFillElements(browserInstance, fieldArray);
		//Open the next task .
		pages.InputDocProcess().switchToDefault();
		pages.Home().gotoInbox();
		//Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNumber));
		pages.Home().openCase(caseNumber);
		pages.InputDocProcess().openCaseFrame();
		FormFieldData[] fieldArray2 = new FormFieldData[3];
		fieldArray2[0] = new FormFieldData();
		fieldArray2[1] = new FormFieldData();
		fieldArray2[2] = new FormFieldData();
		fieldArray2[0].fieldPath = "form[aprobadopor]";
		fieldArray2[0].fieldFindType = FieldKeyType.ID;
		fieldArray2[0].fieldType = FieldType.TEXTBOX;
		fieldArray2[0].fieldValue = "Supervisor";
		fieldArray2[1].fieldPath = "form[requiere]";
		fieldArray2[1].fieldFindType = FieldKeyType.ID;
		fieldArray2[1].fieldType = FieldType.DROPDOWN;
		fieldArray2[1].fieldValue = "NO";
		fieldArray2[2].fieldPath = "form[enviar]";
		fieldArray2[2].fieldFindType = FieldKeyType.ID;
		fieldArray2[2].fieldType = FieldType.BUTTON;
		fieldArray2[2].fieldValue = "";
		FormFiller.formFillElements(browserInstance, fieldArray2);
		//Open the next task to verify if the DerivateCase function works fine 
		pages.InputDocProcess().switchToDefault();
		pages.Home().gotoInbox();
		//Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNumber));
		pages.Home().openCase(caseNumber);
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		pages.Main().logout();
}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/


}