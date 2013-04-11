package com.colosa.qa.automatization.tests.salesProcess;

import com.colosa.qa.automatization.common.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestEmployeeOffboarding extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestEmployeeOffboarding(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void startEnployeeBoarding()throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().startCase("Employee Offboarding v_1 (Start employee offboarding)");
		FormFieldData[] arrayData = new FormFieldData[8];
		arrayData[0] = new FormFieldData();
		arrayData[1] = new FormFieldData();
		arrayData[2] = new FormFieldData();
		arrayData[3] = new FormFieldData();
		arrayData[4] = new FormFieldData();
		arrayData[5] = new FormFieldData();
		arrayData[6] = new FormFieldData();
		arrayData[7] = new FormFieldData();
		arrayData[0].fieldPath = "form[Emp_First_Name]";
		arrayData[0].fieldFindType = FieldKeyType.ID;
		arrayData[0].fieldType = FieldType.TEXTBOX;
		arrayData[0].fieldValue = "Ademar";
		arrayData[1].fieldPath = "form[Emp_Last_Name]";
		arrayData[1].fieldFindType = FieldKeyType.ID;
		arrayData[1].fieldType = FieldType.TEXTBOX;
		arrayData[1].fieldValue = "Hurtado";
		arrayData[2].fieldPath = "form[Emp_Emp_Number]";
		arrayData[2].fieldFindType = FieldKeyType.ID;
		arrayData[2].fieldType = FieldType.TEXTBOX;
		arrayData[2].fieldValue = "xxxxxx";
		arrayData[3].fieldPath = "form[Emp_Position]";
		arrayData[3].fieldFindType = FieldKeyType.ID;
		arrayData[3].fieldType = FieldType.TEXTBOX;
		arrayData[3].fieldValue = "Aux";
		arrayData[4].fieldPath = "form[Emp_Salary]";
		arrayData[4].fieldFindType = FieldKeyType.ID;
		arrayData[4].fieldType = FieldType.TEXTBOX;
		arrayData[4].fieldValue = "3000";
		arrayData[5].fieldPath = "form[Emp_email]";
		arrayData[5].fieldFindType = FieldKeyType.ID;
		arrayData[5].fieldType = FieldType.TEXTBOX;
		arrayData[5].fieldValue = "emp@test.com";
		arrayData[6].fieldPath = "form[Last_day]";
		arrayData[6].fieldFindType = FieldKeyType.ID;
		arrayData[6].fieldType = FieldType.READONLY;
		arrayData[6].fieldValue = "12/12/2012";
		arrayData[7].fieldPath = "form[Change_Emp_Details_Submit]";
		arrayData[7].fieldFindType = FieldKeyType.ID;
		arrayData[7].fieldType = FieldType.BUTTON;
		arrayData[7].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements(browserInstance, arrayData));
		Assert.assertTrue("The button Continue does not exit in this form", browserInstance.elementExists("inputDocProcess.webelement.continue"));
		pages.InputDocProcess().continuebtn();
		pages.Main().logout();

//	}

//	@Test
//	public void registerOffBoarding() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("Jacob", "sample", "workflow", "English");
		pages.Main().goHome();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		FormFieldData[] arrayData8 = new FormFieldData[3];
		arrayData8[0] = new FormFieldData();
		arrayData8[1] = new FormFieldData();
		arrayData8[2] = new FormFieldData();
		arrayData8[0].fieldPath = "form[off_keys]";
		arrayData8[0].fieldFindType = FieldKeyType.ID;
		arrayData8[0].fieldType = FieldType.CHECK;
		arrayData8[0].fieldValue = "";
		arrayData8[1].fieldPath = "form[desk_spec_keys]";
		arrayData8[1].fieldFindType = FieldKeyType.ID;
		arrayData8[1].fieldType = FieldType.CHECK;
		arrayData8[1].fieldValue = "";
		arrayData8[2].fieldPath = "form[Change_Emp_Details_Submit]";
		arrayData8[2].fieldFindType = FieldKeyType.ID;
		arrayData8[2].fieldType = FieldType.BUTTON;
		arrayData8[2].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements(browserInstance, arrayData8));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();
//	}

