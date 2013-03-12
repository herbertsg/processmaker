package com.colosa.qa.automatization.tests.outputDocuments;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestOutputDocProcess{


	@Test
	public void downloadOutputDoc() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();
		Pages.Home().startCase("outputDocProcess (Task 1)");
		Pages.DynaformExecution().intoDynaform();
		Pages.OutputDocProcess().downloadDocFile();
		Pages.OutputDocProcess().downloadPdfFile();
		Pages.OutputDocProcess().nextbtn();
		Pages.OutputDocProcess().downloadDocFile();
		Pages.OutputDocProcess().downloadPdfFile();
		Pages.OutputDocProcess().nextbtn();
		Pages.OutputDocProcess().continuebtn();
		Pages.InputDocProcess().switchToDefault();
		Pages.Main().logout();
	}

    @After
    public void cleanup(){
        Browser.close();
    }

}