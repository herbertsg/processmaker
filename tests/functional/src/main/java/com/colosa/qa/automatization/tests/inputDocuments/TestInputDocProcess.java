package com.colosa.qa.automatization.tests.inputDocuments;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestInputDocProcess{


	@Test
	public void uploadInputDoc() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();
		Pages.Home().startCase("inputDocProcess (Task 1)");
		Pages.InputDocProcess().uploadFile("C:\\test.pdf", "Test File");
		Pages.InputDocProcess().uploadFile("C:\\test.pdf", "Test File");
		Pages.InputDocProcess().continuebtn();
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

    @After
    public void cleanup(){
        Browser.close();
    }


}