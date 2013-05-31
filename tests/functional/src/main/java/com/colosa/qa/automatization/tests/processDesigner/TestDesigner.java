package com.colosa.qa.automatization.tests.processDesigner;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestDesigner extends com.colosa.qa.automatization.tests.common.Test{

    public TestDesigner(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void tasks() throws FileNotFoundException, IOException, Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goDesigner();
		pages.ProcessList().newProcess("Prueba" + new java.util.Date().toString(), "Prueba");
		pages.Designer().createTask();
		pages.Designer().moveTask("Task 1", -100, -300);
		Assert.assertTrue(pages.Designer().createTask());
		pages.Designer().moveTask("Task 2", -300, -150);
		Assert.assertTrue(pages.Designer().createTask());
		pages.Designer().moveTask("Task 3", 0, -150);
		Assert.assertTrue(pages.Designer().createTask());
		pages.Designer().moveTask("Task 4", 300, -150);
		Assert.assertTrue(pages.Designer().createTask());
		pages.Designer().moveTask("Task 5", -100, -80);
		pages.Designer().initialTask("Task 1");
		String[][] tasksListArray = {{"Task 2",""}, {"Task 3",""}, {"Task 4",""}};
		Assert.assertTrue(pages.Designer().parallelFork("Task 1", tasksListArray));
		Assert.assertTrue(pages.Designer().parallelJoin("Task 2", "Task 5"));
		Assert.assertTrue(pages.Designer().parallelJoin("Task 3", "Task 5"));
		Assert.assertTrue(pages.Designer().parallelJoin("Task 4", "Task 5"));
		Assert.assertTrue(pages.Designer().endTask("Task 5"));

		TaskFieldData taskProp = new TaskFieldData();
		taskProp.taskName = "Task 1";
		taskProp.title = "Tarea de Prueba";
		taskProp.caseAssignedBy = "MANUAL";

		pages.TaskProperties().properties(taskProp);

		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}