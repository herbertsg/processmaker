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

public class TestSLAProcessMultitaskWithPenalty{

	protected static int caseNum;

	@Test
	public void executeSLAProcessMultitaskWithPenalty() throws FileNotFoundException, IOException, Exception{

		//Init case
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();	
		Pages.Home().gotoReports();
		Pages.PmslaReport().generateReport("","","","Open");
		Thread.sleep(3000);			
		String summary[] = Pages.PmslaReport().getSlaInfo("SLA - muktitask with penlty");
		System.out.println(summary[2]);		
		Thread.sleep(3000);
		Pages.PmslaReport().displayCases("SLA - muktitask with penlty");
		Thread.sleep(3000);
      String caseNumString = NumberFormat.getIntegerInstance().format(1782);
      if(caseNumString.contains("."))
      {
        caseNumString = caseNumString.replace(".", ",");
      }
 		System.out.println("Case # " + caseNumString);  
		String caseInfo[] = Pages.PmslaReport().getCaseInfo(1782);
		System.out.println(caseInfo[5]);
		Thread.sleep(3000);			
		Pages.PmslaReport().displayTasks(1782);	
		Thread.sleep(3000);					
		String taskInfo[] = Pages.PmslaReport().getTaskInfo("Solicitud");
		System.out.println(taskInfo[1]);		
}

}