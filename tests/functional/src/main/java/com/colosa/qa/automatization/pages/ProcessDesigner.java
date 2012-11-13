package com.colosa.qa.automatization.pages;

import java.util.List;
import java.lang.Exception;
import com.colosa.qa.automatization.common.Browser;
import org.openqa.selenium.WebElement;

public class ProcessDesigner{

	public enum MenuOption{
		DYNAFORMS("dynaforms"), 
		INPUT_DOCUMENTS("inputDocuments"), 
		OUTPUT_DOCUMENTS("outputDocuments"), 
		TRIGGERS("triggers"), 
		REPORT_TABLES("reportTables"), 
		DATABASE_CONNECTIONS("databaseConnections"), 
		CASE_SCHEDULER("caseScheduler");

		private String identifier;

		MenuOption(String id){
			this.identifier = id;
		}

		public String getId(){
			return this.identifier;
		}
	}
	
	public static void open(MenuOption option) throws Exception{
		Browser.driver().switchTo().frame("frameMain");
		Browser.getElement("designerProcesses.webElement.menuItems."+option.getId()).click();
	}

	public static void openDynaforms() throws Exception{
		ProcessDesigner.open(ProcessDesigner.MenuOption.DYNAFORMS);
	}

	public static void openInputDocuments() throws Exception{
		ProcessDesigner.open(ProcessDesigner.MenuOption.INPUT_DOCUMENTS);	
	}

	public static void openOutputDocuments() throws Exception{
		ProcessDesigner.open(ProcessDesigner.MenuOption.OUTPUT_DOCUMENTS);
	}

	public static void openTriggers() throws Exception{
		ProcessDesigner.open(ProcessDesigner.MenuOption.TRIGGERS);
	}

	public static void openReportTables() throws Exception{
		ProcessDesigner.open(ProcessDesigner.MenuOption.REPORT_TABLES);
	}

	public static void openDatabaseConnections() throws Exception{
		ProcessDesigner.open(ProcessDesigner.MenuOption.DATABASE_CONNECTIONS);
	}

	public static void openCaseScheduler() throws Exception{
		ProcessDesigner.open(ProcessDesigner.MenuOption.CASE_SCHEDULER);
	}

}