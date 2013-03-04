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

public class TestDesigner{

	@Test
	public void tasks() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goDesigner();		
		Pages.ProcessList().newProcess("Prueba" + new java.util.Date().toString(), "Prueba");			
		Pages.Designer().createTask();
		Pages.Designer().moveTask("Task 1", -100, -300);
		Assert.assertTrue(Pages.Designer().createTask());
		Pages.Designer().moveTask("Task 2", -300, -150);
		Assert.assertTrue(Pages.Designer().createTask());
		Pages.Designer().moveTask("Task 3", 0, -150);
		Assert.assertTrue(Pages.Designer().createTask());
		Pages.Designer().moveTask("Task 4", 300, -150);
		Assert.assertTrue(Pages.Designer().createTask());
		Pages.Designer().moveTask("Task 5", -100, -80);
		Pages.Designer().initialTask("Task 1");		
		String[][] tasksListArray = {{"Task 2",""}, {"Task 3",""}, {"Task 4",""}};
		Assert.assertTrue(Pages.Designer().parallelFork("Task 1", tasksListArray));
		Assert.assertTrue(Pages.Designer().parallelJoin("Task 2", "Task 5"));
		Assert.assertTrue(Pages.Designer().parallelJoin("Task 3", "Task 5"));
		Assert.assertTrue(Pages.Designer().parallelJoin("Task 4", "Task 5"));
		Assert.assertTrue(Pages.Designer().endTask("Task 5"));

		TaskFieldData taskProp = new TaskFieldData();
		taskProp.taskName = "Task 1";
		taskProp.title = "Tarea de Prueba";
		taskProp.caseAssignedBy = "MANUAL";

		Pages.TaskProperties().properties(taskProp);				

		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

//    @After
//    public void cleanup(){
//        Browser.close();
//    }
}