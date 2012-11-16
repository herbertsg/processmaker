package com.colosa.qa.automatization.tests.inputDocuments;

import org.junit.Assert;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

import com.colosa.qa.automatization.pages.*;
import com.colosa.qa.automatization.common.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestInputDocument{


	@Test
	public void inputDocProcess() throws FileNotFoundException, IOException, Exception{

		JavascriptExecutor js = (JavascriptExecutor) Browser.driver();
		Pages.Login().gotoUrl();
		Pages.Login().loginUser("admin", "admin", "workflow");
		Pages.Main().goHome();
		int casenumb = Pages.Home().startCase("Input Process with and without versioning (enviar formulario y documento)");
		Pages.InputDocProcess().selectOpenCaseFrame();
		Browser.getElement("testinputdocuments.webelement.name").sendKeys("Ernesto Vega Panozo");
		Browser.getElement("testinputdocuments.webelement.date").sendKeys("1987-12-29");
		js.executeScript("document.getElementById('form[fechanacimiento]').value=\"1987-12-29\"");
		Browser.getElement("testinputdocuments.webelement.ci").sendKeys("46545644");
		Select droplist = new Select(Browser.getElement("testinputdocuments.webelement.carreer"));
		droplist.selectByVisibleText("ingenieria electronica");
		Browser.getElement("testinputdocuments.webelement.rdpar").click();
		Browser.getElement("testinputdocuments.webelement.send").click();
		Pages.InputDocProcess().uploadFile("/home/ernesto/Documents/Prueba_Input_Doc.docx", "Test File");
		Pages.InputDocProcess().uploadFile("/home/ernesto/Documents/Prueba_Input_Doc.docx", "Test File");
		Pages.InputDocProcess().continuebtn();

		Pages.Home().openCase(casenumb);
		Pages.InputDocProcess().selectOpenCaseFrame();	
		Browser.getElement("testinputdocuments.webelement.send").click();
		Browser.getElement("inputDocProcess.webelement.submit").click();
		Pages.InputDocProcess().continuebtn();


	}


}