package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFUnpauseCase{

	protected static int caseNum;

	@Test
	public void executePMFUnpauseCase() throws FileNotFoundException, IOException, Exception{

		//Init case
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		caseNum = Pages.Home().startCase("PMFUnpauseCase (Init)");
		Pages.DynaformExecution().intoDynaform();
		Pages.Home().pauseCase(caseNum);
		Pages.DynaformExecution().outDynaform();  
		Pages.Home().gotoPaused();
		Assert.assertTrue("The case does not exist in Paused", Pages.Home().existCase(caseNum));
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().goHome();
		//Init casa again	
		caseNum = Pages.Home().startCase("PMFUnpauseCase (Init)");
		Pages.DynaformExecution().intoDynaform();
		Assert.assertTrue("The button Continue does not exit in this form", Pages.InputDocProcess().continuebtn());	
		Pages.Main().goHome();
    		Pages.Home().gotoInbox();	
		Assert.assertTrue("The case does not exist in Inbox", Pages.Home().existCase(caseNum));
		Pages.Home().openCase(caseNum);	
		Pages.DynaformExecution().intoDynaform();
		Assert.assertTrue("The button Continue does not exit in this form", Pages.InputDocProcess().continuebtn());	
		Pages.Main().goHome();
     		Pages.Home().gotoPaused();	
  		Assert.assertFalse("The case exist in Paused", Pages.Home().existCase(caseNum-1));     								  
		Pages.DynaformExecution().outDynaform();
		Pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}