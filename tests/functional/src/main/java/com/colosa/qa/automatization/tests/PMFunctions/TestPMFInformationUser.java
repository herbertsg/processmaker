package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFInformationUser{

	protected static int caseNum;

	@Test
	public void executePMFInformationUser() throws FileNotFoundException, IOException, Exception{

		//Init case
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("PMFInformationUser (Get Information user)");
		Pages.DynaformExecution().intoDynaform();
		//Verify results
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[userInfoGrid][1][username]"), Value.getValue(FieldKeyType.ID, "form[userName]"));
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[userInfoGrid][1][firstname]"), Value.getValue(FieldKeyType.ID, "form[firstName]"));
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[userInfoGrid][1][lastname]"), Value.getValue(FieldKeyType.ID, "form[lastName]"));
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[userInfoGrid][1][mail]"), Value.getValue(FieldKeyType.ID, "form[mail]"));
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[userInfoGrid][1][status]"), Value.getValue(FieldKeyType.ID, "form[status]"));
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[userInfoGrid][1][address]"), Value.getValue(FieldKeyType.ID, "form[address]"));
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[userInfoGrid][1][phone]"), Value.getValue(FieldKeyType.ID, "form[phone]"));
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[userInfoGrid][1][fax]"), Value.getValue(FieldKeyType.ID, "form[fax]"));
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[userInfoGrid][1][cellular]"), Value.getValue(FieldKeyType.ID, "form[cellular]"));
		Assert.assertEquals(Value.getValue(FieldKeyType.ID, "form[userInfoGrid][1][birthday]"), Value.getValue(FieldKeyType.ID, "form[birthday]"));
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("send", "");
		Assert.assertTrue("The button Continue does not exit in this form", Pages.InputDocProcess().continuebtn());	
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();

	}

//    @After
//    public void cleanup(){
//        Browser.close();
//    }

}