package com.colosa.qa.automatization.tests.processExecutionForEvents;

import com.colosa.qa.automatization.common.FieldKeyType;
import com.colosa.qa.automatization.common.FieldType;
import com.colosa.qa.automatization.common.FormFieldData;
import com.colosa.qa.automatization.common.FormFiller;
import com.colosa.qa.automatization.pages.DynaformExecution;
import org.junit.Assert;
import org.junit.Test;
import org.junit.After;

import java.io.IOException;

public class TestEventSingleTask extends com.colosa.qa.automatization.tests.common.Test{

    public TestEventSingleTask(String browserName) throws IOException {
        super(browserName);
    }

    @Test
	public void runProcess() throws Exception{
		pages.gotoDefaultUrl();
		pages.Login().loginUser("admin","admin","workflow", "English");
    pages.Main().goHome();
    int casenumber=pages.Home().gotoNewCase().startCase("Event Process - Intermediate Conditional_Single Task (Task 1)");
    DynaformExecution form = pages.DynaformExecution();
        form.intoDynaform();
    FormFieldData[] fieldArray=new FormFieldData[4];
    fieldArray[0]=new FormFieldData();
    fieldArray[1]=new FormFieldData();
    fieldArray[2]=new FormFieldData();
    fieldArray[3]=new FormFieldData();

    fieldArray[0].fieldPath="form[nombre]";
    fieldArray[0].fieldFindType=FieldKeyType.ID;
    fieldArray[0].fieldType=FieldType.TEXTBOX;
    fieldArray[0].fieldValue="rodrigo ivan";

    fieldArray[1].fieldPath="form[apellido]";
    fieldArray[1].fieldFindType=FieldKeyType.ID;
    fieldArray[1].fieldType=FieldType.TEXTBOX;
    fieldArray[1].fieldValue="lea plaza chavez";

    fieldArray[2].fieldPath="form[pais]";
    fieldArray[2].fieldFindType=FieldKeyType.ID;
    fieldArray[2].fieldType=FieldType.DROPDOWN;
    fieldArray[2].fieldValue="bolivia";

    fieldArray[3].fieldPath="form[guardar]";
    fieldArray[3].fieldFindType=FieldKeyType.ID;
    fieldArray[3].fieldType=FieldType.BUTTON;
    fieldArray[3].fieldValue="";

    Assert.assertTrue(FormFiller.formFillElements(browserInstance, fieldArray));
    	Assert.assertTrue(pages.InputDocProcess().continuebtn());
    pages.Main().logout();
    //openTask2(casenumber);
/*}
	public void openTask2(int casenumber) throws Exception{ */
        pages.CronExecute().execute("workflow");
		String eventStatus= "";
		pages.gotoDefaultUrl();
		pages.Login().loginUser("iver", "sample", "workflow", "English");
		pages.Main().goHome();
		pages.Main().goAdmin();
		pages.Admin().goToLogs();
		eventStatus = pages.Admin().eventStatus(casenumber);
		Assert.assertEquals("CLOSE", eventStatus);
        pages.Main().goHome();
		pages.Home().gotoInbox().openCase(casenumber);

        form.intoDynaform();
		FormFieldData[] fieldArray2=new FormFieldData[2];

		fieldArray2[0]=new FormFieldData();
		fieldArray2[1]=new FormFieldData();

		fieldArray2[0].fieldPath="form[tipocuenta][ahorro]";
		fieldArray2[0].fieldFindType=FieldKeyType.ID;
		fieldArray2[0].fieldType=FieldType.RADIOBUTTON;
		fieldArray2[0].fieldValue="";

		fieldArray2[1].fieldPath="form[send]";
		fieldArray2[1].fieldFindType=FieldKeyType.ID;
		fieldArray2[1].fieldType=FieldType.BUTTON;
		fieldArray2[1].fieldValue="";

     //Assert.assertTrue(FormFiller.formFillElements( browserInstance, fieldArray2));
    	Assert.assertTrue(pages.InputDocProcess().continuebtn());
    pages.Main().logout();
        //openTask3(casenumber);

	/*}

	public void openTask3(int casenumber) throws Exception{ */
		pages.gotoDefaultUrl();
		pages.Login().loginUser("hector","sample","workflow", "English");
        pages.Main().goHome();
		pages.Home().gotoInbox().openCase(casenumber);

        form.intoDynaform();
		FormFieldData[] fieldArray3=new FormFieldData[2];

		fieldArray3[0]=new FormFieldData();
		fieldArray3[1]=new FormFieldData();

		fieldArray3[0].fieldPath="form[aprobado]";
		fieldArray3[0].fieldFindType=FieldKeyType.ID;
		fieldArray3[0].fieldType=FieldType.DROPDOWN;
		fieldArray3[0].fieldValue="Yes";

		fieldArray3[1].fieldPath="form[send]";
		fieldArray3[1].fieldFindType=FieldKeyType.ID;
		fieldArray3[1].fieldType=FieldType.BUTTON;
		fieldArray3[1].fieldValue="";

     //Assert.assertTrue(FormFiller.formFillElements( browserInstance, fieldArray3));
    	Assert.assertTrue(pages.InputDocProcess().continuebtn());
        pages.InputDocProcess().switchToDefault();
        pages.Main().logout();

}


    @After
    public void cleanup(){
       browserInstance.quit();
    }

}