//	@Test
//	public void desactiveCredentialsInventary() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("Julian", "sample", "workflow", "English");
		pages.Main().goHome();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		FormFieldData[] arrayData2 = new FormFieldData[10];
		arrayData2[0] = new FormFieldData();
		arrayData2[1] = new FormFieldData();
		arrayData2[2] = new FormFieldData();
		arrayData2[3] = new FormFieldData();
		arrayData2[4] = new FormFieldData();
		arrayData2[5] = new FormFieldData();
		arrayData2[6] = new FormFieldData();
		arrayData2[7] = new FormFieldData();
		arrayData2[8] = new FormFieldData();
		arrayData2[9] = new FormFieldData();
		arrayData2[0].fieldPath = "form[Reassign_Email]";
		arrayData2[0].fieldFindType = FieldKeyType.ID;
		arrayData2[0].fieldType = FieldType.TEXTBOX;
		arrayData2[0].fieldValue = "old@testtest.com";
		arrayData2[1].fieldPath = "form[tech_assigned][1][TA_RETURNED]";
		arrayData2[1].fieldFindType = FieldKeyType.ID;
		arrayData2[1].fieldType = FieldType.DROPDOWN;
		arrayData2[1].fieldValue = "Yes";
		arrayData2[2].fieldPath = "form[furniture_assigned][1][FURNITURE_ASSET_NO]";
		arrayData2[2].fieldFindType = FieldKeyType.ID;
		arrayData2[2].fieldType = FieldType.TEXTBOX;
		arrayData2[2].fieldValue = "785-99";
		arrayData2[3].fieldPath = "form[furniture_assigned][1][FURNITURE_DETAILS]";
		arrayData2[3].fieldFindType = FieldKeyType.ID;
		arrayData2[3].fieldType = FieldType.TEXTBOX;
		arrayData2[3].fieldValue = "Small desk";
		arrayData2[4].fieldPath = "form[furniture_assigned][1][FURNITURE_ITEM_LABEL]";
		arrayData2[4].fieldFindType = FieldKeyType.ID;
		arrayData2[4].fieldType = FieldType.TEXTBOX;
		arrayData2[4].fieldValue = "mm-5566";
		arrayData2[5].fieldPath = "form[furniture_assigned][1][FURNITURE_LOCATION]";
		arrayData2[5].fieldFindType = FieldKeyType.ID;
		arrayData2[5].fieldType = FieldType.TEXTBOX;
		arrayData2[5].fieldValue = "PB";
		arrayData2[6].fieldPath = "form[furniture_assigned][1][FURNITURE_QUAN]";
		arrayData2[6].fieldFindType = FieldKeyType.ID;
		arrayData2[6].fieldType = FieldType.TEXTBOX;
		arrayData2[6].fieldValue = "1";
		arrayData2[7].fieldPath = "form[furniture_assigned][1][F_RETURNED]";
		arrayData2[7].fieldFindType = FieldKeyType.ID;
		arrayData2[7].fieldType = FieldType.DROPDOWN;
		arrayData2[7].fieldValue = "Yes";
		arrayData2[8].fieldPath = "form[systems_assigned][1][SA_RESP]";
		arrayData2[8].fieldFindType = FieldKeyType.ID;
		arrayData2[8].fieldType = FieldType.DROPDOWN;
		arrayData2[8].fieldValue = "Yes";
		arrayData2[9].fieldPath = "form[Change_Emp_Details_Submit]";
		arrayData2[9].fieldFindType = FieldKeyType.ID;
		arrayData2[9].fieldType = FieldType.BUTTON;
		arrayData2[9].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements(browserInstance, arrayData2));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();
	}

	@Test
	public void removeEnployee() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("Jason", "sample", "workflow", "English");
		pages.Main().goHome();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		FormFieldData[] arrayData = new FormFieldData[4];
		arrayData[0] = new FormFieldData();
		arrayData[1] = new FormFieldData();
		arrayData[2] = new FormFieldData();
		arrayData[3] = new FormFieldData();
		arrayData[0].fieldPath = "form[Payroll_update1]";
		arrayData[0].fieldFindType = FieldKeyType.ID;
		arrayData[0].fieldType = FieldType.DROPDOWN;
		arrayData[0].fieldValue = "Yes";
		arrayData[1].fieldPath = "form[Payroll_update2]";
		arrayData[1].fieldFindType = FieldKeyType.ID;
		arrayData[1].fieldType = FieldType.DROPDOWN;
		arrayData[1].fieldValue = "Yes";
		arrayData[2].fieldPath = "form[Payroll_update3]";
		arrayData[2].fieldFindType = FieldKeyType.ID;
		arrayData[2].fieldType = FieldType.DROPDOWN;
		arrayData[2].fieldValue = "Yes";
		arrayData[3].fieldPath = "form[Change_Emp_Details_Submit]";
		arrayData[3].fieldFindType = FieldKeyType.ID;
		arrayData[3].fieldType = FieldType.BUTTON;
		arrayData[3].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements(browserInstance, arrayData));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}