package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InputDocumentList extends Page{

    public InputDocumentList(BrowserInstance browser) throws Exception {
        super(browser);
        verifyPage();
    }

    @Override
    public void verifyPage() throws Exception {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void createInputDoc(String titleDoc, String formatDoc, String descriptionDoc, String versioning, String destination, String tags) throws Exception{

		browser.switchToFrame("frameMain");
		WebElement newBtn = browser.findElement("id" + Constant.SEARCH_CRITERIA_SEPARATOR + "form[MNU_ADD]");

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
		fieldArray[0].fieldFindType = FieldKeyType.ID;
		fieldArray[0].fieldType = FieldType.TEXTBOX;
		fieldArray[0].fieldValue = titleDoc;
		fieldArray[1].fieldPath = "form[INP_DOC_FORM_NEEDED]";
		fieldArray[1].fieldFindType = FieldKeyType.ID;
		fieldArray[1].fieldType = FieldType.DROPDOWN;
		fieldArray[1].fieldValue = formatDoc;
		fieldArray[2].fieldPath = "form[INP_DOC_DESCRIPTION]";
		fieldArray[2].fieldFindType = FieldKeyType.ID;
		fieldArray[2].fieldType = FieldType.TEXTAREA;
		fieldArray[2].fieldValue = descriptionDoc;
		fieldArray[3].fieldPath = "form[INP_DOC_VERSIONING]";
		fieldArray[3].fieldFindType = FieldKeyType.ID;
		fieldArray[3].fieldType = FieldType.DROPDOWN;
		fieldArray[3].fieldValue = versioning;
		fieldArray[4].fieldPath = "form[INP_DOC_DESTINATION_PATH]";
		fieldArray[4].fieldFindType = FieldKeyType.ID;
		fieldArray[4].fieldType = FieldType.TEXTBOX;
		fieldArray[4].fieldValue = destination;
		fieldArray[5].fieldPath = "form[INP_DOC_TAGS]";
		fieldArray[5].fieldFindType = FieldKeyType.ID;
		fieldArray[5].fieldType = FieldType.TEXTBOX;
		fieldArray[5].fieldValue = tags;
		fieldArray[6].fieldPath = "form[ACCEPT]";
		fieldArray[6].fieldFindType = FieldKeyType.ID;
		fieldArray[6].fieldType = FieldType.BUTTON;
		fieldArray[6].fieldValue = "";
		FormFiller.formFillElements(browser, fieldArray);

		browser.switchToDefaultContent();

	}

	public void closePopup() throws Exception{
		browser.switchToFrame("frameMain");
		WebElement btnClose = browser.findElement("inputDocumentList.webelement.close");
		btnClose.click();
		browser.switchToDefaultContent();
	}

	public boolean fileExists(String fileName) throws Exception{
		List<WebElement> wel;
		boolean flag = false;
		//html/body/table/tbody/tr/td/table/tbody/tr[3]/td/div/table/tbody/tr/td/div[2]/table/tbody/tr/td/table[2]/tbody/tr[2]/td[2]
		WebElement we = null;
		wel = browser.findElementsByXPath("//table/tbody/tr/td/table/tbody/tr[3]/td/div/table/tbody/tr/td/div[2]/table/tbody/tr/td/table[2]/tbody/tr");
		for(WebElement we2:wel)
		{
			we = we2.findElement(By.xpath("td[2]"));
			if(we.getText().equals(fileName))
			{
				flag = true;
				break;
			}
		}
		if(!flag)
			throw new Exception("file \""+fileName+"\" not found");

		return flag;	

	}

}