package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFGroupList extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFGroupList(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testCase() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("PMF Group List (Task 1)");
		//pages.DynaformExecution().sleep(15000);
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}