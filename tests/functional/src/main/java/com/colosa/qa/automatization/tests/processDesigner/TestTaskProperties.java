package com.colosa.qa.automatization.tests.processDesigner;

import com.colosa.qa.automatization.common.TaskFieldData;
import org.junit.Test;
import org.openqa.selenium.interactions.Actions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestTaskProperties extends com.colosa.qa.automatization.tests.common.Test{

    public TestTaskProperties(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void testFunctional() throws FileNotFoundException, IOException, Exception{

		Actions action = new Actions(browserInstance.getInstanceDriver());

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goDesigner();
		pages.ProcessList().openProcess("Test 1");
		TaskFieldData taskProp = new TaskFieldData();
		taskProp.taskName = "Task 1";
		taskProp.caseAssignedBy = "MANUAL";

		pages.TaskProperties().properties(taskProp);

		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}