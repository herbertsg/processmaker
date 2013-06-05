package com.colosa.qa.automatization.tests.TestCaseScheduler;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestCaseScheduler2 extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;
	protected static String caseStatus;

    public TestCaseScheduler2(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeProcess() throws FileNotFoundException, IOException, Exception{
		//Init case
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Main().goAdmin();
		pages.Admin().goToLogs();
		pages.Admin().showCaseScheduler();
		caseStatus = pages.Admin().lastCreateCaseStatus();
		String[] toArray = caseStatus.split(" ");
		int lastCaseNum = Integer.parseInt(toArray[1]);
		int currentCaseNum = lastCaseNum + 1;
		pages.DynaformExecution().sleep(20000);
		pages.CronExecute().execute("workflow");
		System.out.println("run cron.php");
		pages.DynaformExecution().sleep(20000);
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Main().goAdmin();
		pages.Admin().goToLogs();
		pages.Admin().showCaseScheduler();
		caseStatus = pages.Admin().lastCreateCaseStatus();
		Assert.assertEquals("Case " + Integer.toString(currentCaseNum) + " Started successfully", caseStatus);
		pages.DynaformExecution().sleep(20000);
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}