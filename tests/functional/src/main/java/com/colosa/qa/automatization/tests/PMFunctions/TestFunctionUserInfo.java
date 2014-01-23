package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestFunctionUserInfo extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;
	protected static String firstNameMysql;
	protected static String lastNameMysql;
	protected static String firstNameOracle;
	protected static String lastNameOracle;

    public TestFunctionUserInfo(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeProcess() throws FileNotFoundException, IOException, Exception{

		//Init case
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("UserInfo (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		form.setFieldValue("usernameHold", "admin");
		form.clickButton("send");
		//Verify results
        DynaformExecution form2 = pages.DynaformExecution();
        form2.intoDynaform();
        //Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][username]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[userName]"));
        Assert.assertEquals(form2.getGridFieldValue("userInfoGrid",1, "username"), form2.getFieldValue("userName"));
        Assert.assertEquals(form2.getGridFieldValue("userInfoGrid",1, "firstname"), form2.getFieldValue("firstName"));
        Assert.assertEquals(form2.getGridFieldValue("userInfoGrid",1, "lastname"), form2.getFieldValue("lastName"));
        Assert.assertEquals(form2.getGridFieldValue("userInfoGrid",1, "mail"), form2.getFieldValue("mail"));
        Assert.assertEquals(form2.getGridFieldValue("userInfoGrid",1, "status"), form2.getFieldValue("status"));
        Assert.assertEquals(form2.getGridFieldValue("userInfoGrid",1, "address"), form2.getFieldValue("address"));
        Assert.assertEquals(form2.getGridFieldValue("userInfoGrid",1, "phone"), form2.getFieldValue("phone"));
        Assert.assertEquals(form2.getGridFieldValue("userInfoGrid",1, "fax"), form2.getFieldValue("fax"));
        Assert.assertEquals(form2.getGridFieldValue("userInfoGrid",1, "cellular"), form2.getFieldValue("cellular"));
        Assert.assertEquals(form2.getGridFieldValue("userInfoGrid",1, "birthday"), form2.getFieldValue("birthday"));

		//DynaformExecution form = pages.DynaformExecution();
        //form.intoDynaform();
		//form.clickButton("send");
		//Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		//pages.InputDocProcess().switchToDefault();
		//pages.Main().logout();
}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}