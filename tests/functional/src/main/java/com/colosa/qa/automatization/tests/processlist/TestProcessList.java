package com.colosa.qa.automatization.tests.processlist;

import org.junit.Test;
import org.junit.After;

import java.io.IOException;

public class TestProcessList extends com.colosa.qa.automatization.tests.common.Test{


    public TestProcessList(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void openProc() throws Exception{
        //testeo de las funcionalidades disponible en el listado de procesos
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goDesigner();
		//pages.ProcessList().openProcess("Test2");
		//pages.ProcessList().importProcess("/home/ernesto/Documents/Test_6.pm");
		//pages.ProcessList().exportProcess("Test2");
		pages.ProcessList().newProcess("Test Process2", "Proceso de prueba 2");
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

/*
    @After
    public void cleanup(){
        browserInstance.quit();
    }
*/

}