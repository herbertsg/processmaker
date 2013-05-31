package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.WebElement;

public class OutputDocumentList extends Page{


    public OutputDocumentList(BrowserInstance browser) throws Exception {
        super(browser);
        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void createOutputDoc(String fileTitle, String fileName, String description, String docMedia, String docLandscape, String docGenerate, String securityEnabled, String versioning, String destinationPath, String tagField) throws Exception{
		browser.switchToFrame("frameMain");
		WebElement newBtn = browser.findElement("inputDocumentList.webelement.new");
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
		fieldArray[0].fieldFindType = FieldKeyType.ID;
		fieldArray[0].fieldType = FieldType.TEXTBOX;
		fieldArray[0].fieldValue = fileTitle;		
		fieldArray[1].fieldPath = "form[OUT_DOC_FILENAME]";
		fieldArray[1].fieldFindType = FieldKeyType.ID;
		fieldArray[1].fieldType = FieldType.TEXTBOX;
		fieldArray[1].fieldValue = fileName;
		fieldArray[2].fieldPath = "form[OUT_DOC_DESCRIPTION]";
		fieldArray[2].fieldFindType = FieldKeyType.ID;
		fieldArray[2].fieldType = FieldType.TEXTBOX;
		fieldArray[2].fieldValue = description;
		fieldArray[3].fieldPath = "form[OUT_DOC_MEDIA]";
		fieldArray[3].fieldFindType = FieldKeyType.ID;
		fieldArray[3].fieldType = FieldType.DROPDOWN;
		fieldArray[3].fieldValue = docMedia;
		fieldArray[4].fieldPath = "form[OUT_DOC_LANDSCAPE]";
		fieldArray[4].fieldFindType = FieldKeyType.ID;
		fieldArray[4].fieldType = FieldType.DROPDOWN;
		fieldArray[4].fieldValue = docLandscape;
		fieldArray[5].fieldPath = "form[OUT_DOC_GENERATE]";
		fieldArray[5].fieldFindType = FieldKeyType.ID;
		fieldArray[5].fieldType = FieldType.DROPDOWN;
		fieldArray[5].fieldValue = docGenerate;
		fieldArray[6].fieldPath = "form[OUT_DOC_PDF_SECURITY_ENABLED]";
		fieldArray[6].fieldFindType = FieldKeyType.ID;
		fieldArray[6].fieldType = FieldType.DROPDOWN;
		fieldArray[6].fieldValue = securityEnabled;
		fieldArray[7].fieldPath = "form[OUT_DOC_VERSIONING]";
		fieldArray[7].fieldFindType = FieldKeyType.ID;
		fieldArray[7].fieldType = FieldType.DROPDOWN;
		fieldArray[7].fieldValue = versioning;
		fieldArray[8].fieldPath = "form[OUT_DOC_DESTINATION_PATH]";
		fieldArray[8].fieldFindType = FieldKeyType.ID;
		fieldArray[8].fieldType = FieldType.TEXTBOX;
		fieldArray[8].fieldValue = destinationPath;
		fieldArray[9].fieldPath = "form[OUT_DOC_TAGS]";
		fieldArray[9].fieldFindType = FieldKeyType.ID;
		fieldArray[9].fieldType = FieldType.TEXTBOX;
		fieldArray[9].fieldValue = tagField;
		fieldArray[10].fieldPath = "form[ACCEPT]";
		fieldArray[10].fieldFindType = FieldKeyType.ID;
		fieldArray[10].fieldType = FieldType.BUTTON;
		fieldArray[10].fieldValue = "";
		FormFiller.formFillElements(browser, fieldArray);

		browser.switchToDefaultContent();

	}

	public void closePopup() throws Exception{
		browser.findElement("outputDocumentList.webelement.close").click();
	}


}