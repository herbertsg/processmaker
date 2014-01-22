package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.pages.CaseNote;
import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFGetCaseNotes extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestPMFGetCaseNotes(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executePMFGetCaseNotes() throws FileNotFoundException, IOException, Exception{

		//Init case
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("PMFGetCaseNotes (Add notes)");
        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
        CaseNote caseNotePage = form.openCasesNotes();
        caseNotePage.addCaseNote("Test1");
        caseNotePage.addCaseNote("Test2");
        caseNotePage.addCaseNote("Test3");
        caseNotePage.addCaseNote("Test4");
        caseNotePage.closeWindow();
        pages.AssignTask().pressContinueButton();
        pages.Inbox().openCase(caseNum);


        form.intoDynaform();
        //Verify results

        Assert.assertEquals(form.getGridFieldValue("gridNotes", 1, "NOTE_CONTENT"), form.getGridFieldValue("gridQuery", 4, "NOTE_CONTENT"));
        Assert.assertEquals(form.getGridFieldValue("gridNotes", 2, "NOTE_CONTENT"), form.getGridFieldValue("gridQuery", 3, "NOTE_CONTENT"));
        Assert.assertEquals(form.getGridFieldValue("gridNotes", 3, "NOTE_CONTENT"), form.getGridFieldValue("gridQuery", 2, "NOTE_CONTENT"));
        Assert.assertEquals(form.getGridFieldValue("gridNotes", 4, "NOTE_CONTENT"), form.getGridFieldValue("gridQuery", 1, "NOTE_CONTENT"));

        /*Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[gridNotes][1][NOTE_CONTENT]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[gridQuery][4][NOTE_CONTENT]"));
        Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[gridNotes][2][NOTE_CONTENT]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[gridQuery][3][NOTE_CONTENT]"));
        Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[gridNotes][3][NOTE_CONTENT]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[gridQuery][2][NOTE_CONTENT]"));
        Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[gridNotes][4][NOTE_CONTENT]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[gridQuery][1][NOTE_CONTENT]"));
        */



        /*

        FormFieldData[] fieldArray=new FormFieldData[1];
		fieldArray[0]=new FormFieldData();
		fieldArray[0].fieldPath="//*[@id='caseNotes']/tbody/tr[2]/td[2]/em/button";
		fieldArray[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray[0].fieldType=FieldType.BUTTON;
		fieldArray[0].fieldValue="";
		form.sleep(10000);
        FormFiller.formFillElements( browserInstance, fieldArray);
        FormFieldData[] fieldArray2=new FormFieldData[1];
		fieldArray2[0]=new FormFieldData();
		fieldArray2[0].fieldPath="//*[@id='addCancelBtn']/tbody/tr[2]/td[2]/em/button";
		fieldArray2[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray2[0].fieldType=FieldType.BUTTON;
		fieldArray2[0].fieldValue="";
        FormFiller.formFillElements( browserInstance, fieldArray2);
         FormFieldData[] fieldArray3=new FormFieldData[1];
		fieldArray3[0]=new FormFieldData();
		fieldArray3[0].fieldPath="caseNoteText";
		fieldArray3[0].fieldFindType=FieldKeyType.ID;
		fieldArray3[0].fieldType=FieldType.TEXTAREA;
		fieldArray3[0].fieldValue="Test1";
        FormFiller.formFillElements( browserInstance, fieldArray3);
        FormFieldData[] fieldArray33=new FormFieldData[1];
		fieldArray33[0]=new FormFieldData();
		fieldArray33[0].fieldPath="//*[@id='sendBtn']/tbody/tr[2]/td[2]/em/button";
		fieldArray33[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray33[0].fieldType=FieldType.BUTTON;
		fieldArray33[0].fieldValue="";
        FormFiller.formFillElements( browserInstance, fieldArray33);
		form.sleep(10000);
        FormFiller.formFillElements( browserInstance, fieldArray2);
         FormFieldData[] fieldArray4=new FormFieldData[1];
		fieldArray4[0]=new FormFieldData();
		fieldArray4[0].fieldPath="caseNoteText";
		fieldArray4[0].fieldFindType=FieldKeyType.ID;
		fieldArray4[0].fieldType=FieldType.TEXTAREA;
		fieldArray4[0].fieldValue="Test2";
        FormFiller.formFillElements( browserInstance, fieldArray4);
		form.sleep(5000);
        FormFiller.formFillElements( browserInstance, fieldArray33);
 		form.sleep(10000);
        FormFiller.formFillElements( browserInstance, fieldArray2);
        FormFieldData[] fieldArray5=new FormFieldData[1];
		fieldArray5[0]=new FormFieldData();
		fieldArray5[0].fieldPath="caseNoteText";
		fieldArray5[0].fieldFindType=FieldKeyType.ID;
		fieldArray5[0].fieldType=FieldType.TEXTAREA;
		fieldArray5[0].fieldValue="Test3";
        FormFiller.formFillElements( browserInstance, fieldArray5);
		form.sleep(5000);
        FormFiller.formFillElements( browserInstance, fieldArray33);
 		form.sleep(10000);
        FormFiller.formFillElements( browserInstance, fieldArray2);
        FormFieldData[] fieldArray6=new FormFieldData[1];
		fieldArray6[0]=new FormFieldData();
		fieldArray6[0].fieldPath="caseNoteText";
		fieldArray6[0].fieldFindType=FieldKeyType.ID;
		fieldArray6[0].fieldType=FieldType.TEXTAREA;
		fieldArray6[0].fieldValue="Test4";
        FormFiller.formFillElements( browserInstance, fieldArray6);
		form.sleep(5000);
        FormFiller.formFillElements( browserInstance, fieldArray33);
		form.sleep(6000);
        FormFieldData[] fieldArray44=new FormFieldData[1];
		fieldArray44[0]=new FormFieldData();
		fieldArray44[0].fieldPath="//*[@id='caseNotesWindowPanel']/div[1]/div[1]/div[1]/div[1]/div[1]";
		fieldArray44[0].fieldFindType=FieldKeyType.XPATH;
		fieldArray44[0].fieldType=FieldType.BUTTON;
		fieldArray44[0].fieldValue="";
        FormFiller.formFillElements( browserInstance, fieldArray44);
		form.intoDynaform();
 		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
 		form.outDynaform();
		pages.Main().logout();
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		form.intoDynaform();
		//Verify results
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[gridNotes][1][NOTE_CONTENT]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[gridQuery][4][NOTE_CONTENT]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[gridNotes][2][NOTE_CONTENT]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[gridQuery][3][NOTE_CONTENT]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[gridNotes][3][NOTE_CONTENT]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[gridQuery][2][NOTE_CONTENT]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[gridNotes][4][NOTE_CONTENT]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[gridQuery][1][NOTE_CONTENT]"));
		form.intoDynaform();
		form.setFieldValue("send", "");
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
		*/
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}