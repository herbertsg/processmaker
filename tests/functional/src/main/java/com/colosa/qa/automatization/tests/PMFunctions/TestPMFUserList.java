package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFUserList extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFUserList(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testCase() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("PMFUserList (Task 1)");
        pages.DynaformExecution().intoDynaform();

        //verify returned data of grids
        String countUsersFunction = pages.DynaformExecution().getFieldValue("countUsersFunction");
        String countUsersQuery = pages.DynaformExecution().getFieldValue("countUsers");

        Assert.assertEquals("Invalid number of users returned PMFUserList", countUsersFunction, countUsersQuery);

		//pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}