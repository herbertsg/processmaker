package com.colosa.qa.automatization.tests.login;

import java.lang.Exception;
import com.colosa.qa.automatization.pages.Home;
import com.colosa.qa.automatization.pages.Pages;
import com.colosa.qa.automatization.pages.ProcessDesigner;
import com.colosa.qa.automatization.common.Browser;
import com.colosa.qa.automatization.common.dynaform.DynaformDesigner;
import com.colosa.qa.automatization.common.dynaform.controls.input.TextFieldOptions;
import com.colosa.qa.automatization.common.dynaform.controls.selection.DropDownOptions;
import com.colosa.qa.automatization.common.dynaform.controls.ControlOptions;
import com.colosa.qa.automatization.common.extJs.ExtJSGrid;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;

public class ProcessDesignerTest{

	public static void main(String[] args) throws Exception{
		Pages.Login().gotoUrl();
		Pages.Login().loginDefaultUser();
		Pages.Main().goDesigner();

		Browser.driver().switchTo().frame("frameMain");

		ExtJSGrid grid = new ExtJSGrid(Browser.driver().findElement(By.id("processesGrid")), Browser.driver(), 10);
		WebElement we = grid.getRowByColumnValue("Process Title", "Test X");
		Actions action = new Actions(Browser.driver());
		action.doubleClick(we);
                action.perform();

                Browser.driver().switchTo().defaultContent();
                DynaformDesigner dynaform = ProcessDesigner.newBlankDynaform(new java.util.Date().toString(), ProcessDesigner.DynaformSubType.NORMAL);
                TextFieldOptions options = new TextFieldOptions();
                options.fieldName = "myText";
                options.label = "ingrese un texto";
                options.maxLength = 6;
                options.validate = TextFieldOptions.Validate.ALPHA_NUMERIC;
                options.mask = "mask";
                options.textTransform = TextFieldOptions.TextTransform.UPPER_CASE;
                options.required = true;
                options.readOnly = true;
                //options.dependentFields = ""; --> cómo funciona
                options.defaultValue = "abc";
                options.hint = "Escriba cualquier texto";
                options.size = 20;
                options.mode = ControlOptions.Mode.EDIT;
                options.sqlConnection = ControlOptions.SQLConnection.REPORT;
                options.sql = "some sql sentence";
                dynaform.addTextField(options);



/*
                DropDownOptions options = new DropDownOptions();
                options.fieldName = "myText";
                options.label = "seleccione una opción";
                options.required = true;

                options.addOption("", "");
                dynaform.addDropDown(options);*/

		System.out.println("----fin..");
	}

}