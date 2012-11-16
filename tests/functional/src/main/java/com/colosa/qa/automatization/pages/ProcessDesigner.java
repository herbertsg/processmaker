package com.colosa.qa.automatization.pages;

import java.util.List;
import java.lang.Exception;
import com.colosa.qa.automatization.common.Browser;
import com.colosa.qa.automatization.common.ConfigurationSettings;
import com.colosa.qa.automatization.common.dynaform.DynaformDesigner;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProcessDesigner{

	public enum DynaformType{
		BLANK("blankType"),
		PM_TABLE("pmTableType");

		private String id;

		DynaformType(String id){
			this.id = id;
		}

		public String getId(){
			return this.id;
		}

	};

	public enum DynaformSubType{
		NORMAL("normalType"),
		GRID("gridType");

		private String id;

		DynaformSubType(String id){
			this.id = id;
		}

		public String getId(){
			return this.id;
		}
	}

	public enum MenuOption{
		DYNAFORMS("dynaforms"), 
		INPUT_DOCUMENTS("inputDocuments"), 
		OUTPUT_DOCUMENTS("outputDocuments"), 
		TRIGGERS("triggers"), 
		REPORT_TABLES("reportTables"), 
		DATABASE_CONNECTIONS("databaseConnections"), 
		CASE_SCHEDULER("caseScheduler");

		private String id;

		MenuOption(String id){
			this.id = id;
		}

		public String getId(){
			return this.id;
		}
	};
	
	public static void open(MenuOption option) throws Exception{
		Browser.driver().switchTo().frame("frameMain");
		Browser.getElement("processDesigner.webElement.menuItems."+option.getId()).click();
		Browser.driver().switchTo().defaultContent();
	}

	private static void openDynaforms() throws Exception{
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

	public static void newDynaform(DynaformType type) throws Exception{
		ProcessDesigner.openDynaforms();
		Browser.driver().switchTo().frame("frameMain");
		Browser.getElement("processDesigner.webElement.panelNewButton").click();
		Browser.getElement("processDesigner.webElement.newDynaform."+type.getId()).click();
		Browser.getElement("processDesigner.webElement.newDynaform.selectDynaformTypeButton").click();
	}

	public static DynaformDesigner newBlankDynaform(String title, DynaformSubType type, String description) throws Exception{
		ProcessDesigner.newDynaform(ProcessDesigner.DynaformType.BLANK);
		Browser.getElement("processDesigner.webElement.newBlankDynaform.title").sendKeys(title);
		new Select(Browser.getElement("processDesigner.webElement.newBlankDynaform.type")).selectByValue(ConfigurationSettings.getInstance().getSetting("processDesigner.webElement.newBlankDynaform.type."+type.getId()));
		Browser.getElement("processDesigner.webElement.newBlankDynaform.description").sendKeys(description);
		Browser.getElement("processDesigner.webElement.newBlankDynaform.saveOpen").click();
		return new DynaformDesigner(Browser.getElement("processDesigner.webElement.newBlankDynaform.designer"));
	}

	public static DynaformDesigner newBlankDynaform(String title, DynaformSubType type) throws Exception{
		return ProcessDesigner.newBlankDynaform(title, type, "");
	}
}