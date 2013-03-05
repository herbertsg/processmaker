package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestSLAProcesstaskWithPenalty2{

	protected static int caseNum;

	@Test
	public void executeSLAProcesstaskWithPenalty2() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		
		caseNum = Pages.Home().openFirstCase();
		Pages.DynaformExecution().intoDynaform();
		Pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(Pages.InputDocProcess().continuebtn());

		Pages.CronExecute().execute("workflow");

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "");
		Pages.Main().goHome();
		Pages.Home().gotoReports();

		Pages.PmslaReport().generateReport("SLA Task with Penality", "All", "All", "All");
		Pages.PmslaReport().displayCases("SLA Task with Penality");
		String[] caseInfo = Pages.PmslaReport().getCaseInfo(caseNum);
		String[] splits = caseInfo[0].split(",");
		String hourss = splits[0].replace(" H","");
		String minssh = splits[1].replace(" min","");
		Double hoursi = Double.parseDouble(hourss);			
		Double ninsi = Double.parseDouble(minssh);	
		Double totalHours = 	hoursi + ((Math.round(ninsi*100.0)/100.0) / 60);
		Double penalty = (Math.round(totalHours*100.0)/100.0) * 5 ;
		String penaltyString = String.valueOf(penalty) + " $us";
		Assert.assertEquals(caseInfo[4], penaltyString);		
		Assert.assertEquals(caseInfo[5], "Closed");
		Pages.PmslaReport().displayTasks(caseNum);
		String[] taskInfo = Pages.PmslaReport().getTaskInfo("Reclamo");
		Assert.assertEquals(taskInfo[5], "CLOSED");


}

}