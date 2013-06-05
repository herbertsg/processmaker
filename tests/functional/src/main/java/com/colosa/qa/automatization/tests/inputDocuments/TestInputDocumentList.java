package com.colosa.qa.automatization.tests.inputDocuments;

import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestInputDocumentList extends com.colosa.qa.automatization.tests.common.Test{

    public TestInputDocumentList(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void createInputDocument() throws FileNotFoundException, IOException, Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goDesigner();
		pages.ProcessList().openProcess("Test InputDoc List");
		pages.ProcessDesigner().openInputDocuments();
		pages.InputDocumentList().createInputDoc("Prueba "+new java.util.Date().toString(), "Digital", "Prueba 3", "YES", "@#PROCESS", "_Document3_@#TASK");
		//pages.InputDocumentList().closePopup();

		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }


}