package com.colosa.qa.automatization.tests.processExecutionForEvents;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.IOException;

public class TestEventIntermediate extends com.colosa.qa.automatization.tests.common.Test{

    public TestEventIntermediate(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess() throws Exception{
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin","admin","workflow", "English");
    pages.Main().goHome();
    int casenumber=pages.Home().gotoNewCase().startCase("Event Process - Intermediate Conditional_Multple Task (Task 1)");
    pages.DynaformExecution().intoDynaform();
    FormFieldData[] fieldArray=new FormFieldData[4];
    fieldArray[0]=new FormFieldData();
    fieldArray[1]=new FormFieldData();
    fieldArray[2]=new FormFieldData();
    fieldArray[3]=new FormFieldData();

    fieldArray[0].fieldPath="form[nombre]";
    fieldArray[0].fieldFindType=FieldKeyType.ID;
    fieldArray[0].fieldType=FieldType.TEXTBOX;
    fieldArray[0].fieldValue="rodrigo ivan";

    fieldArray[1].fieldPath="form[edad]";
    fieldArray[1].fieldFindType=FieldKeyType.ID;
    fieldArray[1].fieldType=FieldType.TEXTBOX;
    fieldArray[1].fieldValue="24";

    fieldArray[2].fieldPath="form[departamento]";
    fieldArray[2].fieldFindType=FieldKeyType.ID;
    fieldArray[2].fieldType=FieldType.DROPDOWN;
    fieldArray[2].fieldValue="La Paz";

    fieldArray[3].fieldPath="form[guardar]";
    fieldArray[3].fieldFindType=FieldKeyType.ID;
    fieldArray[3].fieldType=FieldType.BUTTON;
    fieldArray[3].fieldValue="";

    Assert.assertTrue(FormFiller.formFillElements( browserInstance, fieldArray));
    Assert.assertTrue(pages.InputDocProcess().continuebtn());
    pages.Main().logout();
    //openTask2(casenumber);
/*}

public void openTask2(int casenumber) throws Exception{ */
    pages.CronExecute().execute("workflow");
       String eventStatus= "";
        pages.gotoDefaultUrl();
        pages.Login().loginUser("iver","sample","workflow", "English");
        pages.Main().goHome();
        pages.Main().goAdmin();
        pages.Admin().goToLogs();
        eventStatus = pages.Admin().eventStatus(casenumber);
        Assert.assertEquals("CLOSE", eventStatus);
        pages.Main().goHome();
        pages.Home().gotoInbox().openCase(casenumber);
        pages.DynaformExecution().intoDynaform();
        FormFieldData[] fieldArray2=new FormFieldData[2];
        fieldArray2[0]=new FormFieldData();
        fieldArray2[1]=new FormFieldData();
    
    fieldArray2[0].fieldPath="form[estadocivil]";
    fieldArray2[0].fieldFindType=FieldKeyType.ID;
    fieldArray2[0].fieldType=FieldType.DROPDOWN;
    fieldArray2[0].fieldValue="soltero";

    fieldArray2[1].fieldPath="form[send]";
    fieldArray2[1].fieldFindType=FieldKeyType.ID;
    fieldArray2[1].fieldType=FieldType.BUTTON;
    fieldArray2[1].fieldValue="";

     Assert.assertTrue(FormFiller.formFillElements( browserInstance, fieldArray2));
        Assert.assertTrue(pages.InputDocProcess().continuebtn());
        pages.Main().logout();
    //openTask3(casenumber);

/*}

public void openTask3(int casenumber) throws Exception{ */
	pages.gotoDefaultUrl();
    pages.Login().loginUser("hector","sample","workflow", "English");
    pages.Main().goHome();
    pages.Home().gotoInbox().openCase(casenumber);
    pages.DynaformExecution().intoDynaform();
    FormFieldData[] fieldArray3=new FormFieldData[1];
    fieldArray3[0]=new FormFieldData();

     fieldArray3[0].fieldPath="form[send]";
     fieldArray3[0].fieldFindType=FieldKeyType.ID;
     fieldArray3[0].fieldType=FieldType.BUTTON;
     fieldArray3[0].fieldValue="";

	  Assert.assertTrue(FormFiller.formFillElements( browserInstance, fieldArray3));
        Assert.assertTrue(pages.InputDocProcess().continuebtn());
        pages.Main().logout();
    //openTask4(casenumber);

/*}

public void openTask4(int casenumber) throws Exception{   */
	 pages.gotoDefaultUrl();
        pages.Login().loginUser("admin","admin","workflow", "English");
    pages.Main().goHome();
    pages.Home().gotoInbox().openCase(casenumber);
    pages.DynaformExecution().intoDynaform();
	 FormFieldData[] fieldArray4=new FormFieldData[2];
	 fieldArray4[0]=new FormFieldData();
     fieldArray4[1]=new FormFieldData();

	 fieldArray4[0].fieldPath="form[profesion][ingeniero]";
	 fieldArray4[0].fieldFindType=FieldKeyType.ID;
	 fieldArray4[0].fieldType=FieldType.RADIOBUTTON;
	 fieldArray4[0].fieldValue="";

	 fieldArray4[1].fieldPath="form[send]";
	 fieldArray4[1].fieldFindType=FieldKeyType.ID;
	 fieldArray4[1].fieldType=FieldType.BUTTON;
	 fieldArray4[1].fieldValue="";

	  Assert.assertTrue(FormFiller.formFillElements( browserInstance, fieldArray4));
        Assert.assertTrue(pages.InputDocProcess().continuebtn());
    pages.InputDocProcess().switchToDefault();
    pages.Main().logout();

}


    @After
    public void cleanup(){
        browserInstance.quit();
    }

}