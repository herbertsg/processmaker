package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFInformationUser extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestPMFInformationUser(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executePMFInformationUser() throws FileNotFoundException, IOException, Exception{

		//Init case
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("PMFInformationUser (Get Information user)");
		pages.DynaformExecution().intoDynaform();
		//Verify results
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][username]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[userName]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][firstname]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[firstName]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][lastname]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[lastName]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][mail]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[mail]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][status]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[status]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][address]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[address]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][phone]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[phone]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][fax]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[fax]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][cellular]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[cellular]"));
		Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][birthday]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[birthday]"));
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("send", "");
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();

	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}