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

public class OutputDocumentList{

	public OutputDocumentList() throws FileNotFoundException, IOException{
	}

	public void createOutputDoc(String fileTitle, String fileName, String description, String docMedia, String docLandscape, String docGenerate, String securityEnabled, String versioning, String destinationPath, String tagField) throws Exception{
		Browser.driver().switchTo().frame("frameMain");
		WebElement newBtn = Browser.getElement("inputDocumentList.webelement.new");
		newBtn.click();
		FormFieldData[] fieldArray = new FormFieldData[11];
		fieldArray[0] = new FormFieldData();
		fieldArray[1] = new FormFieldData();
		fieldArray[2] = new FormFieldData();
		fieldArray[3] = new FormFieldData();
		fieldArray[4] = new FormFieldData();
		fieldArray[5] = new FormFieldData();
		fieldArray[6] = new FormFieldData();
		fieldArray[7] = new FormFieldData();
		fieldArray[8] = new FormFieldData();
		fieldArray[9] = new FormFieldData();
		fieldArray[10] = new FormFieldData();

		fieldArray[0].fieldPath = "form[OUT_DOC_TITLE]";
		fieldArray[0].fieldFindType = "id";
		fieldArray[0].fieldType = "textbox";
		fieldArray[0].fieldValue = fileTitle;		
		fieldArray[1].fieldPath = "form[OUT_DOC_FILENAME]";
		fieldArray[1].fieldFindType = "id";
		fieldArray[1].fieldType = "textbox";
		fieldArray[1].fieldValue = fileName;
		fieldArray[2].fieldPath = "form[OUT_DOC_DESCRIPTION]";
		fieldArray[2].fieldFindType = "id";
		fieldArray[2].fieldType = "textbox";
		fieldArray[2].fieldValue = description;
		fieldArray[3].fieldPath = "form[OUT_DOC_MEDIA]";
		fieldArray[3].fieldFindType = "id";
		fieldArray[3].fieldType = "dropdown";
		fieldArray[3].fieldValue = docMedia;
		fieldArray[4].fieldPath = "form[OUT_DOC_LANDSCAPE]";
		fieldArray[4].fieldFindType = "id";
		fieldArray[4].fieldType = "dropdown";
		fieldArray[4].fieldValue = docLandscape;
		fieldArray[5].fieldPath = "form[OUT_DOC_GENERATE]";
		fieldArray[5].fieldFindType = "id";
		fieldArray[5].fieldType = "dropdown";
		fieldArray[5].fieldValue = docGenerate;
		fieldArray[6].fieldPath = "form[OUT_DOC_PDF_SECURITY_ENABLED]";
		fieldArray[6].fieldFindType = "id";
		fieldArray[6].fieldType = "dropdown";
		fieldArray[6].fieldValue = securityEnabled;
		fieldArray[7].fieldPath = "form[OUT_DOC_VERSIONING]";
		fieldArray[7].fieldFindType = "id";
		fieldArray[7].fieldType = "dropdown";
		fieldArray[7].fieldValue = versioning;
		fieldArray[8].fieldPath = "form[OUT_DOC_DESTINATION_PATH]";
		fieldArray[8].fieldFindType = "id";
		fieldArray[8].fieldType = "textbox";
		fieldArray[8].fieldValue = destinationPath;
		fieldArray[9].fieldPath = "form[OUT_DOC_TAGS]";
		fieldArray[9].fieldFindType = "id";
		fieldArray[9].fieldType = "textbox";
		fieldArray[9].fieldValue = tagField;
		fieldArray[10].fieldPath = "form[ACCEPT]";
		fieldArray[10].fieldFindType = "id";
		fieldArray[10].fieldType = "button";
		fieldArray[10].fieldValue = "";
		FormFiller.formFillElements(fieldArray);

		Browser.driver().switchTo().defaultContent();

	}

	public void closePopup() throws Exception{
		Browser.getElement("outputDocumentList.webelement.close").click();
	}


}