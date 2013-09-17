package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
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
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("usernameHold", "admin");
		pages.DynaformExecution().setFieldValue("send", "");
		//Verify results

        //Assert.assertEquals(Value.getValue(browserInstance, FieldKeyType.ID, "form[userInfoGrid][1][username]"), Value.getValue(browserInstance, FieldKeyType.ID, "form[userName]"));
        Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("userInfoGrid",1, "username"), pages.DynaformExecution().getFieldValue("userName"));
        Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("userInfoGrid",1, "firstname"), pages.DynaformExecution().getFieldValue("firstName"));
        Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("userInfoGrid",1, "lastname"), pages.DynaformExecution().getFieldValue("lastName"));
        Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("userInfoGrid",1, "mail"), pages.DynaformExecution().getFieldValue("mail"));
        Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("userInfoGrid",1, "status"), pages.DynaformExecution().getFieldValue("status"));
        Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("userInfoGrid",1, "address"), pages.DynaformExecution().getFieldValue("address"));
        Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("userInfoGrid",1, "phone"), pages.DynaformExecution().getFieldValue("phone"));
        Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("userInfoGrid",1, "fax"), pages.DynaformExecution().getFieldValue("fax"));
        Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("userInfoGrid",1, "cellular"), pages.DynaformExecution().getFieldValue("cellular"));
        Assert.assertEquals(pages.DynaformExecution().getGridFieldValue("userInfoGrid",1, "birthday"), pages.DynaformExecution().getFieldValue("birthday"));

		//pages.DynaformExecution().intoDynaform();
		//pages.DynaformExecution().clickButton("send");
		//Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		//pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}