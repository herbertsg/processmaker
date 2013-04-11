package com.colosa.qa.automatization.pages;

import com.colosa.qa.automatization.common.BrowserInstance;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.File;


public class OutputDocProcess extends Page{


    public OutputDocProcess(BrowserInstance browser) {
        super(browser);
    }

    public void downloadPdfFile() throws Exception{

		browser.switchToDefaultContent();
		browser.switchToFrame("casesFrame");
		browser.switchToFrame("casesSubFrame");
		browser.switchToFrame("openCaseFrame");
		browser.findElement("outputDocProcess.webelement.outputPdfButton").click();

	}

	public void downloadDocFile() throws Exception{



		browser.switchToDefaultContent();
		browser.switchToFrame("casesFrame");
		browser.switchToFrame("casesSubFrame");
		browser.switchToFrame("openCaseFrame");
		browser.findElement("outputDocProcess.webelement.outputDocButton").click();

	}

	public void nextbtn() throws Exception{

		browser.findElement("outputDocProcess.webelement.nextButton").click();

	}

	public void continuebtn() throws Exception{

		browser.findElement("inputDocProcess.webelement.continue").click();

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