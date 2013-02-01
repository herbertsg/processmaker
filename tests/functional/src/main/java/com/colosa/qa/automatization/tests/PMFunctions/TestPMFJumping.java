package com.colosa.qa.automatization.tests.PMFunctions;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;
import java.util.*;
import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;
import java.text.DecimalFormat;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFJumping{

	@Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		Pages.Home().startCase("Test PMFJumping (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Assert.assertTrue(Pages.Home().isGridPresent("casesGrid"));

	}

}