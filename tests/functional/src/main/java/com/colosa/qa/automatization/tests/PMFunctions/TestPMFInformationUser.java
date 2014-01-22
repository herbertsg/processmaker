package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.Value;
import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFInformationUser extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestPMFInformationUser(String browserName) throws IOException {
        super(browserName);
    }
    @Before
    public void setup(){

    }
    @After
    public void cleanup(){
        browserInstance.quit();
    }

    @Test
	public void executePMFInformationUser() throws FileNotFoundException, IOException, Exception{

		//Init case
        pages.gotoDefaultUrl();
        pages.Login().loginUser("admin", "admin", "workflow", "English");
        pages.Main().goHome();
        caseNum = pages.Home().gotoNewCase().startCase("PMFInformationUser (Get Information user)");
        DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
		//Verify results
        Assert.assertEquals("Invalid UserName.", form.getGridFieldValue("userInfoGrid", 1, "username"), form.getFieldValue("userName"));
        Assert.assertEquals("Invalid UserName.", form.getGridFieldValue("userInfoGrid", 1, "firstname"), form.getFieldValue("firstName"));
        Assert.assertEquals("Invalid LastName", form.getGridFieldValue("userInfoGrid", 1, "lastname"),form.getFieldValue("lastName") );
        Assert.assertEquals("Invalid Mail", form.getGridFieldValue("userInfoGrid", 1, "mail"),form.getFieldValue("mail") );
        Assert.assertEquals("Invalid status", form.getGridFieldValue("userInfoGrid", 1, "status"),form.getFieldValue("status") );
        Assert.assertEquals("Invalid Address", form.getGridFieldValue("userInfoGrid", 1, "address"),form.getFieldValue("address") );
        Assert.assertEquals("Invalid Phone", form.getGridFieldValue("userInfoGrid", 1, "phone"),form.getFieldValue("phone") );
        Assert.assertEquals("Invalid Fax", form.getGridFieldValue("userInfoGrid", 1, "fax"),form.getFieldValue("fax") );
        Assert.assertEquals("Invalid Cellular", form.getGridFieldValue("userInfoGrid", 1, "cellular"),form.getFieldValue("cellular") );
        Assert.assertEquals("Invalid birthday", form.getGridFieldValue("userInfoGrid", 1, "birthday"),form.getFieldValue("birthday") );
        //pages.Main().logout();

	}



}