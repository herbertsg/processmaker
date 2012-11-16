package com.colosa.qa.automatization.common.dynaform.controls.input;

import com.colosa.qa.automatization.common.Browser;
import com.colosa.qa.automatization.common.dynaform.controls.ControlOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebElement;

public class TextFieldOptions extends ControlOptions{

	
	public int maxLength = 64;
	public Validate validate = Validate.ANY;
	public String mask = "";
	public TextTransform textTransform = TextTransform.NONE;
	public int size = 15;

	public enum TextTransform{
		NONE(""),
		UPPER_CASE("UPPER"),
		LOWER_CASE("LOWER");

		private String value;

		TextTransform(String value){
			this.value = value;
		}

		public String getValue(){
			return this.value;
		}
	}

	public enum Validate{
		ANY("Any"),
		ALPHA("Alpha"),
		ALPHA_NUMERIC("AlphaNum"),
		INT("Int"),
		REAL("Real"),
		EMAIL("Email"),
		LOGIN("Login");

		private String value;

		Validate(String value){
			this.value = value;
		}

		public String getValue(){
			return this.value;
		}
	}

	@Override
	public void fillForm() throws Exception{
		super.fillForm();
		WebElement we = null;
		Select ddown = null;
		we = Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.maxLength");
		we.clear();
		we.sendKeys(Integer.toString(this.maxLength));
		ddown = new Select(Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.validate"));
		ddown.selectByValue(this.validate.getValue());
		Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.mask").sendKeys(this.mask);
		ddown = new Select(Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.textTransform"));
		ddown.selectByValue(this.textTransform.getValue());
		Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.hint").sendKeys(this.hint);
		we = Browser.getElement("dynaformDesigner.webElement.blankDynaformModal.size");
		we.clear();
		we.sendKeys(Integer.toString(this.size));
	}
}