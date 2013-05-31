package com.colosa.qa.automatization.tests.derivationRulesCombinations;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DerivationRulesSelection extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public DerivationRulesSelection(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void derivationRulesSelection() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - selection (Init)");
		pages.DynaformExecution().intoDynaform();
		FormFieldData[] fieldArray = new FormFieldData[1];
		fieldArray[0] = new FormFieldData();
		fieldArray[0].fieldPath = "//*[@id='frmDerivation']/div/div[2]/table/tbody/tr/td/table/tbody/tr[8]/td/input";
		fieldArray[0].fieldFindType = FieldKeyType.XPATH;
		fieldArray[0].fieldType = FieldType.BUTTON;
		fieldArray[0].fieldValue = "";
		FormFiller.formFillElements( browserInstance, fieldArray);
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
	    //cyclical task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("chris", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("TASKS][2][USR_UID", "Swan, William");
		FormFieldData[] fieldArray2 = new FormFieldData[1];
		fieldArray2[0] = new FormFieldData();
		fieldArray2[0].fieldPath = "//*[@id='frmDerivation']/div/div[2]/table/tbody/tr/td/table/tbody/tr[13]/td/input";
		fieldArray2[0].fieldFindType = FieldKeyType.XPATH;
		fieldArray2[0].fieldType = FieldType.BUTTON;
		fieldArray2[0].fieldValue = "";
		FormFiller.formFillElements( browserInstance, fieldArray2);
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Manual task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("william", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("name", "Charles Puyol");
		pages.DynaformExecution().setFieldValue("amount", "3000");
		pages.DynaformExecution().setFieldValue("send", "");
		FormFieldData[] fieldArray3 = new FormFieldData[1];
		fieldArray3[0] = new FormFieldData();
		fieldArray3[0].fieldPath = "//*[@id='frmDerivation']/div/div[2]/table/tbody/tr/td/table/tbody/tr[8]/td/input";
		fieldArray3[0].fieldFindType = FieldKeyType.XPATH;
		fieldArray3[0].fieldType = FieldType.BUTTON;
		fieldArray3[0].fieldValue = "";
		FormFiller.formFillElements( browserInstance, fieldArray3);
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Value based task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("ezequiel", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		FormFieldData[] fieldArray4 = new FormFieldData[1];
		fieldArray4[0] = new FormFieldData();
		fieldArray4[0].fieldPath = "//*[@id='frmDerivation']/div/div[2]/table/tbody/tr/td/table/tbody/tr[8]/td/input";
		fieldArray4[0].fieldFindType = FieldKeyType.XPATH;
		fieldArray4[0].fieldType = FieldType.BUTTON;
		fieldArray4[0].fieldValue = "";
		FormFiller.formFillElements( browserInstance, fieldArray4);
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Report to task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("zachary", "sample", "workflow", "English");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		FormFieldData[] fieldArray5 = new FormFieldData[1];
		fieldArray5[0] = new FormFieldData();
		fieldArray5[0].fieldPath = "//*[@id='frmDerivation']/div/div[2]/table/tbody/tr/td/table/tbody/tr[8]/td/input";
		fieldArray5[0].fieldFindType = FieldKeyType.XPATH;
		fieldArray5[0].fieldType = FieldType.BUTTON;
		fieldArray5[0].fieldValue = "";
		FormFiller.formFillElements( browserInstance, fieldArray5);
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Self service task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("chris", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoUnassigned();
		Assert.assertTrue("The case does not exist in Unassigned", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("BTN_CATCH", "");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("send", "");
		FormFieldData[] fieldArray6 = new FormFieldData[1];
		fieldArray6[0] = new FormFieldData();
		fieldArray6[0].fieldPath = "//*[@id='frmDerivation']/div/div[2]/table/tbody/tr/td/table/tbody/tr[8]/td/input";
		fieldArray6[0].fieldFindType = FieldKeyType.XPATH;
		fieldArray6[0].fieldType = FieldType.BUTTON;
		fieldArray6[0].fieldValue = "";
		FormFiller.formFillElements( browserInstance, fieldArray6);
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Self Service Value Based task
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("william", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoUnassigned();
		Assert.assertTrue("The case does not exist in Unassigned", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("BTN_CATCH", "");
		pages.DynaformExecution().intoDynaform();
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		//Open cases to verify Cyclical assigmnent
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - selection (Init)");
		pages.DynaformExecution().intoDynaform();
		FormFieldData[] fieldArray8 = new FormFieldData[1];
		fieldArray8[0] = new FormFieldData();
		fieldArray8[0].fieldPath = "//*[@id='frmDerivation']/div/div[2]/table/tbody/tr/td/table/tbody/tr[8]/td/input";
		fieldArray8[0].fieldFindType = FieldKeyType.XPATH;
		fieldArray8[0].fieldType = FieldType.BUTTON;
		fieldArray8[0].fieldValue = "";
		FormFiller.formFillElements( browserInstance, fieldArray8);
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("Derivation rules - selection (Init)");
		pages.DynaformExecution().intoDynaform();
		FormFieldData[] fieldArray9 = new FormFieldData[1];
		fieldArray9[0] = new FormFieldData();
		fieldArray9[0].fieldPath = "//*[@id='frmDerivation']/div/div[2]/table/tbody/tr/td/table/tbody/tr[8]/td/input";
		fieldArray9[0].fieldFindType = FieldKeyType.XPATH;
		fieldArray9[0].fieldType = FieldType.BUTTON;
		fieldArray9[0].fieldValue = "";
		FormFiller.formFillElements( browserInstance, fieldArray9);
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}