package com.colosa.qa.automatization.common.dynaform;

import java.lang.Exception;
import com.colosa.qa.automatization.common.Browser;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import java.util.*;
import com.colosa.qa.automatization.common.dynaform.controls.input.TextFieldOptions;
import com.colosa.qa.automatization.common.dynaform.controls.selection.DropDownOptions;

public class DynaformDesigner{

	private WebElement titleBar;
	private Map<String, WebElement> toolbar = new HashMap<String, WebElement>();
	private WebElement contentPanel;
	private WebElement tabBar;
	private WebElement panelLoader;
	private WebElement statusBar;

	public DynaformDesigner(WebElement we) throws Exception{
		WebElement aux = we.findElement(By.cssSelector("#fields_Toolbar"));
		this.titleBar = we.findElement(By.xpath("div[@class='panel_titleBar___processmaker']"));
		this.toolbar.put("save", Browser.getElement("dynaformDesigner.webElement.toolbar.save"));
		this.toolbar.put("save as", Browser.getElement("dynaformDesigner.webElement.toolbar.saveAs"));
		this.toolbar.put("text field", Browser.getElement("dynaformDesigner.webElement.toolbar.textField"));
		this.toolbar.put("currency", Browser.getElement("dynaformDesigner.webElement.toolbar.currency"));
		this.toolbar.put("percentage", Browser.getElement("dynaformDesigner.webElement.toolbar.percentage"));
		this.toolbar.put("password", Browser.getElement("dynaformDesigner.webElement.toolbar.password"));
		this.toolbar.put("suggest", Browser.getElement("dynaformDesigner.webElement.toolbar.suggest"));
		this.toolbar.put("text area", Browser.getElement("dynaformDesigner.webElement.toolbar.textArea"));
		this.toolbar.put("title", Browser.getElement("dynaformDesigner.webElement.toolbar.title"));
		this.toolbar.put("subtitle", Browser.getElement("dynaformDesigner.webElement.toolbar.subtitle"));
		this.toolbar.put("button", Browser.getElement("dynaformDesigner.webElement.toolbar.button"));
		this.toolbar.put("submit", Browser.getElement("dynaformDesigner.webElement.toolbar.submit"));
		this.toolbar.put("reset", Browser.getElement("dynaformDesigner.webElement.toolbar.reset"));
		this.toolbar.put("dropdown", Browser.getElement("dynaformDesigner.webElement.toolbar.dropdown"));
		this.toolbar.put("yes no", Browser.getElement("dynaformDesigner.webElement.toolbar.yesNo"));
		this.toolbar.put("listbox", Browser.getElement("dynaformDesigner.webElement.toolbar.listbox"));
		this.toolbar.put("checkbox", Browser.getElement("dynaformDesigner.webElement.toolbar.checkbox"));
		this.toolbar.put("checkgroup", Browser.getElement("dynaformDesigner.webElement.toolbar.checkgroup"));
		this.toolbar.put("radiogroup", Browser.getElement("dynaformDesigner.webElement.toolbar.radiogroup"));
		this.toolbar.put("date", Browser.getElement("dynaformDesigner.webElement.toolbar.date"));
		this.toolbar.put("hidden", Browser.getElement("dynaformDesigner.webElement.toolbar.hidden"));
		this.toolbar.put("link", Browser.getElement("dynaformDesigner.webElement.toolbar.link"));
		this.toolbar.put("file", Browser.getElement("dynaformDesigner.webElement.toolbar.file"));
		this.toolbar.put("javascript", Browser.getElement("dynaformDesigner.webElement.toolbar.javascript"));
		this.toolbar.put("grid", Browser.getElement("dynaformDesigner.webElement.toolbar.grid"));
	}

	public void addTextField(TextFieldOptions options) throws Exception{
		this.toolbar.get("text field").click();
		WebElement we = Browser.getElement("dynaformDesigner.webElement.blankDynaformModal");
		options.fillForm();
		this.sendModalForm();
	}

	private void sendModalForm() throws Exception{
		Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.submitButton").click();
	}

	public void addDropDown(DropDownOptions options) throws Exception{
		this.toolbar.get("dropdown").click();
		options.fillForm();
		this.sendModalForm();
	}

	public void save(){

	}	

}