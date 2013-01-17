package com.colosa.qa.automatization.pages;

import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;


import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import com.colosa.qa.automatization.common.*;
import com.colosa.qa.automatization.common.extJs.*;



public class OutputDocProcess extends Page{

	public OutputDocProcess() throws FileNotFoundException, IOException{
		
	}

	public void downloadPdfFile() throws Exception{

		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("casesFrame");
		Browser.driver().switchTo().frame("casesSubFrame");
		Browser.driver().switchTo().frame("openCaseFrame");
		Browser.getElement("outputDocProcess.webelement.outputPdfButton").click();

	}

	public void downloadDocFile() throws Exception{



		Browser.driver().switchTo().defaultContent();
		Browser.driver().switchTo().frame("casesFrame");
		Browser.driver().switchTo().frame("casesSubFrame");
		Browser.driver().switchTo().frame("openCaseFrame");
		Browser.getElement("outputDocProcess.webelement.outputDocButton").click();

	}

	public void nextbtn() throws Exception{

		Browser.getElement("outputDocProcess.webelement.nextButton").click();

	}

	public void continuebtn() throws Exception{

		Browser.getElement("inputDocProcess.webelement.continue").click();

	}


	public String checkPdfSecurity(String fileName, String passwordFile) throws Exception{
		String password = passwordFile; 
		String result = "";
		try {
			PdfReader reader = new PdfReader(fileName ,password.getBytes());
			int n = reader.getNumberOfPages();
        	PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        	TextExtractionStrategy strategy;
           	strategy = parser.processContent(1, new SimpleTextExtractionStrategy());
           	result = strategy.getResultantText();
        	reader.close();
      		File fileDel = new File(fileName);
			//fileDel.delete();

		} catch (Exception de) {
			result = de.getMessage();
		}

		return result;

	}


}