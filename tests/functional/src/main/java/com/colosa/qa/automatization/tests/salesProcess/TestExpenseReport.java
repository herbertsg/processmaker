package com.colosa.qa.automatization.tests.salesProcess;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestExpenseReport extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestExpenseReport(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void advanceCash()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("Benjamin", "sample", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Expense Report v_2 (Cash Advance)");
		FormFieldData[] arrayData = new FormFieldData[4];
		arrayData[0] = new FormFieldData();
		arrayData[1] = new FormFieldData();
		arrayData[2] = new FormFieldData();
		arrayData[3] = new FormFieldData();
		arrayData[0].fieldPath = "form[repSubject]";
		arrayData[0].fieldFindType = FieldKeyType.ID;
		arrayData[0].fieldType = FieldType.TEXTBOX;
		arrayData[0].fieldValue = "Purchase cable";
		arrayData[1].fieldPath = "form[repRelatedto]";
		arrayData[1].fieldFindType = FieldKeyType.ID;
		arrayData[1].fieldType = FieldType.TEXTBOX;
		arrayData[1].fieldValue = "Purchase 100 mts of cable.";
		arrayData[2].fieldPath = "form[repCashAdvanced]";
		arrayData[2].fieldFindType = FieldKeyType.ID;
		arrayData[2].fieldType = FieldType.TEXTBOX;
		arrayData[2].fieldValue = "250";
		arrayData[3].fieldPath = "form[repBut]";
		arrayData[3].fieldFindType = FieldKeyType.ID;
		arrayData[3].fieldType = FieldType.BUTTON;
		arrayData[3].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		FormFiller.formFillElements( browserInstance, arrayData);
		FormFieldData[] arrayData2 = new FormFieldData[2];
		arrayData2[0] = new FormFieldData();
		arrayData2[1] = new FormFieldData();
		arrayData2[0].fieldPath = "form[TASKS][1][USR_UID]";
		arrayData2[0].fieldFindType = FieldKeyType.ID;
		arrayData2[0].fieldType = FieldType.DROPDOWN;
		arrayData2[0].fieldValue = "Ciarleglio, Brianna";
		arrayData2[1].fieldPath = "btnContinue";
		arrayData2[1].fieldFindType = FieldKeyType.ID;
		arrayData2[1].fieldType = FieldType.BUTTON;
		arrayData2[1].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements( browserInstance, arrayData2));
		pages.Main().logout();

//	}

//	@Test
//	@Depends("#advanceCash")
//	public void approveAdvanceCash() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("Brianna", "sample", "workflow", "English");
		pages.Main().goHome();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		FormFieldData[] arrayData3 = new FormFieldData[2];
		arrayData3[0] = new FormFieldData();
		arrayData3[1] = new FormFieldData();
		arrayData3[0].fieldPath = "form[repCashApr]";
		arrayData3[0].fieldFindType = FieldKeyType.ID;
		arrayData3[0].fieldType = FieldType.DROPDOWN;
		arrayData3[0].fieldValue = "Yes";
		arrayData3[1].fieldPath = "form[repBut]";
		arrayData3[1].fieldFindType = FieldKeyType.ID;
		arrayData3[1].fieldType = FieldType.BUTTON;
		arrayData3[1].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements( browserInstance, arrayData3));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();
//	}

