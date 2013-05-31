package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Utils;
import com.colosa.qa.automatization.common.Value;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestFunctionExecuteQueryMysqlOracle extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;
	protected static String firstNameMysql;
	protected static String lastNameMysql;
	protected static String firstNameOracle;
	protected static String lastNameOracle;

    public TestFunctionExecuteQueryMysqlOracle(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeProcess() throws FileNotFoundException, IOException, Exception{

		//Init case
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("ExecuteQuery Mysql -Oracle (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("usern", "admin");
		pages.DynaformExecution().setFieldValue("userNameOracle", "xxx");
		pages.DynaformExecution().setFieldValue("send", "");
	    pages.AssignTask().pressContinueButton();
		pages.DynaformExecution().outDynaform();

		//Open task 2 .
		firstNameMysql = "Administrator";
		lastNameMysql = " " ;
		firstNameOracle = "FNOracle-" + Utils.getRandomString(5);
		lastNameOracle = "LNOracle-" + Utils.getRandomString(5);
		pages.InputDocProcess().switchToDefault();
		pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("userFirstName", firstNameMysql);
		pages.DynaformExecution().setFieldValue("userLastName", lastNameMysql);
		pages.DynaformExecution().setFieldValue("firstNameOracle", firstNameOracle);
		pages.DynaformExecution().setFieldValue("lastNameOracle", lastNameOracle);
		pages.DynaformExecution().setFieldValue("send", "");
		//Form with the updates
		Assert.assertEquals(firstNameMysql, Value.getValue(browserInstance, FieldKeyType.ID, "form[firstNameUpdate]"));
		Assert.assertEquals(lastNameMysql, Value.getValue(browserInstance, FieldKeyType.ID, "form[lastNameUpdate]"));
		Assert.assertEquals(firstNameOracle, Value.getValue(browserInstance, FieldKeyType.ID, "form[firstNameOracleUpdate]"));
		Assert.assertEquals(lastNameOracle, Value.getValue(browserInstance, FieldKeyType.ID, "form[lastNameUpateOracle]"));
		Assert.assertEquals(firstNameMysql, Value.getValue(browserInstance, FieldKeyType.ID, "form[updatesAll][1][firstName]"));
		Assert.assertEquals(lastNameMysql, Value.getValue(browserInstance, FieldKeyType.ID, "form[updatesAll][1][lastName]"));
		Assert.assertEquals(firstNameOracle, Value.getValue(browserInstance, FieldKeyType.ID, "form[updatesAll][2][firstName]"));
		Assert.assertEquals(lastNameOracle, Value.getValue(browserInstance, FieldKeyType.ID, "form[updatesAll][2][lastName]"));
		pages.DynaformExecution().setFieldValue("continue", "");
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		//Open the last task 
		pages.InputDocProcess().switchToDefault();
		pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.InputDocProcess().openCaseFrame();
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}