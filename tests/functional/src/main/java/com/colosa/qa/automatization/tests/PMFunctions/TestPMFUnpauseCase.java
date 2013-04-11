package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFUnpauseCase extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestPMFUnpauseCase(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executePMFUnpauseCase() throws FileNotFoundException, IOException, Exception{

		//Init case
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		caseNum = pages.Home().startCase("PMFUnpauseCase (Init)");
		pages.DynaformExecution().intoDynaform();
		pages.Home().pauseCase(caseNum);
		pages.DynaformExecution().outDynaform();
		pages.Home().gotoPaused();
		Assert.assertTrue("The case does not exist in Paused", pages.Home().existCase(caseNum));
		pages.InputDocProcess().switchToDefault();
		pages.Main().goHome();
		//Init casa again	
		caseNum = pages.Home().startCase("PMFUnpauseCase (Init)");
		pages.DynaformExecution().intoDynaform();
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		pages.Main().goHome();
    		pages.Home().gotoInbox();
		Assert.assertTrue("The case does not exist in Inbox", pages.Home().existCase(caseNum));
		pages.Home().openCase(caseNum);
		pages.DynaformExecution().intoDynaform();
		Assert.assertTrue("The button Continue does not exit in this form", pages.InputDocProcess().continuebtn());
		pages.Main().goHome();
     		pages.Home().gotoPaused();
  		Assert.assertFalse("The case exist in Paused", pages.Home().existCase(caseNum-1));
		pages.DynaformExecution().outDynaform();
		pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}