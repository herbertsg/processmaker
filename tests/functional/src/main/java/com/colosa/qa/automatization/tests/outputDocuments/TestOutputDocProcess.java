package com.colosa.qa.automatization.tests.outputDocuments;

import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestOutputDocProcess extends com.colosa.qa.automatization.tests.common.Test{


    public TestOutputDocProcess(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void downloadOutputDoc() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		pages.Home().gotoNewCase().startCase("outputDocProcess (Task 1)");
		pages.DynaformExecution().intoDynaform();
		pages.OutputDocProcess().downloadDocFile();
		pages.OutputDocProcess().downloadPdfFile();
		pages.OutputDocProcess().nextbtn();
		pages.OutputDocProcess().downloadDocFile();
		pages.OutputDocProcess().downloadPdfFile();
		pages.OutputDocProcess().nextbtn();
		pages.OutputDocProcess().continuebtn();
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }

}