//	@Test
//	@Depends("#approveAdvanceCash")
//	public void reportExpenses() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("Benjamin", "sample", "workflow", "English");
		pages.Main().goHome();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		FormFieldData[] arrayData7 = new FormFieldData[17];
		arrayData7[0] = new FormFieldData();
		arrayData7[1] = new FormFieldData();
		arrayData7[2] = new FormFieldData();
		arrayData7[3] = new FormFieldData();
		arrayData7[4] = new FormFieldData();
		arrayData7[5] = new FormFieldData();
		arrayData7[6] = new FormFieldData();
		arrayData7[7] = new FormFieldData();
		arrayData7[8] = new FormFieldData();
		arrayData7[9] = new FormFieldData();
		arrayData7[10] = new FormFieldData();
		arrayData7[11] = new FormFieldData();
		arrayData7[12] = new FormFieldData();
		arrayData7[13] = new FormFieldData();
		arrayData7[14] = new FormFieldData();
		arrayData7[15] = new FormFieldData();
		arrayData7[16] = new FormFieldData();
		arrayData7[0].fieldPath = "form[repDetail][1][dateRep]";
		arrayData7[0].fieldFindType = FieldKeyType.ID;
		arrayData7[0].fieldType = FieldType.READONLY;
		arrayData7[0].fieldValue = "12/12/2012";
		arrayData7[1].fieldPath = "form[repDetail][1][categoryRep]";
		arrayData7[1].fieldFindType = FieldKeyType.ID;
		arrayData7[1].fieldType = FieldType.DROPDOWN;
		arrayData7[1].fieldValue = "Meals";
		arrayData7[2].fieldPath = "form[repDetail][1][expenseRep1]";
		arrayData7[2].fieldFindType = FieldKeyType.ID;
		arrayData7[2].fieldType = FieldType.DROPDOWN;
		arrayData7[2].fieldValue = "Lunch";
		arrayData7[3].fieldPath = "form[repDetail][1][descRep]";
		arrayData7[3].fieldFindType = FieldKeyType.ID;
		arrayData7[3].fieldType = FieldType.TEXTBOX;
		arrayData7[3].fieldValue = "Lunch";
		arrayData7[4].fieldPath = "form[repDetail][1][receiptRep]";
		arrayData7[4].fieldFindType = FieldKeyType.ID;
		arrayData7[4].fieldType = FieldType.DROPDOWN;
		arrayData7[4].fieldValue = "YES";
		arrayData7[5].fieldPath = "form[repDetail][1][amountRep]";
		arrayData7[5].fieldFindType = FieldKeyType.ID;
		arrayData7[5].fieldType = FieldType.TEXTBOX;
		arrayData7[5].fieldValue = "100";
		arrayData7[6].fieldPath = "form[repDetail][1][repBillable]";
		arrayData7[6].fieldFindType = FieldKeyType.ID;
		arrayData7[6].fieldType = FieldType.DROPDOWN;
		arrayData7[6].fieldValue = "Yes";
		arrayData7[7].fieldPath = "form[repDetail][1][cost_center]";
		arrayData7[7].fieldFindType = FieldKeyType.ID;
		arrayData7[7].fieldType = FieldType.DROPDOWN;
		arrayData7[7].fieldValue = "Sales";
		arrayData7[8].fieldPath = "form[repDetail][2][dateRep]";
		arrayData7[8].fieldFindType = FieldKeyType.ID;
		arrayData7[8].fieldType = FieldType.READONLY;
		arrayData7[8].fieldValue = "31/12/2012";
		arrayData7[9].fieldPath = "form[repDetail][2][categoryRep]";
		arrayData7[9].fieldFindType = FieldKeyType.ID;
		arrayData7[9].fieldType = FieldType.DROPDOWN;
		arrayData7[9].fieldValue = "Meals";
		arrayData7[10].fieldPath = "form[repDetail][2][expenseRep1]";
		arrayData7[10].fieldFindType = FieldKeyType.ID;
		arrayData7[10].fieldType = FieldType.DROPDOWN;
		arrayData7[10].fieldValue = "Lunch";
		arrayData7[11].fieldPath = "form[repDetail][2][descRep]";
		arrayData7[11].fieldFindType = FieldKeyType.ID;
		arrayData7[11].fieldType = FieldType.TEXTBOX;
		arrayData7[11].fieldValue = "Lunch";
		arrayData7[12].fieldPath = "form[repDetail][2][receiptRep]";
		arrayData7[12].fieldFindType = FieldKeyType.ID;
		arrayData7[12].fieldType = FieldType.DROPDOWN;
		arrayData7[12].fieldValue = "YES";
		arrayData7[13].fieldPath = "form[repDetail][2][amountRep]";
		arrayData7[13].fieldFindType = FieldKeyType.ID;
		arrayData7[13].fieldType = FieldType.TEXTBOX;
		arrayData7[13].fieldValue = "150";
		arrayData7[14].fieldPath = "form[repDetail][2][repBillable]";
		arrayData7[14].fieldFindType = FieldKeyType.ID;
		arrayData7[14].fieldType = FieldType.DROPDOWN;
		arrayData7[14].fieldValue = "Yes";
		arrayData7[15].fieldPath = "form[repDetail][2][cost_center]";
		arrayData7[15].fieldFindType = FieldKeyType.ID;
		arrayData7[15].fieldType = FieldType.DROPDOWN;
		arrayData7[15].fieldValue = "Sales";
		arrayData7[16].fieldPath = "form[repBut]";
		arrayData7[16].fieldFindType = FieldKeyType.ID;
		arrayData7[16].fieldType = FieldType.BUTTON;
		arrayData7[16].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		WebElement btnLink= browserInstance.findElementById("form[repDetail][addLink]");
		btnLink.click();
		Assert.assertTrue(FormFiller.formFillElements( browserInstance, arrayData7));
		FormFieldData[] arrayData8 = new FormFieldData[1];
		arrayData8[0] = new FormFieldData();
		arrayData8[0].fieldPath = "form[BTN_SUBMIT]";
		arrayData8[0].fieldFindType = FieldKeyType.ID;
		arrayData8[0].fieldType = FieldType.BUTTON;
		arrayData8[0].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements( browserInstance, arrayData8));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();
