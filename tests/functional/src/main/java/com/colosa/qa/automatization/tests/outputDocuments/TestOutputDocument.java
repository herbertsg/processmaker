package com.colosa.qa.automatization.tests.outputDocuments;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestOutputDocument extends com.colosa.qa.automatization.tests.common.Test{


    public TestOutputDocument(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void downloadOutputDoc() throws Exception{

		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin", "admin", "workflow", "English");
		pages.Main().goHome();
		
		int casenumber = pages.Home().gotoNewCase().startCase("Test OutputDocument (Task 1)");

		pages.OutputDocProcess().downloadDocFile();
		pages.OutputDocProcess().downloadPdfFile();
		pages.OutputDocProcess().nextbtn();

        DynaformExecution form = pages.DynaformExecution();

		form.intoDynaform();
		pages.OutputDocProcess().downloadDocFile();
		pages.OutputDocProcess().downloadPdfFile();
		pages.OutputDocProcess().nextbtn();
		
		pages.OutputDocProcess().continuebtn();
		
		pages.Home().gotoInbox().openCase(casenumber);
        //Assert.assertTrue("The case does not exist in inbox", pages.Home().existCase(casenumber));
		//pages.Home() .openCase(casenumber);

        form.intoDynaform();
		FormFieldData[] fieldArray=new FormFieldData[1];
    fieldArray[0]=new FormFieldData();
		
    fieldArray[0].fieldPath="form[SUBMIT]";
    fieldArray[0].fieldFindType=FieldKeyType.ID;
    fieldArray[0].fieldType=FieldType.BUTTON;
    fieldArray[0].fieldValue="";
		
		pages.OutputDocProcess().downloadDocFile();
		pages.OutputDocProcess().downloadPdfFile();
		pages.OutputDocProcess().nextbtn();
		
		pages.OutputDocProcess().downloadDocFile();
		pages.OutputDocProcess().downloadPdfFile();
		pages.OutputDocProcess().nextbtn();
		
		String fieldOUTPUT_CONTENT = form.getFieldAttribute("OUTPUT_CONTENT", "value");
		String fieldOUTPUT_TEMPLATE = form.getFieldAttribute("OUTPUT_TEMPLATE", "value");
		String fieldNOT_VERSION = form.getFieldAttribute("NOT_VERSION", "value");
		String fieldVERSION = form.getFieldAttribute("VERSION", "value");
		
		Assert.assertEquals("Output Content generated is distinct to Output Template", fieldOUTPUT_CONTENT, fieldOUTPUT_TEMPLATE);
    Assert.assertEquals("Exist Output Document Version", "DOES NOT EXIST VERSION", fieldNOT_VERSION);
    Assert.assertEquals("Does not exist Output Document Version", "EXIST VERSION", fieldVERSION);

		FormFiller.formFillElements( browserInstance, fieldArray);
    pages.OutputDocProcess().continuebtn();
		pages.InputDocProcess().switchToDefault();
		pages.Main().logout();
	}

    @After
    public void cleanup(){
        browserInstance.quit();
    }
}