package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFGroupList{

	@Test
	public void testCase() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		Pages.Home().startCase("PMF Group List (Task 1)");
		Pages.DynaformExecution().sleep(15000);
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

//    @After
//    public void cleanup(){
//        Browser.close();
//    }

}