package com.colosa.qa.automatization.tests.processDesigner;

import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestWithSelectCondition extends com.colosa.qa.automatization.tests.common.Test{

    public TestWithSelectCondition(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void createSelectConditionProcess() throws FileNotFoundException, IOException, Exception{

	pages.gotoDefaultUrl();
	pages.Login().loginUser("admin", "admin", "workflow", "English");
	pages.Main().goDesigner();
	pages.ProcessList().newProcess("Test Process with Select Condition " + new java.util.Date().toString(), "Test Process with Select Condition");
	Assert.assertTrue(pages.Designer().createTask());
	pages.Designer().moveTask("Task 1", -100, -300);
	Assert.assertTrue(pages.Designer().createTask());
	pages.Designer().moveTask("Task 2", -300, -200);
	Assert.assertTrue(pages.Designer().createTask());
	pages.Designer().moveTask("Task 3", 100, -200);
	Assert.assertTrue(pages.Designer().createTask());
	pages.Designer().moveTask("Task 4", -400, -100);
	Assert.assertTrue(pages.Designer().createTask());
	pages.Designer().moveTask("Task 5", -200, -100);
	Assert.assertTrue(pages.Designer().createTask());
	pages.Designer().moveTask("Task 6", 100, -100);
	Assert.assertTrue(pages.Designer().initialTask("Task 1"));
	String[][] tasksListArray = {{"Task 2", "@@aprobar == 0"}, {"Task 3", "@@aprobar == 1"}};
	Assert.assertTrue(pages.Designer().selection("Task 1", tasksListArray));
	String[][] tasksListArraySecond = {{"Task 4", "@@aprobar == 0"}, {"Task 5", "@@aprobar == 1"}};
	Assert.assertTrue(pages.Designer().selection("Task 2", tasksListArraySecond));
	Assert.assertTrue(pages.Designer().sequential("Task 3", "Task 6"));
	Assert.assertTrue(pages.Designer().endTask("Task 4"));
	Assert.assertTrue(pages.Designer().endTask("Task 5"));
	Assert.assertTrue(pages.Designer().endTask("Task 6"));

	pages.InputDocProcess().switchToDefault();
	pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}