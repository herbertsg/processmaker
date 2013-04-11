package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFUserList extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFUserList(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testCase() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().startCase("PMF User List (Task 1)");
		pages.DynaformExecution().sleep(15000);
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}