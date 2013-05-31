package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.colosa.qa.automatization.common.ConfigurationSettings;
import com.colosa.qa.automatization.common.controlOptions.input.*;
import com.colosa.qa.automatization.common.controlOptions.selection.DropDownOptions;
import com.colosa.qa.automatization.common.controlOptions.selection.ListBoxOptions;
import com.colosa.qa.automatization.common.controlOptions.selection.YesNoOptions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

public class DynaformDesigner extends Page{

	private WebElement titleBar;
	private Map<String, WebElement> toolbar = new HashMap<String, WebElement>();
	private WebElement contentPanel;
	private WebElement tabBar;
	private WebElement panelLoader;
	private WebElement statusBar;


	public DynaformDesigner(BrowserInstance browserInstance) throws Exception{

        this(browserInstance, browserInstance.findElement("processDesigner.webElement.newBlankDynaform.designer"));

        verifyPage();
	}

    @Override
    public void verifyPage() throws Exception {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public DynaformDesigner(BrowserInstance browserInstance, WebElement we) throws Exception{
        super(browserInstance);

        WebElement aux = we.findElement(By.cssSelector("#fields_Toolbar"));
		this.titleBar = we.findElement(By.xpath("div[@class='panel_titleBar___processmaker']"));
		this.toolbar.put("save", browser.findElement("dynaformDesigner.webElement.toolbar.save"));
		this.toolbar.put("save as", browser.findElement("dynaformDesigner.webElement.toolbar.saveAs"));
		this.toolbar.put("text field", browser.findElement("dynaformDesigner.webElement.toolbar.textField"));
		this.toolbar.put("currency", browser.findElement("dynaformDesigner.webElement.toolbar.currency"));
		this.toolbar.put("percentage", browser.findElement("dynaformDesigner.webElement.toolbar.percentage"));
		this.toolbar.put("password", browser.findElement("dynaformDesigner.webElement.toolbar.password"));
		this.toolbar.put("suggest", browser.findElement("dynaformDesigner.webElement.toolbar.suggest"));
		this.toolbar.put("text area", browser.findElement("dynaformDesigner.webElement.toolbar.textArea"));
		this.toolbar.put("title", browser.findElement("dynaformDesigner.webElement.toolbar.title"));
		this.toolbar.put("subtitle", browser.findElement("dynaformDesigner.webElement.toolbar.subtitle"));
		this.toolbar.put("button", browser.findElement("dynaformDesigner.webElement.toolbar.button"));
		this.toolbar.put("submit", browser.findElement("dynaformDesigner.webElement.toolbar.submit"));
		this.toolbar.put("reset", browser.findElement("dynaformDesigner.webElement.toolbar.reset"));
		this.toolbar.put("dropdown", browser.findElement("dynaformDesigner.webElement.toolbar.dropdown"));
		this.toolbar.put("yes no", browser.findElement("dynaformDesigner.webElement.toolbar.yesNo"));
		this.toolbar.put("listbox", browser.findElement("dynaformDesigner.webElement.toolbar.listbox"));
		this.toolbar.put("checkbox", browser.findElement("dynaformDesigner.webElement.toolbar.checkbox"));
		this.toolbar.put("checkgroup", browser.findElement("dynaformDesigner.webElement.toolbar.checkgroup"));
		this.toolbar.put("radiogroup", browser.findElement("dynaformDesigner.webElement.toolbar.radiogroup"));
		this.toolbar.put("date", browser.findElement("dynaformDesigner.webElement.toolbar.date"));
		this.toolbar.put("hidden", browser.findElement("dynaformDesigner.webElement.toolbar.hidden"));
		this.toolbar.put("link", browser.findElement("dynaformDesigner.webElement.toolbar.link"));
		this.toolbar.put("file", browser.findElement("dynaformDesigner.webElement.toolbar.file"));
		this.toolbar.put("javascript", browser.findElement("dynaformDesigner.webElement.toolbar.javascript"));
		this.toolbar.put("grid", browser.findElement("dynaformDesigner.webElement.toolbar.grid"));
	}
/*
	private void addControl(String control, ControlOptions options){
		this.toolbar.get(control).click();
		options.fillForm();
		browser.findElement("dynaformDesigner.webElement.blankDynaformModal.submitButton").click();
	}*/

	private void setNames(String fieldname, String label) throws Exception{
		if(fieldname == null || fieldname.trim().equals(""))
			throw new Exception("The control name must be specified");
		browser.findElement("dynaformDesigner.webElement.blankDynaformModal.fieldName").sendKeys(fieldname);
		browser.findElement("dynaformDesigner.webElement.blankDynaformModal.label").sendKeys(label);
	}

	private void sendModalForm() throws Exception{
		browser.findElement("dynaformDesigner.webElement.blankDynaformModal.submitButton").click();
	}

	public void addTextField(String fieldname, String label, TextFieldOptions options) throws Exception{
		this.toolbar.get("text field").click();
		this.setNames(fieldname, label);
		options.fillForm(browser);
		this.sendModalForm();
	}

	public void addDropDown(String fieldname, String label, DropDownOptions options) throws Exception{
		this.toolbar.get("dropdown").click();
		this.setNames(fieldname, label);
		options.fillForm(browser);
		this.sendModalForm();
	}

	public void addTextArea(String fieldname, String label, TextAreaOptions options) throws Exception{
		this.toolbar.get("text area").click();
		this.setNames(fieldname, label);
		options.fillForm(browser);
		this.sendModalForm();
	}

	public void addPassword(String fieldname, String label, PasswordOptions options) throws Exception{
		this.toolbar.get("password").click();
		this.setNames(fieldname, label);
		options.fillForm(browser);
		this.sendModalForm();
	}

	public void addSuggest(String fieldname, String label, SuggestOptions options) throws Exception{
		this.toolbar.get("suggest").click();
		this.setNames(fieldname, label);
		options.fillForm(browser);
		this.sendModalForm();
	}

	public void addCurrency(String fieldname, String label, NumericOptions options) throws Exception{
		this.toolbar.get("currency").click();
		this.setNames(fieldname, label);
		options.fillForm(browser);
		this.sendModalForm();
	}

	public void addPercentage(String fieldname, String label, NumericOptions options) throws Exception{
		this.toolbar.get("percentage").click();
		this.setNames(fieldname, label);
		options.fillForm(browser);
		this.sendModalForm();
	}

	public void addButton(String fieldname, String label, String javascript) throws Exception{
		this.toolbar.get("button").click();
		this.setNames(fieldname, label);
		browser.findElement("dynaformDesigner.webElement.blankDynaformModal.javascriptToExecuteOnClickButton").sendKeys(javascript);
		this.sendModalForm();
	}

	public void addSubmit(String fieldname, String label, String javascript) throws Exception{
		this.toolbar.get("submit").click();
		this.setNames(fieldname, label);
		browser.findElement("dynaformDesigner.webElement.blankDynaformModal.javascriptToExecuteOnClickButton").sendKeys(javascript);
		this.sendModalForm();
	}

	public void addReset(String fieldname, String label) throws Exception{
		this.toolbar.get("reset").click();
		this.setNames(fieldname, label);
		this.sendModalForm();
	}

	public void addTitle(String fieldname, String label, Boolean enableHTML) throws Exception{
		this.toolbar.get("title").click();
		this.setNames(fieldname, label);
		WebElement we = browser.findElement("dynaformDesigner.webElement.blankDynaformModal.enableHTML");
		if(enableHTML != we.isSelected())
			we.click();
		this.sendModalForm();
	}

	public void addSubtitle(String fieldname, String content, Boolean enableHTML) throws Exception{
		this.toolbar.get("title").click();
		this.setNames(fieldname, content);
		WebElement we = browser.findElement("dynaformDesigner.webElement.blankDynaformModal.enableHTML");
		if(enableHTML != we.isSelected())
			we.click();
		this.sendModalForm();
	}

	public void addYesNo(String fieldname, String label, YesNoOptions options) throws Exception{
		this.toolbar.get("yes no").click();
		this.setNames(fieldname, label);
		options.fillForm(browser);
		this.sendModalForm();
	}

	public void addListBox(String fieldname, String label, ListBoxOptions options) throws Exception{
		this.toolbar.get("listbox").click();
		this.setNames(fieldname, label);
		options.fillForm(browser);
		this.sendModalForm();
	}

	public Boolean inPage() throws Exception{
		return (browser.getInstanceDriver().getCurrentUrl().indexOf(ConfigurationSettings.getInstance().getSetting("dynaformDesigner.idURL")) >= 0);
	}

	public Boolean inPage(String formName) throws Exception{
		if(browser.findElements("dynaformDesigner.formNameLocation").size() == 0)
			return false;
		return (browser.findElement("dynaformDesigner.formNameLocation").getText().trim().equals(formName.trim())) && this.inPage() ;
	}
/*
	public void addJavascript(String fieldname, String script){
		this.toolbar.get("title").click();
		if(fieldname == null || fieldname.trim().equals(""))
			throw new Exception("The control name must be specified");
		browser.findElement("dynaformDesigner.webElement.blankDynaformModal.fieldName").sendKeys(fieldname);
		browser.findElement("dynaformDesigner.webElement.blankDynaformModal.script").sendKeys(script);
		this.sendModalForm();
	}*/

	public Boolean save() throws Exception{
		this.toolbar.get("save").click();
        Alert alert = browser.switchToAlert();
        Boolean res = alert.getText().equals("DynaForm is now saved");
        alert.accept();
        browser.switchToDefaultContent();
        browser.switchToFrame("frameMain");
        return res;
	}

	public void saveAs(String title, String description) throws Exception{
		this.toolbar.get("save as").click();
		browser.findElement("dynaformDesigner.webElement.blankDynaformModal.saveAs.title").sendKeys(title);
		browser.findElement("dynaformDesigner.webElement.blankDynaformModal.saveAs.description").sendKeys(description);
		browser.findElement("dynaformDesigner.webElement.blankDynaformModal.saveAs.saveButton").click();
	}

}