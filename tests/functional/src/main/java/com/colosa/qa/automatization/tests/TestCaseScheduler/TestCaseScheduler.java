package com.colosa.qa.automatization.tests.TestCaseScheduler;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;

public class TestCaseScheduler extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;
	protected static String caseStatus;

    public TestCaseScheduler(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeCron() throws FileNotFoundException, IOException, Exception{
		//Execute cron
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Main().goAdmin();
		pages.Admin().goToLogs();
		pages.Admin().showCaseScheduler();
		pages.CronExecute().execute("workflow");
		//pages.DynaformExecution().sleep(20000);


	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}