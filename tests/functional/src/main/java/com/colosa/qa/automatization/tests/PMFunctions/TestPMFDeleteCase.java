package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFDeleteCase extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestPMFDeleteCase(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executePMFDeleteCase() throws FileNotFoundException, IOException, Exception{

		//Init case
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("PMFDeleteCase (Delete case)");
		pages.Main().goHome();
    	pages.Home().gotoInbox();
     	pages.Home().gotoDraft();
 		Assert.assertTrue("The case does not exist in Draft", pages.Home().existCase(caseNum));
		pages.Main().goHome();
		caseNum = pages.Home().gotoNewCase().startCase("PMFDeleteCase (Delete case)");
		pages.DynaformExecution().intoDynaform();
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		pages.Main().goHome();
    	pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		pages.Main().goHome();
     	pages.Home().gotoDraft();
  		Assert.assertFalse("The case exist in Draft", pages.Home().existCase(caseNum-1));
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();

}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}