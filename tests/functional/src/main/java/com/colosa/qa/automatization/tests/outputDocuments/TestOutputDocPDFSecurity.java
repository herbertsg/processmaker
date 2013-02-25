package com.colosa.qa.automatization.tests.outputDocuments;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestOutputDocPDFSecurity{


	@Test
	public void testPDFSecurity() throws FileNotFoundException, IOException, Exception{

		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();
		Pages.Home().startCase("Output documents - PDF security (PDF security)");
		Pages.OutputDocProcess().downloadPdfFile();
		System.out.println(Pages.OutputDocProcess().checkPdfSecurity("C:\\Users\\Administrator\\Downloads\\test_1.pdf","test"));
		Assert.assertEquals(" \nTest PDF security.\nIf you can read this message, the file has been opened.\nPowered by TCPDF (www.tcpdf.org)", Pages.OutputDocProcess().checkPdfSecurity("C:\\Users\\Administrator\\Downloads\\test_1.pdf","test"));

	}

    @After
    public void cleanup(){
        Browser.close();
    }


}