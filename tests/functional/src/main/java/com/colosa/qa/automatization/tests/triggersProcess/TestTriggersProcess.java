package com.colosa.qa.automatization.tests.triggersProcess;

import org.junit.Test;

import java.io.IOException;

public class TestTriggersProcess extends com.colosa.qa.automatization.tests.common.Test{


    public TestTriggersProcess(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void createTrigger() throws Exception{

		//Lpgin and create a process for test
		pages.Login().gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goDesigner();
		pages.ProcessList().newProcess("Test ProcessTriggers " + new java.util.Date().toString(), "Test process triggers");

		// Test custom triggers
		pages.ProcessDesigner().openTriggers();
		pages.TriggersProcess().newCustomTrigger("New custom trigger", "new custom test triggers", "custom code here");
		pages.TriggersProcess().closePopup();
		
		//Test Process Maker Functions triggers 
		pages.ProcessDesigner().openTriggers();
		pages.TriggersProcess().newPMFTrigger("PMFAssignUserToGroup","Test PMFAssignUserToGroup", "Test PMFAssignUserToGroup", "userid", "user group", "returnvalue");
		pages.TriggersProcess().closePopup();

		//Test Alfresco DM triggers 
		pages.ProcessDesigner().openTriggers();
		pages.TriggersProcess().newAlfrescoDMTrigger("Checkin","Test Checkin", "Test Checkin", "http://localhost:8080/alfresco", "Alfresco user", "Alfresco password",  "DocumentID", "Comments", "returnvalue");
		pages.TriggersProcess().closePopup();

		//Test Sharepoint DWS Triggers  
		pages.ProcessDesigner().openTriggers();
		pages.TriggersProcess().newSharepointDWSTrigger("CreateDWS","Test CreateDWS", "Test CreateDWS", "http://server:port", "Sharepoint auth", "Name of DWS", "Relevent user", "Title DWS", "@@APPLICATION", "returnvalue");
		pages.TriggersProcess().closePopup();

		//Test Sugar CRM Triggers   
		pages.ProcessDesigner().openTriggers();
		pages.TriggersProcess().newSugarCRMTrigger("CreateSugarAccount","Test CreateSugarAccount", "Test CreateSugarAccount", "http://www.example.com/sugar/soap.php?wsdl", "Sugar user", "Sugar pass", "Account name", "array", "returnvalue");
		pages.TriggersProcess().closePopup();

		//Test Talend ETL Integration Triggers
		pages.ProcessDesigner().openTriggers();
		pages.TriggersProcess().newTalendTrigger("ExecuteTalendWebservice","Test ExecuteTalendWebservice", "Test ExecuteTalendWebservice", "http://www.example.com/talend/soap.php?wsdl", "array(array(a,a1) array(b, b1))", "message", "returnvalue");
		pages.TriggersProcess().closePopup();

		//Test  Zimbra Triggers   
		pages.ProcessDesigner().openTriggers();
		pages.TriggersProcess().newZimbraTrigger("CreateZimbraFolder","Test CreateZimbraFolder", "Test CreateZimbraFolder", "http://zimbra.server:port", "Zimbra user", "Zimbra key", "folder name", "Color name", "returnvalue");
		pages.TriggersProcess().closePopup();

		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

/*    @After
    public void cleanup(){
        Browser.close();
    }*/

}