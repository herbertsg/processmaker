package com.colosa.qa.automatization.tests.outputDocuments;

import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestOutputDocumentList{


		@Test
		public void createOutputDocument() throws FileNotFoundException, IOException, Exception{

			Pages.Login().gotoUrl();
			Pages.Login().loginUser("admin", "admin", "workflow");
			Pages.Main().goDesigner();
			Pages.ProcessList().openProcess("Test 1");
			Pages.ProcessDesigner().openOutputDocuments();
			Pages.OutputDocumentList().createOutputDoc("Prueba "+new java.util.Date().toString(), "@#PROCESS_prueba1", "Prueba1", "A4", "Landscape", "PDF", "Enabled", "YES", "@#PROCESS", "@#TASK");
			//Pages.OutputDocumentList().closePopup();
			Pages.InputDocProcess().switchToDefault();
			Pages.Main().logout();
	}

    @After
    public void cleanup(){
        Browser.close();
    }
}