/*
* TriggersProcess.java
* Created on Wensday 21, 2012
*
*/

/**
* @author Ademar Hurtado
*/
package com.colosa.qa.automatization.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.common.extJs.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TriggersProcess extends Page{

	public TriggersProcess() throws FileNotFoundException, IOException{
		
	}

	/**
	* @param triggerTitle string, triggerDescription string, triggerWebbot string
	*/
	public void newCustomTrigger(String triggerTitle, String triggerDescription, String triggerWebbot) throws FileNotFoundException, IOException, Exception{
		Browser.driver().switchTo().frame("frameMain");
		Browser.getElement("processDesigner.webElement.panelNewButton").click();
		Browser.getElement("triggers.webElement.newCustomTrigger").click();
		Browser.getElement("triggers.webElemet.title").sendKeys(triggerTitle);
		Browser.getElement("triggers.webElemet.description").sendKeys(triggerDescription);
		Browser.getElement("triggers.webElemet.webbot").sendKeys(triggerWebbot);
		Browser.getElement("triggers.webElemet.save").click();
	}

	/**
	* @param arg Array of arguments. The order is: Template name, Trigger Title, Trigger description... 
	* The arguments must be in order from top to down. 
	*/
	public void newPMFTrigger(String... args) throws FileNotFoundException, IOException, Exception{
		Browser.driver().switchTo().frame("frameMain");
		Browser.getElement("processDesigner.webElement.panelNewButton").click();
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.OpenTree").click();
		Browser.getElement("triggers.webElement." + args[0] + ".New").click();
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Title").sendKeys(args[1]);
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Description").sendKeys(args[2]);
		if(args[0].equals("PMFAssignUserToGroup") && args.length == 6){
			Browser.getElement("triggers.webElement.PMFAssignUserToGroup.UserId").sendKeys(args[3]);
			Browser.getElement("triggers.webElement.PMFAssignUserToGroup.GroupId").sendKeys(args[4]);
		}
		else if(args[0].equals("PMFCancelCase") && args.length == 6){
			Browser.getElement("triggers.webElement.PMFCancelCase.UserId").sendKeys(args[3]);
			Browser.getElement("triggers.webElement.PMFCancelCase.GroupId").sendKeys(args[4]);
		}
		else if(args[0].equals("PMFCaseList") && args.length == 5){
			Browser.getElement("triggers.webElement.CaseList.UserId").sendKeys(args[3]);
		}
		else if(args[0].equals("PMFCaseList") && args.length == 5){
			Browser.getElement("triggers.webElement.CaseList.UserId").sendKeys(args[3]);
		}
		else if(args[0].equals("PMFCreateUser") && args.length == 11){
			Browser.getElement("triggers.webElement.PMFCreateUser.UserId").sendKeys(args[3]);
			Browser.getElement("triggers.webElement.PMFCreateUser.Password").sendKeys(args[4]);
			Browser.getElement("triggers.webElement.PMFCreateUser.FirstName").sendKeys(args[5]);
			Browser.getElement("triggers.webElement.PMFCreateUser.LastName").sendKeys(args[6]);			
			Browser.getElement("triggers.webElement.PMFCreateUser.Email").sendKeys(args[7]);
			Browser.getElement("triggers.webElement.PMFCreateUser.Role").sendKeys(args[8]);
			Browser.getElement("triggers.webElement.PMFCreateUser.DueDate").sendKeys(args[9]);
			Browser.getElement("triggers.webElement.PMFCreateUser.Status").sendKeys(args[10]);						
		}	
		else if(args[0].equals("PMFDeleteCase") && args.length == 5){
			Browser.getElement("triggers.webElement.PMFDeleteCase.CaseUid").sendKeys(args[3]);
		}
		else if(args[0].equals("PMFDerivateCase") && args.length == 8){
			Browser.getElement("triggers.webElement.PMFDerivateCase.CaseId").sendKeys(args[3]);
			Browser.getElement("triggers.webElement.PMFDerivateCase.DelegationIndex").sendKeys(args[4]);
			Browser.getElement("triggers.webElement.PMFDerivateCase.Trigger").sendKeys(args[5]);
			Browser.getElement("triggers.webElement.PMFDerivateCase.UserId").sendKeys(args[6]);			
		}
		else if(args[0].equals("PMFGenerateOutputDocument") && args.length == 5){
			Browser.getElement("triggers.webElement.PMFGenerateOutputDocument.OutputID").sendKeys(args[3]);
		}
		else if(args[0].equals("PMFGetCaseNotes") && args.length == 7){
			Browser.getElement("triggers.webElement.PMFGetCaseNotes.ApplicationID").sendKeys(args[3]);
			Browser.getElement("triggers.webElement.PMFGetCaseNotes.UserUid").sendKeys(args[5]);
			Browser.getElement("triggers.webElement.PMFGetCaseNotes.Type").sendKeys(args[4]);			
		}
		else if(args[0].equals("PMFGetNextAssignedUser") && args.length == 6){
			Browser.getElement("triggers.webElement.PMFGetNextAssignedUser.Application").sendKeys(args[3]);
			Browser.getElement("triggers.webElement.PMFGetNextAssignedUser.Task").sendKeys(args[4]);			
		}
		else if(args[0].equals("PMFGetUserEmailAddress") && args.length == 7){
			Browser.getElement("triggers.webElement.PMFGetUserEmailAddress.UserId").sendKeys(args[3]);
			Browser.getElement("triggers.webElement.PMFGetUserEmailAddress.AppUid").sendKeys(args[4]);
			Browser.getElement("triggers.webElement.PMFGetUserEmailAddress.Prefix").sendKeys(args[5]);			
		}
  		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Answer").sendKeys(args[args.length - 1]);             
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Save").click();
	}

	/**
	* @param arg Array of arguments. The order is: Template name, Trigger Title, Trigger description, Alfreco server, Alfresco user, Alfresco password... 
	* The arguments must be in order from top to down. 
	*/
	public void newAlfrescoDMTrigger(String... args) throws FileNotFoundException, IOException, Exception{
		Browser.driver().switchTo().frame("frameMain");		
		Browser.getElement("processDesigner.webElement.panelNewButton").click();
		Browser.getElement("triggers.webElement.AlfrescoDM.OpenTree").click();
		Browser.getElement("triggers.webElement." + args[0] + ".New").click();
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Title").sendKeys(args[1]);
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Description").sendKeys(args[2]);
		Browser.getElement("triggers.webElement.AlfrescoDM.AlfrescoServerUrl").sendKeys(args[3]);
		Browser.getElement("triggers.webElement.AlfrescoDM.User").sendKeys(args[4]);
		Browser.getElement("triggers.webElement.AlfrescoDM.Password").sendKeys(args[5]);

		if(args[0].equals("Checkin") && args.length == 9){
			Browser.getElement("triggers.webElement.Checkin.DocUid").sendKeys(args[6]);
			Browser.getElement("triggers.webElement.Checkin.Comments").sendKeys(args[7]);	
		}	
		else if(args[0].equals("Checkout") && args.length == 8){
			Browser.getElement("triggers.webElement.Checkout.DocUid").sendKeys(args[6]);
		}
		else if(args[0].equals("cancelCheckout") && args.length == 8){
			Browser.getElement("triggers.webElement.CancelCheckout.DocUid").sendKeys(args[6]);
		}
		else if(args[0].equals("CreateFolder") && args.length == 9){
			Browser.getElement("triggers.webElement.CreateFolder.ParentFolder").sendKeys(args[6]);
			Browser.getElement("triggers.webElement.CreateFolder.FolderName").sendKeys(args[7]);			
		}
		else if(args[0].equals("DeleteObject") && args.length == 8){
			Browser.getElement("triggers.webElement.DeleteObject.ObjetcId").sendKeys(args[6]);
		}
		else if(args[0].equals("DownloadDoc") && args.length == 10){
			Browser.getElement("triggers.webElement.DownloadDoc.ObjetcId").sendKeys(args[6]);
			Browser.getElement("triggers.webElement.DownloadDoc.ParentFolder").sendKeys(args[7]);
			Browser.getElement("triggers.webElement.DownloadDoc.FolderName").sendKeys(args[8]);						
		}
		else if(args[0].equals("GetFolderChildren") && args.length == 8){
			Browser.getElement("triggers.webElement.GetFolderChildren.FolderId").sendKeys(args[6]);
		}
		else if(args[0].equals("UploadDoc") && args.length == 12){
			Browser.getElement("triggers.webElement.UploadDoc.FileSource").sendKeys(args[6]);
			Browser.getElement("triggers.webElement.UploadDoc.Title").sendKeys(args[7]);
			Browser.getElement("triggers.webElement.UploadDoc.Description").sendKeys(args[8]);	
			Browser.getElement("triggers.webElement.UploadDoc.DocType").sendKeys(args[9]);
			Browser.getElement("triggers.webElement.UploadDoc.Path").sendKeys(args[10]);								
		}																														
		else{
			throw new Exception("Invalid number of parameters or no exist Template");
		} 

  		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Answer").sendKeys(args[args.length - 1]);             
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Save").click();
	}

	/**
	* @param arg Array of arguments. The order is: Template name, Trigger Title, Trigger description, Sharepint server, Sharepoint auth, Sharepoint DWS Name... 
	* The arguments must be in order from top to down. 
	*/
	public void newSharepointDWSTrigger(String... args) throws FileNotFoundException, IOException, Exception{
		Browser.driver().switchTo().frame("frameMain");		
		Browser.getElement("processDesigner.webElement.panelNewButton").click();
		Browser.getElement("triggers.webElement.SharepointDWS.OpenTree").click();
		Browser.getElement("triggers.webElement." + args[0] + ".New").click();
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Title").sendKeys(args[1]);
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Description").sendKeys(args[2]);
		Browser.getElement("triggers.webElement.SharepointDWS.SharepointServer").sendKeys(args[3]);
		Browser.getElement("triggers.webElement.SharepointDWS.Auth").sendKeys(args[4]);
		Browser.getElement("triggers.webElement.SharepointDWS.Name").sendKeys(args[5]);		

		if(args[0].equals("CreateDWS") && args.length == 10){
			Browser.getElement("triggers.webElement.CreateDWS.Users").sendKeys(args[0]);
			Browser.getElement("triggers.webElement.CreateDWS.Title").sendKeys(args[1]);
			Browser.getElement("triggers.webElement.CreateDWS.Documents").sendKeys(args[2]);				
		}	
		else if(args[0].equals("CreateFolderDWS") && args.length == 8){
			Browser.getElement("triggers.webElement.CreateFolderDWS.DwsFolderName").sendKeys(args[6]);
		}
		else{
			throw new Exception("Invalid number of parameters or no exist Template");
		} 

  		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Answer").sendKeys(args[args.length - 1]);             
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Save").click();
	}

	/**
	* @param arg Array of arguments. The order is: Template name, Trigger Title, Trigger description, Sugar soap, Sugar user, Sugar Password... 
	* The arguments must be in order from top to down. 
	*/
	public void newSugarCRMTrigger(String... args) throws FileNotFoundException, IOException, Exception{
		Browser.driver().switchTo().frame("frameMain");		
		Browser.getElement("processDesigner.webElement.panelNewButton").click();
		Browser.getElement("triggers.webElement.SugarCRM.OpenTree").click();
		Browser.getElement("triggers.webElement." + args[0] + ".New").click();
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Title").sendKeys(args[1]);
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Description").sendKeys(args[2]);
		Browser.getElement("triggers.webElement.SugarCRM.SugarSoap").sendKeys(args[3]);
		Browser.getElement("triggers.webElement.SugarCRM.User").sendKeys(args[4]);
		Browser.getElement("triggers.webElement.SugarCRM.Password").sendKeys(args[5]);		

		if(args[0].equals("CreateSugarAccount") && args.length == 9){
			Browser.getElement("triggers.webElement.CreateSugarAccount.Name").sendKeys(args[6]);
			Browser.getElement("triggers.webElement.CreateSugarAccount.ResultType").sendKeys(args[7]);
		}	
		else{
			throw new Exception("Invalid number of parameters or no exist Template");
		} 

  		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Answer").sendKeys(args[args.length - 1]);             
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Save").click();
	}

	/**
	* @param arg Array of arguments. The order is: Template name, Trigger Title, Trigger description...
	* The arguments must be in order from top to down. 	
	*/
	public void newTalendTrigger(String... args) throws FileNotFoundException, IOException, Exception{
		Browser.driver().switchTo().frame("frameMain");		
		Browser.getElement("processDesigner.webElement.panelNewButton").click();
		Browser.getElement("triggers.webElement.Talend.OpenTree").click();
		Browser.getElement("triggers.webElement." + args[0] + ".New").click();
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Title").sendKeys(args[1]);
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Description").sendKeys(args[2]);

		if(args[0].equals("ExecuteTalendWebservice") && args.length == 7){
			Browser.getElement("triggers.webElement.ExecuteTalendWebservice.Wsdl").sendKeys(args[3]);
			Browser.getElement("triggers.webElement.ExecuteTalendWebservice.Params").sendKeys(args[4]);
			Browser.getElement("triggers.webElement.ExecuteTalendWebservice.Message").sendKeys(args[5]);			
		}	
		else{
			throw new Exception("Invalid number of parameters or no exist Template");
		} 

  		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Answer").sendKeys(args[args.length - 1]);             
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Save").click();
	}

	/**
	* @param arg Array of arguments. The order is: Template name, Trigger Title, Trigger description, Zimbra server, Zimbra user, Zimbra Auth... 
	* The arguments must be in order from top to down. 
	*/
	public void newZimbraTrigger(String... args) throws FileNotFoundException, IOException, Exception{
		Browser.driver().switchTo().frame("frameMain");		
		Browser.getElement("processDesigner.webElement.panelNewButton").click();
		Browser.getElement("triggers.webElement.Zimbra.OpenTree").click();
		Browser.getElement("triggers.webElement." + args[0] + ".New").click();
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Title").sendKeys(args[1]);
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Description").sendKeys(args[2]);
		Browser.getElement("triggers.webElement.Zimbra.ServerUrl").sendKeys(args[3]);
		Browser.getElement("triggers.webElement.Zimbra.Username").sendKeys(args[4]);
		Browser.getElement("triggers.webElement.Zimbra.PreAuthKey").sendKeys(args[5]);		

		if(args[0].equals("CreateZimbraFolder") && args.length == 9){
			Browser.getElement("triggers.webElement.CreateZimbraFolder.FolderName").sendKeys(args[6]);
			Browser.getElement("triggers.webElement.CreateZimbraFolder.ColorFolder").sendKeys(args[7]);
		}	
		else{
			throw new Exception("Invalid number of parameters or no exist Template");
		} 

  		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Answer").sendKeys(args[args.length - 1]);             
		Browser.getElement("triggers.webElement.ProcessmakerFunctions.Save").click();
	}


	public void closePopup() throws Exception{
		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("frameMain");
		WebElement btnClose = Browser.getElement("triggers.webElement.close");
		btnClose.click();
		Browser.driver().switchTo().defaultContent();
	}	

}

