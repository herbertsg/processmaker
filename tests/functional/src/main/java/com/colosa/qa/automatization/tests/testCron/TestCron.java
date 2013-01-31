package com.colosa.qa.automatization.tests.testCron;

import com.colosa.qa.automatization.pages.Pages;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestCron{

	@Test
	public void testCase() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.CronExecute().execute("workflow");
	}

}
