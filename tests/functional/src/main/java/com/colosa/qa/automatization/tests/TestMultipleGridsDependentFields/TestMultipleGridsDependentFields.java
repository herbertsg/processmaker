package com.colosa.qa.automatization.tests.TestMultipleGridsDependentFields;

import com.colosa.qa.automatization.common.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestMultipleGridsDependentFields extends com.colosa.qa.automatization.tests.common.Test{

    public TestMultipleGridsDependentFields(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeProcess() throws FileNotFoundException, IOException, Exception{

		//Open process
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase();
		int caseNumber = pages.Home().gotoNewCase().startCase("Multiple grids with dependent fields (Task 1)");
		pages.InputDocProcess().openCaseFrame();
		FormFieldData[] fieldArray = new FormFieldData[10];
		fieldArray[0] = new FormFieldData();
		fieldArray[1] = new FormFieldData();
		fieldArray[2] = new FormFieldData();
		fieldArray[3] = new FormFieldData();
		fieldArray[4] = new FormFieldData();
		fieldArray[5] = new FormFieldData();
		fieldArray[6] = new FormFieldData();
		fieldArray[7] = new FormFieldData();
		fieldArray[8] = new FormFieldData();
		fieldArray[9] = new FormFieldData();
		fieldArray[0].fieldPath = "form[name]";
		fieldArray[0].fieldFindType = FieldKeyType.ID;
		fieldArray[0].fieldType = FieldType.TEXTBOX;
		fieldArray[0].fieldValue = "Richard Born";
		fieldArray[1].fieldPath = "form[university]";
		fieldArray[1].fieldFindType = FieldKeyType.ID;
		fieldArray[1].fieldType = FieldType.TEXTBOX;
		fieldArray[1].fieldValue = "UCLA";
		fieldArray[2].fieldPath = "form[age]";
		fieldArray[2].fieldFindType = FieldKeyType.ID;
		fieldArray[2].fieldType = FieldType.TEXTBOX;
		fieldArray[2].fieldValue = "23";
		fieldArray[3].fieldPath = "form[countryBirth][1][COUNTRY]";
		fieldArray[3].fieldFindType = FieldKeyType.ID;
		fieldArray[3].fieldType = FieldType.DROPDOWN;
		fieldArray[3].fieldValue = "Bolivia";
		fieldArray[4].fieldPath = "form[countryBirth][1][STATE]";
		fieldArray[4].fieldFindType = FieldKeyType.ID;
		fieldArray[4].fieldType = FieldType.DROPDOWN;
		fieldArray[4].fieldValue = "Chuquisaca";
		fieldArray[5].fieldPath = "form[countryBirth][1][LOCATION]";
		fieldArray[5].fieldFindType = FieldKeyType.ID;
		fieldArray[5].fieldType = FieldType.DROPDOWN;
		fieldArray[5].fieldValue = "Sucre";
		fieldArray[6].fieldPath = "form[countryCurrent][1][COUNTRY]";
		fieldArray[6].fieldFindType = FieldKeyType.ID;
		fieldArray[6].fieldType = FieldType.DROPDOWN;
		fieldArray[6].fieldValue = "Argentina";
		fieldArray[7].fieldPath = "form[countryCurrent][1][STATE]";
		fieldArray[7].fieldFindType = FieldKeyType.ID;
		fieldArray[7].fieldType = FieldType.DROPDOWN;
		fieldArray[7].fieldValue = "Salta";
		fieldArray[8].fieldPath = "form[countryCurrent][1][LOCATION]";
		fieldArray[8].fieldFindType = FieldKeyType.ID;
		fieldArray[8].fieldType = FieldType.DROPDOWN;
		fieldArray[8].fieldValue = "Tartagal";
		fieldArray[9].fieldPath = "form[Submit]";
		fieldArray[9].fieldFindType = FieldKeyType.ID;
		fieldArray[9].fieldType = FieldType.BUTTON;
		fieldArray[9].fieldValue = "";
		FormFiller.formFillElements( browserInstance, fieldArray);
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		//Open the next task .
		pages.InputDocProcess().switchToDefault();
		pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNumber));
		pages.Home().openCase(caseNumber);
		pages.InputDocProcess().openCaseFrame();
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[birthS]"), "BO");
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[countryResidence]"), "AR");
		FormFieldData[] fieldArray2 = new FormFieldData[1];
		fieldArray2[0] = new FormFieldData();
		fieldArray2[0].fieldPath = "form[send]";
		fieldArray2[0].fieldFindType = FieldKeyType.ID;
		fieldArray2[0].fieldType = FieldType.BUTTON;
		fieldArray2[0].fieldValue = "";
		FormFiller.formFillElements( browserInstance, fieldArray2);
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}