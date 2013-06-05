package com.colosa.qa.automatization.tests.processDesigner;

import org.junit.Test;
import org.junit.After;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestProcessExc extends com.colosa.qa.automatization.tests.common.Test{
    public TestProcessExc(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void createProcess() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goDesigner();
		pages.ProcessList().newProcess("TestNewProcess"+new java.util.Date().toString(), "just another test process");
		//Assert.assertTrue(pages.ProcessDesigner().inPage());
		pages.Designer().createTask();
		pages.Designer().moveTask("Task 1", -100, -300);
		pages.Designer().createTask();
		pages.Designer().moveTask("Task 2", -300, -150);
		pages.Designer().initialTask("Task 1");
		pages.Designer().sequential("Task 1", "Task 2");
		pages.Designer().endTask("Task 2");
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}