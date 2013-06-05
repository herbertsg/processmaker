package com.colosa.qa.automatization.tests.inputDocuments;

import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestInputDocProcess extends com.colosa.qa.automatization.tests.common.Test{


    public TestInputDocProcess(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void uploadInputDoc() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("inputDocProcess (Task 1)");
		pages.InputDocProcess().uploadFile("C:\\test.pdf", "Test File");
		pages.InputDocProcess().uploadFile("C:\\test.pdf", "Test File");
		pages.AssignTask().pressContinueButton();
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}


    @After
    public void cleanup(){
        browserInstance.quit();
    }



}