//	}

//	@Test
//	@Depends("#reportExpenses")
//	public void approveReport() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("Ian", "sample", "workflow", "English");
		pages.Main().goHome();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		FormFieldData[] arrayData9 = new FormFieldData[3];
		arrayData9[0] = new FormFieldData();
		arrayData9[1] = new FormFieldData();
		arrayData9[2] = new FormFieldData();
		arrayData9[0].fieldPath = "form[repDetail][1][commentRepSup]";
		arrayData9[0].fieldFindType = FieldKeyType.ID;
		arrayData9[0].fieldType = FieldType.TEXTBOX;
		arrayData9[0].fieldValue = "4 people";
		arrayData9[1].fieldPath = "form[repDetail][2][commentRepSup]";
		arrayData9[1].fieldFindType = FieldKeyType.ID;
		arrayData9[1].fieldType = FieldType.TEXTBOX;
		arrayData9[1].fieldValue = "6 people";
		arrayData9[2].fieldPath = "form[repBut]";
		arrayData9[2].fieldFindType = FieldKeyType.ID;
		arrayData9[2].fieldType = FieldType.BUTTON;
		arrayData9[2].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements( browserInstance, arrayData9));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.Main().logout();
//	}

//	@Test
//	@Depends("#approveReport")
//	public void reinburseExpenses() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("Jason", "sample", "workflow", "English");
		pages.Main().goHome();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		FormFieldData[] arrayData10 = new FormFieldData[2];
		arrayData10[0] = new FormFieldData();
		arrayData10[1] = new FormFieldData();
		arrayData10[0].fieldPath = "form[repPayType]";
		arrayData10[0].fieldFindType = FieldKeyType.ID;
		arrayData10[0].fieldType = FieldType.DROPDOWN;
		arrayData10[0].fieldValue = "Cash";
		arrayData10[1].fieldPath = "form[repBut]";
		arrayData10[1].fieldFindType = FieldKeyType.ID;
		arrayData10[1].fieldType = FieldType.BUTTON;
		arrayData10[1].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements( browserInstance, arrayData10));
		FormFieldData[] arrayData11 = new FormFieldData[1];
		arrayData11[0] = new FormFieldData();
		arrayData11[0].fieldPath = "form[NEXT_STEP]";
		arrayData11[0].fieldFindType = FieldKeyType.ID;
		arrayData11[0].fieldType = FieldType.BUTTON;
		arrayData11[0].fieldValue = "";
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue(FormFiller.formFillElements( browserInstance, arrayData11));
		Assert.assertTrue(pages.InputDocProcess().continuebtn());
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}