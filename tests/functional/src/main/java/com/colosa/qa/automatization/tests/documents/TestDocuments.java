package com.colosa.qa.automatization.tests.documents;

import org.junit.After;
import org.junit.Test;

import java.io.IOException;

public class TestDocuments extends com.colosa.qa.automatization.tests.common.Test{

    public TestDocuments(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void downloadFile() throws Exception{

		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoDocuments();
		pages.Documents().selectFolder("Test 1");
		pages.Documents().downloadDocument("default.conf");
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}