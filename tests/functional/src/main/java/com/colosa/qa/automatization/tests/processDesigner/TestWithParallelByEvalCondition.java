package com.colosa.qa.automatization.tests.processDesigner;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestWithParallelByEvalCondition extends com.colosa.qa.automatization.tests.common.Test{

    public TestWithParallelByEvalCondition(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void createProcess() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goDesigner();
		pages.ProcessList().newProcess("Test Process with Parallel By Evaluation Condition" + new java.util.Date().toString(),"Test Process with Parallel By Evaluation Condition");
		Assert.assertTrue(pages.Designer().createTask());
		pages.Designer().moveTask("Task 1", -100, -300);
		Assert.assertTrue(pages.Designer().createTask());
		pages.Designer().moveTask("Task 2", -300, -150);
		Assert.assertTrue(pages.Designer().createTask());
		pages.Designer().moveTask("Task 3", 0, -150);
		Assert.assertTrue(pages.Designer().createTask());
		pages.Designer().moveTask("Task 4", 300, -150);
		Assert.assertTrue(pages.Designer().createTask());
		pages.Designer().moveTask("Task 5", -100, -80);
		Assert.assertTrue(pages.Designer().initialTask("Task 1"));
		String[][] tasksListArray = {{"Task 2","@@PROCESS"}, {"Task 3","@@PROCESS"}, {"Task 4","@@PROCESS"}};
		Assert.assertTrue(pages.Designer().parallelByEvaluation("Task 1", tasksListArray));
		Assert.assertTrue(pages.Designer().parallelJoin("Task 2", "Task 5"));
		Assert.assertTrue(pages.Designer().parallelJoin("Task 3", "Task 5"));
		Assert.assertTrue(pages.Designer().parallelJoin("Task 4", "Task 5"));
		Assert.assertTrue(pages.Designer().endTask("Task 5"));

		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}