package com.colosa.qa.automatization.tests.PMFunctions;

import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPMFJumping extends com.colosa.qa.automatization.tests.common.Test{

    public TestPMFJumping(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess()throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("Test PMFJumping (Task 1)");
		DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
        form.clickButton("Enviar");
		Assert.assertTrue(pages.Home().isGridPresent("casesGrid"));
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}