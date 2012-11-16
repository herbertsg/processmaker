package com.colosa.qa.automatization.common.dynaform.controls;

import com.colosa.qa.automatization.common.Browser;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

public class ControlOptions{

	public String fieldName = null;
	public String label = "";
	public Boolean required = false;
	public Boolean readOnly = false; 
	public String dependentFields = null; //como funciona?
	public String defaultValue = "";
	public String hint = "";
	public Mode mode = Mode.EDIT;
	public SQLConnection sqlConnection = SQLConnection.NONE; 
	public String sql = "";

	public enum Mode{
		EDIT("edit"),
		VIEW("view");

		private String value;

		Mode(String value){
			this.value = value;
		}

		public String getValue(){
			return this.value;
		}
	}

	public enum SQLConnection{
		NONE(""),
		DBARRAY("dbarray"),
		WORKFLOW("workflow"),
		RBAC("rbac"),
		REPORT("rp");

		private String value;

		SQLConnection(String value){
			this.value = value;
		}

		public String getValue(){
			return this.value;
		}
	}

	protected void fillForm() throws Exception{
		if(this.fieldName == null || this.fieldName.equals(""))
			throw new Exception("The textfield name must be specified");
		WebElement we = null;
		Select ddown = null;
		Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.fieldName").sendKeys(this.fieldName);
		Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.label").sendKeys(this.label);
		
		we = Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.required"); 
		if(this.required != we.isSelected())
			we.click();
		we = Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.readOnly");
		if(this.readOnly != we.isSelected())
			we.click();
		//how it works???
		//Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.dependentFields").sendKeys(this.dependentFields); 
		Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.defaultValue").sendKeys(this.defaultValue);
		
		ddown = new Select(Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.mode"));
		ddown.selectByValue(this.mode.getValue());
		ddown = new Select(Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.sqlConnection"));
		ddown.selectByValue(this.sqlConnection.getValue());
		Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.sql").sendKeys(this.sql);
	}

}