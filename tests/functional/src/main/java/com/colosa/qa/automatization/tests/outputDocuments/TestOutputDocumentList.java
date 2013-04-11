package com.colosa.qa.automatization.tests.outputDocuments;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestOutputDocumentList extends com.colosa.qa.automatization.tests.common.Test{


    public TestOutputDocumentList(String browserName) throws IOException {
        super(browserName);
    }

    @Test
		public void createOutputDocument() throws FileNotFoundException, IOException, Exception{

			pages.Login().gotoDefaultUrl();
			pages.Login().loginUser("admin", "admin", "workflow", "English");
			pages.Main().goDesigner();
			pages.ProcessList().openProcess("Test 1");
			pages.ProcessDesigner().openOutputDocuments();
			pages.OutputDocumentList().createOutputDoc("Prueba "+new java.util.Date().toString(), "@#PROCESS_prueba1", "Prueba1", "A4", "Landscape", "PDF", "Enabled", "YES", "@#PROCESS", "@#TASK");
			//pages.OutputDocumentList().closePopup();
			pages.InputDocProcess().switchToDefault();
			pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/
}