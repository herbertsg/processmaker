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

public class InputDocumentList{

	public InputDocumentList() throws FileNotFoundException, IOException{

	}

	public void createInputDoc(String titleDoc, String formatDoc, String descriptionDoc, String versioning, String destination, String tags) throws Exception{

		Browser.driver().switchTo().frame("frameMain");
		WebElement newBtn = Browser.getElement("id___form[MNU_ADD]");

		newBtn.click();
		FormFieldData[] fieldArray = new FormFieldData[7];
		fieldArray[0] = new FormFieldData();
		fieldArray[1] = new FormFieldData();
		fieldArray[2] = new FormFieldData();
		fieldArray[3] = new FormFieldData();
		fieldArray[4] = new FormFieldData();
		fieldArray[5] = new FormFieldData();
		fieldArray[6] = new FormFieldData();

		fieldArray[0].fieldPath = "form[INP_DOC_TITLE]";
		fieldArray[0].fieldFindType = "id";
		fieldArray[0].fieldType = "textbox";
		fieldArray[0].fieldValue = titleDoc;
		fieldArray[1].fieldPath = "form[INP_DOC_FORM_NEEDED]";
		fieldArray[1].fieldFindType = "id";
		fieldArray[1].fieldType = "dropdown";
		fieldArray[1].fieldValue = formatDoc;
		fieldArray[2].fieldPath = "form[INP_DOC_DESCRIPTION]";
		fieldArray[2].fieldFindType = "id";
		fieldArray[2].fieldType = "textarea";
		fieldArray[2].fieldValue = descriptionDoc;
		fieldArray[3].fieldPath = "form[INP_DOC_VERSIONING]";
		fieldArray[3].fieldFindType = "id";
		fieldArray[3].fieldType = "dropdown";
		fieldArray[3].fieldValue = versioning;
		fieldArray[4].fieldPath = "form[INP_DOC_DESTINATION_PATH]";
		fieldArray[4].fieldFindType = "id";
		fieldArray[4].fieldType = "textbox";
		fieldArray[4].fieldValue = destination;
		fieldArray[5].fieldPath = "form[INP_DOC_TAGS]";
		fieldArray[5].fieldFindType = "id";
		fieldArray[5].fieldType = "textbox";
		fieldArray[5].fieldValue = tags;
		fieldArray[6].fieldPath = "form[ACCEPT]";
		fieldArray[6].fieldFindType = "id";
		fieldArray[6].fieldType = "button";
		fieldArray[6].fieldValue = "";
		FormFiller.formFillElements(fieldArray);

		Browser.driver().switchTo().defaultContent();

	}

	public void closePopup() throws Exception{
		Browser.driver().switchTo().frame("frameMain");
		WebElement btnClose = Browser.getElement("inputDocumentList.webelement.close");
		btnClose.click();
		Browser.driver().switchTo().defaultContent();
	}

}