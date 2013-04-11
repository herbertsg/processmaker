package com.colosa.qa.automatization.tests.processDesigner;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestWithEvaluationCondition extends com.colosa.qa.automatization.tests.common.Test{

    public TestWithEvaluationCondition(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void createEvaluationConditionProcess() throws FileNotFoundException, IOException, Exception{

	pages.Login().gotoDefaultUrl();
	pages.Login().loginUser("admin", "admin", "workflow", "English");
	pages.Main().goDesigner();
	pages.ProcessList().newProcess("Test Process with Evaluation Condition " + new java.util.Date().toString(), "Test Process with Evaluation Condition");
	Assert.assertTrue(pages.Designer().createTask());
	pages.Designer().moveTask("Task 1", -100, -300);
	Assert.assertTrue(pages.Designer().createTask());
	pages.Designer().moveTask("Task 2", -300, -200);
	Assert.assertTrue(pages.Designer().createTask());
	pages.Designer().moveTask("Task 3", -400, -100);
	Assert.assertTrue(pages.Designer().createTask());
	pages.Designer().moveTask("Task 4", -200, -100);
	Assert.assertTrue(pages.Designer().initialTask("Task 1"));
	String[][] tasksListArray = {{"Task 2","@@aprobar == 0"}, {"End of process",""}};
	Assert.assertTrue(pages.Designer().evaluation("Task 1", tasksListArray));
	String[][] tasksListArraySecond = {{"Task 3", "@@aprobar == 0"}, {"Task 4","@@aprobar == 1"}};
	Assert.assertTrue(pages.Designer().evaluation("Task 2", tasksListArraySecond));
	Assert.assertTrue(pages.Designer().endTask("Task 3"));
	Assert.assertTrue(pages.Designer().endTask("Task 4"));

	pages.InputDocProcess().switchToDefault();
	pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}