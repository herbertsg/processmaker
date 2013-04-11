package com.colosa.qa.automatization.tests.pmslaPlugin;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestSLAProcesstaskWithPenalty2 extends com.colosa.qa.automatization.tests.common.Test{

	protected static int caseNum;

    public TestSLAProcesstaskWithPenalty2(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void executeSLAProcesstaskWithPenalty2() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		
		caseNum = pages.Home().openFirstCase();
		pages.DynaformExecution().intoDynaform();
		pages.DynaformExecution().setFieldValue("enviar", "");
		Assert.assertTrue(pages.InputDocProcess().continuebtn());

		pages.CronExecute().execute("workflow");
		pages.DynaformExecution().sleep(5000);

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoReports();

		pages.PmslaReport().generateReport("SLA Task with Penality", "All", "All", "All");
		pages.PmslaReport().displayCases("SLA Task with Penality");
		String[] caseInfo = pages.PmslaReport().getCaseInfo(caseNum);
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
		pages.PmslaReport().displayTasks(caseNum);
		String[] taskInfo = pages.PmslaReport().getTaskInfo("Reclamo");
		Assert.assertEquals(taskInfo[5], "CLOSED");
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

}
/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}